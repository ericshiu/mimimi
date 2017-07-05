<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.product_category.model.*"%>
<%
ProductCategoryVO prcVO = (ProductCategoryVO) request.getAttribute("prcVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<title>會員資料 - listOnePRC.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>會員資料 - ListOnePRC.jsp</h3>
		<a href="select_page.jsp"><img src="images/LOGO.png" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>商品類別編號</th>
		<th>商品主類別</th>
		<th>商品次類別</th>
	</tr>
	<tr align='center' valign='middle'>
			<td><%=prcVO.getPrc_no()%></td>
			<td><%=prcVO.getPrc_main()%></td>
			<td><%=prcVO.getPrc_sub()%></td>
	</tr>
</table>

</body>
</html>
