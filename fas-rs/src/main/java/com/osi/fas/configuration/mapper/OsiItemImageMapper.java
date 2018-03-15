package com.osi.fas.configuration.mapper;


import java.util.Set;

import com.osi.fas.domain.OsiItemImage;
import com.osi.fas.service.dto.OsiItemImageDTO;

/**
 * Mapper for the entity OsiItemImage and its DTO OsiItemImageDTO.
 */
public interface OsiItemImageMapper {

	OsiItemImageDTO osiItemImageToOsiItemImageDTO(OsiItemImage osiItemImage);
	Set<OsiItemImageDTO> osiItemImageListToOsiItemImageDTOList(Set<OsiItemImage> osiItemImage);
	OsiItemImage osiItemImageDTOToOsiItemImage(OsiItemImageDTO osiItemImageDTO);
	Set<OsiItemImage> osiItemImageDTOListToOsiItemImageList(Set<OsiItemImageDTO> osiItemImageDTO);
}
