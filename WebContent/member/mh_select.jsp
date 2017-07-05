<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.mom_health.controller.*" import="com.member.model.*" import="java.util.*" import="sun.misc.*"%>
<%
	MemberVO userVO = (MemberVO) session.getAttribute("userVO");
	session.setAttribute("pageReq", "/member/PersonalMem.jsp");//這行是在存重導路徑，所以要打本頁的網址
%>






<!DOCTYPE html>
<html lang="">
	<head>
	<title>媽媽健康紀錄查詢</title>
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
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/table.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/titlehover.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/timecss.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/table.css">
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/inputtype.css">
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<link href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.12/c3.css" rel="stylesheet" type="text/css">
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
					    <h1 class="">媽媽健康紀錄</h1>
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
					<div class="title-word">1、注意：產檢的必要性</div>
					  <div class="title-word">定期做產檢，除了可以觀察准媽媽的身體健康狀況，</div>
					  <div class="title-word">重點在關心胎兒每個階段的成長與健康狀況，提早發現胎兒重大的先天性異常。</div>
					  <div class="title-word">一般來說，在懷孕28周前，每4個星期產檢一次即可;</div>
					  <div class="title-word">2、確定預產期(6～8周)</div>
					  <div class="title-word">驗出懷孕後，就該立即求診婦產科醫師，提前安排即將來臨的准媽媽280天生活作息及產前檢查的時程，</div>
					  <div class="title-word">順利孕育腹中的新生命。原則上，懷孕周數是平均滿40周(280天)，</div>
					  <div class="title-word">所以，要預測正確的預產期，首先必須知道懷孕的周數。</div>
					  
					</div>
				</div>



				<div class="col-xs-12 col-sm-12">
					
				  	<div class="tbl-header">
				    <table cellpadding="0" cellspacing="0" border="0">
				      <thead>
				        <tr>
				          <th>紀錄日期</th>
				          <th>體重</th>
				          <th>收縮壓</th>
				          <th>舒張壓</th>
				          <th>心跳</th>
				          <th>尿蛋白</th>
				          <th>寶寶心跳</th>
				          <th></th>
				        </tr>
				      </thead>
				    </table>
				  	</div>
				  	<div class="tbl-content">
				    <table cellpadding="0" cellspacing="0" border="0">
				      <tbody>
				    <jsp:useBean id="MHSvc" scope="page" class="com.mom_health.model.MomHealthService" />
				        <c:forEach var="mom_healthVO" items="${MHSvc.all}">
				         <c:if test="${userVO.mem_no==mom_healthVO.mem_no}">
					        <tr>
					          <td>${mom_healthVO.mh_date}</td>
					          <td>${mom_healthVO.mh_weight}</td>
					          <td>${mom_healthVO.mh_ststolic}</td>
					          <td>${mom_healthVO.mh_diastolic}</td>
					          <td>${mom_healthVO.mh_heartbeat}</td>
					          <td>${mom_healthVO.mh_protein}</td>
					          <td>${mom_healthVO.mh_baby_heartbeat}</td>
					          <td>
						        
								  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/mh.do">
						     
									  <input class="btn btn-primary btn-lg outline" type="submit" value="修改" style="padding: 6px 12px;background-color: #fff;">
								     <input type="hidden" name="mh_no" value="${mom_healthVO.mh_no}">
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
						<a href="mh_add.jsp"><button id="button">新增紀錄</button></a>
					</div>


					<div class="col-xs-12 col-sm-4">
					</div>
				</div>
<!-- 				<div class="col-xs-12 col-sm-12"> -->
<%-- 					<canvas class="col-xs-12 col-sm-12" id="myChart" ></canvas> --%>

<!-- 				</div>	 -->
				
				
			
		</div></div>		
		<div class="container">
			<div class="row">
				<div class="col-xs-11 col-sm-12">
					<div id="chart" style="height: 500px"></div>
				</div>	
			</div>
		</div>
				



		<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
				
		<%int i=0; %>
		 <c:forEach var="mom_healthVO" items="${MHSvc.all}">
		  <c:if test="${userVO.mem_no==mom_healthVO.mem_no}">
		  <%i++; %>
		 <div id="date<%=i %>" style="display: none;">${mom_healthVO.mh_date}</div>
		 <div id="weight<%=i %>" style="display: none;">${mom_healthVO.mh_weight}</div>
		 </c:if>
		 </c:forEach>
		<div id="count" style="display: none;"><%=i %></div>
			
		<script src="http://d3js.org/d3.v3.min.js" charset="utf-8"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.12/c3.js"></script>
		
		<script type="text/javascript">
		
		var date =new Array();
		var weight =new Array();
		var count=$id("count").innerHTML;
		date[0]="x";
		weight[0]="體重"
		
		function $id(id){
  		  return document.getElementById(id);
  		}
		
		for(var j=1;j<=count;j++){

			var datee="date"+j;	
			var dateee=$id(datee).innerHTML;
			var weightt="weight"+j;	
			var weighttt=$id(weightt).innerHTML;
			
			date[j]=dateee;
			weight[j]=weighttt;

		}
		
		
		var chart=
			c3.generate({
			    data: {
			        x: 'x',
			        columns: [
			           	date,
			           	weight
			        
			        ]
			    }
				,
			    color: {
			        pattern: ['#1f77b4', '#aec7e8', '#ff7f0e', '#ffbb78', '#2ca02c', '#98df8a', '#d62728', '#ff9896', '#9467bd', '#c5b0d5', '#8c564b', '#c49c94', '#e377c2', '#f7b6d2', '#7f7f7f', '#c7c7c7', '#bcbd22', '#dbdb8d', '#17becf', '#9edae5']
			    },
			    zoom: {
			        enabled: true
			    },
			    axis: {
			        x: {

			            type: 'timeseries',
			            tick: {
			                format: '%Y-%m-%d'
			            }
			        }
			    }
			});

			setTimeout(function () {


			    chart.load({
			        columns: [
			        	
			        ]
			    });
			}, 1000);



		</script>
		
		
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
		<script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>
		<script src="https://code.jquery.com/jquery-3.2.1.js" integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="crossorigin="anonymous"></script>
  		<script type="text/javascript" src='<%=request.getContextPath() %>/member/js/js.js'></script>
  		<script type="text/javascript" src='<%=request.getContextPath() %>/member/js/table.js'></script>

  		

<!--   		<script> -->
  		  		
  		
	</body>
</html>


