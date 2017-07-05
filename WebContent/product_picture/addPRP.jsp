<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_picture.model.*"%>
<%
ProductPictureVO prpVO = (ProductPictureVO) request.getAttribute("prpVO");
%>

<html>
<head>
<title>商品照片新增 - addPRP.jsp</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/RegisterPC.css">
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script src="https://code.jquery.com/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">
</head>


<body bgcolor='white'>
<jsp:include page="/front_end/frontTOP.jsp" flush="true"/>



<FORM METHOD="post" ACTION="prp.do" name="form1" enctype="multipart/form-data">
<div class="container">

	<div class="col-xs-12  col-lg-12" align='center' >
	
	<div class="row" style="width:500px;">
		<div class="panel panel-success panel-group">
			<div class="panel-heading" align="center">
				廠商訂單
			</div>
		<table class="table table-hover table-striped" align="center" >
		<tr align='center' valign='middle'>		
			<td>商品照片:</td>
			<td><input type="file" name="prp_picture" size="45"
			value="<%= (prpVO==null)? "images/tomcat.gif" : prpVO.getPrp_picture()%>" /></td>
		</tr>	
		<tr align='center' valign='middle'>	
			<td>商品編號:<font color=red></font></td>
			<td><input type="TEXT" name="pro_no" size="45" 
			value="<%= (prpVO==null)? "PRO0000001" : prpVO.getPro_no()%>" readonly/></td>
		</tr>
	
		<tr align='center' valign='middle'>
			<td></td>
			<td><button type="submit" value="" class="btn btn-info">送出新增</button></FORM></td>
				<input type="hidden" name="action" value="insert">
			</tr>
	
		</table>
		</div>
	</div>
	</div>
</div>
</form>
<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>

</html>
