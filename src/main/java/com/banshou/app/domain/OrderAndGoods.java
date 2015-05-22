package com.banshou.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bs_order_goods")
public class OrderAndGoods {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "og_id", nullable = false)
	private int number;

	@Column(name = "o_number", nullable = true)
	private String onum;

	@Column(name = "g_number", nullable = true)
	private String gnum;

	@Column(name = "g_password", nullable = true)
	private String password;

	@Column(name = "g_status", nullable = true)
	private String status;

	public String getOnum() {
		return onum;
	}

	public void setOnum(String onum) {
		this.onum = onum;
	}

	public String getGnum() {
		return gnum;
	}

	public void setGnum(String gnum) {
		this.gnum = gnum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
