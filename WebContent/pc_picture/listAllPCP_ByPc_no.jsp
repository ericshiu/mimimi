<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pc_picture.model.*"%>
<%@ page import="com.postpartum_care.model.*"%>

<%
	PostpartumCareVO postpartumCareVO = (PostpartumCareVO) request.getAttribute("postpartumCareVO"); 
%> 

<jsp:useBean id="listPCPs_ByPc_no" scope="request" type="java.util.Set" />
<jsp:useBean id="PCSvc" scope="page" class="com.postpartum_care.model.PostpartumCareService" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>產後廠商照片 - llistPCP_ByPc_no.jsp</title>
</head>
<body>

<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>產後廠商照片 - listPCP_ByPc_no.jsp</h3>
		<a href="<%=request.getContextPath()%>/select_page.jsp"><img src="../images/LOGO.png" width="200" height="100" border="0">回首頁</a>
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
<br>
<table border='2' cellpadding='5' cellspacing='0'>
	<tr align='center' valign='middle' height='20'>
		<th>廠商編號：</th>
		<td>${postpartumCareVO.pc_no}</td>
	</tr>
	<tr align='center' valign='middle' height='20'>
		<th>廠商名稱：</th>
		<td>${postpartumCareVO.pc_name}</td>
	</tr>	
</table>
<br>

<form  METHOD="post" action="<%=request.getContextPath()%>/pc_picture/addPCP.jsp">
	<input type="hidden" name="pc_no" value="${postpartumCareVO.pc_no}">
	<input type="hidden" name="pc_name" value="${postpartumCareVO.pc_name}">
    <input type="submit" value="新增圖片" />
</form>
<br>
<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>照片編號</th>
<!-- 		<th>廠商編號</th> -->
		<th>廠商照片</th>
		<th>照片說明</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	
	<c:forEach var="pcPictureVO" items="${listPCPs_ByPc_no}" >
		<tr align='center' valign='middle' ${(pcPictureVO.pcp_no==param.pcp_no) ? 'bgcolor=#CCCCFF':''}>
			<td>${pcPictureVO.pcp_no}</td>
<%-- 			<td>${pcPictureVO.pc_no}</td> --%>
			<td><img src="<%=request.getContextPath()%>/PC_PictureTransfer?pcp_no=${pcPictureVO.pcp_no}" width=200px ></td>
			<td>${pcPictureVO.pcp_summary}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pc_picture/PCP.do">
			    <input type="submit" value="修改" class="btn btn-info"> 
			    <input type="hidden" name="pcp_no"value="${pcPictureVO.pcp_no}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--><!-- 目前尚未用到  -->
			    <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pc_picture/PCP.do">
			    <input type="submit" value="刪除" class="btn btn-info">
			    <input type="hidden" name="pcp_no" value="${pcPictureVO.pcp_no}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>


<br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b>

</body>
</html>