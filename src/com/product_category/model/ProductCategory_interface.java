package com.product_category.model;

import java.util.List;

import com.product_category.model.ProductCategoryVO;

public interface ProductCategory_interface {
	public void insert(ProductCategoryVO prcVO);
    public void update(ProductCategoryVO prcVO);
    public void delete(String prc_no);
    public ProductCategoryVO findByPrimaryKey(String prc_no);
    public List<ProductCategoryVO> getAll();
}
