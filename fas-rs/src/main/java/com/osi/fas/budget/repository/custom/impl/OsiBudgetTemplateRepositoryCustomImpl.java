package com.osi.fas.budget.repository.custom.impl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.osi.fas.budget.repository.custom.OsiBudgetTemplateRepositoryCustom;
import com.osi.urm.exception.DataAccessException;

/**
 * 
 * @author sparashara
 *
 */
@Component("osiBudgetTemplateRepositoryCustom")
public class OsiBudgetTemplateRepositoryCustomImpl implements OsiBudgetTemplateRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void saveBudgetTemplateInfo(String budgetType, String periodName, Integer periodYear, Integer periodNum,
			String glCompanyCode, String glBranchCode, String glDeptCode, String glNaturalAcct, String glReserve1,
			String glReserve2, String glReserve3, String glReserve4, BigDecimal budgetAmount, BigDecimal availedAmount,
			BigDecimal savingsAmount, String budgetStatus, String budgetFlag, String lastUpdateDate,
			Integer lastUpdatedBy, String deptCodeRef) throws DataAccessException {
		
		try {
			String sql = "EXEC saveBudgetEntry ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
			Query qry = entityManager.createNativeQuery(sql);
			qry.setParameter(1, budgetType);
			qry.setParameter(2, periodName);
			qry.setParameter(3, periodYear);
			qry.setParameter(4, periodNum);
			qry.setParameter(5, glCompanyCode);
			qry.setParameter(6, glBranchCode);
			qry.setParameter(7, glDeptCode);
			qry.setParameter(8, glNaturalAcct);
			qry.setParameter(9, glReserve1);
			qry.setParameter(10, glReserve2);
			qry.setParameter(11, glReserve3);
			qry.setParameter(12, glReserve4);
			qry.setParameter(13, budgetAmount);
			qry.setParameter(14, availedAmount);
			qry.setParameter(15, savingsAmount);
			qry.setParameter(16, budgetStatus);
			qry.setParameter(17, budgetFlag);
			qry.setParameter(18, lastUpdateDate);
			qry.setParameter(19, lastUpdatedBy);
			qry.setParameter(20, deptCodeRef);
			qry.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			//throw new DataAccessException("ERR_1000", "An error occurred while trying to process item : from MIN-MAX template.", e);
		}
	}		
	
}
