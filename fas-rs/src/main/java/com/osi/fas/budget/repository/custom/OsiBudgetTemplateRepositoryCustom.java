package com.osi.fas.budget.repository.custom;

import java.math.BigDecimal;

import com.osi.urm.exception.DataAccessException;

/**
 * 
 * @author sparashara
 *
 */
public interface OsiBudgetTemplateRepositoryCustom {
	
	void saveBudgetTemplateInfo(
			String budgetType,
			String periodName,
			Integer periodYear,
			Integer periodNum,
			String glCompanyCode,
			String glBranchCode,
			String glDeptCode,
			String glNaturalAcct,
			String glReserve1,
			String glReserve2,
			String glReserve3,
			String glReserve4,
			BigDecimal budgetAmount,
			BigDecimal availedAmount,
			BigDecimal savingsAmount,
			String budgetStatus,
			String budgetFlag,
			String lastUpdateDate,
			Integer lastUpdatedBy, String deptCodeReference
			) throws DataAccessException;
	

}
