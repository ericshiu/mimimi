<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.mom_health.controller.*" import="com.member.model.*" import="java.util.*" import="sun.misc.*"%>
<%@ page import="com.mom_health.model.*"%>

<%
	MemberVO userVO = (MemberVO) session.getAttribute("userVO");
	session.setAttribute("pageReq", "/member/PersonalMem.jsp");//這行是在存重導路徑，所以要打本頁的網址
%>
<%
MomHealthVO mom_healthVO = (MomHealthVO) request.getAttribute("mom_healthVO");
%>
<!DOCTYPE html>
<html lang="">
	<head>
	<title>媽媽健康紀錄新增</title>
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

  		<link rel='stylesheet prefetch' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css'>
		<link rel='stylesheet prefetch' href='http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css'>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/addcss.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/timecss.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/table.css">
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
		
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<jsp:include page="/front_end/frontTOP.jsp" flush="true" />	
	<body>
	<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
	</c:if>
		


			<div class="modal-dialog" role="document" style="width: 600px;">
			<div class="modal-content" >
				<div class="imgmemberlogin" src=""></div>
				<div class="modal-header">

					<h4 class="modal-title" id="myModalLabel"
						style="text-align: center; font-weight: bold;color:#888;">媽媽健康紀錄新增</h4>
				</div>
				<div class="modal-body" >
					<FORM METHOD="post" ACTION="mh.do" name="form1">
					<div class="form-group" >
						<div class="input-group" style=" width: 95%; margin: 10px;">
							<div class="input-group-addon" style="width: 85px;" >體重：</div>
							<input type="text" name="mh_weight" class="form-control" style="width:100%;" value="${momHealthVO.mh_weight}">

						</div>
						<div class="input-group" style="width: 95%; margin: 10px;">
							<div class="input-group-addon" style="width: 85px;" >收縮壓：</div>
							<input type="text" name="mh_ststolic" class="form-control" style="width:100%;" value="${momHealthVO.mh_ststolic}">

						</div>
						<div class="input-group" style="width: 95%; margin: 10px;">
							<div class="input-group-addon" style="width: 85px;" >舒張壓：</div>
							<input type="text" name="mh_diastolic" class="form-control" style="width:100%;" value="${momHealthVO.mh_diastolic}">

						</div>
						<div class="input-group" style="width: 95%; margin: 10px;">
							<div class="input-group-addon" style="width: 85px;" >心跳：</div>
							<input type="text" name="mh_heartbeat" class="form-control" style="width:100%;" value="${momHealthVO.mh_heartbeat}">

						</div>
						
						<div class="input-group" style="width: 95%; margin: 10px;">
								<div class="input-group-addon input-radio" >尿蛋白：</div>
									<div class="btn-group" data-toggle="buttons" value="${momHealthVO.mh_protein}">
									  <label class="btn btn-default" >
									    <input type="radio" name="mh_protein" autocomplete="off" value="正常"> 正常
									  </label>
									  <label class="btn btn-default">
									    <input type="radio" name="mh_protein" autocomplete="off" value="不正常"> 不正常
									  </label>								
									</div>	
	
						</div>
						<div class="input-group" style="width: 95%; margin: 10px;">
								<div class="input-group-addon" style="width: 85px;">寶寶心跳：</div>
								<input type="text" name="mh_baby_heartbeat" class="form-control" style="width:100%;" value="${momHealthVO.mh_baby_heartbeat}">
	
							</div>
						
							
						</div>
						<div class="input-group" style="width: 95%; margin: 10px;">
						<div class="input-group-addon" style="width: 85px;" >紀錄日期：</div>
	
						<td bgcolor="#CCCCFF">
						<input type="date" id="bookdate" name="mh_date" value="${momHealthVO.mh_date}">
						</td>

						</div>


					


				<input type="hidden" name="mh_no" value="${momHealthVO.mh_no}">
				<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">		
				<input type="hidden" name="mem_no" value="${userVO.mem_no}" />
				<input type="hidden" name="action" value="update"/>
				<div class="input-group" style="text-align: center; width: 100%;">

					<button type="submit">送出修改</button>
					<button type="button" onclick=showvalue() >神奇小按鈕</button>&nbsp;&nbsp;
				</div>
				</FORM>

			</div>
		</div>
		</div>
<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />

	
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


	$name('mh_weight')[0].value="50";
	$name('mh_ststolic')[0].value="82";
	$name('mh_diastolic')[0].value="122";
	$name('mh_heartbeat')[0].value="82";
	$name('mh_baby_heartbeat')[0].value="82";
	$id('bookdate').value="2017-06-09";
	

	
}


</script>

