<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.postpartum_care.model.*" import="com.pc_application.model.*"%>
<%@ page import="com.pc_picture.model.*" import="com.member.model.*"  import="com.firm.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
	String order = (String) request.getAttribute("order");
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
	MemberVO userVO = (MemberVO) pageContext.getAttribute("userVO"); 

%>

<jsp:useBean id="listPCs_ByCompositeQuery" scope="request" type="java.util.List" />
<jsp:useBean id="PCSvc" scope="page" class="com.postpartum_care.model.PostpartumCareService" />
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

<title>產後照護廠商查詢結果</title>
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet"  href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/css.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">


<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script src="https://code.jquery.com/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/postpartum_care/css/PostpartumCare_index.css">
<!-- <script src="http://maps.google.com/maps/api/js"></script> -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
</head>
<body bgcolor='white'>
<jsp:include page="/front_end/frontTOP.jsp" flush="true" />

<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-offset-1 col-sm-10">

		   <img src="img/pc_search.jpg" class="img-responsive" style="border-radius: 20px; margin:20px 0px;"> 

		</div>		
	</div>
	
	
				<div class="col-xs-12 col-sm-offset-1 col-sm-10" id="search_pc" style="margin-bottom:20px;">
						<FORM METHOD="post" ACTION="PC.do" >	
				<div class="row">
				  <div class="col-xs-12 col-sm-4">
				      <div class="form-group" style="width: 95%;" >
				      <label class="control-label" for="select_pc">選擇廠商類型</label>
					  <select  class="form-control selectpicker show-tick" data-style="btn-info" name="pc_type" id="select_pc" >
					      <option value="">全部</option>
					      <option value="月子中心" <c:if test="${pc_type == '月子中心'}" > selected </c:if> >月子中心</option>
					      <option value="月子餐廠商" <c:if test="${pc_type == '月子餐廠商'}" > selected </c:if> >月子餐廠商</option>
					  </select>
					  </div>
				  </div>	
				  <div class="col-xs-12 col-sm-4">
				   <label class="control-label" for="select_pc">選擇搜尋地區</label>
							<div class="input-group" style="width: 100%;">

							<select class="form-control"  id="city_select" 
								style="width: 45%;height:100%;"></select> 
							<select
								class="form-control " id="area_select" 
								style="width: 55%;height:100%;"></select> 
							<input type="hidden"
								class="form-control" name="pc_address" id="result"
								value="" />
							<input type="hidden" id="carea"  value="${area}"></input>
							<input type="hidden" id="ccity"  value="${city}"></input>
						</div>						 
				  </div>
				  <div class="col-xs-12 col-sm-4">
				      <div class="form-group" style="width: 95%;" >
				      <label class="control-label" for="select_pc">選擇排序</label>
					  <select  class="form-control selectpicker show-tick" data-style="btn-info" name="order" id="select_pc" >
					      <option value="pc_no" <c:if test="${order == 'pc_no'}" > selected </c:if> > 最新</option>
					      <option value="pc_eva_good" <c:if test="${order == 'pc_eva_good'}" > selected </c:if> >正評</option>
					      <option value="pc_point" <c:if test="${order == 'pc_point'}" > selected </c:if> >預約積分</option>
					  </select>
					  </div>
				  </div>				  				  				  
				</div>
				<div class="row" style="margin-top:10px;">
				  <div class="col-xs-12 col-sm-12"> 						
      				<input type="hidden" name="action" value="listPCs_ByCompositeQuery">							
			  		<div class="input-group" style="width:98% ;height:auto;">		  				  	
							  <input type="text" class="form-control" style="height:auto;" name="pc_name" placeholder="請輸入廠商名稱關鍵字" value="${pc_name}">
							  <span class="input-group-btn">
							    <button class="btn btn-info" type="submit">立即搜尋</button>
							  </span>
					</div>	
				  </div>				  
				</div>				

						</FORM>													
						</div>		
	<jsp:include page="/postpartum_care/listPCs_ByCompositeQuery.jsp" flush="true"/>
			
<!-- 	<div class="row"> -->
<!-- 		<div class="col-xs-12 col-sm-offset-1  col-sm-10"> -->
<!-- 		<div class="panel panel-info"> -->
		
	
<!-- 			<div class="panel-heading"> -->
<%-- 				<h3 class="panel-title"><%@ include file="page1_ByCompositeQuery.file" %>	</h3> --%>
<!-- 			</div> -->
		
<!-- 		<table class="table table-hover table-striped" align="center"> -->
<!-- 			<thead> -->
<!-- 			<tr> -->
<!-- 				<th>廠商名稱</th> -->
<!-- 				<th>廠商類型</th> -->
<!-- 				<th>廠商地址</th> -->
<!-- 				<th>預約點數</th>				 -->
<!-- 				<th>廠商連結</th> -->
<!-- 			</tr> -->
<!-- 			</thead> -->
<!-- 			<tbody> -->
<%-- 			<c:forEach var="postpartumCareVO" items="${listPCs_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>
<%-- 				<tr valign='middle' ${(postpartumCareVO.pc_no==param.pc_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已--> --%>
<%-- 					<td>${postpartumCareVO.pc_name}</td> --%>
<%-- 					<td>${postpartumCareVO.pc_type}</td> --%>
<%-- 					<td>${postpartumCareVO.pc_address}</td> --%>
<%-- 					<c:if test="${postpartumCareVO.pc_point !='0'}"> --%>
<%-- 						<td>${postpartumCareVO.pc_point}</td> --%>
<%-- 					</c:if> --%>
<%-- 					<c:if test="${postpartumCareVO.pc_point =='0'}"> --%>
<!-- 						<td>不開放預約</td> -->
<%-- 					</c:if>				 --%>
<!-- 					<td> -->
<%-- 					  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postpartum_care/PC.do"> --%>
<!-- 						<button type="submit" class="btn btn-info"> -->
<!--     						<i class="fa fa-search"></i>去看看 -->
<!-- 						</button> -->
<%-- 					    <input type="hidden" name="pc_no" value="${postpartumCareVO.pc_no}"> --%>
<%-- 					    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<%-- 					    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller--> --%>
<!-- 					    <input type="hidden" name="action"value="getOne_For_Display"></FORM> -->
<!-- 					</td> -->
<!-- 				</tr> -->
<%-- 			</c:forEach> --%>
<!-- 			</tbody> -->
<!-- 		</table> -->
		
<%-- 		<%@ include file="page2_ByCompositeQuery.file" %>	 --%>
<!-- 		</div> -->
<!-- 		</div>		 -->
<!-- 	</div>	 -->
</div>

	<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
	<script src="<%=request.getContextPath()%>/postpartum_care/js/addressForSearch.js"></script>
</body>
</html>
