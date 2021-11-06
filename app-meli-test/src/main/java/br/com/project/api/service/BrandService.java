package br.com.project.api.service;

import java.util.List;

import br.com.project.api.entities.Brand;

public interface BrandService {

	Brand saveOrUpdate(Brand brand);

	void delete(Long id);

	Brand findById(Long id);

	List<Brand> findAll();

}
