package com.osi.fas.configuration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.osi.fas.configuration.repository.custom.OsiNotificationsRepositoryCustom;
import com.osi.fas.domain.OsiNotifications;
import com.osi.fas.domain.OsiPrReqHeader;

public interface OsiNotificationsRepository extends JpaRepository<OsiNotifications,Integer>,OsiNotificationsRepositoryCustom {
	
	/*@Query(" SELECT notificationId, workFlowUrl, reqNumber, requestType, docStatus, prReqHdrId, fundsStatus FROM OsiNotifications n, OsiPrReqHeader rh WHERE n.notificationObjectId = rh.prReqHdrId AND n.notificationUserId=:notificationUserId AND n.notificationStatus='OPEN' AND n.businessGroupId = rh.businessGroupId AND n.businessGroupId = :businessGroupId ")
    public List<OsiPrReqHeader> findAllNotifications(@Param("notificationUserId") Integer notificationUserId, @Param("businessGroupId") Integer businessGroupId);*/
	
	@Query(" SELECT rl.prReqLineId "+
	" FROM OsiNotifications n, OsiPrReqHeader rh, OsiPrReqLines rl "+
	" WHERE n.notificationObjectId = rl.prReqLineId "+
	" and rl.osiPrReqHeader.prReqHdrId=rh.prReqHdrId "+
	" AND n.notificationUserId= :notificationUserId "+
	" AND rh.prReqHdrId= :prReqHdrId AND n.businessGroupId = rh.businessGroupId "+ 
	" AND n.businessGroupId = :businessGroupId AND n.notificationObject='PR'  AND n.notificationStatus='OPEN' ")
		public List<Integer> getNotificationPrlineIds(@Param("prReqHdrId") Integer prReqHdrId,@Param("notificationUserId") Integer notificationUserId,@Param("businessGroupId") Integer businessGroupId);

	@Query(" SELECT ol.poReqLineId "+
	" FROM OsiNotifications n, OsiPoReqHeader oh, OsiPoReqLines ol "+
	" WHERE n.notificationObjectId = ol.poReqLineId "+
	" and ol.osiPoReqHeader.poReqHdrId=oh.poReqHdrId "+
	" AND n.notificationUserId= :notificationUserId "+
	" AND oh.poReqHdrId= :poReqHdrId AND n.businessGroupId = oh.businessGroupId "+ 
	" AND n.businessGroupId = :businessGroupId AND n.notificationObject='PO'  AND n.notificationStatus='OPEN' ")
		public List<Integer> getNotificationPolineIds(@Param("poReqHdrId") Integer poReqHdrId,@Param("notificationUserId") Integer notificationUserId,@Param("businessGroupId") Integer businessGroupId);

}

