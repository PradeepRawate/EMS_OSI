package com.osi.fas.configuration.repository.custom;

import java.util.List;

import com.osi.fas.domain.OsiBranches;
import com.osi.fas.service.dto.OsiBranchesDTO;
import com.osi.urm.exception.DataAccessException;

public interface OsiBranchesRepositoryCustom  {
	
	public List<OsiBranches> getInitialBranches() throws DataAccessException;
	
	public List<OsiBranches> searchAllBranches() throws DataAccessException;
	
	public List<OsiBranches> searchBranches(OsiBranchesDTO osiBranchesDTO) throws DataAccessException;
	
	public OsiBranches saveOrUpdateBranch(OsiBranches osiBranches) throws DataAccessException;
	
	public List<OsiBranches> searchAllActiveBranches() throws DataAccessException;
	
}
