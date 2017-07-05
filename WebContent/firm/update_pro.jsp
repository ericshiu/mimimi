<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*,com.firm.model.*,com.member.model.*,com.postpartum_care.model.*"%>


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
	FirmVO userVO = (FirmVO) pageContext.getAttribute("userVO");
	session.setAttribute("pageReq", "/firm/listAllpro.jsp");
	ProductVO proVO = (ProductVO) request.getAttribute("proVO");	
%>

<jsp:useBean id="categorySvc" scope="page" class="com.product_category.model.ProductCategoryService" />
<html>
<head>
<title>修改商品資料</title></head>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/RegisterPC.css">
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script src="https://code.jquery.com/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">

<body bgcolor='white'>
<jsp:include page="/front_end/frontTOP.jsp" flush="true"/>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/product/pro.do" name="form" >
<table border="0">
	<div class="container">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
			<div class="imgpclogin"></div>
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel" style="text-align: center; 
				font-weight: bold; color: #888;">修改商品</h4>
			</div>
			<div class="modal-body">
			<div class="input-group" style="width: 95%;">
					<div class="input-group-addon input-sum">商品類別:</div>
						<select class="form-control" name="prc_no">
         					<c:forEach var="prcVO" items="${categorySvc.all}" > 
          						<option value="${prcVO.prc_no}">${prcVO.prc_main}
         					</c:forEach>   
       					</select>
			</div>
			<div class="input-group" style="width: 95%;">
				<div class="input-group-addon input-sum">商品名稱:</div>
					<input type="text" class="form-control" name="pro_name" id="pro_name" placeholder="請填寫完整名稱"
					value="<%=(proVO==null)? "娃娃車" : proVO.getPro_name()%>" />
				</div>
				
				<div class="input-group" style="width: 95%;">
					<div class="input-group-addon input-sum">商品介紹:</div>
						<textarea class="form-control" rows="5" name="pro_desc" style="resize: vertical;"><%=(proVO==null)? "" : proVO.getPro_desc()%></textarea>
				</div>

				<div class="input-group" style="width: 95%;">
					<div class="input-group-addon input-sum">商品價錢:</div>
						<input type="text" class="form-control" name="pro_price" id="pro_price" placeholder="請填寫正確金額"
							value="<%=(proVO==null)? "99999" : proVO.getPro_price()%>" />
				</div>
	
				<div class="input-group" style="width: 95%;">
					<div class="input-group-addon input-sum">媽媽適用商品起始週:</div>
						<input type="text" class="form-control" name="pro_age_ms" id="pro_age_ms" placeholder="請填寫完整時間"
							value="<%=(proVO==null)? "0" : proVO.getPro_age_ms()%>" />
				</div>
	
				<div class="input-group" style="width: 95%;">
					<div class="input-group-addon input-sum">媽媽適用商品結束週:</div>
						<input type="text" class="form-control" name="pro_age_me" id="pro_age_me" placeholder="請填寫完整時間"
							value="<%=(proVO==null)? "0" : proVO.getPro_age_me()%>" />
				</div>
	
				<div class="input-group" style="width: 95%;">
					<div class="input-group-addon input-sum">寶寶適用商品起始月:</div>
						<input type="text" class="form-control" name="pro_age_cs" id="pro_age_cs" placeholder="請填寫完整時間"
							value="<%= (proVO==null)? "12" : proVO.getPro_age_cs()%>" />
				</div>
	
				<div class="input-group" style="width: 95%;">
					<div class="input-group-addon input-sum">寶寶適用商品結束月:</div>
						<input type="text" class="form-control" name="pro_age_ce" id="pro_age_ce" placeholder="請填寫完整時間"
							value="<%= (proVO==null)? "24" : proVO.getPro_age_ce()%>" />
				</div>
				
				<input type="hidden" name="action" value="update">
					<div class="input-group" style="text-align: center; width: 100%;">
					<input type="hidden" name="pro_no" value="<%=proVO.getPro_no()%>">
					<input type="hidden" name="prc_no" value="<%=proVO.getPrc_no()%>">
					<input type="hidden" name="fir_no" value="<%=proVO.getFir_no()%>">
					<input type="submit" value="送出修改" class="btn btn-info">
			</div>
			</div>
			</div>
		</div>
	</div>
	
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="js/RegisterPC.js"></script>
<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>
</html>