<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="compositeQuery" scope="request" type="java.util.List" />
<jsp:useBean id="firSvc" scope="page" class="com.firm.model.FirmService" />
<html>
<head>
<title>複合查詢 - compositeQuery.jsp</title>
</head>
<div class="panel panel-success" style="width: 100%;">
		<div class="panel-heading" style="height: 2em;">
			<p class="panel-title" style="text-align: center; font-size: 20px;">廣告清單</p>
		</div>

	<table class="table table-hover" style="text-align: center;">
		<thead>
			<tr>
				<th>序號</th>
				<th>檢視廣告</th>
				<th>廣告編號</th>
				<th>廠商編號</th>
				<th>廠商名稱</th>
				<th>申請日期</th>
				<th>開始日期</th>
				<th>結束日期</th>
				<th>審核狀態</th>
				<th>審核日期</th>
				<th>廣告狀態</th>
				<th>廣告標題</th>
			</tr>
		</thead>
		<tbody>
			<% int q=0;%>

	<c:forEach var="advVO" items="${compositeQuery}" >
		<tr align='center' valign='middle'>
				<td>
				
				<%q++; %>
				<%=q %>
				</td>
				<td>
				  <FORM METHOD="post" ACTION="adv.do">
				    <Button type="submit" class="btn btn-warning" value="">檢視</Button>
				    <input type="hidden" name="adv_no" value="${advVO.adv_no}">
				   
				    <input type="hidden" name="action"value="advDetail"></FORM>
				</td>
				<td>${advVO.adv_no}</td>
				<td>${advVO.fir_no}</td>
				<td>
					 ${firSvc.getOneFir(advVO.fir_no).fir_name}
				</td>
				<td>${advVO.adv_propose_date}</td>
				<td>${advVO.adv_start}</td>
				<td>${advVO.adv_end}</td>
				<td>
					<c:if test="${advVO.adv_review=='0'}"> 
	 	            	未審核
	               	</c:if> 
	               	<c:if test="${advVO.adv_review=='1'}"> 
	 	            	通過     
	               	</c:if> 
	               	<c:if test="${advVO.adv_review=='2'}"> 
	 	            	未通過     
	               	</c:if> 
				</td>
				<td>${advVO.adv_review_date}</td>
				<td>
					<c:if test="${advVO.adv_status=='0'}"> 
	 	            	未審核
	               	</c:if> 
	               	<c:if test="${advVO.adv_status=='1'}"> 
	 	            	上架     
	               	</c:if> 
	               	<c:if test="${advVO.adv_status=='2'}"> 
	 	            	下架     
	               	</c:if> 
				</td>
				<td>${advVO.adv_title}</td>
				
			</tr>
		</c:forEach>
				
	</tbody>
	
	</table>
</body>
</html>
