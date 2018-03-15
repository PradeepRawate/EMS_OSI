package com.osi.fas.configuration.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.osi.fas.configuration.repository.custom.OsiConfigParametersRepositoryCustom;
import com.osi.fas.domain.OsiConfigParameters;
import com.osi.urm.exception.DataAccessException;

public class OsiConfigParametersRepositoryImpl implements OsiConfigParametersRepositoryCustom{

	@PersistenceContext
    private EntityManager entityManager;
	@Override
	public Integer updateConfigParameters(OsiConfigParameters osiConfigParameters) throws DataAccessException {
		Integer count = 0;
		try {
			String query = "update OsiConfigParameters set configValue = :configValue where configId = :configId";
			count = this.entityManager.createQuery(query)
							  .setParameter("configValue", osiConfigParameters.getConfigValue())
							  .setParameter("configId", osiConfigParameters.getConfigId())
							  .executeUpdate();
			if(count==0){
				throw new DataAccessException("ERR_1012", null);
			}
		}catch (DataAccessException e) {
			throw new DataAccessException(e.getErrorCode(), e.getMessage()); 
		}catch (Exception e) {
			throw new DataAccessException("ERR_1089", e.getMessage()); 
		}
		return count;
	}
	@Override
	public List<OsiConfigParameters> findConfigParametersByBusinessGroupIdAndName(String configName,
			Integer businessGroupId) throws DataAccessException {

		List<OsiConfigParameters> list = new ArrayList<>();
		try {
			StringBuilder queryString= new StringBuilder("SELECT ocp FROM OsiConfigParameters ocp WHERE businessGroupId= :businessGroupId AND ocp.configName LIKE CONCAT('%',:configName,'%')" );
			Query query=this.entityManager.createQuery(queryString.toString());
			query.setParameter("businessGroupId", businessGroupId);
			query.setParameter("configName", configName);
			list = query.getResultList();
		} catch (Exception e) {
			System.out.println("Excepton ===========:"+e.getMessage());
		}
		return list;
	}
	
	
	@Override
	public String getConfigParametersByName(String configName, Integer businessGroupId) throws DataAccessException {
		String configValue = "";
		try {
			String query = "select configValue as value FROM OsiConfigParameters WHERE businessGroupId = :businessGroupId and configName =:configName";
			List list = this.entityManager.createQuery(query)
							  .setParameter("configName", configName.toUpperCase())
							  .setParameter("businessGroupId", businessGroupId)
							  .getResultList();
			if(list!=null && list.get(0)!=null)
				configValue = list.get(0).toString();
		}catch (Exception e) {
			throw new DataAccessException("ERR_1089", e.getMessage()); 
			//e.printStackTrace();
		}
		return configValue;
	}
	

}
