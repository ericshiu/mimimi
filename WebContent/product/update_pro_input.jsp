<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%
    ProductVO proVO = (ProductVO) request.getAttribute("proVO");
%>
<html>
<head>
<title>商品資料修改 - update_pro_input.jsp</title></head>


<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCCC' align='center' valign='middle' height='20'>
		<td>
		<h3>商品資料修改</h3>
	</tr>
</table>

<h3>資料修改:</h3>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<FORM METHOD="post" ACTION="pro.do" name="form1" >
<table border="0">
	
	<tr>
		<td>商品編號:<font color=red><b>*</b></font></td>
		<td><%=proVO.getPro_no()%></td>
	</tr>
	<tr>
		<td>商品類別編號:</td>
		<td><%=proVO.getPrc_no()%></td>
	</tr>
	<tr>
		<td>商品名稱:</td>
		<td><input type="TEXT" name="pro_name" size="45" value="<%= proVO.getPro_name()%>" /></td>
	</tr>
	<tr>
		<td>商品介紹:</td>
		<td><input type="TEXT" name="pro_desc" size="45" 
			value="<%= proVO.getPro_desc()%>" /></td>
	</tr>
	<tr>
		<td>商品價錢:</td>
		<td><input type="TEXT" name="pro_price" size="45" 
			value="<%= proVO.getPro_price()%>" /></td>
	</tr>

	<tr>
		<td>媽媽適用商品起始週:</td>
		<td><input type="TEXT" name="pro_age_ms" size="45" 
			value="<%= proVO.getPro_age_ms()%>" /></td>
	</tr>
	<tr>
		<td>媽媽適用商品結束週:</td>
		<td><input type="TEXT" name="pro_age_me" size="45"
			value="<%= proVO.getPro_age_me()%>" /></td>
	</tr>
	<tr>
		<td>寶寶適用商品起始月:</td>
		<td><input type="TEXT" name="pro_age_cs" size="45"
			value="<%= proVO.getPro_age_cs()%>" /></td>
	</tr>
	<tr>
		<td>寶寶適用商品結束月:</td>
		<td><input type="TEXT" name="pro_age_ce" size="45"
			value="<%= proVO.getPro_age_ce()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="pro_no" value="<%=proVO.getPro_no()%>">
<input type="hidden" name="prc_no" value="<%=proVO.getPrc_no()%>">
<input type="hidden" name="fir_no" value="<%=proVO.getFir_no()%>">

<input type="submit" value="送出修改"></FORM>

</body>
</html>