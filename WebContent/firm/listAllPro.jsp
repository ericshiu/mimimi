<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*,com.firm.model.*,com.member.model.*,com.postpartum_care.model.*"%>

<jsp:useBean id="picSvc" scope="page" class="com.product_picture.model.ProductPictureService" />
<jsp:useBean id="cateSvc" scope="page" class="com.product_category.model.ProductCategoryService" />

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
	FirmVO userVO = (FirmVO) pageContext.getAttribute("userVO");
	session.setAttribute("pageReq", "/firm/listAllpro.jsp");
	ProductVO proVO = (ProductVO) request.getAttribute("proVO");	
%>

<%
    ProductService proSvc = new ProductService();
    List<ProductVO> list = proSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">

<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<html>
<head>
<title>所有商品資料查詢 - listAllPro.jsp</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">
<script src="https://code.jquery.com/jquery.js"></script>
</head>
<body>

<jsp:include page="/front_end/frontTOP.jsp" flush="true"/>





<div class="container">


	<div style="background-color:#dff0d8;height:50px;border-radius:2%;padding-top:2px;padding-left:5px;">
	<button type="button"
		onclick="window.location.href='<%=request.getContextPath()%>/firm/addPro.jsp'"
		class="btn btn-warning" style="font-size:20px;background-color:#b39873;border-color:#b39873;">新增商品</button>
	</div>
	<br><br>
	<div class="col-xs-12  col-lg-12">
	
	<div class="row">
		<div class="panel panel-success panel-group">
			<div class="panel-heading" align="center">
				廠商訂單
			</div>
		<table class="table table-hover table-striped" align="center">
		<tr align='center' valign='middle'>
			<th>編號</th>
			<th>類別</th>
			<th>照片</th>
			<th>名稱</th>
			<th width="300px" style="text-align:center;">商品介紹</th>
			<th>價格</th>
			<th>媽媽適用起始週</th>
			<th>寶寶適用起始月</th>
			<th>修改商品</th>
			<th>編輯照片</th>
		</tr>
		<c:forEach var="proVO" items="${list}">
	<c:if test="${proVO.fir_no==userVO.fir_no}">
	
		<tr align='center' valign='middle'>
			<td>${proVO.pro_no}</td>
			<td>	
			<c:forEach var="cateVO" items="${cateSvc.all}">
				<c:if test="${proVO.prc_no==cateVO.prc_no}">
				${cateVO.prc_main}
				</c:if>
			</c:forEach>	
			</td>
			<td>

			<c:forEach var="picVO" items="${picSvc.getOnePicByProNo(proVO.pro_no)}" varStatus="c">
			<div style="background: #fff url('<%=request.getContextPath() %>/ProductPhoto?prp_no=${picVO.prp_no}') no-repeat center top; background-size: cover; width: 200px;height:200px;border-radius:20%;box-shadow:2px 2px 2px rgba(80%,80%,80%,0.6),4px 4px 6px rgba(80%,80%,80%,0.4),6px 6px 12px rgba(80%,80%,80%,0.4);">
			</div>
<%-- 				<img src="<%=request.getContextPath() %>/ProductPhoto?prp_no=${picVO.prp_no}" width="150px"/>    --%>
			</c:forEach>
			</td>
			<td>${proVO.pro_name}</td>
			<td width="50px">${proVO.pro_desc}</td>
			<td>${proVO.pro_price}</td>
			<td>${proVO.pro_age_ms}月~${proVO.pro_age_me}月</td>

			<td>${proVO.pro_age_cs}週~${proVO.pro_age_ce}週</td>

			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/pro.do">
			     <button type="submit" value="" class="btn btn-info">修改</button>
			     <input type="hidden" name="pro_no" value="${proVO.pro_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_picture/prp.do">
			    <button type="submit" value="" class="btn btn-success">查看照片</button>
			    <input type="hidden" name="pro_no" value="${proVO.pro_no}">
			    <input type="hidden" name="action"value="getOne_Prp_Display"></FORM>
			</td>	
		</tr>
		</c:if>
	</c:forEach>
						</table>
			
	
		</div>
	</div>
	</div>
</div>

































<!-- <div class="container"> -->
<!-- <table border='1' bordercolor='#CCCCFF'> -->
<!-- 	<tr> -->
<!-- 		<th>商品編號</th> -->
<!-- 		<th>商品類別</th> -->
<!-- 		<th>商品照片</th> -->
<!-- 		<th>商品名稱</th> -->
<!-- 		<th width="300px">商品介紹</th> -->
<!-- 		<th>商品價格</th> -->
<!-- 		<th>媽媽適用起始週</th> -->
<!-- 		<th>寶寶適用起始月</th> -->
<!-- 		<th>修改商品</th> -->
<!-- 		<th>編輯照片</th> -->
<!-- 	</tr> -->
<%-- 	<c:forEach var="proVO" items="${list}"> --%>
<%-- 	<c:if test="${proVO.fir_no==userVO.fir_no}"> --%>
	
<!-- 		<tr align='center' valign='middle'> -->
<%-- 			<td>${proVO.pro_no}</td> --%>
<!-- 			<td>	 -->
<%-- 			<c:forEach var="cateVO" items="${cateSvc.all}"> --%>
<%-- 				<c:if test="${proVO.prc_no==cateVO.prc_no}"> --%>
<%-- 				${cateVO.prc_main} --%>
<%-- 				</c:if> --%>
<%-- 			</c:forEach>	 --%>
<!-- 			</td> -->
<!-- 			<td> -->

<%-- 			<c:forEach var="picVO" items="${picSvc.getOnePicByProNo(proVO.pro_no)}" varStatus="c"> --%>
<%-- 				<img src="<%=request.getContextPath() %>/ProductPhoto?prp_no=${picVO.prp_no}" width="150px"/>    --%>
<%-- 			</c:forEach> --%>
<!-- 			</td> -->
<%-- 			<td>${proVO.pro_name}</td> --%>
<%-- 			<td width="50px">${proVO.pro_desc}</td> --%>
<%-- 			<td>${proVO.pro_price}</td> --%>
<%-- 			<td>${proVO.pro_age_ms}月~${proVO.pro_age_me}月</td> --%>

<%-- 			<td>${proVO.pro_age_cs}週~${proVO.pro_age_ce}週</td> --%>

<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/pro.do"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="pro_no" value="${proVO.pro_no}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_picture/prp.do"> --%>
<!-- 			    <input type="submit" value="查看照片"> -->
<%-- 			    <input type="hidden" name="pro_no" value="${proVO.pro_no}"> --%>
<!-- 			    <input type="hidden" name="action"value="getOne_Prp_Display"></FORM> -->
<!-- 			</td>	 -->
<!-- 		</tr> -->
<%-- 		</c:if> --%>
<%-- 	</c:forEach> --%>
<!-- </table> -->
</div>

<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>

</html>
