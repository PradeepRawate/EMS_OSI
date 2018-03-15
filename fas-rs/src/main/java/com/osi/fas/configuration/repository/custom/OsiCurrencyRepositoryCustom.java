package com.osi.fas.configuration.repository.custom;

import java.util.List;

import com.osi.fas.domain.OsiCurrency;
import com.osi.urm.exception.DataAccessException;

public interface OsiCurrencyRepositoryCustom {
	List<OsiCurrency> searchCurrency(String queryParam,Integer businessGroupId) throws DataAccessException; 
}
