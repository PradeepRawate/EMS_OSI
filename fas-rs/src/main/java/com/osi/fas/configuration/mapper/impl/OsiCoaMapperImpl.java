package com.osi.fas.configuration.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.fas.configuration.mapper.OsiCoaMapper;
import com.osi.fas.domain.OsiCoa;
import com.osi.fas.service.dto.OsiCoaDTO;
@Component
public class OsiCoaMapperImpl implements OsiCoaMapper {


	@Override
	public OsiCoaDTO osiCoaToOsiCoaDTO(OsiCoa osiCoa) {
		OsiCoaDTO osiCoaDTO = new OsiCoaDTO();
		osiCoaDTO.setCreatedBy(osiCoa.getCreatedBy());
		osiCoaDTO.setUpdatedBy(osiCoa.getUpdatedBy());
		osiCoaDTO.setCreatedDate(osiCoa.getCreatedDate());
		osiCoaDTO.setUpdatedDate(osiCoa.getUpdatedDate());
		osiCoaDTO.setBusinessGroupId(osiCoa.getBusinessGroupId());
		osiCoaDTO.setCoaId(osiCoa.getCoaId());
		osiCoaDTO.setCoaCode(osiCoa.getCoaCode());
		osiCoaDTO.setCoaDesc(osiCoa.getCoaDesc());
		osiCoaDTO.setCoaName(osiCoa.getCoaName());
		osiCoaDTO.setOsiSegmentStructureHdrId(osiCoa.getOsiSegmentStructureHdrId());
		osiCoaDTO.setInvOrgId(osiCoa.getInvOrgId());
		return osiCoaDTO;
	}

	@Override
	public List<OsiCoaDTO> osiCoaListToOsiCoaDTOList(List<OsiCoa> osiCoa) {
		if (osiCoa == null) {
			return null;
		}

		List<OsiCoaDTO> list = new ArrayList<OsiCoaDTO>();
		for (OsiCoa osiCoa1 : osiCoa) {
			list.add(osiCoaToOsiCoaDTO(osiCoa1));
		}

		return list;
	}

	@Override
	public OsiCoa osiCoaDTOToOsiCoa(OsiCoaDTO osiCoaDTO) {
		OsiCoa osiCoa = new OsiCoa();
		osiCoa.setBusinessGroupId(osiCoaDTO.getBusinessGroupId());
		osiCoa.setCreatedBy(osiCoaDTO.getCreatedBy());
		osiCoa.setUpdatedBy(osiCoaDTO.getUpdatedBy());
		osiCoa.setCreatedDate(osiCoaDTO.getCreatedDate());
		osiCoa.setUpdatedDate(osiCoaDTO.getUpdatedDate());
		osiCoa.setCoaId(osiCoaDTO.getCoaId());
		osiCoa.setCoaCode(osiCoaDTO.getCoaCode());
		osiCoa.setCoaDesc(osiCoaDTO.getCoaDesc());
		osiCoa.setCoaName(osiCoaDTO.getCoaName());
		osiCoa.setOsiSegmentStructureHdrId(osiCoaDTO.getOsiSegmentStructureHdrId());
		osiCoa.setInvOrgId(osiCoaDTO.getInvOrgId());
		return osiCoa;
	}

	@Override
	public List<OsiCoa> osiCoaDTOListToOsiCoaList(List<OsiCoaDTO> osiCoaDTO) {
		if (osiCoaDTO == null) {
			return null;
		}

		List<OsiCoa> list = new ArrayList<OsiCoa>();
		for (OsiCoaDTO osiCoaDTO1 : osiCoaDTO) {
			list.add(osiCoaDTOToOsiCoa(osiCoaDTO1));
		}
		return list;
	}

}
