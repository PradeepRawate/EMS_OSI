package com.osi.fas.configuration.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.osi.fas.configuration.repository.custom.OsiUomRepositoryCustom;
import com.osi.fas.domain.OsiUom;
import com.osi.urm.domain.OsiLookupTypes;
import com.osi.urm.exception.DataAccessException;

public class OsiUomRepositoryImpl implements OsiUomRepositoryCustom {
	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public OsiUom updateUom(OsiUom osiUom) throws DataAccessException {
		// TODO Auto-generated method stub
		try {
			/*String deleteUomConversionsQuery = "delete from OsiUomConversion uc where uc.osiUom=:osiUom and uc.businessGroupId = :businessGroupId";
			this.entityManager.createQuery(deleteUomConversionsQuery)
					  .setParameter("osiUom", osiUom)	
					  .setParameter("businessGroupId", osiUom.getBusinessGroupId())
			          .executeUpdate();
			*/
			String updateUom = "update OsiUom u set u.uomCode= :uomCode , u.uomName= :uomName , u.uomDescription= :uomDescription , u.active= :active , u.updatedBy = :updatedBy, u.updatedDate = :updatedDate where u.uomId = :uomId and u.businessGroupId = :businessGroupId";
			int count = this.entityManager.createQuery(updateUom)
					  .setParameter("uomCode", osiUom.getUomCode())
					  .setParameter("uomName", osiUom.getUomName())
					  .setParameter("uomDescription",osiUom.getUomDescription())
					  .setParameter("active",osiUom.getActive())
					  .setParameter("businessGroupId", osiUom.getBusinessGroupId())
					  .setParameter("updatedBy", osiUom.getUpdatedBy())
					  .setParameter("updatedDate", osiUom.getUpdatedDate())
					  .setParameter("uomId", osiUom.getUomId())
			          .executeUpdate();
			/*if(count>0){
				for(OsiUomConversion osiUomConversion: osiUom.getOsiUomConversions() ){
					this.entityManager.persist(osiUomConversion);
				}
			}*/
			
			/*String query = "update OsiMenus m set m.menuName = :menuName, m.description = :description, m.updatedBy = :updatedBy, m.updatedDate = :updatedDate where m.id = :id";
			int count = this.entityManager.createQuery(query)
							  .setParameter("menuName", osiMenus.getMenuName())
							  .setParameter("description", osiMenus.getDescription())
							  .setParameter("updatedBy", osiMenus.getUpdatedBy())
							  .setParameter("updatedDate", osiMenus.getUpdatedDate())
							  .setParameter("id", osiMenus.getId())
					          .executeUpdate();*/
			if(count==0){
				throw new DataAccessException("ERR_1024",  null); 
			}
		}catch (DataAccessException e) {
			throw new DataAccessException(e.getErrorCode() , e.getMessage()); 
			//e.printStackTrace();
		}catch (Exception e) {
			throw new DataAccessException("ERR_1024", e.getMessage()); 
			//e.printStackTrace();
		}

		return osiUom;
	}

	@Override
	public List<OsiUom> searchUom(String queryParam,
			Integer businessGroupId) throws DataAccessException {
		List<OsiUom> list = new ArrayList<OsiUom>();
		try{
			if(queryParam!=null){
				String query = "Select u from OsiUom u where u.businessGroupId = :businessGroupId "+queryParam+" ORDER BY u.updatedDate desc";
				//System.out.println("query "+query);
				@SuppressWarnings("unchecked")
				List<OsiUom> lists = (List<OsiUom>) this.entityManager.createQuery(query)
						  .setParameter("businessGroupId", businessGroupId)
						  .getResultList();
				
				for(OsiUom o : lists ){
					if (o != null)
						list.add((OsiUom) o);
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
}
