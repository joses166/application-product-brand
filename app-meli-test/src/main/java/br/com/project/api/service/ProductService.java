package br.com.project.api.service;

import java.util.List;

import br.com.project.api.entities.Product;

public interface ProductService {

	Product saveOrUpdate(Product product);

	void delete(Long id);

	Product findById(Long id);

	List<Product> findAll();

}
