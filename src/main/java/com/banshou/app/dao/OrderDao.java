package com.banshou.app.dao;

import java.util.List;

import com.banshou.app.domain.Order;

public interface OrderDao {
	public List<Order> getAll();
	public List<Order> getToday();
	public String deleteById(String id);
}
