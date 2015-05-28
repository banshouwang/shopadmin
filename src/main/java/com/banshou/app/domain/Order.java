package com.banshou.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bs_order")
public class Order {

	@Id
	@Column(name = "o_number", nullable = true)
	private String number;

	@Column(name = "o_status", nullable = true)
	private String status;

	@Column(name = "o_total", nullable = true)
	private String total;

	@Column(name = "o_ticket", nullable = true)
	private String ticket;

	@Column(name = "o_createTime", nullable = true)
	private String createTime;

	@Column(name = "o_payTime", nullable = true)
	private String payTime;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
}
