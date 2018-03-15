package com.osi.fas.configuration.repository.custom.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.osi.fas.configuration.repository.custom.OsiCategoriesRepositoryCustom;
import com.osi.fas.domain.OsiCategories;
import com.osi.urm.config.AppConfig;
import com.osi.urm.domain.OsiLookupValues;
import com.osi.urm.exception.DataAccessException;

public class OsiCategoriesRepositoryImpl implements OsiCategoriesRepositoryCustom{
	@PersistenceContext
    private EntityManager entityManager;

	@Autowired
	private AppConfig appConfig;
	
	@Override
	public Integer updateCategory(OsiCategories osiCategories) throws DataAccessException {
		Integer count = 0;
		try {
			String query = "update OsiCategories set categoryDesc = :categoryDesc, categoryCode = :categoryCode, categoryName = :categoryName, updatedBy = :updatedBy, updatedDate = :updatedDate,active=:active,osiCoa = :osiCoa where businessGroupId = :businessGroupId and categoryId =:categoryId";
			count = this.entityManager.createQuery(query)
							  .setParameter("categoryDesc", osiCategories.getCategoryDesc())
							  .setParameter("categoryCode", osiCategories.getCategoryCode())
							  .setParameter("categoryName", osiCategories.getCategoryName())
							  .setParameter("updatedBy", osiCategories.getUpdatedBy())
							  .setParameter("updatedDate", osiCategories.getUpdatedDate())
							  .setParameter("active", osiCategories.getActive())
							  .setParameter("businessGroupId", osiCategories.getBusinessGroupId())
							  .setParameter("categoryId", osiCategories.getCategoryId())
							  .setParameter("osiCoa", osiCategories.getOsiCoa())
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
	
	@Override
	public Integer deleteCategories(List<Integer> categoryIds, Integer businessGroupId, Integer userId) throws DataAccessException {
		Integer count = 0;
		try {
			String query = "update OsiCategories set active = 0, updatedBy = :updatedBy, updatedDate = :updatedDate where businessGroupId = :businessGroupId and categoryId IN (:categoryId)";
			count = this.entityManager.createQuery(query)
							  .setParameter("updatedBy", userId)
							  .setParameter("updatedDate", new Date())
							  .setParameter("businessGroupId", businessGroupId)
							  .setParameter("categoryId", categoryIds)
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
	public Integer findCategoriesByCodeId(Integer categoryId, String categoryCode, Integer businessGroupId)
			throws DataAccessException {
		Integer count = 0;
		try {
			String query = "select count(*) as rec FROM OsiCategories WHERE businessGroupId = :businessGroupId and upper(categoryCode) =:categoryCode and categoryId !=:categoryId ";
			List list = this.entityManager.createQuery(query)
							  .setParameter("categoryId", categoryId)
							  .setParameter("categoryCode", categoryCode.toUpperCase())
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
	public Integer findCategoriesByNameId(Integer categoryId, String categoryName, Integer businessGroupId)
			throws DataAccessException {
		Integer count = 0;
		try {
			String query = "select count(*) as rec FROM OsiCategories WHERE businessGroupId = :businessGroupId and upper(categoryName) =:categoryName and categoryId !=:categoryId";
			List list = this.entityManager.createQuery(query)
							  .setParameter("categoryId", categoryId)
							  .setParameter("categoryName", categoryName.toUpperCase())
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
	public Integer findCategoriesByCode(String categoryCode, Integer businessGroupId) throws DataAccessException {
		Integer count = 0;
		try {
			String query = "select count(*) as rec FROM OsiCategories WHERE businessGroupId = :businessGroupId and upper(categoryCode) =:categoryCode";
			List list = this.entityManager.createQuery(query)
							  .setParameter("categoryCode", categoryCode.toUpperCase())
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
	public Integer findCategoriesByName(String categoryName, Integer businessGroupId) throws DataAccessException {
		Integer count = 0;
		try {
			String query = "select count(*) as rec FROM OsiCategories WHERE businessGroupId = :businessGroupId and upper(categoryName) =:categoryName";
			List list = this.entityManager.createQuery(query)
							  .setParameter("categoryName", categoryName.toUpperCase())
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
	public List<OsiCategories> searchCategories(String queryParam,
			Integer businessGroupId) throws DataAccessException {
		List<OsiCategories> list = new ArrayList<OsiCategories>();
		try{
			if(queryParam!=null){
				String query = "Select c from OsiCategories c where c.businessGroupId = :businessGroupId"+queryParam+" ORDER BY c.updatedDate desc";
				//System.out.println("query "+query);
				@SuppressWarnings("unchecked")
				List<OsiCategories> lists = (List<OsiCategories>) this.entityManager.createQuery(query)
						  .setParameter("businessGroupId", businessGroupId)
						  .getResultList();
				
				for(OsiCategories c : lists ){
					if (c != null)
						list.add((OsiCategories) c);
				}
			}else{
				throw new DataAccessException("ERR_1026",  null); 
			}
		}catch (DataAccessException e) {
			throw new DataAccessException(e.getErrorCode() , e.getMessage()); 
			//e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public OsiCategories validateCategoryCode(String categoryCode, Integer businessGroupId) throws DataAccessException {
		OsiCategories osiCategories = null;
		try {
			String query = "FROM OsiCategories WHERE businessGroupId = :businessGroupId and categoryCode =:categoryCode and active=1";
			osiCategories = (OsiCategories)this.entityManager.createQuery(query)
							  .setParameter("categoryCode", categoryCode.toUpperCase())
							  .setParameter("businessGroupId", businessGroupId)
					          .getSingleResult();
		}catch (NoResultException e) {
			throw new DataAccessException("ERR_1002", e.getMessage()); 
			//e.printStackTrace();
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return osiCategories;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OsiLookupValues> getCategoryNextSegmentValues(String code, Integer seq, Integer businessGroupId) throws DataAccessException {
		List<OsiLookupValues> strList = null;
		try {
			code = code+appConfig.getCategoryDelimiter();
			String query =  "SELECT lv.lookupValue, lv.lookupDesc FROM  OsiSegmentStructureHdr h, OsiSegmentStructureDetails d, OsiLookupValues lv, OsiLookupTypes lt, OsiKeyFlex kf "
					+ " where d.osiSegmentStructureHdr.segmentStructureHdrId = h.segmentStructureHdrId and lt.lookupCode = d.lovDataForValidation and d.segmentStructureDetailsSeq = :segmentStructureDetailsSeq "
					+ " and lt.id = lv.osiLookupTypes.id and h.segmentStructureHdrDesc=kf.value and kf.name=:segmentStructureHdrDesc and h.businessGroupId=:businessGroupId and h.active = 1 "
					+ " and lv.lookupValue IN ( SELECT DISTINCT CASE WHEN LOCATE(:delimiter, SUBSTRING(categoryCode, LENGTH(:code)+1, LENGTH(categoryCode)))= 0 THEN SUBSTRING(categoryCode, LENGTH(:code)+1, LENGTH( categoryCode)) ELSE "
					+ " SUBSTRING(categoryCode,LENGTH(:code)+1, LOCATE(:delimiter, SUBSTRING(categoryCode, LENGTH(:code)+1, LENGTH(categoryCode)))-1) END AS final FROM OsiCategories WHERE businessGroupId = :businessGroupId and categoryCode like '%"+code+"%' and active=1"
					+ " ) order by lv.lookupValue";
			List<Object[]> strList1 = (List<Object[]>)this.entityManager.createQuery(query)
							  .setParameter("code", code)
							  .setParameter("delimiter", appConfig.getCategoryDelimiter())
							  .setParameter("segmentStructureHdrDesc", appConfig.getCategoryStructure())
							  .setParameter("segmentStructureDetailsSeq", seq)
							  .setParameter("businessGroupId", businessGroupId)
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
