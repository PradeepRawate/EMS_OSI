package com.osi.fas.configuration.mapper;


import java.util.Set;

import com.osi.fas.domain.OsiMinMaxPlanning;
import com.osi.fas.service.dto.OsiMinMaxPlanningDTO;

/**
 * Mapper for the entity OsiMinMaxPlanning and its DTO OsiMinMaxPlanningDTO.
 */
public interface OsiMinMaxPlanningMapper {

	OsiMinMaxPlanningDTO osiMinMaxPlanningToOsiMinMaxPlanningDTO(OsiMinMaxPlanning osiMinMaxPlanning);
	Set<OsiMinMaxPlanningDTO> osiMinMaxPlanningListToOsiMinMaxPlanningDTOList(Set<OsiMinMaxPlanning> osiMinMaxPlannings);
	OsiMinMaxPlanning osiMinMaxPlanningDTOToOsiMinMaxPlanning(OsiMinMaxPlanningDTO osiMinMaxPlanningDTO);
	Set<OsiMinMaxPlanning> osiMinMaxPlanningDTOListToOsiMinMaxPlanningList(Set<OsiMinMaxPlanningDTO> osiMinMaxPlanningDTO);
}
