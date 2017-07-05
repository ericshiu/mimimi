package com.article_favorite.model;

import java.util.List;

public interface ArticleFavoriteDAO_interface {
	public void insert(ArticleFavoriteVO articleFavoriteVO);
	public void update(ArticleFavoriteVO articleFavoriteVO);
	public void delete(String mem_no,String fa_no);
	public ArticleFavoriteVO findByPK(String mem_no);
	public ArticleFavoriteVO findByPKfa(String fa_no);
	public ArticleFavoriteVO getmemfa(String mem_no,String fa_no);
	public List<ArticleFavoriteVO>getAll();
	public List<ArticleFavoriteVO>getAllfa(String fa_no);
	public List<ArticleFavoriteVO>getAll_FAV();
}
