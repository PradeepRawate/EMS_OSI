package com.osi.fas.configuration.repository.custom;

import java.text.ParseException;
import java.util.List;

import com.osi.fas.domain.OsiNotifications;
import com.osi.fas.domain.OsiPoReqHeader;
import com.osi.fas.domain.OsiPrAttachments;
import com.osi.fas.domain.OsiPrReqHeader;
import com.osi.fas.service.dto.OsiMrApprovalDTO;
import com.osi.urm.domain.OsiApprovers;
import com.osi.urm.domain.OsiUser;
import com.osi.urm.exception.DataAccessException;

public interface OsiNotificationsRepositoryCustom {
	
	public List<OsiPrReqHeader> getInitialNotifications(Integer notificationUserId,Integer businessGroupId) throws DataAccessException;
	public List<OsiPrReqHeader> findAllNotifications(Integer notificationUserId,Integer businessGroupId, String searchString) throws DataAccessException;
	public int approveOrRejectPendingNotifcation(OsiNotifications osiNotifications) throws DataAccessException,ParseException;
	public List<Object[]> getPRLineDetails(Integer prReqHdrId, Integer businessGroupId) throws DataAccessException;
	public List<Object[]> getPreviousApprovalDetails(Integer prReqHdrId, Integer businessGroupId) throws DataAccessException;
	public List<OsiApprovers> getNextValidApproversDetails(Integer level,Integer categoryId, Integer invOrgId, String trxType, Integer businessGroupId, String departmentCode, Integer operatingUnitId) throws DataAccessException;
	public List<Object[]> getPRLineHistory(Integer prReqLineId,Integer businessGroupId) throws DataAccessException;
	public List<Object[]> getPRLineCreationDetails(Integer prReqLineId,Integer businessGroupId) throws DataAccessException;
	public List<Object[]> getPRLineDetailsSingleApprover(Integer prReqHdrId,Integer userId, Integer businessGroupId) throws DataAccessException;
	public List<Object[]> getRejectedPRLineDetails(Integer prReqHdrId,Integer businessGroupId) throws DataAccessException;
	public List<OsiPrAttachments> getAttachmentsList(Integer hdrId, Integer lineId, String trxType, Integer businessGroupId)  throws DataAccessException;
	
	//PO Approval Methods
	public List<OsiPoReqHeader> getInitialPONotifications(int notificationUserId,Integer businessGroupId) throws DataAccessException;
	public List<OsiPoReqHeader> findAllPONotifications(int notificationUserId,Integer businessGroupId,String searchString) throws DataAccessException;
	public List<Object[]> getPOLineDetails(Integer poReqHdrId, Integer businessGroupId) throws DataAccessException;
	public List<Object[]> getRejectedPOLineDetails(Integer poReqHdrId,Integer businessGroupId) throws DataAccessException;
	public List<Object[]> getPOLineHistory(Integer poReqLineId, Integer businessGroupId) throws DataAccessException;
	public List<Object[]> getPOLineCreationDetails(Integer poReqLineId, Integer businessGroupId) throws DataAccessException;
	
	//MR Approval Methods
	public List<OsiMrApprovalDTO> getInitialMrNotifications(Integer businessGroupId, Integer userId) throws DataAccessException;
	public List<OsiMrApprovalDTO> findAllMrNotifications(OsiMrApprovalDTO osiMrApprovalDTO) throws DataAccessException;
	public List<Object[]> getRejectedMRLineDetails(Integer prReqHdrId, Integer businessGroupId) throws DataAccessException;
	public List<Object[]> getMRLineDetails(Integer prReqHdrId, Integer businessGroupId) throws DataAccessException;
	public List<OsiUser> getItemAdmin(Integer businessGroupId) throws DataAccessException;
	
	//Admin approval screen
	public List<Object[]> getAllTransactionsForAdmin(String trxType, String headerNumber, Integer businessGroupId) throws DataAccessException;

	public int resetTransaction(String notificationObject, Integer hdrId,List<Integer> prLineIds, String notificationAction, Integer id,Integer businessGroupId)  throws DataAccessException;
	
}
