package com.banshou.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bs_user")
public class User {

	@Id
	@Column(name = "u_openid", nullable = false)
	private String openId;

	@Column(name = "u_name", nullable = true)
	private String name;

	@Column(name = "u_image", nullable = true)
	private String image;

	@Column(name = "u_mobile", nullable = true)
	private String mobile;

	@Column(name = "u_is_vip", nullable = true)
	private boolean isvip;

	@Column(name = "u_is_store", nullable = true)
	private boolean isstore;

	@Column(name = "u_storenum", nullable = true)
	private String storeNum;

	@Column(name = "u_time", nullable = true)
	private String time;

	@Column(name = "u_point", nullable = true)
	private int point;

	@Column(name = "u_gender", nullable = true)
	private String gender;
	
	@Column(name = "u_password", nullable = true)
	private String password;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public boolean isIsvip() {
		return isvip;
	}

	public void setIsvip(boolean isvip) {
		this.isvip = isvip;
	}

	public boolean isIsstore() {
		return isstore;
	}

	public void setIsstore(boolean isstore) {
		this.isstore = isstore;
	}

	public String getStoreNum() {
		return storeNum;
	}

	public void setStoreNum(String storeNum) {
		this.storeNum = storeNum;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
