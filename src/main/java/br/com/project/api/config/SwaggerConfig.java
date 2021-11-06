package br.com.project.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.project.api.controller")) // Todas as apis que
																							// estiverem no pacote serão
																							// documentadas
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	public ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("API Teste").description("API do Projeto de produtos e marcas.")
				.version("1.0.0").contact(contact()).build();
	}

	public Contact contact() {
		return new Contact("José Hamilton", "https://github.com/joses166", "ton.ton.leite@hotmai.com");
	}

}