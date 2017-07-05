<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*" import="java.util.*"%>

<%
session.setAttribute("pageReq", "/front_end/frontINDEX.jsp");
%>


<!DOCTYPE html>
<html lang="">
<head>
<!--固定的 -->

		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>媽咪樂寶</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">	
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script	src="https://code.jquery.com/jquery-3.2.1.js" integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE=" crossorigin="anonymous"></script>
<!--固定的 -->
</head>
<body>


<jsp:include page="/front_end/frontTOP.jsp" flush="true"/>


	

<!-- 中間1 -->
		<div class="container-fluid">
			<div class="widget widget-static-block">
			    <div class="banner-container-1 right-banner-block col-xs-12 col-sm-12" style="display: -webkit-box;">
			        <div class="left-banner-block col-xs-4 col-sm-4">
			            <div class="img-responsive">
			                <img class="img-responsive" src="../img/mom_left.png" alt="banner-img" style=" padding-left: 7em; max-width: 66%;" />
			            </div>
			        </div>
			        <div class="center-banner-block col-xs-4 col-sm-4" style="margin-right: 10em;    margin-top: 10em;">
			            <img src="../img/mom.png">
			            <p class="sub-title">Pick products you need,
			                <br> all in a few clicks</p>
			            <div class="button-box">
			                <a href="img/baby-gift-sets.html" class="banner-button">
			                    <span class="banner-button-wrapper">
								<i class="fa fa-long-arrow-left"></i>
								<span>MOM</span>
			                    </span>
			                </a>
			                <a id="banner" href="img/baby-gear.html" class="banner-button">
			                    <span class="banner-button-wrapper">
								<span>BABY</span>
			                    <i class="fa fa-long-arrow-right"></i>
			                    </span>
			                </a>
			            </div>
			        </div>
			        <div class="right-banner-block col-xs-4 col-sm-4">
			            <div class="img-responsive">
			                <img class="img-responsive" src="../img/banner-1_img-2.png" alt="banner-img" style="padding-top: 10em" />
			            </div>
			        </div>
			    </div>
			</div>
		</div>
		
		<!-- 中間1結束 -->
		<!-- 中間2 -->
		<div class="widget widget-static-block">
		    <div class="banner-container-2">
		        <div class="banner-block-1">
		            <div class="banner-img-block">
		                <img class="img-responsive" src="../img/banner-2_img-1.jpg" alt="banner-img" />
		            </div>
		            <div class="banner-text-block">
		                <h3 class="banner-title">Mini <br> 0-12 months</h3>
		                <a href="#" class="banner-button"><span class="banner-button-wrapper">Shop now!</span></a>
		            </div>
		        </div>
		        <div class="banner-block-2" style="margin-left: 62px;">
		            <div class="banner-text-block">
		                <h3 class="banner-title">Baby girl <br> 3 months - 3 years</h3>
		                <a href="#" class="banner-button"><span class="banner-button-wrapper">Shop now!</span></a>
		            </div>
		            <div class="banner-img-block">
		                <img class="img-responsive" src="../img/banner-2_img-2.jpg" alt="banner-img" />
		            </div>
		        </div>
		        <div class="banner-block-3">
		            <div class="banner-img-block">
		                <img class="img-responsive" src="../img/banner-2_img-3.jpg" alt="banner-img" />
		            </div>
		            <div class="banner-text-block">
		                <h3 class="banner-title">Baby girl <br> 4 years - 5 years</h3>
		                <a href="#" class="banner-button"><span class="banner-button-wrapper">Shop now!</span></a>
		            </div>
		        </div>
		    </div>
		</div>

		<!-- 中間2結束 -->

<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />

<!-- 跳出視窗區 -->

<!-- 登入成功 -->
<c:if test="${not empty Login_SMsg}">
			<font color='red' style="display: none;">請修正以下錯誤:
				<ul id="Login_Smessage">
					<c:forEach var="message" items="${Login_SMsg}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</font>
		</c:if>

		<button type="button" data-toggle="modal" data-target="#Login_SBtnModal"
		id="Login_SBtn" style="display: none;">Open Modal</button>
	<div class="modal fade" id="Login_SBtnModal" role="dialog" style="top: 25%;">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header login-modal-header-info" style="height: 60px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="login-modal-title">登入訊息</h4>
				</div>
				<div class="modal-body">
					<p id="Login_SMsg" style="font-size: 16px;"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">OK</button>
				</div>
			</div>

		</div>
	</div>
</body>
	
  		
</html>
