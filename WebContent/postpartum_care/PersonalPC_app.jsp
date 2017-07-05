<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.postpartum_care.model.*" import="com.pc_picture.model.*" import="com.pc_application.model.*" import="java.util.*" import="sun.misc.*"%>
<%@ page import="com.member.model.*" import="com.firm.model.*"%>
<%
	PostpartumCareService PCSvc = new PostpartumCareService();
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
	PostpartumCareVO userVO = (PostpartumCareVO) pageContext.getAttribute("userVO"); 
	
	Set<PcApplicationVO> listApps_ByPc_no_review0 = PCSvc.getAppsByPc_no(userVO.getPc_no(),"0");
	Set<PcApplicationVO> listApps_ByPc_no_review1 = PCSvc.getAppsByPc_no(userVO.getPc_no(),"1");
	Set<PcApplicationVO> listApps_ByPc_no_review2 = PCSvc.getAppsByPc_no(userVO.getPc_no(),"2");
	Set<PcApplicationVO> listApps_ByPc_no_review3 = PCSvc.getAppsByPc_no(userVO.getPc_no(),"3");
	
	pageContext.setAttribute("listApps_ByPc_no_review0", listApps_ByPc_no_review0);
	pageContext.setAttribute("listApps_ByPc_no_review1", listApps_ByPc_no_review1);
	pageContext.setAttribute("listApps_ByPc_no_review2", listApps_ByPc_no_review2);
	pageContext.setAttribute("listApps_ByPc_no_review3", listApps_ByPc_no_review3);

	Set<PcPictureVO> pcps = PCSvc.getPCPsByPc_no(userVO.getPc_no());
	PcPictureVO pcp = null;
	if (!pcps.isEmpty()){
    	pcp = pcps.iterator().next();
	}
    pageContext.setAttribute("pcp",pcp);
    
    
	session.setAttribute("pageReq", "/postpartum_care/PersonalPC_app.jsp");//這行是在存重導路徑，所以要打本頁的網址
%>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />

<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>廠商資料維護專區</title>
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!-- 引用icon -->
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- 引用Css -->
<link rel="stylesheet" type="text/css"	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/css.css">		
<script src="https://code.jquery.com/jquery.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>



</head>

<body onunload="disconnect();">

<!-- <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal"> -->
<!--   Launch demo modal -->
<!-- </button> -->

<jsp:include page="/front_end/frontTOP.jsp" flush="true" />
<!-- 看看原來在哪兒 -->
<c:if test="${review_id != null}">
	<input type="hidden" id="review_id_from" value="${review_id }" />
</c:if>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red' style="display:none;">請修正以下錯誤:
	<ul id="errormessage">
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

	<c:if test="${userVO != null}">
		<input type="hidden" id="myPc_name" value="${userVO.pc_name}" />
	</c:if>	

<button type="button"  data-toggle="modal" data-target="#errorModal" id="errorBtn" style="display:none;">Open Modal</button>
<div class="modal fade" id="errorModal" role="dialog" style="top:25%;">
    <div class="modal-dialog modal-sm">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header modal-header-danger" style="height:60px;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <p class="login-modal-title">錯誤訊息</p>
        </div>
        <div class="modal-body">
          <p id="errorMsg" style="font-size:16px;"></p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>

	


	<!-- personal內容開始 -->

	<hr>
	<div class="container" style="border-style:dashed;border-width:3px 3px 3px 3px;padding:0px;">
<!-- 	<div class="row"> -->
		<div class="col-xs-12  col-sm-2 left" style="text-align:center;width:20%;border-width:0px 1px 0px 0px;border-style:solid;">
			<div class="col-xs-12 col-sm-12 left1" style="padding:10px;">
				<img id="pchead" src="<%=request.getContextPath()%>/PC_PictureTransfer?pcp_no=${pcp.pcp_no}" class="img-responsive" style="width:100%;border-radius:50%;">
			</div>
			<div class="col-xs-12 col-sm-12 left2">
				<p id="pcname" > ${userVO.pc_name} <br>歡迎使用預約管理區!
			
				</p>
			</div>
			<div class="col-xs-12 col-sm-12 left4" style="margin-top:30px;">

					<table class="table table-hover table-striped" style="border:3px dashed #FF96A7;">
						<tr><th colspan="2" style="text-align: center;">資訊總覽</th></tr>
						<tr>
							<th style="text-align: center;"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>預約</th>
							<td style="text-align: center;">
							<c:if test="${userVO.pc_point>0}">${userVO.pc_point}</c:if>
							<c:if test="${userVO.pc_point==0}"><i class="fa fa-times" aria-hidden="true" style="color:red"></i></c:if>									
							</td>
						</tr>							
						<tr>
							<th style="text-align: center;"><i class="fa fa-gift" aria-hidden="true"></i>小禮</th>
							<td style="text-align: center;">
							<c:if test="${userVO.pc_gift=='Y'}"><i class="fa fa-check" aria-hidden="true"></i></c:if>
							<c:if test="${userVO.pc_gift=='N'}"><i class="fa fa-times" aria-hidden="true" style="color:red"></i></c:if>
							</td>
						</tr>
						<tr>
							<th style="text-align: center;"><i class="fa fa-usd " aria-hidden="true"></i>折扣</th>
							<td style="text-align: center;">
							<c:if test="${userVO.pc_bonus=='Y'}"><i class="fa fa-check" aria-hidden="true"></i></c:if>
							<c:if test="${userVO.pc_bonus=='N'}"><i class="fa fa-times" aria-hidden="true" style="color:red"></i></c:if>							
							</td>
						</tr>						
						<tr><th colspan="2" style="text-align: center;">我的評價</th></tr>
						<tr>
							<th style="text-align: center;"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i>優良</th>
							<td style="text-align: center;">${userVO.pc_eva_good}</td>
						</tr>
						<tr>
							<th style="text-align: center;"><i class="fa fa-smile-o" aria-hidden="true"></i>普通</th>
							<td style="text-align: center;">${userVO.pc_eva_normal}</td>
						</tr>
						<tr>
							<th style="text-align: center;"><i class="fa fa-thumbs-o-down" aria-hidden="true"></i>差勁</th>
							<td style="text-align: center;">${userVO.pc_eva_bad}</td>
						</tr>			
					</table>
			
			</div>
			<div class="col-xs-12 col-sm-12 left3" style="margin-top:50px; margin-bottom:50px;">

				<p><i class="fa fa-gratipay" aria-hidden="true"></i>貼心小提醒：</p>
				<p>盡快審核每筆預約，讓會員及早做時間規劃!!</p>
				

			</div>
			<!-- left3 -->
		</div>
		<!-- left -->

		<!-- 左邊選單區left結束 -->

<div class="col-xs-12  col-sm-10 right personalright" style="height:100%;width:80%;margin:0px;">
			<!-- 右邊區塊開始right -->
			
	<div id="reviewTab" style="padding-top:20px;padding-right:20px;">	
		<ul class="nav nav-tabs nav-light"  style="background-color: #e3f2fd;">
			<li id="review0_li" class="active">
        	<a  href="#review0" data-toggle="tab">待審核 <span class="badge" style="position:absolute;top:-3px;right:-3px;background:#FF96A7;"><%=listApps_ByPc_no_review0.size()%></span></a>
			</li>
			<li id="review1_li"><a href="#review1" data-toggle="tab">已通過未完成 <span class="badge" style="position:absolute;top:-3px;right:-3px;background:#FF96A7;"><%=listApps_ByPc_no_review1.size()%></span></a>
			</li>
			<li id="review2_li"><a href="#review2" data-toggle="tab">已完成</a>
			</li>
			<li id="review3_li"><a href="#review3" data-toggle="tab">預約已滿未通過</a>
			</li>			
		</ul>

	<div class="tab-content " style="background-color: #e3f2fd;">
	<div class="tab-pane active" id="review0" style="background-color: #e3f2fd;">
<!-- 	table -->
		<div class="panel panel-info" style="margin:20px;">
			<div class="panel-heading">
				<h3 class="panel-title"><%@ include file="pages/page1_app_review0.file" %>	</h3>
			</div>
		
		<table class="table table-hover table-striped" align="center">
			<thead>
			<tr>
				<th>預約編號</th>
				<th>會員姓名</th>
				<th>會員電話</th>
				<th>申請日期</th>
				<th>預約日期</th>
				<th>備註事項</th>
				<th>審核狀態</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="pcApplicationVO" items="${listApps_ByPc_no_review0}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr valign='middle' ${(pcApplicationVO.pca_no==param.pca_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
					<td>${pcApplicationVO.pca_no}</td>
					<c:forEach var="memberVO" items="${memSvc.all}">
                    <c:if test="${pcApplicationVO.mem_no==memberVO.mem_no}">
	                    <td>${memberVO.mem_name}</td>
	                    <td>${memberVO.mem_phone}</td>
                    </c:if>
                	</c:forEach>
					
					<td>${pcApplicationVO.pca_date}</td>
<%-- 					<td>${pcApplicationVO.pca_appdate}</td> --%>
<%-- 					<td><fmt:formatDate value="${pcApplicationVO.pca_appdate}" pattern="yyyy-MM-dd hh:mm:ss aaa"/></td> --%>
					<td>${pcApplicationVO.pca_appdate_format} </td>
					<td>${pcApplicationVO.pca_memo}</td>
					<td>
						<button type="button" class="btn btn-info" data-target="#appReview0" name="${pcApplicationVO.pca_no}"  id="${pcApplicationVO.mem_no}" onclick="showReview0Modal()" >
						  審核
						</button>				

					</td>
				
				</tr>
			</c:forEach>
			</tbody>
		</table>
<%-- 		<%@ include file="pages/page2_app_review0.file" %>	 --%>
		</div>
<!-- 	table結束 -->	
	</div>
	<div class="tab-pane" id="review1">
<!-- 	table -->
		<div class="panel panel-info" style="margin:20px;">
			<div class="panel-heading">
				<h3 class="panel-title"><%@ include file="pages/page1_app_review1.file" %>	</h3>
			</div>
		
		<table class="table table-hover table-striped" align="center">
			<thead>
			<tr>
				<th>預約編號</th>
				<th>會員姓名</th>
				<th>會員電話</th>
				<th>申請日期</th>
				<th>預約日期</th>
				<th>備註事項</th>
				<th>審核狀態</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="pcApplicationVO" items="${listApps_ByPc_no_review1}">
				<tr valign='middle' ${(pcApplicationVO.pca_no==param.pca_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
					<td>${pcApplicationVO.pca_no}</td>
					<c:forEach var="memberVO" items="${memSvc.all}">
                    <c:if test="${pcApplicationVO.mem_no==memberVO.mem_no}">
	                    <td>${memberVO.mem_name}</td>
	                    <td>${memberVO.mem_phone}</td>
                    </c:if>
                	</c:forEach>
					
					<td>${pcApplicationVO.pca_date}</td>
<%-- 					<td>${pcApplicationVO.pca_appdate}</td> --%>
<%-- 					<td><fmt:formatDate value="${pcApplicationVO.pca_appdate}" pattern="yyyy-MM-dd hh:mm:ss aaa"/></td> --%>
					<td>${pcApplicationVO.pca_appdate_format} </td>
					<td>${pcApplicationVO.pca_memo}</td>
					<td>
						<button type="button" class="btn btn-primary" data-target="#appReview1" name="${pcApplicationVO.pca_no}" onclick="showReview1Modal()" >
						  完成
						</button>
						<button type="button" class="btn btn-warning" data-target="#appReport" name="${pcApplicationVO.pca_no}"  onclick="showReportModal()" >
						  檢舉
						</button>											

					</td>
				
				</tr>
			</c:forEach>
			</tbody>
		</table>
<%-- 		<%@ include file="pages/page2_app_review0.file" %>	 --%>
		</div>
<!-- 	table結束 -->	
	</div>
    <div class="tab-pane" id="review2">
<!-- 	table -->
		<div class="panel panel-info" style="margin:20px;">
			<div class="panel-heading">
				<h3 class="panel-title"><%@ include file="pages/page1_app_review2.file" %>	</h3>
			</div>
		
		<table class="table table-hover table-striped" align="center">
			<thead>
			<tr>
				<th>預約編號</th>
				<th>會員姓名</th>
				<th>會員電話</th>
				<th>申請日期</th>
				<th>預約日期</th>
				<th>備註事項</th>
				<th>審核狀態</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="pcApplicationVO" items="${listApps_ByPc_no_review2}">
				<tr valign='middle' ${(pcApplicationVO.pca_no==param.pca_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
					<td>${pcApplicationVO.pca_no}</td>
					<c:forEach var="memberVO" items="${memSvc.all}">
                    <c:if test="${pcApplicationVO.mem_no==memberVO.mem_no}">
	                    <td>${memberVO.mem_name}</td>
	                    <td>${memberVO.mem_phone}</td>
                    </c:if>
                	</c:forEach>
					
					<td>${pcApplicationVO.pca_date}</td>
<%-- 					<td>${pcApplicationVO.pca_appdate}</td> --%>
<%-- 					<td><fmt:formatDate value="${pcApplicationVO.pca_appdate}" pattern="yyyy-MM-dd hh:mm:ss aaa"/></td> --%>
					<td>${pcApplicationVO.pca_appdate_format} </td>
					<td>${pcApplicationVO.pca_memo}</td>
					<td>
						<button type="button" class="btn btn-success" disabled="disabled">
						  已完成
						</button>				
					</td>
				
				</tr>
			</c:forEach>
			</tbody>
		</table>
<%-- 		<%@ include file="pages/page2_app_review0.file" %>	 --%>
		</div>
<!-- 	table結束 -->	
	</div>
    <div class="tab-pane" id="review3">
<!-- 	table -->
		<div class="panel panel-info" style="margin:20px;">
			<div class="panel-heading">
				<h3 class="panel-title"><%@ include file="pages/page1_app_review3.file" %>	</h3>
			</div>
		
		<table class="table table-hover table-striped" align="center">
			<thead>
			<tr>
				<th>預約編號</th>
				<th>會員姓名</th>
				<th>會員電話</th>
				<th>申請日期</th>
				<th>預約日期</th>
				<th>備註事項</th>
				<th>審核狀態</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="pcApplicationVO" items="${listApps_ByPc_no_review3}">
				<tr valign='middle' ${(pcApplicationVO.pca_no==param.pca_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
					<td>${pcApplicationVO.pca_no}</td>
					<c:forEach var="memberVO" items="${memSvc.all}">
                    <c:if test="${pcApplicationVO.mem_no==memberVO.mem_no}">
	                    <td>${memberVO.mem_name}</td>
	                    <td>${memberVO.mem_phone}</td>
                    </c:if>
                	</c:forEach>
					
					<td>${pcApplicationVO.pca_date}</td>
<%-- 					<td>${pcApplicationVO.pca_appdate}</td> --%>
<%-- 					<td><fmt:formatDate value="${pcApplicationVO.pca_appdate}" pattern="yyyy-MM-dd hh:mm:ss aaa"/></td> --%>
					<td>${pcApplicationVO.pca_appdate_format} </td>
					<td>${pcApplicationVO.pca_memo}</td>
					<td>
						<button type="button" class="btn btn-danger" disabled="disabled" >
						  已拒絕
						</button>				

					</td>
				
				</tr>
			</c:forEach>
			</tbody>
		</table>
<%-- 		<%@ include file="pages/page2_app_review0.file" %>	 --%>
		</div>
<!-- 	table結束 -->	
	</div>
		
		</div>
  </div>	
			
					    		 	     			




</div>
			<!-- 右邊區塊結束right -->

<!-- </div> -->
</div>

<!-- Modal 修改(0)-->
<div class="modal fade" id="appReview0" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document" style="margin-top:200px;">
  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postpartum_care/PCA.do">
    <div class="modal-content">
<!--       <div class="modal-header"> -->
<!--         <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> -->
<!--         <h4 class="modal-title" >新增照片</h4> -->
<!--       </div> -->
      <div class="modal-body"  align="center">
		<table class="table table-hover table-striped" align="center">
			<tr>
				<th colspan="2">預約申請審核</th>
			</tr>			
			<tr>
				<td>請選擇審核結果:</td>
				<td>
				<div><input type="radio" name="pca_status" value="1"/> 通過</div>
				<div><input type="radio" name="pca_status" value="3"/> 該日預約已滿</div>				
				</td>
			</tr>		
		</table>		
      </div>
      <div class="modal-footer">
 		<%
			java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());
		%>
		<input type="hidden" name="pca_review_date" value=<%=date_SQL%>>     
      	<input type="hidden" name="pc_no" value="${userVO.pc_no}">  
      	<input type="hidden" name="pca_no" id="review0_pca_no" value="">  	
      	<input type="hidden" name="action" value="update">
      	<input type="hidden" name="review_id" value="review0">
      	<input type="hidden" id="review0_mem_no" value="">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="submit" class="btn btn-primary" onclick="clickReview();" >確定</button>
      
      </div>
    </div>
     </FORM> 
  </div>
</div>
<!-- model結束 -->
<!-- Modal 完成(1)-->
<div class="modal fade" id="appReview1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document" style="margin-top:200px;">
  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postpartum_care/PCA.do">
    <div class="modal-content">
<!--       <div class="modal-header"> -->
<!--         <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> -->
<!--         <h4 class="modal-title" >新增照片</h4> -->
<!--       </div> -->
      <div class="modal-body"  align="center">
		<table class="table table-hover table-striped" align="center">
			<tr>
				<th>預約完成確認</th>
			</tr>			
			<tr>
				<td>確定案件已完成</td>
			</tr>		
		</table>		
      </div>
      <div class="modal-footer">
  		<input type="hidden" name="pca_review_date" value=<%=date_SQL%>> 
      	<input type="hidden" name="pc_no" value="${userVO.pc_no}">  
      	<input type="hidden" name="pca_no" id="review1_pca_no" value="">
      	<input type="hidden" name="pca_status" value="2">   	
      	<input type="hidden" name="review_id" value="review1">
      	<input type="hidden" name="action" value="update">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="submit" class="btn btn-primary" >確定</button>
      
      </div>
    </div>
     </FORM> 
  </div>
</div>
<!-- model結束 -->
<!-- Modal 檢舉(1)-->
<div class="modal fade" id="appReport" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document" style="margin-top:200px;">
  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postpartum_care/PCA.do">
    <div class="modal-content">
<!--       <div class="modal-header"> -->
<!--         <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> -->
<!--         <h4 class="modal-title" >新增照片</h4> -->
<!--       </div> -->
      <div class="modal-body"  align="center">
		<table class="table table-hover table-striped" align="center">
			<tr>
				<th colspan="2">檢舉會員</th>
			</tr>			
			<tr>
				<td>會員姓名:</td>
				<td id="report_mem_name">
			
				</td>
			</tr>
			<tr>
				<td>檢舉類別:</td>
				<td>
					<select name="pcr_type">
						<option value="未履約">預約未到</option>
						<option value="遲到">遲到</option>
						<option value="禮儀不佳">禮儀不佳</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>檢舉內容:</td>
				<td>
					<textarea name="pcr_content" rows="5" style="resize: vertical;">請詳細描述案件過程，以利管理員審核</textarea>
				</td>
			</tr>										
		</table>		
      </div>
      <div class="modal-footer">

		<input type="hidden" name="pcr_date" value=<%=date_SQL%>>     
      	<input type="hidden" name="pc_no" value="${userVO.pc_no}">  
      	<input type="hidden" name="pca_no" id="report_pca_no" value=""> 	
      	<input type="hidden" name="pca_status" value="2">
      	<input type="hidden" name="pcr_status" value="0">       	 	      	
      	<input type="hidden" name="action" value="updateWithReport">     	
      	<input type="hidden" name="review_id" value="review1">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="submit" class="btn btn-primary" >送出檢舉</button>
      
      </div>
    </div>
     </FORM> 
  </div>
</div>
<!-- model結束 -->
	<!-- personal內容結束 -->

	<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>
</html>
<script>
var MyPoint = "/OrdServer";
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
var webSocket;

function connect() {
	webSocket = new WebSocket(endPointURL);
	
	webSocket.onopen = function(event) {
		
	};

	webSocket.onmessage = function(event) {
   
	};

} 

function clickReview() {	
	var mem_no = document.getElementById("review0_mem_no").value;
	var pc_title = "來自 "+document.getElementById("myPc_name").value+" 的訊息";
	var message = "親愛的會員您好，我們已對預約申請做出審核，請至會員專區查看審核結果!!"
	var jsonObj = {"type" : "application", "mem_no" : mem_no, "pc_title" : pc_title, "message" : message};

	webSocket.send(JSON.stringify(jsonObj));
}


function disconnect () {
	webSocket.close();
}

function $id(id){
	  return document.getElementById(id);
}

function showReview0Modal(e){
	var x = event.target;
	var pca_no = x.name.toString();
	var review_mem_no = x.id.toString();
	
	$('#review0_pca_no').val(pca_no);
	
	$('#review0_mem_no').val(review_mem_no);
	
	$('#appReview0').modal('show');

}

function showReview1Modal(e){
	var x = event.target;
	var pca_no = x.name.toString();
	
	$('#review1_pca_no').val(pca_no);
	
	$('#appReview1').modal('show');

}

function showReportModal(e){
	var x = event.target;
	var pca_no = x.name.toString();

	var mem_name = x.parentNode.parentNode.childNodes[4].innerText;
	//alert(mem_name);

	$('#report_pca_no').val(pca_no);
	$id('report_mem_name').innerText=mem_name;
	
	$('#appReport').modal('show');

}

window.onload = function (){
        
    if ($id("review_id_from")) {    
		var review_id=$id("review_id_from").value;
		var review_id_li=review_id+"_li";
		
		
		$id("review0").classList.remove("active");
		$id(review_id).classList.add("active");
		
		$id("review0_li").classList.remove("active");
		$id(review_id_li).classList.add("active");		
		
	}

    connect();
     
}
</script>
