<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.baby_sleep.model.* "%>
<%@ page import="com.baby_data.model.*"%>
<%@ page import="com.member.model.*" import="java.util.*" import="sun.misc.*"%>
<%
	MemberVO userVO = (MemberVO) session.getAttribute("userVO");
	session.setAttribute("pageReq", "/member/PersonalMem.jsp");//這行是在存重導路徑，所以要打本頁的網址
%>
<%	List<BabySleepVO> list2 = (List<BabySleepVO>) request.getAttribute("lisBS_ByCompositeQuery"); 
	pageContext.setAttribute("list2",list2);
%>

<%
  	BabyDataService BDSvc = new BabyDataService();
	List<BabyDataVO> list = BDSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html lang="">
	<head>
	<title>新生兒睡眠紀錄</title>
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
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/titlehover.css">


	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/title.css">
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
					    <h1 class="">寶寶睡眠紀錄</h1>
					    <p>Go ahead, resize me.</p>
					  </div>
					  <div class="blur">
					  </div>
					</div>
				<div class="col-xs-12 col-sm-3">
				 <h2>選單</h2>
					<jsp:include page="../listgrop.jsp" flush="true"/>
				</div>
			<div class="col-xs-12 col-sm-9">
				<h3>注意</h3>
				 <div class="title">
				 
					<div class="title-word">完善的睡眠結構是兒童健康成長的重要保障！</div>
					  <div class="title-word">睡眠不好可引起兒童體格、智力及心理行為發育方面的障礙。</div>
					  <div class="title-word">嬰幼兒期是睡眠發展及睡眠習慣形成的關鍵時期,許多睡眠障礙源於兒童早期不良的睡眠習慣。</div>
					  <div class="title-word">因此早期睡眠障礙的處理較少需要藥物治療,對家長進行針對性的睡眠健康教育,培養兒童良好的睡眠規律往往是首選措施。</div>
					</div>
				</div>
					<div class="col-xs-12 col-sm-12">
					<jsp:useBean id="babyDataSvc" scope="page" class="com.baby_data.model.BabyDataService" />
					
						<div class="input-group" style=" width: 95%; margin: 10px;">
						<form METHOD="post" ACTION="bs.do" name="form1">
							<select size="1" name="bd_no" style="height: 30px;" onchange="submit();">
							            <option value="請選擇寶寶">請選擇寶寶
							            
							
								<c:forEach var="babyDataVO" items="${babyDataSvc.all}">
									<c:if test="${userVO.mem_no==babyDataVO.mem_no}">
										<option value="${babyDataVO.bd_no}" ${(babyGrowthVO.bd_no==babyDataVO.bd_no)?'selected':'' } >${babyDataVO.bd_name}
									</c:if>
								</c:forEach>
							</select>
<!-- 						<input type="hidden" name="action" value="getOneBD"> -->
							<input type="hidden" name="action" value="listBS_ByCompositeQuery">
						</form>
						</div>
				  	<div class="tbl-header">
				    <table cellpadding="0" cellspacing="0" border="0">
				      <thead>
				        <tr>
				        	<th>寶寶名稱</th>
				        	<th>記錄開始時間</th>
				          	<th>紀錄結束時間</th>
				          	<th>睡眠長度</th>
				          	<th></th>
				        </tr>
				      </thead>
				    </table>
				  	</div>
				  	<div class="tbl-content">
				    <table cellpadding="0" cellspacing="0" border="0">
				      <tbody>
				      <jsp:useBean id="BSSvc" scope="page" class="com.baby_sleep.model.BabySleepService" />
				        <c:forEach var="babySleepVO" items="${list2}">
				        <c:forEach var="babyDataVO" items="${list}">
				        
					        <c:if test="${userVO.mem_no==babyDataVO.mem_no}">
					  		<c:if test="${babyDataVO.bd_no==babySleepVO.bd_no}">
						        <tr>
						        	<td>${babyDataVO.bd_name}</td>
						          	<td>${babySleepVO.bs_start}</td>
						          	<td>${babySleepVO.bs_end}</td>
						          	<td>${babySleepVO.bs_time}</td>
						          	 <td>
						        
								  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/bs.do">
						     
									<input class="btn btn-primary btn-lg outline" type="submit" value="修改" style="padding: 6px 12px;background-color: #fff;">
								     <input type="hidden" name="bs_no" value="${babySleepVO.bs_no}">
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
						<a href="bs_add.jsp"><button id="button">新增紀錄</button></a>
					</div>


					<div class="col-xs-12 col-sm-4">
					</div>
				</div>	
					
<!-- tavle -->

				




					
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

