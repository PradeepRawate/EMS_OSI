package com.osi.fas.configuration.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.fas.configuration.mapper.OsiCategoriesMapper;
import com.osi.fas.domain.OsiCategories;
import com.osi.fas.service.dto.OsiCategoriesDTO;
import com.osi.fas.service.dto.OsiCoaDTO;
@Component
public class OsiCategoriesMapperImpl implements OsiCategoriesMapper {
	
	@Override
	public OsiCategoriesDTO osiCategoriesToOsiCategoriesDTO(OsiCategories osiCategories) {

 		OsiCategoriesDTO osiCategoriesDTO = new OsiCategoriesDTO();
 		osiCategoriesDTO.setActive(osiCategories.getActive());
 		osiCategoriesDTO.setCreatedBy(osiCategories.getCreatedBy());
 		osiCategoriesDTO.setUpdatedBy(osiCategories.getUpdatedBy());
 		osiCategoriesDTO.setCreatedDate(osiCategories.getCreatedDate());
 		osiCategoriesDTO.setUpdatedDate(osiCategories.getUpdatedDate());
		osiCategoriesDTO.setBusinessGroupId(osiCategories.getBusinessGroupId());
		osiCategoriesDTO.setCategoryId(osiCategories.getCategoryId());
		osiCategoriesDTO.setCategoryCode(osiCategories.getCategoryCode());
		osiCategoriesDTO.setCategoryDesc(osiCategories.getCategoryDesc());
		osiCategoriesDTO.setCategoryName(osiCategories.getCategoryName());
		osiCategoriesDTO.setOsiSegmentStructureHdrId(osiCategories.getOsiSegmentStructureHdrId());
		if(osiCategories.getOsiCoa()!=null){
			OsiCoaDTO coaDto = new OsiCoaDTO();
			coaDto.setCoaId(osiCategories.getOsiCoa().getCoaId());
			coaDto.setCoaCode(osiCategories.getOsiCoa().getCoaCode());
			osiCategoriesDTO.setOsiCoa(coaDto);
		}
		return osiCategoriesDTO;
	}

	@Override
	public List<OsiCategoriesDTO> osiCategoriesListToOsiCategoriesDTOList(List<OsiCategories> osiCategories) {
		if ( osiCategories == null ) {
            return null;
        }

        List<OsiCategoriesDTO> list = new ArrayList<OsiCategoriesDTO>();
        for ( OsiCategories osiCategories1 : osiCategories ) {
            list.add( osiCategoriesToOsiCategoriesDTO( osiCategories1 ) );
        }

        return list;
	}

	@Override
	public OsiCategories osiCategoriesDTOToOsiCategories(OsiCategoriesDTO osiCategoriesDTO) {
		//OsiCategories osiCategories = modelMapper.map(osiCategoriesDTO, OsiCategories.class);
		OsiCategories osiCategories = new OsiCategories();
		osiCategories.setBusinessGroupId(osiCategoriesDTO.getBusinessGroupId());
		osiCategories.setActive(osiCategoriesDTO.getActive());
		osiCategories.setCreatedBy(osiCategoriesDTO.getCreatedBy());
		osiCategories.setUpdatedBy(osiCategoriesDTO.getUpdatedBy());
		osiCategories.setCreatedDate(osiCategoriesDTO.getCreatedDate());
		osiCategories.setUpdatedDate(osiCategoriesDTO.getUpdatedDate());
		osiCategories.setCategoryId(osiCategoriesDTO.getCategoryId());
		osiCategories.setCategoryCode(osiCategoriesDTO.getCategoryCode());
		osiCategories.setCategoryDesc(osiCategoriesDTO.getCategoryDesc());
		osiCategories.setCategoryName(osiCategoriesDTO.getCategoryName());
		osiCategories.setOsiSegmentStructureHdrId(osiCategoriesDTO.getOsiSegmentStructureHdrId());
		return osiCategories;
	}

	@Override
	public List<OsiCategories> osiCategoriesDTOListToOsiCategoriesList(List<OsiCategoriesDTO> osiCategoriesDTO) {
		if ( osiCategoriesDTO == null ) {
            return null;
        }

        List<OsiCategories> list = new ArrayList<OsiCategories>();
        for ( OsiCategoriesDTO osiCategoriesDTO1 : osiCategoriesDTO ) {
            list.add( osiCategoriesDTOToOsiCategories( osiCategoriesDTO1 ) );
        }
        return list;
	}
   
}
