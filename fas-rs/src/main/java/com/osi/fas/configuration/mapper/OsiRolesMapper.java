package com.osi.fas.configuration.mapper;

import java.util.List;

import com.osi.urm.domain.OsiRoles;
import com.osi.urm.service.dto.OsiRolesDTO;

public interface OsiRolesMapper {
	
	OsiRolesDTO osiRolesToOsiRolesDTO(OsiRoles osiRole);
		
	List<OsiRolesDTO> osiRolesListToOsiRolesDTOList(List<OsiRoles> osiRoles);
		
	OsiRoles osiRolesDTOToOsiRoles(OsiRolesDTO osiUserRoleDTO);
		
	List<OsiRoles> osiRolesDTOListToOsiRolesList(List<OsiRolesDTO> osiUserRoleDTO);

}
