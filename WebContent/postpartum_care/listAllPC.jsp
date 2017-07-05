<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.postpartum_care.model.*"%>

<%
    PostpartumCareService PCSvc = new PostpartumCareService();
    List<PostpartumCareVO> list = PCSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有產後廠商資料 - listAllPC.jsp</title>
</head>
<body>
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='1600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有產後廠商資料 - ListAllPC.jsp</h3>
		<a href="select_page.jsp"><img src="../images/LOGO.png" width="200" height="100" border="0">回首頁</a>
		</td>
	</tr>
</table>

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
		<th>修改</th>
		<th>刪除</th>
		<th>查看照片</th>
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="postpartumCareVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
			<td>${postpartumCareVO.pc_no}</td>
			<td>${postpartumCareVO.pc_id}</td>
			<td>${postpartumCareVO.pc_password}</td>
			<td>${postpartumCareVO.pc_name}</td>
			<td>${postpartumCareVO.pc_type}</td>
			<td>${postpartumCareVO.pc_address}</td>
			<td>${postpartumCareVO.pc_phone}</td>
			<td>${postpartumCareVO.pc_email}</td>
			<td>${postpartumCareVO.pc_summary}</td>
			<td>${postpartumCareVO.pc_gift}</td>
			<td>${postpartumCareVO.pc_bonus}</td>
			<td>${postpartumCareVO.pc_point}</td>
			<td>${postpartumCareVO.pc_eva_good}</td>
			<td>${postpartumCareVO.pc_eva_normal}</td>
			<td>${postpartumCareVO.pc_eva_bad}</td>
			<td>${postpartumCareVO.pc_authority}</td>			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postpartum_care/PC.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="pc_no" value="${postpartumCareVO.pc_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postpartum_care/PC.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="pc_no" value="${postpartumCareVO.pc_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>	
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postpartum_care/PC.do">
			    <input type="submit" value="查看照片">
			    <input type="hidden" name="pc_no" value="${postpartumCareVO.pc_no}">
			    <input type="hidden" name="action"value="listPCPs_ByPc_no_AllPC"></FORM>
			</td>							
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>
</body>
</html>