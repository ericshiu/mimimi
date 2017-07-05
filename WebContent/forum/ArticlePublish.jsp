<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.*"%>
<%@ page import="com.forum_article.model.*"%>
<%@ page import="com.forum_message.model.*"%>
<%@ page import="com.member.model.*"%>
<%
MemberVO userVO=(MemberVO)session.getAttribute("userVO");
ForumArticleVO FAVO=(ForumArticleVO)request.getAttribute("FAVO");
session.setAttribute("pageReq", "/member/MyArticle.jsp");
%>
<html>
<head>

<title>我的文章</title>
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


	<table class="table table-hover" style="text-align: center;">
	
		<tbody>
<!-- 			int i 給序號使用 -->
			<% int q=0;%>
			
<%-- 			<jsp:useBean id="AFDAO" scope="page" class="com.article_favorite.model.ArticleFavoriteDAO" /> --%>
			<jsp:useBean id="AFSvc" scope="page" class="com.article_favorite.model.ArticleFavoriteService" />
			<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
			<jsp:useBean id="faSvc" scope="page" class="com.forum_article.model.ForumArticleService" />
			<c:forEach var="fadescVO" items="${faSvc.desc}">
			<c:if test="${fadescVO.mem_no==userVO.mem_no}">
			<c:if test="${fadescVO.fa_dislike==1}">
			<tr align='center' valign='middle' >
				<td style="width:6%;text-align:center;">
				<%q++; %>
				<%=q %>
				</td>
				<form method="post" action="<%=request.getContextPath()%>/forum/FA.do">    
				<th style="width:12%;text-align:center;"><button type="submit" class="btn btn-info">修改</button></a></td></th>
				<input type="hidden" name="fa_no" value="${fadescVO.fa_no}">
				<input type="hidden" name="mem_no" value="${fadescVO.mem_no }">
				<input type="hidden" name="action" value="getOne_For_DisplayModify">
				</form>
				<form method="post" action="<%=request.getContextPath()%>/forum/FA.do">
				<th style="width:12%;text-align:center;"><button type="submit" class="btn btn-default">刪除</button></td></th>
				<input type="hidden" name="fa_no" value="${fadescVO.fa_no}">
				<input type="hidden" name="mem_no" value="${fadescVO.mem_no }">
				<input type="hidden" name="back" value="no">
				<input type="hidden" name="action" value="update_dislike">
				</form>
				
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
				
			</tr>
	</c:if>
	</c:if>
	</c:forEach>
				<tr style="width:100%;">
					
				</tr>
	</tbody>
	
	</table>
	
	
	
<!-- </div> -->
</body>
</html>