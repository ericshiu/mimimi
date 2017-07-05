<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_picture.model.*,com.firm.model.*,com.product.model.*"%>
<%
	FirmVO firVO = (FirmVO) request.getAttribute("firVO");
	FirmVO firuserVO = (FirmVO) session.getAttribute("firuserVO");
	session.setAttribute("pageReq", "/firm/addPro.jsp");
	
	ProductPictureVO prpVO = (ProductPictureVO) request.getAttribute("prpVO");
	ProductVO proVO = (ProductVO) request.getAttribute("proVO");
%>

<html>
<head>
<title>商品照片新增 - addPRP.jsp</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">
		
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script	src="https://code.jquery.com/jquery-3.2.1.js" integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE=" crossorigin="anonymous"></script>
</head>


<body bgcolor='white'>

<jsp:include page="/front_end/frontTOP.jsp" flush="true"/>

<FORM METHOD="post" ACTION="prp.do" name="form1" enctype="multipart/form-data">
<div class="container">

	<div class="col-xs-12  col-lg-12" align='center' >
	
	<div class="row" style="width:500px;">
		<div class="panel panel-success panel-group">
			<div class="panel-heading" align="center">
				新增商品照片
			</div>
		<table class="table table-hover table-striped" align="center" >
		<tr align='center' valign='middle'>		
			<td>商品編號:<font color=red></font></td>
			<td>${proVO.pro_no}</td>	
		</tr>	
		<tr align='center' valign='middle'>	
			<td>商品照片:</td>
			<td><input type="file" name="prp_picture" size="45" value="<%= (prpVO==null)? "images/tomcat.gif" : prpVO.getPrp_picture()%>" /></td>	
		</tr>
	
		<tr align='center' valign='middle'>
			<td></td>
			<td><button type="submit" class="btn btn-info" value="">送出新增</button></td>
			<input type="hidden" name="action" value="insert">
			<input type="hidden" name="pro_no" value="${proVO.pro_no}">
			
		</tr>
	
		</table>
		</div>
	</div>
	</div>
</div>
</form>


</div>
</div>
<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>

</html>
