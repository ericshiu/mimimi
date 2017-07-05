package com.product_speci.model;
import java.util.List;

public class ProductSpeciService {

	private ProductSpeci_interface dao;

	public ProductSpeciService() {
		dao = new ProductSpeciDAO();
	}

	public ProductSpeciVO addProductSpeci(String pro_no, String psp_name, String psp_list) {

		ProductSpeciVO pspVO = new ProductSpeciVO();
		pspVO.setPro_no(pro_no);
		pspVO.setPsp_name(psp_name);
		pspVO.setPsp_list(psp_list);
		dao.insert(pspVO);

		return pspVO;
	}

	public ProductSpeciVO updateProductSpeci(String psp_no,String pro_no, String psp_name, String psp_list) {

		ProductSpeciVO pspVO = new ProductSpeciVO();
		pspVO.setPsp_no(psp_no);
		pspVO.setPro_no(pro_no);
		pspVO.setPsp_name(psp_name);
		pspVO.setPsp_list(psp_list);
		dao.update(pspVO);

		return pspVO;
	}

	public void deleteProductSpeci(String psp_no) {
		dao.delete(psp_no);
	}

	public ProductSpeciVO getOneProductSpeci(String psp_no) {
		return dao.findByPrimaryKey(psp_no);
	}

	public List<ProductSpeciVO> getAll() {
		return dao.getAll();
	}
}

