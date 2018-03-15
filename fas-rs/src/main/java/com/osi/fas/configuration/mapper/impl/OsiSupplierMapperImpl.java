package com.osi.fas.configuration.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.fas.configuration.mapper.OsiSupplierMapper;
import com.osi.fas.domain.OsiSupplier;
import com.osi.fas.service.dto.OsiSupplierDTO;
import com.osi.fas.service.dto.OsiSupplierTermsDTO;
@Component
public class OsiSupplierMapperImpl implements OsiSupplierMapper {
	
	@Override
	public OsiSupplierDTO osiSupplierToOsiSupplierDTO(OsiSupplier osiSupplier) {
		//OsiSupplierDTO osiSupplierDTO = modelMapper.map(osiSupplier, OsiSupplierDTO.class);
		OsiSupplierDTO osiSupplierDTO = new OsiSupplierDTO();
		osiSupplierDTO.setSupplierId(osiSupplier.getSupplierId());
		osiSupplierDTO.setSupplierCode(osiSupplier.getSupplierCode());
		osiSupplierDTO.setSupplierName(osiSupplier.getSupplierName());
		osiSupplierDTO.setSupplierSiteId(osiSupplier.getSupplierSiteId());
		osiSupplierDTO.setContactNo(osiSupplier.getContactNo());
		osiSupplierDTO.setSupplierSiteCode(osiSupplier.getSupplierSitecode());
		osiSupplierDTO.setAddress(osiSupplier.getAddress());
		osiSupplierDTO.setsName(osiSupplier.getsName());
		OsiSupplierTermsDTO osiSupplierTermsDTO = new OsiSupplierTermsDTO();
		osiSupplierTermsDTO.setTermId(osiSupplier.getOsiSupplierTerms().getTermId());
		osiSupplierTermsDTO.setTermName(osiSupplier.getOsiSupplierTerms().getTermName());
		osiSupplierTermsDTO.setTermDesc(osiSupplier.getOsiSupplierTerms().getTermDesc());
		osiSupplierDTO.setOsiSupplierTerms(osiSupplierTermsDTO);
		return osiSupplierDTO;
	}

	@Override
	public List<OsiSupplierDTO> osiSupplierListToOsiSupplierDTOList(List<OsiSupplier> osiSuppliers) {
		if ( osiSuppliers == null ) {
            return null;
        }

		List<OsiSupplierDTO> list = new ArrayList<OsiSupplierDTO>();
        for ( OsiSupplier osiSuppliers1 : osiSuppliers ) {
            list.add( osiSupplierToOsiSupplierDTO( osiSuppliers1 ) );
        }

        return list;
	}

	@Override
	public OsiSupplier osiSupplierDTOToOsiSupplier(OsiSupplierDTO osiSupplierDTO) {
		//OsiSupplier osiSupplier = modelMapper.map(osiSupplierDTO, OsiSupplier.class);
		OsiSupplier osiSupplier = new OsiSupplier();
		osiSupplier.setSupplierId(osiSupplierDTO.getSupplierId());
		osiSupplier.setSupplierCode(osiSupplierDTO.getSupplierCode());
		osiSupplier.setSupplierName(osiSupplierDTO.getSupplierName());
		return osiSupplier;
	}

	@Override
	public List<OsiSupplier> osiSupplierDTOListToOsiSupplierList(List<OsiSupplierDTO> osiSupplierDTO) {
		if ( osiSupplierDTO == null ) {
            return null;
        }

		List<OsiSupplier> list = new ArrayList<OsiSupplier>();
        for ( OsiSupplierDTO osiSupplierDTO1 : osiSupplierDTO ) {
            list.add( osiSupplierDTOToOsiSupplier( osiSupplierDTO1 ) );
        }
        return list;
	}
}
