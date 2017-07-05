<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.vaccine_course.model.*"%>

<%
VaccineCourseService VCSvc = new VaccineCourseService();
    List<VaccineCourseVO> list = VCSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html lang="">
	<head>
	<title>疫苗時程表
	</title>
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
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/title.css">
  		<link rel='stylesheet prefetch' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css'>
		<link rel='stylesheet prefetch' href='http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css'>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/timeline.css.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/titlehover.css">




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
			<div class="row">
				<div class="col-xs-12 col-sm-12">
				 <div class="widget center">
					  <div class="text center">
					    <h1 class="">疫苗時程表</h1>
					    <p>Go ahead, resize me.</p>
					  </div>
					  <div class="blur">
					  </div>
					</div>
				</div>
				<div class="col-xs-12 col-sm-3">
				 <jsp:include page="listgrop.jsp" flush="true"/>
				</div>
				<div class="col-xs-12 col-sm-9">
				<h3>注意</h3>
				 <div class="title">
				

 


				 
					<div class="title-word">全面接種，守護寶寶健康</div>
					 <div class="title-word">疾病的預防重於治療，儘管在醫療發達的現代，</div>
					 <div class="title-word">大多數疾病都已研發出相對應的治療藥物，</div>
					 <div class="title-word">然而，脆弱的幼兒一旦感染疾病，</div>
					 <div class="title-word">是否能在第一時間內發覺並及時送醫接受適當的治療乃是一大問題！</div>
					 <div class="title-word">因此，施打疫苗以杜絕病毒、細菌的感染，對寶寶而言，就顯得格外重要！</div>
					 <div class="title-word">所謂「疫苗接種」，是指將病菌的整體或部分接種到人體，</div>
					 <div class="title-word">利用免疫的原理，讓體內的免疫系統對該病菌進行辨識，</div>
					 <div class="title-word">並製造出足以對抗的抗體，進而使接受疫苗者對該病菌所引起的疾病有較強的抵抗能力。</div> 
					  </div>
				</div>
				
				<!-- 側選選單結束 -->

				<div class="col-xs-12 col-sm-12">


					<div class="page-header">
					  <h1>疫苗時程表</h1>
					</div>
					<div style="display:inline-block;width:100%;overflow-y:auto;">
					<ul class="timeline timeline-horizontal">
					<%int i=0; %>
					<c:forEach var="vaccineCourseVO" items="${list}" >
					<%i++; %>
						<li class="timeline-item">
							<div class="timeline-badge primary"><i class="glyphicon glyphicon-check"></i></div>
							<div class="timeline-panel">
								<div class="timeline-heading">
									<a href="#pp<%=i %>"><h4  class="timeline-title">${vaccineCourseVO.vc_name} - ${vaccineCourseVO.vc_sort}</h4></a>
									<p><small class="text-muted" style="font-size: 150%;"><i class="glyphicon glyphicon-time"></i>寶寶 ${vaccineCourseVO.vc_age} 個月</small></p>
								</div>
								<div class="timeline-body">
									<p>${vaccineCourseVO.vc_introduction}</p>
								</div>
							</div>
						</li>
					</c:forEach>	
						
					</ul>
				</div>


			</div>
		</div>
					<div class="row">
				<div class="col-md-12">
					<div class="page-header">
					  <h1>Timeline</h1>
					</div>
					<ul class="timeline">
					<%int a=0; %>
					<c:forEach var="vaccineCourseVO" items="${list}" >
					<%a++; %>
						<li class="timeline-item">
							<div class="timeline-badge"><i class="glyphicon glyphicon-off"></i></div>
							<div class="timeline-panel">
								<div class="timeline-heading">
									<a href=""><h4 id ="pp<%=a %>" class="timeline-title">${vaccineCourseVO.vc_name} - ${vaccineCourseVO.vc_sort}</h4></a>
									<p><small class="text-muted" style="font-size: 150%;"><i class="glyphicon glyphicon-time"></i>寶寶 ${vaccineCourseVO.vc_age} 個月</small></p>
								</div>
								<div class="timeline-body">
									<p>${vaccineCourseVO.vc_detailed}</p>
								</div>
							</div>
						</li>
						
					</c:forEach>		
						
					</ul>
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
  		<script type="text/javascript" src='js/js.js'></script>
  		<script type="text/javascript" src='js/timeline.js'></script>

	</body>
</html>

