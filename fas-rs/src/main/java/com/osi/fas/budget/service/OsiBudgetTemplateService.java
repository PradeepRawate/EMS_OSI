package com.osi.fas.budget.service;

import org.springframework.web.multipart.MultipartFile;

import com.osi.fas.service.dto.OsiBudgetTemplateResponseDTO;
import com.osi.urm.exception.BusinessException;

public interface OsiBudgetTemplateService {

	OsiBudgetTemplateResponseDTO budgetUpload(MultipartFile file, Integer createdBy) throws BusinessException;

}
