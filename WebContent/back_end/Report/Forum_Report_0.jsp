<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
<%@ page import="java.util.*"%>
<%@ page import="com.forum_report.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
ForumReportService FRSvc = new ForumReportService();
List<ForumReportVO> list = FRSvc.getAllStatus("0");
pageContext.setAttribute("list",list);
session.setAttribute("pageReq", "/back_end/ForumReport.jsp");
%>
<html>
<head>

<title>Insert title here</title>
<script language="JavaScript" src="js/ForumReport.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/back_end/css/Forum.css">
</head>
<body>

<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>



<!-- 通過跳出視窗顯示使否去停權跟與扣點數 -->
		<c:if test="${not empty SMsgs}">
			<font color='red' style="display: none;">
				<ul id="Smessage">
					<c:forEach var="message" items="${SMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</font>
		</c:if>
		
		<button type="button" data-toggle="modal" data-target="#SModal"
		id="SBtn" style="display:none;">Open Modal</button>
	<div class="modal fade" id="SModal" role="dialog" style="top: 25%;display: none;">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header modal-header-danger" style="height: 60px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">通知訊息</h4>
				</div>
				<div class="modal-body">
					<p id="SMsg" style="font-size: 16px;"></p>
				</div>
				<div class="modal-footer">
					<FORM METHOD="post" ACTION="mem.do">
				    <c:if test="${not empty FRVO.mem_no}">
				    <input type="hidden" name="mem_no" value="${FRVO.mem_no}">
					</c:if>
				    <input type="hidden" name="action"value="getOne_For_Display">
				    <button type="submit" class="btn btn-success">進行</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">不了</button>
				</FORM>
					
				</div>
			</div>

		</div>
	</div>
<!-- 		通過訊息 -->


<!-- 通過跳出視窗顯示使否去停權跟與扣點數 -->
		<c:if test="${not empty EMsgs}">
			<font color='red' style="display: none;">
				<ul id="Emessage">
					<c:forEach var="message" items="${EMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</font>
		</c:if>
		
		<button type="button" data-toggle="modal" data-target="#EModal"
		id="EBtn" style="display:none;">Open Modal</button>
	<div class="modal fade" id="EModal" role="dialog" style="top: 25%;display: none;">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header modal-header-danger" style="height: 60px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">通知訊息</h4>
				</div>
				<div class="modal-body">
					<p id="EMsg" style="font-size: 16px;"></p>
				</div>
				<div class="modal-footer">
				
					<button type="button" class="btn btn-default" data-dismiss="modal">OK</button>
				</FORM>
					
				</div>
			</div>

		</div>
	</div>
<!-- 		通過訊息 -->

<div class="panel panel-success" style="width: 100%;top:30px;">
		<div class="panel-heading" style="height: 45px;">
			<p class="panel-title" style="text-align: center; font-size: 20px;">討論區檢舉</p>
		</div>

	<table class="table table-hover" style="text-align: center;">
		<thead>
			<tr>
				<th>序號</th>
<!-- 				<th>檢視檢舉</th> -->
				<th>檢舉編號</th>
				<th>檢舉者</th>
				<th>文章編號</th>
				<th>檢舉類別</th>
				<th>檢舉標題</th>			
				<th>檢舉日期</th>
				<th>檢舉狀態</th>
				<th>審核日期</th>
				
			</tr>
		</thead>
		<tbody>
		
<!-- 			int i 給序號使用 -->
			<% int q=0;%>
			<%@ include file="pages/page1.file" %> 
			
			<c:forEach var="FRVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<c:if test="${FRVO.fr_status=='0' }">
			<tr align='center' valign='middle' style="background-color:#ccffff">
				<td>
				
				<%q++; %>
				<%=q %>
				</td>
<!-- 				<td> -->
				
<!-- 				    <Button class="expand" class="btn btn-warning" onclick="function()">檢視</Button> -->
<%-- 				    <%="expand"+q %> --%>
				
<!-- 				</td> -->
				<td>${FRVO.fr_no}</td>
				<td>
					 ${memSvc.getOneMem(FRVO.mem_no).mem_name}
				</td>
				<td>${FRVO.fr_am_no}</td>
				<td>${FRVO.fr_type}</td>
				<td>${FRVO.fr_title}</td>
				<td><fmt:formatDate value="${FRVO.fr_publish_date}" pattern="yyyy-MM-dd aaa HH:mm:ss "/></td>		
				<td>${FRVO.fr_status}</td>	
				<td><fmt:formatDate value="${FRVO.fr_review_date}" pattern="yyyy-MM-dd aaa HH:mm:ss "/></td>		
				
			</tr>

			<tr>
			<th>
			檢舉內容：
			</th>
			<td colspan="5" id="<%="od"+q %>" style="text-align:left;">
			
				${FRVO.fr_content}
			</td>
			<td>
			<FORM METHOD="post" ACTION="FR.do">
				    <Button type="submit" class="btn btn-info" >通過</Button>
				    <input type="hidden" name="fr_no" value="${FRVO.fr_no}">
				   <input type="hidden" name="fr_pass"value="pass">
				    <input type="hidden" name="action"value="update">
				</FORM>
			</td>
			<td>
			<FORM METHOD="post" ACTION="FR.do">
				    <Button type="submit" class="btn btn-success" >未通過</Button>
				    <input type="hidden" name="fr_no" value="${FRVO.fr_no}">
				    <input type="hidden" name="fr_pass"value="unpass">
				    <input type="hidden" name="action"value="update">
				</FORM>
			
			</td>
			<td>
			
			<jsp:useBean id="FASvc" scope="page" class="com.forum_article.model.ForumArticleService" />
		
			<form method="post" action="<%=request.getContextPath()%>/back_end/FA.do">
				<c:if test="${FASvc.getOneFa(FRVO.fr_am_no).fa_dislike=='1'}">
				<button type="submit" class="btn btn-default">屏蔽文章</button>
				</c:if>
				<input type="hidden" name="fa_no" value="${FRVO.fr_am_no}">			
				<input type="hidden" name="back" value="yes">
				<input type="hidden" name="action" value="update_dislike">
			</form>
			
			<form method="post" action="<%=request.getContextPath()%>/back_end/FA.do">
				<c:if test="${FASvc.getOneFa(FRVO.fr_am_no).fa_dislike=='0'}">
				<button type="submit" class="btn btn-default" disabled>屏蔽文章</button>
				</c:if>
				<input type="hidden" name="fa_no" value="${FRVO.fr_am_no}">			
				<input type="hidden" name="back" value="yes">
				<input type="hidden" name="action" value="update_dislike">
			</form>
		
			
			</td>
			
			
			</tr>
		</c:if>
	</c:forEach>
				<tr style="width:100%;">
					<%@ include file="pages/page2.file" %>
				</tr>
			
	</tbody>
	
	</table>
	
	
	
</div>
</body>
</html>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>