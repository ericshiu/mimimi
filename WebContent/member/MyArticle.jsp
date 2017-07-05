<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*" import="java.util.*"
	import="com.firm.model.*" import="com.postpartum_care.model.*"%>

<%
	String userType = (String) session.getAttribute("userType");

	if (userType != null) {
		if (userType.equals("PC")) {
			PostpartumCareVO userVO = (PostpartumCareVO) session.getAttribute("userVO");
			pageContext.setAttribute("userVO", userVO);
		} else if (userType.equals("Fir")) {
			FirmVO userVO = (FirmVO) session.getAttribute("userVO");
			pageContext.setAttribute("userVO", userVO);
		} else if (userType.equals("Mem")) {
			MemberVO userVO = (MemberVO) session.getAttribute("userVO");
			pageContext.setAttribute("userVO", userVO);
		}
	} else {
		userType = "Non";
	}

	session.setAttribute("pageReq", "/member/MyArticle.jsp");//當前頁的網址
%>




<!DOCTYPE html>
<html lang="">
<head>
<!--固定的 -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>文章管理</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.js"integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="crossorigin="anonymous"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>forum/css/forumcss.css">
<!--固定的 -->
</head>

<style>
.modal-header-success {
	color: #fff;
	padding: 9px 15px;
	border-bottom: 1px solid #eee;
	background-color: #5cb85c;
	-webkit-border-top-left-radius: 5px;
	-webkit-border-top-right-radius: 5px;
	-moz-border-radius-topleft: 5px;
	-moz-border-radius-topright: 5px;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
}

.modal-header-warning {
	color: #fff;
	padding: 9px 15px;
	border-bottom: 1px solid #eee;
	background-color: #f0ad4e;
	-webkit-border-top-left-radius: 5px;
	-webkit-border-top-right-radius: 5px;
	-moz-border-radius-topleft: 5px;
	-moz-border-radius-topright: 5px;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
}

.modal-header-danger {
	color: #fff;
	padding: 9px 15px;
	border-bottom: 1px solid #eee;
	background-color: #d9534f;
	-webkit-border-top-left-radius: 5px;
	-webkit-border-top-right-radius: 5px;
	-moz-border-radius-topleft: 5px;
	-moz-border-radius-topright: 5px;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
}

.modal-header-info {
	color: #fff;
	padding: 9px 15px;
	border-bottom: 1px solid #eee;
	background-color: #5bc0de;
	-webkit-border-top-left-radius: 5px;
	-webkit-border-top-right-radius: 5px;
	-moz-border-radius-topleft: 5px;
	-moz-border-radius-topright: 5px;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
}

.modal-header-primary {
	color: #fff;
	padding: 9px 15px;
	border-bottom: 1px solid #eee;
	background-color: #428bca;
	-webkit-border-top-left-radius: 5px;
	-webkit-border-top-right-radius: 5px;
	-moz-border-radius-topleft: 5px;
	-moz-border-radius-topright: 5px;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
}

.modal-header-pink {
	color: #fff;
	padding: 9px 15px;
	border-bottom: 1px solid #eee;
	background-color: pink;
	-webkit-border-top-left-radius: 5px;
	-webkit-border-top-right-radius: 5px;
	-moz-border-radius-topleft: 5px;
	-moz-border-radius-topright: 5px;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
}

.exampleModal {
	padding: 20px;
}

.modal-title {
	color: white;
	font-size: 20px;
	font-style: bold;
}

#modal-dialog-login {
	color: #555;
	width: 1300px;
	background-color: pink;
}

#modal-body-login {
	background-color: pink;
	height: 650px;
	color: #555;
}

#p1 {
	font-size: 16px;
	color: #555;
}
</style>
<body>


	<jsp:include page="/front_end/frontTOP.jsp" flush="true" />

	<div class="container"
		style="border-style: dashed; border-width: 3px 3px 3px 3px; padding: 0px;">

		<div class="col-xs-12  col-lg-2 left"
			style="text-align: center; width: 20%;">
			<jsp:include page="/member/MySelect.jsp" flush="true" />
		</div>

		<div class="col-xs-12  col-lg-10 right"
			style="height: 100%; background-color: #fff; font-size: 20px; width: 80%;">
		
				
				<br>
				<div class="col-xs-12  col-lg-12" style="background-color:#dff0d8;">
				<button type="button"
					onclick="window.location.href='<%=request.getContextPath()%>/forum/ArticleADD.jsp'"
					class="btn btn-primary" style="background-color:#b39873;border-color:#b39873;">新增文章</button>
				</div>
				<br> <br>
				<div class="panel panel-success" style="width: 100%;">
					<div class="modal-header-info">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-heart"></i> &nbsp; &nbsp;追蹤文章
						</h3>
					</div>
					<table class="table table-hover" style="text-align: right;">

						<tr>
							<th style="width: 6%; text-align: center;">序號</th>
							<th style="width: 25%; text-align: center;">文章標題</th>

							<th style="width: 8%; text-align: center;">收藏數</th>
							<th style="width: 15%; text-align: center;">作者</th>
							<th style="width: 25%; text-align: center;">發表日期</th>
							<th style="width: 25%; text-align: center;">最後修正日期</th>

						</tr>

					</table>
					<div class="favoriate" style="overflow: auto; height: 300px;">
						<jsp:include page="/forum/ArticleFavorite.jsp" flush="true" />
					</div>
				</div>
				<!-- favorite -->




				<!-- -------------------------收藏文章-發表文章---------------------------------- -->
		
				<div class="panel panel-success" style="margin-top: 60px;">
					<div class="modal-header-primary">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-globe"></i>&nbsp;&nbsp;我的文章
						</h3>
					</div>
					<table class="table table-hover" style="text-align: right;">

						<tr>
							<th style="width: 6%; text-align: center;">序號</th>
							<th style="width: 12%; text-align: center;">修改</th>
							<th style="width: 12%; text-align: center;">刪除</th>
							<th style="width: 25%; text-align: center;">文章標題</th>

							<th style="width: 8%; text-align: center;">收藏數</th>
							<th style="width: 15%; text-align: center;">作者</th>
							<th style="width: 25%; text-align: center;">發表日期</th>


						</tr>

					</table>
					<div class="favoriate" style="overflow: auto; height: 500px;">
						<jsp:include page="/forum/ArticlePublish.jsp" flush="true" />
					</div>
				</div>

			</div>
</div>

			<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>

</html>
