package com.banshou.app.actions;

import java.security.NoSuchAlgorithmException;
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
import com.banshou.app.utils.common.Config;
import com.banshou.app.utils.security.MD5HashUtil;
import com.banshou.app.utils.send.JsonReqClient;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(UserAction.class);
	private Map<String, Object> dataMap = null;

	private String mobile;
	private String shop;
	private Map<String, Object> session;
	private String password;
	private String oripass;
	private String newpass;
	private String openId;

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
		User user = null;
		try {
			user = userService.login(mobile, MD5HashUtil.hashCode(password));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		Store store = new Store();
		if (user != null) {
			store = storeService.getStoreByNum(user.getStoreNum());
			session.put("user", user);
			session.put("store", store);
			dataMap.put("data", "success");
		} else {
			dataMap.put("data", "error");
		}
		return SUCCESS;
	}

	public String changePassword() {
		LOGGER.info("[shopadmin] [UserAction] begin to change password with the mobile: " + mobile + " and the oripass: " + oripass + " and the new pass: " + newpass);
		dataMap = new HashMap<String, Object>();
		User user = null;
		try {
			user = userService.login(mobile, MD5HashUtil.hashCode(oripass));
			if (user != null) {
				System.out.println(user.getPassword());
				user.setPassword(MD5HashUtil.hashCode(newpass));
				userService.updateUser(user);
				dataMap.put("data", "success");
			} else {
				dataMap.put("data", "oriPassWrong");
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			dataMap.put("data", "error");
		}

		return SUCCESS;
	}

	/* 商家申请重置密码，发送验证短信 */
	public String valid() {
		LOGGER.info("[shopadmin] [UserAction] {mobile valid method} begin ...");
		LOGGER.info("[shopadmin] [UserAction] {mobile valid method} the mobile number is: " + mobile);
		dataMap = new HashMap<String, Object>();

		// 生成手机验证码并发送到手机
		try {
			String validCode = CodeGenerator.mobileValid();
			System.out.println("validCode is: " + validCode);
			JsonReqClient client = new JsonReqClient();

			client.templateSMS(Config.ACCOUNDSID, Config.SMSTOKEN, Config.SMSAPPID, Config.SMSTMPID, mobile, validCode + "," + Config.VALIDTIME);

			dataMap.put("validCode", validCode);
			dataMap.put("mobile", mobile);
			dataMap.put("data", "success");
		} catch (Exception e) {
			e.printStackTrace();
			dataMap.put("data", "error");
		}

		return SUCCESS;
	}

	public String resetPassword() {
		LOGGER.info("[shopadmin] [UserAction] {mobile resetPassword method} ");
		dataMap = new HashMap<String, Object>();

		try {
			userService.resetPassword(mobile, MD5HashUtil.hashCode(newpass));
			dataMap.put("data", "success");
		} catch (Exception e) {
			e.printStackTrace();
			dataMap.put("data", "error");
		}
		return SUCCESS;
	}

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

	public String getOripass() {
		return oripass;
	}

	public void setOripass(String oripass) {
		this.oripass = oripass;
	}

	public String getNewpass() {
		return newpass;
	}

	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

}