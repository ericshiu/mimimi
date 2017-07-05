<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.inspection.model.*" import="com.member.model.*" import="java.util.*" import="sun.misc.*"%>

<%
InspectionVO inspectionVO = (InspectionVO) request.getAttribute("inspectionVO");
%>
<%
	MemberVO userVO = (MemberVO) session.getAttribute("userVO");
	session.setAttribute("pageReq", "/member/PersonalMem.jsp");//這行是在存重導路徑，所以要打本頁的網址
%>
<!DOCTYPE html>
<html lang="">
	<head>
	<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
	</c:if>
	<title>修改產檢日期</title>

		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>Title Page</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!-- 引用icon -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
		<!-- 引用Css -->
		 <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
  		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/addcss.css">
  		<link rel='stylesheet prefetch' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css'>
		<link rel='stylesheet prefetch' href='http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css'>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">

		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/timecss.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/table.css">
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
		
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
	<jsp:include page="/front_end/frontTOP.jsp" flush="true" />	

		<div class="container">
			<div class="modal-dialog" role="document" style="width: 600px;">
			<div class="modal-content" >
				<div class="imgmemberlogin" src=""></div>
				<div class="modal-header">

					<h4 class="modal-title" id="myModalLabel"
						style="text-align: center; font-weight: bold;color:#888;">修改產檢日期</h4>
				</div>
				<div class="modal-body" >
				
				<FORM METHOD="post" ACTION="ins.do" name="form1">
				
					<div class="form-group" >
						<div class="input-group" style="width: 95%; margin: 10px;"">
							<div class="input-group-addon" style="width: 85px;">檢查醫院：</div>
							<input type="text" name="ins_hospital" class="form-control" style="width:100%;" value="${inspectionVO.ins_hospital}">

						</div>
						<div class="input-group" style="width: 95%; margin: 10px;"">
							<div class="input-group-addon" style="width: 85px;">預約診別：</div>
							<input type="text" name="ins_outoatuent" class="form-control" style="width:100%;" value="${inspectionVO.ins_outoatuent}">

						</div>
						<div class="input-group" style="width: 95%; margin: 10px;"">
							<div class="input-group-addon" style="width: 85px;">預約號碼：</div>
							<input type="text" name="ins_resetvation_no" class="form-control" style="width:100%;" value="${inspectionVO.ins_resetvation_no}">

						</div>

						<div class="input-group" style="width: 95%; margin: 10px;">
						<div class="input-group-addon" style="width: 85px;" >預約時間：</div>


							<td bgcolor="#CCCCFF">
							<input type="datetime-local" id="time"  value="${inspectionVO.ins_date}">
							</td>

						</div>
				
						
					</div>


					


				<input id="1" name="ins_date" style="display:none;">
				<input type="hidden" name="ins_no" value="${inspectionVO.ins_no}" /> 
				<input type="hidden" name="mem_no" value="${userVO.mem_no}" /> 
				<input type="hidden" name="action" value="update">
				<div class="input-group" style="text-align: center; width: 100%;">

					<button type="button"  onclick="myFunction()">送出修改</button>
					<button type="button" onclick=showvalue() >神奇小按鈕</button>&nbsp;&nbsp;
				</div>
				</FORM>

			</div>
		</div>
</div>
</div>
<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
<script>
			function send(){
			      document.form1.submit();
			}
			
			function myFunction() {
			    var x = document.getElementById("time");
			    var currentVal = x.value;
			    
			    
			    
			    
			      
			
			   	
					   	document.getElementById("1").value = currentVal.substr(0,10)+" "+currentVal.substr(11,15)+":00";
					   	
				return	send();
			    
			}
			
			
			
</script>
	
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>
		<script
  		src="https://code.jquery.com/jquery-3.2.1.js"
  		integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
  		crossorigin="anonymous"></script>
  		<script type="text/javascript" src='<%=request.getContextPath() %>/member/js/js.js'></script>
  		<script type="text/javascript" src='<%=request.getContextPath() %>/member/js/table.js'></script>
	</body>
</html>
<script>

function $id(id){
	return document.getElementById(id);
}

function $name(name){
	return document.getElementsByName(name);
}

function showvalue(){


	$name('ins_hospital')[0].value="晨芳婦產科診所";
	$name('ins_outoatuent')[0].value="產檢";
	$name('ins_resetvation_no')[0].value="3020";

	
}


</script>
