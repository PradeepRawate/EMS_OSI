package com.osi.fas.configuration.mapper;

/**
 * Mapper for the entity OsiUom and its DTO OsiUomDTO.
 */
public interface OsiUomMapper {

   /* @Mapping(source = "osiItem.id", target = "osiItemId")
    @Mapping(source = "osiUomConversion.id", target = "osiUomConversionId")
    OsiUomDTO osiUomToOsiUomDTO(OsiUom osiUom);

    List<OsiUomDTO> osiUomsToOsiUomDTOs(List<OsiUom> osiUoms);

    @Mapping(source = "osiItemId", target = "osiItem")
    @Mapping(target = "osiBusinessGroups", ignore = true)
    @Mapping(source = "osiUomConversionId", target = "osiUomConversion")
    OsiUom osiUomDTOToOsiUom(OsiUomDTO osiUomDTO);

    List<OsiUom> osiUomDTOsToOsiUoms(List<OsiUomDTO> osiUomDTOs);

    default OsiItem osiItemFromId(Long id) {
        if (id == null) {
            return null;
        }
        OsiItem osiItem = new OsiItem();
        osiItem.setId(id);
        return osiItem;
    }

    default OsiUomConversion osiUomConversionFromId(Long id) {
        if (id == null) {
            return null;
        }
        OsiUomConversion osiUomConversion = new OsiUomConversion();
        osiUomConversion.setId(id);
        return osiUomConversion;
    }*/
}
