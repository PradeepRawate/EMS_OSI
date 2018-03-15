package com.osi.fas.configuration.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.osi.fas.configuration.repository.custom.OsiConfigParametersRepositoryCustom;
import com.osi.fas.domain.OsiConfigParameters;
import com.osi.urm.exception.DataAccessException;

public interface OsiConfigParametersRepository extends JpaRepository<OsiConfigParameters,Integer>, OsiConfigParametersRepositoryCustom{

	@Query(" FROM OsiConfigParameters WHERE businessGroupId = :businessGroupId")
    List<OsiConfigParameters> findConfigParametersByBusinessGroupId(@Param("businessGroupId") Integer businessGroupId) throws DataAccessException;
	
	List<OsiConfigParameters> findByBusinessGroupId(Integer businessGroupId,Pageable pageObject);
	//@Query(" FROM OsiConfigParameters WHERE businessGroupId = :businessGroupId and configName =:configName")
	//List<OsiConfigParameters> findConfigParametersByBusinessGroupIdAndName(@Param("configName") String cnfigName, @Param("businessGroupId") Integer businessGroupId) throws DataAccessException;

	OsiConfigParameters findOsiConfigParametersByBusinessGroupIdAndConfigName(Integer businessGroupId,String configName) throws DataAccessException;
}
