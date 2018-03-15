package com.osi.fas.configuration.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.osi.fas.configuration.repository.custom.OsiItemRepositoryCustom;
import com.osi.fas.domain.OsiInvOrgItems;
import com.osi.fas.domain.OsiItem;
import com.osi.fas.service.dto.OsiItemProjection;
import com.osi.urm.exception.DataAccessException;


/**
 * Spring Data JPA repository for the OsiItem entity.
 */
public interface OsiItemRepository extends JpaRepository<OsiItem,Integer>, OsiItemRepositoryCustom {
	List<OsiItem> findItemsByBusinessGroupIdOrderByUpdatedDateDesc(Integer businessGroupId,Pageable pageObject) throws DataAccessException;
	List<OsiItemProjection> findItemsByBusinessGroupIdOrderByUpdatedDateDesc(Integer businessGroupId,Pageable pageObject,Class<OsiItemProjection> class1) throws DataAccessException;
	@Query(" FROM OsiItem WHERE businessGroupId = :businessGroupId ORDER BY createdDate asc")
    List<OsiItem> findItemsByBusinessGroupId(@Param("businessGroupId") Integer businessGroupId) throws DataAccessException;
	@Query(" FROM OsiItem WHERE businessGroupId = :businessGroupId and itemId =:itemId")
	OsiItem findItemsByBusinessGroupIdAndId(@Param("itemId") Integer categoryId, @Param("businessGroupId") Integer businessGroupId) throws DataAccessException;
	List<OsiItem> findOsiItemByItemIdNotIn(List<Integer> itemIdList);
         
    @Query(" FROM OsiInvOrgItems  c WHERE  c.osiItem.itemId =:itemId order by c.orgid desc") 
    List<OsiInvOrgItems> getOsiItemInvOrgs(@Param("itemId") Integer itemId);
    
    @Query(value = "select * from osi_inv_org_items_v where operating_unit_id =:operatingUnitId and item_id =:itemId", nativeQuery=true) 
     List<Object[]> selectedOsiItemInvOrgs(@Param("itemId") Integer itemId,@Param("operatingUnitId") Integer operatingUnitId) throws DataAccessException;
     
  
	
	
	
}
