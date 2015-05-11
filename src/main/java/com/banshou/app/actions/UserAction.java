package com.banshou.app.actions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.banshou.app.dao.RequestDao;
import com.banshou.app.domain.Request;
import com.banshou.app.domain.Store;
import com.banshou.app.domain.User;
import com.banshou.app.service.StoreService;
import com.banshou.app.service.UserService;
import com.banshou.app.utils.common.CodeGenerator;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(UserAction.class);
	private Map<String, Object> dataMap = null;

	private String mobile;
	private String shop;
	private Map<String, Object> session;
	private String password;

	@Autowired
	UserService userService;
	
	@Autowired
	StoreService storeService;

	public String reg() {
		LOGGER.info("[UserAction] {reg method} begin to handle the registry information...");

		User user = (User) session.get("user");

		if (user != null && !"".equals(user.getOpenId())) {
			user.setIsvip(false);
			user.setIsstore(false);
			user.setMobile(session.get("mobile").toString());
			user.setPoint(0);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String regtime = sdf.format(new Date());

			user.setTime(regtime);
			user.setStoreNum("");

			userService.addUser(user);

			if ("shopyes".equals(shop)) {
				LOGGER.info("[UserAction] {reg method} create shop request start ...");
				Request request = new Request();
				RequestDao rd = new RequestDao();
				request.setCategory("isshop");
				request.setMobile(mobile);
				request.setStatus(regtime);
				request.setStatus("In Progress");
				rd.createRequest(request);
			}

			LOGGER.info("[UserAction] registry gonna to end...");

			return session.get("state").toString();
		} else {
			return "session_expired";
		}

	}

	public String check() {
		LOGGER.info("[UserAction] {mobile check method} begin ...");
		LOGGER.info("[UserAction] {mobile check method} the mobile number is: " + mobile);

		// 生成手机验证码并发送到手机
		String validCode = CodeGenerator.mobileValid();
		System.out.println("validCode is: " + validCode);

		session.put("validCode", validCode);
		session.put("mobile", mobile);
		LOGGER.info("[UserAction] {mobile check method} end ...");
		return SUCCESS;
	}

	public String logout() {
		session.clear();
		return SUCCESS;
	}

	public String login() {
		dataMap = new HashMap<String, Object>();
		User user = userService.login(mobile, password);
		Store store = new Store();
		if(user != null){
			store = storeService.getStoreByNum(user.getStoreNum());
			session.put("user", user);
			session.put("store", store);
			dataMap.put("data", "success");
		} else {
			dataMap.put("data", "error");
		}
		return SUCCESS;
	}

	// public String addPoint(){
	// userService.addPoint(openId, point);
	// return SUCCESS;
	// }

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}