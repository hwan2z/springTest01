package com.hwan.order.model;

import com.hwan.order.domain.OrderItem;

public class OrderItemModel {
	private long id;
	private int amount;
	private ProductModel product;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public ProductModel getProduct() {
		return product;
	}
	public void setProduct(ProductModel product) {
		this.product = product;
	}
	
	public OrderItem buildDomain() {
		OrderItem item = new OrderItem();
		item.setId(id);
		item.setAmount(amount);
		item.setProduct(product.buildDomain());
		return item;
	}
	public void buildModel(OrderItem item) {
		id =  item.getId();
		amount = item.getAmount();
		product = new ProductModel();
		product.buildModel(item.getProduct());
	}
}
