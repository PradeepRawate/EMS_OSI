package com.osi.fas.configuration.repository.custom.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.osi.fas.configuration.repository.custom.OsiSegmentStructureHdrRepositoryCustom;
import com.osi.fas.domain.OsiSegmentStructureDetails;
import com.osi.fas.domain.OsiSegmentStructureHdr;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.service.dto.OsiUserDTO;

public class OsiSegmentStructureHdrRepositoryImpl implements OsiSegmentStructureHdrRepositoryCustom{
	@PersistenceContext
    private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<OsiSegmentStructureDetails> getSegmentStructureValues(String structureName, Integer businessGroupId) throws DataAccessException {
		List<OsiSegmentStructureDetails> osiSegmentStructureDetailsList = null;
		try {
			String query = "select h.segmentStructureHdrId, d.segmentStructureDetailsSeq, d.segmentStructureDetailsDesc, d.lovDataForValidation, lv.lookupValue, lv.lookupDesc "
					+ " from OsiSegmentStructureHdr h, OsiSegmentStructureDetails d, OsiLookupValues lv, OsiLookupTypes lt, OsiKeyFlex kf "
					+ " where d.osiSegmentStructureHdr.segmentStructureHdrId = h.segmentStructureHdrId and lt.lookupCode = d.lovDataForValidation"
					+ " and lt.id = lv.osiLookupTypes.id and h.segmentStructureHdrDesc=kf.value and kf.name=:segmentStructureHdrDesc and h.businessGroupId=:businessGroupId and h.active = 1 order by d.segmentStructureDetailsSeq, d.lovDataForValidation, lv.lookupValue";
			osiSegmentStructureDetailsList = (List<OsiSegmentStructureDetails>)this.entityManager.createQuery(query)
							  .setParameter("segmentStructureHdrDesc", structureName)
							  .setParameter("businessGroupId", businessGroupId)
					          .getResultList();
			
		}catch (NoResultException e) {
			throw new DataAccessException("ERR_1002", e.getMessage()); 
			//e.printStackTrace();
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return osiSegmentStructureDetailsList;
	}
	
	@Override
	public OsiSegmentStructureHdr updateOsiSegmentStructureHdr(OsiSegmentStructureHdr osiSegmentStructureHdr)
			throws DataAccessException {

		try {

			String deleteOsiSegmentStructureHdrChildsQuery = "delete from OsiSegmentStructureDetails ossd where ossd.osiSegmentStructureHdr=:osiSegmentStructureHdrParent and ossd.businessGroupId = :businessGroupId";
			this.entityManager.createQuery(deleteOsiSegmentStructureHdrChildsQuery)
					.setParameter("osiSegmentStructureHdrParent", osiSegmentStructureHdr)
					.setParameter("businessGroupId", osiSegmentStructureHdr.getBusinessGroupId()).executeUpdate();

			String updateOsiSegmentStructureHdr = "update OsiSegmentStructureHdr ossh set ossh.segmentStructureHdrDesc = :segmentStructureHdrDesc, ossh.businessGroupId = :businessGroupId, ossh.updatedBy = :updatedBy, ossh.updatedDate = :updatedDate where ossh.segmentStructureHdrId = :segmentStructureHdrId";
			int count = this.entityManager.createQuery(updateOsiSegmentStructureHdr)
					.setParameter("segmentStructureHdrDesc", osiSegmentStructureHdr.getSegmentStructureHdrDesc())
					.setParameter("businessGroupId", osiSegmentStructureHdr.getBusinessGroupId())
					.setParameter("updatedBy", osiSegmentStructureHdr.getUpdatedBy())
					.setParameter("updatedDate", osiSegmentStructureHdr.getUpdatedDate())
					.setParameter("segmentStructureHdrId", osiSegmentStructureHdr.getSegmentStructureHdrId())
					.executeUpdate();
			if (count > 0) {
				for (OsiSegmentStructureDetails osiSegmentStructureDetails : osiSegmentStructureHdr
						.getOsiSegmentStructureDetailses()) {
					this.entityManager.persist(osiSegmentStructureDetails);
				}
			}
			if (count == 0) {
				throw new DataAccessException("ERR_1022", null);
			}
		} catch (DataAccessException e) {

			throw new DataAccessException(e.getErrorCode(), e.getMessage());
			// e.printStackTrace();
		} catch (Exception e) {
			throw new DataAccessException("ERR_1021", e.getMessage());
			// e.printStackTrace();
		}

		return osiSegmentStructureHdr;
	}

	@Override
	public int deleteSegmentStructureHdr(List<Integer> segmentStructureHdrIds, OsiUserDTO user)
			throws DataAccessException {

		int count = 0;
		try {
			String query = "update OsiSegmentStructureHdr ossh set ossh.updatedBy = :updatedBy, ossh.updatedDate = :updatedDate, ossh.active = 0 where ossh.segmentStructureHdrId IN ( :segmentStructureHdrIds ) and ossh.businessGroupId= :businessGroupId and ossh.active = 1";
			count = this.entityManager.createQuery(query).setParameter("updatedBy", user.getId())
					.setParameter("updatedDate", new Date()).setParameter("businessGroupId", user.getBusinessGroupId())
					.setParameter("segmentStructureHdrIds", segmentStructureHdrIds).executeUpdate();
			if (count == 0) {
				throw new DataAccessException("ERR_1022", null);
			}
		} catch (DataAccessException e) {
			throw new DataAccessException(e.getErrorCode(), e.getMessage());
			// e.printStackTrace();
		} catch (Exception e) {
			throw new DataAccessException("ERR_1021", e.getMessage());
			// e.printStackTrace();
		}
		return count;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsiSegmentStructureHdr> findAllOsiSegmentStructureHdr(Integer businessGroupId) throws DataAccessException {
		List<OsiSegmentStructureHdr> osiSegmentStructureHdrList = null;
		try {
			String query = "SELECT ossh.segmentStructureHdrId, kf.name as segmentStructureHdrDesc FROM OsiSegmentStructureHdr ossh, OsiKeyFlex kf where kf.value = ossh.segmentStructureHdrDesc and ossh.active = 1 AND ossh.businessGroupId = :businessGroupId ORDER BY ossh.updatedDate desc";
			List<Object[]> objectList = (List<Object[]>)this.entityManager.createQuery(query)
							  .setParameter("businessGroupId", businessGroupId)
							  .setMaxResults(10)
					          .getResultList();
			osiSegmentStructureHdrList = new ArrayList<OsiSegmentStructureHdr>();
			for (Object[] objects : objectList) {
				OsiSegmentStructureHdr osiSegmentStructureHdr = new OsiSegmentStructureHdr();
				if(objects[0]!=null)
					osiSegmentStructureHdr.setSegmentStructureHdrId(Integer.parseInt(objects[0].toString()));
				if(objects[1]!=null)
					osiSegmentStructureHdr.setSegmentStructureHdrDesc(objects[1].toString());
				osiSegmentStructureHdrList.add(osiSegmentStructureHdr);
			}
			
		}catch (NoResultException e) {
			throw new DataAccessException("ERR_1002", e.getMessage()); 
			//e.printStackTrace();
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return osiSegmentStructureHdrList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OsiSegmentStructureHdr> searchOsiSegmentStructureHdr(String queryParam,Integer businessGroupId) throws DataAccessException {
		List<OsiSegmentStructureHdr> osiSegmentStructureHdrList = null;
		try {
			String query = "SELECT ossh.segmentStructureHdrId, kf.name as segmentStructureHdrDesc FROM OsiSegmentStructureHdr ossh, OsiKeyFlex kf where kf.value = ossh.segmentStructureHdrDesc and ossh.active = 1 AND ossh.businessGroupId = :businessGroupId "+queryParam+" ORDER BY ossh.updatedDate desc";
			List<Object[]> objectList = (List<Object[]>)this.entityManager.createQuery(query)
							  .setParameter("businessGroupId", businessGroupId)
					          .getResultList();
			osiSegmentStructureHdrList = new ArrayList<OsiSegmentStructureHdr>();
			for (Object[] objects : objectList) {
				OsiSegmentStructureHdr osiSegmentStructureHdr = new OsiSegmentStructureHdr();
				if(objects[0]!=null)
					osiSegmentStructureHdr.setSegmentStructureHdrId(Integer.parseInt(objects[0].toString()));
				if(objects[1]!=null)
					osiSegmentStructureHdr.setSegmentStructureHdrDesc(objects[1].toString());
				osiSegmentStructureHdrList.add(osiSegmentStructureHdr);
			}
			
		}catch (NoResultException e) {
			throw new DataAccessException("ERR_1002", e.getMessage()); 
			//e.printStackTrace();
		}catch (Exception e) {
			throw new DataAccessException("ERR_1089", e.getMessage()); 
			//e.printStackTrace();
		}
		return osiSegmentStructureHdrList;
	}
	
	public Long isCategoryCreatedWithThisStucture(Integer segmentHdrId,Integer businessGroupId) throws DataAccessException{
		Long countVal=0L;
		try{
			
			String query1 = "Select count(*) from  OsiCategories oc where oc.businessGroupId = :businessGroupId and oc.osiSegmentStructureHdrId=:osiSegmentStructureHdrId";	
			@SuppressWarnings("unchecked")
			Long count = (Long) this.entityManager.createQuery(query1)
						.setParameter("businessGroupId", businessGroupId)
						.setParameter("osiSegmentStructureHdrId", segmentHdrId)
					    .getSingleResult();	
			countVal=count;
		}catch(Exception e){
			e.printStackTrace();
			throw new DataAccessException("ERR_1066" , e.getMessage()); 
		}		
		return countVal;
	}
	
	public Long isCoaCreatedWithThisStucture(Integer segmentHdrId,Integer businessGroupId) throws DataAccessException{
		Long countVal=0L;
		try{
			
			String query1 = "Select count(*) from  OsiCoa oc where oc.businessGroupId = :businessGroupId and oc.osiSegmentStructureHdrId=:osiSegmentStructureHdrId";	
			@SuppressWarnings("unchecked")
			Long count = (Long) this.entityManager.createQuery(query1)
						.setParameter("businessGroupId", businessGroupId)
						.setParameter("osiSegmentStructureHdrId", segmentHdrId)
					    .getSingleResult();	
			countVal=count;
		}catch(Exception e){
			e.printStackTrace();
			throw new DataAccessException("ERR_1066" , e.getMessage()); 
		}		
		return countVal;
	}
	
	
}
