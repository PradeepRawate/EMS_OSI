package com.osi.fas.configuration.mapper;


import java.util.Set;

import com.osi.fas.domain.OsiInvOrgItemPrice;
import com.osi.fas.service.dto.OsiInvOrgItemPriceDTO;

/**
 * Mapper for the entity OsiInvOrgItemPrice and its DTO OsiInvOrgItemPriceDTO.
 */
public interface OsiInvOrgItemPriceMapper {

	OsiInvOrgItemPriceDTO osiInvOrgItemPriceToOsiInvOrgItemPriceDTO(OsiInvOrgItemPrice osiInvOrgItemPrice);
	Set<OsiInvOrgItemPriceDTO> osiInvOrgItemPriceListToOsiInvOrgItemPriceDTOList(Set<OsiInvOrgItemPrice> osiInvOrgItemPrices);
	OsiInvOrgItemPrice osiInvOrgItemPriceDTOToOsiInvOrgItemPrice(OsiInvOrgItemPriceDTO osiInvOrgItemPriceDTO);
	Set<OsiInvOrgItemPrice> osiInvOrgItemPriceDTOListToOsiInvOrgItemPriceList(Set<OsiInvOrgItemPriceDTO> osiInvOrgItemPriceDTO);
}
