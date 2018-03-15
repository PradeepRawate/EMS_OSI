package com.osi.fas.configuration.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.osi.fas.configuration.repository.custom.OsiTaxRepositoryCustom;
import com.osi.fas.domain.OsiTax;
import com.osi.urm.exception.DataAccessException;

public class OsiTaxRepositoryImpl implements OsiTaxRepositoryCustom {
	@PersistenceContext
	private EntityManager entityManager;

	public OsiTax updateOsiTax(OsiTax osiTax) throws DataAccessException {
		Integer count = 0;

		try {

			String updatingOsiTax = "UPDATE OsiTax  SET taxCode = :taxCode,  taxName = :taxName, taxValue = :taxValue, taxType = :taxType, active = :active, updatedBy = :updatedBy, updatedDate = :updatedDate WHERE taxId = :taxId and businessGroupId = :businessGroupId";
			count = this.entityManager.createQuery(updatingOsiTax).setParameter("taxCode", osiTax.getTaxCode())
					.setParameter("businessGroupId", osiTax.getBusinessGroupId())
					.setParameter("taxName", osiTax.getTaxName()).setParameter("taxValue", osiTax.getTaxValue())
					.setParameter("taxType", osiTax.getTaxType()).setParameter("active", osiTax.getActive())
					.setParameter("updatedBy", osiTax.getUpdatedBy())
					.setParameter("updatedDate", osiTax.getUpdatedDate()).setParameter("taxId", osiTax.getTaxId())
					.executeUpdate();
			if (count == 0) {
				throw new DataAccessException("ERR_1022", null);
			}
		} catch (DataAccessException e) {

			throw new DataAccessException(e.getErrorCode(), e.getMessage());
			// e.printStackTrace();
		} catch (Exception e) {
			throw new DataAccessException("ERR_1048", e.getMessage());
			// e.printStackTrace();
		}

		return osiTax;

	}

	@Override
	public List<OsiTax> searchTax(String queryParam, Integer businessGroupId) throws DataAccessException {

		List<OsiTax> osiTaxList = new ArrayList<OsiTax>();
		try {
			if (queryParam != null) {
				String searchQuery = "SELECT ot FROM OsiTax ot where ot.businessGroupId = :businessGroupId "
						+ queryParam+"  order by ot.updatedDate desc";
				System.out.println("The Query is like : " + searchQuery);
				@SuppressWarnings("unchecked")
				List<OsiTax> taxLists = (List<OsiTax>) this.entityManager.createQuery(searchQuery)
						.setParameter("businessGroupId", businessGroupId).getResultList();
				for (OsiTax osiTax2 : taxLists) {
					if (osiTax2 != null) {
						osiTaxList.add((OsiTax) osiTax2);
					} else {

						throw new DataAccessException("ERR_1002", null);
					}

				}
			}

		} catch (DataAccessException e) {
			throw new DataAccessException(e.getErrorCode(), e.getMessage());
			// e.printStackTrace();
		}

		return osiTaxList;
	}

	public Integer findTaxByNameId(Integer taxId, String taxName, Integer businessGroupId) throws DataAccessException {
		Integer count = 0;
		try {
			String query = "select count(*) as rec FROM OsiTax WHERE businessGroupId = :businessGroupId and upper(taxName) =:taxName and taxId !=:taxId";
			List list = this.entityManager.createQuery(query).setParameter("taxId", taxId)
					.setParameter("taxName", taxName.toUpperCase())
					.setParameter("businessGroupId", businessGroupId)
					.getResultList();
			if (list != null && list.get(0) != null && Integer.parseInt(list.get(0).toString()) > 0)
				count = 1;
		} catch (Exception e) {
			throw new DataAccessException("ERR_1089", e.getMessage());
			// e.printStackTrace();
		}
		return count;
	}

	public Integer findTaxByCodeId(Integer taxId, String taxCode, Integer businessGroupId) throws DataAccessException {
		Integer count = 0;
		try {
			String query = "select count(*) as rec FROM OsiTax WHERE businessGroupId = :businessGroupId and upper(taxCode) =:taxCode and taxId !=:taxId";
			List list = this.entityManager.createQuery(query).setParameter("taxId", taxId)
					.setParameter("taxCode", taxCode.toUpperCase())
					.setParameter("businessGroupId", businessGroupId)
					.getResultList();
			if (list != null && list.get(0) != null && Integer.parseInt(list.get(0).toString()) > 0)
				count = 1;
		} catch (Exception e) {
			throw new DataAccessException("ERR_1089", e.getMessage());
			// e.printStackTrace();
		}
		return count;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public Integer findByCode(String taxCode, Integer businessGroupId) throws DataAccessException {
		Integer count = 0;
		try {
			String query = "select count(*) as rec FROM OsiTax WHERE businessGroupId = :businessGroupId and upper(taxCode) =:taxCode";
			List list = this.entityManager.createQuery(query)
							  .setParameter("taxCode", taxCode.toUpperCase())
							  .setParameter("businessGroupId", businessGroupId)
					          .getResultList();
			if(list!=null && list.get(0)!=null && Integer.parseInt(list.get(0).toString())>0)
				count = 1;
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return count;
	}

	@Override
	public Integer findByName(String taxName, Integer businessGroupId) throws DataAccessException {
		Integer count = 0;
		try {
			String query = "select count(*) as rec FROM OsiTax WHERE businessGroupId = :businessGroupId and upper(taxName) =:taxName";
			List list = this.entityManager.createQuery(query)
							  .setParameter("taxName", taxName.toUpperCase())
							  .setParameter("businessGroupId", businessGroupId)
							  .getResultList();
			if(list!=null && list.get(0)!=null && Integer.parseInt(list.get(0).toString())>0)
				count = 1;
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return count;
	}
	
	
	
	
	
	
	
	
	

}
