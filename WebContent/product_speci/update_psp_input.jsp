<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_speci.model.*"%>
<%
ProductSpeciVO pspVO = (ProductSpeciVO) request.getAttribute("pspVO");
%>
<html>
<head>
<title>商品規格資料修改 - update_psp_input.jsp</title></head>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>商品規格資料修改 - update_psp_input.jsp</h3>
		<a href="select_page.jsp"><img src="images/LOGO.png" width="100" height="32" border="0">回首頁</a></td>
	</tr>
</table>

<h3>商品規格資料修改:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<FORM METHOD="post" ACTION="psp.do" name="form1" >
<table border="0">
		
	<tr>
		<td>商品規格編號:<font color=red><b>*</b></font></td>
		<td><%=pspVO.getPsp_no()%></td>
	</tr>
	<tr>
		<td>商品編號:</td>
		<td><%=pspVO.getPro_no()%></td>
	</tr>
	<tr>
		<td>商品規格顏色:</td>
		<td><input type="TEXT" name="psp_name" size="45" 
			value="<%= pspVO.getPsp_name()%>" /></td>
	</tr>
	<tr>
		<td>商品尺寸:</td>
		<td><input type="TEXT" name="psp_list" size="45" 
			value="<%= pspVO.getPsp_list()%>" /></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="psp_no" value="<%=pspVO.getPsp_no()%>">
<input type="hidden" name="pro_no" value="<%=pspVO.getPro_no()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>