package com.osi.fas.configuration.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.osi.fas.configuration.repository.custom.OsiSupplierRepositoryCustom;
import com.osi.fas.domain.OsiSupplier;
import com.osi.fas.domain.OsiSupplierTerms;
import com.osi.urm.exception.DataAccessException;

public class OsiSupplierRepositoryImpl implements OsiSupplierRepositoryCustom {
	@PersistenceContext
    private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OsiSupplier> getAllSuppliers(String searchString, Integer businessGroupId)
			throws DataAccessException {
		List<OsiSupplier> osiSupplierList = null;
		List<Object[]> objectList = null;
		String errorCode = "ERR_1000";
		try {
			if(searchString==null){
				searchString = "";
			}
			String supplierQuery = "select sp.supplierId, sp.supplierCode, sps.supplierSiteCode, sps.supplierSiteId, concat((CASE WHEN concat(sps.address1,' | ') IS NULL THEN '' ELSE sps.address1 END),' | ', (CASE WHEN concat(sps.city,' | ') IS NULL THEN '' ELSE sps.city END),' | ',(CASE WHEN concat(sps.province,' | ') IS NULL THEN '' ELSE sps.province END), (CASE WHEN concat(sps.state,' | ') IS NULL THEN '' ELSE sps.state END),(CASE WHEN concat(sps.zipcode,' | ') IS NULL THEN '' ELSE sps.zipcode END)) as address, t.termId, t.termName, t.termDesc, sps.contactNo, sp.supplierName FROM OsiSupplier sp, OsiSupplierSite sps, OsiSupplierTerms t WHERE sp.osiSupplierTerms.termId = t.termId and sp.supplierId = sps.osiSupplier.supplierId and ( sp.supplierCode like '%"+searchString.toUpperCase()+"%' or upper(sp.supplierName) like '%"+searchString.toUpperCase()+"%' "+
			" or upper(sps.address1) like '%"+searchString.toUpperCase()+"%' or upper(sps.city) like '%"+searchString.toUpperCase()+"%'  or upper(sps.province) like '%"+searchString.toUpperCase()+"%'  or sps.supplierSiteCode like '%"+searchString.toUpperCase()+"%' or upper(sps.supplierSiteName) like '%"+searchString.toUpperCase()+"%' ) AND sp.osiBusinessGroup.businessGroupId = :businessGroupId and sp.active=1";
			objectList = this.entityManager.createQuery(supplierQuery)
					  .setParameter("businessGroupId", businessGroupId)
			          .getResultList();
			if(objectList!=null && objectList.size()==0){
				errorCode = "ERR_1021";
				throw new DataAccessException(errorCode, null);
			}
			osiSupplierList = new ArrayList<OsiSupplier>();
			for (Object[] objects : objectList) {
				String address = "";
				OsiSupplier osiSupplier = new OsiSupplier();
				osiSupplier.setSupplierId(Integer.parseInt(objects[0].toString()));
				osiSupplier.setSupplierCode(objects[1].toString());
				if(objects[1]!=null){
					address = address+objects[1].toString();
				}
				if(objects[2]!=null){
					address = address+" | "+objects[2].toString();
					osiSupplier.setSupplierSitecode(objects[2].toString());
				}
				if(objects[4]!=null){
					address = address+" | "+objects[4].toString();
					osiSupplier.setAddress(objects[4].toString());
				}
				osiSupplier.setSupplierName(address);
				osiSupplier.setSupplierSiteId(Integer.parseInt(objects[3].toString()));
				OsiSupplierTerms osiSupplierTerms = new OsiSupplierTerms();
				osiSupplierTerms.setTermId(Integer.parseInt(objects[5].toString()));
				osiSupplierTerms.setTermName(objects[6].toString());
				if(objects[7]!=null)
					osiSupplierTerms.setTermDesc(objects[7].toString());
				if(objects[8]!=null)
					osiSupplier.setContactNo(objects[8].toString());
				if(objects[9]!=null){
					osiSupplier.setsName(objects[9].toString());
				}
				osiSupplier.setOsiSupplierTerms(osiSupplierTerms);
				osiSupplierList.add(osiSupplier);
			}
		}catch (Exception e) {
			throw new DataAccessException(errorCode, e.getMessage()); 
			//e.printStackTrace();
		}
		return osiSupplierList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsiSupplier> getAllSuppliersBySearchString(String searchString, Integer businessGroupId)
			throws DataAccessException {
		List<OsiSupplier> osiSupplierList = null;
		List<Object[]> objectList = null;
		String errorCode = "ERR_1000";
		try {
			if(searchString==null){
				searchString = "";
			}
			String supplierQuery = "select sp.supplierId, sp.supplierCode, sps.supplierSiteCode, sps.supplierSiteId, "
					+ "concat((CASE WHEN concat(sps.address1,' | ') IS NULL THEN '' ELSE sps.address1 END),' | ', "
					+ "(CASE WHEN concat(sps.city,' | ') IS NULL THEN '' ELSE sps.city END),' | ',"
					+ "(CASE WHEN concat(sps.province,' | ') IS NULL THEN '' ELSE sps.province END), "
					+ "(CASE WHEN concat(sps.state,' | ') IS NULL THEN '' ELSE sps.state END),"
					+ "(CASE WHEN concat(sps.zipcode,' | ') IS NULL THEN '' ELSE sps.zipcode END)) as address, "
					+ "t.termId, t.termName, t.termDesc, sps.contactNo, sp.supplierName "
					+ "FROM OsiSupplier sp, OsiSupplierSite sps, OsiSupplierTerms t "
					+ "WHERE sp.osiSupplierTerms.termId = t.termId and sp.supplierId = sps.osiSupplier.supplierId "
					+ "and ( sp.supplierCode like '"+searchString.toUpperCase()+"%' or upper(sp.supplierName) like '"+searchString.toUpperCase()+"%' "
					+ " or upper(sps.address1) like '"+searchString.toUpperCase()+"%' or upper(sps.city) like '"+searchString.toUpperCase()+"%' "
					+ "or upper(sps.province) like '"+searchString.toUpperCase()+"%'  or sps.supplierSiteCode like '"+searchString.toUpperCase()+"%' "
					+ "or upper(sps.supplierSiteName) like '"+searchString.toUpperCase()+"%' ) "
					+ "AND sp.osiBusinessGroup.businessGroupId = :businessGroupId and sp.active=1";
			objectList = this.entityManager.createQuery(supplierQuery)
					  .setParameter("businessGroupId", businessGroupId)
			          .getResultList();
			if(objectList!=null && objectList.size()==0){
				errorCode = "ERR_1021";
				throw new DataAccessException(errorCode, null);
			}
			osiSupplierList = new ArrayList<OsiSupplier>();
			for (Object[] objects : objectList) {
				String address = "";
				OsiSupplier osiSupplier = new OsiSupplier();
				osiSupplier.setSupplierId(Integer.parseInt(objects[0].toString()));
				osiSupplier.setSupplierCode(objects[1].toString());
				if(objects[1]!=null){
					address = address+objects[1].toString();
				}
				if(objects[2]!=null){
					address = address+" | "+objects[2].toString();
					osiSupplier.setSupplierSitecode(objects[2].toString());
				}
				if(objects[4]!=null){
					address = address+" | "+objects[4].toString();
					osiSupplier.setAddress(objects[4].toString());
				}
				osiSupplier.setSupplierName(address);
				osiSupplier.setSupplierSiteId(Integer.parseInt(objects[3].toString()));
				OsiSupplierTerms osiSupplierTerms = new OsiSupplierTerms();
				osiSupplierTerms.setTermId(Integer.parseInt(objects[5].toString()));
				osiSupplierTerms.setTermName(objects[6].toString());
				if(objects[7]!=null)
					osiSupplierTerms.setTermDesc(objects[7].toString());
				if(objects[8]!=null)
					osiSupplier.setContactNo(objects[8].toString());
				if(objects[9]!=null){
					osiSupplier.setsName(objects[9].toString());
				}
				osiSupplier.setOsiSupplierTerms(osiSupplierTerms);
				osiSupplierList.add(osiSupplier);
			}
		}catch (Exception e) {
			throw new DataAccessException(errorCode, e.getMessage()); 
			//e.printStackTrace();
		}
		return osiSupplierList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OsiSupplier> getSupplierByIdAndSiteId(Integer supplierId,
			Integer siteId, Integer businessGroupId) throws DataAccessException {
		List<OsiSupplier> osiSupplierList = null;
		List<Object[]> objectList = null;
		String errorCode = "ERR_1000";
		try {
			String supplierQuery = "select sp.supplierId, sp.supplierCode, sps.supplierSiteCode, sps.supplierSiteId, concat(sps.address1,' | ', sps.city,' | ',(CASE WHEN concat(sps.province,' | ') IS NULL THEN '' ELSE sps.province END), sps.state,'-', sps.zipcode) as address, t.termId, t.termName, t.termDesc, sps.contactNo, sp.supplierName FROM OsiSupplier sp, OsiSupplierSite sps, OsiSupplierTerms t WHERE sp.osiSupplierTerms.termId = t.termId and sp.supplierId = sps.osiSupplier.supplierId AND sp.osiBusinessGroup.businessGroupId = :businessGroupId and sp.active=1 and sp.supplierId = :supplierId and sps.supplierSiteId = :supplierSiteId";
			objectList = this.entityManager.createQuery(supplierQuery)
					  .setParameter("businessGroupId", businessGroupId)
					  .setParameter("supplierId", supplierId)
					  .setParameter("supplierSiteId", siteId)
			          .getResultList();
			if(objectList!=null && objectList.size()==0){
				errorCode = "ERR_1021";
				throw new DataAccessException(errorCode, null);
			}
			String address = "";
			osiSupplierList = new ArrayList<OsiSupplier>();
			for (Object[] objects : objectList) {
				OsiSupplier osiSupplier = new OsiSupplier();
				osiSupplier.setSupplierId(Integer.parseInt(objects[0].toString()));
				osiSupplier.setSupplierCode(objects[1].toString());
				if(objects[1]!=null)
					address = address + objects[1].toString();
				if(objects[2]!=null)
					address = address +" | "+ objects[2].toString();
				if(objects[4]!=null){
					address = address +" | "+ objects[4].toString();
					osiSupplier.setAddress(objects[4].toString());
				}
				osiSupplier.setSupplierName(address);
				osiSupplier.setSupplierSiteId(Integer.parseInt(objects[3].toString()));
				OsiSupplierTerms osiSupplierTerms = new OsiSupplierTerms();
				osiSupplierTerms.setTermId(Integer.parseInt(objects[5].toString()));
				osiSupplierTerms.setTermName(objects[6].toString());
				if(objects[7]!=null)
					osiSupplierTerms.setTermDesc(objects[7].toString());
				if(objects[8]!=null)
					osiSupplier.setContactNo(objects[8].toString());
				if(objects[9]!=null){
					osiSupplier.setsName(objects[9].toString());
				}
				osiSupplier.setOsiSupplierTerms(osiSupplierTerms);
				osiSupplierList.add(osiSupplier);
			}
		}catch (Exception e) {
			throw new DataAccessException(errorCode, e.getMessage()); 
			//e.printStackTrace();
		}
		return osiSupplierList;
	}

}
