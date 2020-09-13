package fr.mycommerce.view.catalog.product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import fr.mycommerce.service.store.product.ProductVariationDTO;
import fr.mycommerce.service.store.product.ProductVariationRestClient;
import fr.mycommerce.transverse.AbstractView;
import fr.mycommerce.transverse.ActionType;
import lombok.Getter;

@Named
@ViewScoped
public class AdminProductVariationMB extends AbstractView<ProductVariationDTO, Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	@RestClient
	@Getter
	private ProductVariationRestClient service;

	@Getter
	private Long productId;

	@Getter
	private Long variationId;

	public AdminProductVariationMB() {
		super(ProductVariationDTO.class);

		final String param1 = getValueParam("productId");
		if (param1 != null) {
			productId = Long.valueOf(param1);
		}
		final String param2 = getValueParam("variationId");
		if (param2 != null) {
			variationId = Long.valueOf(param2);
		}
	}

	public List<ProductVariationDTO> loadByProductId(Long productId) {
		this.productId = productId;
		loadItems(getService().get(productId));

		return this.items;
	}

	@Override
	protected void loadItems(List<ProductVariationDTO> items) {
		super.loadItems(items);

		if (variationId != null) {
			setModel(this.items.stream().filter(o -> o.getId().equals(variationId)).findAny().orElse(null));

			if (model != null && model.getId() != null) {
				this.action = ActionType.UPDATE;
			}
		}
	}

	@Override
	public List<ProductVariationDTO> callServiceGet() {
		if (productId == null) {
			return new ArrayList<>();
		}

		return getService().get(productId);
	}

	@Override
	public void callServicePost() {
		getService().post(productId, model);
	}

	@Override
	public void callServicePut() {
		// TODO Auto-generated method stub

	}

	@Override
	public void callServiceDelete(Long id) {
		getService().delete(productId, Arrays.asList(id));
	}

}
