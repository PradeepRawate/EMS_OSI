package com.osi.fas.activiti.errortaskhandler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osi.fas.service.dto.OsiConfigParametersDTO;
import com.osi.urm.exception.DataAccessException;

@Service
@Transactional
public class ErrorTaskServiceImpl implements ErrorTaskService{
	
	@Autowired
	ErrorTaskRepository errorTaskRepository;
	
	@Override
	public List<ErrorTaskDTO> getAllTasks() throws DataAccessException {
		return errorTaskRepository.getAllTasks();
	}
	
	@Override
	public OsiConfigParametersDTO getActivitiBaseUrl() throws DataAccessException {
		return errorTaskRepository.getActivitiBaseUrl();
	}
	
}
