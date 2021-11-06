package br.com.project.api.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.project.api.converter.ProductConverter;
import br.com.project.api.dto.BrandDTO;
import br.com.project.api.dto.ProductCaUDTO;
import br.com.project.api.dto.ProductDTO;
import br.com.project.api.entities.Brand;
import br.com.project.api.entities.Product;
import br.com.project.api.service.ProductService;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

    private static final String PRODUCT_API = "/api/v1/product";

    @Autowired
    MockMvc mvc;

    @MockBean
    private ProductService productService;
    
    @MockBean
    private ProductConverter productConverter;
    
    @Test
    @DisplayName("Deve salvar um novo produto.")
    public void createProductTest() throws Exception {
        // Cenário
        ProductCaUDTO productCaU = ProductCaUDTO.builder().brand_id(1L).category("teste").id(1L).name("teste").build();
    	String json = new ObjectMapper().writeValueAsString(productCaU);
    	
    	Product product = Product.builder().brand(Brand.builder().id(1L).name("teste").build()).category("teste").id(1L).name("teste").build();
    	ProductDTO productDTO = ProductDTO.builder().brand(BrandDTO.builder().id(1L).name("teste").build()).category("teste").id(1L).name("teste").build();
    	
    	BDDMockito.when( this.productService.saveOrUpdate(Mockito.any()) ).thenReturn(product);
    	BDDMockito.when( this.productConverter.entityToDto(Mockito.any()) ).thenReturn(productDTO);
    	
        // Execução
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(PRODUCT_API)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        // Verificações
        mvc.perform(request)
            .andExpect( status().isOk() )
            .andExpect( jsonPath("data.id").isNotEmpty() )
            .andExpect( jsonPath("data.name").value("teste") );
    }
	
}
