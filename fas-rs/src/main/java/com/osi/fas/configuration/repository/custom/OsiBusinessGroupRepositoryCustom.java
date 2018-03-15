package com.osi.fas.configuration.repository.custom;

import java.util.List;

import com.osi.urm.domain.OsiLookupValues;
import com.osi.urm.exception.DataAccessException;

public interface OsiBusinessGroupRepositoryCustom {

	public List<OsiLookupValues> getInventoryOrgTypes(Integer businessGroupId, String lookupName) throws DataAccessException;
	
	public List<OsiLookupValues> getReceiptRouting(Integer businessGroupId, String lookupName) throws DataAccessException;
	
	public List<OsiLookupValues> getZoneList(Integer businessGroupId, String lookupName) throws DataAccessException;
	
}
