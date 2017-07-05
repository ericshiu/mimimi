package com.product_category.model;

import java.util.List;


public class ProductCategoryService {

	private ProductCategory_interface dao;

	public ProductCategoryService() {
		dao = new ProductCategoryDAO();
	}

	public ProductCategoryVO addProductCategory(String prc_main, String prc_sub) {

		ProductCategoryVO prcVO = new ProductCategoryVO();
		prcVO.setPrc_main(prc_main);
		prcVO.setPrc_sub(prc_sub);
		dao.insert(prcVO);

		return prcVO;
	}

	public ProductCategoryVO updateProductCategory(String prc_no, String prc_main, String prc_sub) {

		ProductCategoryVO prcVO = new ProductCategoryVO();

		prcVO.setPrc_no(prc_no);
		prcVO.setPrc_main(prc_main);
		prcVO.setPrc_sub(prc_sub);
		dao.update(prcVO);

		return prcVO;
	}

	public void deleteProductCategory(String prc_no) {
		dao.delete(prc_no);
	}

	public ProductCategoryVO getOneProductCategory(String prc_no) {
		return dao.findByPrimaryKey(prc_no);
	}

	public List<ProductCategoryVO> getAll() {
		return dao.getAll();
	}
}
