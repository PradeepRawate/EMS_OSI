package com.osi.fas.configuration.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.osi.fas.configuration.repository.custom.OsiCurrencyRepositoryCustom;
import com.osi.fas.domain.OsiCurrency;
import com.osi.urm.exception.DataAccessException;

public class OsiCurrencyRepositoryImpl implements OsiCurrencyRepositoryCustom{

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<OsiCurrency> searchCurrency(String queryParam,
			Integer businessGroupId) throws DataAccessException {
		List<OsiCurrency> list = new ArrayList<OsiCurrency>();
		try{
			if(queryParam!=null){
				String query = "Select c from OsiCurrency c where c.businessGroupId = :businessGroupId"+queryParam;
				System.out.println("query "+query);
				@SuppressWarnings("unchecked")
				List<OsiCurrency> lists = (List<OsiCurrency>) this.entityManager.createQuery(query)
						  .setParameter("businessGroupId", businessGroupId)
						  .getResultList();
				
				for(OsiCurrency o : lists ){
					if (o != null)
						list.add((OsiCurrency) o);
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
