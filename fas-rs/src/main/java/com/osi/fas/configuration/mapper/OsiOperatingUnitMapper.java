package com.osi.fas.configuration.mapper;


import java.util.List;
import java.util.Set;

import com.osi.fas.domain.OsiOperatingUnit;
import com.osi.fas.service.dto.OsiOperatingUnitDTO;

/**
 * Mapper for the entity OsiOperatingUnit and its DTO OsiOperatingUnitDTO.
 */
public interface OsiOperatingUnitMapper {

    OsiOperatingUnitDTO osiOperatingUnitToOsiOperatingUnitDTO(OsiOperatingUnit osiOperatingUnit);

    List<OsiOperatingUnitDTO> osiOperatingUnitsToOsiOperatingUnitDTOs(List<OsiOperatingUnit> osiOperatingUnits);
    
    Set<OsiOperatingUnitDTO> osiOperatingUnitsSetToOsiOperatingUnitDTOSet(Set<OsiOperatingUnit> osiOperatingUnits);

    OsiOperatingUnit osiOperatingUnitDTOToOsiOperatingUnit(OsiOperatingUnitDTO osiOperatingUnitDTO);

    List<OsiOperatingUnit> osiOperatingUnitDTOsToOsiOperatingUnits(List<OsiOperatingUnitDTO> osiOperatingUnitDTOs);
}
