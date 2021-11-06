package br.com.project.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.project.api.entities.Brand;
import br.com.project.api.entities.Product;
import br.com.project.api.repository.BrandRepository;
import br.com.project.api.repository.ProductRepository;
import br.com.project.api.service.ProductService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final BrandRepository brandRepository;

	@Override
	public Product saveOrUpdate(Product product) {
		Optional<Brand> brand = this.brandRepository.findById(product.getBrand().getId());
		if (brand.get() == null)
			return null;
		product.setBrand(Brand.builder().id(brand.get().getId()).name(brand.get().getName()).build());
		return this.productRepository.save(product);
	}

	@Override
	public void delete(Long id) {
		this.productRepository.deleteById(id);
	}

	@Override
	public Product findById(Long id) {
		return this.productRepository.findById(id).orElse(null);
	}

	@Override
	public List<Product> findAll() {
		return this.productRepository.findAll();
	}

}
