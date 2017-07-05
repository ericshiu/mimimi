
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.inspection.model.*" import="com.member.model.*" import="java.util.*" import="sun.misc.*"%>
<%
	MemberVO userVO = (MemberVO) session.getAttribute("userVO");
	session.setAttribute("pageReq", "/member/PersonalMem.jsp");//這行是在存重導路徑，所以要打本頁的網址
%>



<!DOCTYPE html>
<html lang="">
	<head>
	<title>產檢預約查詢</title>
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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/title.css">
  		<link rel='stylesheet prefetch' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css'>
		<link rel='stylesheet prefetch' href='http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css'>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/title.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/titlehover.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/timecss.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/table.css">
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/inputtype.css">
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
	<jsp:include page="/front_end/frontTOP.jsp" flush="true" />	

		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12">
				<div class="widget center">
					  <div class="text center">
					    <h1 class="">產檢預約查詢</h1>
					    <p>Go ahead, resize me.</p>
					  </div>
					  <div class="blur">
					  </div>
					</div>	
				</div>
				<div class="col-xs-12 col-sm-3">
				 <jsp:include page="../listgrop.jsp" flush="true"/>
				</div>
				<div class="col-xs-12 col-sm-9">
				<h3>注意</h3>
				 <div class="title">
	
					  <div class="title-word">「生得過、雞酒香；生不過、四塊板」。</div>
					  <div class="title-word">這是古代婦女懷孕生產的宿命寫照，時至今日由於醫藥保健發達，</div>
					  <div class="title-word">加上出生率 減少，對於孕婦生產，我們要達到生得少但要生得好的目標。</div>
					  <div class="title-word">因此做好完整的產前檢查就很重要了。</div>
					  <div class="title-word">所謂「完整」的產檢：必須依照標準的「時間」及「內容」來檢查，</div>
					  <div class="title-word">才能達到預期的效果，這一點是非常的重要。</div>
					  <div class="title-word">醫師藉由孕婦在孕期的每個階段所進行的詳細檢查，</div>
					  <div class="title-word">可以有效地找出危害母體及胎兒健康的危險因子，</div>
					  <div class="title-word">並且達到及時診治的效果。</div>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12">
				
				  	<div class="tbl-header">
				    <table cellpadding="0" cellspacing="0" border="0">
				      <thead>
				        <tr>
				          <th>檢查醫院名稱</th>
				          <th>預約診別</th>
				          <th>預約編號</th>
				          <th>預約時間</th>
				          <th></th>
				        </tr>
				      </thead>
				    </table>
				  	</div>
				  	<div class="tbl-content">
				    <table cellpadding="0" cellspacing="0" border="0">
				      <tbody>
				      <jsp:useBean id="InsSvc" scope="page" class="com.inspection.model.InspectionService" />
				        <c:forEach var="inspectionVO" items="${InsSvc.all}">
				        <c:if test="${userVO.mem_no==inspectionVO.mem_no}">
				        <tr>
				          <td>${inspectionVO.ins_hospital}</td>
				          <td>${inspectionVO.ins_outoatuent}</td>
				          <td>${inspectionVO.ins_resetvation_no}</td>
				          <td>${inspectionVO.ins_date}</td>
				          <td>
				          <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/ins.do">
						     
									 <input class="btn btn-primary btn-lg outline" type="submit" value="修改" style="padding: 6px 12px;background-color: #fff;">
								     <input type="hidden" name="ins_no" value="${inspectionVO.ins_no}">
								     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
								     <input type="hidden" name="action"	value="getOne_For_Update">
								   </FORM>
				          </td>
				        </tr>
				       </c:if>
				       </c:forEach>
				      </tbody>
				    </table>
				  	</div>


					<div class="col-xs-12 col-sm-4">
					</div>


					<div class="container .button col-xs-12 col-sm-4">
						<a href="ins_add.jsp"><button id="button">新增紀錄</button></a>
					</div>


					<div class="col-xs-12 col-sm-4">
					</div>
				</div>	
					
<!-- tavle -->

				




					
				</div>
			</div>
		</div>



		<div style="height:200px;" ></div>
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

