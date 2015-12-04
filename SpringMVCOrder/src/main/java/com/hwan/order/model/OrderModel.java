package com.hwan.order.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hwan.order.domain.Order;
import com.hwan.order.domain.OrderItem;

//프로퍼티 에디터
// 매개 변수 타입을 모델 객체 타입으로 변환하는거할때나옴
public class OrderModel {	
	private long id;
	private Date orderDate;
	private CustomerModel customer;
	private List<OrderItemModel> items;
	
	public OrderModel() {
		orderDate = new Date();
		customer = new CustomerModel();
		items = new ArrayList<OrderItemModel>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public List<OrderItemModel> getItems() {
		return items;
	}

	public void setItems(List<OrderItemModel> items) {
		this.items = items;
	}
	
	public double getTotal() {
		double result = 0.0;
		for(OrderItemModel item : items){
			result += item.getProduct().getPrice() * item.getAmount();
		}
		return result;
	}
	
	public String toString() {
		String result =  "id : " + id + ", orderDate : " + orderDate + "\n";
		for(OrderItemModel item : items)
			result  += item.toString() + "\n";
		return result;
	}
	
	public Order buildDomain() {
		Order order = new Order();
		order.setId(id);
		order.setOrderDate(orderDate);
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		for(OrderItemModel item : items)
			orderItems.add(item.buildDomain());
		order.setItems(orderItems);
		return order;
	}

	public void buildModel(Order order) {
		id =  order.getId();
		orderDate = order.getOrderDate();
		List<OrderItemModel> items = new ArrayList<OrderItemModel>();
		for(OrderItem orderItem : order.getItems()) {
			OrderItemModel item = new OrderItemModel();
			item.buildModel(orderItem);
			items.add(item);
		}
	}
	

}
