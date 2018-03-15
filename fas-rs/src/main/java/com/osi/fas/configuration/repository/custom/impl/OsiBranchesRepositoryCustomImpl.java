package com.osi.fas.configuration.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.fas.configuration.repository.custom.OsiBranchesRepositoryCustom;
import com.osi.fas.domain.OsiBranches;
import com.osi.fas.service.dto.OsiBranchesDTO;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.DataAccessException;

@Component("osiBranchesRepositoryCustom")
public class OsiBranchesRepositoryCustomImpl implements OsiBranchesRepositoryCustom {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	private AppConfig appConfig;
	
	@Override
	public List<OsiBranches> getInitialBranches() throws DataAccessException {
		List<OsiBranches> osiBranchesList = new ArrayList<>();
		try{
			String query = "SELECT b FROM OsiBranches b ORDER BY b.updatedDate desc";
			osiBranchesList= this.entityManager.createQuery(query, OsiBranches.class)
					.setMaxResults(appConfig.getMaxResults())		
					.getResultList();
			
		}catch(QueryTimeoutException e){
			throw new DataAccessException("ERR_1100", e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("ERR_1002", e.getMessage());
		}
		return osiBranchesList;
	}
	
	@Override
	public List<OsiBranches> searchAllBranches() throws DataAccessException {
		List<OsiBranches> osiBranchesList = new ArrayList<>();
		try{
			String query = "SELECT b FROM OsiBranches b ORDER BY b.updatedDate desc";
			osiBranchesList= this.entityManager.createQuery(query, OsiBranches.class).getResultList();
			
		}catch(QueryTimeoutException e){
			throw new DataAccessException("ERR_1100", e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("ERR_1002", e.getMessage());
		}
		return osiBranchesList;
	}
	
	@Override
	public List<OsiBranches> searchBranches(OsiBranchesDTO osiBranchesDTO) throws DataAccessException {
		List<OsiBranches> osiBranchesList = new ArrayList<>();
		try{
			String query = "SELECT b FROM OsiBranches b WHERE 1 = 1 ";
			
			if(null != osiBranchesDTO.getBranchCode() && !osiBranchesDTO.getBranchCode().isEmpty()) {
				query += "AND upper(b.branchCode) LIKE upper('%" + osiBranchesDTO.getBranchCode() + "%') ";
			}
			if(null != osiBranchesDTO.getBranchName() && !osiBranchesDTO.getBranchName().isEmpty()) {
				query += "AND upper(b.branchName) LIKE upper('%" + osiBranchesDTO.getBranchName() + "%') ";
			}
			query += "ORDER BY b.updatedDate desc ";
	
			Query dbQuery = this.entityManager.createQuery(query, OsiBranches.class);
			osiBranchesList = dbQuery.getResultList();
			
		}catch(QueryTimeoutException e){
			throw new DataAccessException("ERR_1100", e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("ERR_1002", e.getMessage());
		}
	
		return osiBranchesList;
	}
	
	@Override
	public OsiBranches saveOrUpdateBranch(OsiBranches osiBranches) throws DataAccessException {
		try {
			if(null != osiBranches.getBranchId() && osiBranches.getBranchId() != 0){
				OsiBranches osiBranchExist = this.entityManager.find(OsiBranches.class, osiBranches.getBranchId());
				osiBranches.setCreatedBy(osiBranchExist.getCreatedBy());
				osiBranches.setCreatedDate(osiBranchExist.getCreatedDate());
			}
			osiBranches = this.entityManager.merge(osiBranches);
		}catch(QueryTimeoutException e){
			throw new DataAccessException("ERR_1100", e.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage());
		}
		return osiBranches;
	}
	
	@Override
	public List<OsiBranches> searchAllActiveBranches() throws DataAccessException {
		List<OsiBranches> osiBranchesList = new ArrayList<>();
		try{
			String query = "SELECT b FROM OsiBranches b WHERE b.status = 1 ORDER BY b.updatedDate desc";
			osiBranchesList = this.entityManager.createQuery(query, OsiBranches.class).getResultList();
			
		}catch(QueryTimeoutException e){
			throw new DataAccessException("ERR_1100", e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			throw new DataAccessException("ERR_1002", e.getMessage());
		}
		return osiBranchesList;
	}

}
