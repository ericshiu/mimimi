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
	MemberVO userVO = (MemberVO) pageContext.getAttribute("userVO"); ////可改成 PC 或是 Firm
	session.setAttribute("pageReq", "/firm/PersonalFir.jsp");////這行是在存重導路徑，所以要打本頁的網址
%>





<!DOCTYPE html>
<html lang="">
<head>
<!--固定的 -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>一般會員註冊</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="../css/css.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="js/address.js"></script>
<xml id="address" src="xml/ZipCode.xml"> <script
	language="JavaScript" src="js/calendarcode.js"></script>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/mem_idCheck.js"></script> <script
	language="JavaScript" src="js/mem_all.js"></script>
</head>
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


	<FORM METHOD="post" ACTION="mem.do" name="form1" id="form1"
		enctype="multipart/form-data">
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

					<p class="login-modal-title" id="myModalLabel"
						style="text-align: center; font-weight: bold; color: #888;">一般會員註冊</p>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<div class="input-group" style="width: 60%; float: left;">
							<div class="input-group-addon input-sum">帳號：</div>
							<input type="text" class="form-control" name="mem_id" id="_id"
								maxlength="20" placeholder="請輸入6~20碼"
								style="width: 100%; height: 42px;" onchange=getInfo()
								value="<%=(userVO == null) ? "" : userVO.getMem_id()%>" />
							<span class="input-group-btn"><i class="fa fa-search-plus"></i></a>
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
							<input type="text" class="form-control" name="mem_name" id="mem_name"
								id="mem_name" placeholder="真實姓名"
								value="<%=(userVO == null) ? "" : userVO.getMem_name()%>" />
						</div>
						<div class="input-group" style="width: 97%;">
							<div class="input-group-addon">暱稱：</div>
							<input type="text" class="form-control" name="mem_nike" id="mem_nike"
								placeholder=""
								value="<%=(userVO == null) ? "" : userVO.getMem_nike()%>" />
						</div>

						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon input-radio">性別：</div>
							<div class="btn-group" data-toggle="buttons">
								<label class="btn btn-default" id="mem_sex_label_m"> 
									<i class="fa fa-mars" aria-hidden="true"></i>
								<input type="radio" name="mem_sex" autocomplete="off" value="男" id="mem_sex_m"> 男	
								</label> 
								<label class="btn btn-default" id="mem_sex_label_f"> 
								<i class="fa fa-venus" aria-hidden="true"></i>
								<input type="radio" name="mem_sex" autocomplete="off" value="女" id="mem_sex_f"> 女
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
								name="mem_birthday" id="mem_birthday" 
								value="<%=(userVO == null) ? date_SQL : userVO.getMem_birthday()%>">
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
							<input type="text" class="form-control" name="mem_phone" id="mem_phone"
								placeholder=""
								value="<%=(userVO == null) ? "" : userVO.getMem_phone()%>" />
						</div>
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon">電子信箱：</div>
							<input type="email" class="form-control" name="mem_email" id="mem_email"
								placeholder=""
								value="<%=(userVO == null) ? "" : userVO.getMem_email()%>" />
						</div>
						
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon">連絡地址：</div>

							<select name="city" id="city_select"
								style="width: 40%; height: 30px;"></select> <select name="area"
								id="area_select" style="width: 60%; height: 30px;"></select> <input
								type="text" class="form-control" name="mem_address" id="resault"
								value="<%=(userVO == null) ? "" : userVO.getMem_address()%>" />
							<input type="hidden" class="form-control" name="mem_address2"
								id="resault2" value="null" /> <input type="hidden" id="carea"
								name="carea" value="${area}"></input> <input type="hidden"
								id="ccity" name="ccity" value="${city}"></input>

						</div>

<!-- 						<div class="input-group" style="width: 95%;"> -->
<!-- 							<div class="input-group-addon">facebook</div> -->
<!-- 							<input type="email" class="form-control" name="mem_facebook" -->
<%-- 								value="<%=(userVO == null) ? "beautiful@gmail.com" : userVO.getMem_email()%>" /> --%>
<!-- 						</div> -->
<!-- 						<div class="input-group" style="width: 95%;"> -->
<!-- 							<div class="input-group-addon">google：</div> -->
<!-- 							<input type="email" class="form-control" name="mem_google" -->
<%-- 								value="<%=(userVO == null) ? "beautiful@gmail.com" : userVO.getMem_email()%>" /> --%>
<!-- 						</div> -->

						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon">大頭照：</div>
							<input type="file" class="form-control" name="mem_picture"
								id="mem_picture">
						</div>

						<div class="input-group" style="text-align: center; width: 100%;">
							<img id="imgdiv" src="<%=request.getContextPath() %>/img/nullphoto.png">
						</div>
					</div>
				</div>
				<input type="hidden" name="mem_point" value="0" /> <input
					type="hidden" name="mem_password" value="null" /> <input
					type="hidden" name="mem_actual_point" value="0" /> <input
					type="hidden" name="mem_authority" value="y" /> <input
					type="hidden" name="mem_facebook" value="bucktooth888@gmail.com">
				<input type="hidden" name="mem_google"
					value="bucktooth888@gmail.com"> <input type="hidden"
					name="mem_no" value=userVO.getMem_id() /> <input type="hidden"
					name="action" value="insert">
				<div class="input-group" style="text-align: center; width: 100%;">
					<!-- 					<button type="submit" class="btn btn-info" id="RegisterBtn" disabled="true">檢查</button> -->
					<button type="submit" class="btn btn-info" id="RegisterBtngo">送出新增</button>
					<button type="button" class="btn " id="RegisterBtngo"
						onClick=showValue()>神奇小按鈕</button>
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










	<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>
<script src="https://code.jquery.com/jquery.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.js"
	integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
	crossorigin="anonymous"></script>

</html>

<script>

function $id(id){
	return document.getElementById(id);
}

function $Name(name){
	return document.getElementsByName(name);
}

function showValue(){
	$id('_id').value="apple123";
	$id('mem_name').value="洪蘋果";
	$id('mem_nike').value="蘋果";
	$id('mem_birthday').value="1990-07-12";
	$id('mem_phone').value="0923-551951";
	$Name('mem_address')[0].value="中壢市中央路300-1號";
	$Name('mem_address2')[0].value="中壢市中央路300-1號";
	$id('mem_email').value="bucktooth888@gmail.com";
	$('#mem_sex_f').prop("checked", true);
	$('#mem_sex_label_f').addClass("active");
	$('#mem_sex_label_f').addClass("focus");
	

}

</script>
