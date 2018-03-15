package com.osi.fas.configuration.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.osi.fas.configuration.repository.custom.OsiCategoriesRepositoryCustom;
import com.osi.fas.domain.OsiCategories;
import com.osi.fas.domain.OsiPrReqHeaderProjection;
import com.osi.fas.service.dto.OsiCategoriesDTO;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.DataAccessException;



/**
 * Spring Data JPA repository for the OsiCategories entity.
 */
public interface OsiCategoriesRepository extends JpaRepository<OsiCategories,Integer>, OsiCategoriesRepositoryCustom {
	
	@Query(" FROM OsiCategories WHERE businessGroupId = :businessGroupId ORDER BY updatedDate DESC")
    List<OsiCategories> findCategoriesByBusinessGroupId(@Param("businessGroupId") Integer businessGroupId,Pageable pageObject) throws DataAccessException;
	@Query(" FROM OsiCategories WHERE businessGroupId = :businessGroupId ORDER BY updatedDate DESC")
    List<OsiCategories> findCategoriesByBusinessGroupId(@Param("businessGroupId") Integer businessGroupId) throws DataAccessException;
	@Query(" FROM OsiCategories WHERE businessGroupId = :businessGroupId AND ACTIVE = 1 ORDER BY categoryName ASC")
	List<OsiCategories> findActiveCategoriesByBusinessGroupId(@Param("businessGroupId") Integer businessGroupId) throws DataAccessException;
	@Query(" FROM OsiCategories WHERE businessGroupId = :businessGroupId and categoryId =:categoryId  and active=1")
    OsiCategories findCategoriesByBusinessGroupIdAndId(@Param("categoryId") Integer categoryId, @Param("businessGroupId") Integer businessGroupId) throws DataAccessException;
	@Query("select count(*) from OsiItem i where i.businessGroupId= :businessGroupId AND i.active = 1 AND i.osiCategories.categoryId = :categoryId")
	Integer isCategoryInactivableItem(@Param("categoryId") Integer id,@Param("businessGroupId") Integer businessGroupId)  throws DataAccessException;
	@Query("select count(*) from OsiPrReqLines l, OsiPrReqHeader h where l.osiPrReqHeader.prReqHdrId = h.prReqHdrId AND l.businessGroupId= :businessGroupId AND h.businessGroupId= :businessGroupId AND l.categoryId = :categoryId AND h.docStatus!='CANCELLED' AND h.docStatus!='CLOSED' ")
	Integer isCategoryInactivablePR(@Param("categoryId") Integer id,@Param("businessGroupId") Integer businessGroupId)  throws DataAccessException;
	@Query("select count(*) from OsiItem i, OsiInvOrgItems oi where i.itemId=oi.osiItem.itemId and oi.businessGroupId= :businessGroupId and oi.coaId=:coaId and i.osiCategories.categoryId=:categoryId")
	public Integer isCoaUpdatable(@Param("coaId")Integer coaId,@Param("categoryId") Integer categoryId,@Param("businessGroupId")Integer businessGroupId) throws DataAccessException;
}
