package br.com.project.api.converter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.project.api.dto.BrandCaUDTO;
import br.com.project.api.entities.Brand;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BrandConverterTest {

	private BrandConverter brandConverter;

	public BrandConverterTest() {
		this.brandConverter = new BrandConverter();
	}

	@Test
	@DisplayName("Deve converter a classe.")
	public void executeTestConvertingClassDtoToEntity() {
		// Cenário
		BrandCaUDTO brand = BrandCaUDTO.builder().id(1L).name("testeMarca").build();
		// Execução
		Brand brandConverted = brandConverter.cauDtoToEntity(brand);
		// Verificações
		assertThat(brand.getId()).isEqualTo(brandConverted.getId());
		assertThat(brand.getName()).isEqualTo(brandConverted.getName());
	}

}
