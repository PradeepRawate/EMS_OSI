package com.osi.fas.configuration.mapper;


import java.util.List;

import com.osi.fas.domain.OsiInventoryOrg;
import com.osi.fas.service.dto.OsiInventoryOrgDTO;

/**
 * Mapper for the entity OsiInventoryOrg and its DTO OsiInventoryOrgDTO.
 */
public interface OsiInventoryOrgMapper {

	OsiInventoryOrgDTO osiInventoryOrgToOsiInventoryOrgDTO(OsiInventoryOrg osiInventoryOrg);
	List<OsiInventoryOrgDTO> osiInventoryOrgListToOsiInventoryOrgDTOList(List<OsiInventoryOrg> osiInventoryOrgs);
	OsiInventoryOrg osiInventoryOrgDTOToOsiInventoryOrg(OsiInventoryOrgDTO osiInventoryOrgDTO);
	List<OsiInventoryOrg> osiInventoryOrgDTOListToOsiInventoryOrgList(List<OsiInventoryOrgDTO> osiInventoryOrgDTO);
}
