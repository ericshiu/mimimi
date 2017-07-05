<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.optional_test.model.*"%>

<%
OptionalTestService OPSvc = new OptionalTestService();
    List<OptionalTestVO> list = OPSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html lang="">
	<head>
	<title>孕期可做檢查/自費項目<title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>Title Page</title>

		<!-- 引用icon -->
		
		<!-- 引用Css -->
		 <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
  		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
		
  		<link rel='stylesheet prefetch' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css'>
		<link rel='stylesheet prefetch' href='http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css'>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/timeline.css.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/title.css">
			<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/titlehover.css">
		<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
		
		
		<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
		<script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>
		<script
  		src="https://code.jquery.com/jquery-3.2.1.js"
  		integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
  		crossorigin="anonymous"></script>
  		<script type="text/javascript" src='<%=request.getContextPath() %>/js/js.js'></script>
  		<script type="text/javascript" src='<%=request.getContextPath() %>/js/timeline.js'></script>
	



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
					    <h1 class="">孕期可做檢查/自費項目</h1>
					    <p>Go ahead, resize me.</p>
					  </div>
					  <div class="blur">
					  </div>
					</div>
				<div class="col-xs-12 col-sm-3">
				 <jsp:include page="../listgrop.jsp" flush="true"/>
				</div>
				<div class="col-xs-12 col-sm-9">
				<h3>注意</h3>
				 <div class="title">
				 
				 
				
				 
				 
					<div class="title-word">懷孕6週：進行第一次產檢；</div>
					  <div class="title-word">懷孕12-28週：每4周做一次產檢；</div>
					  <div class="title-word">懷孕28—36周：在這一階段，准媽媽產檢的頻率會更密一些，通常每兩周就要進行一次檢查，以便了解孕媽媽體重增長的情況，是否有血壓的增高，以及有沒有尿蛋白等情況。</div>
					  <div class="title-word">懷孕36周以後：准媽媽需要每1周做一次產檢。</div>
					  <div class="title-word">產檢,重要性主要是針對影響出生質量的各種因素：</div>
					  <div class="title-word">遺傳性疾病、環境中的化學、物理、生物的有害物質、</div>
					  <div class="title-word"> 孕婦的營養膳食、職業、疾病等采取積極預防措施，</div>
					  <div class="title-word">運用現代醫學技術對胎兒的成長和健康進行監測，</div>
					  <div class="title-word">對母子實行統一管理， 保證母嬰獲得良好的妊娠結局。</div>
					</div>
				</div>
				
				<!-- 側選選單結束 -->

				
				<div class="col-xs-12 col-sm-12">
					<div class="page-header">
					  <h1>孕期可做檢查/自費項目</h1>
					</div>
					<div style="display:inline-block;width:100%;overflow-y:auto;">
					<ul class="timeline timeline-horizontal">
					
					<c:forEach var="OptionalTestVO" items="${list}" >
						<li class="timeline-item">
							<div class="timeline-badge primary"><i class="glyphicon glyphicon-check"></i></div>
							<div class="timeline-panel">
								<div class="timeline-heading">
									<h4 class="timeline-title">${OptionalTestVO.ot_name}</h4>
									<p><small class="text-muted" style="font-size: 150%;"><i class="glyphicon glyphicon-time"></i>建議檢察 ${OptionalTestVO.ot_week_start} - ${OptionalTestVO.ot_week_end} 周</small></p>
								</div>
								<div class="timeline-body">
									<p>${OptionalTestVO.ot_summary}</p>
								</div>
							</div>
						</li>
					</c:forEach>	
						
					</ul>
				</div>


			</div>
		</div>

		</div>

	</body>
</html>

