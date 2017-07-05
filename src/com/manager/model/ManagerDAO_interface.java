package com.manager.model;

import java.util.List;

public interface ManagerDAO_interface {

	public void insert(ManagerVO manager);
	public void update(ManagerVO manager);
	public void delete(String man_no);
	public ManagerVO findByPrimaryKey(String man_no);
	public ManagerVO findById(String man_id);
	public List<ManagerVO> getAll();
	
}
