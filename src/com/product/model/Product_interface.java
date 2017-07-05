package com.product.model;

import java.util.List;

import com.product.model.ProductVO;
import com.product_picture.model.ProductPictureVO;
import com.product_speci.model.ProductSpeciVO;

public interface Product_interface {
	
	public void insertProWithPicSpeci(ProductVO proVO , List<ProductPictureVO> listPic,List<ProductSpeciVO> listSpeci);
    public void update(ProductVO proVO);
    public ProductVO findByPrimaryKey(String pro_no);
    public List<ProductVO> getProByFir(String fir_no);
    
    public List<ProductVO> getAll();
}
