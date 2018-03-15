package com.osi.fas.configuration.mapper;

/**
 * Mapper for the entity OsiSegmentStructureHdr and its DTO OsiSegmentStructureHdrDTO.
 */
public interface OsiSegmentStructureMapper {

   // @Mapping(source = "osiSegmentStructureDetails.id", target = "osiSegmentStructureDetailsId")
    //OsiSegmentStructureHdrDTO osiSegmentStructureHdrToOsiSegmentStructureHdrDTO(OsiSegmentStructureHdr osiSegmentStructureHdr);

   // List<OsiSegmentStructureHdrDTO> osiSegmentStructureHdrsToOsiSegmentStructureHdrDTOs(List<OsiSegmentStructureHdr> osiSegmentStructureHdrs);

    /*@Mapping(target = "osiBusinessGroups", ignore = true)
    @Mapping(source = "osiSegmentStructureDetailsId", target = "osiSegmentStructureDetails")
    OsiSegmentStructureHdr osiSegmentStructureHdrDTOToOsiSegmentStructureHdr(OsiSegmentStructureHdrDTO osiSegmentStructureHdrDTO);

    List<OsiSegmentStructureHdr> osiSegmentStructureHdrDTOsToOsiSegmentStructureHdrs(List<OsiSegmentStructureHdrDTO> osiSegmentStructureHdrDTOs);

    default OsiSegmentStructureDetails osiSegmentStructureDetailsFromId(Long id) {
        if (id == null) {
            return null;
        }
        OsiSegmentStructureDetails osiSegmentStructureDetails = new OsiSegmentStructureDetails();
        osiSegmentStructureDetails.setId(id);
        return osiSegmentStructureDetails;
    }*/
}
