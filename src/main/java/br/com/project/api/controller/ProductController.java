package br.com.project.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.api.converter.ProductConverter;
import br.com.project.api.dto.ProductCaUDTO;
import br.com.project.api.dto.ProductDTO;
import br.com.project.api.entities.Product;
import br.com.project.api.responses.Response;
import br.com.project.api.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(value = "Endpoint voltado a produtos.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

	private final ProductService productService;
	private final ProductConverter converter;

	@ApiOperation("Recupera a lista de produtos.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Produtos recuperados com sucesso."),
			@ApiResponse(code = 400, message = "Produtos não recuperados.") })
	@GetMapping
	public ResponseEntity<Response<List<ProductDTO>>> getAll() {
		Response<List<ProductDTO>> response = new Response<>();
		try {
			List<Product> product = this.productService.findAll();
			response.setData(converter.entitiesToDtos(product));
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			response.getErrors().add(ex.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@ApiOperation("Recupera um produto específico.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Produto recuperado com sucesso."),
			@ApiResponse(code = 400, message = "Produto não existe.") })
	@GetMapping("{id}")
	public ResponseEntity<Response<ProductDTO>> getById(@PathVariable("id") Long id) {
		Response<ProductDTO> response = new Response<>();
		try {
			Product product = this.productService.findById(id);
			response.setData(converter.entityToDto(product));
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			response.getErrors().add(ex.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@ApiOperation("Altera um produto.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Produto alterado com sucesso."),
			@ApiResponse(code = 400, message = "Produto não alterado.") })
	@PutMapping
	public ResponseEntity<Response<ProductDTO>> update(@RequestBody ProductCaUDTO productDTO) {
		Response<ProductDTO> response = new Response<>();
		try {
			if (this.productService.findById(productDTO.getId()) != null) {
				Product savedProduct = this.productService.saveOrUpdate(converter.cauDtoToEntity(productDTO));
				response.setData(converter.entityToDto(savedProduct));
				return ResponseEntity.ok(response);
			} else {
				response.getErrors().add("Informe um ID válido.");
				return ResponseEntity.badRequest().build();
			}
		} catch (Exception ex) {
			response.getErrors().add(ex.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@ApiOperation("Salva um produto.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Produto salvo com sucesso."),
			@ApiResponse(code = 400, message = "Produto não salvo.") })
	@PostMapping
	public ResponseEntity<Response<ProductDTO>> save(@RequestBody ProductCaUDTO productDTO) {
		Response<ProductDTO> response = new Response<>();
		try {
			Product savedProduct = this.productService.saveOrUpdate(converter.cauDtoToEntity(productDTO));
			ProductDTO entityToDto = converter.entityToDto(savedProduct);
			response.setData(entityToDto);
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			response.getErrors().add(ex.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@ApiOperation("Exclui um produto.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Produto excluído com sucesso."),
			@ApiResponse(code = 400, message = "Produto não excluído.") })
	@DeleteMapping("{id}")
	public ResponseEntity<Response<ProductDTO>> delete(@PathVariable("id") Long id) {
		Response<ProductDTO> response = new Response<>();
		try {
			this.productService.delete(id);
			return ResponseEntity.noContent().build();
		} catch (Exception ex) {
			response.getErrors().add(ex.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

}
