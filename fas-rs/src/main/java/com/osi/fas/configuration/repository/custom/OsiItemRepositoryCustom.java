package com.osi.fas.configuration.repository.custom;

import java.util.List;
import java.util.Set;

import com.osi.fas.domain.OsiInvOrgItems;
import com.osi.fas.domain.OsiInventoryOrg;
import com.osi.fas.domain.OsiItem;
import com.osi.fas.domain.OsiUomConversion;
import com.osi.urm.exception.DataAccessException;

public interface OsiItemRepositoryCustom{
	
	public void updateItem(OsiItem osiItem, List<Integer> invOrgIds) throws DataAccessException;
	public Integer deleteItems(List<Integer> itemIds, Integer businessGroupId, Integer userId) throws DataAccessException;
	public Integer findItemsByCodeId(Integer itemId,String itemCode, Integer businessGroupId) throws DataAccessException;
	public Integer findItemsByCode(String itemCode,  Integer businessGroupId) throws DataAccessException;
	public Integer findItemsByNameId(Integer itemId,String itemName, Integer businessGroupId) throws DataAccessException;
	public Integer findItemsByName(String itemName,  Integer businessGroupId) throws DataAccessException;
	List<OsiItem> getItemsBySearchCriteria(OsiItem osiItem) throws DataAccessException;
	public String generateItemCode(Integer businessGroupId) throws DataAccessException;
	public List<OsiUomConversion> getUomconversionsByItem(Integer itemId, Integer businessGroupId) throws DataAccessException;
	public Integer isStockableItem(Integer itemId, Integer businessGroupId) throws DataAccessException;
	public List<OsiInventoryOrg> getAllOsiInvOrgItems(Integer itemId,Integer operatingUnitId,Integer businessGroupId) throws DataAccessException;
}
