package com.osi.fas.configuration.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.fas.configuration.mapper.OsiConfigParametersMapper;
import com.osi.fas.domain.OsiConfigParameters;
import com.osi.fas.service.dto.OsiConfigParametersDTO;

@Component
public class OsiConfigParametersMapperImpl implements OsiConfigParametersMapper {

	@Override
	public OsiConfigParametersDTO osiConfigParametersToOsiConfigParametersDTO(OsiConfigParameters osiConfigParameters) {
		OsiConfigParametersDTO osiConfigParametersDTO = new OsiConfigParametersDTO();
		osiConfigParametersDTO.setConfigId(osiConfigParameters.getConfigId());
		osiConfigParametersDTO.setConfigName(osiConfigParameters.getConfigName());
		osiConfigParametersDTO.setConfigValue(osiConfigParameters.getConfigValue());
		osiConfigParametersDTO.setBusinessGroupId(osiConfigParameters.getBusinessGroupId());
		return osiConfigParametersDTO;
	}

	@Override
	public List<OsiConfigParametersDTO> osiOsiConfigParametersListToOsiConfigParametersDTOList(
			List<OsiConfigParameters> osiConfigParameters) {
		List<OsiConfigParametersDTO> list = new ArrayList<>();
		if (osiConfigParameters == null)
			return null;
		for (OsiConfigParameters parameters : osiConfigParameters) {
			list.add(osiConfigParametersToOsiConfigParametersDTO(parameters));
		}
		return list;
	}

	@Override
	public List<OsiConfigParameters> osiConfigParametersDTOListToOsiConfigParametersList(
			List<OsiConfigParametersDTO> osiConfigParametersDTO) {
		List<OsiConfigParameters> list = new ArrayList<>();
		if (osiConfigParametersDTO == null)
			return null;
		for (OsiConfigParametersDTO configParametersDTO : osiConfigParametersDTO) {
			list.add(OsiConfigParametersDTOToOsiConfigParameters(configParametersDTO));
		}
		return list;
	}

	@Override
	public OsiConfigParameters OsiConfigParametersDTOToOsiConfigParameters(
			OsiConfigParametersDTO osiConfigParametersDTO) {
		OsiConfigParameters osiConfigParameters = new OsiConfigParameters();
		osiConfigParameters.setConfigId(osiConfigParametersDTO.getConfigId());
		osiConfigParameters.setConfigName(osiConfigParametersDTO.getConfigName());
		osiConfigParameters.setConfigValue(osiConfigParametersDTO.getConfigValue());
		osiConfigParameters.setBusinessGroupId(osiConfigParametersDTO.getBusinessGroupId());
		return osiConfigParameters;
	}

}
