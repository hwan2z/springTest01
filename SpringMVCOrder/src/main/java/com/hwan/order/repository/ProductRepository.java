package com.hwan.order.repository;

import java.util.List;

import com.hwan.order.entity.Pageable;
import com.hwan.order.entity.ProductEntity;

public interface ProductRepository {
	ProductEntity findOne(long id);
	List<ProductEntity> findAll();
	List<ProductEntity> findAll(Pageable page);
	void save(ProductEntity product);
	void delete(long id);
}
