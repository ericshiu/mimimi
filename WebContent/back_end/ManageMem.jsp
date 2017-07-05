<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.member.model.*"  %>
<%@ page import="com.firm.model.*"  %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%
	MemberVO memberVO=(MemberVO)request.getAttribute("memberVO");
	session.setAttribute("pageReq", "/back_end/ManageMem.jsp");
	FirmVO firVO=(FirmVO)request.getAttribute("firVO");

%>


<!DOCTYPE html >
<html>
<head>
<title>會員帳號管理</title>
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




<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="firSvc" scope="page" class="com.firm.model.FirmService" />

<div class="col-xs-12  col-lg-10 right" style="height:100%;font-size:20px;padding-left:20px;width:83%;margin-top:25px;">
<h2>會員權限管理：</h2>
	<div class="col-xs-12 col-lg-12 right1" style="padding-left:10px;color:#333;">
		<FORM METHOD="post" ACTION="mem.do" style="display:inline;">
	       	會員編號:
	       	<select size="1" name="mem_no" class="selectpicker show-menu-arrow" >
	         	<c:forEach var="memVO" items="${memSvc.all}" > 
	          		<option value="${memVO.mem_no}">${memVO.mem_no}
	         	</c:forEach>   
	       	</select>
	       		<button type="submit" class="btn btn-default">查詢</button>
	       		<input type="hidden" name="action" value="getOne_For_Display">
	    </FORM>
		<FORM METHOD="post" ACTION="mem.do" style="display:inline;">
	       
	   	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;會員姓名:<select size="1" name="mem_no" class="selectpicker show-menu-arrow">
	         	<c:forEach var="memVO" items="${memSvc.all}" > 
	          		<option value="${memVO.mem_no}">${memVO.mem_name}
	         	</c:forEach>   
	       	</select>
	       		<button type="submit" class="btn btn-default">查詢</button>
	       		<input type="hidden" name="action" value="getOne_For_Display">
	    </FORM>
		<FORM METHOD="post" ACTION="mem.do" style="display:inline;">
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;輸入會員編號:
	        <input type="text" name="mem_no" class="form-control" style="width:200px;display:inline;">
	        <button type="submit" class="btn btn-default">查詢</button>
	        <input type="hidden" name="action" value="getOne_For_Display">
	    </FORM>
	    <br>
	    <br>
		<%if (memberVO!=null){ %> 		
   		<div class="col-xs-12  col-lg-12">
		<jsp:include page="/back_end/listOneMem.jsp" flush="true"/>
		<%} %>
		</div>
	</div>

  
</div>



<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>

<!-- (Optional) Latest compiled and minified JavaScript translation files -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/i18n/defaults-*.min.js"></script>				
</body>
</html>