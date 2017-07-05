<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_category.model.*"%>
<%
ProductCategoryVO prcVO = (ProductCategoryVO) request.getAttribute("prcVO");
%>

<html>
<head>
<title>商品類別資料新增 - addPRC.jsp</title></head>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>商品類別資料新增 - addPRC.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="images/LOGO.png" width="100" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>

<h3>商品類別資料資料:</h3>
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

<FORM METHOD="post" ACTION="prc.do" name="form1">
<table border="0">

	
	<tr>
		<td>商品主類別:</td>
		<td><input type="TEXT" name="prc_main" size="45" 
			value="<%= (prcVO==null)? "媽媽專區" : prcVO.getPrc_main()%>" /></td>
	</tr>
	
	<tr>
		<td>商品次類別:</td>
		<td><input type="TEXT" name="prc_sub" size="45" 
		value="<%= (prcVO==null)? "哺育用品" : prcVO.getPrc_sub()%>" /></td>
	</tr>
	
	

</table>
<br>
<input type="hidden" name="prc_no" value=prcVO.getPrc_no() />
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>
