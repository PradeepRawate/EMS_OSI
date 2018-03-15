package com.osi.fas.configuration.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.fas.configuration.mapper.OsiRolesMapper;
import com.osi.urm.domain.OsiRoles;
import com.osi.urm.service.dto.OsiRolesDTO;
@Component
public class OsiRolesMapperImpl implements OsiRolesMapper {

	@Override
	public OsiRolesDTO osiRolesToOsiRolesDTO(OsiRoles osiRoles) {
		if(osiRoles==null)
		{
			return null;
		}
		OsiRolesDTO osiRolesDTO=new OsiRolesDTO();
		//osiRolesDTO.setBuisenessGroupId(osiRole.getBuisenessGroupId());
		osiRolesDTO.setCreatedBy(osiRoles.getCreatedBy());
		osiRolesDTO.setCreatedDate(osiRoles.getCreatedDate());
		osiRolesDTO.setRoleId(osiRoles.getRoleId());
		osiRolesDTO.setRoleName(osiRoles.getRoleName());
		osiRolesDTO.setRoleDescription(osiRoles.getRoleDescription());
		osiRolesDTO.setStatus(osiRoles.getStatus());
		osiRolesDTO.setUpdatedBy(osiRoles.getUpdatedBy());
		osiRolesDTO.setUpdatedDate(osiRoles.getUpdatedDate());
		return osiRolesDTO;
	}

	@Override
	public List<OsiRolesDTO> osiRolesListToOsiRolesDTOList(List<OsiRoles> osiRoles) {
		if(osiRoles==null)
		{
			return null;
		}
		List<OsiRolesDTO> osiRolesDTOList=new ArrayList<OsiRolesDTO>();
		
		for(OsiRoles osiRoles_:osiRoles)
		{
			osiRolesDTOList.add(osiRolesToOsiRolesDTO(osiRoles_));
		}
		
		return osiRolesDTOList;
	}

	@Override
	public OsiRoles osiRolesDTOToOsiRoles(OsiRolesDTO osiUserRolesDTO) {
		OsiRoles osiRoles=new OsiRoles();
		osiRoles.setBusinessGroupId(osiUserRolesDTO.getBusinessGroupId());
		osiRoles.setCreatedBy(osiUserRolesDTO.getCreatedBy());
		osiRoles.setCreatedDate(osiUserRolesDTO.getCreatedDate());
		osiRoles.setRoleId(osiUserRolesDTO.getRoleId());
		osiRoles.setRoleName(osiUserRolesDTO.getRoleName());
		osiRoles.setStatus(osiUserRolesDTO.getStatus());
		osiRoles.setRoleDescription(osiUserRolesDTO.getRoleDescription());
		osiRoles.setUpdatedBy(osiUserRolesDTO.getUpdatedBy());
		osiRoles.setUpdatedDate(osiUserRolesDTO.getUpdatedDate());
		return osiRoles;
	}

	@Override
	public List<OsiRoles> osiRolesDTOListToOsiRolesList(List<OsiRolesDTO> osiUserRolesDTO) {
		List<OsiRoles> osiRolesList=new ArrayList<OsiRoles>();
		for(OsiRolesDTO osiRolesDTO_:osiUserRolesDTO)
		{
			osiRolesList.add(osiRolesDTOToOsiRoles(osiRolesDTO_));
		}
		return osiRolesList;
	}

}
