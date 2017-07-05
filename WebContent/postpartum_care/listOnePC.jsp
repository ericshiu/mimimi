<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.postpartum_care.model.*"%>

<%
PostpartumCareVO postpartumCareVO = (PostpartumCareVO) request.getAttribute("postpartumCareVO"); //EmpServlet.java(Concroller), 存入req的postpartumCareVO物件
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>產後廠商資料 - listOnePC.jsp</title>
</head>
<body>
<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>產後廠商資料 - listOnePC.jsp</h3>
		<a href="select_page.jsp"><img src="../images/LOGO.png" width="200" height="100" border="0">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='1600'>
	<tr>
		<th>廠商編號</th>
		<th>廠商帳號</th>
		<th>廠商密碼</th>
		<th>廠商名稱</th>
		<th>廠商類型</th>
		<th>廠商地址</th>
		<th>廠商電話</th>
		<th>廠商e-mail</th>
		<th>廠商簡介</th>
		<th>廠商預約禮</th>
		<th>廠商預約折扣</th>
		<th>廠商預約需求積分</th>
		<th>廠商正評</th>
		<th>廠商普評</th>
		<th>廠商負評</th>
		<th>廠商權限</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=postpartumCareVO.getPc_no()%></td>
		<td><%=postpartumCareVO.getPc_id()%></td>
		<td><%=postpartumCareVO.getPc_password()%></td>
		<td><%=postpartumCareVO.getPc_name()%></td>
		<td><%=postpartumCareVO.getPc_type()%></td>
		<td><%=postpartumCareVO.getPc_address()%></td>
		<td><%=postpartumCareVO.getPc_phone()%></td>
		<td><%=postpartumCareVO.getPc_email()%></td>
		<td><%=postpartumCareVO.getPc_summary()%></td>
		<td><%=postpartumCareVO.getPc_gift()%></td>
		<td><%=postpartumCareVO.getPc_bonus()%></td>
		<td><%=postpartumCareVO.getPc_point()%></td>
		<td><%=postpartumCareVO.getPc_eva_good()%></td>
		<td><%=postpartumCareVO.getPc_eva_normal()%></td>		
		<td><%=postpartumCareVO.getPc_eva_bad()%></td>
		<td><%=postpartumCareVO.getPc_authority()%></td>
	</tr>
</table>
</body>
</html>