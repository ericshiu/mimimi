package com.authority.model;

import java.util.List;

public class AuthorityService {

	private AuthorityDAO_interface dao;

	public AuthorityService() {
		dao = new AuthorityDAO();
	}

	public AuthorityVO addAut(String aut_name, String aut_content) {

		AuthorityVO autVO = new AuthorityVO();

		autVO.setAut_name(aut_name);
		autVO.setAut_content(aut_content);
		
		dao.insert(autVO);

		return autVO;
	}

	public AuthorityVO updateAut(String aut_no, String aut_name, String aut_content) {

		AuthorityVO autVO = new AuthorityVO();

		autVO.setAut_no(aut_no);
		autVO.setAut_name(aut_name);
		autVO.setAut_content(aut_content);
		
		dao.update(autVO);

		return autVO;
	}

	public void deleteAut(String aut_no) {
		dao.delete(aut_no);
	}

	public AuthorityVO getOneAut(String aut_no) {
		return dao.findByPK(aut_no);
	}

	public List<AuthorityVO> getAllAut() {
		return dao.getAll();
	}
}
