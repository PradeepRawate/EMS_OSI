package com.osi.fas.configuration.mapper;

/**
 * Mapper for the entity OsiBusinessGroup and its DTO OsiBusinessGroupDTO.
 */
public interface OsiBusinessGroupMapper {

    /*@Mapping(source = "osiLegalEntities.id", target = "osiLegalEntitiesId")
    @Mapping(source = "osiOperatingUnit.id", target = "osiOperatingUnitId")
    @Mapping(source = "osiInventoryOrg.id", target = "osiInventoryOrgId")
    @Mapping(source = "osiSegmentStructureHdr.id", target = "osiSegmentStructureHdrId")
    @Mapping(source = "osiSegmentStructureDetails.id", target = "osiSegmentStructureDetailsId")
    @Mapping(source = "osiItem.id", target = "osiItemId")
    @Mapping(source = "osiItemImage.id", target = "osiItemImageId")
    @Mapping(source = "osiInvOrgItems.id", target = "osiInvOrgItemsId")
    @Mapping(source = "osiInvOrgItemPrice.id", target = "osiInvOrgItemPriceId")
    @Mapping(source = "osiUomClass.id", target = "osiUomClassId")
    @Mapping(source = "osiUom.id", target = "osiUomId")
    @Mapping(source = "osiUomConversion.id", target = "osiUomConversionId")
    OsiBusinessGroupDTO osiBusinessGroupToOsiBusinessGroupDTO(OsiBusinessGroup osiBusinessGroup);

    List<OsiBusinessGroupDTO> osiBusinessGroupsToOsiBusinessGroupDTOs(List<OsiBusinessGroup> osiBusinessGroups);

    @Mapping(source = "osiLegalEntitiesId", target = "osiLegalEntities")
    @Mapping(source = "osiOperatingUnitId", target = "osiOperatingUnit")
    @Mapping(source = "osiInventoryOrgId", target = "osiInventoryOrg")
    @Mapping(source = "osiSegmentStructureHdrId", target = "osiSegmentStructureHdr")
    @Mapping(source = "osiSegmentStructureDetailsId", target = "osiSegmentStructureDetails")
    @Mapping(source = "osiItemId", target = "osiItem")
    @Mapping(source = "osiItemImageId", target = "osiItemImage")
    @Mapping(source = "osiInvOrgItemsId", target = "osiInvOrgItems")
    @Mapping(source = "osiInvOrgItemPriceId", target = "osiInvOrgItemPrice")
    @Mapping(source = "osiUomClassId", target = "osiUomClass")
    @Mapping(source = "osiUomId", target = "osiUom")
    @Mapping(source = "osiUomConversionId", target = "osiUomConversion")
    OsiBusinessGroup osiBusinessGroupDTOToOsiBusinessGroup(OsiBusinessGroupDTO osiBusinessGroupDTO);

    List<OsiBusinessGroup> osiBusinessGroupDTOsToOsiBusinessGroups(List<OsiBusinessGroupDTO> osiBusinessGroupDTOs);

    default OsiLegalEntities osiLegalEntitiesFromId(Long id) {
        if (id == null) {
            return null;
        }
        OsiLegalEntities osiLegalEntities = new OsiLegalEntities();
        osiLegalEntities.setId(id);
        return osiLegalEntities;
    }

    default OsiOperatingUnit osiOperatingUnitFromId(Long id) {
        if (id == null) {
            return null;
        }
        OsiOperatingUnit osiOperatingUnit = new OsiOperatingUnit();
        osiOperatingUnit.setId(id);
        return osiOperatingUnit;
    }

    default OsiInventoryOrg osiInventoryOrgFromId(Long id) {
        if (id == null) {
            return null;
        }
        OsiInventoryOrg osiInventoryOrg = new OsiInventoryOrg();
        osiInventoryOrg.setId(id);
        return osiInventoryOrg;
    }

    default OsiSegmentStructureHdr osiSegmentStructureHdrFromId(Long id) {
        if (id == null) {
            return null;
        }
        OsiSegmentStructureHdr osiSegmentStructureHdr = new OsiSegmentStructureHdr();
        osiSegmentStructureHdr.setId(id);
        return osiSegmentStructureHdr;
    }

    default OsiSegmentStructureDetails osiSegmentStructureDetailsFromId(Long id) {
        if (id == null) {
            return null;
        }
        OsiSegmentStructureDetails osiSegmentStructureDetails = new OsiSegmentStructureDetails();
        osiSegmentStructureDetails.setId(id);
        return osiSegmentStructureDetails;
    }

    default OsiItem osiItemFromId(Long id) {
        if (id == null) {
            return null;
        }
        OsiItem osiItem = new OsiItem();
        osiItem.setId(id);
        return osiItem;
    }

    default OsiItemImage osiItemImageFromId(Long id) {
        if (id == null) {
            return null;
        }
        OsiItemImage osiItemImage = new OsiItemImage();
        osiItemImage.setId(id);
        return osiItemImage;
    }

    default OsiInvOrgItems osiInvOrgItemsFromId(Long id) {
        if (id == null) {
            return null;
        }
        OsiInvOrgItems osiInvOrgItems = new OsiInvOrgItems();
        osiInvOrgItems.setId(id);
        return osiInvOrgItems;
    }

    default OsiInvOrgItemPrice osiInvOrgItemPriceFromId(Long id) {
        if (id == null) {
            return null;
        }
        OsiInvOrgItemPrice osiInvOrgItemPrice = new OsiInvOrgItemPrice();
        osiInvOrgItemPrice.setId(id);
        return osiInvOrgItemPrice;
    }

    default OsiUomClass osiUomClassFromId(Long id) {
        if (id == null) {
            return null;
        }
        OsiUomClass osiUomClass = new OsiUomClass();
        osiUomClass.setId(id);
        return osiUomClass;
    }

    default OsiUom osiUomFromId(Long id) {
        if (id == null) {
            return null;
        }
        OsiUom osiUom = new OsiUom();
        osiUom.setId(id);
        return osiUom;
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
