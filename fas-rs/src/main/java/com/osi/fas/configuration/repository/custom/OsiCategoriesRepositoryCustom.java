package com.osi.fas.configuration.repository.custom;

import java.util.List;

import com.osi.fas.domain.OsiCategories;
import com.osi.urm.domain.OsiLookupValues;
import com.osi.urm.exception.DataAccessException;

public interface OsiCategoriesRepositoryCustom{
	
	public Integer updateCategory(OsiCategories osiCategories) throws DataAccessException;
	public Integer deleteCategories(List<Integer> categoryIds, Integer businessGroupId, Integer userId) throws DataAccessException;
	public Integer findCategoriesByCodeId(Integer categoryId,String categoryCode, Integer businessGroupId) throws DataAccessException;
	public Integer findCategoriesByNameId(Integer categoryId,String categoryName, Integer businessGroupId) throws DataAccessException;
	public Integer findCategoriesByCode(String categoryCode,  Integer businessGroupId) throws DataAccessException;
	public Integer findCategoriesByName(String categoryName, Integer businessGroupId) throws DataAccessException;
	public List<OsiCategories> searchCategories(String queryParam, Integer businessGroupId) throws DataAccessException;
	public OsiCategories validateCategoryCode(String categoryCode, Integer businessGroupId) throws DataAccessException;
	public List<OsiLookupValues> getCategoryNextSegmentValues(String code, Integer seq, Integer businessGroupId) throws DataAccessException;
}
