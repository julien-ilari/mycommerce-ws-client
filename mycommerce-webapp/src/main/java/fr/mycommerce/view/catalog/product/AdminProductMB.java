package fr.mycommerce.view.catalog.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.primefaces.event.SelectEvent;

import fr.mycommerce.service.store.product.ProductLangDTO;
import fr.mycommerce.service.store.product.ProductRestClient;
import fr.mycommerce.transverse.AbstractView;
import fr.mycommerce.transverse.ActionType;
import fr.mycommerce.view.ManagerCrud;
import lombok.Getter;
import lombok.Setter;

@Named
//@FlowScoped(value = "product")
//@javax.faces.view.ViewScoped
@ConversationScoped
public class AdminProductMB extends AbstractView<ProductLangDTO, Long>
		implements ManagerCrud<ProductLangDTO, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	@RestClient
	@Getter
	private ProductRestClient service;
	
	@Inject @Getter
	private AdminProductVariationMB variationMB;

	@Getter
	@Setter
	private String selectedImage;

	@Getter
	@Setter
	private List<String> images;

	@Inject
	private Conversation conversation;

	@Getter
	@Setter
	protected int activeIndexTabMenu;

	public AdminProductMB() {
		super(ProductLangDTO.class);

	}

	@PostConstruct
	public void postConstruct() {
		startConversation();

		images = new ArrayList<String>();
		for (int i = 1; i <= 12; i++) {
			images.add("nature" + i + ".jpg");
		}
	}

	public void onLoad() {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		// la demande n'est pas une publication:
		if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
			// extraire l'id de la chaîne de requête
			FacesContext fc = FacesContext.getCurrentInstance();
			Map<String, String> paramMap = fc.getExternalContext().getRequestParameterMap();
			String id = paramMap.get("id");

			load(id);
		}
	}

	public void load(String id) {
		if (id != null && !id.isEmpty() && action == ActionType.DEFAULT) {
			startConversation();
			model = service.getById(Long.valueOf(id));

			action = (model != null && model.getId() != null) ? ActionType.UPDATE : ActionType.DEFAULT;
		}
	}

	/**
	 * Démarre une nouvelle conversation
	 */
	protected void startConversation() {
		if (conversation.isTransient()) {
			conversation.setTimeout(
					((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
							.getSession().getMaxInactiveInterval() * 1000);
			conversation.begin();
		}
	}

	/**
	 * Fermeture de la conversation en-cours.
	 * <p>
	 * Vous pouvez appeler cette méthode dans un actionListener sur toute action de
	 * navigation JSF.
	 * </p>
	 */
	public void endConversation() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}

	/**
	 * Action : changement du menu active
	 * 
	 * @param event
	 */
	public void tabMenuAction(ActionEvent event) {
		String value = (String) event.getComponent().getAttributes().get("activeIndexTabMenu");
		this.activeIndexTabMenu = value != null ? Integer.valueOf(value) : -1;
	}

	public String showPage() {
		final FlowProductPage flowProductPage = FlowProductPage.stream()
				.parallel()
				.filter(o -> o.getTabNUm().equals(activeIndexTabMenu))
				.findAny()
				.orElse(FlowProductPage.DEFAULT);

		if (flowProductPage == FlowProductPage.COMBINATIONS) {
			return FlowProductPage.COMBINATIONS.getPage() + "?faces-redirect=true&productId=" + model.getId();
		}

		return flowProductPage.getPage();
	}

	@Override
	public void reset(ActionEvent event) {
		if (action == ActionType.UPDATE || action == ActionType.CREATE) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			NavigationHandler myNav = facesContext.getApplication().getNavigationHandler();
			myNav.handleNavigation(facesContext, null, FlowProductPage.DEFAULT.getPage() + "?faces-redirect=true");
		}

		super.reset();
	}

	@Override
	public void onRowDblSelect(SelectEvent<ProductLangDTO> event) {
		super.onRowDblSelect(event);

		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		NavigationHandler myNav = facesContext.getApplication().getNavigationHandler();
		myNav.handleNavigation(facesContext, null,
				FlowProductPage.EDIT.getPage() + "?faces-redirect=true&id=" + model.getId());
	}
	
	public void onRowSelect(SelectEvent<ProductLangDTO> event) {
		model = event.getObject();
		variationMB.loadByProductId(event.getObject().getId());
	}

	@Override
	public void editAction(ActionEvent event) {
		super.editAction(event);

		FacesContext facesContext = FacesContext.getCurrentInstance();
		NavigationHandler myNav = facesContext.getApplication().getNavigationHandler();
		myNav.handleNavigation(facesContext, null,
				FlowProductPage.EDIT.getPage() + "?faces-redirect=true&id=" + model.getId());
	}

	@Override
	public void saveAction(ActionEvent event) {
		super.saveAction(event);

		// On reste en mode mise à jour
		action = ActionType.UPDATE;
	}

	@Override
	public void postSaveAction() {
		if (action == ActionType.CREATE) {
			super.postSaveAction();
		}
	}


	public String getImage() {
		return getImage(getBigPicture());
	}

	public byte[] getBigPicture() {

		if (action == ActionType.CREATE && uploadedFile != null && uploadedFile.getContent() != null) {
			return uploadedFile.getContent();
		}

		if (action == ActionType.UPDATE && uploadedFile != null && uploadedFile.getContent() != null) {
			return uploadedFile.getContent();
		}

		if (action == ActionType.UPDATE && model != null && model.getPicture() != null) {
			return model.getPicture();
		}

		return null;

	}

}
