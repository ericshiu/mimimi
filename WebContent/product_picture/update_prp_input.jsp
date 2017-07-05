<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_picture.model.*"%>
<%
ProductPictureVO prpVO = (ProductPictureVO) request.getAttribute("prpVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<title>商品照片資料修改 - update_emp_input.jsp</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/RegisterPC.css">
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script src="https://code.jquery.com/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">
</head>

<body bgcolor='white'>
<jsp:include page="/front_end/frontTOP.jsp" flush="true"/>
<table border='1' cellpadding='3' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>商品照片資料修改 - update_prp_input.jsp</h3>
		<a href="select_page.jsp"><img src="images/LOGO.png" width="100" height="32" border="0">回首頁</a></td>
	</tr>
</table>

<h3>資料修改:</h3>
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

<FORM METHOD="post" ACTION="prp.do" name="form1" enctype="multipart/form-data">
<table border="0">
	<tr>
		<td>商品照片編號:<font color=red><b>*</b></font></td>
		<td><%=prpVO.getPrp_no()%></td>
	</tr>
	
	<tr>
		<td>商品照片:</td>
		<td><input type="file" name="prp_picture" size="45" 
		      value="<%=prpVO.getPrp_picture()%>" /></td>
	</tr>

	
	<tr>
		<td>商品編號:<font color=red><b>*</b></font></td>
		<td><%=prpVO.getPro_no()%></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="prp_no" value="<%=prpVO.getPrp_no()%>">
<input type="hidden" name="pro_no" value="<%=prpVO.getPro_no()%>">
<input type="submit" value="送出修改"></FORM>
<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>
</html>
