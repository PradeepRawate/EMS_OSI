package com.osi.fas.configuration.repository.custom;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.osi.fas.domain.OsiSegmentStructureDetails;
import com.osi.fas.domain.OsiSegmentStructureHdr;
import com.osi.urm.exception.DataAccessException;
import com.osi.urm.service.dto.OsiUserDTO;

/**
 * Spring Data JPA repository for the OsiSegmentStructureHdr entity.
 */
public interface OsiSegmentStructureHdrRepositoryCustom {
	public List<OsiSegmentStructureDetails> getSegmentStructureValues(String structureName, Integer businessGroupId) throws DataAccessException;
	OsiSegmentStructureHdr updateOsiSegmentStructureHdr(OsiSegmentStructureHdr osiSegmentStructureHdr) throws DataAccessException;
	int deleteSegmentStructureHdr(List<Integer> segmentStructureHdrIds, OsiUserDTO user) throws DataAccessException;
	public List<OsiSegmentStructureHdr> findAllOsiSegmentStructureHdr(@Param("businessGroupId") Integer businessGroupId)  throws DataAccessException;
	public List<OsiSegmentStructureHdr> searchOsiSegmentStructureHdr(String queryParam,Integer businessGroupId) throws DataAccessException;
	public Long isCategoryCreatedWithThisStucture(Integer segmentHdrId,Integer businessGroupId) throws DataAccessException;
	public Long isCoaCreatedWithThisStucture(Integer segmentHdrId,Integer businessGroupId) throws DataAccessException;
}
