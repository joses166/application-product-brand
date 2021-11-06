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

import br.com.project.api.converter.BrandConverter;
import br.com.project.api.dto.BrandCaUDTO;
import br.com.project.api.dto.BrandDTO;
import br.com.project.api.entities.Brand;
import br.com.project.api.responses.Response;
import br.com.project.api.service.BrandService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {

	private final BrandService brandService;
	private final BrandConverter converter;

	@ApiOperation("Recupera a lista de marcas.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Marcas recuperados com sucesso."),
			@ApiResponse(code = 400, message = "Marcas não recuperados.") })
	@GetMapping
	public ResponseEntity<Response<List<BrandDTO>>> getAll() {
		Response<List<BrandDTO>> response = new Response<>();
		try {
			List<Brand> brand = this.brandService.findAll();
			response.setData(converter.entitiesToDtos(brand));
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			response.getErrors().add(ex.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@ApiOperation("Recupera um marca específico.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Marca recuperado com sucesso."),
			@ApiResponse(code = 400, message = "Marca não existe.") })
	@GetMapping("{id}")
	public ResponseEntity<Response<BrandDTO>> getById(@PathVariable("id") Long id) {
		Response<BrandDTO> response = new Response<>();
		try {
			Brand brand = this.brandService.findById(id);
			response.setData(converter.entityToDto(brand));
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			response.getErrors().add(ex.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@ApiOperation("Altera um marca.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Marca alterado com sucesso."),
			@ApiResponse(code = 400, message = "Marca não alterado.") })
	@PutMapping
	public ResponseEntity<Response<BrandDTO>> update(@RequestBody BrandCaUDTO brandDTO) {
		Response<BrandDTO> response = new Response<>();
		try {
			Brand savedBrand = this.brandService.saveOrUpdate(converter.cauDtoToEntity(brandDTO));
			response.setData(converter.entityToDto(savedBrand));
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			response.getErrors().add(ex.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@ApiOperation("Salva um marca.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Marca salvo com sucesso."),
			@ApiResponse(code = 400, message = "Marca não salvo.") })
	@PostMapping
	public ResponseEntity<Response<BrandDTO>> save(@RequestBody BrandCaUDTO brandDTO) {
		Response<BrandDTO> response = new Response<>();
		try {
			Brand savedBrand = this.brandService.saveOrUpdate(converter.cauDtoToEntity(brandDTO));
			response.setData(converter.entityToDto(savedBrand));
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			response.getErrors().add(ex.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@ApiOperation("Exclui um marca.")
	@ApiResponses({ @ApiResponse(code = 200, message = "Marca excluído com sucesso."),
			@ApiResponse(code = 400, message = "Marca não excluído.") })
	@DeleteMapping("{id}")
	public ResponseEntity<Response<BrandDTO>> delete(@PathVariable("id") Long id) {
		Response<BrandDTO> response = new Response<>();
		try {
			this.brandService.delete(id);
			return ResponseEntity.noContent().build();
		} catch (Exception ex) {
			response.getErrors().add(ex.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

}
