<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.*"%>
<%@ page import="com.forum_article.model.*"%>
<%@ page import="com.member.model.*"%>
<%

%>
<html>
<head>

<title>熱門文章</title>
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

<!-- <div class="panel panel-success" style="width: 100%;"> -->
<!-- 		<div class="panel-heading" style="height: 30px;"> -->
<!-- 			<p class="panel-title" style="text-align: center; font-size: 20px;">會員資料</p> -->
<!-- 		</div> -->

	<table class="table table-hover" style="text-align: center;">
	
		<tbody>
<!-- 			int i 給序號使用 -->
			<% int q=0;%>
			<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
			<jsp:useBean id="FASvc" scope="page" class="com.forum_article.model.ForumArticleService" />
			<jsp:useBean id="AFSvc" scope="page" class="com.article_favorite.model.ArticleFavoriteService" />
			
			<c:forEach var="AF_FAVVO" items="${AFSvc.all_FAV}">
			<c:if test="${FASvc.getOneFa(AF_FAVVO.fa_no).fa_dislike=='1'}">

			<tr align='center' valign='middle'>
			<td style="width:6%;text-align:center;">
				<%q++; %>
				<%=q %>
				</td>

				<td style="width:25%;text-align:center;"><a href="<%=request.getContextPath()%>/forum/FA.do?fa_no=${AF_FAVVO.fa_no}&mem_no=${memSvc.getOneMem(FASvc.getOneFa(AF_FAVVO.fa_no).mem_no).mem_no}&action=getOne_For_Display">${FASvc.getOneFa(AF_FAVVO.fa_no).fa_title}</a></td>
				<td style="width:8%;text-align:center;">${AF_FAVVO.mem_no}</td>
				<td style="width:15%;text-align:center;">
					 ${memSvc.getOneMem(FASvc.getOneFa(AF_FAVVO.fa_no).mem_no).mem_name}

				</td>
				
				<td style="width:25%;text-align:center;"><fmt:formatDate value="${FASvc.getOneFa(AF_FAVVO.fa_no).fa_publish_date}" pattern="yyyy-MM-dd aaa HH:mm:ss "/></td>
				<td style="width:25%;text-align:center;">${FASvc.getOneFa(AF_FAVVO.fa_no).fa_modify_date}</td>
				
			</tr>

</c:if>
	</c:forEach>
				<tr style="width:100%;">
					
				</tr>
	</tbody>
	
	</table>
	
	
	
<!-- </div> -->
</body>
</html>