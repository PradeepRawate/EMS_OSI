package com.osi.fas.configuration.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.osi.fas.configuration.repository.custom.OsiBusinessGroupRepositoryCustom;
import com.osi.urm.domain.OsiLookupValues;
import com.osi.urm.exception.DataAccessException;

public class OsiBusinessGroupRepositoryImpl implements
		OsiBusinessGroupRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<OsiLookupValues> getInventoryOrgTypes(Integer businessGroupId, String lookupName)
			throws DataAccessException {
		List<OsiLookupValues> lookupValuesList = new ArrayList<OsiLookupValues>();
		try {
		String query = "SELECT lv FROM OsiLookupValues lv, OsiLookupTypes lt WHERE lv.osiLookupTypes.id = lt.id AND "
				+ "lt.lookupCode = :lookupName AND lv.businessGroupId = :businessGroupId AND lt.businessGroupId = :businessGroupId ";
		System.out.println("query "+query);
		lookupValuesList = (List<OsiLookupValues>) this.entityManager
				.createQuery(query)
				.setParameter("businessGroupId", businessGroupId)
				.setParameter("lookupName", lookupName)
				.getResultList();
		} catch(Exception e) {
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage());
		}
		return lookupValuesList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsiLookupValues> getReceiptRouting(Integer businessGroupId,
			String lookupName) throws DataAccessException {
		List<OsiLookupValues> lookupValuesList = new ArrayList<OsiLookupValues>();
		try {
		String query = "SELECT lv FROM OsiLookupValues lv, OsiLookupTypes lt WHERE lv.osiLookupTypes.id = lt.id AND "
				+ "lt.lookupCode = :lookupName AND lv.businessGroupId = :businessGroupId AND lt.businessGroupId = :businessGroupId ";
		System.out.println("query "+query);
		lookupValuesList = (List<OsiLookupValues>) this.entityManager
				.createQuery(query)
				.setParameter("businessGroupId", businessGroupId)
				.setParameter("lookupName", lookupName)
				.getResultList();
		} catch(Exception e) {
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage());
		}
		return lookupValuesList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OsiLookupValues> getZoneList(Integer businessGroupId, String lookupName) throws DataAccessException {
		List<OsiLookupValues> lookupValuesList = new ArrayList<OsiLookupValues>();
		try {
		String query = "SELECT lv FROM OsiLookupValues lv, OsiLookupTypes lt WHERE lv.osiLookupTypes.id = lt.id AND lt.active = 1 AND "
				+ "lt.lookupCode = :lookupName AND lv.businessGroupId = :businessGroupId AND lt.businessGroupId = :businessGroupId ";
		System.out.println("query "+query);
		lookupValuesList = (List<OsiLookupValues>) this.entityManager
				.createQuery(query)
				.setParameter("businessGroupId", businessGroupId)
				.setParameter("lookupName", lookupName)
				.getResultList();
		} catch(Exception e) {
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage());
		}
		return lookupValuesList;
	}

}