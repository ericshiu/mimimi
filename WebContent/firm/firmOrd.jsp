<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>     
<%@ page import="java.util.* ,com.member.model.*, com.firm.model.* , com.orders.model.*, com.product.model.*, com.order_detail.model.*,com.postpartum_care.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
	FirmVO userVO = (FirmVO) pageContext.getAttribute("userVO");
	session.setAttribute("pageReq", "/firm/firmOrd.jsp");
	
	OrdersService orderSvc = new OrdersService();
	List<OrdersVO> ordVO = orderSvc.getAllOrd();
	List<ProductVO> orderList = orderSvc.getOrdByFir(userVO.getFir_no());
	List<ProductVO> ordByFirList = orderSvc.getOneOrdByFir(userVO.getFir_no());
	pageContext.setAttribute("ordVO",ordVO);
	pageContext.setAttribute("orderList",orderList);
	pageContext.setAttribute("ordByFirList",ordByFirList);
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
th{
	text-align:center;
}
.modal-content{
	top:200px;
}


</style>

<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>廠商訂單</title>
</head>
<body onload="connect();" onunload="disconnect();">
<input type="hidden" id="statusNow">
<jsp:include page="../front_end/frontTOP.jsp" flush="true"/>


<div class="container" style="border-style:dashed;border-width:3px 3px 3px 3px;padding:0px;">

		<div class="col-xs-12  col-lg-2 left" style="text-align:center;width:20%;">
			<jsp:include page="/member/MySelect.jsp" flush="true"/>
		</div>

		<div class="col-xs-12  col-lg-10 right personalright" style="height: 100%; width: 80%; margin: 0px; border-width: 0px 0px 0px 1px; border-style: solid;background-color:#08ffff;">
		<div class="panel panel-success panel-group">
			<div class="panel-heading" align="center">
				廠商訂單
			</div>
		<table class="table table-hover table-striped" align="center">
		<tr align='center' valign='middle'>
			<th>訂購日期</th>
			<th>訂單總價</th>
			<th>收件者姓名</th>
			<th>收件者地址</th>
			<th>收件者電話</th>
			<th>訂單明細</th>
			<th>狀態</th>
			<th>準備出貨</th>
			<th>完成出貨</th>
		</tr>
		<c:forEach var="ordVO" items="${ordVO}">
			<c:forEach var="ordListVO" items="${ordByFirList}">
				<c:if test="${ordVO.ord_no==ordListVO.ord_no}">
				<tr align='center' valign='middle'>
				
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${ordVO.ord_date}" /></td>
					<td>${ordVO.ord_sum}</td>
					<td>${ordVO.ord_rec_name}</td>
					<td>${ordVO.ord_rec_address}</td>
					<td>${ordVO.ord_rec_phone}</td>
					<td><div><input type="button" id="<%= i%>" class="expand btn btn-success" value="訂單明細"></div></td>
					<td class="ordStatus">${map[ordVO.ord_status]}</td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orders/ord.do">
						<input type="hidden" name="ord_no" value="${ordVO.ord_no}">
						<input type="hidden" name="action" value="PREPARE">
					<td><input type="submit" value="準備出貨" class="prepare btn btn-primary" 
					<c:if test="${ordVO.ord_status!='01'}">disabled="disabled"</c:if> onclick="prepareShip(<%= i%>)"> </td>
						<input type="hidden" id="ord_no<%= i%>" value="${ordVO.ord_no}">
					</FORM>
					<td><input type="button" value="完成出貨" class="finishShip btn btn-warning" data-toggle="modal" 
					data-target="#shipModal<%= i%>" <c:if test="${ordVO.ord_status!='03'}">disabled="disabled"</c:if>></td>
				</tr>
				
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orders/ord.do">
				<div class="modal fade" id="shipModal<%= i%>" role="dialog">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<span class="modal-title" style="font-size:20px;">完成出貨</span>
							</div>
							<div class="modal-body">
								<input type="text" name="ord_ship_no" id="ord_ship_no<%= i%>" placeholder="請輸入貨運單號"></input>
							</div>
							<div class="modal-footer">
								<input type="submit" value="確認出貨" class="finishShipF btn btn-default" onclick="finishShip(<%= i%>)">
								<input type="hidden" id="ord_no<%= i%>" name="ord_no" value="${ordVO.ord_no}">
								<input type="hidden" name="action" value="finishOrd">
							</div>
						</div>
					</div>
				</div>
				</FORM>
				
				<tr class="od od<%= i++%>" style="background-color:#5f94bf">
					<td colspan="10">
						<table width="90%" class="table table-hover table-striped" align="center">
							<tr>
								<td>訂單編號 : <span class="ordNo">${ordVO.ord_no}</span></td>
								<td>出貨日期 : ${ordVO.ord_ship_date}</td>
								<td>貨運編號 : ${ordVO.ord_ship_no}</td>
								<td></td>
								<td></td>
								
							</tr>
							<tr>
								<td width="18%">產品編號</td>
								<td width="18%">商品名稱</td>
								<td width="20%">商品價格</td>
								<td width="44%">購買數量</td>
								<td></td>
								
							</tr>
							
							<c:forEach var="orderVO" items="${orderList}">
								<c:if test="${ordListVO.ord_no==orderVO.ord_no}">
									<tr>
										<td width="18%">${orderVO.pro_no}</td>
										<td width="18%">${orderVO.pro_name}</td>
										<td width="20%">${orderVO.pro_price}</td>
										<td width="44%">${orderVO.pro_quantity}</td>
										<td></td>
										
									</tr>
								</c:if>
							</c:forEach>
						</table>
					</td>
				</tr>
<input type="hidden" value="${ordVO.ord_no}" id="ordNo">
<input type="hidden" value="${order.ord_status}" id="ordStatus">		
				</c:if>
			</c:forEach>
		</c:forEach>
		</table>
<input type="hidden" value="<%= i-1 %>" id="index">
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
