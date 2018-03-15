package com.osi.fas.activiti.errortaskhandler;

import java.util.List;

import com.osi.fas.service.dto.OsiConfigParametersDTO;
import com.osi.urm.exception.DataAccessException;

public interface ErrorTaskRepository {
	public List<ErrorTaskDTO> getAllTasks() throws DataAccessException;
	public OsiConfigParametersDTO getActivitiBaseUrl() throws DataAccessException;
}
