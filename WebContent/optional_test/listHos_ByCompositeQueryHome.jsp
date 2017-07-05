<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>
<%

	String hospital_ot_no = (String) request.getAttribute("hospital_ot_no");

%>

<%-- <jsp:useBean id="listPCs_ByCompositeQuery" scope="request" type="java.util.List" /> --%>
<jsp:useBean id="OTSvc" scope="page" class="com.optional_test.model.OptionalTestService" />
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

<title>自費項目查詢結果</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css">

<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script src="https://code.jquery.com/jquery.js"></script>
<!-- <link rel="stylesheet" type="text/css" href="css/PostpartumCare_index.css"> -->
<!-- <script src="http://maps.google.com/maps/api/js"></script> -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
<style type="text/css">

#search_hos{
    border: 15px solid transparent;
    padding: 5px;
    border-image:url(img/border-b-r.png) 10 stretch;
}
</style>

</head>
<body bgcolor='white'>
<jsp:include page="/optional_test/ot_select.jsp" flush="true" />	

	<div class="container">

	
	<div class="row">
				<div class="col-xs-12 col-sm-12" id="search_hos" style="margin-bottom:20px;margin-top:20px;">
						<FORM METHOD="post" ACTION="hos.do" >
				
				  <div class="col-xs-12 col-sm-4">
				      <div class="form-group" style="width: auto" >
				      <label class="control-label" for="select_pc">選擇自費項目</label>
					  <select  class="form-control selectpicker show-tick" data-style="btn-info" name="hospital_ot_item.ot_no">
					         <c:forEach var="optionalTestVO" items="${OTSvc.all}" > 
					          <option value="${optionalTestVO.ot_no}" <c:if test="${optionalTestVO.ot_no == hospital_ot_no}" > selected </c:if>>${optionalTestVO.ot_name} </option>
					         </c:forEach>  
					  </select>
					  </div>
				  </div>	
				  <div class="col-xs-12 col-sm-4">
				   <label class="control-label" for="select_pc">選擇搜尋地區</label>
							<div class="input-group" style="width: auto">

							<select class="form-control"  id="city_select" 
								style="width: 40%;"></select> 
							<select
								class="form-control " id="area_select" 
								style="width: 50%;"></select> 
							<input type="hidden"
								class="form-control" name="hospital.hos_address" id="result"
								value="" />
							<input type="hidden" id="carea"  value="${area}"></input>
							<input type="hidden" id="ccity"  value="${city}"></input>
						</div>						 
				  </div>
				  <div class="col-xs-12 col-sm-4">

				  </div>				  				  				  
				
				<div class="row" style="margin-top:10px;">
				  <div class="col-xs-12 col-sm-12"> 						
      				<input type="hidden" name="action" value="listHos_ByCompositeQuery">
      											
			  		<div class="input-group">		  				  	
							  <input type="text" class="form-control" name="hospital.hos_name" placeholder="請輸入醫院名稱關鍵字" value="${hos_name}">
							  <span class="input-group-btn">
							    <button class="btn btn-info" type="submit">立即搜尋</button>
							  </span>
					</div>	
				  </div>				  
				</div>				

						</FORM>													
						</div>
				
			<div class="col-xs-12 col-sm-12"> 
			<%if (request.getAttribute("listHos_ByCompositeQuery")!=null){%>
			       <jsp:include page="/optional_test/listHos_ByCompositeQuery.jsp" />
			<%} %>			
			</div>
			</div>
						
			
			</div>
			</div>
			

</body>
	<script src="js/addressForSearch.js"></script>
</html>
<div class="container-fluid">
<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />	
</div>
