package com.osi.fas.configuration.mapper.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.osi.fas.configuration.mapper.OsiOperatingUnitMapper;
import com.osi.fas.domain.OsiOperatingUnit;
import com.osi.fas.service.dto.OsiBusinessGroupDTO;
import com.osi.fas.service.dto.OsiLegalEntitiesDTO;
import com.osi.fas.service.dto.OsiOperatingUnitDTO;
@Component
public class OsiOperatingUnitMapperImpl implements OsiOperatingUnitMapper {

	@Override
	public OsiOperatingUnitDTO osiOperatingUnitToOsiOperatingUnitDTO(OsiOperatingUnit osiOperatingUnit) {
		OsiOperatingUnitDTO osiOperatingUnitDTO=new OsiOperatingUnitDTO();
		osiOperatingUnitDTO.setOperatingUnitId(osiOperatingUnit.getOperatingUnitId());
		osiOperatingUnitDTO.setAddress1(osiOperatingUnit.getAddress1());
		osiOperatingUnitDTO.setAddress2(osiOperatingUnit.getAddress2());
		osiOperatingUnitDTO.setCity(osiOperatingUnit.getCity());
		osiOperatingUnitDTO.setCountry(osiOperatingUnit.getCountry());
		osiOperatingUnitDTO.setCreatedBy(osiOperatingUnit.getCreatedBy());
		osiOperatingUnitDTO.setCreatedDate(osiOperatingUnit.getCreatedDate());
		osiOperatingUnitDTO.setUpdatedBy(osiOperatingUnit.getUpdatedBy());
		osiOperatingUnitDTO.setUpdatedDate(osiOperatingUnit.getUpdatedDate());
		osiOperatingUnitDTO.setDateFrom(osiOperatingUnit.getDateFrom());
		osiOperatingUnitDTO.setDateTo(osiOperatingUnit.getDateTo());
		osiOperatingUnitDTO.setDefaultCurrencyCode(osiOperatingUnit.getDefaultCurrencyCode());
		osiOperatingUnitDTO.setOperatingUnitCode(osiOperatingUnit.getOperatingUnitCode());
		osiOperatingUnitDTO.setOperatingUnitName(osiOperatingUnit.getOperatingUnitName());
		osiOperatingUnitDTO.setOperatingUnitShortName(osiOperatingUnit.getOperatingUnitShortName());
		osiOperatingUnitDTO.setProvince(osiOperatingUnit.getProvince());
		osiOperatingUnitDTO.setState(osiOperatingUnit.getState());
		
		osiOperatingUnitDTO.setOsiBusinessGroup(new OsiBusinessGroupDTO());
		osiOperatingUnitDTO.getOsiBusinessGroup().setBusinessGroupId(osiOperatingUnit.getOsiBusinessGroup().getBusinessGroupId());
		
		osiOperatingUnitDTO.setOsiLegalEntities(new OsiLegalEntitiesDTO());
		osiOperatingUnitDTO.getOsiLegalEntities().setLegalEntityId(osiOperatingUnit.getOsiLegalEntities().getLegalEntityId());
		
		return osiOperatingUnitDTO;
	}

	@Override
	public List<OsiOperatingUnitDTO> osiOperatingUnitsToOsiOperatingUnitDTOs(List<OsiOperatingUnit> osiOperatingUnits) {
		if (osiOperatingUnits == null) {
			return null;
		}

		List<OsiOperatingUnitDTO> list = new ArrayList<OsiOperatingUnitDTO>();
		for (OsiOperatingUnit osiLocation : osiOperatingUnits) {
			list.add(osiOperatingUnitToOsiOperatingUnitDTO(osiLocation));
		}

		return list;
	}
	
	@Override
	public Set<OsiOperatingUnitDTO> osiOperatingUnitsSetToOsiOperatingUnitDTOSet(Set<OsiOperatingUnit> osiOperatingUnits) {
		if (osiOperatingUnits == null) {
			return null;
		}
		Set<OsiOperatingUnitDTO> operatingUnitDTOs = new HashSet<OsiOperatingUnitDTO>(0);
		for (OsiOperatingUnit osiOperatingUnit : osiOperatingUnits) {
			OsiOperatingUnitDTO osiOperatingUnitDTO = new OsiOperatingUnitDTO();
			osiOperatingUnitDTO.setOperatingUnitName(osiOperatingUnit.getOperatingUnitCode() +" "+osiOperatingUnit.getOperatingUnitName());
			operatingUnitDTOs.add(osiOperatingUnitDTO);
		}

		return operatingUnitDTOs;
	}

	@Override
	public OsiOperatingUnit osiOperatingUnitDTOToOsiOperatingUnit(OsiOperatingUnitDTO osiOperatingUnitDTO) {
		OsiOperatingUnit OsiOperatingUnit=new OsiOperatingUnit();
		
		
		OsiOperatingUnit.setAddress1(osiOperatingUnitDTO.getAddress1());
		OsiOperatingUnit.setAddress2(osiOperatingUnitDTO.getAddress2());
		OsiOperatingUnit.setCity(osiOperatingUnitDTO.getCity());
		OsiOperatingUnit.setCountry(osiOperatingUnitDTO.getCountry());
		OsiOperatingUnit.setCreatedBy(osiOperatingUnitDTO.getCreatedBy());
		OsiOperatingUnit.setCreatedDate(osiOperatingUnitDTO.getCreatedDate());
		OsiOperatingUnit.setUpdatedBy(osiOperatingUnitDTO.getUpdatedBy());
		OsiOperatingUnit.setUpdatedDate(osiOperatingUnitDTO.getUpdatedDate());
		OsiOperatingUnit.setDateFrom(osiOperatingUnitDTO.getDateFrom());
		OsiOperatingUnit.setDateTo(osiOperatingUnitDTO.getDateTo());
		OsiOperatingUnit.setDefaultCurrencyCode(osiOperatingUnitDTO.getDefaultCurrencyCode());
		OsiOperatingUnit.setOperatingUnitCode(osiOperatingUnitDTO.getOperatingUnitCode());
		OsiOperatingUnit.setOperatingUnitName(osiOperatingUnitDTO.getOperatingUnitName());
		OsiOperatingUnit.setOperatingUnitShortName(osiOperatingUnitDTO.getOperatingUnitShortName());
		OsiOperatingUnit.setProvince(osiOperatingUnitDTO.getProvince());
		OsiOperatingUnit.setState(osiOperatingUnitDTO.getState());
		return OsiOperatingUnit;
	}

	@Override
	public List<OsiOperatingUnit> osiOperatingUnitDTOsToOsiOperatingUnits(
			List<OsiOperatingUnitDTO> osiOperatingUnitDTOs) {
		if (osiOperatingUnitDTOs == null) {
			return null;
		}

		List<OsiOperatingUnit> list = new ArrayList<OsiOperatingUnit>();
		for (OsiOperatingUnitDTO osiLocation : osiOperatingUnitDTOs) {
			list.add(osiOperatingUnitDTOToOsiOperatingUnit(osiLocation));
		}

		return list;
	}

}
