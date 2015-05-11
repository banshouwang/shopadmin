package com.banshou.app.service;

import java.util.List;

import com.banshou.app.domain.Order;

public interface OrderService {
	public List<Order> getAll();
	public List<Order> getToday();
	public String deleteById(String id);
}	
