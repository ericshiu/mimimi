<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*,com.product_picture.model.*"%>

<jsp:useBean id="picSvc" scope="page" class="com.product_picture.model.ProductPictureService" />
<%
    ProductService proSvc = new ProductService();
    List<ProductVO> list = proSvc.getAll();
    ProductVO pro1 = list.get(1);
    ProductVO pro2 = list.get(2);
    ProductVO pro3 = list.get(3);
    pageContext.setAttribute("pro1",pro1);
    pageContext.setAttribute("pro2",pro2);
    pageContext.setAttribute("pro3",pro3);
    
    ProductPictureService prpSvc = new ProductPictureService();
    Set<ProductPictureVO> prp1 = prpSvc.getPicByProNO(pro1.getPro_no());
    Set<ProductPictureVO> prp2 = prpSvc.getPicByProNO(pro2.getPro_no());
    Set<ProductPictureVO> prp3 = prpSvc.getPicByProNO(pro3.getPro_no());

    ProductPictureVO proPic1 = prp1.iterator().next();
    ProductPictureVO proPic2 = prp2.iterator().next();
    ProductPictureVO proPic3 = prp3.iterator().next();
    
    pageContext.setAttribute("proPic1",proPic1);
    pageContext.setAttribute("proPic2",proPic2);
    pageContext.setAttribute("proPic3",proPic3);
    
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!-- 引用icon -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- 引用Css -->
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css">
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script src="https://code.jquery.com/jquery.js"></script>		
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css" href="css/PostpartumCare_index.css">



<link rel="stylesheet" href="css/bootstrap.min.css">
		<!-- font-awesome.min.css -->
        <link rel="stylesheet" href="css/font-awesome.min.css">
		<!-- owl.carousel.css -->
        <link rel="stylesheet" href="css/owl.carousel.css">
		<!-- owl.carousel.css -->
        <link rel="stylesheet" href="css/meanmenu.min.css">
		<!-- jquery-ui.min.css -->
        <link rel="stylesheet" href="css/jquery-ui.min.css">
		<!-- shortcode/shortcodes.css -->
        <link rel="stylesheet" href="css/shortcode/shortcodes.css">
		<!-- nivo-slider.css -->
        <link rel="stylesheet" href="css/nivo-slider.css">
        <!-- style.css -->
        <link rel="stylesheet" href="style.css">
        
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="s-title"><h2>商品</h2></div>	
			<div class="col-xs-12 col-sm-3 ps-item">
			</div>	   
			<div class="col-xs-12 col-sm-3 ps-item" id="PC_1">
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/postpartum_care/PC.do">
					<img src="<%=request.getContextPath() %>/ProductPhoto?prp_no=${proPic1.prp_no}" width=150px class="img-responsive"/>
					<h3><%= pro1.getPro_name() %></h3>
					<p><%= pro1.getPro_desc() %></p>
					<p><%= pro1.getPro_price()%></p>
					<div class="input-group" style="text-align: center; width: 100%;">
						<input type="submit" value="GO" class="btn btn-info">
					</div>
				</form>
			</div>
			<div class="col-xs-12 col-sm-3 ps-item" id="PC_1">
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/postpartum_care/PC.do">
					<img src="<%=request.getContextPath() %>/ProductPhoto?prp_no=${proPic2.prp_no}" width=150px class="img-responsive"/>
					<h3><%= pro2.getPro_name() %></h3>
					<p><%= pro2.getPro_desc() %></p>
					<p><%= pro2.getPro_price()%></p>
					<div class="input-group" style="text-align: center; width: 100%;">
						<input type="submit" value="GO" class="btn btn-info">
					</div>
				</form>
			</div>						
			<div class="col-xs-12 col-sm-3 ps-item" id="PC_1">
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/postpartum_care/PC.do">
					<img src="<%=request.getContextPath() %>/ProductPhoto?prp_no=${proPic3.prp_no}" width=150px class="img-responsive"/>
					<h3><%= pro3.getPro_name() %></h3>
					<p><%= pro3.getPro_desc() %></p>
					<p><%= pro3.getPro_price()%></p>
					<div class="input-group" style="text-align: center; width: 100%;">
						<input type="submit" value="GO" class="btn btn-info">
					</div>
				</form>
			</div>	
		</div>
	</div>
</div>

<div class="tab-content">
									<div role="tabpanel" class="tab-pane active" id="home">
										<div class="row">
											<div class="col-lg-4 col-md-4 col-sm-6">
												<div class="single-product mb-30  white-bg">
													<div class="product-img pt-20">
														<a href="#"><img src="img/product/1.jpg" alt="" /></a>
													</div>
													<div class="product-content product-i">
														<div class="pro-title">
															<h4><a href="product-details.html">Moebius Table</a></h4>
														</div>
														<div class="pro-rating ">
															<a href="#"><i class="fa fa-star"></i></a>
															<a href="#"><i class="fa fa-star"></i></a>
															<a href="#"><i class="fa fa-star"></i></a>
															<a href="#"><i class="fa fa-star"></i></a>
															<a href="#"><i class="fa fa-star-o"></i></a>
														</div>
														<div class="price-box">
															<span class="price product-price">$400.00</span>
														</div>
														<div class="product-icon">
															<div class="product-icon-left f-left">
																<a href="#"><i class="fa fa-shopping-cart"></i>Add to Cart</a>
															</div>
															<div class="product-icon-right floatright">
																<a href="#" data-toggle="tooltip" title="Compare"><i class="fa fa-exchange"></i></a>
																<a href="#" data-toggle="tooltip" title="Wishlist"><i class="fa fa-heart"></i></a>
															</div>
														</div>
													</div>
													<span class="new">new</span>
												</div>											
											</div>
											</div>
											</div>
											</div>
</body>
</html>

