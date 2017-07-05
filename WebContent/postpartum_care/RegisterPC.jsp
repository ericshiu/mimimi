<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.postpartum_care.model.*" import="com.member.model.*" import="java.util.*"%>
<%@ page import="com.pc_picture.model.*" import="com.firm.model.*"%>
<%
PostpartumCareVO postpartumCareVO = (PostpartumCareVO) request.getAttribute("postpartumCareVO");

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
	PostpartumCareVO userVO = (PostpartumCareVO) pageContext.getAttribute("userVO"); 

	request.setAttribute("pageReq", "/postpartum_care/RegisterPC.jsp");
%>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

<title>產後照護廠商註冊</title>
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/css.css">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/postpartum_care/css/RegisterPC.css">
<!-- <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script> -->
<script src="https://code.jquery.com/jquery.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>


<body>
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

	<FORM METHOD="post" ACTION="PC.do" name="form1" enctype="multipart/form-data">


		<div class="container">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="imgpclogin"></div>
					<div class="modal-header">

						<h4 class="modal-title" id="myModalLabel"
							style="text-align: center; font-weight: bold; color: #888;">產後照護廠商註冊</h4>
					</div>

					<div class="modal-body">


						<!--帳號 -->
						<div class="input-group" style="width: 50%; float: left;">
							<div class="input-group-addon input-sum">廠商帳號：</div>
							<input type="text" class="form-control" name="pc_id" id="pc_id"
								maxlength="20" placeholder="請輸入6~20碼" style="width: 100%;"
								value="<%=(postpartumCareVO == null) ? "" : postpartumCareVO.getPc_id()%>"
								onchange=getInfo() />
							<!-- 							<span class="input-group-btn"> -->
							<!-- 						    <a class="btn btn-info"  type="button"><i class="fa fa-search-plus"></i></a> -->
							<!-- 						    </span>	 -->
						</div>
						<div class="input-group" style="width: 30%; float: left; padding-top: 8px;"
							id="idCheck-div">

							<i id="idCheck-icon"></i>

						</div>
						<div style="clear: both;"></div>


						<!--密碼 -->
						<!-- 						<div class="input-group" style="width:60%;float:left;"> -->
						<!-- 							<div class="input-group-addon input-sum" >廠商密碼：</div> -->
						<!-- 							<input type="password" class="form-control" name="pc_password" id="pc_password" -->
						<!-- 								placeholder="請輸入6~20個密碼" -->
						<%-- 								value="<%=(postpartumCareVO == null) ? "123" : postpartumCareVO.getPc_password()%>" /> --%>
						<!-- 						</div> -->
						<!-- 						<div class="input-group" style="float:left; padding-top:8px;" id="pc_password_div"> -->

						<!-- 						    <i class="fa fa-check-circle" aria-hidden="true" style="color:rgb(10,200,0); font-size:18px" ></i>					     -->
						<!-- 						可以使用  -->
						<!-- 						</div>			     -->
						<!-- 						<div style="clear:both;"></div>		 -->

						<!-- 						<div class="input-group" style="width:60%;float:left;"> -->
						<!-- 							<div class="input-group-addon input-sum">密碼確認：</div> -->
						<!-- 							<input type="password" class="form-control" -->
						<!-- 								name="pc_passwordcheck"  id="pc_passwordcheck" placeholder="再次確認密碼" /> -->
						<!-- 						</div> -->
						<!-- 						<div class="input-group" style="float:left; padding-top:8px;"> -->

						<!-- 						    <i class="fa fa-check-circle" aria-hidden="true" style="color:rgb(10,200,0); font-size:18px" ></i>					     -->
						<!-- 						正確  -->
						<!-- 						</div>			     -->
						<!-- 						<div style="clear:both;"></div>							 -->


						<!--名稱 -->
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon input-sum">廠商名稱：</div>
							<input type="text" class="form-control" name="pc_name"
								id="pc_name" placeholder="請填寫完整名稱"
								value="<%=(postpartumCareVO == null) ? "" : postpartumCareVO.getPc_name()%>" />
						</div>

						<!--類型 -->
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon input-radio">廠商類型：</div>
							<div class="btn-group" data-toggle="buttons">
								<label id="pc_type1_label"
									class="btn btn-default <c:if test="${postpartumCareVO.pc_type=='月子中心'}">active focus </c:if>">
									<i class="fa fa-hospital-o" aria-hidden="true"></i><input
									type="radio" name="pc_type"  id="pc_type1" value="月子中心"
									<c:if test="${postpartumCareVO.pc_type=='月子中心'}">checked </c:if> />
									月子中心
								</label> <label id="pc_type2_label"
									class="btn btn-default <c:if test="${postpartumCareVO.pc_type=='月子餐廠商'}">active focus </c:if>">
									<i class="fa fa-cutlery" aria-hidden="true"></i><input
									type="radio" name="pc_type" id="pc_type2" value="月子餐廠商"
									<c:if test="${postpartumCareVO.pc_type=='月子餐廠商'}">checked </c:if> />
									月子餐廠商
								</label>
							</div>
						</div>


						<!-- 連絡資訊 -->
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon input-sum">連絡電話：</div>
							<input type="text" class="form-control" name="pc_phone" id="pc_phone"
								placeholder=""
								value="<%=(postpartumCareVO == null) ? "" : postpartumCareVO.getPc_phone()%>" />
						</div>
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon" style="background-color: #ddd;">電子信箱：</div>
							<input type="email" class="form-control" name="pc_email" id="pc_email"
								placeholder=""
								value="<%=(postpartumCareVO == null) ? "" : postpartumCareVO.getPc_email()%>" />
						</div>
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon" style="background-color: #ddd;">連絡地址：</div>

							<select class="form-control" name="city" id="city_select"
								style="width: 40%; height: 30px;"></select> <select
								class="form-control" name="area" id="area_select"
								style="width: 60%; height: 30px;"></select> <input type="text"
								class="form-control" name="pc_address" id="resault"
								value="<%=(postpartumCareVO == null) ? "" : postpartumCareVO.getPc_address()%>" />
							<input type="hidden" class="form-control" name="pc_address2"
								id="resault2"
								value="<%=(postpartumCareVO == null) ? "" : postpartumCareVO.getPc_address()%>" />
							<input type="hidden" id="carea" name="carea" value="${area}"></input>
							<input type="hidden" id="ccity" name="ccity" value="${city}"></input>

						</div>

						<!-- 簡介 -->
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon input-sum">廠商簡介：</div>
							<textarea class="form-control" rows="5" name="pc_summary" id="pc_summary"
								style="resize: vertical;"><%=(postpartumCareVO == null) ? "" : postpartumCareVO.getPc_summary()%></textarea>
						</div>

						<!--預約積分 -->
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon input-sum">預約積分：</div>
							<input type="number" class="form-control" name="pc_point" id="pc_point"
								value="<%=(postpartumCareVO == null) ? 0 : postpartumCareVO.getPc_point()%>" />
							<div class="input-group-addon">
								<span>如為0則代表不開放預約申請</span>
							</div>
						</div>

						<!--預約小禮-->
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon input-radio">預約小禮：</div>
							<div class="btn-group" data-toggle="buttons">
								<label id="pc_gift0_label"
									class="btn btn-default <c:if test="${postpartumCareVO.pc_gift=='N'}">active focus</c:if>">
									<i class="fa fa-times-circle-o" aria-hidden="true"></i><input
									type="radio" name="pc_gift" id="pc_gift0" value="N"
									<c:if test="${postpartumCareVO.pc_gift=='N'}">checked</c:if> />
									暫不提供
								</label> <label id="pc_gift1_label"
									class="btn btn-default <c:if test="${postpartumCareVO.pc_gift=='Y'}">active focus</c:if>">
									<i class="fa fa-gift" aria-hidden="true"></i><input
									type="radio" name="pc_gift" id="pc_gift1" value="Y"
									<c:if test="${postpartumCareVO.pc_gift=='Y'}">checked</c:if> />
									提供預約小禮
								</label>
							</div>
						</div>

						<!--預約折扣-->
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon input-radio">預約折扣：</div>
							<div class="btn-group" data-toggle="buttons">
								<label id="pc_bonus0_label"
									class="btn btn-default <c:if test="${postpartumCareVO.pc_bonus=='N'}">active focus </c:if>">
									<i class="fa fa-times-circle-o" aria-hidden="true"></i><input
									type="radio" name="pc_bonus" id="pc_bonus0" value="N"
									<c:if test="${postpartumCareVO.pc_bonus=='N'}">checked </c:if> />
									暫不提供
								</label> <label id="pc_bonus1_label"
									class="btn btn-default <c:if test="${postpartumCareVO.pc_bonus=='Y'}">active focus</c:if>">
									<i class="fa fa-usd " aria-hidden="true"></i><input
									type="radio" name="pc_bonus" id="pc_bonus1" value="Y"
									<c:if test="${postpartumCareVO.pc_bonus=='Y'}">checked</c:if> />
									提供額外折扣
								</label>
							</div>
						</div>

						<!--照片-->
						<div class="input-group"
							style="width: 95%; border: 1px solid #ccc;" id="PC_PICTURES">
							<div class="input-group-addon input-radio">廠商照片：</div>

							<!-- 							  要可以動態新增 -->
							<div class="form-group pic-group">
								<div class="input-group" style="width: 95%;">
									<span class="input-group-addon input-sum">照片說明</span> <input
										type="text" class="form-control" value="照片說明"
										name="pcp_summary"> <span class="input-group-btn">
										<span class="btn btn-warning btn-del"> <i
											class="fa fa-trash-o" aria-hidden="true"> &nbsp移除</i> <input
											type="button" class="divDel">
									</span>
									</span>
								</div>
								<div class="input-group" style="width: 95%;">
									<span class="input-group-btn"> <span
										class="btn btn-info btn-file"> 選擇照片 <input type="file"
											class="imgInp_ori" name="pcp_picture">
									</span>
									</span> <input type="text" class="form-control" readonly>
								</div>

							</div>
							<!-- 							  要可以動態新增   -->


							<label style="width: 100%; text-align: center; margin-top: 10px;"
								id="addPic"> <a class="btn btn-success" type="button"
								onclick="createImgDiv()"><i class="fa fa-plus"
									aria-hidden="true"></i></a>
							</label>
						</div>


					</div>
					<input type="hidden" name="action" value="insert">
					<div class="input-group" style="text-align: center; width: 100%;">
						<input type="submit" value="送出新增" class="btn btn-info">
					</div>
					<div class="input-group" style="text-align: right; width: 90%;">
						<input type="button" value="寶寶來幫你" class="btn btn-success" onClick="babyHelper()">
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
					<p class="login-modal-title">請修正以下錯誤</p>
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






	<script src="<%=request.getContextPath()%>/postpartum_care/js/address.js"></script>
	<script src="<%=request.getContextPath()%>/postpartum_care/js/RegisterPC.js"></script>

	<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />


</body>
</html>

