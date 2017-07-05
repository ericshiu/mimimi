<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_picture.model.*" import="java.util.*"
	import="com.cart.controller.*"%>
<%@ page import="java.util.*" import="com.cart.model.*"%>
<%
	Vector<CartVO> buylist = (Vector<CartVO>) session.getAttribute("shoppingcart");
	pageContext.setAttribute("buylist", buylist);
%>
<!DOCTYPE html>
<html lang="">
<head>
<style>
.div {
	font-family:ch;
}
</style>
<meta charset="utf-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/front_end/css/top.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/Cart.css">
<script src="<%=request.getContextPath()%>/js/js.js"></script>

</head>
<body>

	<!-- 頂部 <-->

	<div class="container-fluid top-links"
		style="margin-top: 1em; margin-bottom: 1em;">
		<div id="B">
			<div class="col-xs-12 col-sm-6"></div>

			<div class="col-xs-12 col-sm-6" style="text-align: right;">
				<div class="home">
					<div class="col-xs-12 col-sm-2">
						<c:if test="${userVO != null}">
							<c:if test="${userType.equals('Mem')}">
								<a href="<%=request.getContextPath()%>/member/MyPersonal.jsp" style="font-size: 10px;">Hi!
									${userVO.mem_nike}</a>
							</c:if>
							<c:if test="${userType.equals('Fir')}">
								<a href="<%=request.getContextPath()%>/firm/PersonalFir.jsp" style="font-size: 10px;">Hi!
									${userVO.fir_name}</a>
							</c:if>
							<c:if test="${userType.equals('PC')}">
								<a href="<%=request.getContextPath()%>/postpartum_care/PersonalPC.jsp"
									style="font-size: 10px;">Hi! ${userVO.pc_name}</a>
							</c:if>
						</c:if>

					</div>

					<div class="col-xs-12 col-sm-2">
						<a href="<%=request.getContextPath()%>/front_end/frontINDEX.jsp">Home</a>
					</div>
					<c:if test="${!userType.equals('PC') && !userType.equals('Fir') }">
						<script src="https://code.jquery.com/jquery.js"></script>
						<div class="col-xs-12 col-sm-2" style="padding:0px;">
							<div class="shop-cart-area">
								<div class="top-cart-wrapper">
									<div class="block-cart">

										<span class="title-cart" style="cursor:pointer;color:#5a89bc"><i class="fa fa-cart-plus"></i>
           									my cart
           								</span>

										<c:if test="${!empty buylist}">
										<div class="top-cart-content">
										
<%-- 											${buylist.size()==0?"style='display:none'":""}> --%>
											<c:forEach var="order" items="${buylist}">
												<ol class="mini-products-list">
													<li>
														<div class="product-image">
															<jsp:useBean id="picCartSvc" scope="page" class="com.product_picture.model.ProductPictureService" />
															<c:forEach var="picVO" items="${picCartSvc.getOnePicByProNo(order.pro_no)}">	
																<img src="<%=request.getContextPath() %>/ProductPhoto?prp_no=${picVO.prp_no}" width="50px"/>	
															</c:forEach>
														</div>
														<div class="product-details">
															<p class="cartproduct-name">${order.pro_name}</p>
															<strong class="qty">QTY : ${order.pro_quantity}</strong>
															<span class="sig-price">${order.sub_total}</span>
														</div>
													</li>
												</ol>
											</c:forEach>

											<div"cart-actions">
												<form name="checkoutForm"
													action="<%=request.getContextPath()%>/cart/car.do"
													method="POST">
													<input type="hidden" name="action" value="GOCART">
													<button type="submit" class="btn btn-info">前往購物車</button>
												</form>
											</div>
										</div>
										</c:if>
									</div>
								</div>
							</div>
						</div>
					</c:if>
					<c:if test="${empty userVO}">
						<div class="col-xs-12 col-sm-2">
							<a href="#" data-toggle="modal" data-target="#ModalRegister"
								data-whatever="">Register</a>
						</div>
					</c:if>
					<div class="col-xs-12 col-sm-2">
						<c:if test="${userVO == null}">
							<a href="#" data-toggle="modal" data-target="#ModalMemberLogin"
								data-whatever="">會員Log In</a>
						</c:if>
						
						
					</div>
					<div class="col-xs-12 col-sm-2">
						<c:if test="${userVO == null}">
							<a href="#" data-toggle="modal" data-target="#ModalFirmLogin"
								data-whatever="">廠商Log In</a>
						</c:if>
					</div>
					<div class="col-xs-12 col-sm-2">
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
				<div class="top-phone"
					style="font-size: 52px; position: relative; top: -3px; margin-right: 3px; color: #5f94bf;">
					<i class="fa fa-whatsapp"></i> TEL:0800-000-123
					<p>bucktooth168@gmail.com</p>
				</div>

			</div>
			<div class="col-xs-12 col-sm-4" style="margin-top: 0em;">
				<img class="img-responsive"
					src="<%=request.getContextPath()%>/img/logo.png"
					style="height: 10em; display: inline;">
			</div>
			<div class="col-xs-12 col-sm-4">
				<div class="col-xs-12 col-sm-4"></div>
				<div class="col-xs-12 col-sm-4"></div>
				<div class="col-xs-12 col-sm-4">
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


				<c:if test="${userVO==null || userType.equals('Mem')}">
					<ul class="nav navbar-nav" style="width:1200px;">
						<li><a style="color: #5f94bf;"
						href="<%=request.getContextPath()%>/book.jsp">MOM
								BABY寶典</a></li>
						<li><a style="
    color: #5f94bf;"
							href="<%=request.getContextPath()%>/member/MyPersonal.jsp">Member會員專區</a></li>
						<li><a style="
    color: #5f94bf;"
							href="<%=request.getContextPath()%>/market/listAllPro.jsp">Shop商城</a></li>
						<li><a style="
    color: #5f94bf;"
							href="<%=request.getContextPath()%>/postpartum_care/PostpartumCare_Index.jsp">PostpartumCare產後護理</a></li>
						<li><a style="
    color: #5f94bf;"
							href="<%=request.getContextPath()%>/forum/ForumINDEX.jsp">Forum討論區</a></li>
					</ul>
				</c:if>

				<c:if test="${userType.equals('PC')}">
					<ul class="nav navbar-nav">
						<li><a
							href="<%=request.getContextPath()%>/postpartum_care/PersonalPC.jsp">廠商資訊維護</a></li>
						<li><a
							href="<%=request.getContextPath()%>/postpartum_care/PC.do?action=listApps_ByPc_no&pc_no=${userVO.pc_no}">會員預約管理</a></li>
						<li><a
							href="<%=request.getContextPath()%>/postpartum_care/PersonalPC_eva.jsp">查看我的評價</a></li>
						<li><a
							href="<%=request.getContextPath()%>/postpartum_care/PersonalPC_report.jsp">查看檢舉</a></li>
					</ul>
				</c:if>

				<c:if test="${userType.equals('Fir')}">
					<ul class="nav navbar-nav">
						<li><a
							href="<%=request.getContextPath()%>/firm/PersonalFir.jsp">廠商資訊維護</a></li>
						<li><a href="<%=request.getContextPath()%>/firm/firmOrd.jsp">訂單管理</a></li>
						<li><a
							href="<%=request.getContextPath()%>/firm/listAllPro.jsp">商品管理</a></li>
						<li><a
							href="<%=request.getContextPath()%>/advertise/AdvertiseApply.jsp">廣告申請</a></li>
					</ul>
				</c:if>

			</div>
		</div>
	</NAV>
	<!-- 頂部結束 -->

	<!-- 會員登入畫面 -->

	<!-- 	會員登入 -->
	<div class="modal fade" id="ModalMemberLogin" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
		style="top: 25%;">

		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">

				<div class="modal-header login-modal-header-pink">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<p class="login-modal-title" id="exampleModalLabel">會員登入畫面</p>
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
						<button type="button" class="btn btn-default">
							<a
								href="<%=request.getContextPath()%>/front_end/ForgetPswMem.jsp">忘記密碼</a>
						</button>
						<input type="submit" name="memLogin" value="登入"
							class="btn btn-pink"> <input type="hidden" name="action"
							value="memLogin">
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- 	會員登入結束 -->
	<!-- 	廠商登入 -->
	<div class="modal fade" id="ModalFirmLogin" tabindex="1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" style="top: 25%;">

		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header login-modal-header-info">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<p class="login-modal-title" id="exampleModalLabel">商場廠商登入</p>
				</div>
				<div class="modal-body">
					<form METHOD="post"
						action="<%=request.getContextPath()%>/front_end/fir_PC_Login.do">
						<div class="form-group">
							<label for="exampleInputAccount1">帳號：</label> <input type="text"
								class="form-control" id="fir_PC_LoginId" name="fir_PC_LoginId"
								placeholder="Account" value="456456">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">密碼：</label> <input
								type="password" class="form-control" id="fir_PC_LoginPsw"
								name="fir_PC_LoginPsw" placeholder="Password" value="456456">
						</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default">
						<a href="#" data-toggle="modal" data-target="#ModalRegister"
							data-whatever="" data-dismiss="modal">註冊會員</a>
					</button>
					<button type="button" class="btn btn-default">
						<a href="<%=request.getContextPath()%>/front_end/ForgetPswFir.jsp">忘記密碼</a>
					</button>
					<button type="submit" class="btn btn-info">登入</button>
					<input type="hidden" name="action" value="fir_PC_Login">
				</div>

				</form>
			</div>
		</div>
	</div>
	<!-- 	廠商登入結束 -->

	<div class="modal fade" id="ModalLogout" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true"
		style="top: 25%;">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header login-modal-header-info"
					class="modal fade bs-example-modal-sm">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<p class="login-modal-title" id="exampleModalLabel">登出確認畫面</p>
				</div>
				<div class="modal-body">確定要登出嗎？!</div>
				<div class="modal-footer">
					<c:if test="${userVO !=null }">
						<c:if test="${userType.equals('Mem')}">
							<form METHOD="post" ACTION="mem.do">
								<input type="hidden" name="action" value="memLogout">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">先不要</button>
								<button type="submit" name="memLogout" class="btn btn-secondary">登出</button>
							</form>
						</c:if>
						<c:if test="${userType.equals('Fir')}">
							<form METHOD="post" ACTION="fir_PC_Login.do">
								<input type="hidden" name="action" value="fir_PC_Logout">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">先不要</button>
								<button type="submit" name="fir_PC_Logout"
									class="btn btn-secondary">登出</button>
							</form>
						</c:if>
						<c:if test="${userType.equals('PC')}">
							<form METHOD="post" ACTION="fir_PC_Login.do">
								<input type="hidden" name="action" value="fir_PC_Logout">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">先不要</button>
								<button type="submit" name="fir_PC_Logout"
									class="btn btn-secondary">登出</button>
							</form>
						</c:if>
					</c:if>

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
											href="<%=request.getContextPath()%>/postpartum_care/RegisterPC.jsp">註冊</a>
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 會員註冊專區結束 -->

</body>
</head>
</html>