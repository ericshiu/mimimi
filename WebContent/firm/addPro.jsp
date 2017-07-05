<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	session.setAttribute("pageReq", "/firm/addPro.jsp");
	ProductVO proVO = (ProductVO) request.getAttribute("proVO");	
%>
<jsp:useBean id="categorySvc" scope="page" class="com.product_category.model.ProductCategoryService" />
<html>
<head>
<title>新增商品資料</title></head>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/firm/css/RegisterPC.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script src="https://code.jquery.com/jquery.js"></script>
<style>

.imgmemberlogin{
				height: 350px;
				background:#fcc url('../img/productadd.jpg')no-repeat center top;
				background-size:cover  ;
			}

</style>

<body bgcolor='white'>

<jsp:include page="../front_end/frontTOP.jsp" flush="true"/>

<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>


<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/product/pro.do" name="form1" enctype="multipart/form-data">
	<div class="container">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
			<div class="imgmemberlogin"></div>
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel" style="text-align: center; 
				font-weight: bold; color: #888;">新增商品</h4>
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
					<div class="input-group-addon input-sum">廠商編號:</div>
						<div class="form-control">${userVO.fir_no}</div>
						<input type="hidden" name="fir_no" value="${userVO.fir_no}">				
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
<!--          照片        -->

				<div class="input-group" style="width: 95%; border: 1px solid #ccc;" id="PC_PICTURES">
					<div class="input-group-addon input-radio">廠商照片：</div>
					<div class="form-group pic-group">
						
				<div class="input-group" style="width:95%;">
					<span class="input-group-btn">
					<span class="btn btn-info btn-file"> 選擇照片 <input type="file" class="imgInp_ori" name="prp_picture">
					</span>
					</span> <input type="text" class="form-control" readonly>
					<span class="input-group-btn" style="height:100%;">
					<span class="btn btn-warning btn-del" style="height:100%;">
							<i class="fa fa-trash-o" aria-hidden="true"> &nbsp移除</i> 
							<input type="button" class="divDel">
					</span>
					</span>
				</div>
			</div>
			
			<label style="width: 100%; text-align: center; margin-top: 10px;" id="addPic">
				<a class="btn btn-success" type="button" onclick="createImgDiv()">
					<i class="fa fa-plus" aria-hidden="true"></i>
				</a>
			</label>
			</div>
			</div>
				<input type="hidden" name="action" value="insert">
					<div class="input-group" style="text-align: center; width: 100%;">
						<input type="submit" value="送出新增" class="btn btn-info">
			</div>
			</div>
			</div>
		</div>
	</div>
</FORM>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/firm/js/addPro.js"></script>
<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>

</html>
