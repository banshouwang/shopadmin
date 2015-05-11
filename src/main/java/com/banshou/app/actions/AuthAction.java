package com.banshou.app.actions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banshou.app.domain.Store;
import com.banshou.app.domain.User;
import com.banshou.app.service.StoreService;
import com.banshou.app.service.UserService;
import com.banshou.app.utils.auth.QRCodeUtil;
import com.banshou.app.utils.auth.QrAuth;
import com.banshou.app.utils.common.Constants;
import com.banshou.app.utils.common.GetServerDir;
import com.banshou.app.utils.common.RandomStrUtil;
import com.opensymphony.xwork2.ActionSupport;

@Component("AuthAction")
public class AuthAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(AuthAction.class);
	private Map<String, Object> dataMap = null;
	private Map<String, Object> session;
	private String authCode;

	@Autowired
	UserService userService;

	@Autowired
	StoreService storeService;

	public String generator() throws Exception {
		LOGGER.info("[AuthAction] {generator method} begin to generate the auth code ");

		dataMap = new HashMap<String, Object>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String regtime = sdf.format(new Date());
		String authCode = "QRAUTH" + regtime + RandomStrUtil.getNumStr(6);
		GetServerDir serverDir = GetServerDir.getInstance();
		String prePath = "/"+serverDir.getRootPath();
		String text = Constants.WXAUTH.replace("STATE", authCode);
		QRCodeUtil.encode(text, prePath + "images" + File.separator + "sys" + File.separator + "logo.jpg", prePath + "images" + File.separator + "qr", authCode, true);
		String result = QrAuth.addCode(authCode, "unauth");
		if ("success".equals(result)) {
			dataMap.put("data", authCode);
		} else {
			dataMap.put("data", "error");
		}
		return SUCCESS;
	}

	public String reload() {
		dataMap = new HashMap<String, Object>();
		String result = QrAuth.reload(authCode);
		dataMap.put("data", result);
		dataMap.put("code", authCode);
		return SUCCESS;
	}
	
	public String expired() {
		dataMap = new HashMap<String, Object>();
		String result = QrAuth.expired(authCode);
		dataMap.put("data", result);
		dataMap.put("code", authCode);
		return SUCCESS;
	}

	public String login() {
		User user = new User();
		Store store = new Store();
		String result = QrAuth.loadOpenid(authCode);
		if (!"error".equals(result)) {
			user = userService.getUserInfo(result);
			store = storeService.getStoreByNum(user.getStoreNum());
			session.put("user", user);
			session.put("store", store);
			return SUCCESS;
		} else {
			return "error";
		}
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}

}
