package com.osi.fas.configuration.mapper;


import java.util.Set;

import com.osi.fas.domain.OsiApprovedSuppliers;
import com.osi.fas.service.dto.OsiApprovedSuppliersDTO;

/**
 * Mapper for the entity OsiApprovedSuppliers and its DTO OsiApprovedSuppliersDTO.
 */
public interface OsiApprovedSuppliersMapper {

	OsiApprovedSuppliersDTO osiApprovedSuppliersToOsiApprovedSuppliersDTO(OsiApprovedSuppliers osiApprovedSuppliers);
	Set<OsiApprovedSuppliersDTO> osiApprovedSuppliersListToOsiApprovedSuppliersDTOList(Set<OsiApprovedSuppliers> osiApprovedSuppliers);
	OsiApprovedSuppliers osiApprovedSuppliersDTOToOsiApprovedSuppliers(OsiApprovedSuppliersDTO osiApprovedSuppliersDTO);
	Set<OsiApprovedSuppliers> osiApprovedSuppliersDTOListToOsiApprovedSuppliersList(Set<OsiApprovedSuppliersDTO> osiApprovedSuppliersDTO);
}
