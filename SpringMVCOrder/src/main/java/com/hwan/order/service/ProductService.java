package com.hwan.order.service;

import java.util.List;

import com.hwan.order.domain.Product;

public interface ProductService {
	Product getProduct(long id);
	List<Product> getProducts();
	List<Product> getProductsByPage(int index, int size);
	void saveProduct(Product product);
	void updateProduct(Product product);
	void deleteProduct(long id);
}
