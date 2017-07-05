<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.postpartum_care.model.*" import="com.pc_picture.model.*" import="java.util.*" import="sun.misc.*"%>
<%@ page import="com.member.model.*" import="com.firm.model.*"%>
<%
	PostpartumCareService PCSvc = new PostpartumCareService();
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

	Set<PcPictureVO> pcps = PCSvc.getPCPsByPc_no(userVO.getPc_no());
	PcPictureVO pcp = null;
	if (!pcps.isEmpty()){
    	pcp = pcps.iterator().next();
	}
    pageContext.setAttribute("pcp",pcp);
    
	session.setAttribute("pageReq", "/postpartum_care/PersonalPC.jsp");//這行是在存重導路徑，所以要打本頁的網址
%>

<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>產後照護廠商資料維護專區</title>
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
<link rel="stylesheet" type="text/css" href="../css/css.css">

<link rel="stylesheet" type="text/css" href="css/RegisterPC.css">

<script src="https://code.jquery.com/jquery.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>

<!-- <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal"> -->
<!--   Launch demo modal -->
<!-- </button> -->

<jsp:include page="/front_end/frontTOP.jsp" flush="true" />
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red' style="display:none;">請修正以下錯誤:
	<ul id="errormessage">
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>
<button type="button"  data-toggle="modal" data-target="#errorModal" id="errorBtn" style="display:none;">Open Modal</button>
<div class="modal fade" id="errorModal" role="dialog" style="top:25%;">
    <div class="modal-dialog modal-sm">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header modal-header-danger" style="height:60px;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <p class="login-modal-title">錯誤訊息</p>
        </div>
        <div class="modal-body">
          <p id="errorMsg" style="font-size:16px;"></p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>

	


	<!-- personal內容開始 -->

	<hr>
	<div class="container" style="border-style:dashed;border-width:3px 3px 3px 3px;padding:0px;">

		<div class="col-xs-12  col-sm-2 left" style="text-align:center;width:20%;">
			<div class="col-xs-12 col-sm-12 left1" style="padding:10px;">
				<img id="pchead" src="<%=request.getContextPath()%>/PC_PictureTransfer?pcp_no=${pcp.pcp_no}" class="img-responsive" style="width:100%;border-radius:50%;">
			</div>
			<div class="col-xs-12 col-sm-12 left2">
				<p id="pcname" > ${userVO.pc_name} <br>歡迎您登入!
			
				</p>
			</div>
			<div class="col-xs-12 col-sm-12 left4" style="margin-top:30px;">

					<table class="table table-hover table-striped" style="border:3px dashed #FF96A7;">
						<tr><th colspan="2" style="text-align: center;">資訊總覽</th></tr>
						<tr>
							<th style="text-align: center;"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>預約</th>
							<td style="text-align: center;">
							<c:if test="${userVO.pc_point>0}">${userVO.pc_point}</c:if>
							<c:if test="${userVO.pc_point==0}"><i class="fa fa-times" aria-hidden="true" style="color:red"></i></c:if>									
							</td>
						</tr>							
						<tr>
							<th style="text-align: center;"><i class="fa fa-gift" aria-hidden="true"></i>小禮</th>
							<td style="text-align: center;">
							<c:if test="${userVO.pc_gift=='Y'}"><i class="fa fa-check" aria-hidden="true"></i></c:if>
							<c:if test="${userVO.pc_gift=='N'}"><i class="fa fa-times" aria-hidden="true" style="color:red"></i></c:if>
							</td>
						</tr>
						<tr>
							<th style="text-align: center;"><i class="fa fa-usd " aria-hidden="true"></i>折扣</th>
							<td style="text-align: center;">
							<c:if test="${userVO.pc_bonus=='Y'}"><i class="fa fa-check" aria-hidden="true"></i></c:if>
							<c:if test="${userVO.pc_bonus=='N'}"><i class="fa fa-times" aria-hidden="true" style="color:red"></i></c:if>							
							</td>
						</tr>						
						<tr><th colspan="2" style="text-align: center;">我的評價</th></tr>
						<tr>
							<th style="text-align: center;"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i>優良</th>
							<td style="text-align: center;">${userVO.pc_eva_good}</td>
						</tr>
						<tr>
							<th style="text-align: center;"><i class="fa fa-smile-o" aria-hidden="true"></i>普通</th>
							<td style="text-align: center;">${userVO.pc_eva_normal}</td>
						</tr>
						<tr>
							<th style="text-align: center;"><i class="fa fa-thumbs-o-down" aria-hidden="true"></i>差勁</th>
							<td style="text-align: center;">${userVO.pc_eva_bad}</td>
						</tr>			
					</table>
			
			</div>
			<div class="col-xs-12 col-sm-12 left3" style="margin-top:50px;margin-bottom:50px;">

				<p><i class="fa fa-gratipay" aria-hidden="true"></i>貼心小提醒：</p>
				<p>提供預約小禮及下訂折扣，可以增加會員預約填單的數量喔!!</p>

			</div>
			<!-- left3 -->
		</div>
		<!-- left -->

		<!-- 左邊選單區left結束 -->

<div class="col-xs-12  col-sm-10 right personalright" style="height:100%;width:80%;margin:0px;border-width:0px 0px 0px 1px;border-style:solid;">
			<!-- 右邊區塊開始right -->		    		 	     			


			<div class="modal-dialog" role="document">

				<div class="modal-content">

<!-- 					<div class="imgpclogin"></div> -->
					<div class="modal-header">

						<p class="login-modal-title" id="myModalLabel"
							style="text-align: center; font-weight: bold; color: #888;">產後照護廠商資料維護</p>
					</div>
<form METHOD="post" ACTION="PC.do" name="form1">
					<div class="modal-body">

				
						<!--帳號 -->
						<div class="input-group" style="width: 60%; float: left;">
							<div class="input-group-addon input-sum">廠商帳號：</div>
							<input type="text" class="form-control" 
								maxlength="20" style="width: 100%;"
								value="<%=(userVO == null) ? "xxxx" : userVO.getPc_id()%>"
								readonly disabled="disabled"/>
							<!-- 							<span class="input-group-btn"> -->
							<!-- 						    <a class="btn btn-info"  type="button"><i class="fa fa-search-plus"></i></a> -->
							<!-- 						    </span>	 -->
						</div>
						<!--密碼 -->
						<%String dataPsw=null; %>
						<%try{ %>
						<%dataPsw=new String(userVO.getPc_password()); %>
						<%final BASE64Decoder decoder = new BASE64Decoder(); %>
						<%String Psw=new String(decoder.decodeBuffer(dataPsw), "UTF-8"); %>
						<%String real=Psw.replaceAll("[a-zA-Z0-9]","*"); %>						
						
											
												<div class="input-group" style="width:60%;float:left;">
													<div class="input-group-addon input-sum" >重設密碼：</div>
													<input type="text" class="form-control" name="pc_password" id="pc_password"
														placeholder="請輸入6~20個密碼"
														value="<%=(userVO == null) ? "123" : Psw%>" />
												</div>
						<%}catch(Exception e){ %>
						<% e.getStackTrace(); %>
						
						<%} %>		    

<!-- 											<div class="input-group" style="width: 60%; float: left;"> -->
<!-- 												<div class="input-group-addon input-sum">密碼確認：</div> -->
<!-- 												<input type="text" class="form-control" name="pc_id" id="pc_id" -->
<!-- 													maxlength="20" placeholder="請再次輸入密碼" style="width: 100%;" -->
<!-- 													value="" -->
<!-- 													onchange=getInfo() /> -->
<!-- 																			<span class="input-group-btn"> -->
<!-- 																		    <a class="btn btn-info"  type="button"><i class="fa fa-search-plus"></i></a> -->
<!-- 																		    </span>	 -->
<!-- 											</div> -->
<!-- 											<div class="input-group" style="float: left; padding-top: 8px;" -->
<!-- 												id="idCheck-div"> -->
					
<!-- 												<i id="idCheck-icon"></i> -->
					
<!-- 											</div> -->
<!-- 											<div style="clear: both;"></div>					 -->


						<!--名稱 -->
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon input-sum">廠商名稱：</div>
							<input type="text" class="form-control" name="pc_name"
								id="pc_name" placeholder="請填寫完整名稱"
								value="<%=(userVO == null) ? "123月子中心" : userVO.getPc_name()%>" />
						</div>

						<!--類型 -->
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon input-radio">廠商類型：</div>
							<div class="btn-group" data-toggle="buttons">
								<label
									class="btn btn-default <c:if test="${userVO.pc_type=='月子中心'}">active focus </c:if>">
									<i class="fa fa-hospital-o" aria-hidden="true"></i><input
									type="radio" name="pc_type" value="月子中心"
									<c:if test="${userVO.pc_type=='月子中心'}">checked </c:if> />
									月子中心
								</label> <label
									class="btn btn-default <c:if test="${userVO.pc_type=='月子餐廠商'}">active focus </c:if>">
									<i class="fa fa-cutlery" aria-hidden="true"></i><input
									type="radio" name="pc_type" value="月子餐廠商"
									<c:if test="${userVO.pc_type=='月子餐廠商'}">checked </c:if> />
									月子餐廠商
								</label>
							</div>
						</div>


						<!-- 連絡資訊 -->
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon input-sum">連絡電話：</div>
							<input type="text" class="form-control" name="pc_phone"
								placeholder=""
								value="<%=(userVO == null) ? "03-3335422" : userVO.getPc_phone()%>" />
						</div>
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon" style="background-color: #ddd;">電子信箱：</div>
							<input type="email" class="form-control" name="pc_email"
								placeholder=""
								value="<%=(userVO == null) ? "koykogina@yahoo.com.tw" : userVO.getPc_email()%>" />
						</div>
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon" style="background-color: #ddd;">連絡地址：</div>

							<select class="form-control" name="city" id="city_select"
								style="width: 40%; height: 30px;"></select> <select
								class="form-control" name="area" id="area_select"
								style="width: 60%; height: 30px;"></select> <input type="text"
								class="form-control" name="pc_address" id="resault"
								value="<%=(userVO == null) ? "320桃園市中壢區中央路xxxx號" : userVO.getPc_address()%>" />
							<input type="hidden" class="form-control" name="pc_address2"
								id="resault2"
								value="<%=(userVO == null) ? "320桃園市中壢區" : userVO.getPc_address()%>" />
							<input type="hidden" id="carea" name="carea" value="${area}"></input>
							<input type="hidden" id="ccity" name="ccity" value="${city}"></input>

						</div>

						<!-- 簡介 -->
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon input-sum">廠商簡介：</div>
							<textarea class="form-control" rows="5" name="pc_summary"
								style="resize: vertical;"><%=(userVO == null) ? "1 2 3 4 5 6 7 8 9 10" : userVO.getPc_summary()%> 
						    </textarea>
						</div>

						<!--預約積分 -->
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon input-sum">預約積分：</div>
							<input type="number" class="form-control" name="pc_point"
								value="<%=(userVO == null) ? 0 : userVO.getPc_point()%>" />
							<div class="input-group-addon">
								<span>如為0則代表不開放預約申請</span>
							</div>
						</div>

						<!--預約小禮-->
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon input-radio">預約小禮：</div>
							<div class="btn-group" data-toggle="buttons">
								<label
									class="btn btn-default <c:if test="${userVO.pc_gift=='N'}">active focus</c:if>">
									<i class="fa fa-times-circle-o" aria-hidden="true"></i><input
									type="radio" name="pc_gift" value="N"
									<c:if test="${userVO.pc_gift=='N'}">checked</c:if> />
									暫不提供
								</label> <label
									class="btn btn-default <c:if test="${userVO.pc_gift=='Y'}">active focus</c:if>">
									<i class="fa fa-gift" aria-hidden="true"></i><input
									type="radio" name="pc_gift" value="Y"
									<c:if test="${userVO.pc_gift=='Y'}">checked</c:if> />
									提供預約小禮
								</label>
							</div>
						</div>

						<!--預約折扣-->
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon input-radio">預約折扣：</div>
							<div class="btn-group" data-toggle="buttons">
								<label
									class="btn btn-default <c:if test="${userVO.pc_bonus=='N'}">active focus </c:if>">
									<i class="fa fa-times-circle-o" aria-hidden="true"></i><input
									type="radio" name="pc_bonus" value="N"
									<c:if test="${userVO.pc_bonus=='N'}">checked </c:if> />
									暫不提供
								</label> <label
									class="btn btn-default <c:if test="${userVO.pc_bonus=='Y'}">active focus</c:if>">
									<i class="fa fa-usd " aria-hidden="true"></i><input
									type="radio" name="pc_bonus" value="Y"
									<c:if test="${userVO.pc_bonus=='Y'}">checked</c:if> />
									提供額外折扣
								</label>
							</div>
						</div>


						</div>

					<input type="hidden" name="pc_no" value="${userVO.pc_no}">
					<input type="hidden" name="pc_id" value="${userVO.pc_id}">
					<input type="hidden" name="pc_authority" value="${userVO.pc_authority}">
					<input type="hidden" name="pc_eva_good" value="${userVO.pc_eva_good}">
					<input type="hidden" name="pc_eva_normal" value="${userVO.pc_eva_normal}">
					<input type="hidden" name="pc_eva_bad" value="${userVO.pc_eva_bad}">										
					<input type="hidden" name="action" value="update">
					<div class="input-group" style="text-align: center; width: 100%;">
						<input type="submit" value="送出修改" class="btn btn-info">
					</div>
			</form>
					
					
					<FORM METHOD="post" ACTION="PC.do" name="form1" enctype="multipart/form-data">
									<input type="hidden" name="pc_no" value="${userVO.pc_no}">	
									<input type="hidden" name="action" value="listPCPs_ByPc_no">
									<div class="input-group" style="text-align:right; width:95%;">
									<sapn>編輯專頁照片由此去→ &nbsp;</span>
										<input type="submit" value="編輯照片" class="btn btn-warning">
									</div>
					</FORM>	

					</div>


				</div>




	
		</div>
			
	  	</div>


			<!-- 右邊區塊結束right -->




	<!-- personal內容結束 -->

	<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
	<script src="js/ModityPC.js"></script>
	<script src="js/address.js"></script>
</body>
</html>
<script>
function $id(id){
	  return document.getElementById(id);
}

window.onload = function (){
    
	$(city_select).empty();
	$(city_select).append("<option value=''>桃園市</option>");
	$(area_select).empty();
	$(area_select).append("<option value=''>中壢區</option>");
 
     
}

</script>
