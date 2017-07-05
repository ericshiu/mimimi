<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.postpartum_care.model.*" import="com.member.model.*"%>
<%@ page import="com.pc_picture.model.*" import="com.pc_application.model.*" import="com.firm.model.*"%>

<%
PostpartumCareVO postpartumCareVO = (PostpartumCareVO) session.getAttribute("postpartumCareVO"); 
PcApplicationVO pcApplicationVO = (PcApplicationVO) request.getAttribute("pcApplicationVO");

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

MemberVO userVO = (MemberVO) pageContext.getAttribute("userVO");  

session.setAttribute("pageReq", "/postpartum_care/ViewOnePC.jsp");


%>

<jsp:useBean id="listPCPs_ByPc_no" scope="session" type="java.util.Set" />
<jsp:useBean id="PCSvc" scope="page" class="com.postpartum_care.model.PostpartumCareService" />

<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

<title>產後照護廠商-${postpartumCareVO.pc_name}</title>
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
<!-- <link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"> -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/css.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/postpartum_care/css/ViewOnePC.css">
<!-- <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script> -->

</head>



<body onload="connect();" onunload="disconnect();">
	<jsp:include page="/front_end/frontTOP.jsp" flush="true"/>

	<c:if test="${not empty errorMsgs}">
		<font color='red'>
			<ul id="errorMsgs" style="display: none;">
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>

	<c:if test="${not empty successMsgs}">
		<font color='red'>
			<ul id="successMsgs" style="display: none;">
				<c:forEach var="message2" items="${successMsgs}">
					<li>${message2}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>
	
	<c:if test="${userVO != null}">
		<input type="hidden" id="myMem_no" value="${userVO.mem_no}" />
	</c:if>	
		
		<br>
		<br>
		<div class="container">
<!-- 照片區-->		
			<div class="row">
				<div class="col-xs-12 col-sm-offset-3 col-sm-6">
				
  		<div id="carousel-id" class="carousel slide" data-ride="carousel" style="height:300px; ">
      <!-- 幻燈片小圓點區 -->
      <ol class="carousel-indicators">
      	<% for (int i=0; i<listPCPs_ByPc_no.size(); i++) { %>
      		<% if (i==0){ %>
      		<li data-target="#carousel-id" data-slide-to=<%=i %> class="active"></li>
      		<% }else{%>	
      		<li data-target="#carousel-id" data-slide-to=<%=i %> class=""></li>
      		<% }%>
      	<% }%>
      </ol>
      <!-- 幻燈片主圖區 -->
      <div class="carousel-inner" >
      
<%--       	<% for (int i=0; i<listPCPs_ByPc_no.size(); i++) { %> --%>
<%--       		<% if (i==0){ %>     --%>
<!--            <div class="item active"> -->
<%--               <img src="<%=request.getContextPath()%>/PC_PictureTransfer?pcp_no=${pcPictureVO.pcp_no}" class="img-responsive"> --%>
<!--            </div>     		   -->
<%--        		<% }else{%>	   --%>
<!--             <div class="item"> -->
<%--               <img src="<%=request.getContextPath()%>/PC_PictureTransfer?pcp_no=${pcPictureVO.pcp_no}" class="img-responsive"> --%>
<!--            </div>        		   		  -->
<%--        		<% }%> --%>
<%--       	<% }%>      --%>
 
		 	<c:forEach var="pcPictureVO" items="${listPCPs_ByPc_no}" varStatus="c">
		 		<c:if test="${c.index==0}"> 
		 		     <c:set var="isActive" value="active"></c:set>
		 		</c:if>
		 		<c:if test="${c.index!=0}"> 
		 		     <c:set var="isActive" value=""></c:set>
		 		</c:if>
	            <div class="item ${isActive}"> 
         	      <img src="<%=request.getContextPath()%>/PC_PictureTransfer?pcp_no=${pcPictureVO.pcp_no}" class="img-responsive" > 
          	  	</div>   
			</c:forEach>
	
        
      </div>
      <!-- 上下頁控制區 -->
      <a class="left carousel-control" href="#carousel-id" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
      <a class="right carousel-control" href="#carousel-id" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
   <div class="clear">

  </div>
  </div>				
	
				</div>
				
				<div class="col-xs-12 col-sm-3">
					<img src="img/banner-1_bg-2.png" class="img-responsive" style="border-radius: 20px; margin-top:160px;"> 
				</div>		
			</div>
			<br>
			<br>
<!-- 資料區-->
			<div class="row">
				<div class="col-xs-12 col-sm-6" id="pcInfo">
						<!--名稱 -->
						<div class="input-group" style="width: 100%;">
							<div class="input-group-addon input-sum" style="background-color: #ddd;">廠商名稱：</div>
							<input type="text" class="form-control" name="pc_name"
								id="pc_name" readonly
								value="<%=(postpartumCareVO == null) ? "好貝貝月子中心" : postpartumCareVO.getPc_name()%>" />
						</div>
						<br>
						<!--類型 -->
						<div class="input-group" style="width: 100%;">
							<div class="input-group-addon input-sum" style="background-color: #ddd;">廠商類型：</div>
							<input type="text" class="form-control" name="pc_type"
								id="pc_type" readonly
								value="<%=(postpartumCareVO == null) ? "月子餐廠商" : postpartumCareVO.getPc_type()%>" />
						</div>
						<br>											
						<!-- 連絡資訊 -->
						<div class="input-group" style="width: 100%;">
							<div class="input-group-addon input-sum" style="background-color: #ddd;">連絡電話：</div>
							<input type="text" class="form-control" name="pc_phone"
								readonly
								value="<%=(postpartumCareVO == null) ? "03-3335422" : postpartumCareVO.getPc_phone()%>" />
						</div>
						<br>	
						<div class="input-group" style="width: 100%;">
							<div class="input-group-addon" style="background-color: #ddd;">電子信箱：</div>
							<input type="email" class="form-control" name="pc_email"
								readonly
								value="<%=(postpartumCareVO == null) ? "koykogina@yahoo.com.tw" : postpartumCareVO.getPc_email()%>" />
						</div>
						<br>	
						<div class="input-group" style="width: 100%;">
							<div class="input-group-addon" style="background-color: #ddd;">連絡地址：</div>

								<input type="text"
								class="form-control" name="pc_address" id="pc_address" readonly
								value="<%=(postpartumCareVO == null) ? "" : postpartumCareVO.getPc_address()%>" />
							
						</div>
						<br>	
						
						<!-- 地圖放這裡 -->
						<div style="padding:20px;">
						<div id="myMap"></div>
						</div>
										
				</div>
				
				
		<!-- 這一排放簡介 -->		
				<div class="col-xs-12 col-sm-6">

					<div class="panel panel-danger"  style="margin:0px 20px 20px 20px; padding:10px;">
					<div class="panel-heading">
						<h3 class="panel-title">獨家優惠在這裡</h3>
					</div>
				
					<table class="table table-hover table-striped">
						<tr>
							<c:if test="${postpartumCareVO.pc_type=='月子中心'}"> 
		 		     			<th>申請預約參觀</th>
		 					</c:if>
		 					<c:if test="${postpartumCareVO.pc_type=='月子餐廠商'}"> 
		 		     			<th>申請預約試吃</th>
		 					</c:if>
						
		 					<c:if test="${postpartumCareVO.pc_point==0}"> 
		 		     			<th><i class="fa fa-times" aria-hidden="true" style="color:red"></i>&nbsp;目前沒有開放喔</th>
		 					</c:if>
		 					<c:if test="${postpartumCareVO.pc_point>0}"> 
		 		     			<th><i class="fa fa-thumbs-o-up" aria-hidden="true" style="color:#5F94BF"></i>&nbsp;歡迎填單預約</th>
		 					</c:if>		 					
						</tr>
						
						<tr>
		 		     		<th>會員預約好禮</th>				
		 					<c:if test="${postpartumCareVO.pc_gift=='Y'}"> 
		 		     			<th><i class="fa fa-gift" aria-hidden="true" style="color:#5F94BF"></i>&nbsp;有提供驚喜小禮</th>
		 					</c:if>
		 					<c:if test="${postpartumCareVO.pc_gift=='N'}"> 
		 		     			<th><i class="fa fa-times" aria-hidden="true" style="color:red"></i>&nbsp;目前沒有提供喔</th>
		 					</c:if>		 					
						</tr>
						
						<tr>
		 		     		<th>會員下訂折扣</th>				
		 					<c:if test="${postpartumCareVO.pc_bonus=='Y'}"> 
		 		     			<th><i class="fa fa-usd " aria-hidden="true" style="color:#5F94BF"></i>&nbsp;有提供會員額外折扣</th>
		 					</c:if>
		 					<c:if test="${postpartumCareVO.pc_bonus=='N'}"> 
		 		     			<th><i class="fa fa-times" aria-hidden="true" style="color:red"></i>&nbsp;目前沒有提供喔</th>
		 					</c:if>		 				
						</tr>	
						
		 					<c:if test="${postpartumCareVO.pc_point!=0}"> 
		 		     			<tr aligh="center">
		 		     			<th>
		 		     				預約需求積分：<span  style="color:red; font-size:1.5em;">${postpartumCareVO.pc_point}</span>
		 		     			</th>
		 		     			<td>
								  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postpartum_care/PC.do">
									<button type="submit" class="btn btn-info">
			    						<i class="fa fa-pencil" aria-hidden="true"></i> 馬上預約
									</button>
								    <input type="hidden" name="pc_no" value="${postpartumCareVO.pc_no}">
								    <input type="hidden" name="mem_no" value="${userVO.mem_no}">
								    <input type="hidden" name="action" value="PC_application">
								    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
								    <!--送出本網頁的路徑給Controller-->		 
								    </FORM>		     					 		     			
		 		     			</td>
		 		     			</tr>
		 					</c:if>											
						
					</table>

					<table class="table table-hover table-striped" style="border:3px dashed #FF96A7;">
<!-- 						<tr><th colspan="3" style="text-align: center;">廠商評價</th></tr> -->
						<tr>
							<th style="text-align: center;"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i>優良</th>
							<th style="text-align: center;"><i class="fa fa-smile-o" aria-hidden="true"></i>普通</th>
							<th style="text-align: center;"><i class="fa fa-thumbs-o-down" aria-hidden="true"></i>差勁</th>
						</tr>
						<tr>
							<td style="text-align: center;">${postpartumCareVO.pc_eva_good}</td>
							<td style="text-align: center;">${postpartumCareVO.pc_eva_normal}</td>
							<td style="text-align: center;">${postpartumCareVO.pc_eva_bad}</td>
						</tr>
					</table>
					
										
					</div>	
					
						
					<div>  <!-- 簡介 -->
					
					<textarea readonly ><%=(postpartumCareVO == null) ? ":)" : postpartumCareVO.getPc_summary()%> 
					</textarea>
			
					</div>			
				</div>
			</div>	
					
			
		</div>  <!-- container結束 -->




	<button type="button" data-toggle="modal" data-target="#errorModal"
		id="errorBtn" style="display: none;">Open Modal</button>
	<div class="modal fade" id="errorModal" role="dialog" style="top: 25%;">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header modal-header-danger" style="height: 60px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<p class="login-modal-title">喔~喔!! 出了一點問題：</p>
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

	<button type="button" data-toggle="modal" data-target="#successModal"
		id="successBtn" style="display: none;">Open Modal</button>
	<div class="modal fade" id="successModal" role="dialog" style="top: 25%;">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header modal-header-success" style="height: 60px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<p class="login-modal-title">恭喜你</p>
				</div>
				<div class="modal-body">
					<p id="successMsg" style="font-size: 16px;"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>


<!-- websocket-->
<div class="modal fade" id="webSocket" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document" style="margin-top:200px;">

    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <p class="modal-title" id="websocketName"></p>
      </div>
      <div class="modal-body"  align="center">
		 <p id="message"></p>
		 <img id="messageImg" style="width:100px;" src="img/review.png">
      </div>
    </div>
  </div>
</div>
<!-- websocket結束 -->


	<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCtNh35TMrivOI_KettMn5BTjYPHg2cN34"  type="text/javascript""></script>


	<script src="<%=request.getContextPath()%>/postpartum_care/js/ViewOnePC.js"></script>
	
</body>
</html>
<script>
var MyPoint = "/OrdServer";
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
var webSocket;

function $id(id){
	  return document.getElementById(id);
}

function connect() {
	webSocket = new WebSocket(endPointURL);
	
	webSocket.onopen = function(event) {
		
		
	};

	webSocket.onmessage = function(event) {

        var jsonObj = JSON.parse(event.data);
        var myMem_no = $id("myMem_no").value;
        
		if(jsonObj.mem_no==myMem_no) {
			if(jsonObj.type=='application') {
		        $id("message").innerText = jsonObj.message;
		        $id("websocketName").innerText = jsonObj.pc_title;	
		        $('#webSocket').modal('show'); 
			}
		}

        
         
	};

}

function disconnect () {
	webSocket.close();
}

</script>
