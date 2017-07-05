<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.firm.model.*"  %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%
	FirmVO firVO=(FirmVO)request.getAttribute("firVO");
	session.setAttribute("pageReq", "/back_end/ManageFir.jsp");
%>


<!DOCTYPE html >
<html>
<head>
<title>廠商帳號管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
</head>
<style>

body{
				margin:0px;
				padding:0px;

				background:#fff url("img/backbaby1.jpg") center center fixed no-repeat;
				background-size: cover;　

			}     
</style>
<body>
<div class="col-xs-12  col-lg-2">		
<jsp:include page="/back_end/ManagerSelect.jsp" flush="true"/>
</div>


<jsp:useBean id="firSvc" scope="page" class="com.firm.model.FirmService" />

<div class="col-xs-12  col-lg-10 right" style="height:100%;font-size:20px;width:83%;margin-top:25px;">
<h2>廠商權限管理：</h2>
	<div class="col-xs-12 col-lg-12 right1" style="padding-left:10px;color:#333;">
		<FORM METHOD="post" ACTION="fir.do" style="display:inline;">
	       	會員編號:
	       	<select size="1" name="fir_no" class="selectpicker show-menu-arrow" >
	         	<c:forEach var="firVO" items="${firSvc.allFir}" > 
	          		<option value="${firVO.fir_no}">${firVO.fir_no}
	         	</c:forEach>   
	       	</select>
	       		<button type="submit" class="btn btn-default">查詢</button>
	       		<input type="hidden" name="action" value="getOne_For_Display">
	    </FORM>
		<FORM METHOD="post" ACTION="fir.do" style="display:inline;">
	       
	   	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;會員姓名:
	   		<select size="1" name="fir_no" class="selectpicker show-menu-arrow">
	         	<c:forEach var="firVO" items="${firSvc.allFir}" > 
	          		<option value="${firVO.fir_no}">${firVO.fir_name}
	         	</c:forEach>   
	       	</select>
	       		<button type="submit" class="btn btn-default">查詢</button>
	       		<input type="hidden" name="action" value="getOne_For_Display">
	    </FORM>
		<FORM METHOD="post" ACTION="fir.do" style="display:inline;">
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;輸入會員編號:
	        <input type="text" name="fir_no" class="form-control" style="width:200px;display:inline;">
	        <button type="submit" class="btn btn-default">查詢</button>
	        <input type="hidden" name="action" value="getOne_For_Display">
	    </FORM>			  
   </div>
   		<br>
   		<br>
   		<%if (firVO!=null){ %> 		
   		<div class="col-xs-12  col-lg-12">
		<jsp:include page="/back_end/listOneFir.jsp" flush="true"/>
		<%} %>
		</div>
   	
</div>


<!-- 		下拉式選單的樣式 -->
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>

<!-- (Optional) Latest compiled and minified JavaScript translation files -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/i18n/defaults-*.min.js"></script>	
<!-- 		下拉式選單的樣子 -->
		
</body>
</html>