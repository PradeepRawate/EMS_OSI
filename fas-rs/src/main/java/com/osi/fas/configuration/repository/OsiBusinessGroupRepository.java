package com.osi.fas.configuration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.osi.fas.configuration.repository.custom.OsiBusinessGroupRepositoryCustom;
import com.osi.fas.domain.OsiBusinessGroup;
import com.osi.fas.domain.OsiBusinessUnit;
import com.osi.fas.domain.OsiInvOrgSubInventory;
import com.osi.fas.domain.OsiInventoryOrg;
import com.osi.fas.domain.OsiLegalEntities;
import com.osi.fas.domain.OsiLocation;
import com.osi.fas.domain.OsiOperatingUnit;

/**
 * Spring Data JPA repository for the OsiBusinessGroup entity.
 */
public interface OsiBusinessGroupRepository extends JpaRepository<OsiBusinessGroup,Integer>, OsiBusinessGroupRepositoryCustom  {
	
	@Query(" FROM OsiBusinessGroup b where b.businessGroupId = :businessGroupId ") // and b.dateFrom <=:today and b.dateTo>=:today
    public List<OsiBusinessGroup> findAllBusinessGroups(@Param("businessGroupId") Integer businessGroupId); //@Param("today") Date today, 

	@Query("Select o FROM OsiOperatingUnit o where o.osiBusinessGroup.businessGroupId = :businessGroupId and o.operatingUnitId= :operatingUnitId")
	public OsiOperatingUnit findOperatingUnit(@Param("operatingUnitId") Integer operatingUnitId,@Param("businessGroupId") Integer businessGroupId);

	@Query("Select le FROM OsiLegalEntities le where le.osiBusinessGroup.businessGroupId = :businessGroupId and le.legalEntityId= :legalEntityId")
	public OsiLegalEntities findLegalEntity(@Param("legalEntityId") Integer legalEntityId,@Param("businessGroupId") Integer businessGroupId);

	@Query("Select io FROM OsiInventoryOrg io where io.osiBusinessGroup.businessGroupId = :businessGroupId and io.invOrgId= :invOrgId")
	public OsiInventoryOrg findInventoryOrg(@Param("invOrgId")Integer invOrgId, @Param("businessGroupId") Integer businessGroupId);

	@Query("Select si FROM OsiInvOrgSubInventory si where si.osiBusinessGroup.businessGroupId = :businessGroupId and si.subInvId= :subInvId")
	public OsiInvOrgSubInventory findSubInventory(@Param("subInvId") Integer subInvId,@Param("businessGroupId") Integer businessGroupId);
	
	@Query("Select bu FROM OsiBusinessUnit bu where bu.osiBusinessGroup.businessGroupId = :businessGroupId and bu.buId= :buId")
	public OsiBusinessUnit findBusinessUnit(@Param("buId") Integer buId,@Param("businessGroupId") Integer businessGroupId);
	
	@Query("Select loc FROM OsiLocation loc where loc.locationId = :locationId")
	public OsiLocation findLocation(@Param("locationId") Integer locationId);

}

