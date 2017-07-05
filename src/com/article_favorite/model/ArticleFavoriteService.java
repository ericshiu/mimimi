package com.article_favorite.model;

import java.util.List;


import com.article_favorite.model.ArticleFavoriteVO;

public class ArticleFavoriteService {
	
	private ArticleFavoriteDAO_interface dao;

	public ArticleFavoriteService() {
		dao = new ArticleFavoriteDAO();
	}

	public ArticleFavoriteVO addAF(String mem_no, String fa_no) {

		ArticleFavoriteVO artfavVO = new ArticleFavoriteVO();

		artfavVO.setMem_no(mem_no);
		artfavVO.setFa_no(fa_no);
		
		dao.insert(artfavVO);

		return artfavVO;
	}

	public ArticleFavoriteVO updateAF(String mem_no, String fa_no) {

		ArticleFavoriteVO artfavVO = new ArticleFavoriteVO();
		
		artfavVO.setMem_no(mem_no);
		artfavVO.setFa_no(fa_no);
		
		
		dao.update(artfavVO);

		return artfavVO;
	}

	public void deleteAF(String mem_no,String fa_no) {
		
		dao.delete(mem_no,fa_no);
	}

	public ArticleFavoriteVO getOneAF(String mem_no) {
		return dao.findByPK(mem_no);
	}

	public ArticleFavoriteVO getOnefa(String fa_no) {
		return dao.findByPK(fa_no);
	}
	
	public List<ArticleFavoriteVO> getAll() {
		return dao.getAll();
	}
	
	public List<ArticleFavoriteVO>getAllfa(String fa_no){
		return dao.getAllfa(fa_no);
	}
	
	public List<ArticleFavoriteVO> getAll_FAV() {
		return dao.getAll_FAV();
	}
}
