package com.osi.fas.configuration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.osi.fas.domain.OsiBranches;

public interface OsiBranchesRepository extends JpaRepository<OsiBranches, Integer> {
	
	@Query("FROM OsiBranches b WHERE branchId = :branchId")
	public OsiBranches getBranchById(@Param("branchId") Integer branchId);

}
