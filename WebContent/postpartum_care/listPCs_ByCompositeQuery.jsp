<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
// 	String order = (String) request.getAttribute("order");
// 	String area = (String) request.getAttribute("area");
// 	String city = (String) request.getAttribute("city");

%>

<jsp:useBean id="listPCs_ByCompositeQuery" scope="request" type="java.util.List" />
<jsp:useBean id="PCSvc" scope="page" class="com.postpartum_care.model.PostpartumCareService" />
<html>
<head>
<!-- <meta charset="utf-8"> -->
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<!-- <meta name="viewport" -->
<!-- 	content="width=device-width, initial-scale=1.0, shrink-to-fit=no"> -->

<!-- <title>產後照護廠商查詢結果</title> -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"> -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"> -->
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css"> -->
<!-- <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script> -->
<!-- <script src="https://code.jquery.com/jquery.js"></script> -->
<!-- <link rel="stylesheet" type="text/css" href="css/PostpartumCare_index.css"> -->
<!-- <script src="http://maps.google.com/maps/api/js"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script> -->
</head>
<body bgcolor='white'>


<!-- <div class="container"> -->
<!-- 	<div class="row"> -->
<!-- 		<div class="col-xs-12 col-sm-offset-1 col-sm-10"> -->

<!-- 		   <img src="img/pc_search.jpg" class="img-responsive" style="border-radius: 20px; margin:20px 0px;">  -->

<!-- 		</div>		 -->
<!-- 	</div> -->
	
	
<!-- 				<div class="col-xs-12 col-sm-offset-1 col-sm-10" id="search_pc" style="margin-bottom:20px;"> -->
<!-- 						<FORM METHOD="post" ACTION="PC.do" >	 -->
<!-- 				<div class="row"> -->
<!-- 				  <div class="col-xs-12 col-sm-4"> -->
<!-- 				      <div class="form-group" style="width: auto" > -->
<!-- 				      <label class="control-label" for="select_pc">選擇廠商類型</label> -->
<!-- 					  <select  class="form-control selectpicker show-tick" data-style="btn-info" name="pc_type" id="select_pc" > -->
<!-- 					      <option value="">全部</option> -->
<!-- 					      <option value="月子中心">月子中心</option> -->
<!-- 					      <option value="月子餐廠商">月子餐廠商</option> -->
<!-- 					  </select> -->
<!-- 					  </div> -->
<!-- 				  </div>	 -->
<!-- 				  <div class="col-xs-12 col-sm-4"> -->
<!-- 				   <label class="control-label" for="select_pc">選擇搜尋地區</label> -->
<!-- 							<div class="input-group" style="width: auto"> -->

<!-- 							<select class="form-control"  id="city_select" name="city" -->
<!-- 								style="width: 40%;"></select>  -->
<!-- 							<select -->
<!-- 								class="form-control " id="area_select" name="area" -->
<!-- 								style="width: 50%;"></select>  -->
<!-- 							<input type="hidden" -->
<!-- 								class="form-control" name="pc_address" id="result" -->
<!-- 								value="" /> -->
<%-- 							<input type="hidden" id="carea"  value="${area}"></input> --%>
<%-- 							<input type="hidden" id="ccity"  value="${city}"></input> --%>
<!-- 						</div>						  -->
<!-- 				  </div> -->
<!-- 				  <div class="col-xs-12 col-sm-4"> -->
<!-- 				      <div class="form-group" style="width: auto" > -->
<!-- 				      <label class="control-label" for="select_pc">選擇排序</label> -->
<!-- 					  <select  class="form-control selectpicker show-tick" data-style="btn-info" name="order" id="select_pc" > -->
<!-- 					      <option value="pc_no">最新</option> -->
<!-- 					      <option value="pc_eva_good">正評</option> -->
<!-- 					      <option value="pc_point">預約點數</option> -->
<!-- 					  </select> -->
<!-- 					  </div> -->
<!-- 				  </div>				  				  				   -->
<!-- 				</div> -->
<!-- 				<div class="row" style="margin-top:10px;"> -->
<!-- 				  <div class="col-xs-12 col-sm-12"> 						 -->
<!--       				<input type="hidden" name="action" value="listPCs_ByCompositeQuery">							 -->
<!-- 			  		<div class="input-group">		  				  	 -->
<!-- 							  <input type="text" class="form-control" name="pc_name" placeholder="請輸入廠商名稱關鍵字" > -->
<!-- 							  <span class="input-group-btn"> -->
<!-- 							    <button class="btn btn-info" type="submit">立即搜尋</button> -->
<!-- 							  </span> -->
<!-- 					</div>	 -->
<!-- 				  </div>				   -->
<!-- 				</div>				 -->

<!-- 						</FORM>													 -->
<!-- 						</div>		 -->
	
			
	<div class="row">
		<div class="col-xs-12 col-sm-offset-1  col-sm-10">
		<div class="panel panel-info">
		
	
			<div class="panel-heading">
				<h3 class="panel-title"><%@ include file="pages/page1_ByCompositeQuery.file" %>	</h3>
			</div>
		
		<table class="table table-hover table-striped" align="center">
			<thead>
			<tr>
				<th>廠商名稱</th>
				<th>廠商類型</th>
				<th>廠商地址</th>
				<th>廠商正評</th>
				<th>預約點數</th>				
				<th>廠商連結</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="postpartumCareVO" items="${listPCs_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr valign='middle' ${(postpartumCareVO.pc_no==param.pc_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
					<td>${postpartumCareVO.pc_name}</td>
					<td>${postpartumCareVO.pc_type}</td>
					<td>${postpartumCareVO.pc_address}</td>
					<td>${postpartumCareVO.pc_eva_good}</td>
					<c:if test="${postpartumCareVO.pc_point !='0'}">
						<td>${postpartumCareVO.pc_point}</td>
					</c:if>
					<c:if test="${postpartumCareVO.pc_point =='0'}">
						<td>不開放預約</td>
					</c:if>				
					<td>
					  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postpartum_care/PC.do">
						<button type="submit" class="btn btn-info">
    						<i class="fa fa-search"></i>去看看
						</button>
					    <input type="hidden" name="pc_no" value="${postpartumCareVO.pc_no}">
					    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
					    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
					    <input type="hidden" name="action"value="getOne_For_Display"></FORM>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		
		<%@ include file="pages/page2_ByCompositeQuery.file" %>	
		</div>
		</div>		
	</div>	
<!-- </div> -->


	<script src="js/addressForSearch.js"></script>
</body>
</html>
