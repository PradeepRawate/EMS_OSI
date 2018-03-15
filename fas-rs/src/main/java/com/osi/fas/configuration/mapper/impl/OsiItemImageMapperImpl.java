package com.osi.fas.configuration.mapper.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.fas.configuration.mapper.OsiItemImageMapper;
import com.osi.fas.configuration.mapper.OsiItemMapper;
import com.osi.fas.domain.OsiItemImage;
import com.osi.fas.service.dto.OsiItemImageDTO;
@Component
public class OsiItemImageMapperImpl implements OsiItemImageMapper {
	@Autowired
	private OsiItemMapper osiItemMapper;
	
	@Override
	public OsiItemImageDTO osiItemImageToOsiItemImageDTO(OsiItemImage osiItemImage) {
		//OsiItemImageDTO osiItemImageDTO = modelMapper.map(osiItemImage, OsiItemImageDTO.class);
		OsiItemImageDTO osiItemImageDTO = new OsiItemImageDTO();
		osiItemImageDTO.setBusinessGroupId(osiItemImage.getBusinessGroupId());
		osiItemImageDTO.setItemImage(osiItemImage.getItemImage());
		osiItemImageDTO.setItemImageId(osiItemImage.getItemImageId());
		//osiItemImageDTO.setOsiItem(osiItemMapper.osiItemToOsiItemDTO(osiItemImage.getOsiItem()));
		return osiItemImageDTO;
	}

	@Override
	public Set<OsiItemImageDTO> osiItemImageListToOsiItemImageDTOList(Set<OsiItemImage> osiItemImages) {
		if ( osiItemImages == null ) {
            return null;
        }

		Set<OsiItemImageDTO> list = new HashSet<OsiItemImageDTO>();
        for ( OsiItemImage osiItemImages1 : osiItemImages ) {
            list.add( osiItemImageToOsiItemImageDTO( osiItemImages1 ) );
        }

        return list;
	}

	@Override
	public OsiItemImage osiItemImageDTOToOsiItemImage(OsiItemImageDTO osiItemImageDTO) {
		//OsiItemImage osiItemImage = modelMapper.map(osiItemImageDTO, OsiItemImage.class);
		OsiItemImage osiItemImage  = new OsiItemImage();
		osiItemImage.setBusinessGroupId(osiItemImageDTO.getBusinessGroupId());
		osiItemImage.setItemImage(osiItemImageDTO.getItemImage());
		osiItemImage.setItemImageId(osiItemImageDTO.getItemImageId());
		//osiItemImage.setOsiItem(osiItemMapper.osiItemDTOToOsiItem(osiItemImageDTO.getOsiItem()));
		return osiItemImage;
	}

	@Override
	public Set<OsiItemImage> osiItemImageDTOListToOsiItemImageList(Set<OsiItemImageDTO> osiItemImageDTO) {
		if ( osiItemImageDTO == null ) {
            return null;
        }

		Set<OsiItemImage> list = new HashSet<OsiItemImage>();
        for ( OsiItemImageDTO osiItemImageDTO1 : osiItemImageDTO ) {
            list.add( osiItemImageDTOToOsiItemImage( osiItemImageDTO1 ) );
        }
        return list;
	}
}
