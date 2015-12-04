package com.hwan.order.service;

import java.util.List;

import com.hwan.order.domain.Customer;

public interface CustomerService {
	Customer getCustomer(long id);
	Customer getCustomerByName(String name);
	List<Customer> getCustomers();
	List<Customer> getCustomersByName(String name);
	List<Customer> getCustomerByPage(int index, int size);
	void saveCustomer(Customer customer);
	void updateCustomer(Customer customer);
	void deleteCustomer(long id);
}
