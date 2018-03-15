package com.osi.fas.configuration.mapper;


import java.util.List;

import com.osi.fas.domain.OsiSupplier;
import com.osi.fas.service.dto.OsiSupplierDTO;

/**
 * Mapper for the entity OsiSupplier and its DTO OsiSupplierDTO.
 */
public interface OsiSupplierMapper {

	OsiSupplierDTO osiSupplierToOsiSupplierDTO(OsiSupplier osiSupplier);
	List<OsiSupplierDTO> osiSupplierListToOsiSupplierDTOList(List<OsiSupplier> osiSuppliers);
	OsiSupplier osiSupplierDTOToOsiSupplier(OsiSupplierDTO osiSupplierDTO);
	List<OsiSupplier> osiSupplierDTOListToOsiSupplierList(List<OsiSupplierDTO> osiSupplierDTO);
}
