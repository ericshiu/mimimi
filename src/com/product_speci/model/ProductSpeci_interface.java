package com.product_speci.model;

import java.util.List;

import com.product_speci.model.ProductSpeciVO;

public interface ProductSpeci_interface {
	public void insert(ProductSpeciVO pspVO);
    public void update(ProductSpeciVO pspVO);
    public void delete(String psp_no);
    public ProductSpeciVO findByPrimaryKey(String psp_no);
    public List<ProductSpeciVO> getAll();
    
    public void insert3 (ProductSpeciVO proSpeciVO, java.sql.Connection con);
    
    
}
	

