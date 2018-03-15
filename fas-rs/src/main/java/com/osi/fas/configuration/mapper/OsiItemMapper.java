package com.osi.fas.configuration.mapper;


import java.util.List;

import com.osi.fas.domain.OsiItem;
import com.osi.fas.service.dto.OsiItemDTO;
import com.osi.urm.exception.BusinessException;

/**
 * Mapper for the entity OsiItem and its DTO OsiItemDTO.
 */
public interface OsiItemMapper {

	OsiItemDTO osiItemToOsiItemDTO(OsiItem osiItem);
	List<OsiItemDTO> osiItemListToOsiItemDTOList(List<OsiItem> osiItems);
	OsiItem osiItemDTOToOsiItem(OsiItemDTO osiItemDTO) throws BusinessException;
	List<OsiItem> osiItemDTOListToOsiItemList(List<OsiItemDTO> osiItemDTO) throws BusinessException;
}
