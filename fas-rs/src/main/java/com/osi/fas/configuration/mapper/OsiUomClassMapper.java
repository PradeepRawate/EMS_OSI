package com.osi.fas.configuration.mapper;

/**
 * Mapper for the entity OsiUomClass and its DTO OsiUomClassDTO.
 */
public interface OsiUomClassMapper {

	/*@Mapping(source = "osiUomConversion.id", target = "osiUomConversionId")
    OsiUomClassDTO osiUomClassToOsiUomClassDTO(OsiUomClass osiUomClass);

    List<OsiUomClassDTO> osiUomClassesToOsiUomClassDTOs(List<OsiUomClass> osiUomClasses);

    @Mapping(target = "osiBusinessGroups", ignore = true)
    @Mapping(source = "osiUomConversionId", target = "osiUomConversion")
    OsiUomClass osiUomClassDTOToOsiUomClass(OsiUomClassDTO osiUomClassDTO);

    List<OsiUomClass> osiUomClassDTOsToOsiUomClasses(List<OsiUomClassDTO> osiUomClassDTOs);

    default OsiUomConversion osiUomConversionFromId(Long id) {
        if (id == null) {
            return null;
        }
        OsiUomConversion osiUomConversion = new OsiUomConversion();
        osiUomConversion.setId(id);
        return osiUomConversion;
    }*/
}
