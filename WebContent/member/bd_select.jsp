<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.member.model.*" import="java.util.*" import="sun.misc.*"%>
<%@ page import="com.baby_data.model.*"%>
<%@ page import="com.baby_excretion.model.*"%>
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
	<title>寶寶中心</title>
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
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/title.css">
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/inputtype.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
	
	
	<jsp:include page="/front_end/frontTOP.jsp" flush="true"/>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-3">
				
				<jsp:include page="../listgrop.jsp" flush="true"/>
			</div>
			<div class="col-xs-12 col-sm-9">


			
				<c:forEach var="babyDataVO" items="${list}">
					<c:if test="${userVO.mem_no==babyDataVO.mem_no}">
					<div class="col-xs-12 col-sm-4" >
					
						<div class="row" ">
							<div class="modal-content" style="border-style:dashed;border-width:3px 3px 3px 3px; margin: 5px;">
										<div class="panel panel-info" style="">
											<div class="panel-heading">
											<img style="width: 232px; height: 160px; border-radius: 50%; text-align: center;" src="<%= request.getContextPath() %>/DBGifReaderBD?bd_no=${babyDataVO.bd_no}">
												<h3 class="panel-title" style="text-align: center;"><b>會員資訊維護</b></h3>
											</div>
											<table class="table table-hover" style="text-align: center;">
												<tbody>
					
					
													<tr id="Fathernike">
														<th style="text-align:center;">寳寳姓名：</th>
														<th name="mem_nike"   id="Usernike" style="text-align:center;">${babyDataVO.bd_name}</th>
													</tr>
													<tr>
														<th style="text-align:center;">性別：</th>
														<th name="mem_sex"  style="text-align:center;">${babyDataVO.bd_sex}</th>			
													</tr>
													<tr>
														<th style="text-align:center;">生日：</th>
														<th name="mem_birthday" value=""  style="text-align:center;">${babyDataVO.bd_birthday}</th>
													</tr>
													<tr>
														<th style="text-align:center;">預產期	：</th>
														<th name="mem_birthday" value=""  style="text-align:center;">${babyDataVO.bd_pre}</th>
													</tr>
													<tr>
														<th style="text-align:center;">胎次：</th>
														<th name="mem_birthday" value=""  style="text-align:center;">${babyDataVO.bd_order}</th>
													</tr>
													<tr>
														<th th colspan="2" style="text-align: center;">
															<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/bd.do">
									     
																 <input type="submit" value="修改" class="btn btn-primary btn-lg outline">
															     <input type="hidden" name="bd_no" value="${babyDataVO.bd_no}">
															     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
															     <input type="hidden" name="action"	value="getOne_For_Update">
															 </FORM>
														</th>
														
													</tr>
												</tbody>
											</table>
											
										</div>		
							</div>
						</div>
					
					</div>
					</c:if>
					</c:forEach>
			</div>
		</div>
	</div>
		<div class="container">
		<div class="row">

		<div class="col-xs-12 col-sm-3"></div>
		<div class="col-xs-12 col-sm-9">
			<div class="col-xs-12 col-sm-4">
					</div>


					<div class="container .button col-xs-12 col-sm-4">
						<a href="bd_add.jsp"><button id="button">新增紀錄</button></a>
					</div>


					<div class="col-xs-12 col-sm-4">
					</div>
					</div></div>
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

