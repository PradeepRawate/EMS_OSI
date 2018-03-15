package com.osi.fas.activiti.errortaskhandler;

import java.io.Serializable;
import java.util.Date;

public class ErrorTaskDTO implements Serializable{
	private String taskId;
	private String reqType;
	private String reqNum;
	private String reqLineId;
	private String taskName;
	private String procInstanceId;
	private String procDefinitionId;
	private String errorMsg;
	private String createdDate;
	private String reqLineNum;
	
	public ErrorTaskDTO(){}
	
	public ErrorTaskDTO(String taskId, String reqType, String reqNum, String reqLineId, String taskName,
			String procInstanceId, String procDefinitionId, String errorMsg, String createdDate, String reqLineNum) {
		this.taskId = taskId;
		this.reqType = reqType;
		this.reqNum = reqNum;
		this.reqLineId = reqLineId;
		this.taskName = taskName;
		this.procInstanceId = procInstanceId;
		this.procDefinitionId = procDefinitionId;
		this.errorMsg = errorMsg;
		this.createdDate = createdDate;
		this.reqLineNum = reqLineNum;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getReqType() {
		return reqType;
	}

	public void setReqType(String reqType) {
		this.reqType = reqType;
	}

	public String getReqNum() {
		return reqNum;
	}

	public void setReqNum(String reqNum) {
		this.reqNum = reqNum;
	}

	public String getReqLineId() {
		return reqLineId;
	}

	public void setReqLineId(String reqLineId) {
		this.reqLineId = reqLineId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getProcInstanceId() {
		return procInstanceId;
	}

	public void setProcInstanceId(String procInstanceId) {
		this.procInstanceId = procInstanceId;
	}

	public String getProcDefinitionId() {
		return procDefinitionId;
	}

	public void setProcDefinitionId(String procDefinitionId) {
		this.procDefinitionId = procDefinitionId;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getReqLineNum() {
		return reqLineNum;
	}

	public void setReqLineNum(String reqLineNum) {
		this.reqLineNum = reqLineNum;
	}
	
	
	
}
