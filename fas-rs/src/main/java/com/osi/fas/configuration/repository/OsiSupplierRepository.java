package com.osi.fas.configuration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osi.fas.configuration.repository.custom.OsiSupplierRepositoryCustom;
import com.osi.fas.domain.OsiSupplier;



/**
 * Spring Data JPA repository for the OsiInventoryOrg entity.
 */
public interface OsiSupplierRepository extends JpaRepository<OsiSupplier,Integer>, OsiSupplierRepositoryCustom {

}
