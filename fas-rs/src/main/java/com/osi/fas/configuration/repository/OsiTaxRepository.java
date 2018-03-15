package com.osi.fas.configuration.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.osi.fas.configuration.repository.custom.OsiTaxRepositoryCustom;
import com.osi.fas.domain.OsiCoa;
import com.osi.fas.domain.OsiTax;
import com.osi.urm.exception.DataAccessException;

public interface OsiTaxRepository extends JpaRepository<OsiTax,Integer>, OsiTaxRepositoryCustom {
	
	/*@Query("SELECT ot FROM OsiTax ot where ot.businessGroupId = :businessGroupId order by ot.updatedDate desc")
	public List<OsiTax> findAllOsiTax(@Param("businessGroupId") Integer businessGroupId);*/
	List<OsiTax> findTaxesByBusinessGroupIdOrderByUpdatedDateDesc(Integer businessGroupId,Pageable pageObject) throws DataAccessException;
	
	@Query("SELECT ot FROM OsiTax ot where ot.businessGroupId = :businessGroupId and ot.active=1 order by ot.updatedDate desc")
	public List<OsiTax> findAllActiveOsiTax(@Param("businessGroupId") Integer businessGroupId);

	@Query("SELECT count(*) FROM OsiItem i, OsiItemApplicableTax it where i.itemId=it.osiItem.itemId AND i.businessGroupId = :businessGroupId AND it.businessGroupId = :businessGroupId AND i.active = 1 AND it.osiTax.taxId= :taxId ")
	public Integer isTaxInactivable(@Param("taxId") Integer id,@Param("businessGroupId") Integer businessGroupId) throws DataAccessException;
	
	
	/*@Query("SELECT ot.taxCode FROM OsiTax ot where ot.businessGroupId = :businessGroupId AND UPPER(ot.taxCode)=:taxCode")
    public List<Integer> validateTaxCode(@Param("taxCode") String uomCode,@Param("businessGroupId") Integer businessGroupId);
	
	@Query("SELECT ot.taxName FROM OsiTax ot where ot.businessGroupId = :businessGroupId AND UPPER(ot.taxName)=:taxName")
    public List<Integer> validateTaxName(@Param("taxName") String uomName,@Param("businessGroupId") Integer businessGroupId);*/
	
}
