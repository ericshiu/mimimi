<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>

<jsp:useBean id="picSvc" scope="page" class="com.product_picture.model.ProductPictureService" />
<jsp:useBean id="cateSvc" scope="page" class="com.product_category.model.ProductCategoryService" />
<%
    ProductService proSvc = new ProductService();
    List<ProductVO> list = proSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有商品資料查詢 - listAllPro.jsp</title>
</head>
<body>
<table border='1' cellpadding='5' cellspacing='0' width='1900'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<h3>所有商品資料 - ListAllPro.jsp</h3>
	</tr>
</table>


<table border='1' bordercolor='#CCCCFF' width='1900'>
	<tr>
		<th>商品編號</th>
		<th>商品類別編號</th>
		<th>商品照片</th>
		<th>商品名稱</th>
		<th>商品介紹</th>
		<th>商品價格</th>
		<th>媽媽適用起始週</th>
		<th>媽媽適用結束週</th>
		<th>寶寶適用起始月</th>
		<th>寶寶適用結束月</th>
		<th>修改商品</th>
		<th>查看照片</th>
	</tr>
	<c:forEach var="proVO" items="${list}">
	
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
			<c:forEach var="picVO" items="${picSvc.all}">
				<c:if test="${proVO.pro_no==picVO.pro_no}">
	                <img src="<%=request.getContextPath() %>/ProductPhoto?prp_no=${picVO.prp_no}" width=150px/>
                </c:if>
			</c:forEach>
			</td>
			<td>${proVO.pro_name}</td>
			<td>${proVO.pro_desc}</td>
			<td>${proVO.pro_price}</td>
			<td>${proVO.pro_age_ms}</td>
			<td>${proVO.pro_age_me}</td>
			<td>${proVO.pro_age_cs}</td>
			<td>${proVO.pro_age_ce}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/pro.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="pro_no" value="${proVO.pro_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/pro.do">
			    <input type="submit" value="查看商品">
			    <input type="hidden" name="pro_no" value="${proVO.pro_no}">
			    <input type="hidden" name="action" value="getOnePro"></FORM>
			</td>				
		</tr>
	</c:forEach>
</table>

</body>
</html>
