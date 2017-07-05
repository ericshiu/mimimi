<%@page import="java.io.BufferedInputStream"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product_picture.model.*"%>
<%@ page import="com.product.model.*"%>


<%
    ProductPictureService PRPSvc = new ProductPictureService();
    List<ProductPictureVO> list = PRPSvc.getAll();
    pageContext.setAttribute("list",list);
    
    ProductVO proVO = (ProductVO) request.getAttribute("proVO"); 
%>

<html>
<head>
<title>所有商品照片資料 - listAllPRP.jsp</title>
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
	<div class="row" >
		<div class="panel panel-success panel-group">
			<div class="panel-heading" align="center">
				廠商訂單
			</div>
		<table class="table table-hover table-striped" align="center" >
		<tr>
		<td  colspan="5">
		<%@ include file="page1.file" %> 
		</td>
	
		</tr>
		
		<tr align='center' valign='middle'>		
			<th style="text-align:center;">商品照片編號</th>
			<th style="text-align:center;">商品照片</th>
			<th style="text-align:center;">商品編號</th>
			<th></th>
			<th></th>
		</tr>	
		
		<tr align='center' valign='middle'>	
			<td>${prpVO.prp_no}</td>		
			<td >
			<img src="<%= request.getContextPath() %>/ProductPhoto?prp_no=${prpVO.prp_no}" width=300px>
			</td>	
			<td>${prpVO.pro_no}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_picture/prp.do">
			     <button type="submit" value="" class="btn btn-info">修改</button>
			     <input type="hidden" name="prp_no" value="${prpVO.prp_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_picture/prp.do">
			    <button type="submit" value="" class="btn btn-danger">刪除</button>
			    <input type="hidden" name="prp_no" value="${prpVO.prp_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
		<tr align='center' valign='middle'>
			<td></td>
			<td><button type="submit" value="" class="btn btn-info">送出新增</button></FORM></td>
				<input type="hidden" name="action" value="insert">
			<td></td>
			<td></td>
			<td></td>
		</tr>
			<c:forEach var="prpVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr align='center' valign='middle'>	
				</tr>
			</c:forEach>
			<%@ include file="page2.file" %>
		</table>
		</div>
	</div>
	</div>
</div>
</form>


<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>
</html>
