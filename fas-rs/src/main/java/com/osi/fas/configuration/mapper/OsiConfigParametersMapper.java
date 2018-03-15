package com.osi.fas.configuration.mapper;

import java.util.List;

import com.osi.fas.domain.OsiConfigParameters;
import com.osi.fas.service.dto.OsiConfigParametersDTO;


public interface OsiConfigParametersMapper {
	
	OsiConfigParametersDTO osiConfigParametersToOsiConfigParametersDTO(OsiConfigParameters osiConfigParameters);

	List<OsiConfigParametersDTO> osiOsiConfigParametersListToOsiConfigParametersDTOList(List<OsiConfigParameters> osiConfigParameters);
	
	OsiConfigParameters OsiConfigParametersDTOToOsiConfigParameters(OsiConfigParametersDTO osiConfigParametersDTO);

	List<OsiConfigParameters> osiConfigParametersDTOListToOsiConfigParametersList(List<OsiConfigParametersDTO> osiConfigParametersDTO);

}
