<%@ page import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.firm.model.*"%>
<%

String fir_id=request.getParameter("fir_id");
FirmService firSvc=new FirmService();
List<FirmVO> list = firSvc.getAllFir();
pageContext.setAttribute("list",list);

%>

<c:forEach var="check" items="${list}">
		"${check.fir_id}"
		</c:forEach>


