package com.osi.fas.configuration.repository.custom;

import java.util.List;

import com.osi.fas.domain.OsiSupplier;
import com.osi.urm.exception.DataAccessException;

public interface OsiSupplierRepositoryCustom {
	public List<OsiSupplier> getAllSuppliers(String searchString, Integer businessGroupId) throws DataAccessException;
	public List<OsiSupplier> getAllSuppliersBySearchString(String searchString, Integer businessGroupId) throws DataAccessException;
	public List<OsiSupplier> getSupplierByIdAndSiteId(Integer supplierId,Integer siteId, Integer businessGroupId)  throws DataAccessException;
}
