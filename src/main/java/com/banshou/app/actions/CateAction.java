package com.banshou.app.actions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banshou.app.domain.Category;
import com.banshou.app.service.CateService;
import com.banshou.app.utils.common.RandomStrUtil;
import com.opensymphony.xwork2.ActionSupport;

@Component("CateAction")
public class CateAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(CateAction.class);
	private Map<String, Object> dataMap = null;
	private String name;
	
	@Autowired
	CateService cateService;
	
	public String add(){
		LOGGER.info("[CateAction] {add method} begin to add the category to the DB ...");
		LOGGER.info("[CateAction] category name is " + name);
		
		Category category = new Category();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String regtime = sdf.format(new Date());
		String num = "FL" + regtime + RandomStrUtil.getNumStr(6);
		
		category.setName(name);
		category.setIs_valid(1);
		category.setTime(regtime);
		category.setNum(num);
		cateService.addCate(category);
		
		LOGGER.info("[CateAction] add category gonna to end...");
		
		return SUCCESS;
	}
	
	public String getAll(){
		dataMap = new HashMap<String, Object>();
		List<Category> cates = cateService.getAll();
		dataMap.put("data", cates);
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
	
}
