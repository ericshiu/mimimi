<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ page import="java.util.* , com.member.model.* , com.orders.model.*,com.firm.model.*,com.postpartum_care.model.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<script src="<%= request.getContextPath() %>/orders/js/webSocket.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">
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
	MemberVO userVO = (MemberVO) pageContext.getAttribute("userVO");
	session.setAttribute("pageReq", "/member/memOrd.jsp");
	
	OrdersService ordSvc = new OrdersService();
    List<OrdersVO> list = ordSvc.getAllOrd();
    pageContext.setAttribute("list",list);
%>

<%
    session.removeAttribute("buylist");
%>

<%
	Map<String, String> map = new LinkedHashMap<String, String>();
	map.put("01", "確認訂單");
	map.put("02", "修改訂單中");
	map.put("03", "出貨中");
	map.put("04", "完成出貨");
	map.put("05", "已收到商品");
	pageContext.setAttribute("map", map);
%>

<% int i=0;%>
<style>
.content{
    padding:20px;
    background:#fff;
    width:100px;
    height:100px;
	}
.od{
	display: none;
}
.orderTop{
	margin-top:30px;
}
th{
	text-align:center;
}
.modal-content{
	top:200px;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>會員訂單</title>
</head>
<body onload="connect();" onunload="disconnect();">
<input type="hidden" id="statusNow">
<jsp:include page="../front_end/frontTOP.jsp" flush="true"/>

<div class="container" style="border-style:dashed;border-width:3px 3px 3px 3px;padding:0px;">

		<div class="col-xs-12  col-lg-2 left" style="text-align:center;width:20%;">
			<jsp:include page="/member/MySelect.jsp" flush="true"/>
		</div>

	<div class="col-xs-12  col-lg-10 right personalright" style="height: 100%; width: 80%; margin: 0px; border-width: 0px 0px 0px 1px; border-style: solid;background-color:#fff;">
		<br>
		<div class="panel panel-info">
		<table class="table table-hover table-striped" align="center">
			<div class="panel-heading" align="center">
				會員訂單
			</div>
			<tr>
				<th>訂購日期</th>
				<th>收件者姓名</th>
				<th>收件者地址</th>
				<th>收件者電話</th>
				<th>訂單狀態</th>
				<th>訂單明細</th>
				<th>更改收貨資訊</th>
				<th>給予評價</th>
			</tr>
			
			<c:forEach var="order" items="${list}">
				<c:if test="${order.mem_no==userVO.mem_no}">
				
				<tr align='center' valign='middle'>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${order.ord_date}" /></td>
					<td>${order.ord_rec_name}</td>
					<td>${order.ord_rec_address}</td>
					<td>${order.ord_rec_phone}</td>
					<td class="memOrdStatus">${map[order.ord_status]}</td>
					<td><div><input type="button" id="<%= i%>" class="expand btn btn-info" value="訂單明細"></div></td>								
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orders/ord.do">
						<input type="submit" class="changeOrd btn btn-success" value="修改收件資訊"
						<c:if test="${order.ord_status!='01'}">disabled="disabled"</c:if> onclick="clickBtn(<%= i%>)">
						<input type="hidden" id="ord_no<%= i%>" value="${order.ord_no}">
						<input type="hidden" name="ord_no" value="${order.ord_no}">
						<input type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
					<td>
						<button type="button" class="finishOrd btn btn-default" data-toggle="modal" 
						data-target="#evaModal<%= i%>" <c:if test="${order.ord_status!='04'}">disabled="disabled"</c:if>>評價</button>
					</td>
				</tr>
				
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orders/ord.do">
				<div class="modal fade" id="evaModal<%= i%>" role="dialog">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<span class="modal-title" style="font-size:20px;">給予評價</span>
							</div>
							<div class="modal-body">
								<textarea class="form-control" rows="5" id="comment" name="fe_content"></textarea>
								<div class="btn-group" data-toggle="buttons">
									<label class="btn btn-default">
										<i class="fa fa-thumbs-o-up" aria-hidden="true"></i><input
										type="radio" name="fe_type" value="good" />
										正評
									</label> 
									<label class="btn btn-default">
										<i class="fa fa-smile-o" aria-hidden="true"></i><input
										type="radio" name="fe_type" value="normal" />
										普通
									</label>
									<label class="btn btn-default ">
										<i class="fa fa-thumbs-o-down" aria-hidden="true"></i><input
										type="radio" name="fe_type" value="bad" />
										負評
									</label>							
								</div>
							</div>
							<div class="modal-footer">
								<input type="submit" value="給予評價" class="btn btn-default" >
								<input type="hidden" name="ord_no" value="${order.ord_no}">
								<input type="hidden" name="mem_no" value="${order.mem_no}">
								<input type="hidden" name="action" value="forEvaluation">
							</div>
						</div>
					</div>
				</div>
				</FORM>
				
				<tr class="od od<%= i++%>" style="background-color:	#E0FFFF">
					<td colspan="8">
					
						<table width="90%" class="table table-hover table-striped" align="center">
							<tr>
								<td width="3%"></td>
								<td>商品名稱</td>
								<td width="10%">商品價格</td>
								<td width="10%">購買數量</td>
								<td></td>
								<td></td>
							</tr>
					
							<jsp:useBean id="odSvc" scope="page" class="com.order_detail.model.OrderDetailService" />
							<c:forEach var="odVO" items="${odSvc.allOD}">
								<c:if test="${odVO.ord_no==order.ord_no}">	
									<jsp:useBean id="proSvc" scope="page" class="com.product.model.ProductService" />
									<c:forEach var="proVO" items="${proSvc.all}">
									<c:if test="${proVO.pro_no==odVO.pro_no}">
										<tr>
											<td width="3%"></td>
											<td >${proVO.pro_name}</td>
											<td width="10%">${proVO.pro_price}</td>
											<td width="10%">${odVO.pro_quantity}</td>
											<td></td>
											<td></td>
										<tr>
									</c:if>
									</c:forEach>
								</c:if>
           					</c:forEach>
           								<tr>
           									<td width="3%"></td>
           									<td>訂單編號 : <span class="memOrdNo">${order.ord_no}</span></td>
           									<td>訂單總額 : ${order.ord_sum}</td>
           									<td></td>
           									<td>貨運單號 : <span class="ordShipNo">${order.ord_ship_no}</span></td>
           									<td></td>
           								</tr>
						</table>
					</td>
				</tr>		
<input type="hidden" value="${order.ord_no}" id="ordNo">
<input type="hidden" value="${order.ord_status}" id="ordStatus">
				</c:if>
			</c:forEach>  	
		</table>
		</div>
	</div>
</div>
<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>
</html>

<script>
	$(function() {
		$(".expand").click(function() {					
			$('.od'+$(this).attr('id')).slideToggle();			
		});
	});
</script>