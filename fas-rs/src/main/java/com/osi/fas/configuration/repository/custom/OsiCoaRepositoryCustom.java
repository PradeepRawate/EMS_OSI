package com.osi.fas.configuration.repository.custom;

import java.util.List;

import com.osi.fas.domain.OsiCoa;
import com.osi.urm.domain.OsiLookupValues;
import com.osi.urm.exception.DataAccessException;

public interface OsiCoaRepositoryCustom {
	
	
	public Integer updateCoa(OsiCoa osiCoa) throws DataAccessException;
	public Integer deleteCoa(List<Integer> coaIds, Integer businessGroupId, Integer userId) throws DataAccessException;
	public Integer findCoaByCodeId(Integer coaId,String coaCode, Integer businessGroupId) throws DataAccessException;
    public Integer findCoaByNameId(Integer coaId,String coaName, Integer businessGroupId) throws DataAccessException;
	public Integer findCoaByCode(String coaCode,  Integer businessGroupId) throws DataAccessException;
	public Integer findCoaByName(String coaName, Integer businessGroupId) throws DataAccessException;
	//search
	public List<OsiCoa> searchCoa(String string, Integer businessGroupId) throws DataAccessException;
	public OsiCoa validateCoaCode(String coaCode, Integer businessGroupId) throws DataAccessException;
	public List<OsiLookupValues> getCoaNextSegmentValues(String code, Integer seq, Integer businessGroupId) throws DataAccessException;
}

