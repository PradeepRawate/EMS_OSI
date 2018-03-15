package com.osi.fas.activiti.errortaskhandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.osi.fas.domain.OsiOperatingUnit;
import com.osi.fas.service.dto.OsiConfigParametersDTO;
import com.osi.urm.exception.DataAccessException;

@Component("errorTaskRepository")
public class ErrorTaskRepositoryImpl implements ErrorTaskRepository {
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ErrorTaskDTO> getAllTasks() throws DataAccessException {
		List<ErrorTaskDTO> resultList = null;
		try {
			String query = "select" 
					+ " ID_ TASK_ID,"
					+ " PROC_INST_ID_,"
					+ " PROC_DEF_ID_,"
					+ " CREATE_TIME_,"
					+ " B.TEXT_ TASK_NAME,"
					+ " C.TEXT_ ERROR,"
					+ " CASE WHEN PROC_DEF_ID_ LIKE 'PR%' THEN  (SELECT REQ_LINE_ID FROM OSI_PR_REQ_LINES WHERE WORK_FLOW_RUN_ID = PROC_INST_ID_)"
					+ " WHEN  PROC_DEF_ID_ LIKE 'PO%' THEN  (SELECT PO_REQ_LINE_ID FROM OSI_PO_REQ_LINES WHERE WORK_FLOW_RUN_ID = PROC_INST_ID_)"
					+ " WHEN  PROC_DEF_ID_ LIKE 'MR%' THEN  (SELECT ISS_REQ_LINES_ID FROM OSI_ITEM_ISSUE_LINES WHERE WORK_FLOW_RUN_ID = PROC_INST_ID_)"
					+ " END	AS LINE_ID,"
					+ " CASE WHEN PROC_DEF_ID_ LIKE 'PR%' THEN  (SELECT h.REQ_NUMBER FROM OSI_PR_REQ_LINES l, OSI_PR_REQ_HEADER h WHERE l.WORK_FLOW_RUN_ID = PROC_INST_ID_ and l.REQ_HDR_ID = h.REQ_HDR_ID)"
					+ " WHEN  PROC_DEF_ID_ LIKE 'PO%' THEN  (SELECT h.PO_NUMBER FROM OSI_PO_REQ_LINES l, OSI_PO_REQ_HEADER h WHERE l.WORK_FLOW_RUN_ID = PROC_INST_ID_ and l.PO_REQ_HDR_ID = h.PO_REQ_HDR_ID)"
					+ " WHEN  PROC_DEF_ID_ LIKE 'MR%' THEN  (SELECT h.ISS_REQ_NUMBER FROM OSI_ITEM_ISSUE_LINES l, OSI_ITEM_ISSUE_HEADER h WHERE l.WORK_FLOW_RUN_ID = PROC_INST_ID_ and l.ISS_REQ_HDR_ID = h.ISS_REQ_HDR_ID)"
					+ " END	AS REQUEST_NUMBER,"
					+ " CASE WHEN PROC_DEF_ID_ LIKE 'PR%' THEN  (SELECT REQ_LINE_NUMBER FROM OSI_PR_REQ_LINES WHERE WORK_FLOW_RUN_ID = PROC_INST_ID_)"
					+ " WHEN  PROC_DEF_ID_ LIKE 'PO%' THEN  (SELECT PO_LINE_NUMBER FROM OSI_PO_REQ_LINES WHERE WORK_FLOW_RUN_ID = PROC_INST_ID_)"
					+ " WHEN  PROC_DEF_ID_ LIKE 'MR%' THEN  (SELECT ISS_REQ_LINE_NUMBER FROM OSI_ITEM_ISSUE_LINES WHERE WORK_FLOW_RUN_ID = PROC_INST_ID_)"
					+ " END	AS LINE_NUM"
					+ " from "
					+ " (select ID_, EXECUTION_ID_, PROC_INST_ID_, PROC_DEF_ID_, CREATE_TIME_ from ACT_RU_TASK) A,"
					+ " (select text_, name_, EXECUTION_ID_ from act_ru_variable where name_ = 'task-name') B,"
					+ " (select text_, name_, EXECUTION_ID_ from act_ru_variable where name_ = 'error') C"
					+ " where B.EXECUTION_ID_ = C.EXECUTION_ID_"
					+ " and A.EXECUTION_ID_ = B.EXECUTION_ID_"
					+ " order by CREATE_TIME_";
			
			List<Object[]> objectList = (List<Object[]>) this.entityManager.createNativeQuery(query)
					.getResultList();
			resultList = new ArrayList<ErrorTaskDTO>();
			for (Object[] objects : objectList) {
				ErrorTaskDTO errorTask = new ErrorTaskDTO();
				if (objects[0] != null)
					errorTask.setTaskId(objects[0].toString());
				if (objects[1] != null)
					errorTask.setProcInstanceId(objects[1].toString());
				if (objects[2] != null)
					errorTask.setProcDefinitionId(objects[2].toString());
				if (objects[3] != null)
					errorTask.setCreatedDate(objects[3].toString());
				if (objects[4] != null)
					errorTask.setTaskName(objects[4].toString());
				if (objects[5] != null)
					errorTask.setErrorMsg(objects[5].toString());
				if (objects[6] != null)
					errorTask.setReqLineId(objects[6].toString());
				if (objects[7] != null)
					errorTask.setReqNum(objects[7].toString());
				if (objects[8] != null)
					errorTask.setReqLineNum(objects[8].toString());
				
				resultList.add(errorTask);
			}
			
		} catch (NoResultException e) {
			throw new DataAccessException("ERR_1002", e.getMessage());
		} catch (Exception e) {
			throw new DataAccessException("ERR_1089", e.getMessage());
		}
		return resultList;
	}

	@Override
	public OsiConfigParametersDTO getActivitiBaseUrl() throws DataAccessException {
		OsiConfigParametersDTO osiConfigParametersDTO = new OsiConfigParametersDTO();
		try {
			String query = "SELECT CONFIG_ID, CONFIG_NAME, CONFIG_VALUE, BUSINESS_GROUP_ID FROM OSI_CONFIG_PARAMETERS WHERE CONFIG_NAME = 'WORKFLOW_BASE_URL';";
			
			Object[] row = (Object[]) this.entityManager.createNativeQuery(query)
					.getSingleResult();
			
			osiConfigParametersDTO.setConfigId(Integer.parseInt(row[0].toString()));
			osiConfigParametersDTO.setConfigName(row[1].toString());
			osiConfigParametersDTO.setConfigValue(row[2].toString());
			osiConfigParametersDTO.setBusinessGroupId(Integer.parseInt(row[3].toString()));
			
		} catch (NoResultException e) {
			throw new DataAccessException("ERR_1002", e.getMessage());
		} catch (Exception e) {
			throw new DataAccessException("ERR_1089", e.getMessage());
		}
		return osiConfigParametersDTO;
	}
	
}
