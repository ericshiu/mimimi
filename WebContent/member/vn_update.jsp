<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.vaccine_notes.model.*"%>
<%@ page import="com.member.model.*"%>

<%
VaccineNotesVO vaccineNotesVO = (VaccineNotesVO) request.getAttribute("vaccineNotesVO");
%>

<!DOCTYPE html>
<html lang="">
	<head>
	<title>修改寶寶施打疫苗紀錄</title>
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
	<body>
	<jsp:include page="/front_end/frontTOP.jsp" flush="true" />		
			<div class="modal-dialog" role="document" style="width: 600px;">
			<div class="modal-content" >
				<div class="imgmemberlogin" src="" style="background:  url(img/vn_img.jpg)no-repeat center top;"></div>
				<div class="modal-header">

					<h4 class="modal-title" id="myModalLabel"
						style="text-align: center; font-weight: bold;color:#888;">修改寶寶施打疫苗紀錄</h4>
				</div>
				<div class="modal-body" >
				<FORM METHOD="post" ACTION="vn.do" name="form1">
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
<!-- 						選擇施打的疫苗 -->
						<jsp:useBean id="VCSvc" scope="page" class="com.vaccine_course.model.VaccineCourseService" />
						<div class="input-group" style="width: 95%; margin: 10px;">
							<div class="input-group-addon" style="width: 85px;">疫苗：</div>
							<select size="1" name="vc_no" style="height: 30px;">
								<c:forEach var="vaccineCourseVO" items="${VCSvc.all}">
								
									<option value="${vaccineCourseVO.vc_no}" ${(vaccineCourseVO.vc_no==vaccineCourseVO.vc_no)?'selected':'' } >${vaccineCourseVO.vc_name}
								
								</c:forEach>
							</select>

						</div>
						
						
<!-- 						選擇施打日期 -->
						<div class="input-group" style="width: 95%; margin: 10px;">
						<div class="input-group-addon" style="width: 85px;">注射日期：</div>


						<td bgcolor="#CCCCFF">
							<input type="date" id="bookdate" name="vn_date">
						</td>

						</div>
					
					
						
					</div>


					


				<input type="hidden" name="vn_no" value="<%=vaccineNotesVO.getVn_no()%>">
				<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">	
				<input type="hidden" name="action" value="update">
				<div class="input-group" style="text-align: center; width: 100%;">

					<button type="submit" value="送出新增">送出修改</button>
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

