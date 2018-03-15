package com.osi.fas.budget.web.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.osi.fas.budget.service.OsiBudgetTemplateService;
import com.osi.fas.minmax.service.OsiMinMaxTemplateService;
import com.osi.fas.service.dto.OsiBudgetTemplateResponseDTO;
import com.osi.fas.service.dto.OsiMinMaxFileTemplateResponseDTO;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.exception.RestServiceException;
import com.osi.urm.security.util.AuthTokenStore;
import com.osi.urm.security.util.AuthorizationToken;

/**
 * 
 * @author sparashara
 *
 */
@RestController
@RequestMapping("/api")
public class OsiBudgetTemplateResource {

	@Autowired
	private OsiBudgetTemplateService osiBudgetTemplateService;

	@Autowired
	private AuthTokenStore authTokenStore;

	@Autowired
	private Environment env;

	@Autowired
	AppConfig appConfig;

	/**
	 * 
	 * @param file
	 * @param authToken
	 * @return
	 * @throws RestServiceException
	 */
	@PostMapping("/upload-budget")
	public ResponseEntity<OsiBudgetTemplateResponseDTO> budgetTemplateUpload(MultipartFile file,
			@Valid @RequestHeader(value = AppConfig.AUTH_TOKEN) String authToken) throws RestServiceException {
		OsiBudgetTemplateResponseDTO response = null;
		AuthorizationToken auth = authTokenStore.retrieveToken(authToken);
		Integer createdId = auth.getOsiUserDTO().getId();	
		try {
			response = osiBudgetTemplateService.budgetUpload(file,createdId);
		} catch (BusinessException e) {
			throw new RestServiceException(HttpStatus.BAD_REQUEST.value(), env.getProperty(e.getErrorCode()),
					e.getErrorCode(), e.getSystemMessage());
		}
		return new ResponseEntity<>(response != null ? response : new OsiBudgetTemplateResponseDTO(),
				HttpStatus.OK);
	}

}
