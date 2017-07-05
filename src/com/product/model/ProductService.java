package com.product.model;

import java.util.List;

import com.product_picture.model.ProductPictureVO;
import com.product_speci.model.ProductSpeciVO;
public class ProductService {

	private Product_interface dao;

	public ProductService() {
		dao = new ProductDAO();
	}

	public void addProduct(ProductVO proVO , List<ProductPictureVO> listPic,List<ProductSpeciVO> listSpeci) {
		dao.insertProWithPicSpeci(proVO, listPic, listSpeci);
	}

	public ProductVO updateProduct(String pro_no,String prc_no, String fir_no,String pro_name,String pro_desc,Integer pro_price,
			String pro_range, Integer pro_age_ms,Integer pro_age_me,Integer pro_age_cs,Integer pro_age_ce,String pro_qa) {

		ProductVO proVO = new ProductVO();

		proVO.setPro_no(pro_no);
		proVO.setPrc_no(prc_no);
		proVO.setFir_no(fir_no);
		proVO.setPro_name(pro_name);
		proVO.setPro_desc(pro_desc);
		proVO.setPro_price(pro_price);
		proVO.setPro_range(pro_range);
		proVO.setPro_age_ms(pro_age_ms);
		proVO.setPro_age_me(pro_age_me);
		proVO.setPro_age_cs(pro_age_cs);
		proVO.setPro_age_ce(pro_age_ce);
		proVO.setPro_qa(pro_qa);
		dao.update(proVO);

		return proVO;
	}

	public ProductVO getOneProduct(String pro_no) {
		return dao.findByPrimaryKey(pro_no);
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}
	
	public List<ProductVO> getProWithFirm(String fir_no) {
		return dao.getProByFir(fir_no);
	}
}

