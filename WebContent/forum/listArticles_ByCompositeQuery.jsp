<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:useBean id="listArticles_ByCompositeQuery" scope="request"
	type="java.util.List" />
<html>
<head>
<title>文章查詢</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/css.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">

</head>
<body>
<%	int q = 0;	%>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
	<jsp:include page="/front_end/frontTOP.jsp" flush="true" />
	<div class="container">
	
		<div class="panel panel-success">
			<div class="panel-header-primary">
				<h3 class="panel-title"></h3>
			</div>
			<table class="table table-hover" style="text-align: right;">
			
				<tr style="background-color: rgba(240, 140, 160, 0.4); color: #555;">
					<th style="width: 6%; text-align: center;">序號</th>
					<th style="width: 25%; text-align: center;">文章標題</th>
					<th style="width: 8%; text-align: center;">收藏數</th>
					<th style="width: 15%; text-align: center;">作者</th>
					<th style="width: 25%; text-align: center;">發表日期</th>
					<th style="width: 25%; text-align: center;">最後修正日期</th>
				</tr>
				<c:forEach var="FAVO" items="${listArticles_ByCompositeQuery}">
					<tr align='center' valign='middle'">
						<td style="width: 6%; text-align: center;">
							<%
								q++;
							%> <%=q%>
						</td>
						<td style="width: 25%; text-align: center;"><a
							href="<%=request.getContextPath()%>/forum/FA.do?fa_no=${FAVO.fa_no}&mem_no=${FAVO.mem_no }&action=getOne_For_Display">${FAVO.fa_title}</a></td>
						<%
							int i = 0;
						%>
					
						<td style="width: 8%; text-align: center;"><%=i%></td>
						<td style="width: 15%; text-align: center;">
							${memSvc.getOneMem(FAVO.mem_no).mem_name}</td>
						<td style="width: 25%; text-align: center;"><fmt:formatDate value="${FAVO.fa_publish_date}" pattern="yyyy-MM-dd aaa HH:mm:ss "/></td>
						
						<td style="width: 25%; text-align: center;"><fmt:formatDate value="${FAVO.fa_modify_date}" pattern="yyyy-MM-dd aaa HH:mm:ss "/></td>
					</tr>	
				</c:forEach>		
			</table>
		</div>
	</div>
	<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>
<script src="https://code.jquery.com/jquery.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.js"
	integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
	crossorigin="anonymous"></script>

</html>
