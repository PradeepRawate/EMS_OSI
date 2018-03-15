package com.osi.fas.configuration.repository.custom.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;

import com.osi.fas.configuration.repository.custom.OsiNotificationsRepositoryCustom;
import com.osi.fas.domain.OsiItemIssueHeader;
import com.osi.fas.domain.OsiItemIssueLines;
import com.osi.fas.domain.OsiNotifications;
import com.osi.fas.domain.OsiPhyInvHeader;
import com.osi.fas.domain.OsiPhyInvLines;
import com.osi.fas.domain.OsiPoAttachments;
import com.osi.fas.domain.OsiPoReqHeader;
import com.osi.fas.domain.OsiPoReqLines;
import com.osi.fas.domain.OsiPrAttachments;
import com.osi.fas.domain.OsiPrReqHeader;
import com.osi.fas.domain.OsiPrReqLines;
import com.osi.fas.service.dto.OsiMrApprovalDTO;
import com.osi.urm.config.AppConfig;
import com.osi.urm.domain.OsiApprovers;
import com.osi.urm.domain.OsiUser;
import com.osi.urm.exception.DataAccessException;

public class OsiNotificationsRepositoryImpl implements OsiNotificationsRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private AppConfig appConfig;

	@SuppressWarnings("unchecked")
	@Override
	public List<OsiPrReqHeader> getInitialNotifications(Integer notificationUserId, Integer businessGroupId) throws DataAccessException {
		List<OsiPrReqHeader> osiPrReqHeaderList = null;
		try{

			//String query = "SELECT n.notificationId, rh.workFlowUrl, rh.reqNumber, rh.requestType, rh.docStatus, rh.prReqHdrId, rh.fundsStatus, rh.createdDate, u FROM OsiNotifications n, OsiPrReqHeader rh, OsiUser u WHERE n.notificationObjectId = rh.prReqHdrId AND u.id = rh.createdBy AND n.notificationUserId=:notificationUserId AND n.notificationStatus='OPEN' AND n.businessGroupId = rh.businessGroupId AND n.businessGroupId = :businessGroupId";
			
			String query = " SELECT  rh.workFlowUrl, rh.reqNumber, rh.requestType, rh.docStatus, rh.prReqHdrId, rh.fundsStatus, rh.createdDate, u.firstName, u.lastName, inv.invOrgName "+
					" FROM OsiNotifications n, OsiPrReqHeader rh, OsiUser u, OsiPrReqLines rl, OsiInventoryOrg inv "+
					" WHERE n.notificationObjectId = rl.prReqLineId AND n.notificationObject='PR' AND u.id = rh.createdBy AND rl.osiPrReqHeader.prReqHdrId = rh.prReqHdrId "+
					" AND n.notificationUserId=:notificationUserId AND inv.invOrgId = rh.shipToInvOrgId "+ 
					" AND n.notificationStatus='OPEN' AND n.businessGroupId = rh.businessGroupId AND u.businessGroupId = :businessGroupId AND rl.businessGroupId = :businessGroupId and rl.reqLineStatus not in ('CANCELLED') "+
					" AND n.businessGroupId = :businessGroupId group by  rh.workFlowUrl, rh.reqNumber, rh.requestType, rh.docStatus, rh.prReqHdrId, rh.fundsStatus, rh.createdDate, u.firstName, u.lastName, inv.invOrgName  ";
			
			List<Object[]> objectList = (List<Object[]>) this.entityManager.createQuery(query) 
					.setParameter("businessGroupId", businessGroupId)
					.setParameter("notificationUserId", notificationUserId)
					.setMaxResults(appConfig.getMaxResults())
					.getResultList();
			osiPrReqHeaderList = new ArrayList<OsiPrReqHeader>();
			for (Object[] objects : objectList) {
				OsiPrReqHeader osiPrReqHeader = new OsiPrReqHeader();
				/*if (objects[0] != null)
					osiPrReqHeader.setNotificationId(Integer.parseInt(objects[0].toString()));*/
				if (objects[0] != null)
					osiPrReqHeader.setWorkFlowUrl(objects[0].toString());
				if (objects[1] != null)
					osiPrReqHeader.setReqNumber(objects[1].toString());
				if (objects[2] != null)
					osiPrReqHeader.setRequestType(objects[2].toString());
				if (objects[3] != null)
					osiPrReqHeader.setDocStatus(objects[3].toString());
				if (objects[4] != null)
					osiPrReqHeader.setPrReqHdrId(Integer.parseInt(objects[4].toString()));
				if (objects[5] != null)
					osiPrReqHeader.setFundsStatus(objects[5].toString());
				if (objects[6] != null) 
					osiPrReqHeader.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(objects[6].toString()));
				if (objects[7] != null)
					osiPrReqHeader.setCreatorFirstName(objects[7].toString());
				if (objects[8] != null)
					osiPrReqHeader.setCreatorLastName(objects[8].toString());
				if (objects[9] != null)
					osiPrReqHeader.setDeliveryLocation(objects[9].toString());
	
				osiPrReqHeaderList.add(osiPrReqHeader);
			}
		}catch(Exception e){
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}

		return osiPrReqHeaderList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OsiPrReqHeader> findAllNotifications(Integer notificationUserId, Integer businessGroupId, String searchString) throws DataAccessException {
		List<OsiPrReqHeader> osiPrReqHeaderList = null;
		try{

			//String query = "SELECT n.notificationId, rh.workFlowUrl, rh.reqNumber, rh.requestType, rh.docStatus, rh.prReqHdrId, rh.fundsStatus, rh.createdDate, u FROM OsiNotifications n, OsiPrReqHeader rh, OsiUser u WHERE n.notificationObjectId = rh.prReqHdrId AND u.id = rh.createdBy AND n.notificationUserId=:notificationUserId AND n.notificationStatus='OPEN' AND n.businessGroupId = rh.businessGroupId AND n.businessGroupId = :businessGroupId";
			
			String query = " SELECT  rh.workFlowUrl, rh.reqNumber, rh.requestType, rh.docStatus, rh.prReqHdrId, rh.fundsStatus, rh.createdDate, u.firstName, u.lastName, inv.invOrgName "+
					" FROM OsiNotifications n, OsiPrReqHeader rh, OsiUser u, OsiPrReqLines rl, OsiInventoryOrg inv "+
					" WHERE n.notificationObjectId = rl.prReqLineId AND n.notificationObject='PR' AND u.id = rh.createdBy AND rl.osiPrReqHeader.prReqHdrId = rh.prReqHdrId "+
					" AND n.notificationUserId=:notificationUserId AND inv.invOrgId = rh.shipToInvOrgId "+ 
					" AND n.notificationStatus='OPEN' AND n.businessGroupId = rh.businessGroupId AND u.businessGroupId = :businessGroupId AND rl.businessGroupId = :businessGroupId and rl.reqLineStatus not in ('CANCELLED') "+ searchString + 
					" AND n.businessGroupId = :businessGroupId group by  rh.workFlowUrl, rh.reqNumber, rh.requestType, rh.docStatus, rh.prReqHdrId, rh.fundsStatus, rh.createdDate, u.firstName, u.lastName, inv.invOrgName  ";
			
			List<Object[]> objectList = (List<Object[]>) this.entityManager.createQuery(query) 
					.setParameter("businessGroupId", businessGroupId)
					.setParameter("notificationUserId", notificationUserId)
					.getResultList();
			osiPrReqHeaderList = new ArrayList<OsiPrReqHeader>();
			for (Object[] objects : objectList) {
				OsiPrReqHeader osiPrReqHeader = new OsiPrReqHeader();
				/*if (objects[0] != null)
					osiPrReqHeader.setNotificationId(Integer.parseInt(objects[0].toString()));*/
				if (objects[0] != null)
					osiPrReqHeader.setWorkFlowUrl(objects[0].toString());
				if (objects[1] != null)
					osiPrReqHeader.setReqNumber(objects[1].toString());
				if (objects[2] != null)
					osiPrReqHeader.setRequestType(objects[2].toString());
				if (objects[3] != null)
					osiPrReqHeader.setDocStatus(objects[3].toString());
				if (objects[4] != null)
					osiPrReqHeader.setPrReqHdrId(Integer.parseInt(objects[4].toString()));
				if (objects[5] != null)
					osiPrReqHeader.setFundsStatus(objects[5].toString());
				if (objects[6] != null){
					
						
							//System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(objects[7].toString()));
							//osiPrReqHeader.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(objects[7].toString()));
					osiPrReqHeader.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(objects[6].toString()));
						
					
				}
				if (objects[7] != null)
					osiPrReqHeader.setCreatorFirstName(objects[7].toString());
				if (objects[8] != null)
					osiPrReqHeader.setCreatorLastName(objects[8].toString());
				if (objects[9] != null)
					osiPrReqHeader.setDeliveryLocation(objects[9].toString());
	
				osiPrReqHeaderList.add(osiPrReqHeader);
			}
		}catch(Exception e){
			//e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}

		return osiPrReqHeaderList;
	}

	@Override
	public int approveOrRejectPendingNotifcation(
			OsiNotifications osiNotifications) throws DataAccessException {
		int count = 0;
		String errorCode="";
		try {
			
			
			String approveOrRejectNotificationQuery = "update OsiNotifications n set n.notificationAction = :notificationAction, n.notificationActionDate = :notificationActionDate, n.notificationStatus = :notificationStatus, n.comments= :comments where n.notificationUserId = :notificationUserId and n.notificationStatus = 'OPEN' and n.businessGroupId = :businessGroupId and n.notificationObjectId = :notificationObjectId and n.notificationObject= :notificationObject";
			count = this.entityManager.createQuery(approveOrRejectNotificationQuery)
					  .setParameter("notificationAction", osiNotifications.getNotificationAction())
					  .setParameter("notificationActionDate", osiNotifications.getNotificationActionDate())
					  .setParameter("notificationStatus", osiNotifications.getNotificationStatus())
					  .setParameter("notificationUserId", osiNotifications.getNotificationUserId())
					  .setParameter("businessGroupId", osiNotifications.getBusinessGroupId())
					  .setParameter("notificationObjectId",osiNotifications.getNotificationObjectId())
					  .setParameter("notificationObject", osiNotifications.getNotificationObject())
					  .setParameter("comments",osiNotifications.getComments())
			          .executeUpdate();
			
			//String approveOrRejectPRHeaderQry = "update ";
			
			if(count==0){
				errorCode="ERR_1000";
				if(osiNotifications.getNotificationAction().equalsIgnoreCase("approved")){
					errorCode="ERR_1054";
				}
				if(osiNotifications.getNotificationAction().equalsIgnoreCase("rejected")){
					errorCode="ERR_1055";
				}
				if(osiNotifications.getNotificationAction().equalsIgnoreCase("RFI")){
					errorCode="ERR_1062";
				}
				throw new DataAccessException(errorCode,  null); 
			}
		}catch (DataAccessException e) {
			throw new DataAccessException(e.getErrorCode() , e.getMessage()); 
			//e.printStackTrace();
		}catch (Exception e) {
			throw new DataAccessException("ERR_1000", e.getMessage()); 
			//e.printStackTrace();
		}
		return count;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getPRLineDetails(Integer prReqHdrId, Integer businessGroupId) throws DataAccessException {
		List<Object[]> objectList = null;
		try{

			//String query = "SELECT rh, u.firstName,u.lastName, c, rl FROM OsiNotifications n, OsiPrReqHeader rh, OsiPrReqLines rl, OsiUser u, OsiCurrency c WHERE rh.currencyId=c.currencyId and n.notificationObjectId = rl.prReqLineId and rl.osiPrReqHeader.prReqHdrId=rh.prReqHdrId AND u.id = rh.createdBy AND n.notificationUserId= :notificationUserId AND rh.prReqHdrId= :prReqHdrId AND n.businessGroupId = rh.businessGroupId AND n.businessGroupId = :businessGroupId AND n.notificationObject='PR'  AND n.notificationStatus='OPEN'";
			//String query = "SELECT rh, u.firstName,u.lastName, c, rl, cat FROM OsiPrReqHeader rh, OsiPrReqLines rl, OsiUser u, OsiCurrency c, OsiCategories cat WHERE rh.currencyId=c.currencyId and rl.osiPrReqHeader.prReqHdrId=rh.prReqHdrId AND u.id = rh.createdBy AND rh.prReqHdrId= :prReqHdrId AND u.businessGroupId = rh.businessGroupId AND c.businessGroupId = rh.businessGroupId AND rh.businessGroupId = :businessGroupId AND cat.categoryId = rl.categoryId order by rl.prReqLineId ";
					//+ " and rl.reqLineStatus not in ('CANCELLED','APPROVED','REJECTED','RFI')  order by rl.prReqLineId";
			
			String query = "SELECT rh, u.firstName, u.lastName, c, rl, cat, io.invOrgCode, io.invOrgName " + 
						   "FROM OsiPrReqHeader rh, OsiPrReqLines rl, OsiUser u, OsiCurrency c, OsiCategories cat, OsiInventoryOrg io " + 
						   "WHERE rh.currencyId = c.currencyId AND rl.osiPrReqHeader.prReqHdrId = rh.prReqHdrId " + 
						   "	  AND u.id = rh.createdBy AND rh.prReqHdrId = :prReqHdrId " + 
						   "	  AND u.businessGroupId = rh.businessGroupId AND c.businessGroupId = rh.businessGroupId " + 
						   "	  AND rh.businessGroupId = :businessGroupId AND cat.categoryId = rl.categoryId " + 
						   "	  AND rh.shipToInvOrgId = io.invOrgId " + 
						   "ORDER BY rl.prReqLineId ";
			 objectList = (List<Object[]>) this.entityManager.createQuery(query)
					.setParameter("prReqHdrId", prReqHdrId)
					.setParameter("businessGroupId", businessGroupId)
					.getResultList();
			//osiPrReqHeader = new OsiPrReqHeader();
			/*for (Object[] objects : objectList) {
				OsiPrReqHeader osiPrReqHeader = null;
				if (objects[0] != null){
					osiPrReqHeader = (OsiPrReqHeader) objects[0];
					for(OsiPrReqLines line : osiPrReqHeader.getOsiPrReqLinesSet()){
						System.out.println(line.getPrReqLineId());
					}
				}
				if (objects[1] != null)
					osiPrReqHeader.setCreatorFirstName(objects[1].toString());
				
				if (objects[2] != null)
					osiPrReqHeader.setCreatorFirstName(objects[2].toString());
				
				if (objects[3] != null)
					osiPrReqHeader.setOsiCurrency((OsiCurrency)objects[3]);
				*/
				//osiPrReqHeaderList.add(osiPrReqHeader);
			//}
		}catch(Exception e){
			/*e.printStackTrace();*/
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}

		return objectList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getRejectedPRLineDetails(Integer prReqHdrId, Integer businessGroupId) throws DataAccessException {
		List<Object[]> objectList = null;
		try{

			//String query = "SELECT rh, u.firstName,u.lastName, c, rl FROM OsiNotifications n, OsiPrReqHeader rh, OsiPrReqLines rl, OsiUser u, OsiCurrency c WHERE rh.currencyId=c.currencyId and n.notificationObjectId = rl.prReqLineId and rl.osiPrReqHeader.prReqHdrId=rh.prReqHdrId AND u.id = rh.createdBy AND n.notificationUserId= :notificationUserId AND rh.prReqHdrId= :prReqHdrId AND n.businessGroupId = rh.businessGroupId AND n.businessGroupId = :businessGroupId AND n.notificationObject='PR'  AND n.notificationStatus='OPEN'";
			//AND n.notificationAction='REJECTED'
			String query = " SELECT rl,n.comments,cat FROM OsiPrReqHeader rh, OsiPrReqLinesHistory rl, OsiNotifications n, OsiCategories cat "+
							" WHERE rl.osiPrReqHeader.prReqHdrId=rh.prReqHdrId AND n.notificationObjectId = rl.prReqLineId AND n.notificationObject='PR' "+
							" and n.businessGroupId=rh.businessGroupId and rl.notificationId = n.notificationId "+
							" AND rh.prReqHdrId= :prReqHdrId AND rl.businessGroupId = rh.businessGroupId "+
							" AND rh.businessGroupId = :businessGroupId AND cat.categoryId = rl.categoryId ";
			 objectList = (List<Object[]>) this.entityManager.createQuery(query)
					.setParameter("prReqHdrId", prReqHdrId)
					.setParameter("businessGroupId", businessGroupId)
					.getResultList();
		}catch(Exception e){
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}

		return objectList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getPreviousApprovalDetails(Integer prReqHdrId, Integer businessGroupId) throws DataAccessException {
		List<Object[]> objectList = null;
		try{

			String query = "Select n, u from OsiNotifications n, OsiUser u where n.notificationUserId=u.id and n.businessGroupId = :businessGroupId and n.notificationObjectId= :prReqHdrId and n.notificationActionDate is not null and n.notificationAction is not null order by n.notificationActionDate asc";
			objectList = (List<Object[]>) this.entityManager.createQuery(query)
					.setParameter("prReqHdrId", prReqHdrId)
					.setParameter("businessGroupId", businessGroupId)
					.getResultList();
			
			/*for (OsiNotifications objects : objectList) {
				System.out.println(objects.getNotificationId()+" "+objects.getNotificationUserId());
			}*/
		}catch(Exception e){
			/*e.printStackTrace();*/
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}

		return objectList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsiApprovers> getNextValidApproversDetails(Integer level,Integer categoryId, Integer invOrgId, String trxType, Integer businessGroupId, String departmentCode, Integer bpaId)
			throws DataAccessException {
		List<OsiApprovers> approversList = null;
		List<Object[]> obj = null;
		try{
			if(trxType!=null && trxType.equals("BPA")){
				String query =  " SELECT distinct h.limit, u.userId FROM OsiApprovalHierarchy h, OsiUser u, OsiUserRoles ur, com.osi.fas.domain.OsiInvOrgUser iu, OsiRoleCategoryMapping rcm, OsiBpaAssignments bpaass "+
						" WHERE h.osiRoles.roleId = ur.roleId.roleId and u.businessGroupId = :businessGroupId and u.startDate <= :currentDate and u.endDate >= :currentDate "+
						" and ur.osiUser.userId =u.userId and iu.osiUser.userId =u.userId and h.osiRoles.roleId = rcm.osiRoles.roleId and rcm.categoryId=:categoryId and iu.osiInventoryOrg.osiOperatingUnit.operatingUnitId = bpaass.operatingUnitId "+
						" and h.approvalLevel =:level and h.requestType=:trxType and iu.osiInventoryOrg.osiOperatingUnit.osiLegalEntities.osiBusinessUnit.buId=h.businessUnitId and rcm.categoryId = h.categoryId and bpaass.osiPoReqHeader.poReqHdrId=:bpaId ";
				obj = (List<Object[]>) this.entityManager.createQuery(query)
						.setParameter("level", level+"")
						.setParameter("businessGroupId", businessGroupId)
						.setParameter("categoryId", categoryId)
						.setParameter("currentDate", new Date(), TemporalType.DATE)
						.setParameter("trxType", trxType)
						.setParameter("bpaId", bpaId)
						.getResultList();
			}else if(trxType!=null && trxType.equals("PI")){
				String query =  " SELECT distinct h.limit, u.userId FROM OsiApprovalHierarchy h, OsiUser u, OsiUserRoles ur, com.osi.fas.domain.OsiInvOrgUser iu "+
						" WHERE h.osiRoles.roleId = ur.roleId.roleId and u.businessGroupId = :businessGroupId and u.startDate <= :currentDate and u.endDate >= :currentDate "+
						" and ur.osiUser.userId =u.userId and iu.osiUser.userId =u.userId and iu.osiInventoryOrg.invOrgId = :invOrgId "+
						" and h.approvalLevel =:level and h.requestType=:trxType and iu.osiInventoryOrg.osiOperatingUnit.osiLegalEntities.osiBusinessUnit.buId=h.businessUnitId ";
				obj = (List<Object[]>) this.entityManager.createQuery(query)
						.setParameter("level", level+"")
						.setParameter("businessGroupId", businessGroupId)
						/*.setParameter("categoryId", categoryId)*/
						.setParameter("currentDate", new Date(), TemporalType.DATE)
						.setParameter("trxType", trxType)
						.setParameter("invOrgId", invOrgId)
						.getResultList();
			}else{
				String query =  " SELECT distinct h.limit, u.userId FROM OsiUserDepartment d, OsiApprovalHierarchy h, OsiUser u, OsiUserRoles ur, com.osi.fas.domain.OsiInvOrgUser iu, OsiRoleCategoryMapping rcm "+
						" WHERE d.osiUser.userId =u.userId and  h.osiRoles.roleId = ur.roleId.roleId and u.businessGroupId = :businessGroupId and u.startDate <= :currentDate and u.endDate >= :currentDate "+
						" and ur.osiUser.userId =u.userId and iu.osiUser.userId =u.userId and h.osiRoles.roleId = rcm.osiRoles.roleId and rcm.categoryId=:categoryId and iu.osiInventoryOrg.invOrgId = :invOrgId "+
						" and h.approvalLevel =:level and h.requestType=:trxType and iu.osiInventoryOrg.osiOperatingUnit.osiLegalEntities.osiBusinessUnit.buId=h.businessUnitId and d.departmentId= :departmentId  and rcm.categoryId = h.categoryId ";
				obj = (List<Object[]>) this.entityManager.createQuery(query)
						.setParameter("level", level+"")
						.setParameter("businessGroupId", businessGroupId)
						.setParameter("categoryId", categoryId)
						.setParameter("currentDate", new Date(), TemporalType.DATE)
						.setParameter("trxType", trxType)
						.setParameter("invOrgId", invOrgId)
						.setParameter("departmentId", Integer.parseInt(departmentCode))
						.getResultList();
			}
			approversList = new ArrayList<OsiApprovers>();
			if(obj!=null && obj.size()>0){
				for (Object[] objects : obj) {
					OsiApprovers osiApprovers = new OsiApprovers();
					osiApprovers.setApproverLimit((Integer)objects[0]);
					osiApprovers.setUserId((Integer)objects[1]);
					approversList.add(osiApprovers);
				}
			}
			/*for (OsiNotifications objects : objectList) {
				System.out.println(objects.getNotificationId()+" "+objects.getNotificationUserId());
			}*/
		}catch(Exception e){
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}

		return approversList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getPRLineHistory(Integer prReqLineId,
			Integer businessGroupId) throws DataAccessException {
		List<Object[]> objectList = null;
		List<Object[]> objectList1 = null;
		try{
			objectList1 = new ArrayList<Object[]>();
			String query = " SELECT 0 as orby, usr.firstName + ' ' + usr.lastName, 'SUBMITTED' as action, lin.createdDate, " +
					" lin.quantity, lin.amount, lin.comments, lin.justification "+
					" FROM OsiPrReqLines lin, OsiPrReqHeader hdr, OsiUser usr "+
					" where lin.prReqLineId =:lineId "+
					" and lin.osiPrReqHeader.prReqHdrId = hdr.prReqHdrId "+
					" and hdr.preparerId = usr.userId ";
			objectList = (List<Object[]>) this.entityManager.createQuery(query)
					.setParameter("lineId", prReqLineId).getResultList();
			objectList1.addAll(objectList);
			
			String query1 = " select notx.notificationId as orby, usr.firstName + ' ' + usr.lastName, notx.notificationAction as action, notx.notificationActionDate as createdDate, "+ 
			" hist.quantity, hist.amount, hist.comments, hist.justification  "+
			" from OsiNotifications notx, OsiPrReqLinesHistory hist, OsiPrReqLines lin, OsiPrReqHeader hdr, OsiUser usr "+
			" where lin.prReqLineId = :lineId  "+
			" and lin.osiPrReqHeader.prReqHdrId = hdr.prReqHdrId "+
			" and lin.prReqLineId = hist.prReqLineId "+
			" and hist.notificationId = notx.notificationId "+
			" and lin.prReqLineId = notx.notificationObjectId "+
			" and notx.notificationObject = 'PR' "+
			" and notx.notificationUserId = usr.userId ";
			objectList = (List<Object[]>) this.entityManager.createQuery(query1)
					.setParameter("lineId", prReqLineId).getResultList();
			objectList1.addAll(objectList);
					String query2 =  " select notx.notificationId as orby, usr.firstName + ' ' + usr.lastName, 'PENDING' as action, '' as createdDate, 0 as quantity, 0 as amount, '' as comments, ''  as justification "+
					" from OsiNotifications notx, OsiPrReqLines lin, OsiPrReqHeader hdr, OsiUser usr "+
					" where lin.prReqLineId = :lineId  "+
					" and lin.osiPrReqHeader.prReqHdrId = hdr.prReqHdrId "+
					" and lin.prReqLineId = notx.notificationObjectId "+
					" and notx.notificationObject = 'PR' "+
					" and notx.notificationUserId = usr.userId "+
					" and  not exists ( "+
					"        select  1 "+
					"      from    OsiPrReqLinesHistory hist "+
					" where   hist.notificationId = notx.notificationId "+
					"  ) ";
			objectList = (List<Object[]>) this.entityManager.createQuery(query2)
					.setParameter("lineId", prReqLineId).getResultList();
			objectList1.addAll(objectList);
			/*String query = "Select n, u from OsiNotifications n, OsiUser u where n.notificationUserId=u.id and n.businessGroupId = :businessGroupId and n.notificationObjectId= :prReqLineId and n.notificationObject='PR'  order by n.notificationActionDate asc";
			objectList = (List<Object[]>) this.entityManager.createQuery(query)
					.setParameter("prReqLineId", prReqLineId)
					.setParameter("businessGroupId", businessGroupId)
					.getResultList();*/
		}catch(Exception e){
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}
		return objectList1;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getPRLineCreationDetails(Integer prReqLineId,
			Integer businessGroupId) throws DataAccessException {
		List<Object[]> objectList = null;
		try{
			String query = "Select pl, u from OsiPrReqLines pl, OsiUser u where pl.createdBy=u.userId and pl.businessGroupId = :businessGroupId and u.businessGroupId = :businessGroupId and pl.prReqLineId= :prReqLineId ";
			objectList = (List<Object[]>) this.entityManager.createQuery(query)
					.setParameter("prReqLineId", prReqLineId)
					.setParameter("businessGroupId", businessGroupId)
					.getResultList();
		}catch(Exception e){
			/*e.printStackTrace();*/
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}
		return objectList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getPRLineDetailsSingleApprover(Integer prReqHdrId,
			Integer userId, Integer businessGroupId) throws DataAccessException {
		List<Object[]> objectList = null;
		try{
			String query = "SELECT rh, u.firstName,u.lastName, c, rl FROM OsiPrReqHeader rh, OsiPrReqLines rl, OsiUser u, OsiCurrency c, OsiNotifications n "+
			" WHERE rh.currencyId=c.currencyId and rl.osiPrReqHeader.prReqHdrId=rh.prReqHdrId AND u.id = rh.createdBy AND rh.prReqHdrId= :prReqHdrId AND u.businessGroupId = rh.businessGroupId "+
			" AND c.businessGroupId = rh.businessGroupId AND rh.businessGroupId = :businessGroupId AND n.notificationObjectId = rl.prReqLineId AND n.businessGroupId= :businessGroupId AND n.notificationObject = 'PR' AND n.notificationStatus='OPEN' AND n.notificationUserId = :notificationUserId order by rl.prReqLineId";
			 objectList = (List<Object[]>) this.entityManager.createQuery(query)
					.setParameter("prReqHdrId", prReqHdrId)
					.setParameter("businessGroupId", businessGroupId)
					.setParameter("notificationUserId", userId)
					.getResultList();
		}catch(Exception e){
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}

		return objectList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsiPrAttachments> getAttachmentsList(Integer hdrId, Integer lineId, String trxType,Integer businessGroupId)
			throws DataAccessException {
		String errorCode = "ERR_1000";
		List<OsiPrAttachments> result = new ArrayList<OsiPrAttachments>();
		List<OsiPoAttachments> poAttachments = new ArrayList<OsiPoAttachments>();
		List<Object[]> objectList = null;
		try{
			if(trxType.equals("PR")){
				String lineParameter="";
				if(lineId!=null){
					lineParameter=" and pra.osiPrReqLines.prReqLineId= "+lineId+" ";
				}else{
					lineParameter=" and pra.osiPrReqLines.prReqLineId= null ";
				}
				String query="select pra from OsiPrAttachments pra where pra.businessGroupId= :businessGroupId and pra.osiPrReqHeader.prReqHdrId= :prReqHdrId"+lineParameter;
				result = (List<OsiPrAttachments>) this.entityManager.createQuery(query)
						.setParameter("businessGroupId", businessGroupId)
						.setParameter("prReqHdrId",hdrId)
						.getResultList();
				if(result==null || result.isEmpty()){
					errorCode = "ERR_1059";
					throw new Exception();
				}
			}
			if(trxType.equals("PO")){
				String lineParameter="";
				if(lineId!=null){
					lineParameter=" and poa.osiPoReqLines.poReqLineId= "+lineId+" ";
				}else{
					lineParameter=" and poa.osiPoReqLines.poReqLineId= null ";
				}
				String query="select poa from OsiPoAttachments poa where poa.businessGroupId= :businessGroupId and poa.osiPoReqHeader.poReqHdrId= :poReqHdrId"+lineParameter;
				poAttachments = (List<OsiPoAttachments>) this.entityManager.createQuery(query)
						.setParameter("businessGroupId", businessGroupId)
						.setParameter("poReqHdrId",hdrId)
						.getResultList();
				if(poAttachments==null || poAttachments.isEmpty()){
					errorCode = "ERR_1059";
					throw new Exception();
				}
				for(OsiPoAttachments poattachment : poAttachments){
					OsiPrAttachments attachment = new OsiPrAttachments();
					attachment.setPrAttachmentId(poattachment.getPoAttachmentId());
					attachment.setAttachmentName(poattachment.getAttachmentName());
					attachment.setCreatedDate(poattachment.getCreatedDate());
					attachment.setOriginalFileName(poattachment.getOriginalFileName());
					result.add(attachment);
				}
			}
		}catch(Exception e){
			throw new DataAccessException(errorCode, e.getMessage()); 
		}
		return result;
	}

	/*************************************************
	*
	*
	*
	*		PO Approval services
	*
	*
	*
	**************************************************/
	@SuppressWarnings("unchecked")
	@Override
	public List<OsiPoReqHeader> getInitialPONotifications(int notificationUserId, Integer businessGroupId) throws DataAccessException {
		List<OsiPoReqHeader> osiPoReqHeaderList = null;
		try{
			String query = " SELECT  oh.workFlowUrl, oh.poNumber, oh.poType, oh.docStatus, oh.poReqHdrId, oh.fundStatus, oh.createdDate, u.firstName, u.lastName, inv.invOrgName "+
					" FROM OsiNotifications n, OsiPoReqHeader oh, OsiUser u, OsiPoReqLines ol, OsiInventoryOrg inv  "+
					" WHERE n.notificationObjectId = ol.poReqLineId AND n.notificationObject='PO' AND u.id = oh.createdBy AND ol.osiPoReqHeader.poReqHdrId = oh.poReqHdrId "+
					" AND n.notificationUserId=:notificationUserId  AND inv.invOrgId = oh.shipToInvOrgId  "+ 
					" AND n.notificationStatus='OPEN' AND n.businessGroupId = oh.businessGroupId AND u.businessGroupId = :businessGroupId AND ol.businessGroupId = :businessGroupId and ol.poLineStatus not in ('CANCELLED') "+
					" AND n.businessGroupId = :businessGroupId group by  oh.workFlowUrl, oh.poNumber, oh.poType, oh.docStatus, oh.poReqHdrId, oh.fundStatus, oh.createdDate, u.firstName, u.lastName, inv.invOrgName ";
					
			
			List<Object[]> objectList = (List<Object[]>) this.entityManager.createQuery(query) 
					.setParameter("businessGroupId", businessGroupId)
					.setParameter("notificationUserId", notificationUserId)
					.setMaxResults(appConfig.getMaxResults())
					.getResultList();
			osiPoReqHeaderList = new ArrayList<OsiPoReqHeader>();
			for (Object[] objects : objectList) {
				OsiPoReqHeader osiPoReqHeader = new OsiPoReqHeader();
				if (objects[0] != null)
					osiPoReqHeader.setWorkFlowUrl(objects[0].toString());
				if (objects[1] != null)
					osiPoReqHeader.setPoNumber(objects[1].toString());
				if (objects[2] != null)
					osiPoReqHeader.setPoType(objects[2].toString());
				if (objects[3] != null)
					osiPoReqHeader.setDocStatus(objects[3].toString());
				if (objects[4] != null)
					osiPoReqHeader.setPoReqHdrId(Integer.parseInt(objects[4].toString()));
				if (objects[5] != null)
					osiPoReqHeader.setFundStatus(objects[5].toString());
				if (objects[6] != null)
					osiPoReqHeader.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(objects[6].toString()));
				if (objects[7] != null)
					osiPoReqHeader.setCreatorFirstName(objects[7].toString());
				if (objects[8] != null)
					osiPoReqHeader.setCreatorLastName(objects[8].toString());
				if (objects[9] != null)
					osiPoReqHeader.setDeliveryLocation(objects[9].toString());
	
				osiPoReqHeaderList.add(osiPoReqHeader);
			}
		}catch(Exception e){
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}
		
		return osiPoReqHeaderList;
	}
	
	@Override
	public List<OsiPoReqHeader> findAllPONotifications(int notificationUserId,
			Integer businessGroupId, String searchString) throws DataAccessException {
		List<OsiPoReqHeader> osiPoReqHeaderList = null;
		try{

			//String query = "SELECT n.notificationId, rh.workFlowUrl, rh.reqNumber, rh.requestType, rh.docStatus, rh.prReqHdrId, rh.fundsStatus, rh.createdDate, u FROM OsiNotifications n, OsiPrReqHeader rh, OsiUser u WHERE n.notificationObjectId = rh.prReqHdrId AND u.id = rh.createdBy AND n.notificationUserId=:notificationUserId AND n.notificationStatus='OPEN' AND n.businessGroupId = rh.businessGroupId AND n.businessGroupId = :businessGroupId";
			
			String query = " SELECT  oh.workFlowUrl, oh.poNumber, oh.poType, oh.docStatus, oh.poReqHdrId, oh.fundStatus, oh.createdDate, u.firstName, u.lastName, inv.invOrgName "+
					" FROM OsiNotifications n, OsiPoReqHeader oh, OsiUser u, OsiPoReqLines ol, OsiInventoryOrg inv  "+
					" WHERE n.notificationObjectId = ol.poReqLineId AND n.notificationObject='PO' AND u.id = oh.createdBy AND ol.osiPoReqHeader.poReqHdrId = oh.poReqHdrId "+
					" AND n.notificationUserId=:notificationUserId  AND inv.invOrgId = oh.shipToInvOrgId  "+ 
					" AND n.notificationStatus='OPEN' AND n.businessGroupId = oh.businessGroupId AND u.businessGroupId = :businessGroupId AND ol.businessGroupId = :businessGroupId and ol.poLineStatus not in ('CANCELLED') "+ searchString +
					" AND n.businessGroupId = :businessGroupId group by  oh.workFlowUrl, oh.poNumber, oh.poType, oh.docStatus, oh.poReqHdrId, oh.fundStatus, oh.createdDate, u.firstName, u.lastName, inv.invOrgName  ";
			
			List<Object[]> objectList = (List<Object[]>) this.entityManager.createQuery(query) 
					.setParameter("businessGroupId", businessGroupId)
					.setParameter("notificationUserId", notificationUserId)
					.getResultList();
			osiPoReqHeaderList = new ArrayList<OsiPoReqHeader>();
			for (Object[] objects : objectList) {
				OsiPoReqHeader osiPoReqHeader = new OsiPoReqHeader();
				/*if (objects[0] != null)
					osiPrReqHeader.setNotificationId(Integer.parseInt(objects[0].toString()));*/
				if (objects[0] != null)
					osiPoReqHeader.setWorkFlowUrl(objects[0].toString());
				if (objects[1] != null)
					osiPoReqHeader.setPoNumber(objects[1].toString());
				if (objects[2] != null)
					osiPoReqHeader.setPoType(objects[2].toString());
				if (objects[3] != null)
					osiPoReqHeader.setDocStatus(objects[3].toString());
				if (objects[4] != null)
					osiPoReqHeader.setPoReqHdrId(Integer.parseInt(objects[4].toString()));
				if (objects[5] != null)
					osiPoReqHeader.setFundStatus(objects[5].toString());
				if (objects[6] != null){
					
						
							//System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(objects[7].toString()));
							//osiPrReqHeader.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(objects[7].toString()));
					osiPoReqHeader.setCreatedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(objects[6].toString()));
						
					
				}
				if (objects[7] != null)
					osiPoReqHeader.setCreatorFirstName(objects[7].toString());
				if (objects[8] != null)
					osiPoReqHeader.setCreatorLastName(objects[8].toString());
				if (objects[9] != null)
					osiPoReqHeader.setDeliveryLocation(objects[9].toString());
	
				osiPoReqHeaderList.add(osiPoReqHeader);
			}
		}catch(Exception e){
			//e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}

		return osiPoReqHeaderList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getPOLineDetails(Integer poReqHdrId, Integer businessGroupId) throws DataAccessException {
		List<Object[]> objectList = null;
		try{

			//String query = "SELECT oh, u.firstName,u.lastName, c, ol FROM OsiPoReqHeader oh, OsiPoReqLines ol, OsiUser u, OsiCurrency c WHERE oh.currencyId=c.currencyId and ol.osiPoReqHeader.poReqHdrId=oh.poReqHdrId AND u.id = oh.createdBy AND oh.poReqHdrId= :poReqHdrId AND u.businessGroupId = oh.businessGroupId AND c.businessGroupId = oh.businessGroupId AND oh.businessGroupId = :businessGroupId  order by ol.poReqLineId";
			//		+ "and ol.poLineStatus not in ('CANCELLED','APPROVED','REJECTED','RFI') order by ol.poReqLineId";
			
			String query = "SELECT oh, u.firstName, u.lastName, c, ol, io.invOrgCode, io.invOrgName, s.supplierName " + 
						   "FROM OsiPoReqHeader oh, OsiPoReqLines ol, OsiUser u, OsiCurrency c, OsiInventoryOrg io, OsiSupplier s  " + 
						   "WHERE oh.currencyId = c.currencyId AND ol.osiPoReqHeader.poReqHdrId = oh.poReqHdrId " + 
						   "	  AND u.id = oh.createdBy AND oh.poReqHdrId = :poReqHdrId " + 
						   "	  AND u.businessGroupId = oh.businessGroupId AND c.businessGroupId = oh.businessGroupId " + 
						   "	  AND oh.businessGroupId = :businessGroupId AND oh.shipToInvOrgId = io.invOrgId " +
						   "	  AND oh.vendorId = s.supplierId " + 
						   "ORDER BY ol.poReqLineId";
			 objectList = (List<Object[]>) this.entityManager.createQuery(query)
					.setParameter("poReqHdrId", poReqHdrId)
					.setParameter("businessGroupId", businessGroupId)
					.getResultList();
		}catch(Exception e){
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}

		return objectList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getRejectedPOLineDetails(Integer poReqHdrId, Integer businessGroupId) throws DataAccessException {
		List<Object[]> objectList = null;
		try{
			// AND n.notificationAction='REJECTED'
			String query = " SELECT ol,n.comments FROM OsiPoReqHeader oh, OsiPoReqLinesHistory ol, OsiNotifications n "+
							" WHERE ol.osiPoReqHeader.poReqHdrId=oh.poReqHdrId AND n.notificationObjectId = ol.poReqLineId AND n.notificationObject='PO' "+
							" and n.businessGroupId=oh.businessGroupId AND ol.notificationId = n.notificationId"+ 
							" AND oh.poReqHdrId= :poReqHdrId AND ol.businessGroupId = oh.businessGroupId "+
							" AND oh.businessGroupId = :businessGroupId ";
			 objectList = (List<Object[]>) this.entityManager.createQuery(query)
					.setParameter("poReqHdrId", poReqHdrId)
					.setParameter("businessGroupId", businessGroupId)
					.getResultList();
		}catch(Exception e){
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}

		return objectList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getPOLineCreationDetails(Integer poReqLineId,
			Integer businessGroupId) throws DataAccessException {
		List<Object[]> objectList = null;
		try{
			String query = "Select pol, u from OsiPoReqLines pol, OsiUser u where pol.createdBy=u.userId and pol.businessGroupId = :businessGroupId and u.businessGroupId = :businessGroupId and pol.poReqLineId= :poReqLineId ";
			objectList = (List<Object[]>) this.entityManager.createQuery(query)
					.setParameter("poReqLineId", poReqLineId)
					.setParameter("businessGroupId", businessGroupId)
					.getResultList();
		}catch(Exception e){
			/*e.printStackTrace();*/
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}
		return objectList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getPOLineHistory(Integer poReqLineId,
			Integer businessGroupId) throws DataAccessException {
		List<Object[]> objectList = null;
		List<Object[]> objectList1 = null;
		try{
			objectList1 = new ArrayList<Object[]>();
			//String query = "Select n, u from OsiNotifications n, OsiUser u where n.notificationUserId=u.id and n.businessGroupId = :businessGroupId and n.notificationObjectId= :poReqLineId and n.notificationObject='PO'  order by n.notificationActionDate asc";
			String query = " SELECT 0 as orby, usr.firstName+' '+usr.lastName, 'SUBMITTED' as action, lin.createdDate, " +
					" lin.quantity, lin.amount, lin.comments, lin.justification "+
					" FROM OsiPoReqLines lin, OsiPoReqHeader hdr, OsiUser usr "+
					" where lin.poReqLineId =:lineId "+
					" and lin.osiPoReqHeader.poReqHdrId = hdr.poReqHdrId "+
					" and hdr.buyerId = usr.userId ";
			objectList = (List<Object[]>) this.entityManager.createQuery(query)
					.setParameter("lineId", poReqLineId).getResultList();
			objectList1.addAll(objectList);
			
			String query1 = " select notx.notificationId as orby, usr.firstName+' '+usr.lastName, notx.notificationAction as action, notx.notificationActionDate as createdDate, "+ 
			" hist.quantity, hist.amount, hist.comments, hist.justification  "+
			" from OsiNotifications notx, OsiPoReqLinesHistory hist, OsiPoReqLines lin, OsiPoReqHeader hdr, OsiUser usr "+
			" where lin.poReqLineId = :lineId  "+
			" and lin.osiPoReqHeader.poReqHdrId = hdr.poReqHdrId "+
			" and lin.poReqLineId = hist.poReqLineId "+
			" and hist.notificationId = notx.notificationId "+
			" and lin.poReqLineId = notx.notificationObjectId "+
			" and notx.notificationObject = 'PO' "+
			" and notx.notificationUserId = usr.userId ";
			objectList = (List<Object[]>) this.entityManager.createQuery(query1)
					.setParameter("lineId", poReqLineId).getResultList();
			objectList1.addAll(objectList);
					String query2 =  " select notx.notificationId as orby, usr.firstName+' '+usr.lastName, 'PENDING' as action, '' as createdDate, 0 as quantity, 0 as amount, '' as comments, ''  as justification "+
					" from OsiNotifications notx, OsiPoReqLines lin, OsiPoReqHeader hdr, OsiUser usr "+
					" where lin.poReqLineId = :lineId  "+
					" and lin.osiPoReqHeader.poReqHdrId = hdr.poReqHdrId "+
					" and lin.poReqLineId = notx.notificationObjectId "+
					" and notx.notificationObject = 'PO' "+
					" and notx.notificationUserId = usr.userId "+
					" and  not exists ( "+
					"        select  1 "+
					"      from    OsiPoReqLinesHistory hist "+
					" where   hist.notificationId = notx.notificationId "+
					"  ) ";
			objectList = (List<Object[]>) this.entityManager.createQuery(query2)
					.setParameter("lineId", poReqLineId).getResultList();
			objectList1.addAll(objectList);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}
		return objectList1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsiMrApprovalDTO> getInitialMrNotifications(Integer businessGroupId, Integer userId) throws DataAccessException {
		List<OsiMrApprovalDTO> issHeaderDtoList = new ArrayList<OsiMrApprovalDTO>();
		try{
			String query = "SELECT  mrh.workFlowUrl, mrh.issReqNumber, mrh.issReqHdrStatus, mrh.issReqHdrId, mrh.createdDate, u.firstName, u.lastName, mrh.issReqType, mrh.issReqTotalAmt "+
					" FROM OsiNotifications n, OsiItemIssueHeader mrh, OsiUser u, OsiItemIssueLines mrl "+
					" WHERE n.notificationObjectId = mrl.issReqLinesId AND n.notificationObject='MR' AND u.id = mrh.createdBy AND mrl.osiItemIssueHeader.issReqHdrId = mrh.issReqHdrId "+
					" AND n.notificationUserId=:notificationUserId AND mrh.issReqHdrStatus='WIP'"+ 
					" AND n.notificationStatus='OPEN' AND n.businessGroupId = mrh.businessGroupId AND u.businessGroupId = :businessGroupId AND mrl.businessGroupId = :businessGroupId "+
					" AND n.businessGroupId = :businessGroupId";
			
			query = query + " group by mrh.workFlowUrl, mrh.issReqNumber, mrh.issReqHdrStatus, mrh.issReqHdrId, mrh.createdDate, u.firstName, u.lastName, mrh.issReqType, mrh.issReqTotalAmt";
			
			List<Object[]> objectList = (List<Object[]>) this.entityManager.createQuery(query) 
					.setParameter("businessGroupId", businessGroupId)
					.setParameter("notificationUserId", userId)
					.setMaxResults(appConfig.getMaxResults())
					.getResultList();
			String workFlowUrl = "";
			String issReqNumber = "";
			String issReqHdrStatus = "";
			Integer issReqHdrId = 0;
			Date createdDate = null;
			String requesterName = "";
			String reqType = "";
			BigDecimal issReqTotalAmt = BigDecimal.ZERO;
			
			for(Object[] obj : objectList){
				
				workFlowUrl = (String)obj[0];
				issReqNumber = (String)obj[1];
				issReqHdrStatus = (String)obj[2];
				issReqHdrId = (Integer)obj[3];
				createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(obj[4].toString());
				requesterName = (String)obj[5] + " " + (String)obj[6];
				reqType = (String)obj[7];
				issReqTotalAmt = (BigDecimal)obj[8];
				
				OsiMrApprovalDTO dto = new OsiMrApprovalDTO();
				dto.setWorkFlowUrl(workFlowUrl);
				dto.setIssReqNumber(issReqNumber);
				dto.setIssReqHdrStatus(issReqHdrStatus);
				dto.setIssReqHdrId(issReqHdrId);
				dto.setCreatedDate(createdDate);
				dto.setRequesterName(requesterName);
				dto.setReqType(reqType);
				dto.setIssReqTotalAmt(issReqTotalAmt);
				
				issHeaderDtoList.add(dto);
			}
		}catch(Exception e){
			/*e.printStackTrace();*/
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}
		return issHeaderDtoList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OsiMrApprovalDTO> findAllMrNotifications(OsiMrApprovalDTO osiMrApprovalDTO) throws DataAccessException {
		
		List<OsiMrApprovalDTO> issHeaderDtoList = new ArrayList<OsiMrApprovalDTO>();
		
		try{
			
			String query = "SELECT  mrh.workFlowUrl, mrh.issReqNumber, mrh.issReqHdrStatus, mrh.issReqHdrId, mrh.createdDate, u.firstName, u.lastName, mrh.issReqType, mrh.issReqTotalAmt "+
					" FROM OsiNotifications n, OsiItemIssueHeader mrh, OsiUser u, OsiItemIssueLines mrl "+
					" WHERE n.notificationObjectId = mrl.issReqLinesId AND n.notificationObject='MR' AND u.id = mrh.createdBy AND mrl.osiItemIssueHeader.issReqHdrId = mrh.issReqHdrId "+
					" AND n.notificationUserId=:notificationUserId AND mrh.issReqHdrStatus='WIP'"+ 
					" AND n.notificationStatus='OPEN' AND n.businessGroupId = mrh.businessGroupId AND u.businessGroupId = :businessGroupId AND mrl.businessGroupId = :businessGroupId "+
					" AND n.businessGroupId = :businessGroupId";
			
			if(null!=osiMrApprovalDTO.getIssReqNumber()){
				query = query + " AND mrh.issReqNumber LIKE '%"+osiMrApprovalDTO.getIssReqNumber()+"%'";
			}
			if(null!=osiMrApprovalDTO.getRequesterName()){
				query = query + " AND u.firstName+ ' ' +u.lastName LIKE '%"+osiMrApprovalDTO.getRequesterName()+"%'";
			}
			
			query = query + " group by mrh.workFlowUrl, mrh.issReqNumber, mrh.issReqHdrStatus, mrh.issReqHdrId, mrh.createdDate, u.firstName, u.lastName, mrh.issReqType, mrh.issReqTotalAmt";
			
			List<Object[]> objectList = (List<Object[]>) this.entityManager.createQuery(query) 
					.setParameter("businessGroupId", osiMrApprovalDTO.getBusinessGroupId())
					.setParameter("notificationUserId", osiMrApprovalDTO.getUserId())
					.getResultList();
			
			String workFlowUrl = "";
			String issReqNumber = "";
			String issReqHdrStatus = "";
			Integer issReqHdrId = 0;
			Date createdDate = null;
			String requesterName = "";
			String reqType = "";
			BigDecimal issReqTotalAmt = BigDecimal.ZERO;
			
			for(Object[] obj : objectList){
				
				workFlowUrl = (String)obj[0];
				issReqNumber = (String)obj[1];
				issReqHdrStatus = (String)obj[2];
				issReqHdrId = (Integer)obj[3];
				createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(obj[4].toString());
				requesterName = (String)obj[5] + " " + (String)obj[6];
				reqType = (String)obj[7];
				issReqTotalAmt = (BigDecimal)obj[8];
				
				OsiMrApprovalDTO dto = new OsiMrApprovalDTO();
				dto.setWorkFlowUrl(workFlowUrl);
				dto.setIssReqNumber(issReqNumber);
				dto.setIssReqHdrStatus(issReqHdrStatus);
				dto.setIssReqHdrId(issReqHdrId);
				dto.setCreatedDate(createdDate);
				dto.setRequesterName(requesterName);
				dto.setReqType(reqType);
				dto.setIssReqTotalAmt(issReqTotalAmt);
				
				issHeaderDtoList.add(dto);
				
			}
			
		}catch(Exception e){
			/*e.printStackTrace();*/
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}
		
		return issHeaderDtoList;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getMRLineDetails(Integer issReqHdrId, Integer businessGroupId) throws DataAccessException {
		List<Object[]> objectList = null;
		try{

			//String query = "SELECT rh, u.firstName,u.lastName, c, rl FROM OsiNotifications n, OsiPrReqHeader rh, OsiPrReqLines rl, OsiUser u, OsiCurrency c WHERE rh.currencyId=c.currencyId and n.notificationObjectId = rl.prReqLineId and rl.osiPrReqHeader.prReqHdrId=rh.prReqHdrId AND u.id = rh.createdBy AND n.notificationUserId= :notificationUserId AND rh.prReqHdrId= :prReqHdrId AND n.businessGroupId = rh.businessGroupId AND n.businessGroupId = :businessGroupId AND n.notificationObject='PR'  AND n.notificationStatus='OPEN'";
			String query = "SELECT mrh, u.firstName,u.lastName, mrl FROM OsiItemIssueHeader mrh, OsiItemIssueLines mrl, OsiUser u WHERE mrl.osiItemIssueHeader.issReqHdrId=mrh.issReqHdrId AND u.id = mrh.createdBy AND mrh.issReqHdrId= :issReqHdrId AND u.businessGroupId = rh.businessGroupId AND rh.businessGroupId = :businessGroupId order by mrl.issReqLinesId";
			 objectList = (List<Object[]>) this.entityManager.createQuery(query)
					.setParameter("issReqHdrId", issReqHdrId)
					.setParameter("businessGroupId", businessGroupId)
					.getResultList();
			//osiPrReqHeader = new OsiPrReqHeader();
			/*for (Object[] objects : objectList) {
				OsiPrReqHeader osiPrReqHeader = null;
				if (objects[0] != null){
					osiPrReqHeader = (OsiPrReqHeader) objects[0];
					for(OsiPrReqLines line : osiPrReqHeader.getOsiPrReqLinesSet()){
						System.out.println(line.getPrReqLineId());
					}
				}
				if (objects[1] != null)
					osiPrReqHeader.setCreatorFirstName(objects[1].toString());
				
				if (objects[2] != null)
					osiPrReqHeader.setCreatorFirstName(objects[2].toString());
				
				if (objects[3] != null)
					osiPrReqHeader.setOsiCurrency((OsiCurrency)objects[3]);
				*/
				//osiPrReqHeaderList.add(osiPrReqHeader);
			//}
		}catch(Exception e){
			/*e.printStackTrace();*/
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}

		return objectList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getRejectedMRLineDetails(Integer prReqHdrId, Integer businessGroupId) throws DataAccessException {
		List<Object[]> objectList = null;
		try{

			//String query = "SELECT rh, u.firstName,u.lastName, c, rl FROM OsiNotifications n, OsiPrReqHeader rh, OsiPrReqLines rl, OsiUser u, OsiCurrency c WHERE rh.currencyId=c.currencyId and n.notificationObjectId = rl.prReqLineId and rl.osiPrReqHeader.prReqHdrId=rh.prReqHdrId AND u.id = rh.createdBy AND n.notificationUserId= :notificationUserId AND rh.prReqHdrId= :prReqHdrId AND n.businessGroupId = rh.businessGroupId AND n.businessGroupId = :businessGroupId AND n.notificationObject='PR'  AND n.notificationStatus='OPEN'";
			String query = " SELECT mrl FROM OsiPrReqHeader rh, OsiPrReqLinesHistory rl, OsiNotifications n "+
							" WHERE rl.osiPrReqHeader.prReqHdrId=rh.prReqHdrId AND n.notificationObjectId = rl.prReqLineId AND n.notificationObject='PR' "+
							" AND n.notificationAction='REJECTED' and n.businessGroupId=rh.businessGroupId and rl.notificationId = n.notificationId "+
							" AND rh.prReqHdrId= :prReqHdrId AND rl.businessGroupId = rh.businessGroupId "+
							" AND rh.businessGroupId = :businessGroupId ";
			 objectList = (List<Object[]>) this.entityManager.createQuery(query)
					.setParameter("prReqHdrId", prReqHdrId)
					.setParameter("businessGroupId", businessGroupId)
					.getResultList();
		}catch(Exception e){
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}

		return objectList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OsiUser> getItemAdmin(Integer businessGroupId) throws DataAccessException {
		List<OsiUser> osiUser = null;
		try{
			String query = " SELECT u from OsiUser u, OsiRespUser ru, OsiMenuEntries me WHERE u.id = ru.osiUser.id and (ru.osiResponsibilities.osiMenus.id = me.osiMenusBySubMenuId.id or ru.osiResponsibilities.osiMenus.id = me.osiMenusByMenuId.id) and me.osiFunctions.funcValue='/item' and ru.businessGroupId =:businessGroupId";
			osiUser = (List<OsiUser>) this.entityManager.createQuery(query)
					.setParameter("businessGroupId", businessGroupId)
					.getResultList();
		}catch(NoResultException e){
			e.printStackTrace();
			throw new DataAccessException("ERR_1002", e.getMessage()); 
		}
		catch(Exception e){
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}
		return osiUser;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllTransactionsForAdmin(String trxType, String headerNumber, Integer businessGroupId) throws DataAccessException {
		List<Object[]> objectList = null;
		try{
			if(trxType.equals("PR")){
				String headerNoQueryParam = "";
				if(headerNumber!=null && !headerNumber.equals("")){
					headerNoQueryParam = " and UPPER(prh.reqNumber) like '%"+headerNumber.toUpperCase().trim()+"%'";
				}
				String query = " select prh.prReqHdrId, prh.reqNumber, 'PR' as trxType, prh.docStatus, usr.firstName + ' ' + usr.lastName as creatorName "+
								" from OsiPrReqHeader prh, OsiPrReqLines prl, OsiUser usr where prh.createdBy = usr.userId and prh.businessGroupId = :businessGroupId and usr.businessGroupId = :businessGroupId and prh.docStatus in ('COMPLETED','WIP','CFLD') and prl.osiPrReqHeader.prReqHdrId = prh.prReqHdrId and prl.reqLineStatus in ('COMPLETED','WIP') "
								+ " "+headerNoQueryParam+" group by prh.prReqHdrId, prh.reqNumber, prh.docStatus, usr.firstName , usr.lastName ";
				objectList = (List<Object[]>) this.entityManager.createQuery(query)
						.setParameter("businessGroupId", businessGroupId)
						.getResultList();
			}
			if(trxType.equals("PO")){
				String headerNoQueryParam = "";
				if(headerNumber!=null && !headerNumber.equals("")){
					headerNoQueryParam = " and UPPER(poh.poNumber) like '%"+headerNumber.toUpperCase().trim()+"%'";
				}
				String query = "select poh.poReqHdrId, poh.poNumber, 'PO' as trxType, poh.docStatus, usr.firstName + ' ' + usr.lastName as creatorName "+
								" from OsiPoReqHeader poh, OsiPoReqLines pol, OsiUser usr where poh.buyerId = usr.userId and poh.businessGroupId = :businessGroupId and usr.businessGroupId = :businessGroupId  and poh.docStatus in ('COMPLETED','WIP','CFLD') and pol.osiPoReqHeader.poReqHdrId = poh.poReqHdrId and pol.poLineStatus in ('COMPLETED','WIP') "
								+ " "+headerNoQueryParam+" group by poh.poReqHdrId, poh.poNumber, poh.docStatus, usr.firstName, usr.lastName  ";
				objectList = (List<Object[]>) this.entityManager.createQuery(query)
						.setParameter("businessGroupId", businessGroupId)
						.getResultList();
			}
			if(trxType.equals("PI")){
				String headerNoQueryParam = "";
				if(headerNumber!=null && !headerNumber.equals("")){
					headerNoQueryParam = " and UPPER(pih.phyInvName) like '%"+headerNumber.toUpperCase().trim()+"%'";
				}
				String query = " select pih.phyInvHdrId, pih.phyInvName, 'PI' as trxType, pih.phyInvStatus, usr.firstName + ' ' + usr.lastName as creatorName "+
						" from OsiPhyInvHeader pih, OsiPhyInvLines pil, OsiUser usr where pih.createdBy=usr.userId and pih.businessGroupId = :businessGroupId and usr.businessGroupId = :businessGroupId and pih.phyInvStatus in ('COMPLETED','WIP','CFLD') and pil.osiPhyInvHeader.phyInvHdrId = pih.phyInvHdrId and pil.phyInvLineStatus in ('COMPLETED','WIP') "
						+ " "+headerNoQueryParam+" group by pih.phyInvHdrId, pih.phyInvName, pih.phyInvStatus, usr.firstName, usr.lastName";
				objectList = (List<Object[]>) this.entityManager.createQuery(query)
						.setParameter("businessGroupId", businessGroupId)
						.getResultList();
			}
			if(trxType.equals("IR")){
				String headerNoQueryParam = "";
				if(headerNumber!=null && !headerNumber.equals("")){
					headerNoQueryParam = " and UPPER(iih.issReqNumber) like '%"+headerNumber.toUpperCase().trim()+"%'";
				}
				String query = " select iih.issReqHdrId, iih.issReqNumber, 'IR' as trxType, iih.issReqHdrStatus, usr.firstName + ' ' + usr.lastName as creatorName "+
								" from OsiItemIssueHeader iih, OsiItemIssueLines iil, OsiUser usr where iih.createdBy=usr.userId and iih.businessGroupId = :businessGroupId and usr.businessGroupId = :businessGroupId and iih.issReqHdrStatus in ('COMPLETED','WIP','CFLD') and iil.osiItemIssueHeader.issReqHdrId = iih.issReqHdrId and iil.issReqHdrStatus in ('COMPLETED','WIP') "
								+ " "+headerNoQueryParam+" group by iih.issReqHdrId, iih.issReqNumber,  iih.issReqHdrStatus, usr.firstName, usr.lastName  ";
				objectList = (List<Object[]>) this.entityManager.createQuery(query)
						.setParameter("businessGroupId", businessGroupId)
						.getResultList();
			}
		}catch(NoResultException e){
			e.printStackTrace();
			throw new DataAccessException("ERR_1002", e.getMessage()); 
		}
		catch(Exception e){
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}
		return objectList;
	}

	@Override
	public int resetTransaction(String notificationObject, Integer hdrId,
			List<Integer> prLineIds, String notificationAction, Integer userId,
			Integer businessGroupId) throws DataAccessException {
		int result = 0;
		try{
			if(notificationObject.equals("PR")){
				String lineUpdateQuery = " update OsiPrReqLines l set l.reqLineStatus= :reqLineStatus, l.updatedBy = :updatedBy, "+
						" l.updatedDate = :updatedDate where l.osiPrReqHeader.prReqHdrId = :prReqHdrId and l.prReqLineId in (:prReqLineIds) and l.businessGroupId = :businessGroupId ";
				int count1 = this.entityManager.createQuery(lineUpdateQuery)
						  .setParameter("reqLineStatus", notificationAction)
						  .setParameter("updatedBy", userId)
						  .setParameter("updatedDate", new Date())
						  .setParameter("prReqHdrId",hdrId)
						  .setParameter("businessGroupId", businessGroupId)
						  .setParameter("prReqLineIds",prLineIds)
				          .executeUpdate();
				
				String query = " select prh from OsiPrReqHeader prh where prh.businessGroupId = :businessGroupId and prh.prReqHdrId = :prReqHdrId";
				List<OsiPrReqHeader> osiPrReqHeaderList = (List<OsiPrReqHeader>) this.entityManager.createQuery(query)
						.setParameter("prReqHdrId",hdrId)
						.setParameter("businessGroupId", businessGroupId)
						.getResultList();
				if(osiPrReqHeaderList!=null){
					OsiPrReqHeader osiPrReqHeader = osiPrReqHeaderList.get(0);
					Set<String> statuses = new HashSet<String>();
					for(OsiPrReqLines lines: osiPrReqHeader.getOsiPrReqLinesSet()){
						statuses.add(lines.getReqLineStatus());
					}
					String headerStatus = "";
					if(statuses.size()==1){
						for(String status : statuses){
							headerStatus= status;
						}
					}else{
						headerStatus = "CFLD";
					}
					
					String headerUpdateQuery = " update OsiPrReqHeader h set h.docStatus= :docStatus "
							+ " where h.prReqHdrId = :prReqHdrId and h.businessGroupId = :businessGroupId ";
					int count2 = this.entityManager.createQuery(headerUpdateQuery)
							  .setParameter("docStatus", headerStatus)
							  .setParameter("prReqHdrId",hdrId)
							  .setParameter("businessGroupId", businessGroupId)
					          .executeUpdate();
					
					String notificationUpdateQuery = " update OsiNotifications n set n.notificationStatus = 'CLOSED', n.notificationAction = 'RESET', "+
							 " n.notificationActionDate = :actionDate where n.notificationObjectHdrId = :hdrId and n.notificationObject = :notificationObject "+
							 " and n.notificationObjectId in (:notificationObjectIds) and n.notificationStatus = 'OPEN' and n.businessGroupId = :businessGroupId ";
					int count3 = this.entityManager.createQuery(notificationUpdateQuery)
							  .setParameter("notificationObject", notificationObject)
							  .setParameter("actionDate", new Date())
							  .setParameter("hdrId",hdrId)
							  .setParameter("businessGroupId", businessGroupId)
							  .setParameter("notificationObjectIds",prLineIds)
					          .executeUpdate();
					result = count1;
				}
			}
			if(notificationObject.equals("PO")){
				String lineUpdateQuery = " update OsiPoReqLines l set l.poLineStatus= :poLineStatus, l.updatedBy = :updatedBy, "+
						" l.updatedDate = :updatedDate where l.osiPoReqHeader.poReqHdrId = :poReqHdrId and l.poReqLineId in (:poReqLineIds) and l.businessGroupId = :businessGroupId ";
				int count1 = this.entityManager.createQuery(lineUpdateQuery)
						  .setParameter("poLineStatus", notificationAction)
						  .setParameter("updatedBy", userId)
						  .setParameter("updatedDate", new Date())
						  .setParameter("poReqHdrId",hdrId)
						  .setParameter("businessGroupId", businessGroupId)
						  .setParameter("poReqLineIds",prLineIds)
				          .executeUpdate();
				
				String query = " select poh from OsiPoReqHeader poh where poh.businessGroupId = :businessGroupId and poh.poReqHdrId = :poReqHdrId";
				List<OsiPoReqHeader> osiPoReqHeaderList = (List<OsiPoReqHeader>) this.entityManager.createQuery(query)
						.setParameter("poReqHdrId",hdrId)
						.setParameter("businessGroupId", businessGroupId)
						.getResultList();
				if(osiPoReqHeaderList!=null){
					OsiPoReqHeader osiPoReqHeader = osiPoReqHeaderList.get(0);
					Set<String> statuses = new HashSet<String>();
					for(OsiPoReqLines lines: osiPoReqHeader.getOsiPoReqLineses()){
						statuses.add(lines.getPoLineStatus());
					}
					String headerStatus = "";
					if(statuses.size()==1){
						for(String status : statuses){
							headerStatus= status;
						}
					}else{
						headerStatus = "CFLD";
					}
					
					String headerUpdateQuery = " update OsiPoReqHeader h set h.docStatus= :docStatus "
							+ " where h.poReqHdrId = :poReqHdrId and h.businessGroupId = :businessGroupId ";
					int count2 = this.entityManager.createQuery(headerUpdateQuery)
							  .setParameter("docStatus", headerStatus)
							  .setParameter("poReqHdrId",hdrId)
							  .setParameter("businessGroupId", businessGroupId)
					          .executeUpdate();
					
					String notificationUpdateQuery = " update OsiNotifications n set n.notificationStatus = 'CLOSED', n.notificationAction = 'RESET', "+
							 " n.notificationActionDate = :actionDate where n.notificationObjectHdrId = :hdrId and n.notificationObject = :notificationObject "+
							 " and n.notificationObjectId in (:notificationObjectIds) and n.notificationStatus = 'OPEN' and n.businessGroupId = :businessGroupId ";
					int count3 = this.entityManager.createQuery(notificationUpdateQuery)
							  .setParameter("notificationObject", notificationObject)
							  .setParameter("actionDate", new Date())
							  .setParameter("hdrId",hdrId)
							  .setParameter("businessGroupId", businessGroupId)
							  .setParameter("notificationObjectIds",prLineIds)
					          .executeUpdate();
					result = count1;
				}
			}
			if(notificationObject.equals("PI")){
				String lineUpdateQuery = " update OsiPhyInvLines l set l.phyInvLineStatus= :phyInvLineStatus, l.updatedBy = :updatedBy, "+
						" l.updatedDate = :updatedDate where l.osiPhyInvHeader.phyInvHdrId = :phyInvHdrId and l.phyInvLinesId in (:phyInvLinesIds) and l.businessGroupId = :businessGroupId ";
				int count1 = this.entityManager.createQuery(lineUpdateQuery)
						  .setParameter("phyInvLineStatus", notificationAction)
						  .setParameter("updatedBy", userId)
						  .setParameter("updatedDate", new Date())
						  .setParameter("phyInvHdrId",hdrId)
						  .setParameter("businessGroupId", businessGroupId)
						  .setParameter("phyInvLinesIds",prLineIds)
				          .executeUpdate();
				
				String query = " select pih from OsiPhyInvHeader pih where pih.businessGroupId = :businessGroupId and pih.phyInvHdrId = :phyInvHdrId";
				List<OsiPhyInvHeader> osiPhyInvHeaderList = (List<OsiPhyInvHeader>) this.entityManager.createQuery(query)
						.setParameter("phyInvHdrId",hdrId)
						.setParameter("businessGroupId", businessGroupId)
						.getResultList();
				if(osiPhyInvHeaderList!=null){
					OsiPhyInvHeader osiPhyInvHeader = osiPhyInvHeaderList.get(0);
					Set<String> statuses = new HashSet<String>();
					for(OsiPhyInvLines lines: osiPhyInvHeader.getOsiPhyInvLinesSet()){
						statuses.add(lines.getPhyInvLineStatus());
					}
					String headerStatus = "";
					if(statuses.size()==1){
						for(String status : statuses){
							headerStatus= status;
						}
					}else{
						headerStatus = "CFLD";
					}
					
					String headerUpdateQuery = " update OsiPhyInvHeader h set h.phyInvStatus= :phyInvStatus "
							+ " where h.phyInvHdrId = :phyInvHdrId and h.businessGroupId = :businessGroupId ";
					int count2 = this.entityManager.createQuery(headerUpdateQuery)
							  .setParameter("phyInvStatus", headerStatus)
							  .setParameter("phyInvHdrId",hdrId)
							  .setParameter("businessGroupId", businessGroupId)
					          .executeUpdate();
					
					String notificationUpdateQuery = " update OsiNotifications n set n.notificationStatus = 'CLOSED', n.notificationAction = 'RESET', "+
							 " n.notificationActionDate = :actionDate where n.notificationObjectHdrId = :hdrId and n.notificationObject = :notificationObject "+
							 " and n.notificationObjectId in (:notificationObjectIds) and n.notificationStatus = 'OPEN' and n.businessGroupId = :businessGroupId ";
					int count3 = this.entityManager.createQuery(notificationUpdateQuery)
							  .setParameter("notificationObject", notificationObject)
							  .setParameter("actionDate", new Date())
							  .setParameter("hdrId",hdrId)
							  .setParameter("businessGroupId", businessGroupId)
							  .setParameter("notificationObjectIds",prLineIds)
					          .executeUpdate();
					result = count1;
				}
			}
			if(notificationObject.equals("IR")){
				String lineUpdateQuery = " update OsiItemIssueLines l set l.issReqHdrStatus= :issReqHdrStatus, l.updatedBy = :updatedBy, "+
						" l.updatedDate = :updatedDate where l.osiItemIssueHeader.issReqHdrId = :issReqHdrId and l.issReqLinesId in (:issReqLinesIds) and l.businessGroupId = :businessGroupId ";
				int count1 = this.entityManager.createQuery(lineUpdateQuery)
						  .setParameter("issReqHdrStatus", notificationAction)
						  .setParameter("updatedBy", userId)
						  .setParameter("updatedDate", new Date())
						  .setParameter("issReqHdrId",hdrId)
						  .setParameter("businessGroupId", businessGroupId)
						  .setParameter("issReqLinesIds",prLineIds)
				          .executeUpdate();
				
				String query = " select ih from OsiItemIssueHeader ih where ih.businessGroupId = :businessGroupId and ih.issReqHdrId = :issReqHdrId";
				List<OsiItemIssueHeader> osiItemIssueHeaderList = (List<OsiItemIssueHeader>) this.entityManager.createQuery(query)
						.setParameter("issReqHdrId",hdrId)
						.setParameter("businessGroupId", businessGroupId)
						.getResultList();
				if(osiItemIssueHeaderList!=null){
					OsiItemIssueHeader osiItemIssueHeader = osiItemIssueHeaderList.get(0);
					Set<String> statuses = new HashSet<String>();
					for(OsiItemIssueLines lines: osiItemIssueHeader.getOsiItemIssueLinesSet()){
						statuses.add(lines.getIssReqHdrStatus());
					}
					String headerStatus = "";
					if(statuses.size()==1){
						for(String status : statuses){
							headerStatus= status;
						}
					}else{
						headerStatus = "CFLD";
					}
					
					String headerUpdateQuery = " update OsiItemIssueHeader h set h.issReqHdrStatus= :issReqHdrStatus "
							+ " where h.issReqHdrId = :issReqHdrId and h.businessGroupId = :businessGroupId ";
					int count2 = this.entityManager.createQuery(headerUpdateQuery)
							  .setParameter("issReqHdrStatus", headerStatus)
							  .setParameter("issReqHdrId",hdrId)
							  .setParameter("businessGroupId", businessGroupId)
					          .executeUpdate();
					
					String notificationUpdateQuery = " update OsiNotifications n set n.notificationStatus = 'CLOSED', n.notificationAction = 'RESET', "+
							 " n.notificationActionDate = :actionDate where n.notificationObjectHdrId = :hdrId and n.notificationObject = :notificationObject "+
							 " and n.notificationObjectId in (:notificationObjectIds) and n.notificationStatus = 'OPEN' and n.businessGroupId = :businessGroupId ";
					int count3 = this.entityManager.createQuery(notificationUpdateQuery)
							  .setParameter("notificationObject", "MR")
							  .setParameter("actionDate", new Date())
							  .setParameter("hdrId",hdrId)
							  .setParameter("businessGroupId", businessGroupId)
							  .setParameter("notificationObjectIds",prLineIds)
					          .executeUpdate();
					result = count1;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new DataAccessException("ERR_1000", e.getMessage()); 
		}
		return result;
	}

}
