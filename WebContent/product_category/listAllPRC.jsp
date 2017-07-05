<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product_category.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%

    ProductCategoryService PRCSvc = new ProductCategoryService();
    List<ProductCategoryVO> list = PRCSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>

</head>
<body bgcolor='white'>



<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>商品類別編號</th>
		<th>商品主類別</th>
		<th>商品次類別</th>
	</tr>

	<c:forEach var="prcVO" items="${list}">
		<tr align='center' valign='middle'>
			<td>${prcVO.prc_no}</td>
			<td>${prcVO.prc_main}</td>
			<td>${prcVO.prc_sub}</td>
					
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_category/prc.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="prc_no" value="${prcVO.prc_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_category/prc.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="prc_no" value="${prcVO.prc_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>
