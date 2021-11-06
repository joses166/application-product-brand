package br.com.project.api.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.project.api.dto.BrandCaUDTO;
import br.com.project.api.dto.BrandDTO;
import br.com.project.api.dto.ProductDTO;
import br.com.project.api.entities.Brand;
import br.com.project.api.entities.Product;

@Service
public class BrandConverter {

	private ProductConverter converter;
	
	public BrandConverter() {
		this.converter = new ProductConverter();
	}
	
	public Brand dtoToEntity(BrandDTO dto) {
		List<Product> listproducts = new ArrayList<>();
		if (dto.getProducts() != null && !dto.getProducts().isEmpty()) listproducts.addAll(converter.dtosToEntities(dto.getProducts()));
		return Brand.builder().id(dto.getId()).name(dto.getName()).products(listproducts).build();
	}

	public BrandDTO entityToDto(Brand entity) {
		List<ProductDTO> listproducts = new ArrayList<>();
		if (entity.getProducts() != null && !entity.getProducts().isEmpty()) listproducts.addAll(converter.entitiesToDtos(entity.getProducts()));
		return BrandDTO.builder().id(entity.getId()).name(entity.getName()).products(listproducts).build();
	}

	public List<Brand> dtosToEntities(List<Brand> dtos) {
		List<Brand> brands = new ArrayList<Brand>();

		dtos.forEach(dto -> {
			brands.add(Brand.builder().id(dto.getId()).name(dto.getName()).products(dto.getProducts()).build());
		});

		return brands;
	}

	public List<BrandDTO> entitiesToDtos(List<Brand> entities) {
		List<BrandDTO> dtos = new ArrayList<BrandDTO>();
		List<ProductDTO> listproducts = new ArrayList<>();
		entities.forEach(entity -> {
			if (entity.getProducts() != null && !entity.getProducts().isEmpty()) listproducts.addAll(converter.entitiesToDtos(entity.getProducts()));
			dtos.add(BrandDTO.builder()
					.id(entity.getId())
					.name(entity.getName())
					.products(listproducts)
					.build());
			listproducts.clear();
		});

		return dtos;
	}

	public Brand cauDtoToEntity(BrandCaUDTO dto) {
		return Brand.builder().id(dto.getId()).name(dto.getName()).build();
	}

}
