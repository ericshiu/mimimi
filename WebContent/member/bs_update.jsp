

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.baby_sleep.model.* "%>
<%@ page import="com.member.model.*" import="java.util.*" import="sun.misc.*"%>
<%
BabySleepVO babySleepVO = (BabySleepVO) request.getAttribute("babySleepVO");
%>
<%
	MemberVO userVO = (MemberVO) session.getAttribute("userVO");
	session.setAttribute("pageReq", "/member/PersonalMem.jsp");//這行是在存重導路徑，所以要打本頁的網址
%>
<!DOCTYPE html>
<html lang="">
	<head>
	<title>睡眠紀錄</title>

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
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/inputtype.css">
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
						style="text-align: center; font-weight: bold;color:#888;">新增睡眠紀錄</h4>
				</div>
				<div class="modal-body" >
			<FORM METHOD="post" ACTION="bs.do" name="form1" >
					<div class="form-group" >
<!-- 					選寶寶 -->
						<jsp:useBean id="babyDataSvc" scope="page" class="com.baby_data.model.BabyDataService" />
						<div class="input-group" style=" width: 95%; margin: 10px;">
							<div class="input-group-addon" style="width: 85px;">寶寶：</div>
							<select size="1" name="bd_no" style="height: 30px;">
								<c:forEach var="babyDataVO" items="${babyDataSvc.all}">
									<c:if test="${userVO.mem_no==babyDataVO.mem_no}">
										<option value="${babyDataVO.bd_no}" ${(babySleepVO.bd_no==babyDataVO.bd_no)?'selected':'' } >${babyDataVO.bd_name}
									</c:if>
								</c:forEach>
							</select>

						</div>
						
						<%java.sql.Timestamp date_SQL = new java.sql.Timestamp(System.currentTimeMillis());%>
						<div class="input-group" style="width: 95%; margin: 10px;">
						<div class="input-group-addon" style="width: 85px;">開始時間：</div>
						<td bgcolor="#CCCCFF">
							<input id="star"  type="datetime-local" value="<%= (babySleepVO==null)? date_SQL : babySleepVO.getBs_start()%>">
						</td>

						</div>
						
						<div class="input-group" style="width: 95%; margin: 10px;">
						<div class="input-group-addon" style="width: 85px;">結束時間：</div>
						<td bgcolor="#CCCCFF">
							<input id="end"  type="datetime-local" value="<%= (babySleepVO==null)? date_SQL : babySleepVO.getBs_end()%>">
						</td>

						</div>
				
						
					</div>


					


					
				<input
					type="hidden" name="action" value="update">
				<div class="input-group" style="text-align: center; width: 100%;">

					<button type="button" value="送出新增" onclick="myFunction()">送出新增</button>
				</div>
				
		
			<input type="hidden" name="bs_no" value="<%=babySleepVO.getBs_no()%>">
			<input id="1" name="bs_start" style="display:none;">
			<input id="2" name="bs_end" style="display:none;">
			<input id="3" name="bs_time" style="display:none;">
			<input type="hidden" name="action" value="insert">
			
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
			    var x = document.getElementById("star");
			    var currentVal = x.value;
			    
			    var a = document.getElementById("end");
			    var currentVala = a.value;
			    
			    
			      
			
					var Date_A = new Date(currentVal.substr(0,4),currentVal.substr(5,2),currentVal.substr(8,2),currentVal.substr(11,2),currentVal.substr(14,2));  
					var Date_B = new Date(currentVala.substr(0,4),currentVala.substr(5,2),currentVala.substr(8,2),currentVala.substr(11,2),currentVala.substr(14,2));
			
			
					var Date_C = new Date(Date_B - Date_A);
					
					   	document.getElementById("3").value =Math.floor(Date_C.getTime() / 3600000) + "小時 " + Date_C.getUTCMinutes() + "分 " + Date_C.getUTCSeconds() + "秒";
					   	document.getElementById("1").value = currentVal.substr(0,10)+" "+currentVal.substr(11,15)+":00";
					   	document.getElementById("2").value = currentVala.substr(0,10)+" "+currentVala.substr(11,15)+":00";
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

