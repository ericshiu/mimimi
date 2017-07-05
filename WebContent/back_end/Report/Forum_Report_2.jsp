<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
<%@ page import="java.util.*"%>
<%@ page import="com.forum_report.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
ForumReportService FRSvc = new ForumReportService();
List<ForumReportVO> list = FRSvc.getAllStatus("2");
pageContext.setAttribute("list",list);
%>
<html>
<head>

<title>Insert title here</title>
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
			<c:if test="${FRVO.fr_status=='2' }">
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
			<td colspan="8" id="<%="od"+q %>" style="text-align:left;">
			<%="od"+q %>
				${FRVO.fr_content}
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


</body>
</html>