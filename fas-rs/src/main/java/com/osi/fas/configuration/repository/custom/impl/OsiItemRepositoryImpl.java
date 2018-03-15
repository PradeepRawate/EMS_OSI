package com.osi.fas.configuration.repository.custom.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.osi.fas.configuration.repository.custom.OsiItemRepositoryCustom;
import com.osi.fas.domain.OsiInvOrgItems;
import com.osi.fas.domain.OsiInventoryOrg;
import com.osi.fas.domain.OsiItem;
import com.osi.fas.domain.OsiItemApplicableTax;
import com.osi.fas.domain.OsiItemImage;
import com.osi.fas.domain.OsiUomConversion;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.DataAccessException;

public class OsiItemRepositoryImpl implements OsiItemRepositoryCustom{
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	private AppConfig appConfig;
	@Override
	public void updateItem(OsiItem osiItem, List<Integer> invOrgIds) throws DataAccessException {
		try {
			String itemUpdateQuery = "update OsiItem set itemCode=:itemCode, itemName=:itemName, itemDescription=:itemDescription, itemLongDescription=:itemLongDescription, primaryUomId=:primaryUomId, active=:active, osiCategories.categoryId=:categoryId, categoryCode=:categoryCode, isStockable=:isStockable, isPhysicalCountEnabled=:isPhysicalCountEnabled, isUsedApprovedSupplier=:isUsedApprovedSupplier, isMinMaxPlanningEnabled=:isMinMaxPlanningEnabled, leadTimeInDays=:leadTimeInDays,  minQuantity=:minQuantity, maxQuantity=:maxQuantity, orderMultiplies=:orderMultiplies, updatedBy = :updatedBy, updatedDate = :updatedDate,listPrice=:listPrice,marketPrice=:marketPrice where businessGroupId = :businessGroupId and itemId =:itemId";
			String itemImageDeleteQuery = "delete from OsiItemImage where osiItem.itemId=:itemId";
			//String itemInvOrgItemsQuery = "delete from OsiInvOrgItems where osiItem.itemId=:itemId";
			String itemInvOrgItemPricesQuery = "delete from OsiInvOrgItemPrice where osiItem.itemId=:itemId";
			//String itemApprovedSuppliersesQuery = "delete from OsiApprovedSuppliers where osiItem.itemId=:itemId";
			String itemUomConversionQuery = "delete from OsiUomConversion where osiItem.itemId=:itemId";
			//String itemAvailableTaxQuery = "delete from OsiItemApplicableTax where osiItem.itemId=:itemId";
			this.entityManager.createQuery(itemImageDeleteQuery).setParameter("itemId", osiItem.getItemId()).executeUpdate();
			//this.entityManager.createQuery(itemInvOrgItemsQuery).setParameter("itemId", osiItem.getItemId()).executeUpdate();
			this.entityManager.createQuery(itemInvOrgItemPricesQuery).setParameter("itemId", osiItem.getItemId()).executeUpdate();
			//this.entityManager.createQuery(itemApprovedSuppliersesQuery).setParameter("itemId", osiItem.getItemId()).executeUpdate();
			this.entityManager.createQuery(itemUomConversionQuery).setParameter("itemId", osiItem.getItemId()).executeUpdate();
			//this.entityManager.createQuery(itemAvailableTaxQuery).setParameter("itemId", osiItem.getItemId()).executeUpdate();
			//if (osiItem.getOperatingUnitId()!=null){
				
//				String itemInvOrgItemsQuery = "delete from OsiInvOrgItems where osiItem.itemId=:itemId and osiInventoryOrg.invOrgId in (select invOrgId from OsiInventoryOrg where osiOperatingUnit.operatingUnitId =:operatingUnitId )";
			if(invOrgIds != null && invOrgIds.size() > 0) {
				String itemInvOrgItemsQuery = "delete from OsiInvOrgItems where osiItem.itemId=:itemId and osiInventoryOrg.invOrgId in (:invOrgIds)";	
				this.entityManager.createQuery(itemInvOrgItemsQuery).setParameter("itemId", osiItem.getItemId())
				.setParameter("invOrgIds",invOrgIds)
				.executeUpdate();
			}
			this.entityManager.createQuery(itemUpdateQuery)
							  .setParameter("itemCode", osiItem.getItemCode())
							  .setParameter("itemName", osiItem.getItemName())
							  .setParameter("itemDescription", osiItem.getItemDescription())
							  .setParameter("itemLongDescription", osiItem.getItemLongDescription())
							  .setParameter("primaryUomId", osiItem.getPrimaryUomId())
							  .setParameter("active", osiItem.getActive())
							  .setParameter("categoryId", osiItem.getOsiCategories().getCategoryId())
							  .setParameter("categoryCode", osiItem.getOsiCategories().getCategoryCode())
							  .setParameter("isStockable", osiItem.getIsStockable())
							  .setParameter("isPhysicalCountEnabled", osiItem.getIsPhysicalCountEnabled())
							  .setParameter("isUsedApprovedSupplier", osiItem.getIsUsedApprovedSupplier())
							  .setParameter("isMinMaxPlanningEnabled", osiItem.getIsMinMaxPlanningEnabled())
							  .setParameter("leadTimeInDays", osiItem.getLeadTimeInDays())
							  .setParameter("minQuantity", osiItem.getMinQuantity())
							  .setParameter("maxQuantity", osiItem.getMaxQuantity())
							  .setParameter("orderMultiplies", osiItem.getOrderMultiplies())
							  .setParameter("updatedBy", osiItem.getUpdatedBy())
							  .setParameter("updatedDate", new Date())
							  .setParameter("businessGroupId", osiItem.getBusinessGroupId())
							  .setParameter("listPrice", osiItem.getListPrice())
							  .setParameter("marketPrice",osiItem.getMarketPrice())
							  .setParameter("itemId", osiItem.getItemId())
							  .executeUpdate();
			for (OsiItemImage osiItemImages : osiItem.getOsiItemImages()) {
					this.entityManager.persist(osiItemImages);
			}
			for (OsiInvOrgItems osiInvOrgItems : osiItem.getOsiInvOrgItemses()) {
					this.entityManager.persist(osiInvOrgItems);
			}
			/*for (OsiInvOrgItemPrice osiInvOrgItemPrice : osiItem.getOsiInvOrgItemPrices()) {
					this.entityManager.persist(osiInvOrgItemPrice);
			}*/
			/*for (OsiApprovedSuppliers osiApprovedSuppliers : osiItem.getOsiApprovedSupplierses()) {
					this.entityManager.persist(osiApprovedSuppliers);
			}*/
			for (OsiUomConversion osiUomConversion : osiItem.getOsiUomConversions()) {
					this.entityManager.persist(osiUomConversion);
			}
			/*for (OsiItemApplicableTax osiItemApplicableTax : osiItem.getOsiItemApplicableTaxes()) {
					this.entityManager.persist(osiItemApplicableTax);
			}*/
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
	}
	
	@Override
	public Integer deleteItems(List<Integer> itemIds, Integer businessGroupId, Integer userId) throws DataAccessException {
		Integer count = 0;
		try {
			String query = "update OsiItem set active = 0, updatedBy = :updatedBy, updatedDate = :updatedDate where businessGroupId = :businessGroupId and itemId IN (:itemId)";
			count = this.entityManager.createQuery(query)
							  .setParameter("updatedBy", userId)
							  .setParameter("updatedDate", new Date())
							  .setParameter("businessGroupId", businessGroupId)
							  .setParameter("itemId", itemIds)
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
	public Integer findItemsByCodeId(Integer itemId, String itemCode, Integer businessGroupId)
			throws DataAccessException {
		Integer count = 0;
		try {
			String query = "select count(*) as cnt  FROM OsiItem WHERE businessGroupId = :businessGroupId and itemCode =:itemCode and itemId !=:itemId and active=1";
			List list = this.entityManager.createQuery(query)
							  .setParameter("itemId", itemId)
							  .setParameter("itemCode", itemCode)
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
	public Integer findItemsByCode(String itemCode, Integer businessGroupId) throws DataAccessException {
		Integer count = 0;
		try {
			String query = "select count(*) as cnt FROM OsiItem WHERE businessGroupId = :businessGroupId and itemCode =:itemCode and active=1";
			List list = this.entityManager.createQuery(query)
							  .setParameter("itemCode", itemCode)
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
	public Integer findItemsByNameId(Integer itemId, String itemName, Integer businessGroupId)
			throws DataAccessException {
		Integer count = 0;
		try {
			String query = "select count(*) as cnt  FROM OsiItem WHERE businessGroupId = :businessGroupId and upper(itemName) =:itemName and itemId !=:itemId";
			List list = this.entityManager.createQuery(query)
							  .setParameter("itemId", itemId)
							  .setParameter("itemName", itemName.toUpperCase())
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
	public Integer findItemsByName(String itemName, Integer businessGroupId) throws DataAccessException {
		Integer count = 0;
		try {
			String query = "select count(*) as cnt FROM OsiItem WHERE businessGroupId = :businessGroupId and upper(itemName) =:itemName";
			List list = this.entityManager.createQuery(query)
							  .setParameter("itemName", itemName.toUpperCase())
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

	/*@SuppressWarnings("unchecked")
	@Override
	public List<OsiItem> getItemsBySearchCriteria(OsiItem osiItem) throws DataAccessException {
		List<OsiItem> osiItemList = null;
		List<OsiItem> osiItemTopTenList = null;
		
		try {
			String queryParam = "";
			if(osiItem.getInventoryOrgCode()!=null && !osiItem.getInventoryOrgCode().equals("")){
				queryParam = " and upper(io.invOrgCode) like '%"+osiItem.getInventoryOrgCode()+"%' ";
			}
			String query = " SELECT coalesce(io.invOrgName,'') as invOrgName, coalesce(io.invOrgCode,'') as invOrgCode, i.itemId, i.itemName, i.itemCode, i.categoryCode, i.active FROM OsiItem i left join i.osiInvOrgItemses ii left join ii.osiInventoryOrg io WHERE i.businessGroupId = :businessGroupId and upper(i.itemCode) like '%"+osiItem.getItemCode()+"%' and upper(i.itemName) like '%"+osiItem.getItemName()+"%'  and upper(i.categoryCode) like '%"+osiItem.getCategoryCode()+"%' "+queryParam+" order by i.updatedDate,i.itemCode, io.invOrgCode";
				List<Object[]> objList = (List<Object[]>)this.entityManager.createQuery(query)
							  .setParameter("businessGroupId", osiItem.getBusinessGroupId())
							  .getResultList();
			osiItemList = new ArrayList<OsiItem>();
			String previousCode = "";
			String invOrgCode = "";
			OsiItem osiItem1 = null;
			for (Object[] objects : objList) {
				if(objects[1]!=null && !objects[3].toString().equals(previousCode)){
					osiItem1 = new OsiItem();
					invOrgCode = objects[1].toString();
					osiItem1.setInventoryOrgName(objects[0].toString());
					osiItem1.setInventoryOrgCode(objects[1].toString());
					osiItem1.setItemId(Integer.parseInt(objects[2].toString()));
					osiItem1.setItemCode(objects[4].toString());
					osiItem1.setItemName(objects[3].toString());
					osiItem1.setCategoryCode(objects[5].toString());
					osiItem1.setActive(Integer.parseInt(objects[6].toString()));
					osiItemList.add(osiItem1);
				}else{
					invOrgCode = invOrgCode+","+objects[1].toString();
					osiItem1.setInventoryOrgCode(invOrgCode);
				}
				previousCode =objects[3].toString();
				
			}
			if(queryParam.equals("") && osiItemList.size()>10){
				osiItemTopTenList=new ArrayList<OsiItem>();
				for(int i=0;i<10; i++){
					osiItemTopTenList.add(osiItemList.get(i));
				}
			}
			
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return osiItemList;
	}*/
	
	public List<OsiItem> getItemsBySearchCriteria(OsiItem osiItem) throws DataAccessException {
		List<OsiItem> osiItemList = null;
		List<OsiItem> osiItemTopTenList = null;
		
		try {
			String queryParam = "";
			if(osiItem.getInventoryOrgCode()!=null && !osiItem.getInventoryOrgCode().equals("")){
				queryParam = " and upper(io.invOrgCode) like '%"+osiItem.getInventoryOrgCode()+"%' ";
			}
			String query = " SELECT  i.itemId, i.itemName, i.itemCode, i.categoryCode, i.active FROM OsiItem i WHERE i.businessGroupId = :businessGroupId and upper(i.itemCode) like '%"+osiItem.getItemCode()+"%' and upper(i.itemName) like '%"+osiItem.getItemName()+"%'  and upper(i.categoryCode) like '%"+osiItem.getCategoryCode()+"%' "+queryParam+" order by i.updatedDate,i.itemCode";
				List<Object[]> objList = (List<Object[]>)this.entityManager.createQuery(query)
							  .setParameter("businessGroupId", osiItem.getBusinessGroupId())
							  .getResultList();
			osiItemList = new ArrayList<OsiItem>();
			OsiItem osiItem1 = null;
			for (Object[] objects : objList) {
					osiItem1 = new OsiItem();
					osiItem1.setItemId((Integer)objects[0]);
					osiItem1.setItemCode(objects[2].toString());
					osiItem1.setItemName(objects[1].toString());
					osiItem1.setCategoryCode(objects[3].toString());
					osiItem1.setActive(Integer.parseInt(objects[4].toString()));
					osiItemList.add(osiItem1);	
			}
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return osiItemList;
	}
	public String generateItemCode(Integer businessGroupId) throws DataAccessException {
		String itemCode = null;
		try {
				StoredProcedureQuery query1= this.entityManager.
						createStoredProcedureQuery("getNewItemNumber")
						.registerStoredProcedureParameter("nextval", Integer.class, ParameterMode.OUT);
				query1.execute();
				Integer seqNumber = (Integer) query1.getOutputParameterValue("nextval");
			//String query = "SELECT (count(*)+1) as itemCode FROM OsiItem WHERE businessGroupId = :businessGroupId";
		/*	Object object = (Object)this.entityManager.createQuery(query)
							  .setParameter("businessGroupId", businessGroupId)
							  .getSingleResult();*/
			if(seqNumber!=null){
				itemCode = StringUtils.leftPad(seqNumber.toString(), Integer.parseInt(appConfig.getNoOfDigitsforItemCode()), "0");
			}
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}
		return itemCode;
	}
	
	@Override
	public List<OsiUomConversion> getUomconversionsByItem(Integer itemId, Integer businessGroupId) throws DataAccessException {
		List<OsiUomConversion> osiUomConversionSet = null;
		try {
			String query = "SELECT um, u.uomCode FROM OsiUomConversion um, OsiUom u WHERE u.uomId = um.targetUomId and um.businessGroupId = :businessGroupId and um.osiItem.itemId =:itemId";
			@SuppressWarnings("unchecked")
			List<Object[]> object = (List<Object[]>)this.entityManager.createQuery(query)
							  .setParameter("itemId", itemId)
							  .setParameter("businessGroupId", businessGroupId)
					          .getResultList();
			osiUomConversionSet = new ArrayList<OsiUomConversion>();
			for (Object[] objects : object) {
				OsiUomConversion osiUomConversion = new OsiUomConversion();
				osiUomConversion = (OsiUomConversion)objects[0];
				osiUomConversion.setTargetUomCode(objects[1].toString());
				osiUomConversionSet.add(osiUomConversion);
			}
			
		}catch (NoResultException e) {
			throw new DataAccessException("ERR_1002", e.getMessage()); 
			//e.printStackTrace();
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return osiUomConversionSet;
	}
	
	public Integer isStockableItem(Integer itemId, Integer businessGroupId) throws DataAccessException{
		Integer isStokable = 0;
		try {
			String query = "FROM OsiItem WHERE businessGroupId = :businessGroupId and itemId=:itemId";
			OsiItem object = (OsiItem)this.entityManager.createQuery(query)
							  .setParameter("businessGroupId", businessGroupId)
							  .setParameter("itemId", itemId)
							  .getSingleResult();
			if(object!=null){
				isStokable = object.getIsStockable();
			}
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}
		return isStokable;
	}

	@Override
	public List<OsiInventoryOrg> getAllOsiInvOrgItems(Integer itemId, Integer operatingUnitId,Integer businessGroupId)
			throws DataAccessException {
		// TODO Auto-generated method stub
		List<OsiInventoryOrg> listInvOrgList=new ArrayList<>();
		
		try {
			String query = "select  invorg.invOrgCode,invorg.invOrgName, invorg.invOrgType,invorg.invOrgId  from OsiInventoryOrg invorg , OsiInvOrgItems invItem  "
					+     " where invorg.invOrgId=invItem.osiInventoryOrg.invOrgId and invItem.businessGroupId=:businessGroupId and invItem.osiItem.itemId =:itemId  and invorg.osiOperatingUnit.operatingUnitId=:operatingUnitId";
			
            
			List<Object[]> listInvOrgList1= this.entityManager.createQuery(query)
					          .setParameter("businessGroupId", businessGroupId)
							  .setParameter("itemId", itemId)
							  .setParameter("operatingUnitId", operatingUnitId)
							  .getResultList();
			for(Object[]objectList :listInvOrgList1)
			{
				OsiInventoryOrg osiInventoryOrg=new OsiInventoryOrg();
				osiInventoryOrg.setInvOrgCode(objectList[0].toString());
				osiInventoryOrg.setInvOrgName(objectList[1].toString());
				osiInventoryOrg.setInvOrgType(Integer.parseInt(objectList[2].toString()));
				osiInventoryOrg.setInvOrgId(Integer.parseInt(objectList[3].toString()));
				listInvOrgList.add(osiInventoryOrg);
			}
			
		
			
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}
		return listInvOrgList;
	}
}
