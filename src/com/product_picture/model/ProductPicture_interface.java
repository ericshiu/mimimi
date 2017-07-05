package com.product_picture.model;

import java.util.List;
import java.util.Set;

public interface ProductPicture_interface {
	public void insert(ProductPictureVO prpVO);
    public void update(ProductPictureVO prpVO);
    public void delete(String prp_no);
    public ProductPictureVO findByPrimaryKey(String prp_no);
    public List<ProductPictureVO> getAll();
    public Set<ProductPictureVO> findByProNo(String pro_no);
    public List<ProductPictureVO> findPicByProNo(String pro_no);
    
    public void insert2 (ProductPictureVO proPictureVO , java.sql.Connection con);
}
