package com.osi.fas.configuration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.osi.fas.configuration.repository.custom.OsiCurrencyRepositoryCustom;
import com.osi.fas.domain.OsiCurrency;
import com.osi.urm.exception.DataAccessException;

/**
 * Spring Data JPA repository for the OsiCoa entity.
 */
@Repository
public interface OsiCurrencyRepository extends JpaRepository<OsiCurrency,Integer>, OsiCurrencyRepositoryCustom {
	
	@Query(" FROM OsiCurrency WHERE businessGroupId = :businessGroupId")
    List<OsiCurrency> findAllActiveCurrencies(@Param("businessGroupId") Integer businessGroupId) throws DataAccessException;
	
	@Query(" FROM OsiCurrency WHERE businessGroupId = :businessGroupId and currencyId =:currencyId")
	OsiCurrency findSingleCurrency(@Param("currencyId") Integer currencyId, @Param("businessGroupId") Integer businessGroupId) throws DataAccessException;
	
	
}


