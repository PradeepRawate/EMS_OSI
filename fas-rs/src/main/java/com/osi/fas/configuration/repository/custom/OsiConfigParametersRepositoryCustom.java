package com.osi.fas.configuration.repository.custom;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.osi.fas.domain.OsiConfigParameters;
import com.osi.urm.exception.DataAccessException;

public interface OsiConfigParametersRepositoryCustom {
	public Integer updateConfigParameters(OsiConfigParameters osiConfigParameters) throws DataAccessException;
	List<OsiConfigParameters> findConfigParametersByBusinessGroupIdAndName(String configName, Integer businessGroupId) throws DataAccessException;
	public String getConfigParametersByName(String configName, Integer businessGroupId) throws DataAccessException;

}
