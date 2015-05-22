package com.banshou.app.actions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banshou.app.domain.Goods;
import com.banshou.app.service.GoodsService;
import com.banshou.app.utils.common.RandomStrUtil;
import com.opensymphony.xwork2.ActionSupport;

@Component("GoodsAction")
public class GoodsAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(GoodsAction.class);
	private Map<String, Object> dataMap = null;

	private String gname;
	private String category;
	private String priceori;
	private String pricehere;
	private String isticket;
	private String ticket;
	private String store;
	private String brief;
	private String number;
	private String imageName;
	private String condition;
	private String address;
	private String storeNum;
	private String latitude;
	private String longitude;
	
	private String password;
	
	@Autowired
	GoodsService goodsService;
	
	public String add(){
		LOGGER.info("[GoodAction] {add method} begin to add the goods to the DB ...");
		LOGGER.info("[GoodAction] name is " + gname + ", category is " + category + ", is_ticket is " + isticket + ", ticket is " + ticket + ", image is " + imageName + ", address is " + address);
		
		Goods goods = new Goods();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		String regtime = sdf.format(new Date());
		String number = "SP" + regtime + RandomStrUtil.getNumStr(6);

		goods.setName(gname);
		goods.setCategory(category);
		goods.setNumber(number);
		goods.setPriceori(Integer.parseInt(priceori));
		goods.setPricehere(Integer.parseInt(pricehere));
		goods.setBrief(brief);
		
		if("on".equals(isticket)){
			goods.setIsticket("是");
			goods.setTicket(Integer.parseInt(ticket));
		} else {
			goods.setIsticket("否");
			goods.setTicket(0);
		}
		
		String area = address.substring(3, 6);
		goods.setArea(area);
		goods.setDistance(0);
		goods.setImageName(imageName);
		goods.setIsvalid("是");
		goods.setAddress(address);
		goods.setStorenum(storeNum);
		goods.setLatitude(latitude);
		goods.setLongitude(longitude);
		goodsService.addGoods(goods);
		
		LOGGER.info("[GoodAction] add goods gonna to end...");
		
		return SUCCESS;
	}
	
	public String getAll(){
		LOGGER.info("[GoodAction] {getAll method} begin to get the goods from DB ...");
		dataMap = new HashMap<String, Object>();
		List<Goods> goods = goodsService.getAll();
		
		LOGGER.info("[GoodAction] {getAll method} the count of the goods items is: " + goods.size());
		dataMap.put("data", goods);
		return SUCCESS;
	}
	
	public String getGoodsByCondition(String condition){
		
		return SUCCESS;
	}
	
	public String delete(){
		LOGGER.info("[GoodAction] {delete method} the number of the item will be deleted is : " + number);
		dataMap = new HashMap<String, Object>();
		goodsService.deleteById(number);
		LOGGER.info("[GoodAction] {delete method} delete item: " + number + " successfully");
		dataMap.put("data", "success");
		return SUCCESS;
	}
	
	public String consume(){
		LOGGER.info("[GoodAction] {consume method} begin to exchange the password: " + password);
		dataMap = new HashMap<String, Object>();
		try{
			String result = goodsService.consume(password);
			if("success".equals(result)){
				dataMap.put("data", "success");
			} else {
				dataMap.put("data", "already");
			}
		} catch (Exception e){
			e.printStackTrace();
			dataMap.put("data", "error");
		}
		return SUCCESS;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPriceori() {
		return priceori;
	}

	public void setPriceori(String priceori) {
		this.priceori = priceori;
	}

	public String getPricehere() {
		return pricehere;
	}

	public void setPricehere(String pricehere) {
		this.pricehere = pricehere;
	}

	public String getIsticket() {
		return isticket;
	}

	public void setIsticket(String isticket) {
		this.isticket = isticket;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
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

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStoreNum() {
		return storeNum;
	}

	public void setStoreNum(String storeNum) {
		this.storeNum = storeNum;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
