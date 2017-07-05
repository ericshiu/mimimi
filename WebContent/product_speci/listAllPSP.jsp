<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product_speci.model.*"%>

<%
    ProductSpeciService PSPSvc = new ProductSpeciService();
    List<ProductSpeciVO> list = PSPSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>查詢所有商品規格資料 </title>
</head>
<body bgcolor='white'>


<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>商品規格編號</th>
		<th>商品編號</th>
		<th>商品規格名稱</th>
		<th>商品規格細項</th>
	</tr>

	<c:forEach var="pspVO" items="${list}">
		<tr align='center' valign='middle'>
			<td>${pspVO.psp_no}</td>
			<td>${pspVO.pro_no}</td>
			<td>${pspVO.psp_name}</td>
			<td>${pspVO.psp_list}</td>
	<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_speci/psp.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="psp_no" value="${pspVO.psp_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_speci/psp.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="psp_no" value="${pspVO.psp_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>