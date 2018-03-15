package com.osi.fas.configuration.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.fas.configuration.mapper.OsiInventoryOrgMapper;
import com.osi.fas.domain.OsiBusinessGroup;
import com.osi.fas.domain.OsiInventoryOrg;
import com.osi.fas.domain.OsiLocation;
import com.osi.fas.domain.OsiOperatingUnit;
import com.osi.fas.service.dto.OsiBusinessGroupDTO;
import com.osi.fas.service.dto.OsiInventoryOrgDTO;
import com.osi.fas.service.dto.OsiLocationDTO;
import com.osi.fas.service.dto.OsiOperatingUnitDTO;
@Component
public class OsiInventoryOrgMapperImpl implements OsiInventoryOrgMapper {
	
	@Override
	public OsiInventoryOrgDTO osiInventoryOrgToOsiInventoryOrgDTO(OsiInventoryOrg osiInventoryOrg) {
		//OsiInventoryOrgDTO osiInventoryOrgDTO = modelMapper.map(osiInventoryOrg, OsiInventoryOrgDTO.class);
		OsiInventoryOrgDTO osiInventoryOrgDTO = new OsiInventoryOrgDTO();
		osiInventoryOrgDTO.setInvOrgId(osiInventoryOrg.getInvOrgId());
		osiInventoryOrgDTO.setInvOrgCode(osiInventoryOrg.getInvOrgCode());
		osiInventoryOrgDTO.setInvOrgName(osiInventoryOrg.getInvOrgName());
		osiInventoryOrgDTO.setInvOrgType(osiInventoryOrg.getInvOrgType());
		osiInventoryOrgDTO.setInvOrgShortName(osiInventoryOrg.getInvOrgName());
		osiInventoryOrgDTO.setDateFrom(osiInventoryOrg.getDateFrom());
		osiInventoryOrgDTO.setDateTo(osiInventoryOrg.getDateTo());
		osiInventoryOrgDTO.setMaterialIssueTimeoutPeriod(osiInventoryOrg.getMaterialIssueTimeoutPeriod());
		osiInventoryOrgDTO.setMaterialIssueTimeoutAction(osiInventoryOrg.getMaterialIssueTimeoutAction());
		osiInventoryOrgDTO.setIsNegativeBalanceAllowed(osiInventoryOrg.getIsNegativeBalanceAllowed());
		osiInventoryOrgDTO.setPurchasePriceVarianceAccountCoaId(osiInventoryOrg.getPurchasePriceVarianceAccountCoaId());
		osiInventoryOrgDTO.setInvoicePriceVarianceAccountCoaId(osiInventoryOrg.getInvoicePriceVarianceAccountCoaId());
		osiInventoryOrgDTO.setInventoryApAccrualCoaId(osiInventoryOrg.getInventoryApAccrualCoaId());
		osiInventoryOrgDTO.setReceiptRouting(osiInventoryOrg.getReceiptRouting());
		osiInventoryOrgDTO.setRmaReceiptRouting(osiInventoryOrg.getRmaReceiptRouting());
		osiInventoryOrgDTO.setReceivingInvAccountCoaId(osiInventoryOrg.getReceivingInvAccountCoaId());
		osiInventoryOrgDTO.setRetroactivePriceAdjAccountCoaId(osiInventoryOrg.getRetroactivePriceAdjAccountCoaId());
		osiInventoryOrgDTO.setClearingAccountCoaId(osiInventoryOrg.getClearingAccountCoaId());
		osiInventoryOrgDTO.setIsMaster(osiInventoryOrg.getIsMaster());
		osiInventoryOrgDTO.setActive(osiInventoryOrg.getActive());
		osiInventoryOrgDTO.setCreatedBy(osiInventoryOrg.getCreatedBy());
		osiInventoryOrgDTO.setCreatedDate(osiInventoryOrg.getCreatedDate());
		osiInventoryOrgDTO.setUpdatedBy(osiInventoryOrg.getUpdatedBy());
		osiInventoryOrgDTO.setUpdatedDate(osiInventoryOrg.getUpdatedDate());
		osiInventoryOrgDTO.setEncumbranceAccountCoaId(osiInventoryOrg.getEncumbranceAccountCoaId());
		
		osiInventoryOrgDTO.setOsiBusinessGroup(new OsiBusinessGroupDTO());
		osiInventoryOrgDTO.getOsiBusinessGroup().setBusinessGroupId(osiInventoryOrg.getOsiBusinessGroup().getBusinessGroupId());
		
		osiInventoryOrgDTO.setOsiOperatingUnit(new OsiOperatingUnitDTO());
		osiInventoryOrgDTO.getOsiOperatingUnit().setOperatingUnitId(osiInventoryOrg.getOsiOperatingUnit().getOperatingUnitId());
		osiInventoryOrgDTO.getOsiOperatingUnit().setOperatingUnitName(osiInventoryOrg.getOsiOperatingUnit().getOperatingUnitName());
		
		osiInventoryOrgDTO.setOsiLocation(new OsiLocationDTO());
		osiInventoryOrgDTO.getOsiLocation().setLocationId(osiInventoryOrg.getOsiLocation().getLocationId());
		osiInventoryOrgDTO.getOsiLocation().setLocationName(osiInventoryOrg.getOsiLocation().getLocationName());
		
		
		return osiInventoryOrgDTO;
	}

	@Override
	public List<OsiInventoryOrgDTO> osiInventoryOrgListToOsiInventoryOrgDTOList(List<OsiInventoryOrg> osiInventoryOrgs) {
		if ( osiInventoryOrgs == null ) {
            return null;
        }

		List<OsiInventoryOrgDTO> list = new ArrayList<OsiInventoryOrgDTO>();
        for ( OsiInventoryOrg osiInventoryOrgs1 : osiInventoryOrgs ) {
            list.add( osiInventoryOrgToOsiInventoryOrgDTO( osiInventoryOrgs1 ) );
        }

        return list;
	}

	@Override
	public OsiInventoryOrg osiInventoryOrgDTOToOsiInventoryOrg(OsiInventoryOrgDTO osiInventoryOrgDTO) {
		//OsiInventoryOrg osiInventoryOrg = modelMapper.map(osiInventoryOrgDTO, OsiInventoryOrg.class);
	OsiInventoryOrg osiInventoryOrg = new OsiInventoryOrg();
		
		osiInventoryOrg.setInvOrgId(osiInventoryOrgDTO.getInvOrgId());
		osiInventoryOrg.setInvOrgCode(osiInventoryOrgDTO.getInvOrgCode());
		osiInventoryOrg.setInvOrgName(osiInventoryOrgDTO.getInvOrgName());
		osiInventoryOrg.setInvOrgType(osiInventoryOrgDTO.getInvOrgType());
		osiInventoryOrg.setInvOrgShortName(osiInventoryOrgDTO.getInvOrgName());
		osiInventoryOrg.setDateFrom(osiInventoryOrgDTO.getDateFrom());
		osiInventoryOrg.setDateTo(osiInventoryOrgDTO.getDateTo());
		osiInventoryOrg.setMaterialIssueTimeoutPeriod(osiInventoryOrgDTO.getMaterialIssueTimeoutPeriod());
		osiInventoryOrg.setMaterialIssueTimeoutAction(osiInventoryOrgDTO.getMaterialIssueTimeoutAction());
		osiInventoryOrg.setIsNegativeBalanceAllowed(osiInventoryOrgDTO.getIsNegativeBalanceAllowed());
		osiInventoryOrg.setPurchasePriceVarianceAccountCoaId(osiInventoryOrgDTO.getPurchasePriceVarianceAccountCoaId());
		osiInventoryOrg.setInvoicePriceVarianceAccountCoaId(osiInventoryOrgDTO.getInvoicePriceVarianceAccountCoaId());
		osiInventoryOrg.setInventoryApAccrualCoaId(osiInventoryOrgDTO.getInventoryApAccrualCoaId());
		osiInventoryOrg.setReceiptRouting(osiInventoryOrgDTO.getReceiptRouting());
		osiInventoryOrg.setRmaReceiptRouting(osiInventoryOrgDTO.getRmaReceiptRouting());
		osiInventoryOrg.setReceivingInvAccountCoaId(osiInventoryOrgDTO.getReceivingInvAccountCoaId());
		osiInventoryOrg.setRetroactivePriceAdjAccountCoaId(osiInventoryOrgDTO.getRetroactivePriceAdjAccountCoaId());
		osiInventoryOrg.setClearingAccountCoaId(osiInventoryOrgDTO.getClearingAccountCoaId());
		osiInventoryOrg.setIsMaster(osiInventoryOrgDTO.getIsMaster());
		osiInventoryOrg.setActive(osiInventoryOrgDTO.getActive());
		osiInventoryOrg.setCreatedBy(osiInventoryOrgDTO.getCreatedBy());
		osiInventoryOrg.setCreatedDate(osiInventoryOrgDTO.getCreatedDate());
		osiInventoryOrg.setUpdatedBy(osiInventoryOrgDTO.getUpdatedBy());
		osiInventoryOrg.setUpdatedDate(osiInventoryOrgDTO.getUpdatedDate());
		osiInventoryOrg.setEncumbranceAccountCoaId(osiInventoryOrgDTO.getEncumbranceAccountCoaId());
		
		osiInventoryOrg.setOsiBusinessGroup(new OsiBusinessGroup());
		osiInventoryOrg.getOsiBusinessGroup().setBusinessGroupId(osiInventoryOrgDTO.getOsiBusinessGroup().getBusinessGroupId());
		
		osiInventoryOrg.setOsiOperatingUnit(new OsiOperatingUnit());
		osiInventoryOrg.getOsiOperatingUnit().setOperatingUnitId(osiInventoryOrgDTO.getOsiOperatingUnit().getOperatingUnitId());
		
		osiInventoryOrg.setOsiLocation(new OsiLocation());
		osiInventoryOrg.getOsiLocation().setLocationId(osiInventoryOrgDTO.getOsiLocation().getLocationId());
		
		return osiInventoryOrg;
	}

	@Override
	public List<OsiInventoryOrg> osiInventoryOrgDTOListToOsiInventoryOrgList(List<OsiInventoryOrgDTO> osiInventoryOrgDTO) {
		if ( osiInventoryOrgDTO == null ) {
            return null;
        }

		List<OsiInventoryOrg> list = new ArrayList<OsiInventoryOrg>();
        for ( OsiInventoryOrgDTO osiInventoryOrgDTO1 : osiInventoryOrgDTO ) {
            list.add( osiInventoryOrgDTOToOsiInventoryOrg( osiInventoryOrgDTO1 ) );
        }
        return list;
	}
}
