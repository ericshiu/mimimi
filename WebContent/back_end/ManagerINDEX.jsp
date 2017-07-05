<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.manager.model.*" import="java.util.*"%>
<%
	ManagerVO managerVO = (ManagerVO) request.getAttribute("managerVO");
	ManagerVO manuserVO = (ManagerVO) session.getAttribute("manuserVO");
%>
<!DOCTYPE html>
<html lang="" style="height:100%;width:100%;">

	<!--固定的 -->
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>媽咪樂寶後端管理平台</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
	
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
<!--固定的 -->
<style>
		body{
				margin:0px;
				padding:0px;

				background:#fff url("img/backbaby2.jpg") center center fixed no-repeat;
				background-size: cover;　

			}     
</style>			   
	<body style="height:100%;width:100%;">
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

			
				<div class="col-xs-12  col-lg-2 " style="height:100%;width:17%;margin:0px;">
				<jsp:include page="/back_end/ManagerSelect.jsp" flush="true"/>
				</div>
				<div class="col-xs-12  col-lg-10 " style="height:100%;width:83%;top:50px;padding:0px;margin:0px;">
					<div class="col-xs-12  col-lg-3 ">
					<div class="col-xs-12  col-lg-12 " style="margin:20px;">
					<c:forEach var="autlistVO" items="${autlistSvc.all}">
					<c:if test="${autlistVO.man_no==manuserVO.man_no}">			
	
					<c:if test="${autlistVO.aut_no=='AUT0000020'}">	
						<div class="col-xs-12  col-lg-8 ">
						<p style="font-size:20px;font-weight:bold;">廣告未審核件數：</p>
						</div>
						<div class="col-xs-12  col-lg-4 ">
						<a href="AdvertiseReview.jsp" style="font-size:30px;"><%=adv++ %></a>
						</div>
					</div>
					</c:if>
					<c:if test="${autlistVO.aut_no=='AUT0000040'}">
					<div class="col-xs-12  col-lg-12 " style="margin:20px;">
						<div class="col-xs-12  col-lg-8 ">
						<p style="font-size:20px;font-weight:bold;">待授權管理員：</p>
						</div>
						<div class="col-xs-12  col-lg-4 ">
						<a href="AuthorityMan.jsp" style="font-size:30px;"><%=z %></a>
						</div>
					</div>
					</c:if>
					<c:if test="${autlistVO.aut_no=='AUT0000030'}">
					<div class="col-xs-12  col-lg-12 " style="margin:20px;">
						<div class="col-xs-12  col-lg-8 ">
						<p style="font-size:20px;font-weight:bold;">討論區檢舉件數：</p>
						</div>
						<div class="col-xs-12  col-lg-4 ">
						<a href="ForumReport.jsp" style="font-size:30px;"><%=fr %></a>
						</div>
					</div>
					</c:if>
					<c:if test="${autlistVO.aut_no=='AUT0000030'}">	
					<div class="col-xs-12  col-lg-12 " style="margin:20px;">
						<div class="col-xs-12  col-lg-9 ">
						<p style="font-size:20px;font-weight:bold;">產後護理檢舉件數：</p>
						</div>
						<div class="col-xs-12  col-lg-3 ">
						<a href="PC_Report.jsp" style="font-size:30px;"><%=pcr %></a>
						</div>
					</div>
					</c:if>
				</c:if>
				</c:forEach>
				</div>	
				
				
					<div class="col-xs-12  col-lg-3 "></div>
					<div class="col-xs-12  col-lg-3 "></div>
					<div class="col-xs-12  col-lg-3 "></div>
				
				</div>
				
				
</body>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>	
</html>
	
