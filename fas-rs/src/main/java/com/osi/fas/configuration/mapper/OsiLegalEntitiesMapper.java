package com.osi.fas.configuration.mapper;

/**
 * Mapper for the entity OsiLegalEntities and its DTO OsiLegalEntitiesDTO.
 */
public interface OsiLegalEntitiesMapper {

    /*@Mapping(source = "osiOperatingUnit.id", target = "osiOperatingUnitId")
    OsiLegalEntitiesDTO osiLegalEntitiesToOsiLegalEntitiesDTO(OsiLegalEntities osiLegalEntities);

    List<OsiLegalEntitiesDTO> osiLegalEntitiesToOsiLegalEntitiesDTOs(List<OsiLegalEntities> osiLegalEntities);

    @Mapping(target = "osiBusinessGroups", ignore = true)
    @Mapping(source = "osiOperatingUnitId", target = "osiOperatingUnit")
    OsiLegalEntities osiLegalEntitiesDTOToOsiLegalEntities(OsiLegalEntitiesDTO osiLegalEntitiesDTO);

    List<OsiLegalEntities> osiLegalEntitiesDTOsToOsiLegalEntities(List<OsiLegalEntitiesDTO> osiLegalEntitiesDTOs);

    default OsiOperatingUnit osiOperatingUnitFromId(Long id) {
        if (id == null) {
            return null;
        }
        OsiOperatingUnit osiOperatingUnit = new OsiOperatingUnit();
        osiOperatingUnit.setId(id);
        return osiOperatingUnit;
    }*/
}
