<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
// 	String order = (String) request.getAttribute("order");
// 	String area = (String) request.getAttribute("area");
// 	String city = (String) request.getAttribute("city");

%>

<jsp:useBean id="listHos_ByCompositeQuery" scope="request" type="java.util.List" />
<%-- <jsp:useBean id="PCSvc" scope="page" class="com.postpartum_care.model.PostpartumCareService" /> --%>
<html>
<head>
<!-- <meta charset="utf-8"> -->
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<!-- <meta name="viewport" -->
<!-- 	content="width=device-width, initial-scale=1.0, shrink-to-fit=no"> -->

<!-- <title>產後照護廠商查詢結果</title> -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"> -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"> -->
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css"> -->
<!-- <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script> -->
<!-- <script src="https://code.jquery.com/jquery.js"></script> -->
<!-- <link rel="stylesheet" type="text/css" href="css/PostpartumCare_index.css"> -->
<!-- <script src="http://maps.google.com/maps/api/js"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script> -->
</head>
<body bgcolor='white'>


<div class="container">		
	<div class="row" style="margin-left:-30px;">
		<div class="col-xs-12   col-sm-12">
		<div class="panel panel-info">
		
	
			<div class="panel-heading">
				<h3 class="panel-title"><%@ include file="page1_ByCompositeQueryHos.file" %>	</h3>
			</div>
		
		<table class="table table-hover table-striped" align="center">
			<thead>
			<tr>
				<th>醫院名稱</th>
				<th>醫院地址</th>
				<th>醫院電話</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="hospitalVO" items="${listHos_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr valign='middle' ${(hospitalVO.hos_no==param.pc_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
					<td>${hospitalVO.hos_name}</td>
					<td>${hospitalVO.hos_address}</td>
					<td>${hospitalVO.hos_phone}</td>
				</tr>
			</c:forEach>
			<%@ include file="page2_ByCompositeQueryHos.file" %>
			</tbody>
		</table>
		
			
		</div>
		</div>		
	</div>	
</div>

	<script src="js/addressForSearch.js"></script>
</body>
</html>
