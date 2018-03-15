package com.osi.fas.configuration.mapper.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.fas.configuration.mapper.OsiInvOrgItemPriceMapper;
import com.osi.fas.configuration.mapper.OsiInventoryOrgMapper;
import com.osi.fas.domain.OsiInvOrgItemPrice;
import com.osi.fas.domain.OsiInventoryOrg;
import com.osi.fas.service.dto.OsiInvOrgItemPriceDTO;
@Component
public class OsiInvOrgItemPriceMapperImpl implements OsiInvOrgItemPriceMapper {
	
	@Autowired
	private OsiInventoryOrgMapper osiInventoryOrgMapper;
	@Override
	public OsiInvOrgItemPriceDTO osiInvOrgItemPriceToOsiInvOrgItemPriceDTO(OsiInvOrgItemPrice osiInvOrgItemPrice) {
		//OsiInvOrgItemPriceDTO osiInvOrgItemPriceDTO = modelMapper.map(osiInvOrgItemPrice, OsiInvOrgItemPriceDTO.class);
		OsiInvOrgItemPriceDTO osiInvOrgItemPriceDTO = new OsiInvOrgItemPriceDTO();
		osiInvOrgItemPriceDTO.setBusinessGroupId(osiInvOrgItemPrice.getBusinessGroupId());
		osiInvOrgItemPriceDTO.setIsTaxAmtIncluded(osiInvOrgItemPrice.getIsTaxAmtIncluded());
		osiInvOrgItemPriceDTO.setItemPriceId(osiInvOrgItemPrice.getItemPriceId());
		osiInvOrgItemPriceDTO.setListPrice(osiInvOrgItemPrice.getListPrice());
		osiInvOrgItemPriceDTO.setMarketPrice(osiInvOrgItemPrice.getMarketPrice());
		osiInvOrgItemPriceDTO.setSellingPrice(osiInvOrgItemPrice.getSellingPrice());
		OsiInventoryOrg osiInventoryOrg = new OsiInventoryOrg();
		osiInventoryOrg.setInvOrgId(osiInvOrgItemPrice.getOsiInventoryOrg().getInvOrgId());
		osiInvOrgItemPriceDTO.setOsiInventoryOrg(osiInventoryOrgMapper.osiInventoryOrgToOsiInventoryOrgDTO(osiInvOrgItemPrice.getOsiInventoryOrg()));
		return osiInvOrgItemPriceDTO;
	}

	@Override
	public Set<OsiInvOrgItemPriceDTO> osiInvOrgItemPriceListToOsiInvOrgItemPriceDTOList(Set<OsiInvOrgItemPrice> osiInvOrgItemPrices) {
		if ( osiInvOrgItemPrices == null ) {
            return null;
        }

		Set<OsiInvOrgItemPriceDTO> list = new HashSet<OsiInvOrgItemPriceDTO>();
        for ( OsiInvOrgItemPrice osiInvOrgItemPrices1 : osiInvOrgItemPrices ) {
            list.add( osiInvOrgItemPriceToOsiInvOrgItemPriceDTO( osiInvOrgItemPrices1 ) );
        }

        return list;
	}

	@Override
	public OsiInvOrgItemPrice osiInvOrgItemPriceDTOToOsiInvOrgItemPrice(OsiInvOrgItemPriceDTO osiInvOrgItemPriceDTO) {
		//OsiInvOrgItemPrice osiInvOrgItemPrice = modelMapper.map(osiInvOrgItemPriceDTO, OsiInvOrgItemPrice.class);
		OsiInvOrgItemPrice osiInvOrgItemPrice = new OsiInvOrgItemPrice();
		osiInvOrgItemPrice.setBusinessGroupId(osiInvOrgItemPriceDTO.getBusinessGroupId());
		osiInvOrgItemPrice.setIsTaxAmtIncluded(osiInvOrgItemPriceDTO.getIsTaxAmtIncluded());
		osiInvOrgItemPrice.setItemPriceId(osiInvOrgItemPriceDTO.getItemPriceId());
		osiInvOrgItemPrice.setListPrice(osiInvOrgItemPriceDTO.getListPrice());
		osiInvOrgItemPrice.setMarketPrice(osiInvOrgItemPriceDTO.getMarketPrice());
		osiInvOrgItemPrice.setSellingPrice(osiInvOrgItemPriceDTO.getSellingPrice());
		OsiInventoryOrg osiInventoryOrgDTO = new OsiInventoryOrg();
		osiInventoryOrgDTO.setInvOrgId(osiInvOrgItemPriceDTO.getOsiInventoryOrg().getInvOrgId());
		osiInvOrgItemPrice.setOsiInventoryOrg(osiInventoryOrgMapper.osiInventoryOrgDTOToOsiInventoryOrg(osiInvOrgItemPriceDTO.getOsiInventoryOrg()));
		return osiInvOrgItemPrice;
	}

	@Override
	public Set<OsiInvOrgItemPrice> osiInvOrgItemPriceDTOListToOsiInvOrgItemPriceList(Set<OsiInvOrgItemPriceDTO> osiInvOrgItemPriceDTO) {
		if ( osiInvOrgItemPriceDTO == null ) {
            return null;
        }

		Set<OsiInvOrgItemPrice> list = new HashSet<OsiInvOrgItemPrice>();
        for ( OsiInvOrgItemPriceDTO osiInvOrgItemPriceDTO1 : osiInvOrgItemPriceDTO ) {
            list.add( osiInvOrgItemPriceDTOToOsiInvOrgItemPrice( osiInvOrgItemPriceDTO1 ) );
        }
        return list;
	}
}
