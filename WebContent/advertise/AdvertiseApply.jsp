<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*" import="java.util.*" import="com.advertise.model.*"
	import="com.firm.model.*" import="com.postpartum_care.model.*"%>

<%
String userType = (String) session.getAttribute("userType"); 

if (userType != null){
	if (userType.equals("PC")){
		PostpartumCareVO userVO = (PostpartumCareVO) session.getAttribute("userVO");
		pageContext.setAttribute("userVO",userVO);
	} else if (userType.equals("Fir")){
		FirmVO userVO = (FirmVO) session.getAttribute("userVO");
		pageContext.setAttribute("userVO",userVO);
	} else if (userType.equals("Mem")){
		MemberVO userVO = (MemberVO) session.getAttribute("userVO");  
		pageContext.setAttribute("userVO",userVO);
	} 
} else {
	userType = "Non";
}
	FirmVO userVO = (FirmVO) pageContext.getAttribute("userVO");  
	AdvertiseVO advVO = (AdvertiseVO) request.getAttribute("advVO");
	session.setAttribute("pageReq", "/advertise/AdvertiseApply.jsp");
%>





<!DOCTYPE html>
<html lang="">
<head>
<!--固定的 -->
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>申請廣告</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="../css/css.css">
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
<!--固定的 -->
	</head>

<style type="text/css">
.login-modal-header-success {
    color:#fff;
    padding:9px 15px;
    border-bottom:1px solid #eee;
    background-color: #5cb85c;
    -webkit-border-top-left-radius: 5px;
    -webkit-border-top-right-radius: 5px;
    -moz-border-radius-topleft: 5px;
    -moz-border-radius-topright: 5px;
     border-top-left-radius: 5px;
     border-top-right-radius: 5px;
}
.login-modal-header-warning {
    color:#fff;
    padding:9px 15px;
    border-bottom:1px solid #eee;
    background-color: #f0ad4e;
    -webkit-border-top-left-radius: 5px;
    -webkit-border-top-right-radius: 5px;
    -moz-border-radius-topleft: 5px;
    -moz-border-radius-topright: 5px;
     border-top-left-radius: 5px;
     border-top-right-radius: 5px;
}
.login-modal-header-danger {
    color:#fff;
    padding:9px 15px;
    border-bottom:1px solid #eee;
    background-color: #d9534f;
    -webkit-border-top-left-radius: 5px;
    -webkit-border-top-right-radius: 5px;
    -moz-border-radius-topleft: 5px;
    -moz-border-radius-topright: 5px;
     border-top-left-radius: 5px;
     border-top-right-radius: 5px;
}
.login-modal-header-info {
    color:#fff;
    padding:9px 15px;
    border-bottom:1px solid #eee;
    background-color: #5bc0de;
    -webkit-border-top-left-radius: 5px;
    -webkit-border-top-right-radius: 5px;
    -moz-border-radius-topleft: 5px;
    -moz-border-radius-topright: 5px;
     border-top-left-radius: 5px;
     border-top-right-radius: 5px;
}
.login-modal-header-primary {
    color:#fff;
    padding:9px 15px;
    border-bottom:1px solid #eee;
    background-color: #428bca;
    -webkit-border-top-left-radius: 5px;
    -webkit-border-top-right-radius: 5px;
    -moz-border-radius-topleft: 5px;
    -moz-border-radius-topright: 5px;
     border-top-left-radius: 5px;
     border-top-right-radius: 5px;
}

.login-modal-header-pink {
    color:#fff;
    padding:9px 15px;
    border-bottom:1px solid #eee;
    background-color: pink;
    -webkit-border-top-left-radius: 5px;
    -webkit-border-top-right-radius: 5px;
    -moz-border-radius-topleft: 5px;
    -moz-border-radius-topright: 5px;
     border-top-left-radius: 5px;
     border-top-right-radius: 5px;
}



.login-modal-title{
    color: white;
    font-size: 20px;
    font-style: bold;
}

#fgps {
	margin: 10px;
}


.imgmemberlogin{
				height: 350px;
				background:#fcc url('../img/advertise.jpg')no-repeat center top;
				background-size:cover  ;
			}

</style>
<body>


<jsp:include page="/front_end/frontTOP.jsp" flush="true"/>
<!-- 	登入錯誤訊息視窗 -->
	<c:if test="${not empty errorMsgs}">
			<font color='red' style="display:none;">請修正以下錯誤:
				<ul id="errormessage" >
					<c:forEach var="message" items="${errorMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</font>
		</c:if>
	
	<button type="button" data-toggle="modal" data-target="#errorModal"
		id="errorBtn" style="display:none;">Open Modal</button>
	<div class="modal fade" id="errorModal" role="dialog" style="top: 25%;">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header modal-header-danger" style="height: 60px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" style="color:#555;">錯誤訊息</h4>
				</div>
				<div class="modal-body">
					<p id="errorMsg" style="font-size: 16px;"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>
	
<!-- 	錯誤訊息視窗結束 -->
	
	
	
	
<!-- 	登入成功視窗 -->
		<c:if test="${not empty SMsgs}">
			<font color='red' style="display: none;">
				<ul id="Smessage">
					<c:forEach var="message" items="${SMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</font>
		</c:if>
		
		<button type="button" data-toggle="modal" data-target="#SModal"
		id="SBtn" style="display:none;">Open Modal</button>
	<div class="modal fade" id="SModal" role="dialog" style="top: 25%;display: none;">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header modal-header-danger" style="height: 60px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" style="color:#555;">申請成功</h4>
				</div>
				<div class="modal-body">
					<p id="SMsg" style="font-size: 16px;"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>
		
<!-- 	登入成功視窗結束 -->

	<FORM METHOD="post" ACTION="adv.do" name="form1" enctype="multipart/form-data">


		<div class="container">
			<div class="modal-dialog" role="document" style="width:750px;">
				<div class="modal-content">
					<div class="imgmemberlogin"></div>
					<div class="modal-header">
						<p class="login-modal-title" id="myModalLabel" style="text-align: center; font-weight: bold; color: #888;">廣告申請</p>
					</div>
					<div class="modal-body">
						<div class="input-group" style="width: 100%;">
							<div class="input-group-addon input-sum">廠商名稱：</div>
							<input type="text" class="form-control" name="fir_name" readonly style="width:70%;"
								 value="<%=(userVO == null) ? "" : userVO.getFir_name()%>" />
						</div>
						<div class="input-group" style="width: 100%;">
							<div class="input-group-addon input-sum">廣告標題：</div>
							<input type="text" class="form-control" name="adv_title"  id="adv_title" style="width:70%;"
								placeholder="" 	value="<%=(advVO == null) ? "" : advVO.getAdv_title()%>" />
							<i id="checktitle" class="fa fa-times-circle" aria-hidden="true" "
								style="color: rgb(10, 200, 0); font-size: 10px; padding-top: 10px;width:30%;text-align:center;">請填寫標題</i>
							
							</div>
							
						<div class="input-group" style="width: 100%;">
							<div class="input-group-addon">廣告圖片：</div>
							<input type="file" class="form-control" name="adv_picture" id="adv_picture" style="padding:0px;width:70%;">
							
							<i id="checkpicture" class="fa fa-times-circle" aria-hidden="true"
								style="color: rgb(10, 200, 0); font-size: 10px; padding-top: 10px;width:30%;text-align:center;">請選擇圖片</i>
							</div>
						<div class="input-group" style="text-align: center;width:100%;">
							<img id="advimg" src="<%=request.getContextPath() %>/advertise/img/AdvertiseAdd.jpg" style="text-align: center; width: 600px;height:150px;">
						</div>
					</div>		
					<div class="input-group" style="text-align: center; width: 100%;">
						<input id="Btn" type="submit" value="請填寫完成" class="btn btn-info" disabled="true">
					</div>
				</div>
			</div>
		</div>
	<input type="hidden" name="fir_no" value="${userVO.fir_no }">
	<input type="hidden" name="fir_name" value="${userVO.fir_name }">
	<input type="hidden" name="action" value="insertadv">

	</FORM>

	<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script
  		src="https://code.jquery.com/jquery-3.2.1.js"
  		integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
  		crossorigin="anonymous"></script>
  		<script src="<%=request.getContextPath() %>/advertise/js/AdvertiseApply.js"></script>
</html>
