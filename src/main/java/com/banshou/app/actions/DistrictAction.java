package com.banshou.app.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banshou.app.domain.District;
import com.banshou.app.service.DistrictService;
import com.opensymphony.xwork2.ActionSupport;

@Component("DistrictAction")
public class DistrictAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Map<String, Object> dataMap = null;

	@Autowired
	DistrictService districtService;

	public String getAll() {
		dataMap = new HashMap<String, Object>();
		List<District> dists = null;
		try {
			dists = districtService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataMap.put("data", dists);
		return SUCCESS;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

}
