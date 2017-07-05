<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.postpartum_care.model.*"%>
<%
PostpartumCareVO postpartumCareVO = (PostpartumCareVO) request.getAttribute("PostpartumCareVO");
%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>產後廠商資料新增 - addPC.jsp</title>
</head>
<body>
<table border='1' cellpadding='5' cellspacing='0' width='500'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>產後廠商資料新增 - addPC.jsp</h3>
		</td>
		<td>
		   <a href="select_page.jsp"><img src="../images/LOGO.png" width="200" height="100" border="1">回首頁</a>
	    </td>
	</tr>
</table>
<h3>產後廠商資料:</h3>
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

<FORM METHOD="post" ACTION="PC.do" name="form1">
<table border="0">

	<tr>
		<td>廠商帳號:</td>
		<td><input type="TEXT" name="pc_id" size="50" maxlength="20"
			value="<%= (postpartumCareVO==null)? "PC00005" : postpartumCareVO.getPc_id()%>" /></td>
	</tr>
	<tr>
		<td>廠商密碼:</td>
		<td><input type="TEXT" name="pc_password" size="50" maxlength="20"
			value="<%= (postpartumCareVO==null)? "PC00005" : postpartumCareVO.getPc_password()%>" /></td>
	</tr>
	<tr>
		<td>廠商名稱:</td>
		<td><input type="TEXT" name="pc_name" size="50" maxlength="25"
			value="<%= (postpartumCareVO==null)? "好寶貝北投館產後護理之家" : postpartumCareVO.getPc_name()%>" /></td>
	</tr>
	<tr>
		<td>廠商類型:</td>
		<td><input type="RADIO" name="pc_type" value="月子中心" 
					<c:if test="${postpartumCareVO.pc_type=='月子中心'}">
	                  checked 
                    </c:if>/>月子中心
		    <input type="RADIO" name="pc_type" value="月子餐"
		    		<c:if test="${postpartumCareVO.pc_type=='月子餐'}">
	                  checked 
                    </c:if> />月子餐
        </td>
	</tr>
	<tr>
		<td>廠商地址:</td>
		<td><input type="TEXT" name="pc_address" size="50" maxlength="50"
			value="<%= (postpartumCareVO==null)? "台北市北投區北投路二段11號6樓-7樓" : postpartumCareVO.getPc_address()%>" /></td>
	</tr>	
	<tr>
		<td>廠商電話:</td>
		<td><input type="TEXT" name="pc_phone" size="50" maxlength="20"
			value="<%= (postpartumCareVO==null)? "02-2898-3267" : postpartumCareVO.getPc_phone()%>" /></td>
	</tr>
	<tr>
		<td>廠商電子信箱:</td>
		<td><input type="email" name="pc_email" size="50" maxlength="50"
			value="<%= (postpartumCareVO==null)? "111@543.com.tw" : postpartumCareVO.getPc_email()%>" /></td>
	</tr>
	<tr>
		<td>廠商簡介:</td>
		<td><textarea name="pc_summary" rows="4" cols="50"><%= (postpartumCareVO==null)? "愛在這裡成長,是寶寶與媽媽第一個家" : postpartumCareVO.getPc_summary()%></textarea>
	</tr>
	<tr>
		<td>預約小禮:</td>
		<td><input type="RADIO" name="pc_gift" value="Y" 
					<c:if test="${postpartumCareVO.pc_gift=='Y'}">
	                  checked 
                    </c:if>/>是
		    <input type="RADIO" name="pc_gift" value="N"
		    		<c:if test="${postpartumCareVO.pc_gift=='N'}">
	                  checked 
                    </c:if> />否
        </td>
	</tr>
	<tr>
		<td>預約下訂折扣:</td>
		<td><input type="RADIO" name="pc_bonus" value="Y" 
					<c:if test="${postpartumCareVO.pc_bonus=='Y'}">
	                  checked 
                    </c:if>/>是
		    <input type="RADIO" name="pc_bonus" value="N"
		    		<c:if test="${postpartumCareVO.pc_bonus=='N'}">
	                  checked 
                    </c:if> />否
        </td>
	</tr>
	<tr>
		<td>預約需求積分:</td>
		<td><input type="number" name="pc_point" size="20" value="<%= (postpartumCareVO==null)? "100" : postpartumCareVO.getPc_point()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="pc_eva_good" value="0">
<input type="hidden" name="pc_eva_normal" value="0">
<input type="hidden" name="pc_eva_bad" value="0">
<input type="hidden" name="pc_authority" value="Y">
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>
</html>