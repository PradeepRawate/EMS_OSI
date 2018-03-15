package com.osi.fas.configuration.mapper;


import java.util.List;

import com.osi.fas.domain.OsiCategories;
import com.osi.fas.service.dto.OsiCategoriesDTO;

/**
 * Mapper for the entity OsiCategories and its DTO OsiCategoriesDTO.
 */

public interface OsiCategoriesMapper {
	
	OsiCategoriesDTO osiCategoriesToOsiCategoriesDTO(OsiCategories osiCategories);

	List<OsiCategoriesDTO> osiCategoriesListToOsiCategoriesDTOList(List<OsiCategories> osiCategories);

	OsiCategories osiCategoriesDTOToOsiCategories(OsiCategoriesDTO osiCategoriesDTO);

	List<OsiCategories> osiCategoriesDTOListToOsiCategoriesList(List<OsiCategoriesDTO> osiCategoriesDTO);
	
}
