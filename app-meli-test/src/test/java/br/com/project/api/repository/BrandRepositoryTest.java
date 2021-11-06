package br.com.project.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.project.api.entities.Brand;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class BrandRepositoryTest {

	@Autowired
	private BrandRepository brandRepository;

	@Test
	@DisplayName("Deve salvar uma marca.")
	public void createAnUserTest() {
		// Cenário
		Brand brand = Brand.builder().name("nestle").build();
		// Execução
		Brand savedBrand = this.brandRepository.save(brand);
		// Verificações
		assertThat(savedBrand.getId()).isNotNull();
		assertThat(savedBrand.getName()).isEqualTo(brand.getName());
	}

}
