package com.banshou.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bs_city")
public class City {

	@Id
	@Column(name = "c_number", nullable = true)
	private String number;

	@Column(name = "c_name", nullable = true)
	private String name;
	
	@Column(name = "p_name", nullable = true)
	private String pname;

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

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}
}
