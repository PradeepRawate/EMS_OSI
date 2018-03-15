package com.osi.fas.configuration.repository.custom;

import java.util.List;

import com.osi.fas.domain.OsiInventoryOrg;
import com.osi.urm.exception.DataAccessException;

public interface OsiInventoryOrgRepositoryCustom {
	public List<OsiInventoryOrg> getAllInventoryOrgs(String searchString, Integer businessGroupId) throws DataAccessException;
	public OsiInventoryOrg getMasterOrganization(Integer businessGroupId)
			throws DataAccessException;
	public List<OsiInventoryOrg> getAllActiveInventoryOrgs(Integer businessGroupId) throws DataAccessException;
        
        public List<OsiInventoryOrg> getAllInventoryOrgsExclusions(String searchString,String invOrgId,Integer businessGroupId) throws DataAccessException;
}
