package com.authority.model;

import java.util.List;

import com.authority.model.AuthorityVO;

public interface AuthorityDAO_interface {

	public void insert(AuthorityVO autority);
	public void update(AuthorityVO autority);
	public void delete(String man_no);
	public AuthorityVO findByPK(String man_no);
	public List<AuthorityVO> getAll();
}
