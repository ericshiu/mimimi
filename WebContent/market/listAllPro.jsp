<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*,com.product.model.*,com.product_picture.model.*, com.cart.controller.* ,com.cart.model.*,com.firm.model.*,com.postpartum_care.model.*"%>

<jsp:useBean id="picSvc" scope="page" class="com.product_picture.model.ProductPictureService" />
<%
String userType = (String) session.getAttribute("userType"); 

if (userType != null){
	if (userType.equals("PC")){
		PostpartumCareVO userVO = (PostpartumCareVO) session.getAttribute("userVO");
		pageContext.setAttribute("userVO",userVO);
	} else if (userType.equals("Fir")){
		FirmVO userVO = (FirmVO) session.getAttribute("userVO");
		pageContext.setAttribute("userVO",userVO);
	} else if (userType.equals("Mem")){
		MemberVO userVO = (MemberVO) session.getAttribute("userVO");  
		pageContext.setAttribute("userVO",userVO);
	} 
} else {
	userType = "Non";
}
	MemberVO userVO = (MemberVO) pageContext.getAttribute("userVO");
	session.setAttribute("pageReq", "/market/listAllPro.jsp");
%>

<%
    ProductService proSvc = new ProductService();
    List<ProductVO> list = proSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/market/css/Market.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/market/css/PostpartumCare_index.css">

<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
<script	src="https://code.jquery.com/jquery-3.2.1.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!-- 引用icon -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<style>
.border{
	z-index: 9990;
	border: 5px solid transparent;
	border-image: url('../img/border-b.png') 5 repeat;
	margin-top: -5px;
}
</style>

<title>媽咪樂寶商城</title>
</head>
<body bgcolor='white'>
<jsp:include page="../front_end/frontTOP.jsp" flush="true"/>

<div class="container" style="clear:both;">
	<div class="row">
		<div class="col-md-3 col-sm-4" style="margin-top:63px;">
			<ul class="nav nav-pills nav-stacked">
  			<div class="sidebar-title"><span style="color:#BA9D75;font-size:20px;">寶寶生活</span></div>
  				<li class="active"><a href="#1" data-toggle="tab"><span style="font-size:15px;">幼兒服裝</span></a></li>
  				<li><a href="#2" data-toggle="tab"><span style="font-size:15px;">幼兒用品</span></a></li>
  				<li><a href="#3" data-toggle="tab"><span style="font-size:15px;">幼兒消耗品</span></a></li>
  				<li><a href="#4" data-toggle="tab"><span style="font-size:15px;">幼兒玩具</span></a></li>
  			<div class="sidebar-title"><span style="color:#BA9D75;font-size:20px;">媽咪生活</span></div>
  				<li><a href="#5" data-toggle="tab"><span style="font-size:15px;">媽咪服裝</span></a></li>
  				<li><a href="#6" data-toggle="tab"><span style="font-size:15px;">媽咪好物</span></a></li>
  			<div class="sidebar-title"><span style="color:#BA9D75;font-size:20px;">貼心組合</span></div>
  				<li><a href="#7" data-toggle="tab"><span style="font-size:15px;">可愛禮包</span></a></li>
			</ul>			
		</div>
		<div class="col-md-9 col-sm-8" >				
			<div class="row">
<!-- 1 -->
				<div class="tab-content ">
					<div class="tab-pane active" id="1">
					<c:forEach var="proVO" items="${list}">
					<c:if test="${proVO.prc_no=='PRC0000001'}">
						<div class="col-lg-4 col-md-4 col-sm-6" >
							<div class="single-product mb-30  white-bg">
								<div class="product-img pt-20" style="height:300px;">
									<c:forEach var="picVO" items="${picSvc.getOnePicByProNo(proVO.pro_no)}">	
		 	                			<img style="width: 200px;height: 270px;" src="<%=request.getContextPath() %>/ProductPhoto?prp_no=${picVO.prp_no}"/>	
		 							</c:forEach>
								</div>	
								<div class="product-content product-i">
									<div class="pro-title">
										<h4><a href="#" style="color:#5f94bf;font-size:20px;">${proVO.pro_name}</a></h4>
									</div>
									<div class="price-box">
										<span class="price product-price" style="color:#5f94bf;font-size:20px;">$:${proVO.pro_price}</span>
									</div>
									<div class="product-icon">
										<div class="product-icon-left f-left">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/pro.do">
					    						<button type="submit" value="" class="btn " style="background-color:#48c7c1;color:#fff;">查看商品</button>
					    						<input type="hidden" name="pro_no" value="${proVO.pro_no}">
					    						<input type="hidden" name="action" value="getOnePro">
					    					</FORM>
										</div>
									</div>
								</div>
							</div>											
						</div>
					</c:if>
					</c:forEach>
					</div>
		<!-- 2 -->
					<div class="tab-pane" id="2">
					<c:forEach var="proVO" items="${list}">
					<c:if test="${proVO.prc_no=='PRC0000002'}">
						<div class="col-lg-4 col-md-4 col-sm-6" >
							<div class="single-product mb-30  white-bg">
								<div class="product-img pt-20" style="height:300px;">
									<c:forEach var="picVO" items="${picSvc.getOnePicByProNo(proVO.pro_no)}">	
		 	                			<img style="width: 200px;height: 270px;" src="<%=request.getContextPath() %>/ProductPhoto?prp_no=${picVO.prp_no}"/>	
		 							</c:forEach>
								</div>	
								<div class="product-content product-i">
									<div class="pro-title">
										<h4><a href="#" style="color:#5f94bf;font-size:20px;">${proVO.pro_name}</a></h4>
									</div>
									<div class="price-box">
										<span class="price product-price" style="color:#5f94bf;font-size:20px;">$:${proVO.pro_price}</span>
									</div>
									<div class="product-icon">
										<div class="product-icon-left f-left">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/pro.do">
					    						<button type="submit" value="" class="btn " style="background-color:#48c7c1;color:#fff;">查看商品</button>
					    						<input type="hidden" name="pro_no" value="${proVO.pro_no}">
					    						<input type="hidden" name="action" value="getOnePro">
					    					</FORM>
										</div>
									</div>
								</div>
							</div>											
						</div>
					</c:if>
					</c:forEach>
					</div>
		<!-- 3 -->
					<div class="tab-pane" id="3">
					<c:forEach var="proVO" items="${list}">
					<c:if test="${proVO.prc_no=='PRC0000003'}">
						<div class="col-lg-4 col-md-4 col-sm-6" >
							<div class="single-product mb-30  white-bg">
								<div class="product-img pt-20" style="height:300px;">
									<c:forEach var="picVO" items="${picSvc.getOnePicByProNo(proVO.pro_no)}">	
		 	                			<img style="width: 200px;height: 270px;" src="<%=request.getContextPath() %>/ProductPhoto?prp_no=${picVO.prp_no}"/>	
		 							</c:forEach>
								</div>	
								<div class="product-content product-i">
									<div class="pro-title">
										<h4><a href="#" style="color:#5f94bf;font-size:20px;">${proVO.pro_name}</a></h4>
									</div>
									<div class="price-box">
										<span class="price product-price" style="color:#5f94bf;font-size:20px;">$:${proVO.pro_price}</span>
									</div>
									<div class="product-icon">
										<div class="product-icon-left f-left">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/pro.do">
					    						<button type="submit" value="" class="btn " style="background-color:#48c7c1;color:#fff;">查看商品</button>
					    						<input type="hidden" name="pro_no" value="${proVO.pro_no}">
					    						<input type="hidden" name="action" value="getOnePro">
					    					</FORM>
										</div>
									</div>
								</div>
							</div>											
						</div>
					</c:if>
					</c:forEach>
					</div>
		<!-- 4			 -->
					<div class="tab-pane" id="4">
					<c:forEach var="proVO" items="${list}">
					<c:if test="${proVO.prc_no=='PRC0000004'}">
						<div class="col-lg-4 col-md-4 col-sm-6" >
							<div class="single-product mb-30  white-bg">
								<div class="product-img pt-20" style="height:300px;">
									<c:forEach var="picVO" items="${picSvc.getOnePicByProNo(proVO.pro_no)}">	
		 	                			<img style="width: 200px;height: 270px;" src="<%=request.getContextPath() %>/ProductPhoto?prp_no=${picVO.prp_no}"/>	
		 							</c:forEach>
								</div>	
								<div class="product-content product-i">
									<div class="pro-title">
										<h4><a href="#" style="color:#5f94bf;font-size:20px;">${proVO.pro_name}</a></h4>
									</div>
									<div class="price-box">
										<span class="price product-price" style="color:#5f94bf;font-size:20px;">$:${proVO.pro_price}</span>
									</div>
									<div class="product-icon">
										<div class="product-icon-left f-left">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/pro.do">
					    						<button type="submit" value="" class="btn " style="background-color:#48c7c1;color:#fff;">查看商品</button>
					    						<input type="hidden" name="pro_no" value="${proVO.pro_no}">
					    						<input type="hidden" name="action" value="getOnePro">
					    					</FORM>
										</div>
									</div>
								</div>
							</div>											
						</div>
					</c:if>
					</c:forEach>
					</div>
		<!-- 5			 -->
					<div class="tab-pane" id="5">
					<c:forEach var="proVO" items="${list}">
					<c:if test="${proVO.prc_no=='PRC0000005'}">
						<div class="col-lg-4 col-md-4 col-sm-6" >
							<div class="single-product mb-30  white-bg">
								<div class="product-img pt-20" style="height:300px;">
									<c:forEach var="picVO" items="${picSvc.getOnePicByProNo(proVO.pro_no)}">	
		 	                			<img style="width: 200px;height: 270px;" src="<%=request.getContextPath() %>/ProductPhoto?prp_no=${picVO.prp_no}"/>	
		 							</c:forEach>
								</div>	
								<div class="product-content product-i">
									<div class="pro-title">
										<h4><a href="#" style="color:#5f94bf;font-size:20px;">${proVO.pro_name}</a></h4>
									</div>
									<div class="price-box">
										<span class="price product-price" style="color:#5f94bf;font-size:20px;">$:${proVO.pro_price}</span>
									</div>
									<div class="product-icon">
										<div class="product-icon-left f-left">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/pro.do">
					    						<button type="submit" value="" class="btn " style="background-color:#48c7c1;color:#fff;">查看商品</button>
					    						<input type="hidden" name="pro_no" value="${proVO.pro_no}">
					    						<input type="hidden" name="action" value="getOnePro">
					    					</FORM>
										</div>
									</div>
								</div>
							</div>											
						</div>
					</c:if>
					</c:forEach>
					</div>
		<!-- 6			 -->
					<div class="tab-pane" id="6">
					<c:forEach var="proVO" items="${list}">
					<c:if test="${proVO.prc_no=='PRC0000006'}">
						<div class="col-lg-4 col-md-4 col-sm-6" >
							<div class="single-product mb-30  white-bg">
								<div class="product-img pt-20" style="height:300px;">
									<c:forEach var="picVO" items="${picSvc.getOnePicByProNo(proVO.pro_no)}">	
		 	                			<img style="width: 200px;height: 270px;" src="<%=request.getContextPath() %>/ProductPhoto?prp_no=${picVO.prp_no}"/>	
		 							</c:forEach>
								</div>	
								<div class="product-content product-i">
									<div class="pro-title">
										<h4><a href="#" style="color:#5f94bf;font-size:20px;">${proVO.pro_name}</a></h4>
									</div>
									<div class="price-box">
										<span class="price product-price" style="color:#5f94bf;font-size:20px;">$:${proVO.pro_price}</span>
									</div>
									<div class="product-icon">
										<div class="product-icon-left f-left">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/pro.do">
					    						<button type="submit" value="" class="btn " style="background-color:#48c7c1;color:#fff;">查看商品</button>
					    						<input type="hidden" name="pro_no" value="${proVO.pro_no}">
					    						<input type="hidden" name="action" value="getOnePro">
					    					</FORM>
										</div>
									</div>
								</div>
							</div>											
						</div>
					</c:if>
					</c:forEach>
					</div>
		<!-- 7			 -->
					<div class="tab-pane" id="7">
					<c:forEach var="proVO" items="${list}">
					<c:if test="${proVO.prc_no=='PRC0000007'}">
						<div class="col-lg-4 col-md-4 col-sm-6" >
							<div class="single-product mb-30  white-bg">
								<div class="product-img pt-20" style="height:300px;">
									<c:forEach var="picVO" items="${picSvc.getOnePicByProNo(proVO.pro_no)}">	
		 	                			<img style="width: 200px;height: 270px;" src="<%=request.getContextPath() %>/ProductPhoto?prp_no=${picVO.prp_no}"/>	
		 							</c:forEach>
								</div>	
								<div class="product-content product-i">
									<div class="pro-title">
										<h4><a href="#" style="color:#5f94bf;font-size:20px;">${proVO.pro_name}</a></h4>
									</div>
									<div class="price-box">
										<span class="price product-price" style="color:#5f94bf;font-size:20px;">$:${proVO.pro_price}</span>
									</div>
									<div class="product-icon">
										<div class="product-icon-left f-left">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/pro.do">
					    						<button type="submit" value="" class="btn " style="background-color:#48c7c1;color:#fff;">查看商品</button>
					    						<input type="hidden" name="pro_no" value="${proVO.pro_no}">
					    						<input type="hidden" name="action" value="getOnePro">
					    					</FORM>
										</div>
									</div>
								</div>
							</div>											
						</div>
					</c:if>
					</c:forEach>
					</div>
				</div>
			</div>
		</div>															
	</div>
</div>

<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>
</html>
