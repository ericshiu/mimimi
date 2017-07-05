<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.postpartum_care.model.*" import="com.pc_picture.model.*" import="com.firm_evaluation.model.*" import="java.util.*" import="sun.misc.*"%>
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
    	
    	FirmEvaluationService FESvc = new FirmEvaluationService();
    	
    	Set<FirmEvaluationVO> listEVAs_ByPc_no_good = FESvc.getAllByFirmAndType(userVO.getPc_no(),"good");
    	Set<FirmEvaluationVO> listEVAs_ByPc_no_normal = FESvc.getAllByFirmAndType(userVO.getPc_no(),"normal");
    	Set<FirmEvaluationVO> listEVAs_ByPc_no_bad = FESvc.getAllByFirmAndType(userVO.getPc_no(),"bad");

    	
    	pageContext.setAttribute("listEVAs_ByPc_no_good", listEVAs_ByPc_no_good);
    	pageContext.setAttribute("listEVAs_ByPc_no_normal", listEVAs_ByPc_no_normal);
    	pageContext.setAttribute("listEVAs_ByPc_no_bad", listEVAs_ByPc_no_bad);


    	Set<PcPictureVO> pcps = PCSvc.getPCPsByPc_no(userVO.getPc_no());
    	PcPictureVO pcp = null;
    	if (!pcps.isEmpty()){
          pcp = pcps.iterator().next();
    	}
        pageContext.setAttribute("pcp",pcp);   	
    	

	session.setAttribute("pageReq", "/postpartum_care/PersonalPC_report.jsp");//這行是在存重導路徑，所以要打本頁的網址
%>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />

<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>廠商評價專區</title>
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!-- 引用icon -->
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<!-- 引用Css -->
<link rel="stylesheet" type="text/css"	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/css.css">	
<script src="https://code.jquery.com/jquery.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>



</head>

<body>

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
		<div class="col-xs-12  col-sm-2 left" style="height:100%;width:20%;text-align:center;border-width:0px 1px 0px 0px;border-style:solid;">
			<div class="col-xs-12 col-sm-12 left1" style="padding:10px;">
				<img id="pchead" src="<%=request.getContextPath()%>/PC_PictureTransfer?pcp_no=${pcp.pcp_no}" class="img-responsive" style="width:100%;border-radius:50%;">
			</div>
			<div class="col-xs-12 col-sm-12 left2">
				<p id="pcname" > ${userVO.pc_name} <br>歡迎查看評價專區!
			
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
			<div class="col-xs-12 col-sm-12 left3" style="margin-top:50px; margin-bottom:100px;">

				<p><i class="fa fa-gratipay" aria-hidden="true"></i>貼心小提醒：</p>
				<p>每次的會員預約，都是新增優良評價的好機會唷!!</p>
				

			</div>

			<!-- left3 -->
		</div>
		<!-- left -->

		<!-- 左邊選單區left結束 -->

<div class="col-xs-12  col-sm-10 right personalright" style="width:80%;height:auto;margin:0px;">
			<!-- 右邊區塊開始right -->
			
	<div id="evaTab" style="padding-top:20px;padding-right:20px;">	
		<ul class="nav nav-tabs nav-light"  style="background-color: #e3f2fd;">
			<li id="good_li" class="active">
        	<a  href="#good" data-toggle="tab"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i> 優良 <span class="badge" style="background:#FF96A7;"><%=listEVAs_ByPc_no_good.size()%></span></a>
			</li>
			<li id="normal_li"><a href="#normal" data-toggle="tab"><i class="fa fa-smile-o" aria-hidden="true"></i> 普通 <span class="badge" style="background:#5F94BF;"><%=listEVAs_ByPc_no_normal.size()%></span></a>
			</li>
			<li id="bad_li"><a href="#bad" data-toggle="tab"><i class="fa fa-thumbs-o-down" aria-hidden="true"></i> 差勁 <span class="badge" style="background:#BA9D75;"><%=listEVAs_ByPc_no_bad.size()%></span></a>
			</li>			
		</ul>

	<div class="tab-content " style="background-color: #e3f2fd;">
	<div class="tab-pane active" id="good" style="background-color: #e3f2fd;">
<!-- 	table -->
		<div class="panel panel-info" style="margin:20px;">
			<div class="panel-heading">
				<h3 class="panel-title"><%@ include file="pages/page1_eva_good.file" %>	</h3>
			</div>
		
		<table class="table table-hover table-striped" align="center">
			<thead>
			<tr>
				<th>評價編號</th>
				<th>會員姓名</th>
				<th>評價日期</th>
				<th>評價類別</th>
				<th>評價內容</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="firmEvaluationVO" items="${listEVAs_ByPc_no_good}">
				<tr valign='middle' ${(firmEvaluationVO.fe_no==param.pcr_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
					<td>${firmEvaluationVO.fe_no}</td>
					<c:forEach var="memberVO" items="${memSvc.all}">
                    <c:if test="${firmEvaluationVO.mem_no==memberVO.mem_no}">
	                    <td>${memberVO.mem_name}</td>
                    </c:if>
                	</c:forEach>
					
					<td>${firmEvaluationVO.fe_date}</td>
<%-- 					<td>${pcApplicationVO.pca_appdate}</td> --%>
<%-- 					<td><fmt:formatDate value="${pcApplicationVO.pca_appdate}" pattern="yyyy-MM-dd hh:mm:ss aaa"/></td> --%>
					<td>優良</td>
					<td>${firmEvaluationVO.fe_content}</td>
				
				</tr>
			</c:forEach>
			</tbody>
		</table>
<%-- 		<%@ include file="pages/page2_report0.file" %>	 --%>
		</div>
<!-- 	table結束 -->	
	</div>
	<div class="tab-pane" id="normal">
<!-- 	table -->
		<div class="panel panel-info" style="margin:20px;">
			<div class="panel-heading">
				<h3 class="panel-title"><%@ include file="pages/page1_eva_normal.file" %>	</h3>
			</div>
		
		<table class="table table-hover table-striped" align="center">
			<thead>
			<tr>
				<th>評價編號</th>
				<th>會員姓名</th>
				<th>評價日期</th>
				<th>評價類別</th>
				<th>評價內容</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="firmEvaluationVO" items="${listEVAs_ByPc_no_normal}">
				<tr valign='middle' ${(firmEvaluationVO.fe_no==param.fe_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
					<td>${firmEvaluationVO.fe_no}</td>
					<c:forEach var="memberVO" items="${memSvc.all}">
                    <c:if test="${firmEvaluationVO.mem_no==memberVO.mem_no}">
	                    <td>${memberVO.mem_name}</td>
                    </c:if>
                	</c:forEach>
					
					<td>${firmEvaluationVO.fe_date}</td>
<%-- 					<td>${pcApplicationVO.pca_appdate}</td> --%>
<%-- 					<td><fmt:formatDate value="${pcApplicationVO.pca_appdate}" pattern="yyyy-MM-dd hh:mm:ss aaa"/></td> --%>
					<td>普通</td>
					<td>${firmEvaluationVO.fe_content}</td>
				
				</tr>
			</c:forEach>
			</tbody>
		</table>
<%-- 		<%@ include file="pages/page2_report1.file" %>	 --%>
		</div>
<!-- 	table結束 -->	
	</div>
    <div class="tab-pane" id="bad">
<!-- 	table -->
		<div class="panel panel-info" style="margin:20px;">
			<div class="panel-heading">
				<h3 class="panel-title"><%@ include file="pages/page1_eva_bad.file" %>	</h3>
			</div>
		
		<table class="table table-hover table-striped" align="center">
			<thead>
			<tr>
				<th>評價編號</th>
				<th>會員姓名</th>
				<th>評價日期</th>
				<th>評價類別</th>
				<th>評價內容</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="firmEvaluationVO" items="${listEVAs_ByPc_no_bad}">
				<tr valign='middle' ${(firmEvaluationVO.fe_no==param.fe_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
					<td>${firmEvaluationVO.fe_no}</td>
					<c:forEach var="memberVO" items="${memSvc.all}">
                    <c:if test="${firmEvaluationVO.mem_no==memberVO.mem_no}">
	                    <td>${memberVO.mem_name}</td>
                    </c:if>
                	</c:forEach>
					
					<td>${firmEvaluationVO.fe_date}</td>
<%-- 					<td>${pcApplicationVO.pca_appdate}</td> --%>
<%-- 					<td><fmt:formatDate value="${pcApplicationVO.pca_appdate}" pattern="yyyy-MM-dd hh:mm:ss aaa"/></td> --%>
					<td>差勁</td>
					<td>${firmEvaluationVO.fe_content}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
<%-- 		<%@ include file="pages/page2_report2.file" %>	 --%>
		</div>
<!-- 	table結束 -->	
	</div>

		
		</div>
  </div>	
			
					    		 	     			




</div>
			<!-- 右邊區塊結束right -->

<!-- </div> -->
</div>


	<!-- personal內容結束 -->

	<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>
</html>
<script>

</script>
