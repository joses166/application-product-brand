package br.com.project.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCaUDTO {

	private Long id;
	private String name;
	private String category;
	private Long brand_id;

}
