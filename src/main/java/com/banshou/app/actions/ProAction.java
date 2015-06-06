package com.banshou.app.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banshou.app.domain.Province;
import com.banshou.app.service.ProService;
import com.banshou.app.utils.common.CodeGenerator;
import com.opensymphony.xwork2.ActionSupport;

@Component("ProAction")
public class ProAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(ProAction.class);
	private Map<String, Object> dataMap = null;
	private String name;
	private String number;

	@Autowired
	ProService proService;

	public String add() {
		dataMap = new HashMap<String, Object>();
		try {
			Province p = new Province();
			String num = "SF" + CodeGenerator.generateTimeStampString();
			p.setNumber(num);
			p.setName(name);
			proService.addPro(p);
			dataMap.put("data", "success");
		} catch (Exception e) {
			e.printStackTrace();
			dataMap.put("data", "error");
		}

		return SUCCESS;
	}

	public String getAll() {
		dataMap = new HashMap<String, Object>();
		List<Province> pros = null;
		try{
			pros = proService.getAll();
		} catch (Exception e){
			e.printStackTrace();
		}
		dataMap.put("data", pros);
		return SUCCESS;
	}
	
	public String deleteProByNumber(){
		LOGGER.info("[ProAction] {deleteByNumber method} begin to delete the province by the number: " + number);
		dataMap = new HashMap<String, Object>();
		try{
			proService.deleteByNumber(number);
			dataMap.put("data", "success");
			
		} catch (Exception e){
			e.printStackTrace();
			dataMap.put("data", "error");
		}
		return SUCCESS;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
