package com.banshou.app.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banshou.app.domain.Order;
import com.banshou.app.service.OrderService;
import com.opensymphony.xwork2.ActionSupport;

@Component("OrderAction")
public class OrderAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(OrderAction.class);
	private Map<String, Object> dataMap = null;
	private String number;


	@Autowired
	OrderService orderService;


	public String getToday() {
		LOGGER.info("[OrderAction] {getToday method} begin to get the orders from DB ...");
		dataMap = new HashMap<String, Object>();
		try{
			List<Order> orders = orderService.getToday();
			dataMap.put("data", orders);
		} catch (Exception e){
			dataMap.put("data", null);
		}
		return SUCCESS;
	}
	
	public String getAll() {
		LOGGER.info("[OrderAction] {getAll method} begin to get the all orders from DB ...");
		dataMap = new HashMap<String, Object>();
		try{
			List<Order> orders = orderService.getAll();
			dataMap.put("data", orders);
		} catch (Exception e){
			dataMap.put("data", null);
		}
		return SUCCESS;
	}
	
	public String delete(){
		LOGGER.info("[OrderAction] {delete method} the number of the item will be deleted is : " + number);
		dataMap = new HashMap<String, Object>();
		String result = orderService.deleteById(number);
		LOGGER.info("[GoodAction] {delete method} delete item: " + number + " successfully");
		dataMap.put("data", result);
		return SUCCESS;
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
