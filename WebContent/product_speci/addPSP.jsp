<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_speci.model.*"%>
<%
	ProductSpeciVO pspVO = (ProductSpeciVO) request.getAttribute("pspVO");
%>

<html>
<head>
<title>商品規格資料新增 - addPSP.jsp</title></head>


<body bgcolor='white'>

<h3>商品規格資料:</h3>
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

<FORM METHOD="post" ACTION="psp.do" name="form1">
<table border="0">

	<tr>
		<td>商品編號:</td>
		<td><input type="TEXT" name="pro_no" size="45" 
			value="<%= (pspVO==null)? "PRO0000001" : pspVO.getPro_no()%>" /></td>
	</tr>
	<tr>
		<td>商品規格名稱:</td>
		<td><input type="TEXT" name="psp_name" size="45" 
		value="<%= (pspVO==null)? "尺寸" : pspVO.getPsp_name()%>" /></td>
	</tr>
	<tr>
		<td>商品規格細項:</td>
		<td><input type="TEXT" name="psp_list" size="45" 
			value="<%= (pspVO==null)? "s" : pspVO.getPsp_list()%>" /></td>
	</tr>
	
</table>

	<input type="button" id="btn" value="新增規格" />
	
	<div id="showBlock"></div>
<br>

<input type="hidden" name="action" value="insert">
<input type="hidden" name="pro_no" value=pspVO.getPro_no() />
<input type="submit" value="送出新增"></FORM>


<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script>
$(document).ready(function($) {
  //set the default value
  var txtId = 1;
  //add input block in showBlock
  $("#btn").click(function () {
      $("#showBlock").append('<div id="div' + txtId + '"><span id="input' + txtId + '"> 規格:<input type="text" name="psp_name' + txtId + '" size="5"/> 屬性:<input type="text" name="psp_list' + txtId + '"size="5"/></span><input type="button" value="add" onclick="addtxt('+txtId+')"><input type="button" value="del" onclick="deltxt('+txtId+')"></div>');
      txtId++;
  });
  //remove div
  function deltxt(id) {
      $("#div"+id).remove();
  }
  //add input
  function addtxt(id) {
      $("#input"+id).append('&nbsp'+'<input type="text" name="psp_list' + id + '" size="5"/>');
  }
});
</script> 
</body>
</html>
