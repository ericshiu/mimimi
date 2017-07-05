<%@ page import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%

String mem_id=request.getParameter("mem_id");
MemberService memSvc=new MemberService();
List<MemberVO> list = memSvc.getAll();
pageContext.setAttribute("list",list);

%>

<c:forEach var="check" items="${list}">
		"${check.mem_id}"
		</c:forEach>


