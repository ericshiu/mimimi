<%@page import="java.io.BufferedInputStream"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product_picture.model.*"%>
<%@ page import="com.product.model.* ,com.firm.model.*,com.member.model.*,com.postpartum_care.model.*"%>


<%
String userType = (String) session.getAttribute("userType"); 

if (userType != null){
	if (userType.equals("PC")){
		PostpartumCareVO userVO = (PostpartumCareVO) session.getAttribute("userVO");
		pageContext.setAttribute("userVO",userVO);
	} else if (userType.equals("Fir")){
		FirmVO userVO = (FirmVO) session.getAttribute("userVO");
		pageContext.setAttribute("userVO",userVO);
	} else if (userType.equals("Mem")){
		MemberVO userVO = (MemberVO) session.getAttribute("userVO");  
		pageContext.setAttribute("userVO",userVO);
	} 
} else {
	userType = "Non";
}
	FirmVO userVO = (FirmVO) pageContext.getAttribute("userVO");
	session.setAttribute("pageReq", "/firm/listAllpro.jsp");
%>

<jsp:useBean id="getPicByPro" scope="request" type="java.util.Set" />

<html>
<head>
<title>商品照片</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/RegisterPC.css">
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script src="https://code.jquery.com/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">
</head>
<body bgcolor='white'>
<jsp:include page="/front_end/frontTOP.jsp" flush="true"/>



<div class="container">
<div style="background-color:#dff0d8;height:50px;border-radius:2%;padding-top:2px;padding-left:5px;">
<FORM  METHOD="post" ACTION="<%=request.getContextPath()%>/product_picture/prp.do">
<button type="submit" value="" class="btn btn-warning" style="font-size:20px;">新增圖片</button>
<input type="hidden" name="action"	value="addPic"> 
<c:forEach var="proVO" items="${getPicByPro}">
<input type="hidden" name="pro_no"	value="${proVO.pro_no}">
</c:forEach>
</form>

</div>	
	<br>
	<div class="col-xs-12  col-lg-12" align='center' >
	
	
		<div class="panel panel-success panel-group">
			<div class="panel-heading" align="center">
				廠商訂單
			</div>
		<table class="table table-hover table-striped" align="center" >
		<tr align='center' valign='middle'>		
			<th>商品照片編號</th>
			<th>商品照片</th>
			<th>商品編號</th>
			<th></th>
			<th></th>
		</tr>	
	<c:forEach var="prpVO" items="${getPicByPro}">
		<tr align='center' valign='middle'>
			<td>${prpVO.prp_no}</td>		
			<td>
			<div style="background: #fff url('<%= request.getContextPath() %>/ProductPhoto?prp_no=${prpVO.prp_no}') no-repeat center top; background-size: cover; width: 200px;height:200px;border-radius:20%;box-shadow:2px 2px 2px rgba(80%,80%,80%,0.6),4px 4px 6px rgba(80%,80%,80%,0.4),6px 6px 12px rgba(80%,80%,80%,0.4);">
			</div>
<%-- 			<img src="<%= request.getContextPath() %>/ProductPhoto?prp_no=${prpVO.prp_no}" style="width:300px;border-radius:30%;"> --%>
			</td>
			
			<td>${prpVO.pro_no}</td>

			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_picture/prp.do">
			     <button type="submit" value="" class="btn btn-info">修改</button>
			     <input type="hidden" name="prp_no" value="${prpVO.prp_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_picture/prp.do">
			    <button type="submit" value="" class="btn btn-danger">刪除</button>
			    <input type="hidden" name="prp_no" value="${prpVO.prp_no}">
			    <input type="hidden" name="pro_no" value="${prpVO.pro_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
	
		</table>
		</div>
		
	</div>
	
</div>


<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>
</html>
