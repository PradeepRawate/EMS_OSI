package com.osi.fas.configuration.mapper;


import java.util.Set;

import com.osi.fas.domain.OsiInvOrgItems;
import com.osi.fas.service.dto.OsiInvOrgItemsDTO;

/**
 * Mapper for the entity OsiInvOrgItems and its DTO OsiInvOrgItemsDTO.
 */
public interface OsiInvOrgItemsMapper {

	OsiInvOrgItemsDTO osiInvOrgItemsToOsiInvOrgItemsDTO(OsiInvOrgItems osiInvOrgItems);
	Set<OsiInvOrgItemsDTO> osiInvOrgItemsListToOsiInvOrgItemsDTOList(Set<OsiInvOrgItems> osiInvOrgItemss);
	OsiInvOrgItems osiInvOrgItemsDTOToOsiInvOrgItems(OsiInvOrgItemsDTO osiInvOrgItemsDTO);
	Set<OsiInvOrgItems> osiInvOrgItemsDTOListToOsiInvOrgItemsList(Set<OsiInvOrgItemsDTO> osiInvOrgItemsDTO);
}
