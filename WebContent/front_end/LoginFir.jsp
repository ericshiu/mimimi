<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*" import="java.util.*"
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


%>





<!DOCTYPE html>
<html lang="">
<head>
<!--固定的 -->
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>廠商登入頁面</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
<!--固定的 -->
	</head>
	
	
		</head>
		<style>
	.modal-header-success {
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
.modal-header-warning {
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
.modal-header-danger {
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
.modal-header-info {
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
.modal-header-primary {
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

.modal-header-pink {
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


.exampleModal{
    padding: 20px;
}


.modal-title{
    color: white;
    font-size: 20px;
    font-style: bold;
}



        #modal-dialog-login{
          color:#555;
          width: 1300px;
          background-color: pink;
        }   
          
        #modal-body-login {   
          background-color: pink;
          height: 650px;
          color:#555;
        }  
        #p1{
          font-size: 16px;
          color:#555;
        }
	
	</style>
	
<body>


<jsp:include page="/front_end/frontTOP.jsp" flush="true"/>

		
<!-- 有成功訊息的話就會跳出視窗 -->
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
					<h4 class="modal-title">註冊成功</h4>
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
<div class="col-xs-12 col-sm-12">
    <div class="modal-dialog modal-sm" role="document" >
    <div class="modal-content" >
       <div class="modal-header modal-header-info">
        
        <p class="modal-title" id="exampleModalLabel">商場廠商登入</p>
      </div>
      <div class="modal-body">
        <form METHOD="post" ACTION="fir_PC_Login.do">
          <div class="form-group">
            <label for="exampleInputAccount1">帳號：</label>
            <input type="text" class="form-control" id="fir_PC_LoginId"  name="fir_PC_LoginId" placeholder="Account" value="456456">
          </div>
          <div class="form-group">
            <label for="exampleInputPassword1">密碼：</label>
			<input type="password" class="form-control" id="fir_PC_LoginPsw" name="fir_PC_LoginPsw" placeholder="Password" value="456456">
          </div>
       
      </div>
      <div class="modal-footer">
     	 <button type="button" class="btn btn-default"><a href="#" data-toggle="modal" data-target="#ModalRegister" data-whatever="" data-dismiss="modal">註冊會員</a></button>
        <button type="button" class="btn btn-default">忘記密碼</button>
        <button type="submit" class="btn btn-info">登入</button>
        <input type="hidden" name="action" value="fir_PC_Login">
      </div>
    </div>
 </div>
  </div>
		
		

<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script
  		src="https://code.jquery.com/jquery-3.2.1.js"
  		integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
  		crossorigin="anonymous"></script>
  		<script src="<%=request.getContextPath() %>/front_end/js/SuccessJoin.js"></script>
</html>

