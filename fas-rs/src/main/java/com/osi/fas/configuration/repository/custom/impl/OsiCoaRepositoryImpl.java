package com.osi.fas.configuration.repository.custom.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.osi.fas.configuration.repository.custom.OsiCoaRepositoryCustom;
import com.osi.fas.domain.OsiCoa;
import com.osi.urm.config.AppConfig;
import com.osi.urm.domain.OsiLookupValues;
import com.osi.urm.exception.DataAccessException;

public class OsiCoaRepositoryImpl implements OsiCoaRepositoryCustom{
	@PersistenceContext
    private EntityManager entityManager;

	@Autowired
	private AppConfig appConfig;
	@Override
	public Integer updateCoa(OsiCoa osiCoa) throws DataAccessException {
		Integer count = 0;
		try {
			String query = "update OsiCoa set coaDesc = :coaDesc, coaCode = :coaCode,coaName = :coaName, updatedBy = :updatedBy, updatedDate = :updatedDate where businessGroupId = :businessGroupId and coaId =:coaId";
			count = this.entityManager.createQuery(query)
							  .setParameter("coaDesc", osiCoa.getCoaDesc())
							  .setParameter("coaCode", osiCoa.getCoaCode())
							  .setParameter("coaName", osiCoa.getCoaName())
							  .setParameter("updatedBy", osiCoa.getUpdatedBy())
							  .setParameter("updatedDate", osiCoa.getUpdatedDate())
							  .setParameter("businessGroupId", osiCoa.getBusinessGroupId())
							  .setParameter("coaId", osiCoa.getCoaId())
					          .executeUpdate();
			if(count==0){
				throw new DataAccessException("ERR_1011", null);
			}
		}catch (DataAccessException e) {
			throw new DataAccessException(e.getErrorCode(), e.getMessage()); 
			//e.printStackTrace();
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return count;
	}
	
	public Integer deleteCoa(List<Integer> coaIds, Integer businessGroupId, Integer userId) throws DataAccessException {
		Integer count = 0;
		try {
			String query = "update OsiCoa set updatedBy = :updatedBy, updatedDate = :updatedDate where businessGroupId = :businessGroupId and coaId IN (:coaId)";
			count = this.entityManager.createQuery(query)
							  .setParameter("updatedBy", userId)
							  .setParameter("updatedDate", new Date())
							  .setParameter("businessGroupId", businessGroupId)
							  .setParameter("coaId", coaIds)
					          .executeUpdate();
			if(count==0){
				throw new DataAccessException("ERR_1012", null);
			}
		}catch (DataAccessException e) {
			throw new DataAccessException(e.getErrorCode(), e.getSystemMessage()); 
			//e.printStackTrace();
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return count;
		
		
	}

	@Override
	public Integer findCoaByCodeId(Integer coaId, String coaCode, Integer businessGroupId)
			throws DataAccessException {
		Integer count = 0;
		try {
			String query = "select count(*) as rec FROM OsiCoa WHERE businessGroupId = :businessGroupId and upper(coaCode) =:coaCode and coaId !=:coaId";
			List list = this.entityManager.createQuery(query)
							  .setParameter("coaId", coaId)
							  .setParameter("coaCode", coaCode.toUpperCase())
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
	public Integer findCoaByNameId(Integer coaId, String coaName, Integer businessGroupId)
			throws DataAccessException {
		Integer count = 0;
		try {
			String query = "select count(*) as rec FROM OsiCoa WHERE businessGroupId = :businessGroupId and upper(coaName) =:coaName and coaId !=:coaId";
			List list = this.entityManager.createQuery(query)
							  .setParameter("coaId", coaId)
							  .setParameter("coaName", coaName.toUpperCase())
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
	public Integer findCoaByCode(String coaCode, Integer businessGroupId) throws DataAccessException {
		Integer count = 0;
		try {
			String query = "select count(*) as rec FROM OsiCoa WHERE businessGroupId = :businessGroupId and upper(coaCode) =:coaCode";
			List list = this.entityManager.createQuery(query)
							  .setParameter("coaCode", coaCode.toUpperCase())
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
	public Integer findCoaByName(String coaName, Integer businessGroupId) throws DataAccessException {
		Integer count = 0;
		try {
			String query = "select count(*) as rec FROM OsiCoa WHERE businessGroupId = :businessGroupId and upper(coaName) =:coaName";
			List list = this.entityManager.createQuery(query)
							  .setParameter("coaName", coaName.toUpperCase())
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
	public List<OsiCoa> searchCoa(String queryParam, Integer businessGroupId)
			throws DataAccessException {
		List<OsiCoa> list = new ArrayList<OsiCoa>();
		try{
			if(queryParam!=null){
				String query = "Select ca from OsiCoa ca where ca.businessGroupId = :businessGroupId "+queryParam+"  order by ca.updatedDate desc";
				//System.out.println("query "+query);
				@SuppressWarnings("unchecked")
				List<OsiCoa> lists = (List<OsiCoa>) this.entityManager.createQuery(query)
						  .setParameter("businessGroupId", businessGroupId)
						  .getResultList();
				
				for(OsiCoa c : lists ){
					if (c != null)
						list.add((OsiCoa) c);
				}
			}else{
				throw new DataAccessException("ERR_1092",  null); 
			}
		}catch (DataAccessException e) {
			throw new DataAccessException(e.getErrorCode() , e.getMessage()); 
			//e.printStackTrace();
		}
		return list;
	}

	@Override
	public OsiCoa validateCoaCode(String coaCode, Integer businessGroupId) throws DataAccessException {
		OsiCoa osiCoa = null;
		try{
				String query = "from OsiCoa ca where ca.businessGroupId = :businessGroupId and ca.coaCode=:coaCode";
				osiCoa = (OsiCoa) this.entityManager.createQuery(query)
						  .setParameter("businessGroupId", businessGroupId)
						  .setParameter("coaCode", coaCode)
						  .getSingleResult();
				
		}catch (NoResultException e) {
			throw new DataAccessException("ERR_1002" , e.getMessage()); 
			//e.printStackTrace();
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000" , e.getMessage()); 
			//e.printStackTrace();
		}
		return osiCoa;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<OsiLookupValues> getCoaNextSegmentValues(String code, Integer seq, Integer businessGroupId) throws DataAccessException {
		List<OsiLookupValues> strList = null;
		try {
			code = code+appConfig.getCategoryDelimiter();
			String query =" SELECT lv.lookupValue, lv.lookupDesc FROM  OsiSegmentStructureHdr h, OsiSegmentStructureDetails d, OsiLookupValues lv, OsiLookupTypes lt, OsiKeyFlex kf "
							+ " where d.osiSegmentStructureHdr.segmentStructureHdrId = h.segmentStructureHdrId and lt.lookupCode = d.lovDataForValidation and d.segmentStructureDetailsSeq = :segmentStructureDetailsSeq "
							+ " and lt.id = lv.osiLookupTypes.id and h.segmentStructureHdrDesc=kf.value and kf.name=:segmentStructureHdrDesc and h.businessGroupId=:businessGroupId and h.active = 1 "
					+" and lv.lookupValue IN ( SELECT DISTINCT CASE WHEN LOCATE(:delimiter, SUBSTRING(coaCode, LENGTH(:code)+1, LENGTH(coaCode)))= 0 THEN SUBSTRING(coaCode, LENGTH(:code)+1, LENGTH( coaCode)) ELSE "
					+ " SUBSTRING(coaCode,LENGTH(:code)+1, LOCATE(:delimiter, SUBSTRING(coaCode, LENGTH(:code)+1, LENGTH(coaCode)))-1) END AS final FROM OsiCoa WHERE businessGroupId = :businessGroupId and coaCode like '%"+code+"%'"
					+ " ) order by lv.lookupValue";
			List<Object[]> strList1 = (List<Object[]>)this.entityManager.createQuery(query)
							  .setParameter("code", code)
							  .setParameter("delimiter", appConfig.getCoaDelimiter())
							  .setParameter("businessGroupId", businessGroupId)
							  .setParameter("segmentStructureHdrDesc", appConfig.getCoaStructure())
							  .setParameter("segmentStructureDetailsSeq", seq)
					          .getResultList();
			strList = new ArrayList<OsiLookupValues>();
			for (Object[] objects : strList1) {
				OsiLookupValues osiLookupValues = new OsiLookupValues();
				osiLookupValues.setLookupValue(objects[0].toString());
				osiLookupValues.setLookupDesc(objects[1].toString());
				strList.add(osiLookupValues);
			}
		}catch (NoResultException e) {
			throw new DataAccessException("ERR_1002", e.getMessage()); 
			//e.printStackTrace();
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return strList;
	}
	
}
