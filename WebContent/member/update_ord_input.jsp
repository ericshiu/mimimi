<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.orders.model.*, com.member.model.*"%>
<%
	OrdersVO ordVO = (OrdersVO) request.getAttribute("ordVO");
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
	MemberVO uesrVO = (MemberVO) session.getAttribute("userVO");
	session.setAttribute("pageReq","/member/memOrd.jsp");
%>
<html>
<head>
<title>修改訂單收件資料</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script src="<%= request.getContextPath() %>/orders/js/webSocket.js"></script>
<script language="JavaScript" src="js/calendarcode.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">
<div id="popupcalendar" class="text"></div>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/css.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">


<body bgcolor='white' onload="connect();" onunload="disconnect();">
<jsp:include page="/front_end/frontTOP.jsp" flush="true" />
<c:if test="${not empty errorMsgs}">
	<font color='red'>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<FORM METHOD="post" ACTION="ord.do" name="form1">

<div class="container" style="border-style:dashed;border-width:3px 3px 3px 3px;padding:0px;">

	<div class="col-xs-12  col-lg-2 left" style="text-align:center;width:20%;">
		<jsp:include page="/member/MySelect.jsp" flush="true"/>
	</div>

<div class="col-xs-12  col-lg-10 right personalright" style="height: 60%; width: 80%; margin: 0px; border-width: 0px 0px 0px 1px; border-style: solid;background-color:#fff;" align='center' valign='middle' >
		<br>
		<div class="panel panel-success panel-group" style="width:500px;">
			<div class="panel-heading" align="center">
				變更收貨資訊
			</div>
		<table class="table table-hover table-striped" align="center"  >
		<tr align='center' valign='middle'>		
			<td>訂單編號:<font color=red></font></td>
			<td><%=ordVO.getOrd_no()%></td>
		</tr>	
		<tr align='center' valign='middle'>	
			<td>訂單日期:<font color=red></font></td>
			<td><%=ordVO.getOrd_date()%></td>
		</tr>
	
		<tr align='center' valign='middle'>
			<td>訂單金額:<font color=red></font></td>
			<td><%=ordVO.getOrd_sum()%></td>
			
		</tr>
		
		<tr align='center' valign='middle'>
			<td>收件者姓名:</td>
			<td><input type="TEXT" name="ord_rec_name" size="45" value="<%=ordVO.getOrd_rec_name()%>"/></td>
			
		</tr>
		<tr align='center' valign='middle'>
			<td>收件者地址:</td>
			<td><input type="TEXT" name="ord_rec_address" size="45" value="<%=ordVO.getOrd_rec_address()%>" /></td>
			
		</tr>
		<tr align='center' valign='middle'>
			<td>收件者電話:</td>
			<td><input type="TEXT" name="ord_rec_phone" size="45"	value="<%=ordVO.getOrd_rec_phone()%>" /></td>
			
		</tr>
	
		<tr align='center'>
			<td colspan="2">
			<button type="submit" value="" class="btn btn-info" onclick="finishUpdate()">送出修改</button>
			
			</td>
		</tr>
		</table>
		</div>
	</div>
	</div>
</div>
</div>
</div>
<input type="hidden" name="action" value="update">
<input type="hidden" name="ord_no" id="ord_no" value="<%=ordVO.getOrd_no()%>">
<input type="hidden" name="ord_date" value="<%=ordVO.getOrd_date()%>">
<input type="hidden" name="mem_no" value="<%=ordVO.getMem_no()%>">
<input type="hidden" name="fir_no" value="<%=ordVO.getFir_no()%>">
<input type="hidden" name="ord_sum" value="<%=ordVO.getOrd_sum()%>">
<input type="hidden" name="ord_status" value="<%=ordVO.getOrd_status()%>">
<input type="hidden" name="ord_ship_no" value=" ">
<input type="hidden" name="mem_account" value="<%=ordVO.getMem_account()%>">
</FORM>


<script src="https://code.jquery.com/jquery.js"></script>
<!-- <script src="https://code.jquery.com/jquery-3.2.1.js" -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>
</html>
