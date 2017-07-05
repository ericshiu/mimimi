<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="com.forum_article.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%

%>
<html>
<head>

<title>最新文章</title>
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
			<jsp:useBean id="AFSvc" scope="page" class="com.article_favorite.model.ArticleFavoriteService" />
			<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
			<jsp:useBean id="faSvc" scope="page" class="com.forum_article.model.ForumArticleService" />
			<c:forEach var="fadescVO" items="${faSvc.desc}">
			<c:if test="${fadescVO.fa_dislike=='1'}">
			<tr align='center' valign='middle' href="http://yahoo.com.tw">
				<td style="width:6%;text-align:center;">
				<%q++; %>
				<%=q %>
				</td>
<!-- 				<td> -->
<!-- 				  <FORM METHOD="post" ACTION="adv.do"> -->
<!-- 				    <Button type="submit" class="btn btn-warning" value="">檢視</Button> -->
<%-- 				    <input type="hidden" name="adv_no" value="${advVO.adv_no}"> --%>
				   
<!-- 				    <input type="hidden" name="action"value="advDetail"></FORM> -->
<!-- 				</td> -->
				
				<td style="width:25%;text-align:center;"><a href="<%=request.getContextPath()%>/forum/FA.do?fa_no=${fadescVO.fa_no}&mem_no=${fadescVO.mem_no }&action=getOne_For_Display">${fadescVO.fa_title}</a></td>
				<%int i=0; %>
				<c:forEach var="AFVO" items="${AFSvc.all}">
				<c:if test="${fadescVO.fa_no==AFVO.fa_no}">
				<%i++; %>
				</c:if>
				</c:forEach>
				<td style="width:8%;text-align:center;"><%=i %></td>
				<td style="width:15%;text-align:center;">
					 ${memSvc.getOneMem(fadescVO.mem_no).mem_name}
				</td>
				<td style="width:25%;text-align:center;"><fmt:formatDate value="${fadescVO.fa_publish_date}" pattern="yyyy-MM-dd aaa HH:mm:ss "/></td>
				<td style="width:25%;text-align:center;"><fmt:formatDate value="${fadescVO.fa_modify_date}" pattern="yyyy-MM-dd aaa HH:mm:ss "/></td>

				
				
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