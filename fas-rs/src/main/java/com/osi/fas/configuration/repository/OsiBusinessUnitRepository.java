
package com.osi.fas.configuration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osi.fas.domain.OsiBusinessUnit;



/**
 * Spring Data JPA repository for the OsiLegalEntities entity.
 */
@SuppressWarnings("unused")
public interface OsiBusinessUnitRepository extends JpaRepository<OsiBusinessUnit,Long> {

}
