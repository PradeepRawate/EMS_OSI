package com.osi.fas.configuration.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.osi.fas.configuration.repository.custom.OsiCoaRepositoryCustom;
import com.osi.fas.domain.OsiCategories;
import com.osi.fas.domain.OsiCoa;
import com.osi.fas.domain.OsiInventoryOrg;
import com.osi.urm.domain.OsiLookupTypes;
import com.osi.urm.exception.DataAccessException;

/**
 * Spring Data JPA repository for the OsiCoa entity.
 */
public interface OsiCoaRepository extends JpaRepository<OsiCoa,Integer>, OsiCoaRepositoryCustom {
	
	/*@Query(" FROM OsiCoa WHERE businessGroupId = :businessGroupId order by updatedDate desc")
    List<OsiCoa> findCoaByBusinessGroupId(@Param("businessGroupId") Integer businessGroupId) throws DataAccessException;*/
	List<OsiCoa> findCoasByBusinessGroupIdOrderByUpdatedDateDesc(Integer businessGroupId,Pageable pageObject) throws DataAccessException;
	@Query(" FROM OsiCoa WHERE businessGroupId = :businessGroupId and coaId =:coaId")
    OsiCoa findCoaByBusinessGroupIdAndId(@Param("coaId") Integer coaId, @Param("businessGroupId") Integer businessGroupId) throws DataAccessException;
}


