<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*" import="java.util.*"
	import="com.point_record.model.*" import="com.firm.model.*"
	import="com.postpartum_care.model.*"%>

<%
	session.setAttribute("pageReq","/forum/ForumINDEX.jsp");
%>
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
	MemberVO userVO = (MemberVO) pageContext.getAttribute("userVO");
%>

<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>討論區首頁</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/css.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">

</head>
<style>

.carousel-control{

position:none;
width:0;
}


</style>
<body>


	<jsp:include page="/front_end/frontTOP.jsp" flush="true" />

	<!-- --------------內容--------------------- -->

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font color='red' style="display: none;">請修正以下錯誤:
			<ul id="errormessage">
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>
	<button type="button" data-toggle="modal" data-target="#errorModal"
		id="errorBtn" style="display: none;">Open Modal</button>
	<div class="modal fade" id="errorModal" role="dialog" style="top: 25%;">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header modal-header-danger" style="height: 60px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">錯誤訊息</h4>
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





	<div class="container">


		<div id="carousel-id" class="carousel slide" data-ride="carousel">

			<!-- 幻燈片主圖區 -->
			<div class="carousel-inner">

				<div class="item active">
					<div
						style="background: #fff url('../img/imgmemberlogin.jpg') no-repeat center top; background-size: contain; width: 1200px; height: 285px;">
						<!-- 	            <img src="../img/elephant2.jpg" alt="" style="width: 1200px;height: 285px;"> -->
					</div>
					<div class="container">
						<div class="carousel-caption">
<!-- 							<h1>CSS可樂好喝超爽快</h1> -->
						</div>
					</div>
				</div>

				<jsp:useBean id="advSvc" scope="page"
					class="com.advertise.model.AdvertiseService" />
				<c:forEach var="advVO" items="${advSvc.allAdv}">
					<c:if test="${advVO.adv_status=='1'}">


						<div class="item ">
							<div
								style="background: #fff url('<%= request.getContextPath() %>/AdvertisePhoto?adv_no=${advVO.adv_no}') no-repeat center top;
	background-size: contain;width: 1200px;height: 285px;"></div>
							<%-- 	            <img src="<%= request.getContextPath() %>/AdvertisePhoto?adv_no=${advVO.adv_no}">"> --%>
							<div class="container">
								<div class="carousel-caption">
<%-- 									<h1 style="font-size: bolder; color: #555;">${advVO.adv_title }</h1> --%>

								</div>
							</div>
						</div>
					</c:if>
				</c:forEach>


			</div>

			<!-- 上下頁控制區 -->
			<a class="left carousel-control" href="#carousel-id"
				data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
			<a class="right carousel-control" href="#carousel-id"
				data-slide="next"><span
				class="glyphicon glyphicon-chevron-right"></span></a>
		</div>

		<br>
		
		<br>
		<br>
		<br>


					
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forum/FA.do" name="form1">
       <div class="col-xs-12  col-lg-12">
     	<div class="col-xs-12  col-lg-4" style="padding:0px;">
       	文章標題關鍵字:
       <input type="text" name="fa_title" value="" style="width:50%;"><br>
       </div>
       <div class="col-xs-12  col-lg-6" style="padding:0px;display:inline;">
       	文章內容關鍵字:
       <input type="text" name="fa_content" value="" style="width:30%;">
        <button type="submit" value="" class="btn btn-success">查詢</button>
        <input type="hidden" name="action" value="listArticle_BycompositeQuery">
    	</div>
      
        <div class="col-xs-12  col-lg-2" style="padding:0px;text-align:right;">
        <c:if test="${not empty userVO}">
			<div class="col-xs-12  col-lg-12"
				style="border-style: solid; border-width: 0px 0px 0px 0px; padding: 3px;border-color:#5a89bc;border-style: dashed;text-align:right; ">
				<button type="button"
					onclick="window.location.href='<%=request.getContextPath()%>/forum/ArticleADD.jsp'"
					class="btn btn-primary"
					style="background-color: #b39873; border-color: #b39873;">新增文章</button>
			</div>
			<br>
		</c:if>
        </div>
        <br>
        </div>
     </FORM>
		<div class="col-xs-12  col-lg-12" style="height:20px;"></div>
		<div class="col-xs-12  col-lg-12 right"	style="height: 100%; font-size: 20px; width: 100%;">
			<div class="col-xs-12 col-lg-12 right1"	style="padding-left: 10px; width: 100%; border-style: dashed; border-width: 3px; padding: 10px;">
				<div>
			
		</div>
				<div id="exTab2" class="container" style="width: 100%;">
					<ul class="nav nav-tabs" style="background-color:#d9edf7;">
						<li id="li0" class="active"><a href="#0" data-toggle="tab"><i class="glyphicon glyphicon-dashboard"></i>&nbsp;&nbsp;最新文章</a></li>
						<li id="li1"><a href="#1" data-toggle="tab"><i class="glyphicon glyphicon-fire"></i> &nbsp; &nbsp;熱門文章</a></li>

					</ul>

					<div class="tab-content ">
						<div class="tab-pane active" id="0">
							<br>

							<div class="panel panel-success" >
								<div class="panel-header-primary">
									<h3 class="panel-title">
										
									</h3>
								</div>
								<table class="table table-hover" style="text-align: right;">

									<tr style="background-color:rgba(240,140,160,0.4);color:#555;">
										<th style="width: 6%; text-align: center;">序號</th>
										<th style="width: 25%; text-align: center;">文章標題</th>
										<th style="width: 8%; text-align: center;">收藏數</th>
										<th style="width: 15%; text-align: center;">作者</th>
										<th style="width: 25%; text-align: center;">發表日期</th>
										<th style="width: 25%; text-align: center;">最後修正日期</th>

									</tr>

								</table>
								<div class="favoriate" style="overflow: auto; height: 700px;">
									<jsp:include page="/forum/ArticleLatest.jsp" flush="true" />
								</div>
							</div>


						</div>
						<div class="tab-pane" id="1">
							<br>

							<div class="panel panel-success" style="width: 100%;">
								<div class="panel-header-danger">
									<h3 class="panel-title">
										
									</h3>
								</div>
								<table class="table table-hover" style="text-align: right;">

									<tr style="background-color:rgba(240,140,160,0.4);color:#555;">
										<th style="width: 6%; text-align: center;">序號</th>
										<th style="width: 25%; text-align: center;">文章標題</th>
										<th style="width: 8%; text-align: center;">收藏數</th>
										<th style="width: 15%; text-align: center;">作者</th>
										<th style="width: 25%; text-align: center;">發表日期</th>
										<th style="width: 25%; text-align: center;">最後修正日期</th>

									</tr>

								</table>
								<div class="favoriate" style="overflow: auto; height: 700px;">
									<jsp:include page="/forum/ArticleHot.jsp" flush="true" />
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
</div>


<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>
<script src="https://code.jquery.com/jquery.js"></script>
<!-- <script src="https://code.jquery.com/jquery-3.2.1.js" -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</html>
