<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
	MemberVO userVO = (MemberVO) session.getAttribute("userVO");
%>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

<title>一般會員註冊</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="js/address.js"></script>
<xml id="address" src="xml/ZipCode.xml">
<script language="JavaScript" src="js/calendarcode.js"></script>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/mem_idCheck.js"></script>
<script language="JavaScript" src="js/mem_all.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">

<style type="text/css">
.input-group {
	margin: 10px;
}

.imgmemberlogin {
	height: 250px;
	background: #fcc url('../img/imgmemberlogin.jpg') no-repeat center top;
	background-size: cover;
}

#imgdiv {
	height: 150px;
	width: 150px;
	border-radius: 100%;
}
</style>
</head>

<div id="popupcalendar" class="text" style="z-index: 20"></div>
<body>
	<jsp:include page="/front_end/frontTOP.jsp" flush="true" />

	<FORM METHOD="post" ACTION="mem.do" name="form1" id="form1" enctype="multipart/form-data">
		<!-- onsubmit="return false; -->

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


		<div class="modal-dialog" role="document" style="width: 600px;">
			<div class="modal-content">
				<div class="imgmemberlogin" src=""></div>
				<div class="modal-header">

					<h4 class="modal-title" id="myModalLabel"
						style="text-align: center; font-weight: bold; color: #888;">一般會員註冊</h4>
				</div>
				<div class="modal-body">
			<div class="form-group">
						<div class="input-group" style="width: 60%; float: left;">
							<div class="input-group-addon input-sum">廠商帳號：</div>
							<input type="text" class="form-control" name="mem_id" id="_id"
								maxlength="20" placeholder="請輸入6~20碼"
								style="width: 100%; height: 42px;" onchange=getInfo()
								value="<%=(memberVO == null) ? "beautiful" : memberVO.getMem_id()%>" /> <span
								class="input-group-btn"> <a class="btn btn-info"
								type="button" onClick=getInfo()><i class="fa fa-search-plus"></i></a>
							</span>
						</div>

						<div class="input-group"
							style="padding: 0px; width: 95%; display: none;">
							<div class="input-group-addon" style="margin: 0px; padding: 0px;">
								<button type="button" class="btn btn-primary" onClick=getInfo()
									style="margin: 0px; padding: 0px;">帳號驗證</button>
							</div>
							<input type="hidden" id="Input_idCheck" name="Input_mem_idCheck"></input>
							<label class="form-control" name="mem_idCheck" id="_idCheck"></label>

						</div>
						<div id="checkword" class="input-group"
							style="float: left; padding-top: 20px; width: 100px; display: none;">

							<i id="iconcheck" class="fa fa-check-circle" aria-hidden="true"
								style="color: rgb(10, 200, 0); font-size: 18px; padding-top: 20px"></i>

						</div>
						<div style="clear: both;"></div>

		
					<div class="input-group" style="width: 97%;">
						<div class="input-group-addon">姓名：</div>
						<input type="text" class="form-control" name="mem_name"
							placeholder="真實姓名"
							value="<%=(memberVO == null) ? "林志玲" : memberVO.getMem_name()%>" />
					</div>
					<div class="input-group" style="width: 97%;">
						<div class="input-group-addon">暱稱：</div>
						<input type="text" class="form-control" name="mem_nike"
							placeholder=""
							value="<%=(memberVO == null) ? "第一名模" : memberVO.getMem_nike()%>" />
					</div>

					<div class="input-group" style="width: 95%;">
					<div class="input-group-addon input-radio">性別：</div>
					<div class="btn-group" data-toggle="buttons">
						<label class="btn btn-default"> <i
							class="fa fa-mars" aria-hidden="true"></i><input
							type="radio" name="mem_sex" autocomplete="off" value="男">
							男
						</label> <label class="btn btn-default"> <i class="fa fa-venus"
							aria-hidden="true"></i><input type="radio" name="mem_sex"
							autocomplete="off" value="女"> 女
						</label>
					</div>
				</div>


				<div class="input-group">
					<div class="input-group-addon">生日：</div>
					<%
						java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());
					%>

					<td bgcolor="#CCCCFF"><input class="cal-TextBox"
						onFocus="this.blur()" size="9" readonly type="text"
						name="mem_birthday"
						value="<%=(memberVO == null) ? date_SQL : memberVO.getMem_birthday()%>">
						<a class="so-BtnLink" href="javascript:calClick();return false;"
						onmouseover="calSwapImg('BTN_date', 'img_Date_OVER',true);"
						onmouseout="calSwapImg('BTN_date', 'img_Date_UP',true);"
						onclick="calSwapImg('BTN_date', 'img_Date_DOWN');showCalendar('form1','mem_birthday','BTN_date');return false;">
							<img align="middle" border="0" name="BTN_date"
							src="images/btn_date_up.gif" width="22" height="17" alt="開始日期">
					</a></td>

				</div>
				<div class="input-group" style="width: 95%;">
					<div class="input-group-addon">連絡電話：</div>
					<input type="text" class="form-control" name="mem_phone"
						placeholder=""
						value="<%=(memberVO == null) ? "0911-223344" : memberVO.getMem_phone()%>" />
				</div>
				<div class="input-group" style="width: 95%;">
					<div class="input-group-addon">電子信箱：</div>
					<input type="email" class="form-control" name="mem_email"
						placeholder=""
						value="<%=(memberVO == null) ? "bucktooth888@gmail.com" : memberVO.getMem_email()%>" />
				</div>
				<div class="input-group" style="width: 95%;">
					<div class="input-group-addon">連絡地址：</div>

					<select name="city" id="city_select"
						style="width: 40%; height: 30px;"></select> <select name="area"
						id="area_select" style="width: 60%; height: 30px;"></select> <input
						type="text" class="form-control" name="mem_address" id="resault"
						value="<%=(memberVO == null) ? "" : memberVO.getMem_address()%>" />
					<input type="hidden" class="form-control" name="mem_address2"
						id="resault2" value="null" /> <input type="hidden" id="carea"
						name="carea" value="${area}"></input> <input type="hidden"
						id="ccity" name="ccity" value="${city}"></input>

				</div>


			


				<div class="input-group" style="width: 95%;">
					<div class="input-group-addon">facebook</div>
					<input type="email" class="form-control" name="mem_facebook"
						value="<%=(memberVO == null) ? "beautiful@gmail.com" : memberVO.getMem_email()%>" />
				</div>
				<div class="input-group" style="width: 95%;">
					<div class="input-group-addon">google：</div>
					<input type="email" class="form-control" name="mem_google"
						value="<%=(memberVO == null) ? "beautiful@gmail.com" : memberVO.getMem_email()%>" />
				</div>

				<div class="input-group" style="width: 95%;">
					<div class="input-group-addon">大頭照：</div>
					<input type="file" class="form-control" name="mem_picture"
						id="mem_picture">
				</div>

				<div class="input-group" style="text-align: center; width: 100%;">

					<img id="imgdiv" src="../img/boy.png">
				</div>
			</div>
			</div>
			<input type="hidden" name="mem_point" value="0" /> <input
				type="hidden" name="mem_password" value="null" /> <input
				type="hidden" name="mem_actual_point" value="0" /> <input
				type="hidden" name="mem_authority" value="y" /> <input
				type="hidden" name="mem_no" value=memberVO.getMem_id() /> <input
				type="hidden" name="action" value="insert">
			<div class="input-group" style="text-align: center; width: 100%;">
				<!-- 					<button type="submit" class="btn btn-info" id="RegisterBtn" disabled="true">檢查</button> -->
				<button type="submit" class="btn btn-info" id="RegisterBtngo"
					onClick=checknotnull()>送出新增</button>
			</div>
</div>

		</div>

		</div>

	</FORM>




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

	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>
</html>

