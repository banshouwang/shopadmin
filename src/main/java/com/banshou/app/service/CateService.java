package com.banshou.app.service;

import java.util.List;

import com.banshou.app.domain.Category;

public interface CateService {
	public void addCate(Category category);
	public List<Category> getAll();
}
