package com.osi.fas.configuration.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osi.fas.domain.OsiBusinessGroup;
import com.osi.fas.domain.OsiOperatingUnit;



/**
 * Spring Data JPA repository for the OsiOperatingUnit entity.
 */
public interface OsiOperatingUnitRepository extends JpaRepository<OsiOperatingUnit,Long> {
	
	Set<OsiOperatingUnit> findDistinctByOperatingUnitIdIn(List<Integer> operatingUnitIds);
	List<OsiOperatingUnit> findByOsiBusinessGroupAndDateFromLessThanEqualAndDateToGreaterThanEqualOrderByUpdatedDateDesc(OsiBusinessGroup businessGroupId,Date currentDate1,Date currentDate2);

}
