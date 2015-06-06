package com.banshou.app.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banshou.app.domain.City;
import com.banshou.app.service.CityService;
import com.banshou.app.utils.common.CodeGenerator;
import com.opensymphony.xwork2.ActionSupport;

@Component("CityAction")
public class CityAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(CityAction.class);
	private Map<String, Object> dataMap = null;
	private String name;
	private String number;
	private String pname;

	@Autowired
	CityService cityService;

	public String add() {
		dataMap = new HashMap<String, Object>();
		try {
			City c = new City();
			String num = "CS" + CodeGenerator.generateTimeStampString();
			c.setNumber(num);
			c.setName(name);
			c.setPname(pname);
			cityService.addCity(c);
			dataMap.put("data", "success");
		} catch (Exception e) {
			e.printStackTrace();
			dataMap.put("data", "error");
		}

		return SUCCESS;
	}

	public String getAll() {
		dataMap = new HashMap<String, Object>();
		List<City> cities = null;
		try{
			cities = cityService.getAll();
		} catch (Exception e){
			e.printStackTrace();
		}
		dataMap.put("data", cities);
		return SUCCESS;
	}
	
	public String deleteCityByNumber(){
		LOGGER.info("[CityAction] {deleteProByNumber method} begin to delete the province by the number: " + number);
		dataMap = new HashMap<String, Object>();
		try{
			cityService.deleteByNumber(number);
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

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

}
