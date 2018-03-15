package com.osi.fas.configuration.mapper;


import java.util.List;

import com.osi.fas.domain.OsiLocation;
import com.osi.fas.service.dto.OsiLocationDTO;

/**
 * Mapper for the entity OsiLocation and its DTO OsiLocationDTO.
 */
public interface OsiLocationMapper {

    OsiLocationDTO osiLocationToOsiLocationDTO(OsiLocation osiLocation);

    List<OsiLocationDTO> osiLocationsToOsiLocationDTOs(List<OsiLocation> osiLocations);

    OsiLocation osiLocationDTOToOsiLocation(OsiLocationDTO osiLocationDTO);

    List<OsiLocation> osiLocationDTOsToOsiLocations(List<OsiLocationDTO> osiLocationDTOs);
}
