package com.osi.fas.configuration.mapper.impl;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.osi.fas.common.CommonService;
import com.osi.fas.configuration.mapper.OsiCategoriesMapper;
import com.osi.fas.configuration.mapper.OsiItemMapper;
import com.osi.fas.domain.OsiApprovedSuppliers;
import com.osi.fas.domain.OsiInvOrgItemPrice;
import com.osi.fas.domain.OsiInvOrgItems;
import com.osi.fas.domain.OsiInventoryOrg;
import com.osi.fas.domain.OsiItem;
import com.osi.fas.domain.OsiItemApplicableTax;
import com.osi.fas.domain.OsiItemImage;
import com.osi.fas.domain.OsiTax;
import com.osi.fas.domain.OsiUomConversion;
import com.osi.fas.service.dto.OsiApprovedSuppliersDTO;
import com.osi.fas.service.dto.OsiInvOrgItemPriceDTO;
import com.osi.fas.service.dto.OsiInvOrgItemsDTO;
import com.osi.fas.service.dto.OsiItemApplicableTaxDTO;
import com.osi.fas.service.dto.OsiItemDTO;
import com.osi.fas.service.dto.OsiItemImageDTO;
import com.osi.fas.service.dto.OsiTaxDTO;
import com.osi.fas.service.dto.OsiUomConversionDTO;
import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;
@Component
public class OsiItemMapperImpl implements OsiItemMapper {
	
	@Autowired
	private OsiCategoriesMapper osiCategoriesMapper;
	@Autowired
	private AppConfig appConfig;
	@Override
	public OsiItemDTO osiItemToOsiItemDTO(OsiItem osiItem) {
		OsiItemDTO osiItemDTO= new OsiItemDTO();
		osiItemDTO.setItemId(osiItem.getItemId());
		osiItemDTO.setItemCode(osiItem.getItemCode());
		osiItemDTO.setItemName(osiItem.getItemName());
		osiItemDTO.setItemDescription(osiItem.getItemDescription());
		osiItemDTO.setItemLongDescription(osiItem.getItemLongDescription());
		osiItemDTO.setPrimaryUomId(osiItem.getPrimaryUomId());
		osiItemDTO.setActive(osiItem.getActive());
		osiItemDTO.setLeadTimeInDays(osiItem.getLeadTimeInDays());
		osiItemDTO.setIsStockable(osiItem.getIsStockable());
		osiItemDTO.setMaxQuantity(osiItem.getMaxQuantity());
		osiItemDTO.setMinQuantity(osiItem.getMinQuantity());
		osiItemDTO.setOrderMultiplies(osiItem.getOrderMultiplies());
		osiItemDTO.setIsMinMaxPlanningEnabled(osiItem.getIsMinMaxPlanningEnabled());
		osiItemDTO.setOsiCategories(osiCategoriesMapper.osiCategoriesToOsiCategoriesDTO(osiItem.getOsiCategories()));
		if(osiItemDTO.getOsiCategories()!=null)
			osiItemDTO.setCategoryCode(osiItem.getOsiCategories().getCategoryCode());
		//osiItemDTO.setIsBom(osiItem.getIsBom());
		osiItemDTO.setBusinessGroupId(osiItem.getBusinessGroupId());
		osiItemDTO.setIsUsedApprovedSupplier(osiItem.getIsUsedApprovedSupplier());
		osiItemDTO.setIsPhysicalCountEnabled(osiItem.getIsPhysicalCountEnabled());
		osiItemDTO.setListPrice(osiItem.getListPrice());
		osiItemDTO.setMarketPrice(osiItem.getMarketPrice());
		
		//osiItemDTO.setTaxId(osiItem.getTaxId());
		Set<OsiItemImageDTO> osiItemImageSetDTO = new HashSet<OsiItemImageDTO>(0);
		Set<OsiInvOrgItemsDTO> osiInvOrgItemsSetDTO = new HashSet<OsiInvOrgItemsDTO>(0);
		Set<OsiInvOrgItemPriceDTO> osiInvOrgItemPriceSetDTO = new HashSet<OsiInvOrgItemPriceDTO>(0);
		Set<OsiApprovedSuppliersDTO> osiApprovedSuppliersSetDTO = new HashSet<OsiApprovedSuppliersDTO>(0);
		List<OsiUomConversionDTO> osiUomConversionSetDTO = new ArrayList<OsiUomConversionDTO>(0);
		Set<OsiItemApplicableTaxDTO> osiItemApplicableTaxDTOSet = new HashSet<OsiItemApplicableTaxDTO>(0);
		for (OsiItemImage osiItemImage : osiItem.getOsiItemImages()) {
			String filePath =appConfig.getImagePath()+File.separator+"ITEM"+File.separator+osiItemImage.getItemImage();
			OsiItemImageDTO osiItemImageDTO = new OsiItemImageDTO();
			Pattern pattern = Pattern.compile("(.*)\\.(.*)");
			Matcher m = pattern.matcher(filePath);
			if (m.find() && m.groupCount() == 2) {
			    osiItemImageDTO.setFileType(m.group(2));
			}
			try {
				osiItemImageDTO.setFileContent(new CommonService().encodeFile(filePath));
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			osiItemImageDTO.setItemImage(osiItemImage.getItemImage());
			osiItemImageSetDTO.add(osiItemImageDTO);
		}
		
		for (OsiInvOrgItems osiInvOrgItems : osiItem.getOsiInvOrgItemses()) {
			//item-inventory
			//if(osiInvOrgItems.getOsiInventoryOrg().getIsMaster()==0){
				OsiInvOrgItemsDTO osiInvOrgItemsDTO = new OsiInvOrgItemsDTO();
				osiInvOrgItemsDTO.setInvOrgId(osiInvOrgItems.getOsiInventoryOrg().getInvOrgId());
				osiInvOrgItemsDTO.setCoaId(osiInvOrgItems.getCoaId());
				osiInvOrgItemsDTO.setCoaCode(osiInvOrgItems.getCoaCode());
				
				osiInvOrgItemsDTO.setInvOrgType(osiInvOrgItems.getInvOrgType());
				osiInvOrgItemsDTO.setListPrice(osiInvOrgItems.getListPrice());
				osiInvOrgItemsDTO.setMarketPrice(osiInvOrgItems.getMarketPrice());
				osiInvOrgItemsDTO.setMinThreshold(osiInvOrgItems.getMinThreshold());
				osiInvOrgItemsDTO.setMaxThreshold(osiInvOrgItems.getMaxThreshold());
				
				osiInvOrgItemsSetDTO.add(osiInvOrgItemsDTO);
				
			//}
		}
			//prices
		/*OsiInvOrgItemPrice osiInvOrgItemPrice =osiItem.getOsiInvOrgItemPrices().iterator().next();
		OsiInvOrgItemPriceDTO osiInvOrgItemPriceDTO = new OsiInvOrgItemPriceDTO();
		osiInvOrgItemPriceDTO.setIsTaxAmtIncluded(osiInvOrgItemPrice.getIsTaxAmtIncluded());
		osiInvOrgItemPriceDTO.setItemPriceId(osiInvOrgItemPrice.getItemPriceId());
		osiInvOrgItemPriceDTO.setListPrice(osiInvOrgItemPrice.getListPrice());
		osiInvOrgItemPriceDTO.setMarketPrice(osiInvOrgItemPrice.getMarketPrice());
		osiInvOrgItemPriceDTO.setSellingPrice(osiInvOrgItemPrice.getSellingPrice());
		osiInvOrgItemPriceSetDTO.add(osiInvOrgItemPriceDTO);*/
			
		for (OsiApprovedSuppliers osiApprovedSuppliers : osiItem.getOsiApprovedSupplierses()) {
			OsiApprovedSuppliersDTO osiApprovedSuppliersDTO = new OsiApprovedSuppliersDTO();
			osiApprovedSuppliersDTO.setSupplierId(osiApprovedSuppliers.getSupplierId());
			osiApprovedSuppliersDTO.setSupplierSiteId(osiApprovedSuppliers.getSupplierSiteId());
			osiApprovedSuppliersSetDTO.add(osiApprovedSuppliersDTO);
		}
		for (OsiUomConversion osiUomConversion : osiItem.getOsiUomConversions()) {
			OsiUomConversionDTO osiUomConversionDTO = new OsiUomConversionDTO();
			osiUomConversionDTO.setBaseUomId(osiUomConversion.getBaseUomId());
			osiUomConversionDTO.setTargetUomId(osiUomConversion.getTargetUomId());
			osiUomConversionDTO.setConversion(osiUomConversion.getConversion());
			osiUomConversionDTO.setActive(osiUomConversion.getActive());
			osiUomConversionSetDTO.add(osiUomConversionDTO);
		}
		/*for (OsiItemApplicableTax osiItemApplicableTax : osiItem.getOsiItemApplicableTaxes()) {
			OsiItemApplicableTaxDTO osiItemApplicableTaxDTO = new OsiItemApplicableTaxDTO();
			OsiTaxDTO osiTaxDTO = new OsiTaxDTO();
			osiTaxDTO.setTaxId(osiItemApplicableTax.getOsiTax().getTaxId());
			osiItemApplicableTaxDTO.setOsiTax(osiTaxDTO);
			osiItemApplicableTaxDTOSet.add(osiItemApplicableTaxDTO);
		}
		osiItemDTO.setOsiItemApplicableTaxes(osiItemApplicableTaxDTOSet);*/
		osiItemDTO.setOsiUomConversionList(osiUomConversionSetDTO);
		osiItemDTO.setOsiItemImages(osiItemImageSetDTO);
		osiItemDTO.setOsiInvOrgItemses(osiInvOrgItemsSetDTO);
		//osiItemDTO.setOsiInvOrgItemPrices(osiInvOrgItemPriceSetDTO);
		osiItemDTO.setOsiApprovedSupplierses(osiApprovedSuppliersSetDTO);
		return osiItemDTO;
	}

	@Override
	public List<OsiItemDTO> osiItemListToOsiItemDTOList(List<OsiItem> osiItems) {
		if ( osiItems == null ) {
            return null;
        }

        List<OsiItemDTO> list = new ArrayList<OsiItemDTO>();
        for ( OsiItem osiItems1 : osiItems ) {
            list.add( osiItemToOsiItemDTO( osiItems1 ) );
        }

        return list;
	}

	@Override
	public OsiItem osiItemDTOToOsiItem(OsiItemDTO osiItemDTO) throws BusinessException {
		OsiItem osiItem= new OsiItem();
		osiItem.setItemId(osiItemDTO.getItemId());
		osiItem.setItemCode(osiItemDTO.getItemCode());
		osiItem.setItemName(osiItemDTO.getItemName());
		osiItem.setItemDescription(osiItemDTO.getItemDescription());
		osiItem.setItemLongDescription(osiItemDTO.getItemLongDescription());
		osiItem.setPrimaryUomId(osiItemDTO.getPrimaryUomId());
		osiItem.setActive(osiItemDTO.getActive());
		osiItem.setLeadTimeInDays(osiItemDTO.getLeadTimeInDays());
		osiItem.setIsStockable(osiItemDTO.getIsStockable());
		osiItem.setMaxQuantity(osiItemDTO.getMaxQuantity());
		osiItem.setMinQuantity(osiItemDTO.getMinQuantity());
		osiItem.setOrderMultiplies(osiItemDTO.getOrderMultiplies());
		osiItem.setIsMinMaxPlanningEnabled(osiItemDTO.getIsMinMaxPlanningEnabled());
		osiItem.setListPrice(osiItemDTO.getListPrice());
		osiItem.setMarketPrice(osiItemDTO.getMarketPrice());
		osiItem.setOperatingUnitId(osiItemDTO.getOperatingUnitId());
		
		
		osiItem.setOsiCategories(osiCategoriesMapper.osiCategoriesDTOToOsiCategories(osiItemDTO.getOsiCategories()));
		if(osiItem.getOsiCategories()!=null)
		osiItem.setCategoryCode(osiItem.getOsiCategories().getCategoryCode());
		osiItem.setBusinessGroupId(osiItemDTO.getBusinessGroupId());
		osiItem.setIsUsedApprovedSupplier(osiItemDTO.getIsUsedApprovedSupplier());
		osiItem.setIsPhysicalCountEnabled(osiItemDTO.getIsPhysicalCountEnabled());
		//osiItem.setTaxId(osiItemDTO.getTaxId());
		osiItem.setFreeze(0);
		osiItem.setIsTradeble(0);
		osiItem.setIsInventory(0);
		osiItem.setIsPurchasable(0);
		osiItem.setIsReservable(0);
		osiItem.setIsServiceItem(0);
		osiItem.setIsTradeble(0);
		osiItem.setIsTransactable(0);
		osiItem.setIsBom(0);
		if(osiItemDTO.getItemId()!=null){
			osiItem.setUpdatedBy(osiItemDTO.getUpdatedBy());
			osiItem.setUpdatedDate(osiItemDTO.getUpdatedDate());
		}else{
			osiItem.setCreatedBy(osiItemDTO.getCreatedBy());
			osiItem.setCreatedDate(osiItemDTO.getCreatedDate());
		}
		Set<OsiItemImage> osiItemImageSet = new HashSet<OsiItemImage>(0);
		Set<OsiInvOrgItems> osiInvOrgItemsSet = new HashSet<OsiInvOrgItems>(0);
		Set<OsiInvOrgItemPrice> osiInvOrgItemPriceSet = new HashSet<OsiInvOrgItemPrice>(0);
		Set<OsiApprovedSuppliers> osiApprovedSuppliersSet = new HashSet<OsiApprovedSuppliers>(0);
		List<OsiUomConversion> osiUomConversionSet = new ArrayList<OsiUomConversion>(0);
		Set<OsiItemApplicableTax> osiItemApplicableTaxSet = new HashSet<OsiItemApplicableTax>(0);
		for (OsiItemImageDTO osiItemImageDTO : osiItemDTO.getOsiItemImages()) {
			if(osiItemImageDTO.getFileContent()!=null && !osiItemImageDTO.getFileContent().equals("")
					&& osiItemImageDTO.getFileType()!=null && !osiItemImageDTO.getFileType().equals("")){
				OsiItemImage osiItemImage = new OsiItemImage();
				osiItemImage.setBusinessGroupId(osiItemDTO.getBusinessGroupId());
				
				String attachmentName = osiItemDTO.getItemCode()+new Date().getTime()+"."+osiItemImageDTO.getFileType();
				osiItemImage.setItemImage(attachmentName);
				File prDir = new File(appConfig.getImagePath()+File.separator+"ITEM");
				if(!prDir.isDirectory()){
					prDir.mkdir();
				}
				String attachmentNameWithUploadDirectory=appConfig.getImagePath()+File.separator+"ITEM"+File.separator+Paths.get(attachmentName).toString();//Paths.get(attachmentsDirectory+File.separator+File.separator+attachmentName).toString();
				CommonService commonService = new CommonService();
				try {
					commonService.uploadAttachment(osiItemImageDTO.getFileContent(), attachmentNameWithUploadDirectory);
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					throw new BusinessException("ERR_1000", "");
				}
				//osiItemImage.setItemImage(osiItemDTO.getItemCode()+new Date().getTime()+"."+osiItemImageDTO.getItemImage());
				/*if(osiItemImageDTO.getFile()!=null){
					CommonService commonService = new CommonService();
					commonService.uploadImages(osiItemImageDTO.getFile(), osiItemImage.getItemImage());
				}*/
			//	osiItemImage.setItemImageId(osiItemImageDTO.getItemImageId());
				osiItemImage.setOsiItem(osiItem);
				osiItemImageSet.add(osiItemImage);
			}
		}
		osiItem.setOsiItemImages(osiItemImageSet);
		for (OsiInvOrgItemsDTO osiInvOrgItemsDTO : osiItemDTO.getOsiInvOrgItemses()) {
			//item-inventory
			OsiInvOrgItems osiInvOrgItems = new OsiInvOrgItems();
			OsiInventoryOrg osiInventoryOrg =new OsiInventoryOrg();
			osiInventoryOrg.setInvOrgId(osiInvOrgItemsDTO.getInvOrgId());
                        
		//	osiInvOrgItems.setInvOrgItemId(osiInvOrgItemsDTO.getInvOrgItemId());
			osiInvOrgItems.setBusinessGroupId(osiItemDTO.getBusinessGroupId());
			osiInvOrgItems.setOsiItem(osiItem);
			osiInvOrgItems.setInvOrgId(osiInvOrgItemsDTO.getInvOrgId());
                        osiInvOrgItems.setOrgid(osiInvOrgItemsDTO.getInvOrgId());
			osiInvOrgItems.setCoaId(osiItemDTO.getOsiCategories().getOsiCoa().getCoaId());
			osiInvOrgItems.setCoaCode(osiItemDTO.getOsiCategories().getOsiCoa().getCoaCode());
			osiInvOrgItems.setOsiInventoryOrg(osiInventoryOrg);
			
			osiInvOrgItems.setInvOrgType(osiInvOrgItemsDTO.getInvOrgType());
			osiInvOrgItems.setListPrice(osiInvOrgItemsDTO.getListPrice());
			osiInvOrgItems.setMarketPrice(osiInvOrgItemsDTO.getMarketPrice());
			osiInvOrgItems.setMinThreshold(osiInvOrgItemsDTO.getMinThreshold());
			osiInvOrgItems.setMaxThreshold(osiInvOrgItemsDTO.getMaxThreshold());
			
			osiInvOrgItemsSet.add(osiInvOrgItems);
			//prices
			/*OsiInvOrgItemPriceDTO osiInvOrgItemPriceDTO =osiItemDTO.getOsiInvOrgItemPrices().iterator().next();
			OsiInvOrgItemPrice osiInvOrgItemPrice = new OsiInvOrgItemPrice();
		//	osiInvOrgItemPrice.setItemPriceId(osiInvOrgItemPriceDTO.getItemPriceId());
			osiInvOrgItemPrice.setBusinessGroupId(osiItemDTO.getBusinessGroupId());
			osiInvOrgItemPrice.setActive(1);
			osiInvOrgItemPrice.setIsTaxAmtIncluded(0);
			osiInvOrgItemPrice.setListPrice(osiInvOrgItemPriceDTO.getListPrice());
			osiInvOrgItemPrice.setMarketPrice(osiInvOrgItemPriceDTO.getMarketPrice());
			osiInvOrgItemPrice.setSellingPrice(osiInvOrgItemPriceDTO.getSellingPrice());
			osiInvOrgItemPrice.setOsiInventoryOrg(osiInventoryOrg);
			osiInvOrgItemPrice.setOsiItem(osiItem);
			osiInvOrgItemPriceSet.add(osiInvOrgItemPrice);*/
		}
		for (OsiApprovedSuppliersDTO osiApprovedSuppliersDTO : osiItemDTO.getOsiApprovedSupplierses()) {
			OsiApprovedSuppliers osiApprovedSuppliers = new OsiApprovedSuppliers();
			osiApprovedSuppliers.setBusinessGroupId(osiItemDTO.getBusinessGroupId());
			osiApprovedSuppliers.setSupplierId(osiApprovedSuppliersDTO.getSupplierId());
			osiApprovedSuppliers.setSupplierSiteId(osiApprovedSuppliersDTO.getSupplierSiteId());
			osiApprovedSuppliers.setOsiItem(osiItem);
			osiApprovedSuppliersSet.add(osiApprovedSuppliers);
		}
		
		for (OsiUomConversionDTO osiUomConversionDTO : osiItemDTO.getOsiUomConversionList()) {
			OsiUomConversion osiUomConversion = new OsiUomConversion();
			//osiUomConversion.setUomConversionId(osiUomConversionDTO.getUomConversionId());
			osiUomConversion.setBusinessGroupId(osiItemDTO.getBusinessGroupId());
			osiUomConversion.setBaseUomId(osiUomConversionDTO.getBaseUomId());
			osiUomConversion.setTargetUomId(osiUomConversionDTO.getTargetUomId());
			osiUomConversion.setConversion(osiUomConversionDTO.getConversion());
			osiUomConversion.setActive(osiUomConversionDTO.getActive());
			osiUomConversion.setOsiItem(osiItem);
			osiUomConversionSet.add(osiUomConversion);
		}
		for (OsiItemApplicableTaxDTO osiItemApplicableTaxDTO : osiItemDTO.getOsiItemApplicableTaxes()) {
			OsiItemApplicableTax osiItemApplicableTax = new OsiItemApplicableTax();
			osiItemApplicableTax.setItemApplicableTaxId(osiItemApplicableTaxDTO.getItemApplicableTaxId());
			OsiTax osiTax = new OsiTax();
			osiTax.setTaxId(osiItemApplicableTaxDTO.getOsiTax().getTaxId());
			osiItemApplicableTax.setOsiTax(osiTax);
			osiItemApplicableTax.setOsiItem(osiItem);
			osiItemApplicableTax.setBusinessGroupId(osiItemDTO.getBusinessGroupId());
			osiItemApplicableTaxSet.add(osiItemApplicableTax);
		}
		//osiItem.setOsiItemApplicableTaxes(osiItemApplicableTaxSet);
		osiItem.setOsiUomConversions(osiUomConversionSet);
		osiItem.setOsiInvOrgItemses(osiInvOrgItemsSet);
		//osiItem.setOsiInvOrgItemPrices(osiInvOrgItemPriceSet);
		osiItem.setOsiApprovedSupplierses(osiApprovedSuppliersSet);
		return osiItem;
	}

	@Override
	public List<OsiItem> osiItemDTOListToOsiItemList(List<OsiItemDTO> osiItemDTO) throws BusinessException {
		if ( osiItemDTO == null ) {
            return null;
        }

        List<OsiItem> list = new ArrayList<OsiItem>();
        for ( OsiItemDTO osiItemDTO1 : osiItemDTO ) {
            list.add( osiItemDTOToOsiItem( osiItemDTO1 ) );
        }
        return list;
	}
   
}
