package com.osi.fas.configuration.repository.custom;

import java.util.List;

import com.osi.fas.domain.OsiTax;
import com.osi.urm.exception.DataAccessException;

public interface OsiTaxRepositoryCustom {

	OsiTax updateOsiTax(OsiTax osiTax) throws DataAccessException;

	List<OsiTax> searchTax(String queryParam, Integer businessGroupId) throws DataAccessException;

	Integer findTaxByCodeId(Integer taxId, String taxCode, Integer businessGroupId) throws DataAccessException;

	Integer findTaxByNameId(Integer taxId, String taxName, Integer businessGroupId) throws DataAccessException;

	Integer findByName(String taxName, Integer businessGroupId) throws DataAccessException;

	Integer findByCode(String taxCode, Integer businessGroupId) throws DataAccessException;

}
