package com.banshou.app.actions;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banshou.app.domain.Store;
import com.banshou.app.service.StoreService;
import com.opensymphony.xwork2.ActionSupport;

@Component("StoreAction")
public class StoreAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(StoreAction.class);

	private Map<String, Object> dataMap = null;
	private String storeName;
	private String brief;
	private String address;
	private String tel;
	private String names;
	private String storeNum;
	private String longitude;
	private String latitude;

	@Autowired
	StoreService storeService;

	public String update() {
		LOGGER.info("[StoreAction] {update method} begin to update the store details ...");
		dataMap = new HashMap<String, Object>();
		String[] images = names.split(",");
		String icon = images[0];
		StringBuffer sb = new StringBuffer();
		for(int i = 1; i < images.length; i ++){
			sb.append(images[i]).append(",");
		}
		String image = sb.toString();
		
		
		Store store = new Store();
		store.setNumber(storeNum);
		store.setName(storeName);
		store.setBrief(brief);
		store.setAddress(address);
		store.setTel(tel);
		store.setIcon(icon);
		store.setImage(image.substring(0, image.length() - 1));
		store.setIsvalid(true);
		store.setIsvip(false);
		store.setLongitude(longitude);
		store.setLatitude(latitude);
		store.setPoint(0);

		int result = storeService.updateStoreByNum(store);
		if (result == 1) {
			dataMap.put("data", "success");
		} else {
			dataMap.put("data", "fail");
		}
		names = "";
		return SUCCESS;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getStoreNum() {
		return storeNum;
	}

	public void setStoreNum(String storeNum) {
		this.storeNum = storeNum;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
}
