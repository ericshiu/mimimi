<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.baby_eating.model.*"%>
<%@ page import="com.baby_data.model.*"%>
<%@ page import="com.member.model.*" import="java.util.*" import="sun.misc.*"%>
<%
	MemberVO userVO = (MemberVO) session.getAttribute("userVO");
	session.setAttribute("pageReq", "/member/PersonalMem.jsp");//這行是在存重導路徑，所以要打本頁的網址
%>
<%
  	BabyDataService BDSvc = new BabyDataService();
	List<BabyDataVO> list = BDSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html lang="">
	<head>
	
	<title>新生兒進食記錄</title>
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
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/title.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/timecss.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/table.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/inputtype.css">
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
		
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/titlehover.css">
		
		
		<link rel="stylesheet" type="text/css" href="css/component.css" />
		
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
						    <h1 class="">寶寶進食紀錄</h1>
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

					  <div class="title-word">不要讓孩子像大人一樣吃東西。</div>
					  <div class="title-word">自幼開始為孩子培養正確的味覺。</div>
					  <div class="title-word">給孩子的食物要與其體質相符。</div>
					  <div class="title-word">不要對孩子的嘔吐或腹瀉症狀過於敏感。</div>
					  <div class="title-word">1歲到2歲的寶寶將陸續長出十幾顆牙齒，</div>
					  <div class="title-word">主要食物也逐漸從以奶類為主轉向以混合食物為主，</div>
					  <div class="title-word">而此時寶寶的消化系統尚未成熟，因此還不能給寶寶吃大人的食物，</div>
					  <div class="title-word">要根據寶寶的生理特點和營養需求，</div>
					  <div class="title-word">為他製作可口的食物，保證獲得均衡營養。</div>
					</div>
				</div>



				
				<div class="col-xs-12 col-sm-12">					
					<jsp:useBean id="babyDataSvc" scope="page" class="com.baby_data.model.BabyDataService" />
					
						<div class="input-group" style=" width: 95%; margin: 10px;">
						<form METHOD="post" ACTION="be.do" name="form1" style="text-align: center;">
							<select size="1" name="bd_no" style="height: 30px;" onchange="submit();">
							            <option value="請選擇寶寶">請選擇寶寶
							            <option value="2">全部寶寶
							
								<c:forEach var="babyDataVO" items="${babyDataSvc.all}">
									<c:if test="${userVO.mem_no==babyDataVO.mem_no}">
										<option value="${babyDataVO.bd_no}" ${(babyGrowthVO.bd_no==babyDataVO.bd_no)?'selected':'' } >${babyDataVO.bd_name}
									</c:if>
								</c:forEach>
							</select>
							<input type="hidden" name="action" value="listBE_ByCompositeQuery">
							
						</form>
						</div>
						
					
					
				  	<div class="tbl-header">
				    <table cellpadding="0" cellspacing="0" border="0">
				      <thead>
				        <tr>
				          <th>寶寶名稱</th>
				          <th>進食時間</th>
				          <th>進食類別紀錄</th>
				          <th>奶量</th>
				          <th></th>
				          
				        </tr>
				      </thead>
				    </table>
				  	</div>
				  	<div class="tbl-content">
				    <table cellpadding="0" cellspacing="0" border="0">
				    
				    
				      <tbody>
				       <jsp:useBean id="BESvc" scope="page" class="com.baby_eating.model.BabyEatingService" />
				      <c:forEach var="babyEatingVO" items="${BESvc.all}">
				      <c:forEach var="babyDataVO" items="${list}">
				      <c:if test="${userVO.mem_no==babyDataVO.mem_no}">
					  	<c:if test="${babyDataVO.bd_no==babyEatingVO.bd_no}">
				        <tr>
				          <td>${babyDataVO.bd_name}</td>
				          <td>${babyEatingVO.be_date}</td>
				          <td>${babyEatingVO.be_sort}</td>
				          <td>${babyEatingVO.be_mete}</td>
				          <td>
							        
									  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/be.do">
							     		 <input class="btn btn-primary btn-lg outline" type="submit" value="修改" style="padding: 6px 12px;background-color: #fff;">
										 
									     <input type="hidden" name="be_no" value="${babyEatingVO.be_no}">
									     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
									     <input type="hidden" name="action"	value="getOne_For_Update">
									   </FORM>
				
							          
						 </td>
				          
				        </tr>
				        </c:if>
				        </c:if>
				        </c:forEach>
				       </c:forEach>
				        	
				      </tbody>
				    </table>
				  	</div>
					</div>


					<div class="col-xs-12 col-sm-4">
					</div>


					<div class="container .button col-xs-12 col-sm-4">
						<a href="be_add.jsp"><button id="button">新增紀錄</button></a>
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

