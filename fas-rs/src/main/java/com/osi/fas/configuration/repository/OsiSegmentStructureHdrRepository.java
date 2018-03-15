package com.osi.fas.configuration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.osi.fas.configuration.repository.custom.OsiSegmentStructureHdrRepositoryCustom;
import com.osi.fas.domain.OsiKeyFlex;
import com.osi.fas.domain.OsiSegmentStructureHdr;

/**
 * Spring Data JPA repository for the OsiSegmentStructureHdr entity.
 */
@SuppressWarnings("unused")
public interface OsiSegmentStructureHdrRepository
		extends JpaRepository<OsiSegmentStructureHdr, Long>, OsiSegmentStructureHdrRepositoryCustom {
	
	@Query("SELECT ossh.segmentStructureHdrId FROM OsiSegmentStructureHdr ossh where ossh.segmentStructureHdrDesc=:segmentStructureHdrDesc")
	public Integer findOneSegmentStructureHdrId(
			@Param("segmentStructureHdrDesc") String segmentStructureHdrDesc);

	/*@Query("SELECT ossh FROM OsiSegmentStructureHdr ossh where ossh.active = 1 AND ossh.businessGroupId = :businessGroupId ORDER BY createdDate asc")
	public List<OsiSegmentStructureHdr> findAllOsiSegmentStructureHdr(
			@Param("businessGroupId") Integer businessGroupId);*/

	@Query("SELECT ossh FROM OsiSegmentStructureHdr ossh where ossh.active=1 AND ossh.businessGroupId = :businessGroupId AND ossh.segmentStructureHdrId = :segmentStructureHdrId ")
	public OsiSegmentStructureHdr findOneSegmentStructureHdr(
			@Param("segmentStructureHdrId") Integer segmentStructureHdrId,
			@Param("businessGroupId") Integer businessGroupId);

	@Query("SELECT ossh.id FROM OsiSegmentStructureHdr ossh where ossh.active = 1 AND ossh.businessGroupId = :businessGroupId AND UPPER(ossh.segmentStructureHdrDesc)=:segmentStructureHdrDesc")
	public Integer validateUniqueSegmentStructureHdrDesc(@Param("segmentStructureHdrDesc") String segmentStructureHdrDesc,
			@Param("businessGroupId") Integer businessGroupId);
	
	@Query("SELECT kf FROM OsiKeyFlex kf where kf.value not in( select segmentStructureHdrDesc from OsiSegmentStructureHdr where businessGroupId = :businessGroupId and segmentStructureHdrId!=:segmentStructureHdrId) and kf.businessGroupId=:businessGroupId order by kf.name")
	public List<OsiKeyFlex> getRemainingKeyFlexFields(@Param("segmentStructureHdrId") Integer segmentStructureHdrId, @Param("businessGroupId") Integer businessGroupId);
	

	//public OsiSegmentStructureHdr findBySegmentStructureHdrDesc(Integer segmentStructureHdrId);

}
