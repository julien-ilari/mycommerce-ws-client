package fr.mycommerce.view.catalog.product;

import java.util.stream.Stream;

import lombok.Getter;

public enum FlowProductPage {

	DEFAULT(-1, "product"), EDIT(0, "product-edit"), COMBINATIONS(1, "product-combinations"),
	STOCK(2, "product-stock"), SHIPPING(3, "product-shipping"), Pricing(4, "product-pricing"),
	SEO(5, "product-seo");

	@Getter
	private Integer tabNUm;
	@Getter
	private String page;

	private FlowProductPage(Integer tabNUm, String page) {
		this.tabNUm = tabNUm;
		this.page = page;
	}

	public static Stream<FlowProductPage> stream() {
		return Stream.of(FlowProductPage.values());
	}

}