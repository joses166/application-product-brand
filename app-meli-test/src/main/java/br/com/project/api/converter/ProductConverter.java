package br.com.project.api.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.project.api.dto.BrandDTO;
import br.com.project.api.dto.ProductCaUDTO;
import br.com.project.api.dto.ProductDTO;
import br.com.project.api.entities.Brand;
import br.com.project.api.entities.Product;

@Service
public class ProductConverter {

	public Product dtoToEntity(ProductDTO productDTO) {
		return Product.builder().category(productDTO.getCategory())
				.brand(Brand.builder().id(productDTO.getBrand().getId()).name(productDTO.getBrand().getName()).build()).name(productDTO.getName())
				.id(productDTO.getId()).build();
	}

	public ProductDTO entityToDto(Product entity) {
		return ProductDTO.builder().id(entity.getId()).name(entity.getName()).category(entity.getCategory())
				.brand(BrandDTO.builder().id(entity.getBrand().getId()).name(entity.getBrand().getName()).build()).build();
	}

	public List<Product> dtosToEntities(List<ProductDTO> dtos) {
		List<Product> products = new ArrayList<Product>();

		dtos.forEach(productDTO -> {
			products.add(Product.builder().category(productDTO.getCategory())
					.brand(Brand.builder().id(productDTO.getBrand().getId()).name(productDTO.getBrand().getName()).build()).name(productDTO.getName())
					.id(productDTO.getId()).build());
		});

		return products;
	}

	public List<ProductDTO> entitiesToDtos(List<Product> entities) {
		List<ProductDTO> dtos = new ArrayList<ProductDTO>();

		entities.forEach(entity -> {
			dtos.add(ProductDTO.builder().id(entity.getId()).name(entity.getName()).category(entity.getCategory())
					.brand(BrandDTO.builder().id(entity.getBrand().getId()).name(entity.getBrand().getName()).build()).build());
		});

		return dtos;
	}

	public Product cauDtoToEntity(ProductCaUDTO productDTO) {
		return Product.builder().category(productDTO.getCategory())
				.brand(Brand.builder().id(productDTO.getBrand_id()).build()).name(productDTO.getName())
				.id(productDTO.getId()).build();
	}

}
