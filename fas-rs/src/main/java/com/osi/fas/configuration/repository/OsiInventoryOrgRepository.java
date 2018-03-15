package com.osi.fas.configuration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.osi.fas.configuration.repository.custom.OsiInventoryOrgRepositoryCustom;
import com.osi.fas.domain.OsiInvOrgSubInventory;
import com.osi.fas.domain.OsiInventoryOrg;
import com.osi.urm.exception.DataAccessException;



/**
 * Spring Data JPA repository for the OsiInventoryOrg entity.
 */
public interface OsiInventoryOrgRepository extends JpaRepository<OsiInventoryOrg,Integer>, OsiInventoryOrgRepositoryCustom {
	@Query(" FROM OsiInventoryOrg WHERE osiBusinessGroup.businessGroupId = :businessGroupId and active=1")
    List<OsiInventoryOrg> findInventoryOrgByBusinessGroup( @Param("businessGroupId") Integer businessGroupId) throws DataAccessException;
	
	@Query(" FROM OsiInventoryOrg WHERE osiBusinessGroup.businessGroupId = :businessGroupId and invOrgId= :invOrgId and active=1")
	OsiInventoryOrg findInventoryOrgsByBusinessGroupAndInvOrgId(@Param("businessGroupId") Integer businessGroupId,@Param("invOrgId") Integer invOrgId) throws DataAccessException;

	@Query(" FROM OsiInvOrgSubInventory WHERE osiBusinessGroup.businessGroupId = :businessGroupId and osiInventoryOrg.invOrgId= :invOrgId")
	List<OsiInvOrgSubInventory> findSubInventoryOrgsByInvOrgId(@Param("businessGroupId") Integer businessGroupId,@Param("invOrgId") Integer invOrgId) throws DataAccessException;

}
