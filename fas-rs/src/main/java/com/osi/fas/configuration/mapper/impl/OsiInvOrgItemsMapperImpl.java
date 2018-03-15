package com.osi.fas.configuration.mapper.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.osi.fas.configuration.mapper.OsiInvOrgItemsMapper;
import com.osi.fas.domain.OsiInvOrgItems;
import com.osi.fas.service.dto.OsiInvOrgItemsDTO;
@Component
public class OsiInvOrgItemsMapperImpl implements OsiInvOrgItemsMapper {
	
	@Override
	public OsiInvOrgItemsDTO osiInvOrgItemsToOsiInvOrgItemsDTO(OsiInvOrgItems osiInvOrgItems) {
		//OsiInvOrgItemsDTO osiInvOrgItemsDTO = modelMapper.map(osiInvOrgItems, OsiInvOrgItemsDTO.class);
		OsiInvOrgItemsDTO osiInvOrgItemsDTO = new OsiInvOrgItemsDTO();
		osiInvOrgItemsDTO.setBusinessGroupId(osiInvOrgItems.getBusinessGroupId());
		osiInvOrgItemsDTO.setInvOrgId(osiInvOrgItems.getInvOrgId());
		osiInvOrgItemsDTO.setInvOrgItemId(osiInvOrgItems.getInvOrgItemId());
		return osiInvOrgItemsDTO;
	}

	@Override
	public Set<OsiInvOrgItemsDTO> osiInvOrgItemsListToOsiInvOrgItemsDTOList(Set<OsiInvOrgItems> osiInvOrgItemss) {
		if ( osiInvOrgItemss == null ) {
            return null;
        }

        Set<OsiInvOrgItemsDTO> list = new HashSet<OsiInvOrgItemsDTO>();
        for ( OsiInvOrgItems osiInvOrgItemss1 : osiInvOrgItemss ) {
            list.add( osiInvOrgItemsToOsiInvOrgItemsDTO( osiInvOrgItemss1 ) );
        }

        return list;
	}

	@Override
	public OsiInvOrgItems osiInvOrgItemsDTOToOsiInvOrgItems(OsiInvOrgItemsDTO osiInvOrgItemsDTO) {
		//OsiInvOrgItems osiInvOrgItems = modelMapper.map(osiInvOrgItemsDTO, OsiInvOrgItems.class);
		OsiInvOrgItems osiInvOrgItems =  new OsiInvOrgItems();
		osiInvOrgItems.setBusinessGroupId(osiInvOrgItemsDTO.getBusinessGroupId());
		osiInvOrgItems.setInvOrgId(osiInvOrgItemsDTO.getInvOrgId());
		osiInvOrgItems.setInvOrgItemId(osiInvOrgItemsDTO.getInvOrgItemId());
		return osiInvOrgItems;
	}

	@Override
	public Set<OsiInvOrgItems> osiInvOrgItemsDTOListToOsiInvOrgItemsList(Set<OsiInvOrgItemsDTO> osiInvOrgItemsDTO) {
		if ( osiInvOrgItemsDTO == null ) {
            return null;
        }

		Set<OsiInvOrgItems> list = new HashSet<OsiInvOrgItems>();
        for ( OsiInvOrgItemsDTO osiInvOrgItemsDTO1 : osiInvOrgItemsDTO ) {
            list.add( osiInvOrgItemsDTOToOsiInvOrgItems( osiInvOrgItemsDTO1 ) );
        }
        return list;
	}
}
