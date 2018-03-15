package com.osi.fas.configuration.mapper.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.osi.fas.configuration.mapper.OsiApprovedSuppliersMapper;
import com.osi.fas.domain.OsiApprovedSuppliers;
import com.osi.fas.service.dto.OsiApprovedSuppliersDTO;
@Component
public class OsiApprovedSuppliersMapperImpl implements OsiApprovedSuppliersMapper {
	

	@Override
	public OsiApprovedSuppliersDTO osiApprovedSuppliersToOsiApprovedSuppliersDTO(OsiApprovedSuppliers osiApprovedSuppliers) {
		//OsiApprovedSuppliersDTO osiApprovedSuppliersDTO = modelMapper.map(osiApprovedSuppliers, OsiApprovedSuppliersDTO.class);
		OsiApprovedSuppliersDTO osiApprovedSuppliersDTO = new OsiApprovedSuppliersDTO();
		osiApprovedSuppliersDTO.setApprovedSupplierId(osiApprovedSuppliers.getApprovedSupplierId());
		//osiApprovedSuppliersDTO.setBusinessGroupId(businessGroupId);
		return osiApprovedSuppliersDTO;
	}

	@Override
	public Set<OsiApprovedSuppliersDTO> osiApprovedSuppliersListToOsiApprovedSuppliersDTOList(Set<OsiApprovedSuppliers> osiApprovedSupplierss) {
		if ( osiApprovedSupplierss == null ) {
            return null;
        }

		Set<OsiApprovedSuppliersDTO> list = new HashSet<OsiApprovedSuppliersDTO>();
        for ( OsiApprovedSuppliers osiApprovedSupplierss1 : osiApprovedSupplierss ) {
            list.add( osiApprovedSuppliersToOsiApprovedSuppliersDTO( osiApprovedSupplierss1 ) );
        }

        return list;
	}

	@Override
	public OsiApprovedSuppliers osiApprovedSuppliersDTOToOsiApprovedSuppliers(OsiApprovedSuppliersDTO osiApprovedSuppliersDTO) {
		//OsiApprovedSuppliers osiApprovedSuppliers = modelMapper.map(osiApprovedSuppliersDTO, OsiApprovedSuppliers.class);
		OsiApprovedSuppliers osiApprovedSuppliers = new OsiApprovedSuppliers();
		osiApprovedSuppliers.setApprovedSupplierId(osiApprovedSuppliersDTO.getApprovedSupplierId());
		return osiApprovedSuppliers;
	}

	@Override
	public Set<OsiApprovedSuppliers> osiApprovedSuppliersDTOListToOsiApprovedSuppliersList(Set<OsiApprovedSuppliersDTO> osiApprovedSuppliersDTO) {
		if ( osiApprovedSuppliersDTO == null ) {
            return null;
        }

		Set<OsiApprovedSuppliers> list = new HashSet<OsiApprovedSuppliers>();
        for ( OsiApprovedSuppliersDTO osiApprovedSuppliersDTO1 : osiApprovedSuppliersDTO ) {
            list.add( osiApprovedSuppliersDTOToOsiApprovedSuppliers( osiApprovedSuppliersDTO1 ) );
        }
        return list;
	}
}
