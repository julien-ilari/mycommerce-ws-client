package fr.mycommerce.transverse;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.Part;

import org.apache.commons.codec.binary.Base64;
import org.ocpsoft.shade.org.apache.commons.beanutils.BeanUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.TreeNode;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.IOUtils;

import fr.mycommerce.exception.MissingArgumentException;
import fr.mycommerce.exception.MissingModelException;
import fr.mycommerce.service.ID;
import fr.mycommerce.view.Manager;
import lombok.Getter;
import lombok.Setter;

/**
 * <h1>Classe d'administration générique</h1>
 * <p>
 * Gère les actions crud (create, read, update, delete)
 * </p>
 * 
 * @author Julien ILARI
 *
 */
public abstract class AbstractView<M extends ID<I>, I extends Serializable> implements Manager<M, I> {

	private static final long serialVersionUID = 1L;

	@Getter
	protected DataModel<M> dataModels;
	
	@Getter
	@Setter
	protected List<DataModel<M>> test;
	
	@Getter
	private TreeNode root;

	/**
	 * Type oppération
	 */
	@Getter
	@Setter
	protected ActionType action;

	protected Constructor<M> constructor;

	/**
	 * DataTable, nos éléments
	 */
	@Getter
	protected List<M> items;

	/**
	 * DataTable, éléments filtrés Attention : M.A.J à la charge du framework
	 * primefaces
	 */
	@Getter
	@Setter
	private List<M> filteredItems;

	/**
	 * DataTable, selection multiple Attention : M.A.J à la charge du framework
	 * primefaces
	 */
	@Getter
	@Setter
	private List<M> selectedItems;

	/**
	 * DataTable, selection simple Attention : M.A.J à la charge du framework
	 * primefaces
	 */
	@Getter
	@Setter
	protected M model;

	@Getter
	@Setter
	protected Part file;

	/**
	 * Gestion de upload
	 */
	@Getter
	protected UploadedFile uploadedFile;
	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
		if (uploadedFile != null) {
			try {
				BeanUtils.copyProperty(model, "picture", uploadedFile.getContent());
			} catch (Exception e) {
				// Ignore
			}
		}
	}

	@Getter
	@Setter
	protected List<UploadedFile> uploadedFiles;

	@Getter
	@Setter
	private DataTable dataTable;

	public AbstractView(Class<M> c) {
		dataModels = new ListDataModel<M>();
		items = new ArrayList<>();
		try {
			this.constructor = c.getConstructor();
		} catch (NoSuchMethodException x) {
			throw new IllegalArgumentException(x);
		}
		reset();
	}

	@PostConstruct
	public void postConstructAbstractView() {
		//TODO : il faudra réactiver cette partie loadItems(callServiceGet());
	}
	
	protected void loadItems(List<M> items)
	{
		try {
			this.items.clear();
			this.items = Optional.ofNullable(items).orElse(Collections.emptyList());
			dataModels.setWrappedData(items);
		} catch (Exception e) {
			addFlashMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
		}
	}

	public void reset() {
		model = newInstance();
		uploadedFile = null;
		this.action = ActionType.DEFAULT;
	}

	/**
	 * Action upload d'un fichier
	 * 
	 * @param event
	 */
	public void fileUploadEvent(FileUploadEvent event) {
		uploadedFile = event.getFile();
	}

	public void reset(ActionEvent event) {
		reset();

		// ---- Update dataTable ---- //
		if (null != dataTable) {
			PrimeFaces.current().executeScript("PF('" + dataTable.getWidgetVar() + "').filter();");
		}
	}

	/**
	 * Action passage mode création
	 * 
	 * @param event
	 */
	public void newItem(ActionEvent event) {
		this.model = newInstance();
		action = ActionType.CREATE;
	}

	/**
	 * Action selectionner item
	 * 
	 * @param event
	 */
	public void onRowDblSelect(SelectEvent<M> event) {
		model = event.getObject();
		action = ActionType.UPDATE;
	}

	/**
	 * Action demande d'édition de l'item
	 * 
	 * @param event
	 */
	public void editAction(ActionEvent event) {
		final Object id = Optional.ofNullable(event.getComponent().getAttributes().get("itemId"))
				.orElseThrow(() -> new MissingArgumentException("itemId"));

		model = items.stream().filter(o -> o.getId().equals(id)).findAny()
				.orElseThrow(() -> new MissingModelException());

		action = ActionType.UPDATE;
	}

	/**
	 * Action sauvegarde item
	 * <h2>Action(s)</h2>
	 * <ul>
	 * <li>ActionType.CREATE</li>
	 * <li>ActionType.UPDATE</li>
	 * </ul>
	 */
	public void saveAction(ActionEvent event) {
		if (uploadedFile != null) {
			try {
				BeanUtils.copyProperty(model, "picture", uploadedFile.getContent());
			} catch (Exception e) {
				// Ignore
			}
		}
		
		
		try {
			if (action == ActionType.CREATE) {
				callServicePost();
			} else if (action == ActionType.UPDATE) {
				callServicePut();
			}
			successOfAction();
			postSaveAction();
		} catch (Exception e) {
			failureOfAction();
		}

	}

	public void postSaveAction() {
		reset();
		postConstructAbstractView();
	}

	/**
	 * Succès de l'action CRéation d'un facesMessage
	 */
	protected void successOfAction() {
		addFlashMessage(FacesMessage.SEVERITY_INFO, "success of the action " + action.name().toLowerCase());
		// ---- Update dataTable ---- //
		if (null != dataTable) {
			PrimeFaces.current().executeScript("PF('" + dataTable.getWidgetVar() + "').filter();");
		}
	}

	/**
	 * Passage de la validation en échec avec création d'un facesMessage. Demande de
	 * passer à la phase Response, en ignorant les phases non encore exécutées.
	 */
	protected void failureOfAction() {
		addFlashMessage(FacesMessage.SEVERITY_ERROR, "failure of the action " + action.name().toLowerCase());
	}

	/**
	 * ActionEvent event supprimer
	 * 
	 * @param event
	 */
	@SuppressWarnings("unchecked")
	public void deleteAction(ActionEvent event) {

		ActionType tmp = action;
		action = ActionType.DELETE;
		final I id = Optional.ofNullable((I) event.getComponent().getAttributes().get("itemId"))
				.orElseThrow(() -> new MissingArgumentException("itemId"));
		try {
			callServiceDelete(id);
			successOfAction();
		} catch (Exception e) {
			failureOfAction();
		}

		afterDeleteAction(event.getComponent().getAttributes());

		action = tmp;
	}

	/**
	 * TODO : créer un test-unitaire
	 * <h1>Après action de suppression</h1>
	 * <h2>Etape(s)</h2>
	 * <ul>
	 * <li>appel du service distant pour suppression</li>
	 * <li>rafraîchir la liste (supp. dans items)</li>
	 * <li>rafraîchir le formulaire d'édition (appel la méthode reset)</li>
	 * </ul>
	 * <p>
	 * Les étapes de rafraîchissement sans soliciter le service distant, afin de
	 * réduire les appels.
	 * </p>
	 * 
	 * @param id
	 */
	@SuppressWarnings("unchecked")
	protected void afterDeleteAction(final Map<String, Object> attributes) {
		final Optional<I> id = Optional.ofNullable((I) attributes.get("itemId"));
		if (id.isPresent()) {
			items.removeIf(o -> o.getId().equals(id.get()));
			if (model != null && model.getId() != null && model.getId().equals(id.get())) {
				reset();
			}
		}
	}

	protected static void addFlashMessage(final Severity severityName, final String message) {
		FacesMessage facesMessage = null;
		if (severityName.equals(FacesMessage.SEVERITY_INFO)) {
			facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!", message);
		} else if (severityName.equals(FacesMessage.SEVERITY_ERROR)) {
			facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", message);
		}else
		{
			return;
		}

		final FacesContext facesContext = FacesContext.getCurrentInstance();
//		final Flash flash = facesContext.getExternalContext().getFlash();
//		flash.setKeepMessages(true);
//		flash.setRedirect(true);

		facesContext.addMessage(null, facesMessage);
	}

	private M newInstance() {
		try {
			return constructor.newInstance();
		} catch (Exception x) {
			throw new RuntimeException("error.techn.model.create-instance", x);
		}
	}

	/**
	 * Obtient fichier sous forme d'octet
	 * 
	 * @exception IllegalArgumentException [error.arg.null] - argument file est null
	 * @param file élément reçu dans une demande POST multipart/form-data.
	 * @return le tableau d'octets demandé
	 */
	protected static byte[] encodeToFile(final Part file) {
		if (file == null)
			return null;
		try (InputStream input = file.getInputStream()) {
			/*
			 * toByteArray met l'entrée en mémoire tampon en interne, il n'est donc pas
			 * nécessaire d'utiliser un BufferedInputStream.
			 */
			return IOUtils.toByteArray(input);
		} catch (IOException e) {
			throw new RuntimeException("error.techn.io.encode", e);
		}
	}

	protected String getValueParam(String param) {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		// la demande n'est pas une publication:
		if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
			// extraire l'id de la chaîne de requête
			FacesContext fc = FacesContext.getCurrentInstance();
			Map<String, String> paramMap = fc.getExternalContext().getRequestParameterMap();
			return paramMap.get(param);
		}

		return null;
	}
	
	public String getImage(byte[] flux) {
		if (flux == null)
			return "http://placehold.it/372x372";
					
//					"data:image/png;base64,"
//					+ "iVBORw0KGgoAAAANSUhEUgAAAHwAAAB8CAYAAACrHtS+AAAAxklEQVR42u3RQQEAAAQEMFfLUzHVBbFVWHp6izciXDjCEY5whCMc4QhHOMIRjnCEC0c4whGOcIQjHOEIRzjCEY5whAtHOMIRjnCEIxzhCEc4whGOcOEIRzjCEY5whCMc4QhHOMIRjnDhCEc4whGOcIQjHOEIRzjCES5cuHCEIxzhCEc4whGOcIQjHOEIF45whCMc4QhHOMIRjnCEIxzhCBeOcIQjHOEIRzjCEY5whCMc4cIRjnCEIxzhCEc4whGOcIQjHOH/HBqhlilYoS+oAAAAAElFTkSuQmCC";

		return "data:image/png;base64," + Base64.encodeBase64String(flux);
	}
	
    private RenderMode findBrowserRenderMode() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        String userAgent = externalContext.getRequestHeaderMap().get("User-Agent");
        return userAgent.toLowerCase().contains("mobile") ? RenderMode.MOBILE : RenderMode.WEB;
    }
    
    public String findRenderMode() {
        return findBrowserRenderMode().name();
    }
    
    public void switchTo(String renderMode) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String viewId = facesContext.getViewRoot().getViewId();
        RenderMode nextRenderMode = RenderMode.valueOf(renderMode);
        ExternalContext externalContext = facesContext.getExternalContext();
        String context = externalContext.getRequestContextPath();
        viewId = viewId.substring(viewId.lastIndexOf("/") + 1);
        externalContext.redirect(String.format("%s/%s/%s", context, nextRenderMode.name().toLowerCase(), viewId));
    }

}
