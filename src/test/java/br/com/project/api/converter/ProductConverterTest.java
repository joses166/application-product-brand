package br.com.project.api.converter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.project.api.dto.BrandDTO;
import br.com.project.api.dto.ProductDTO;
import br.com.project.api.entities.Product;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ProductConverterTest {

	private ProductConverter converter;

	public ProductConverterTest() {
		this.converter = new ProductConverter();
	}

	@Test
	@DisplayName("Deve converter a classe de DTO para Entity")
	public void executeConversionDtoToEntity() {
		// Cenário
		ProductDTO product = ProductDTO.builder().brand(BrandDTO.builder().id(1L).name("teste").build())
				.category("teste").name("teste").id(1L).build();
		// Execução
		Product converted = converter.dtoToEntity(product);
		// Verificações
		assertThat(product.getBrand().getId()).isEqualTo(converted.getBrand().getId());
		assertThat(product.getBrand().getName()).isEqualTo(converted.getBrand().getName());
		assertThat(product.getCategory()).isEqualTo(converted.getCategory());
		assertThat(product.getId()).isEqualTo(converted.getId());
		assertThat(product.getName()).isEqualTo(converted.getName());
	}

}
