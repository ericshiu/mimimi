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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Title Page</title>
<link rel="stylesheet" type="text/css" href="../css/css.css">

<style type="text/css">
#fgps {
	margin: 10px;
}
</style>


</head>
<body>

	<%-- <c:if test="${not empty errorMsgs}"> --%>
	<!-- 			<font color='red'>請修正以下錯誤: -->
	<!-- 				<ul> -->
	<%-- 					<c:forEach var="message" items="${errorMsgs}"> --%>
	<%-- 						<li>${message}</li> --%>
	<%-- 					</c:forEach> --%>
	<!-- 				</ul> -->
	<!-- 			</font> -->
	<%-- 		</c:if>	 --%>



	<!-- 頂部 <-->
	</-->
	<div class="container-fluid top-links"
		style="margin-top: 1em; margin-bottom: 1em;">
		<div id="B">
			<div class="col-xs-12 col-sm-4">
				<div>
					<div class="home">
						<div class="col-xs-12 col-sm-4">
							<a href="<%=request.getContextPath()%>/front_end/frontINDEX.jsp">Home</a>
						</div>
						<div class="col-xs-12 col-sm-4">
							<a href="#">My Account</a>
						</div>
						<div class="col-xs-12 col-sm-4">
							<a href="#">Checkout</a>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-4">
				<div class="home">
					<div class="col-xs-12 col-sm-4 col-sm-offset-2">
						<a href="#"><i class="glyphicon glyphicon-heart-empty"></i>
							Wishlist:0</a>
					</div>
					<div class="col-xs-12 col-sm-4">
						<a href="#"><i class="fa fa-cart-plus"></i> CART:0</a>
					</div>
					<div class="col-xs-12 col-sm-2"></div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-4">
				<div class="home">
					<div class="col-xs-12 col-sm-4">
						<c:if test="${userVO != null}">
							<c:if test="${userType.equals('Mem')}">
								<a href="../member/PersonalMem.jsp" style="font-size: 10px;">Hi!
								${userVO.mem_nike}</a>
							</c:if>
							<c:if test="${userType.equals('Fir')}">
								<a href="../firm/PersonalFir.jsp" style="font-size: 10px;">Hi!
								${userVO.fir_name}</a>
							</c:if>	
							<c:if test="${userType.equals('PC')}">
								<a href="../postpartum_care/PersonalPC.jsp" style="font-size: 10px;">Hi!
								${userVO.pc_name}</a>
							</c:if>														
						</c:if>
	
			

					</DIV>

					<div class="col-xs-12 col-offset-sm-4 col-sm-4">
					
					<c:if test="${userVO == null}">					
						<a href="#" data-toggle="modal" data-target="#ModalMemberLogin"
							data-whatever=""> Log In<i class="fa fa-sign-in"></i></a>
					</c:if>
					
					<c:if test="${userVO != null}">					
						<a href="#" data-toggle="modal" data-target="#ModalLogout"
							data-whatever=""> Log out<i class="fa fa-sign-out"></i></a>
					
					</c:if>

					</div>



				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row" style="margin-bottom: 1em;">
			<div class="col-xs-12 col-sm-4">
				<div class="top-phone">
					<i class="fa fa-whatsapp"></i> TEL:0800-000-123
					<p>Lorem ipsum dolor sit amet/p>
				</div>

			</div>
			<div class="col-xs-12 col-sm-4">
				<img class="img-responsive" src="../img/logo.png"
					style="height: 10em; display: inline;">
			</div>
			<div class="col-xs-12 col-sm-4">
				<div class="col-xs-12 col-sm-4"></div>
				<div class="col-xs-12 col-sm-4"></div>
				<div class="col-xs-12 col-sm-4">
					<input type="text" class="form-control" placeholder="請輸入關鍵字">
					<span class="input-group-btn">
						<button class="btn" type="button" style="background-color: #fff;">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div>
			</div>
		</div>
	</div>
	<nav class="navbar navbar-default aa" role="navigation" id="nav_bar"
		style="text-align: center;">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">選單切換</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>

			</div>

			<!-- 手機隱藏選單區 -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">



				<c:if test="${userVO == null || userType.equals('Mem')}">
				<ul class="nav navbar-nav">
					<li><a href="寶典/BOOK.html">MOM BABY寶典</a></li>
					<li><a href="會員專區/indexeason.html">Member會員專區</a></li>
					<li><a href="先交差的商場首頁/shop.html">Shop商城</a></li>
					<li><a href="../postpartum_care/PostpartumCare_Index.jsp">PostpartumCare產後護理</a></li>
					<li><a href="討論區.html">Forum討論區</a></li>
				</ul>
				</c:if>
				
				<c:if test="${userType.equals('PC')}">
				<ul class="nav navbar-nav">
					<li><a
						href="<%=request.getContextPath()%>/postpartum_care/PersonalPC.jsp">廠商資訊維護</a></li>
					<li><a
						href="<%=request.getContextPath()%>/postpartum_care/PC.do?action=listApps_ByPc_no&pc_no=${userVO.pc_no}">會員預約管理</a></li>
					<li><a
						href="<%=request.getContextPath()%>/postpartum_care/PersonalPC_app.jsp">查看我的評價</a></li>
					<li><a
						href="<%=request.getContextPath()%>/postpartum_care/PersonalPC_app.jsp">查看檢舉</a></li>								
				</ul>
				</c:if>


			</div>
		</div>
	</NAV>
	<!-- 頂部結束 -->

	<!-- 會員登入畫面 -->


	<div class="modal fade" id="ModalMemberLogin" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">


		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">

				<div class="modal-header modal-header-success">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<p class="modal-title" id="exampleModalLabel">會員登入畫面</p>
				</div>
				<form METHOD="post" ACTION="mem.do">
					<div class="modal-body">

						<div class="form-group">
							<label for="exampleInputAccount1">帳號：</label> <input type="text"
								class="form-control" id="memLoginId" name="memLoginId"
								placeholder="Account" value="123123">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">密碼：</label> <input
								type="password" class="form-control" id="memLoginPsw"
								name="memLoginPsw" placeholder="Password" value="123123">
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default">
							<a href="#" data-toggle="modal" data-target="#ModalRegister"
								data-whatever="" data-dismiss="modal">註冊會員</a>
						</button>
						<button type="button" class="btn btn-default">忘記密碼</button>
						<input type="submit" name="memLogin" value="登入"> <input
							type="hidden" name="action" value="memLogin">
				</form>
			</div>
		</div>




		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header modal-header-info">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<p class="modal-title" id="exampleModalLabel">商場廠商登入</p>
				</div>
				<form METHOD="post" ACTION="fir_PC_Login.do">
				<div class="modal-body">
					
						<div class="form-group">
							<label for="exampleInputAccount1">帳號：</label> <input type="text"
								class="form-control" id="fir_PC_LoginId" name="fir_PC_LoginId"
								placeholder="Account" value="123456">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">密碼：</label> <input
								type="password" class="form-control" id="fir_PC_LoginPsw"
								name="fir_PC_LoginPsw" placeholder="Password" value="123456">
						</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default">
						<a href="#" data-toggle="modal" data-target="#ModalRegister" data-whatever="" data-dismiss="modal">註冊會員</a>
					</button>
					<input type="hidden" name="action" value="fir_PC_Login">
					<button type="button" class="btn btn-default">忘記密碼</button>
					<button type="submit" class="btn btn-info">登入</button>
					
						
				</div>
				</form>
			</div>
		</div>
	











	</div>
	</div>

	<div class="modal fade" id="ModalLogout" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header modal-header-info"
					class="modal fade bs-example-modal-sm">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<p class="modal-title" id="exampleModalLabel">登出確認畫面</p>
				</div>
				<div class="modal-body">確定要登出嗎？!</div>
				<div class="modal-footer">
	
					<c:if test="${userVO !=null }">
						<c:if test="${userType.equals('Mem')}">
							<form METHOD="post" ACTION="mem.do">
							<input type="hidden" name="action" value="memLogout">
							</form>
						</c:if>
						<c:if test="${userType.equals('Fir')}">
							<form METHOD="post" ACTION="fir_PC_Login.do">
							<input type="hidden" name="action" value="fir_PC_Logout">
							</form>
						</c:if>						
						<c:if test="${userType.equals('PC')}">
							<form METHOD="post" ACTION="fir_PC_Login.do">
							<input type="hidden" name="action" value="fir_PC_Logout">
							</form>
						</c:if>	
					</c:if>
				

						<button type="button" class="btn btn-secondary" data-dismiss="modal">先不要</button>
						<button type="submit" class="btn btn-secondary" name="memLogout">登出</button>


						</form>
				</div>
			</div>
		</div>
	</div>




	<!--  登出畫面結束 -->

	<!-- 會員註冊專區開始 -->
	<div class="modal fade" id="ModalRegister" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" id="modal-dialog-login">
			<div class="modal-content">

				<div class="modal-body" id="modal-body-login">


					<div class="container text-center">
						<h2>會員註冊專區</h2>
						<br>
						<div class="row">
							<div class="col-lg-4">
								<p id="p1" class="text-center">
									<strong>一般會員</strong>
								</p>
								<br> <a href="#demo" data-toggle="collapse">
									<div class="loginmember"></div>
								</a>
								<div id="demo" class="collapse">
									<p>1.充實孕期、產後資訊</p>
									<p>2.瀏覽討論區、解除疑問</p>
									<p>3.瀏覽商品、建立口袋名單</p>
									<button type="button" class="btn btn-success">
										<a
											href="<%=request.getContextPath()%>/front_end/RegisterMem.jsp">註冊</a>
									</button>
								</div>
							</div>


							<div class="col-lg-4">
								<p id="p1" class="text-center">
									<strong>商場廠商</strong>
								</p>
								<br> <a href="#demo2" data-toggle="collapse">
									<div class="loginfirm"></div>
								</a>
								<div id="demo2" class="collapse">
									<p>1.新平台、新客源</p>
									<p>2.新的商品廣告方式</p>
									<p>3.提高廠商知名度</p>
									<p>4.試用系統推廣商品</p>
									<button type="button" class="btn btn-success">
										<a
											href="<%=request.getContextPath()%>/front_end/RegisterFir.jsp">註冊</a>
									</button>
								</div>
							</div>




							<div class="col-lg-4">
								<p id="p1" class="text-center">
									<strong>產後護理廠商</strong>
								</p>
								<br> <a href="#demo3" data-toggle="collapse">
									<div class="loginpc"></div>
								</a>
								<div id="demo3" class="collapse">
									<p>1.結合我的地圖提高曝光率</p>
									<p>2.預約、試吃一站搞定</p>
									<p>3.評價系統增加廠商親合度</p>
									<p>4.釋出BONUS增加下訂率</p>
									<button type="button" class="btn btn-success">
										<a
											href="<%=request.getContextPath()%>/front_end/RegisterPC.jsp">註冊</a>
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	</div>



	</div>
	<!-- 會員註冊專區結束 -->

	
	<script type="text/javascript" src='../js/js.js'></script>
</body>
</html>