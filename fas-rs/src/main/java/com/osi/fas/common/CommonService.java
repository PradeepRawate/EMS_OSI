package com.osi.fas.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.osi.urm.config.AppConfig;
import com.osi.urm.exception.BusinessException;

public class CommonService {
	
	@Autowired
	private AppConfig appConfig;
	public void uploadImages(MultipartFile file, String fileName){

	            try {
	       		 if (!file.isEmpty()) {
	                byte[] bytes = file.getBytes();
	                BufferedOutputStream stream = 
	                        new BufferedOutputStream(new FileOutputStream(new File(appConfig.getImagePath()+"\\"+fileName)));
	                stream.write(bytes);
	                stream.close();
	       		 }
	            } catch (Exception e) {
	               e.printStackTrace();
	            }
	}
	
	//Base64 file content upload for PR and PO
	public void uploadAttachment(String fileContent, String fileNameWithUploadDirectory) throws BusinessException {
		BufferedOutputStream stream;
		try {
			byte[] data = Base64.decodeBase64(fileContent.getBytes(StandardCharsets.UTF_8));
			//OutputStream stream = new FileOutputStream(fileNameWithUploadDirectory);
			File file = new File(fileNameWithUploadDirectory);
			stream = new BufferedOutputStream(new FileOutputStream(file));			
			stream.write(data);
			stream.close();
		} catch (Exception e) {
			//e.printStackTrace();
			throw new BusinessException("ERR_1000", e.getMessage());
		} /*catch(Throwable t){
			t.printStackTrace();
		}	*/	
		
	}
	
	//Deletes Files from File System for PR, PO & BPA
	public void deleteAttachment(String fileNameWithUploadDirectory) throws BusinessException {
		try {
			File file = new File(fileNameWithUploadDirectory);
			file.delete();
		} catch (Exception e) {
			throw new BusinessException("ERR_1000", e.getMessage());
		}
	}
	
	//Encode file to Base64
	@SuppressWarnings("resource")
	public String encodeFile(String filePath) throws BusinessException{
		String encodedString = "";
		try{
			File file = new File(filePath);
			InputStream is = new FileInputStream(file);

		    long length = file.length();
		    if (length > Integer.MAX_VALUE) {
		        // File is too large
		    }
		    byte[] bytes = new byte[(int)length];
		    
		    int offset = 0;
		    int numRead = 0;
		    while (offset < bytes.length
		           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
		        offset += numRead;
		    }

		    if (offset < bytes.length) {
		        throw new BusinessException("ERR_1000","");
		    }

		    is.close();
		    byte[] encoded = Base64.encodeBase64(bytes);
		    encodedString = new String(encoded, "UTF-8");
			
		}catch(Exception e){
			//e.printStackTrace();
			throw new BusinessException("ERR_1060", "");
		}
		
		return encodedString;
	}
}
