package com.osi.fas.configuration.mapper.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.osi.fas.configuration.mapper.OsiMinMaxPlanningMapper;
import com.osi.fas.domain.OsiMinMaxPlanning;
import com.osi.fas.service.dto.OsiMinMaxPlanningDTO;
@Component
public class OsiMinMaxPlanningMapperImpl implements OsiMinMaxPlanningMapper {
	
	@Override
	public OsiMinMaxPlanningDTO osiMinMaxPlanningToOsiMinMaxPlanningDTO(OsiMinMaxPlanning osiMinMaxPlanning) {
		//OsiMinMaxPlanningDTO osiMinMaxPlanningDTO = modelMapper.map(osiMinMaxPlanning, OsiMinMaxPlanningDTO.class);
		OsiMinMaxPlanningDTO osiMinMaxPlanningDTO = new OsiMinMaxPlanningDTO();
		osiMinMaxPlanningDTO.setPlanningId(osiMinMaxPlanning.getPlanningId());
		//osiMinMaxPlanningDTO.setInvOrgType(osiMinMaxPlanning.getInvOrgType());
		osiMinMaxPlanningDTO.setBusinessGroupId(osiMinMaxPlanning.getBusinessGroupId());
		osiMinMaxPlanningDTO.setMaxThreshold(osiMinMaxPlanning.getMaxThreshold());
		osiMinMaxPlanningDTO.setMinThreshold(osiMinMaxPlanning.getMinThreshold());
		osiMinMaxPlanningDTO.setOrderQtyMultiples(osiMinMaxPlanning.getOrderQtyMultiples());
		return osiMinMaxPlanningDTO;
	}

	@Override
	public Set<OsiMinMaxPlanningDTO> osiMinMaxPlanningListToOsiMinMaxPlanningDTOList(Set<OsiMinMaxPlanning> osiMinMaxPlannings) {
		if ( osiMinMaxPlannings == null ) {
            return null;
        }

		Set<OsiMinMaxPlanningDTO> list = new HashSet<OsiMinMaxPlanningDTO>();
        for ( OsiMinMaxPlanning osiMinMaxPlannings1 : osiMinMaxPlannings ) {
            list.add( osiMinMaxPlanningToOsiMinMaxPlanningDTO( osiMinMaxPlannings1 ) );
        }

        return list;
	}

	@Override
	public OsiMinMaxPlanning osiMinMaxPlanningDTOToOsiMinMaxPlanning(OsiMinMaxPlanningDTO osiMinMaxPlanningDTO) {
		//OsiMinMaxPlanning osiMinMaxPlanning = modelMapper.map(osiMinMaxPlanningDTO, OsiMinMaxPlanning.class);
		OsiMinMaxPlanning osiMinMaxPlanning = new OsiMinMaxPlanning();
		osiMinMaxPlanning.setPlanningId(osiMinMaxPlanningDTO.getPlanningId());
		//osiMinMaxPlanning.setInvOrgType(osiMinMaxPlanningDTO.getInvOrgType());
		osiMinMaxPlanning.setBusinessGroupId(osiMinMaxPlanningDTO.getBusinessGroupId());
		osiMinMaxPlanning.setMaxThreshold(osiMinMaxPlanningDTO.getMaxThreshold());
		osiMinMaxPlanning.setMinThreshold(osiMinMaxPlanningDTO.getMinThreshold());
		osiMinMaxPlanning.setOrderQtyMultiples(osiMinMaxPlanningDTO.getOrderQtyMultiples());
		return osiMinMaxPlanning;
	}

	@Override
	public Set<OsiMinMaxPlanning> osiMinMaxPlanningDTOListToOsiMinMaxPlanningList(Set<OsiMinMaxPlanningDTO> osiMinMaxPlanningDTO) {
		if ( osiMinMaxPlanningDTO == null ) {
            return null;
        }

		Set<OsiMinMaxPlanning> list = new HashSet<OsiMinMaxPlanning>();
        for ( OsiMinMaxPlanningDTO osiMinMaxPlanningDTO1 : osiMinMaxPlanningDTO ) {
            list.add( osiMinMaxPlanningDTOToOsiMinMaxPlanning( osiMinMaxPlanningDTO1 ) );
        }
        return list;
	}
}
