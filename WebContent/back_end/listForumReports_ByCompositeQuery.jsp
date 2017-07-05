<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listForumReports_ByCompositeQuery" scope="request" type="java.util.List" />
<jsp:useBean id="FASvc" scope="page" class="com.forum_article.model.ForumArticleService" />
<html>
<head>
<title>複合查詢 - listForumReports_ByCompositeQuery.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=blue>
☆萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位<br>
☆此頁作為複合查詢時之結果練習，</font> <font color=red>已增加分頁、送出修改、刪除之功能:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3><font color=red>複合查詢</font>員工 - listEmps_ByCompositeQuery.jsp</h3>
		<a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
		</td>
	</tr>
</table>


<table border='1' bordercolor='#CCCCFF' width='800'>
	<tr>
		<th>檢舉編號</th>
		<th>會員編號</th>
		<th>文章編號</th>
		<th>檢舉類別</th>
		<th>檢舉標題</th>
		<th>檢舉內容</th>
		<th>檢舉時間</th>
		<th>檢舉狀態</th>
		<th>審核日期</th>
	</tr>

	<c:forEach var="FRVO" items="${listForumReports_ByCompositeQuery}" >
		<tr align='center' valign='middle' ${(FRVO.fr_am_no==param.fa_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			<td>${FRVO.fr_am_no}</td>
			<td>
			<c:forEach var="FAVO" items="${FASvc.allFa}">
			<c:if test="${FRVO.fr_am_no==FAVO.fa_no}">
				 ${FAVO.fa_no}
			</c:if>
			</c:forEach>
              
			</td>
			<td>${FRVO.fr_am_no}</td>
			<td>${FRVO.fr_type}</td>
			<td>${FRVO.fr_title}</td>
			<td>${FRVO.fr_content}</td>
			<td>${FRVO.fr_publish_date}</td>			
			</c:forEach>
</table>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do"> --%>
<!-- 			     <input type="submit" value="修改">  -->
<%-- 			     <input type="hidden" name="empno" value="${empVO.empno}"> --%>
<%-- 			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<%-- 			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller--> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do"> --%>
<!-- 			    <input type="submit" value="刪除"> -->
<%-- 			    <input type="hidden" name="empno" value="${empVO.empno}"> --%>
<%-- 			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<%-- 			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller--> --%>
<!-- 			    <input type="hidden" name="action"value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
<%-- 	</c:forEach> --%>
<!-- </table> -->
<%-- <%@ include file="pages/page2_ByCompositeQuery.file" %> --%>

<br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b>
</body>
</body>
</html>
