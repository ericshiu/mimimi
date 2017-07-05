<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*" import="java.util.*"
	import="com.point_record.model.*" import="com.firm.model.*"
	import="com.postpartum_care.model.*"%>

<%
	session.setAttribute("pageReq", "/member/MyForumReport.jsp");
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
<title>廠商檢舉</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.js"
	integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
	crossorigin="anonymous"></script>
</head>
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


		<div class="container" style="border-style:dashed;border-width:3px 3px 3px 3px;padding:0px;">

		<div class="col-xs-12  col-lg-2 left" style="text-align:center;width:20%;">
			<jsp:include page="/member/MySelect.jsp" flush="true"/>
		</div>

<!-- 		右選單開始 -->


		
		<div class="col-xs-12  col-lg-10 right"
			style="height: 100%; background-color: #fff; font-size: 20px; width:80%;">
			<h2>廠商檢舉：</h2>
			<div class="col-xs-12 col-lg-12 right1"
				style="background-color: #fff; padding-left: 10px; width: 100%;">


				<div id="exTab2" class="container" style="width: 100%;">
					<ul class="nav nav-tabs">
						<li id="li0" class="active"><a href="#0" data-toggle="tab">檢舉</a></li>
						


					</ul>

					<div class="tab-content ">
						<div class="tab-pane active" id="0">
							<br>

							<div class="panel panel-success">
								<div class="panel-heading">
									<h3 class="panel-title" style="text-align: left;">檢舉紀錄</h3>
								</div>
								<table class="table table-hover" style="text-align: center;">
									<thead>
										<tr>
											<th style="width: 10%">序號</th>
											<th style="width: 20%">檢舉編號</th>
											<th style="width: 20%">檢舉廠商</th>
											<th style="width: 20%">檢舉內容</th>
											<th style="width: 20%">日期</th>
											<th style="width: 10%">檢舉狀態</th>

										</tr>
									</thead>
								</table>
								<div style="overflow: auto; height: 600px;">
									<table class="table table-hover" style="text-align: center;">

										<tbody>
											<jsp:useBean id="PCRSvc" scope="page" class="com.pc_report.model.PcReportService" />
											<%int q=0; %>
											<c:forEach var="PCRVO" items="${PCRSvc.all}">
												<c:if test="${PCRVO.mem_no==userVO.mem_no }">
													<tr><%q++; %>
														<td style="width: 10%"><%=q %></td>
														<td style="width: 20%">${PCRVO.pcr_no}</td>
														<td style="width: 20%">${PCRVO.pc_no}</td>
														<th style="width: 20%">${PCRVO.pcr_content}</th>
														<td style="width: 20%">${PCRVO.pcr_date}</td>
														<c:if test="${PCRVO.pcr_status=='0' }">
														<td style="width: 10%">未審核</td>
														</c:if>
														<c:if test="${PCRVO.pcr_status=='1' }">
														<td style="width: 10%">通過</td>
														</c:if>
														<c:if test="${PCRVO.pcr_status=='2' }">
														<td style="width: 10%">未通過</td>
														</c:if>
														
													</tr>
												</c:if>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>


						</div>
						

					</div>
				</div>
			</div>
		</div>

		<!-- personal內容結束 -->
</div>
		<!-- -----------------內容---------------------------- -->
		<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>


</html>

<script>
	function $id(id) {
		return document.getElementById(id);
	}

	function init() {

	}

	window.onload = init;
</script>