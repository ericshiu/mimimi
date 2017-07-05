<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pc_picture.model.*"%>

<%
	PcPictureVO pcPictureVO = (PcPictureVO) request.getAttribute("pcPictureVO"); 
	pageContext.setAttribute("pcPictureVO",pcPictureVO);
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

<FORM METHOD="post" ACTION="PCP.do" name="form1" enctype="multipart/form-data">
<table border="0">
	<tr>
		<td>照片編號:<font color=red><b>*</b></font></td>
		<td><%=pcPictureVO.getPcp_no()%></td>
	</tr>
	<tr>
		<td>廠商編號:<font color=red><b>*</b></font></td>
		<td><%=pcPictureVO.getPc_no()%></td>	</tr>
	<tr>
		<td>照片說明(20字):</td>
		<td><input type="TEXT" name="pcp_summary" size="50"	value="<%=pcPictureVO.getPcp_summary()%>" /></td>
	</tr>
	<tr>
		<td rowspan="2">照片上傳:</td>
		<td><input id="picture_input" type="file" name="pcp_picture" /></td>
	</tr>
	<tr>
		<td><img id="pcp_picture" src="<%=request.getContextPath()%>/PC_PictureTransfer?pcp_no=${pcPictureVO.pcp_no}" width=400px ></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="pcp_no" value="<%=pcPictureVO.getPcp_no()%>">
<input type="hidden" name="pc_no" value="<%=pcPictureVO.getPc_no()%>">
<input type="submit" value="送出修改"></FORM>

<script type="text/javascript">
function showImage(e){
		obj = document.getElementById("pcp_picture");
		obj.src = URL.createObjectURL(event.target.files[0]);

}

window.onload = function (){
    document.getElementById("picture_input").onchange=showImage;
}

</script>

</body>
</html>