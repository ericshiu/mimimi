<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.product_speci.model.*"%>
<%
ProductSpeciVO pspVO = (ProductSpeciVO) request.getAttribute("pspVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<title>商品規格資料 - listOnePSP.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>商品規格資料 - ListOnePSP.jsp</h3>
		<a href="select_page.jsp"><img src="images/LOGO.png" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>商品規格編號</th>
		<th>商品編號</th>
		<th>商品規格名稱</th>
		<th>商品規格細項</th>
	</tr>
	<tr align='center' valign='middle'>
			<td><%=pspVO.getPsp_no()%></td>
			<td><%=pspVO.getPro_no()%></td>
			<td><%=pspVO.getPsp_name()%></td>
			<td><%=pspVO.getPsp_list()%></td>
			
	</tr>
</table>

</body>
</html>
