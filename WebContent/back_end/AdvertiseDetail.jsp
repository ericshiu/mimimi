<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.advertise.model.*"%>
<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>
<%
	AdvertiseVO advVO = (AdvertiseVO) request.getAttribute("advVO");
session.setAttribute("pageReq", "/back_end/AdvertiseDetail.jsp");
%>

<html>
<head>
<title>廣告明細</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
</head>
<style>
		body{
				margin:0px;
				padding:0px;

				background:#fff url("img/backbaby1.jpg") center center fixed no-repeat;
				background-size: cover;　

			}     
</style>	
<style>

.modal-header-info {
    color:#fff;
    padding:9px 15px;
    border-bottom:1px solid #eee;
    background-color: #5bc0de;
    -webkit-border-top-left-radius: 5px;
    -webkit-border-top-right-radius: 5px;
    -moz-border-radius-topleft: 5px;
    -moz-border-radius-topright: 5px;
     border-top-left-radius: 5px;
     border-top-right-radius: 5px;
}
</style>
<script src="js/AdvertiseDetail.js"></script>
</head>
<body>
<jsp:include page="/back_end/ManagerSelect.jsp" flush="true" />


<!-- 	登入錯誤訊息視窗 -->
	<c:if test="${not empty errorMsgs}">
			<font color='red' style="display:none;">請修正以下錯誤:
				<ul id="errormessage" >
					<c:forEach var="message" items="${errorMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</font>
		</c:if>
	
	<button type="button" data-toggle="modal" data-target="#errorModal"
		id="errorBtn" style="display:none;">Open Modal</button>
	<div class="modal fade" id="errorModal" role="dialog" style="top: 25%;">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header modal-header-info" style="height: 60px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" style="color:#555;">審核訊息</h4>
				</div>
				<div class="modal-body">
					<p id="errorMsg" style="font-size: 16px;"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>
	
<!-- 	錯誤訊息視窗結束 -->
















<jsp:useBean id="firSvc" scope="page" class="com.firm.model.FirmService" />
<div class="col-xs-12  col-lg-10 right" style="height:100%;font-size:20px;width:83%;margin-top:50px;">

	<div class="panel panel-success" style="width: 100%;">
		<div class="panel-heading" style="height: 3em;">
			<p class="panel-title" style="text-align: center; font-size: 20px;">廣告明細</p>
		</div>

		<table class="table table-hover" style="text-align: center;">
			<thead>
				<tr>

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

				</tr>
			</thead>
			<tbody>

				<tr align='center' valign='middle'>
						<td>
							<FORM METHOD="post" ACTION="adv.do">
								<Button type="submit" class="btn btn-warning" value="">檢視</Button>
								<input type="hidden" name="adv_no" value="${advVO.adv_no}">

								<input type="hidden" name="action" value="advDetail">
							</FORM>
						</td>
					<td>${advVO.adv_no}</td>
					<td>${advVO.fir_no}</td>
					<td>${firSvc.getOneFir(advVO.fir_no).fir_name}</td>
					<td>${advVO.adv_propose_date}</td>
					<td>${advVO.adv_start}</td>
					<td>${advVO.adv_end}</td>
					<td><c:if test="${advVO.adv_review=='0'}"> 
	 	            	未審核
	               	</c:if> <c:if test="${advVO.adv_review=='1'}"> 
	 	            	通過     
	               	</c:if> <c:if test="${advVO.adv_review=='2'}"> 
	 	            	未通過     
	               	</c:if></td>
					<td>${advVO.adv_review_date}</td>
					<td><c:if test="${advVO.adv_status=='0'}"> 
	 	            	未審核
	               	</c:if> <c:if test="${advVO.adv_status=='1'}"> 
	 	            	上架     
	               	</c:if> <c:if test="${advVO.adv_status=='2'}"> 
	 	            	下架     
	               	</c:if></td>
				</tr>		
			</tbody>

		</table>
		</div>
		<br>
		
<!-- 		廣告標題開始-->
		<div class="panel panel-success" style="width: 100%;">
			<div class="panel-heading" style="height: 45px;">
			<p class="panel-title" style="text-align: center; font-size: 20px;">廣告標題</p>
			</div>
		<table class="table table-hover" style="text-align: center;">
			<thead>
				<tr >
					<td style="text-align: center; font-size: 30px;">
					${advVO.adv_title}
					</td>
				</tr>
			</thead>
		</table>
		
		
		</div>
<!-- 		廣告標題結束 -->


<!-- 		廣告圖片開始-->
		<div class="panel panel-success" style="width: 100%;">
			<div class="panel-heading" style="height: 45px;">
			<p class="panel-title" style="text-align: center; font-size: 20px;">廣告標題</p>
			</div>
		<table class="table table-hover" >
			<thead>
				<tr >
					<td style="text-align: center;">
					<img  src="<%= request.getContextPath() %>/AdvertisePhoto?adv_no=${advVO.adv_no}">
					</td>
				</tr>
			</thead>
			<tfoot>
			<tr>
				<td >
				<FORM METHOD="post" ACTION="adv.do" id="form1" enctype="multipart/form-data">
				<button type="submit" name="adv_pass" id="btnpass1" value="1" class="btn btn-info" >通過</button>
				<button type="submit" name="adv_pass" id="btnpass2" value="2" class="btn btn-info" >未通過</button>
				<button type="submit" name="adv_pass" value="3" class="btn btn-info" >下架</button>
				<input type="hidden" name="adv_no" value="${advVO.adv_no}">
				<input type="hidden" name="fir_no" value="${advVO.fir_no}">
				<input type="hidden" name="adv_propose_date" value="${advVO.adv_propose_date}">
				<input type="hidden" name="adv_start" value="${advVO.adv_start}">
				<input type="hidden" name="adv_end" value="${advVO.adv_end}">
				<input type="hidden" name="adv_review" id="adv_review" value="${advVO.adv_review}">
				<input type="hidden" name="adv_review_date" value="${advVO.adv_review_date}">
				<input type="hidden" name="adv_status" value="${advVO.adv_status}">
				<input type="hidden" name="adv_picture" value="null">
				<input type="hidden" name="adv_title" value="${advVO.adv_title}">
				<input type="hidden" name="action" value="update">
				</FORM>
				</td>
			</tr>
			</tfoot>
		</table>
		
		
		</div>
<!-- 		廣告圖片結束 -->


		
</div>  <!-- 最外bs10 -->


<%-- 				</a> --%>

		

</body>
</html>
<script>
function $id(id){
  return document.getElementById(id);
}

	function init() {

		//錯誤訊息跳出
		if ($id("errormessage")!=null){
			str=$id("errormessage").innerHTML
			$id("errorMsg").innerHTML=str;
			$id("errorBtn").click();
			
		
		}

	}

	window.onload = init;
</script>