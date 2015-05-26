package com.banshou.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bs_store")
public class Store {

	@Id
	@Column(name = "st_number", nullable = true)
	private String number;

	@Column(name = "st_name", nullable = true)
	private String name;

	@Column(name = "st_tel", nullable = true)
	private String tel;

	@Column(name = "st_is_valid", nullable = true)
	private Boolean isvalid;

	@Column(name = "st_is_vip", nullable = true)
	private Boolean isvip;

	@Column(name = "st_point", nullable = true)
	private int point;

	@Column(name = "st_brief", nullable = true)
	private String brief;

	@Column(name = "st_image", nullable = true)
	private String image;
	
	@Column(name = "st_icon", nullable = true)
	private String icon;

	@Column(name = "st_address", nullable = true)
	private String address;

	/* 经度 */
	@Column(name = "st_longitude", nullable = true)
	private String longitude;

	/* 纬度 */
	@Column(name = "st_latitude", nullable = true)
	private String latitude;

	@Column(name = "st_time", nullable = true)
	private String time;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Boolean getIsvalid() {
		return isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

	public Boolean getIsvip() {
		return isvip;
	}

	public void setIsvip(Boolean isvip) {
		this.isvip = isvip;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
