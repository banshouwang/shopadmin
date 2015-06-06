package com.banshou.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bs_district")
public class District {

	@Id
	@Column(name = "d_number", nullable = true)
	private String number;

	@Column(name = "d_name", nullable = true)
	private String name;
	
	@Column(name = "c_name", nullable = true)
	private String cname;

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

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
}
