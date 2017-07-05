package com.authority_list.model;

import java.util.List;

public interface AuthorityListDAO_interface {

	public void insert(AuthorityListVO authorityListVO);
	public void update(AuthorityListVO authorityListVO);
	public void delete(String man_no);
	public AuthorityListVO findByPK(String man_no);
	public List<AuthorityListVO>getAll();
	
	
}
