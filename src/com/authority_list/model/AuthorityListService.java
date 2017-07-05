package com.authority_list.model;

import java.util.List;

public class AuthorityListService {

	private AuthorityListDAO_interface dao;

	public AuthorityListService() {
		dao = new AuthorityListDAO();
	}

	public AuthorityListVO addAutlist(String man_no, String aut_no) {

		AuthorityListVO autlistVO = new AuthorityListVO();

		autlistVO.setMan_no(man_no);
		autlistVO.setAut_no(aut_no);
		
		dao.insert(autlistVO);

		return autlistVO;
	}

	public AuthorityListVO updateAutlist(String man_no,String aut_no) {

		AuthorityListVO autlistVO = new AuthorityListVO();
		
		autlistVO.setMan_no(man_no);
		autlistVO.setAut_no(aut_no);
		
		
		dao.update(autlistVO);

		return autlistVO;
	}

	public void deleteAutlist(String man_no) {
		dao.delete(man_no);
	}

	public AuthorityListVO getOneAutlist(String man_no) {
		return dao.findByPK(man_no);
	}

	public List<AuthorityListVO> getAllAutlist() {
		return dao.getAll();
	}
}
