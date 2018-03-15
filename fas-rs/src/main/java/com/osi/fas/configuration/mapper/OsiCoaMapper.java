package com.osi.fas.configuration.mapper;

import java.util.List;

import com.osi.fas.domain.OsiCoa;
import com.osi.fas.service.dto.OsiCoaDTO;

public interface OsiCoaMapper {

	OsiCoaDTO osiCoaToOsiCoaDTO(OsiCoa osiCoa);

	List<OsiCoaDTO> osiCoaListToOsiCoaDTOList(List<OsiCoa> osiCoa);

	OsiCoa osiCoaDTOToOsiCoa(OsiCoaDTO osiCoaDTO);

	List<OsiCoa> osiCoaDTOListToOsiCoaList(List<OsiCoaDTO> osiCoaDTO);

}
