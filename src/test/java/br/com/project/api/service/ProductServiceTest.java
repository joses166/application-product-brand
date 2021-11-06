package br.com.project.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.project.api.entities.Brand;
import br.com.project.api.entities.Product;
import br.com.project.api.repository.BrandRepository;
import br.com.project.api.repository.ProductRepository;
import br.com.project.api.service.impl.ProductServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ProductServiceTest {

    ProductService productService;

    @MockBean
    ProductRepository productRepository;
    
    @MockBean
    BrandRepository brandRepository;

    @BeforeEach
    public void setUp() {
        this.productService = new ProductServiceImpl(productRepository, brandRepository);
    }

    @Test
    @DisplayName("Deve salvar um produto.")
    public void createAnProduct() {
    	// Cenário
		Brand brand = Brand.builder().id(1L).name("nestle").build();
		Product product = Product.builder().brand(brand).category("sorvete").name("flocos").build();
		Product productSaved = Product.builder().id(1L).brand(brand).category("sorvete").name("flocos").build();
		
		Mockito.when( this.brandRepository.findById(1L) ).thenReturn( Optional.of(brand) );
		Mockito.when( this.productRepository.save( product ) ).thenReturn( productSaved );
    	// Execução
		productSaved = this.productService.saveOrUpdate(product);
    	// Verificações
		assertThat(productSaved.getId()).isNotNull();
		assertThat(productSaved.getName()).isEqualTo(product.getName());
		assertThat(productSaved.getCategory()).isEqualTo(product.getCategory());
		assertThat(productSaved.getBrand().getId()).isEqualTo(product.getBrand().getId());
		assertThat(productSaved.getBrand().getName()).isEqualTo(product.getBrand().getName());
    }
	
}
