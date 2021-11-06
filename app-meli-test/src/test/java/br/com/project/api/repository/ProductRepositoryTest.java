package br.com.project.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.project.api.entities.Brand;
import br.com.project.api.entities.Product;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class ProductRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ProductRepository repository;

	@Test
	@DisplayName("Deve salvar um produto.")
	public void createAnUserTest() {
		// Cenário
		Brand brand = Brand.builder().name("nestle").build();
		entityManager.persist(brand);
		Product product = Product.builder().brand(brand).category("sorvete").name("flocos").build();
		// Execução
		Product savedProduct = this.repository.save(product);
		// Verificações
		assertThat(savedProduct.getId()).isNotNull();
		assertThat(savedProduct.getName()).isEqualTo(product.getName());
		assertThat(savedProduct.getCategory()).isEqualTo(product.getCategory());
		assertThat(savedProduct.getBrand().getId()).isEqualTo(product.getBrand().getId());
		assertThat(savedProduct.getBrand().getName()).isEqualTo(product.getBrand().getName());
	}

}
