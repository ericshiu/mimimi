<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>產後護理檢舉管理</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery.js"></script>
</head>
<style>

body{
				margin:0px;
				padding:0px;

				background:#fff url("img/backbaby1.jpg") center center fixed no-repeat;
				background-size: cover;　

			}     
</style>
<body>
	<div class="col-xs-12  col-lg-2">
		<jsp:include page="/back_end/ManagerSelect.jsp" flush="true" />
	</div>

<div class="col-xs-12  col-lg-10 right" style="height:100%;font-size:20px;padding-left:20px;width:83%;margin-top:25px;">
<h2>產後護理檢舉：</h2>
	<div class="col-xs-12 col-lg-12 right1" style="padding-left:10px;width:100%;">

	
	<div id="exTab2" class="container" style="width:100%;">
		<ul class="nav nav-tabs">
			<li id="li0" class="active"><a href="#0" data-toggle="tab">未審核</a></li>
			<li id="li1"><a href="#1"  data-toggle="tab">通過</a></li>
			<li id="li2"><a href="#2"  data-toggle="tab">未通過</a></li>
			
		</ul>

		<div class="tab-content ">
			<div class="tab-pane active" id="0">
			<br>
				<jsp:include page="/back_end/Report/PC_Report_0.jsp" flush="true"/>
			
			</div>
			<div class="tab-pane" id="1">
			<br>	
				<jsp:include page="/back_end/Report/PC_Report_1.jsp" flush="true"/>
			</div>
			<div class="tab-pane" id="2">
			<br>
				<jsp:include page="/back_end/Report/PC_Report_2.jsp" flush="true"/>
			</div>
			
		</div>
	</div>
	</div>
</div>


</body>
</html>