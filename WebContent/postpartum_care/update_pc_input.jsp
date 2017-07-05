<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.postpartum_care.model.*"%>
<%
	PostpartumCareVO postpartumCareVO = (PostpartumCareVO) request.getAttribute("postpartumCareVO"); 
	pageContext.setAttribute("postpartumCareVO",postpartumCareVO);
%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>產後廠商資料修改 - update_pc_input.jsp</title>
</head>
<body>

<table border='1' cellpadding='5' cellspacing='0' width='500'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>廠商資料修改 - update_emp_input.jsp</h3>
		<a href="select_page.jsp"><img src="../images/LOGO.png" width="200" height="100" border="0">回首頁</a></td>
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

<FORM METHOD="post" ACTION="PC.do" name="form1">
<table border="0">
	<tr>
		<td>廠商編號:<font color=red><b>*</b></font></td>
		<td><%=postpartumCareVO.getPc_no()%></td>
	</tr>
	<tr>
		<td>廠商帳號:</td>
		<td><input type="TEXT" name="pc_id" size="50" value="<%=postpartumCareVO.getPc_id()%>" /></td>
	</tr>
	<tr>
		<td>廠商密碼:</td>
		<td><input type="TEXT" name="pc_password" size="50"	value="<%=postpartumCareVO.getPc_password()%>" /></td>
	</tr>
	<tr>
		<td>廠商名稱:</td>
		<td><input type="TEXT" name="pc_name" size="50"	value="<%=postpartumCareVO.getPc_name()%>" /></td>
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
		<td><input type="TEXT" name="pc_address" size="50"	value="<%=postpartumCareVO.getPc_address()%>" /></td>
	</tr>
	<tr>
		<td>廠商電話:</td>
		<td><input type="TEXT" name="pc_phone" size="50"	value="<%=postpartumCareVO.getPc_phone()%>" /></td>
	</tr>
	<tr>
		<td>廠商電子信箱:</td>
		<td><input type="TEXT" name="pc_email" size="50"	value="<%=postpartumCareVO.getPc_email()%>" /></td>
	</tr>
	<tr>
		<td>廠商簡介:</td>
		<td><textarea name="pc_summary" rows="4" cols="50"><%=postpartumCareVO.getPc_summary()%></textarea>
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
		<td><input type="number" name="pc_point" size="20"	value="<%=postpartumCareVO.getPc_point()%>" /></td>
	</tr>
	<tr>
		<td>廠商正評:</td>
		<td><%=postpartumCareVO.getPc_eva_good()%></td>
		<input type="hidden" name="pc_eva_good" value="<%=postpartumCareVO.getPc_eva_good()%>">
	</tr>
	<tr>
		<td>廠商普評:</td>
		<td><%=postpartumCareVO.getPc_eva_normal()%></td>
		<input type="hidden" name="pc_eva_normal" value="<%=postpartumCareVO.getPc_eva_normal()%>">
	</tr>
	<tr>
		<td>廠商負評:</td>
		<td><%=postpartumCareVO.getPc_eva_bad()%></td>
		<input type="hidden" name="pc_eva_bad" value="<%=postpartumCareVO.getPc_eva_bad()%>">
	</tr>
	<tr>
		<td>使用權限:</td>
		<td><input type="RADIO" name="pc_authority" value="Y" 
					<c:if test="${postpartumCareVO.pc_authority=='Y'}">
	                  checked 
                    </c:if>/>正常
		    <input type="RADIO" name="pc_authority" value="N"
		    		<c:if test="${postpartumCareVO.pc_authority=='N'}">
	                  checked 
                    </c:if> />凍結
        </td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="pc_no" value="<%=postpartumCareVO.getPc_no()%>">
<input type="submit" value="送出修改"></FORM>



</body>
</html>