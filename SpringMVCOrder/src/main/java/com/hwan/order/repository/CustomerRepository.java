package com.hwan.order.repository;

import java.util.List;

import com.hwan.order.entity.CustomerEntity;
import com.hwan.order.entity.Pageable;

public interface CustomerRepository {
	CustomerEntity findOne(long id);
	CustomerEntity findOneByName(String name);
	List<CustomerEntity> findAll();
	List<CustomerEntity> findAll(Pageable page);
	List<CustomerEntity> findByName(String name);
	void save(CustomerEntity customer);
	void delete(long id);
}
