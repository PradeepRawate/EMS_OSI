package com.osi.fas.configuration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.osi.fas.domain.OsiInventoryOrg;
import com.osi.fas.domain.OsiLocation;
import com.osi.urm.exception.DataAccessException;


/**
 * Spring Data JPA repository for the OsiLocation entity.
 */
@SuppressWarnings("unused")
public interface OsiLocationRepository extends JpaRepository<OsiLocation,Long> {

}
