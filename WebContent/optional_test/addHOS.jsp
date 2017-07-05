<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.hospital.model.*"%>
<%
HospitalVO hospitalVO = (HospitalVO) request.getAttribute("HospitalVO");
%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自費項目醫院資料新增</title>
</head>
<body>
<table border='1' cellpadding='5' cellspacing='0' width='500'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>自費項目醫院資料新增</h3>
		</td>
		<td>
		   <a href="#"><img src="../images/LOGO.png" width="200" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>
<h3>自費項目醫院資料:</h3>
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

<FORM METHOD="post" ACTION="hos.do" name="form1">
<table border="0">


	<tr>
		<td>醫院名稱:</td>
		<td><input type="TEXT" name="hos_name" size="50" maxlength="25"
			value="<%= (hospitalVO==null)? "" : hospitalVO.getHos_name()%>" /></td>
	</tr>

	<tr>
		<td>醫院地址:</td>
		<td><input type="TEXT" name="hos_address" size="50" maxlength="50"
			value="<%= (hospitalVO==null)? "" : hospitalVO.getHos_address()%>" /></td>
	</tr>	
	<tr>
		<td>醫院電話:</td>
		<td><input type="TEXT" name="hos_phone" size="50" maxlength="20"
			value="<%= (hospitalVO==null)? "" : hospitalVO.getHos_phone()%>" /></td>
	</tr>

<jsp:useBean id="OTSvc" scope="page" class="com.optional_test.model.OptionalTestService" />
         <c:forEach var="optionalTestVO" items="${OTSvc.all}" > 
          <tr><td colspan="2">
          <input type="checkbox" name="ot_no" value="${optionalTestVO.ot_no}">${optionalTestVO.ot_name}
          </td></tr>
         </c:forEach>   


</table>


<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>
</html>