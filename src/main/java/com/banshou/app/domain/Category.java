package com.banshou.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bs_category")
public class Category {
	
	@Id
	@Column(name = "c_num", nullable = true)
	private String num;
	
	@Column(name = "c_name", nullable = true)
	private String name;
	
	@Column(name = "c_time", nullable = true)
	private String time;
	
	@Column(name = "c_is_valid", nullable = true)
	private int is_valid;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getIs_valid() {
		return is_valid;
	}
	public void setIs_valid(int is_valid) {
		this.is_valid = is_valid;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
}
