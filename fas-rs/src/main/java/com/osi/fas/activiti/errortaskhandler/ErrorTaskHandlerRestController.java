package com.osi.fas.activiti.errortaskhandler;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osi.fas.service.dto.OsiConfigParametersDTO;
import com.osi.urm.config.AppConfig;

@RestController
@RequestMapping("/api")
public class ErrorTaskHandlerRestController {
	private final Logger LOGGER = LoggerFactory.getLogger(ErrorTaskHandlerRestController.class);
	@Autowired
	private ErrorTaskService errorTaskService;
	
	@RequestMapping(value={"/activiti/error-handling-tasks"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json; charset=UTF-8"})
	public List<ErrorTaskDTO> getAllTasks(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) {
		List<ErrorTaskDTO> errorTaskList = null;
		try {
			errorTaskList = (List<ErrorTaskDTO>)errorTaskService.getAllTasks();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error getting all tasks.");
		}
		return errorTaskList;
	}
	
	@RequestMapping(value={"/activiti/base-url"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json; charset=UTF-8"})
	public OsiConfigParametersDTO getActivitiBaseUrl(@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) {
		OsiConfigParametersDTO activitiBaseUrlConfigParams = null;
		try {
			activitiBaseUrlConfigParams = errorTaskService.getActivitiBaseUrl();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error getting activiti base url.");
		}
		return activitiBaseUrlConfigParams;
	}
}
