<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.manager.model.*"%>
<%

String man_id=request.getParameter("man_id");
ManagerService manSvc=new ManagerService();
List<ManagerVO> list = manSvc.getAllMan();
pageContext.setAttribute("list",list);

%>

<c:forEach var="check" items="${list}">
		"${check.man_id}"
		</c:forEach>


