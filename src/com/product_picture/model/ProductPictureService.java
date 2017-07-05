package com.product_picture.model;

import java.util.List;
import java.util.Set;

public class ProductPictureService{


	private ProductPicture_interface dao;

	public ProductPictureService() {
		dao = new ProductPictureDAO();
	}
	public ProductPictureVO addProductPicture(byte[] prp_picture, String pro_no) {

		ProductPictureVO prpVO = new ProductPictureVO();
		prpVO.setPrp_picture(prp_picture);
		prpVO.setPro_no(pro_no);

		dao.insert(prpVO);

		return prpVO;
	}

	public ProductPictureVO updateProductPicture(String prp_no, byte[] prp_picture,String pro_no) {

		ProductPictureVO prpVO = new ProductPictureVO();
		prpVO.setPrp_no(prp_no);
		prpVO.setPrp_picture(prp_picture);
		prpVO.setPro_no(pro_no);
		
		dao.update(prpVO);

		return prpVO;
	}

	public void deleteProductPicture(String prp_no) {
		dao.delete(prp_no);
	}

	public ProductPictureVO getOneProductPicture(String prp_no) {
		return dao.findByPrimaryKey(prp_no);
	}

	public List<ProductPictureVO> getAll() {
		return dao.getAll();
	}
	
	public Set<ProductPictureVO> getPicByProNO(String pro_no) {
		return dao.findByProNo(pro_no);
	}
	
	public List<ProductPictureVO> getOnePicByProNo(String pro_no) {
		return dao.findPicByProNo(pro_no);
	}

}

