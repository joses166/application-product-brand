package br.com.project.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.project.api.entities.Brand;
import br.com.project.api.repository.BrandRepository;
import br.com.project.api.service.BrandService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

	private final BrandRepository repository;

	@Override
	public Brand saveOrUpdate(Brand brand) {
		return this.repository.save(brand);
	}

	@Override
	public void delete(Long id) {
		this.repository.deleteById(id);
	}

	@Override
	public Brand findById(Long id) {
		return this.repository.findById(id).orElse(null);
	}

	@Override
	public List<Brand> findAll() {
		return this.repository.findAll();
	}

}
