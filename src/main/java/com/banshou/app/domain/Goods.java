package com.banshou.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bs_goods")
public class Goods {
	
	@Id
	@Column(name = "g_number", nullable = true)
	private String number;
	
	@Column(name = "g_name", nullable = true)
	private String name;
	
	@Column(name = "g_category", nullable = true)
	private String category;
	
	@Column(name = "g_status", nullable = true)
	private String status;
	
	@Column(name = "g_priceori", nullable = true)
	private int priceori;
	
	@Column(name = "g_pricehere", nullable = true)
	private int pricehere;
	
	@Column(name = "g_is_ticket", nullable = true)
	private String isticket;
	
	@Column(name = "g_ticket", nullable = true)
	private int ticket;
	
	@Column(name = "g_brief", nullable = true)
	private String brief;
	
	@Column(name = "g_image", nullable = true)
	private String imageName;
	
	@Column(name = "g_storenum", nullable = true)
	private String storenum;
	
	@Column(name = "g_addr", nullable = true)
	private String address;
	
	/* 经度 */
	@Column(name = "g_longitude", nullable = true)
	private String longitude;
	
	/* 纬度 */
	@Column(name = "g_latitude", nullable = true)
	private String latitude;
	
	@Column(name = "g_distance", nullable = true)
	private double distance;
	
	@Column(name = "g_area", nullable = true)
	private String area;
	
	@Column(name = "g_sell", nullable = true)
	private int sellTotal;
	
	@Column(name = "s_name", nullable = true)
	private String storeName;
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPriceori() {
		return priceori;
	}
	public void setPriceori(int priceori) {
		this.priceori = priceori;
	}
	public int getPricehere() {
		return pricehere;
	}
	public void setPricehere(int pricehere) {
		this.pricehere = pricehere;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsticket() {
		return isticket;
	}
	public void setIsticket(String isticket) {
		this.isticket = isticket;
	}
	public int getTicket() {
		return ticket;
	}
	public void setTicket(int ticket) {
		this.ticket = ticket;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getStorenum() {
		return storenum;
	}
	public void setStorenum(String storenum) {
		this.storenum = storenum;
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
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public int getSellTotal() {
		return sellTotal;
	}
	public void setSellTotal(int sellTotal) {
		this.sellTotal = sellTotal;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
}
