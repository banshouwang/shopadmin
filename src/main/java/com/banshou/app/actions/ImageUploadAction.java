package com.banshou.app.actions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banshou.app.domain.Store;
import com.banshou.app.service.StoreService;
import com.banshou.app.utils.aliyun.BucketHandler;
import com.banshou.app.utils.common.CodeGenerator;
import com.banshou.app.utils.common.Constants;
import com.banshou.app.utils.common.RandomStrUtil;
import com.opensymphony.xwork2.ActionSupport;

@Component("ImageUploadAction")
public class ImageUploadAction extends ActionSupport implements SessionAware {

	private static final Logger LOGGER = Logger.getLogger(GoodsAction.class);
	private static final long serialVersionUID = 1L;
	
	
	private Map<String, Object> dataMap = null;

	private File upload;
	private File file;
	private String uploadContentType;
	private String uploadFileName;
	private String storageFileName;
	private String fileName;
	public String path = "";
	private String storeNum;
	
	@Autowired
	StoreService storeService;
	private Map<String, Object> session;
	
	public String upload() {
		LOGGER.info("[ImageUploadAction] {upload method} begin to upload the image with the file name " + "{ " + fileName + " } and the store number is: " + storeNum);
		path = ServletActionContext.getServletContext().getRealPath(File.separator + "uploads");
		dataMap = new HashMap<String, Object>();
		BucketHandler bh = new BucketHandler();
		
		String key = Constants.STOREICONKEY + fileName;
		try {
			bh.uploadFile(Constants.BUCKETNAME, key, upload);
			storeService.updateIcon(fileName, storeNum);
			Store s = storeService.getStoreByNum(storeNum);
			session.put("store", s);
			dataMap.put("data", key);
			
		} catch (Exception e) {
			e.printStackTrace();
			dataMap.put("data", "failed");
		} 
		return SUCCESS;
	}
	
	public String uploadBatch() {
		LOGGER.info("[ImageUploadAction] {batch upload method} begin to upload the images with the store number: " + storeNum);
		dataMap = new HashMap<String, Object>();
		BucketHandler bh = new BucketHandler();
		String name = "ST" + CodeGenerator.generateTimeStampString() + RandomStrUtil.getNumStr(6);

		String key = Constants.STOREKEY + name;
		System.out.println("key: " + key);
		try {
			bh.uploadFile(Constants.BUCKETNAME, key, file);
			storeService.updateImage(name, storeNum);
			Store s = storeService.getStoreByNum(storeNum);
			session.put("store", s);
			dataMap.put("data", "success");
		} catch (Exception e) {
			e.printStackTrace();
			dataMap.put("data", "error");
		} 
		
		name = "";
		return SUCCESS;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getStorageFileName() {
		return storageFileName;
	}

	public void setStorageFileName(String storageFileName) {
		this.storageFileName = storageFileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getStoreNum() {
		return storeNum;
	}

	public void setStoreNum(String storeNum) {
		this.storeNum = storeNum;
	}

	public Map<String, Object> getSession() {
		return session;
	}
}