package com.banshou.app.dao;

import java.util.List;

import com.banshou.app.domain.Category;

public interface CateDao {
	public void addCate(Category category);
	public List<Category> getAll();
}
