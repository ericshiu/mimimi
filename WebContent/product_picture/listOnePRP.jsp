<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.product_picture.model.*"%>
<%
ProductPictureVO prpVO = (ProductPictureVO) request.getAttribute("prpVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>商品照片資料 - listOnePRP.jsp</title>
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
<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>商品照片資料 - ListOnePRP.jsp</h3>
		<a href="select_page.jsp"><img src="images/LOGO.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>商品照片編號</th>
		<th>商品照片</th>
		<th>商品編號</th>
	</tr>
	<tr align='center' valign='middle'>
	<td><%=prpVO.getPrp_no()%></td> 
			
			<td>
	        <img src="<%= request.getContextPath() %>/ProductPhoto?prp_no=${prpVO.prp_no}" width=300px> 
			</td>
			
		<td><%=prpVO.getPro_no()%></td>
	</tr>
</table>
<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>
</html>
