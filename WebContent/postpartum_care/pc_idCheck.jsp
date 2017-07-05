
<%@ page import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.postpartum_care.model.*"%>
<%

String pc_id=request.getParameter("pc_id");
// pc_id="PC0001";
String pc_check;
PostpartumCareService postpartumCareSvc=new PostpartumCareService();

try {
   PostpartumCareVO postpartumCareCheck = postpartumCareSvc.getOnePCById(pc_id);
   pc_check = postpartumCareCheck.getPc_id();
} catch (Exception se) {
	pc_check="查無此帳號";
	
} 

%>

<%=pc_check.trim()%>
