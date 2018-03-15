package com.osi.fas.budget.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.osi.fas.budget.repository.custom.OsiBudgetTemplateRepositoryCustom;
import com.osi.fas.budget.service.OsiBudgetTemplateService;
import com.osi.fas.service.dto.OsiBudgetTemplateResponseDTO;
import com.osi.urm.domain.OsiUser;
import com.osi.urm.exception.BusinessException;
import com.osi.urm.repository.OsiUserRepository;

@Service
@Transactional
public class OsiBudgetTemplateServiceImpl implements OsiBudgetTemplateService {
	private final Logger log = LoggerFactory.getLogger(OsiBudgetTemplateServiceImpl.class);
	
	@Autowired
	private OsiBudgetTemplateRepositoryCustom osiBudgetTemplateRepositoryCustom;
	
	@Autowired
	private OsiUserRepository osiUserRepository;

	@Value("${spring.deptCode}")
	String deptCodeReference;
	
	
	/**
	 * 
	 */
	public OsiBudgetTemplateResponseDTO budgetUpload(MultipartFile file, Integer createdBy) throws BusinessException {
		OsiBudgetTemplateResponseDTO dto = null;
		try {
			if (!file.isEmpty()) {
				XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());
				XSSFSheet worksheet = wb.getSheetAt(0);
				Iterator<Row> objIterator = worksheet.rowIterator();
				dto = new OsiBudgetTemplateResponseDTO("File uploaded successfully");
				
				while (objIterator.hasNext()) {
					Row row = objIterator.next();
					if (row.getRowNum() >= 1) {
						if (!processBudgetTemplateInfo(row,worksheet,createdBy)) {
							dto.setStatus("File has been processed with errors");
						}
					}
				}
				if(null != wb){
					wb.close();
				}
			}

		} catch (Exception e) {
			throw new BusinessException("ERR_1073", "File uploading failed", e);
		}
		return dto != null ? dto : new OsiBudgetTemplateResponseDTO();
	}

	/**
	 * 
	 * @param row
	 * @param evaluator
	 * @return
	 * @throws BusinessException
	 */
	private Boolean processBudgetTemplateInfo(Row row, XSSFSheet worksheet,Integer createdBy) throws BusinessException {
		
			Boolean rowUploadSuccessful = false;
			Boolean hasValidationErrors = false;
			
			String budgetType = null;
			String periodName = null;
			Integer periodYear = null;
			Integer periodNum = null;
			String glCompanyCode = null;
			String glBranchCode = null;
			String glDeptCode = null;
			String glNaturalAcct = null;
			String glReserve1 = null;
			String glReserve2 = null;
			String glReserve3 = null;
			String glReserve4 = null;
			BigDecimal budgetAmount = null;
			BigDecimal availedAmount = null;
			BigDecimal savingsAmount = null;
			String budgetStatus = null;
			String budgetFlag = null;
			Integer lastUpdatedBy = createdBy;
			
		try {
			try{
				row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
				budgetType = row.getCell(0).getStringCellValue();
				
				row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
				periodName = row.getCell(1).getStringCellValue();
				
				row.getCell(2).setCellType(Cell.CELL_TYPE_NUMERIC);
				periodYear = new BigDecimal(String.valueOf(row.getCell(2).getNumericCellValue())).intValue();
				
				row.getCell(3).setCellType(Cell.CELL_TYPE_NUMERIC);
				periodNum = new BigDecimal(String.valueOf(row.getCell(3).getNumericCellValue())).intValue();
				
				row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
				glCompanyCode = row.getCell(4).getStringCellValue();
				
				row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
				glBranchCode = row.getCell(5).getStringCellValue();
				
				row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
				glDeptCode = row.getCell(6).getStringCellValue();
				
				row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
				glNaturalAcct = row.getCell(7).getStringCellValue();
				
				row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
				glReserve1 = row.getCell(8).getStringCellValue();
				
				row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
				glReserve2 = row.getCell(9).getStringCellValue();
				
				row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
				glReserve3 = row.getCell(10).getStringCellValue();
				
				row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
				glReserve4 = row.getCell(11).getStringCellValue();
				
				row.getCell(12).setCellType(Cell.CELL_TYPE_NUMERIC);
				budgetAmount = new BigDecimal(String.valueOf(row.getCell(12).getNumericCellValue()));
				
				row.getCell(13).setCellType(Cell.CELL_TYPE_NUMERIC);
				availedAmount = new BigDecimal(String.valueOf(row.getCell(13).getNumericCellValue()));
				
				row.getCell(14).setCellType(Cell.CELL_TYPE_NUMERIC);
				savingsAmount = new BigDecimal(String.valueOf(row.getCell(14).getNumericCellValue()));
				
				row.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
				budgetStatus = row.getCell(15).getStringCellValue();
				
				row.getCell(16).setCellType(Cell.CELL_TYPE_STRING);
				budgetFlag = row.getCell(16).getStringCellValue();
				//lastUpdateDate = new SimpleDateFormat("MM/dd/yyyy").format(row.getCell(17).getDateCellValue());
				//lastUpdatedBy = row.getCell(18).getStringCellValue();
				
				if(budgetAmount.doubleValue() == 0.00 || periodYear < 1900 || periodNum == 0
						|| periodYear > 2200 || periodNum > 12
						|| null == glCompanyCode || glCompanyCode.equalsIgnoreCase("")
						|| null == glBranchCode || glBranchCode.equalsIgnoreCase("")
						|| null == glDeptCode || glDeptCode.equalsIgnoreCase("")){
					
					hasValidationErrors = true;
				}
				
			}catch(Exception e){
				hasValidationErrors = true;
			}

			if(!hasValidationErrors){
				osiBudgetTemplateRepositoryCustom.saveBudgetTemplateInfo(budgetType,
						periodName,periodYear,periodNum,glCompanyCode,
						glBranchCode,glDeptCode,glNaturalAcct,glReserve1,
						glReserve2,glReserve3,glReserve4,budgetAmount,
						availedAmount,savingsAmount,budgetStatus,
						budgetFlag,null,lastUpdatedBy,deptCodeReference); //Should trigger a stored precedure	
			}
			
			rowUploadSuccessful = true;

		} catch (Exception e) {
			throw new BusinessException("ERR_1073", "File uploading failed", e);
		}

		return rowUploadSuccessful;
	}
}
