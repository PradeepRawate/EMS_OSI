package com.osi.fas.configuration.repository.custom;

import java.util.List;

import com.osi.fas.domain.OsiUom;
import com.osi.urm.domain.OsiLookupTypes;
import com.osi.urm.exception.DataAccessException;

public interface OsiUomRepositoryCustom {
	public OsiUom updateUom(OsiUom osiUom) throws DataAccessException;
	public List<OsiUom> searchUom(String queryParam,
			Integer businessGroupId) throws DataAccessException;
}
