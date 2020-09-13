package fr.mycommerce.view.catalog;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import fr.mycommerce.service.store.category.CategoryDTO;
import fr.mycommerce.service.store.category.CategoryRestClient;
import fr.mycommerce.transverse.AbstractView;
import fr.mycommerce.transverse.ActionType;
import fr.mycommerce.view.ManagerCrud;
import lombok.Getter;
import lombok.Setter;

/**
 * Bean managed pour la partie administration des catégories
 * 
 * @author Julien ILARI
 *
 */
@Named
@javax.faces.view.ViewScoped
public class AdminCategoryMB extends AbstractView<CategoryDTO, Long> implements ManagerCrud<CategoryDTO, Long> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Nœud sélectionné
	 */
	@Getter
	@Setter
	private TreeNode selectedNode;

	/**
	 * Liste filtées de noeuds
	 */
	@Getter
	@Setter
	private List<TreeNode> filteredNode;

	/**
	 * Parent selectionné dans le formulaire d'édition
	 */
	@Getter
	@Setter
	private TreeNode parent;

	@Inject
	@RestClient
	@Getter
	private CategoryRestClient service;

	public AdminCategoryMB() {
		super(CategoryDTO.class);
	}

	@Override
	public void reset() {
		super.reset();
		parent = null;
		selectedNode = null;
	}

	@Override
	public void editAction(ActionEvent event) {
		model = (CategoryDTO) selectedNode.getData();
		action = ActionType.UPDATE;
	}

	public TreeNode getRoot() {
		if (items == null)
			return new DefaultTreeNode(new CategoryDTO(), null);

		final TreeNode root = new DefaultTreeNode();
		childTreeNode(root, items);

		return root;
	}

	private void childTreeNode(TreeNode node, List<CategoryDTO> childs) {
		if (childs.isEmpty())
			return;

		childs.forEach(child -> {
			final TreeNode childNode = new DefaultTreeNode(child, node);
			childTreeNode(childNode, child.getChildren());
		});
	}

	public void deleteNode(ActionEvent event) {
		if (selectedNode == null) {
			throw new IllegalArgumentException();
		}

		ActionType tmp = action;
		action = ActionType.DELETE;
		model = (CategoryDTO) selectedNode.getData();
		try {
			getService().delete(model.getId());
			successOfAction();
		} catch (Exception e) {
			failureOfAction();
		}

		event.getComponent().getAttributes().put("itemId", model.getId());
		afterDeleteAction(event.getComponent().getAttributes());

		action = tmp;
	}

	@Override
	protected void afterDeleteAction(final Map<String, Object> attributes) {
		super.afterDeleteAction(attributes);
		final Optional<Object> itemId = Optional.ofNullable(attributes.get("itemId"));

		// remove item dans la liste des enfants du parent de item
		if (itemId.isPresent()) {
			items.parallelStream().forEach(item -> {
				item.getChildren().removeIf(o -> o.getId().equals(itemId.get()));
			});
		}
	}

	@Override
	public void callServicePost() {
		if (parent != null) {
			final CategoryDTO parentDTO = (CategoryDTO) parent.getData();
			parentDTO.getChildren().add(model);

			getService().put(parentDTO);
		} else {
			getService().post(model);
		}
	}

	@Override
	public void callServicePut() {
		if (parent != null) {
			final CategoryDTO parentDTO = (CategoryDTO) parent.getData();
			parentDTO.getChildren().add(model);

			getService().put(parentDTO);
		} else {
			getService().put(model);
		}
	}

	@Override
	public void saveAction(ActionEvent event) {
		if (uploadedFile != null) {
			model.setBigPicture(uploadedFile.getContent());
		}
		super.saveAction(event);
	}

	public byte[] getPicture() {
		if (action == ActionType.CREATE) {
			return uploadedFile != null ? uploadedFile.getContent() : null;
		} else if (action == ActionType.UPDATE) {
			return model.getBigPicture();
		} else {
			return null;
		}
	}

	public String getImage() {
		return getImage(getPicture());
	}

	public String getImage(byte[] bytes) {
		if (bytes == null)
			return null;

		return "data:image/png;base64," + Base64.encodeBase64String(bytes);
	}

}
