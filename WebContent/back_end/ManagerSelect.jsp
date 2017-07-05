<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.manager.model.*" import="java.util.*"%>
<%
	ManagerVO managerVO = (ManagerVO) request.getAttribute("managerVO");
	ManagerVO manuserVO = (ManagerVO) session.getAttribute("manuserVO");

%>
<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>媽咪樂寶後端管理平台</title>
		<script src="https://code.jquery.com/jquery.js"></script>
		
		<script src="js/ManagerSelect.js"></script>
		<style type="text/css">
			.container{
				margin: 0;
				border:0;

			}
			.left{
				
				margin: 0px;
				border:0px;
				padding: 0px;
				max-width: 100%;
				height: 1000px;
				background-color:#CCFFFF;
				position:fixed; top:10px; left:0px; /*浮動定位*/
				text-align:center;
			}
			.right{
				position:absolute;  right:0px;
				padding-right: 0px;
				top:30px;
				height: 1000px;
				/*background-color:#bbb;*/
			}
			.right1{
/* 				margin: 0px; */
/* 				padding-left: 0px; */
 				padding-top: 5px; 
/* 				background-color:#cee; */
/* 				padding-bottom:3px; */
				height: 55px;
			}
			.right2{
				
				padding-left: 0px;
				height: 35%;
			}
			.right3{
				padding-left: 0px;
				height: 55%;
			}
			.left1{
				padding-top: 30px;
				padding-left: 12%;
				 text-align: center;
				
				/*height: 20%;*/
			}
			.left2{
				/*padding-top: 10px;*/
				text-align: center;
				background-color:#CCFFFF;
				/*height: 5%;*/
			}
			.left3{
				background-color:#CCFFFF;
				/*height: 75%;*/
			}

			.bighead{
				width: 150px;
				height: 150px;
				border-radius:50%;
				border-color: #fff;
				

			}
			.panel-heading{
				color:white;
				text-align: center;
				font-size:18px;
			}
			
			.panel-body{
				color:white;
				text-align: center;
				font-size:14px;
			}
			.collapsed{
				color:white;

			}
			#navtop{
				padding-right: 20px;
				background-color:#1aa2cc;
			}
			#toptext{
				color: #eaedee;
			}
			.tablecontext{
				font-size:22px;
　				color:pink;
			}
			.adv{
				width: 60%;
				height: 300px;
			}
			th{
				text-align: center;
			}
			#leftfont:link{
				color:#fff;

			}
			#leftfont:hover{
				font-weight: bold;
				
			}
			.thumbnail{
				width: 150px;
				height: 150px;
				border-radius:100%;
				text-align: center;
				margin:0px;
			}
			
			
			
			a:link {color:#555;font-size:16px;font-weight:bold;}
			a:visited {color: gray}
			a:active {color: green}
			a:hover {color: #555}
			
			
			
/* @font-face */
/* { */
/* font-family: myFirstFont; */
/* src: url('../setofont.ttf') */
/*     ,url('../setofont.ttf'); /* IE9+ */ */
/* } */

/* @font-face */
/* { */
/* font-family:ch; */
/* src: url('../font/setofont.ttf') */
/*     ,url('../font/setofont.ttf'); /* IE9+ */ */
/* font-weight:bold; */
/* } */

/* div */
/* { */
/* font-family:ch; */
/* } */

	
	
	
	
	</style>
		
	</head>
	<body>
		
		

		<nav class="navbar navbar-default navbar-fixed-top" role="navigation" id="navtop">
			<!-- logo區 -->
			<a class="navbar-brand" href="<%=request.getContextPath() %>/back_end/ManagerINDEX.jsp" id="toptext"></i>媽咪樂寶後端管理平台</a>
		
			
		
			
			<!-- 右選單 -->
			<ul class="nav navbar-nav navbar-right">
				
				
				<!-- 下拉選單 -->
				
<!-- 						<li><a href="#" id="toptext">修改密碼</a></li> -->
					
				
				<li><a href='<%=request.getContextPath() %>/back_end/man.do?action=ManLogout' data-toggle="modal" id="toptext"><i class="glyphicon glyphicon-log-out"></i> 登出</a></li>


			</ul>
		</nav>
		
<!-- -------------------------------nav----------left-------------------------------------	 -->
<jsp:useBean id="autlistSvc" scope="page" class="com.authority_list.model.AuthorityListDAO" />
<jsp:useBean id="autSvc" scope="page" class="com.authority.model.AuthorityDAO" />
<jsp:useBean id="FRSvc" scope="page" class="com.forum_report.model.ForumReportService" />
<jsp:useBean id="PCRSvc" scope="page" class="com.pc_report.model.PcReportService" />
<jsp:useBean id="advSvc" scope="page" class="com.advertise.model.AdvertiseService" />
<jsp:useBean id="manSvc" scope="page" class="com.manager.model.ManagerService" />
<!-- 討論區檢舉數量 -->
<%int fr=0; %>
<c:forEach var="FRVO" items="${FRSvc.getAllStatus('0')}">
<%fr++; %>
</c:forEach>
<!-- 產後檢舉數量 -->
<%int pcr=0; %>
<c:forEach var="PCRVO" items="${PCRSvc.all}">
<c:if test="${PCRVO.pcr_status=='0'}">	
<%pcr++; %>
</c:if>
</c:forEach>
<!-- 新廣告數量 -->
<%int adv=0; %>
<c:forEach var="advVO" items="${advSvc.getStatus('0')}">
<%adv++; %>
</c:forEach>
<!-- 新授權管理員 -->
<%int z=0; %>
<c:forEach var="manVO" items="${manSvc.allMan}">
<c:if test="${empty autlistSvc.findByPK(manVO.man_no)}">
<%z++; %>
</c:if>
</c:forEach>

			<div class="col-xs-12  col-lg-2 left" style="width:17%;">left
				<div class="col-xs-12 col-lg-12 left1">
<%-- 					<a href="<%=request.getContextPath() %>/back_end/ManagerINDEX.jsp" class="thumbnail"><img class="bighead" src="../img/boy.png" /></a> --%>
				</div>
					<div class="col-xs-12 col-lg-12 left2"><h4 id="jobname">${manuserVO.man_name }，你好！</h4></div>
				<div class="col-xs-12 col-lg-12 left3">
									
						<c:forEach var="autlistVO" items="${autlistSvc.all}">
						<c:if test="${autlistVO.man_no==manuserVO.man_no}">			
	
						<c:if test="${autlistVO.aut_no=='AUT0000010'}">	
						  <div>
						   	<div id="AUT0000010" class="panel-heading" role="tab" id="headingOne">
						      
						        	<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne" id="leftfont" style="color:#555;font-size:18px;">
						         		 會員管理
						        	</a>
						     	
						    </div>
						    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
						      	<div class="panel-body" id="leftfont" style="font-size:18px;"><a href="ManageMem.jsp">
						      		 會員帳號
						      	</div>
						      	<div class="panel-body" id="leftfont" style="font-size:18px;"><a href="ManageFir.jsp">
						       		廠商帳號
						      	</div>
						    </div>
						  </div>
						  </c:if>
						  
						  <c:if test="${autlistVO.aut_no=='AUT0000020'}">	
						  <div>
						    <div id="AUT0000020" class="panel-heading" role="tab" id="headingTwo" >
						  
						        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo" id="leftfont" style="color:#555;font-size:18px;">
						          	廣告管理
						        </a>
						      		<span class="badge" style="font-size:20px;position: relative;left:42px;"><%=adv++ %></span>
						    	</div>
						    	<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
						       	<div class="panel-body" id="leftfont" style="font-size:18px;"><a href="AdvertiseReview.jsp">
						       		檢視與審核廣告</a>
						      	</div>
						    </div>
						  </div>
						  </c:if>
						  
						  
						  <c:if test="${autlistVO.aut_no=='AUT0000030'}">	
						  <div>
						    <div id="AUT0000030" class="panel-heading" role="tab" id="headingThree">
						    
						        	<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree" id="leftfont" style="color:#555;font-size:18px;">
						         		 檢舉管理
						        	</a>
						      	<span class="badge" style="font-size:20px;position: relative;left:42px;"><%=fr+pcr %></span>
						    </div>
						    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
						      	<div class="panel-body" id="leftfont"><a href="<%=request.getContextPath() %>/back_end/PC_Report.jsp">
						        		廠商檢舉會員</a><span class="badge" style="font-size:10px;position: relative;left:30px;font-size:18px;"><%=pcr %></span>
						      	</div>
						 
						      	<div class="panel-body" id="leftfont"><a href="<%=request.getContextPath() %>/back_end/ForumReport.jsp">
						        		討論區檢舉</a><span class="badge" style="font-size:10px;position: relative;left:30px;font-size:18px;"><%=fr %></span>
						      	</div>
						    </div>
						</div>
						</c:if>
						
						
						
						
						
						
						<c:if test="${autlistVO.aut_no=='AUT0000040'}">	
						<div>
							<div id="AUT0000040" class="panel-heading" role="tab" id="headingFour" >
						      
						        
						      		<a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour" id="leftfont" style="color:#555;font-size:18px;">
						         		 管理員管理
						         	</a>	
						         		<span class="badge" style="font-size:20px;position: relative;left:30px;"><%=z %></span>
						        		
						        	
						    </div>
						    <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
						      	
						      	<div class="panel-body" id="leftfont" style="font-size:18px;"><a href="AuthorityMan.jsp">
						       						   						       
						       		 權限管理</a>
						        
						      	</div>
						     
						    </div>
						    
						</div>
						</c:if>
						
						
				</c:if>
				</c:forEach>		
						
						
						
				</div>


			</div>


	
	</body>
</html>
<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>