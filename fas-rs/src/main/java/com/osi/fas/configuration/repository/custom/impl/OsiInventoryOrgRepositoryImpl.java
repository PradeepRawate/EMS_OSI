package com.osi.fas.configuration.repository.custom.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.osi.fas.configuration.repository.custom.OsiInventoryOrgRepositoryCustom;
import com.osi.fas.domain.OsiBusinessGroup;
import com.osi.fas.domain.OsiInventoryOrg;
import com.osi.fas.domain.OsiLocation;
import com.osi.fas.domain.OsiOperatingUnit;
import com.osi.urm.exception.DataAccessException;

public class OsiInventoryOrgRepositoryImpl implements OsiInventoryOrgRepositoryCustom {
	@PersistenceContext
    private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OsiInventoryOrg> getAllInventoryOrgs(String searchString, Integer businessGroupId)
			throws DataAccessException {
		List<OsiInventoryOrg> osiInventoryOrgList = null;
		try {
			if(searchString==null){
				searchString = "";
			}
			String inventoryOrg = "SELECT io.invOrgId, io.invOrgCode, io.invOrgName, io.isMaster,io.invOrgType FROM OsiInventoryOrg io WHERE (upper(io.invOrgName) like '%"+searchString.toUpperCase()+"%' or io.invOrgCode like '%"+searchString.toUpperCase()+"%') AND io.osiBusinessGroup.businessGroupId = :businessGroupId and io.active=1 order by io.invOrgCode asc";
			List<Object[]> objList = (List<Object[]>)this.entityManager.createQuery(inventoryOrg)
					  .setParameter("businessGroupId", businessGroupId)
			          .getResultList();
			osiInventoryOrgList = new ArrayList<OsiInventoryOrg>();
			for (Object[] objects : objList) {
				OsiInventoryOrg osiInventoryOrg = new OsiInventoryOrg(); 
				osiInventoryOrg.setInvOrgId(Integer.parseInt(objects[0].toString()));
				osiInventoryOrg.setInvOrgCode(objects[1].toString());
				osiInventoryOrg.setInvOrgName(objects[2].toString());
				if(objects[3]!=null)
					osiInventoryOrg.setIsMaster(Integer.parseInt(objects[3].toString()));
				else
					osiInventoryOrg.setIsMaster(0);
				osiInventoryOrg.setInvOrgType(Integer.parseInt(objects[4].toString()));
				osiInventoryOrgList.add(osiInventoryOrg);
				
			}
			
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return osiInventoryOrgList;
	}

	public OsiInventoryOrg getMasterOrganization(Integer businessGroupId)
			throws DataAccessException {
		OsiInventoryOrg masterOrg = null;
		try {
			String inventoryOrg = " FROM OsiInventoryOrg io WHERE io.isMaster=1 AND io.osiBusinessGroup.businessGroupId = :businessGroupId and io.active=1";
			masterOrg= (OsiInventoryOrg)this.entityManager.createQuery(inventoryOrg)
					  .setParameter("businessGroupId", businessGroupId)
			          .getSingleResult();
		}catch (NoResultException e) {
			throw new DataAccessException("ERR_1021", e.getMessage()); 
			//e.printStackTrace();
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return masterOrg;
	}

	@Override
	public List<OsiInventoryOrg> getAllActiveInventoryOrgs(Integer businessGroupId) throws DataAccessException {
		List<OsiInventoryOrg> osiActiveInventoryOrgList = new ArrayList<>();
		try {
			
			String activeInventoryOrgString = "Select oig.invOrgId, oig.invOrgCode, oig.invOrgName, oig.isMaster,oig.invOrgType,oig.osiBusinessGroup.businessGroupId,oig.osiOperatingUnit.operatingUnitId,oig.osiLocation.locationId from OsiInventoryOrg oig, OsiOperatingUnit oou,"
					+ "OsiLegalEntities ole, OsiBusinessUnit obu where oig.osiOperatingUnit.operatingUnitId = oou.operatingUnitId  and "
					+ "oou.osiLegalEntities.legalEntityId=ole.legalEntityId and ole.osiBusinessUnit.buId=obu.buId and obu.active =1 and ole.active =1 "
					+ "and oou.dateFrom <= :currentDate and oou.dateTo >= :currentDate and oig.active = 1 and oig.osiBusinessGroup.businessGroupId=:businessGroupId";
			List<Object[]> objList = (List<Object[]>)this.entityManager.createQuery(activeInventoryOrgString)
					  .setParameter("businessGroupId", businessGroupId)
					  .setParameter("currentDate", new Date())
			          .getResultList();
			
			for (Object[] objects : objList) {
				OsiInventoryOrg osiInventoryOrg = new OsiInventoryOrg(); 
				osiInventoryOrg.setInvOrgId(Integer.parseInt(objects[0].toString()));
				osiInventoryOrg.setInvOrgCode(objects[1].toString());
				osiInventoryOrg.setInvOrgName(objects[2].toString());
				if(objects[3]!=null)
					osiInventoryOrg.setIsMaster(Integer.parseInt(objects[3].toString()));
				else
					osiInventoryOrg.setIsMaster(0);
				osiInventoryOrg.setInvOrgType(Integer.parseInt(objects[4].toString()));
				OsiBusinessGroup osiBusinessGroup=new OsiBusinessGroup();
				osiBusinessGroup.setBusinessGroupId(Integer.parseInt(objects[5].toString()));
				osiInventoryOrg.setOsiBusinessGroup(osiBusinessGroup);
				OsiOperatingUnit osiOperatingUnit=new OsiOperatingUnit();
				osiOperatingUnit.setOperatingUnitId(Integer.parseInt(objects[6].toString()));
				osiInventoryOrg.setOsiOperatingUnit(osiOperatingUnit);
				OsiLocation osiLocation=new OsiLocation();
				osiLocation.setLocationId(Integer.parseInt(objects[7].toString()));
				osiInventoryOrg.setOsiLocation(osiLocation);
				osiActiveInventoryOrgList.add(osiInventoryOrg);
				
			}
			
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return osiActiveInventoryOrgList;
	}

    @Override
    public List<OsiInventoryOrg> getAllInventoryOrgsExclusions(String searchString, String invOrgId, Integer businessGroupId) throws DataAccessException {
    
		List<OsiInventoryOrg> osiInventoryOrgList = null;
		try {
			if(searchString==null){
				searchString = "";
			}
			String inventoryOrg = "SELECT io.invOrgId, io.invOrgCode, io.invOrgName, io.isMaster,io.invOrgType FROM OsiInventoryOrg io WHERE (upper(io.invOrgName) like '%"+searchString.toUpperCase()+"%' or io.invOrgCode like '%"+searchString.toUpperCase()+"%') AND io.osiBusinessGroup.businessGroupId = :businessGroupId and io.active=1 and io.invOrgId not in("+invOrgId+") order by io.invOrgCode asc";
			List<Object[]> objList = (List<Object[]>)this.entityManager.createQuery(inventoryOrg)
					  .setParameter("businessGroupId", businessGroupId)
                                         
			          .getResultList();
			osiInventoryOrgList = new ArrayList<OsiInventoryOrg>();
			for (Object[] objects : objList) {
				OsiInventoryOrg osiInventoryOrg = new OsiInventoryOrg(); 
				osiInventoryOrg.setInvOrgId(Integer.parseInt(objects[0].toString()));
				osiInventoryOrg.setInvOrgCode(objects[1].toString());
				osiInventoryOrg.setInvOrgName(objects[2].toString());
				if(objects[3]!=null)
					osiInventoryOrg.setIsMaster(Integer.parseInt(objects[3].toString()));
				else
					osiInventoryOrg.setIsMaster(0);
				osiInventoryOrg.setInvOrgType(Integer.parseInt(objects[4].toString()));
				osiInventoryOrgList.add(osiInventoryOrg);
				
			}
			
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return osiInventoryOrgList;
    }
}
