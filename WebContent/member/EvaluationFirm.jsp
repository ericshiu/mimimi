<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.postpartum_care.model.*" import="com.firm_evaluation.model.*"%>
<%@ page import="com.pc_picture.model.*" import="com.member.model.*"  import="com.firm.model.*"%>
<%
	PostpartumCareVO postpartumCareVO = (PostpartumCareVO) session.getAttribute("postpartumCareVO");
	FirmEvaluationVO firmEvaluationVO = (FirmEvaluationVO) request.getAttribute("firmEvaluationVO");
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
		session.setAttribute("pageReq", "/member/Evaluation.jsp");////這行是在存重導路徑，所以要打本頁的網址
%>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

<title>廠商評價表</title>

<link rel="stylesheet"	href="https://cdn.jsdelivr.net/bootstrap.datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css">
	
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/css.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/member/css/Evaluation.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">

<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/bootstrap.datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=request.getContextPath()%>/postpartum_care/js/ApplicationPC.js"></script>
	

</head>

<body>
	<jsp:include page="/front_end/frontTOP.jsp" flush="true"/>

	<c:if test="${not empty errorMsgs}">
		<font color='red'>
			<ul id="errorMsgs" style="display: none;">
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>

	<FORM METHOD="post" ACTION="EVA.do" name="form1" >


		<div class="container">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="imgpclogin"></div>
					<div class="modal-header">

						<h4 class="modal-title" id="myModalLabel"
							style="text-align: center; font-weight: bold; color: #888;">廠商評價表</h4>
					</div>

					<div class="modal-body">


						<!--名稱 -->
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon input-sum">廠商名稱：</div>
							<input type="text" class="form-control" name="fp_name"
								id="fp_name" placeholder="請填寫完整名稱"
								value="<%=(postpartumCareVO == null) ? "123月子中心" : postpartumCareVO.getPc_name()%>" readOnly/>
						</div>

																	
						
						<!--姓名-->
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon input-sum">我的姓名：</div>
							<input type="text" class="form-control" name="mem_name"
								placeholder="請填寫完整名稱"
								value="<%=(userVO == null) ? "王小美" : userVO.getMem_name()%>" />
						</div>	
						
						
											
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon input-radio">本次評價：</div>
							<div class="btn-group" data-toggle="buttons">
								<label
									class="btn btn-default <c:if test="${firmEvaluationVO.fe_type=='good'}">active focus </c:if>">
									<i class="fa fa-thumbs-o-up" aria-hidden="true"></i><input
									type="radio" name="fe_type" value="good"
									<c:if test="${firmEvaluationVO.fe_type=='good'}">checked </c:if> />
									優良
								</label> 
								<label
									class="btn btn-default <c:if test="${firmEvaluationVO.fe_type=='normal'}">active focus </c:if>">
									<i class="fa fa-smile-o" aria-hidden="true"></i><input
									type="radio" name="fe_type" value="normal"
									<c:if test="${firmEvaluationVO.fe_type=='normal'}">checked </c:if> />
									普通
								</label>
								<label
									class="btn btn-default <c:if test="${firmEvaluationVO.fe_type=='bad'}">active focus </c:if>">
									<i class="fa fa-thumbs-o-down" aria-hidden="true"></i><input
									type="radio" name="fe_type" value="bad"
									<c:if test="${firmEvaluationVO.fe_type=='bad'}">checked </c:if> />
									差勁
								</label>							
							</div>
						</div>						
						
					
						


						<!-- 備註 -->
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon input-sum">評價內容：</div>
							<textarea class="form-control" rows="5" name="fe_content"
								style="resize: vertical;"><%=(firmEvaluationVO == null) ? "" : firmEvaluationVO.getFe_content()%> 
						    </textarea>
						</div>



						</div>
						
					<input type="hidden" name="fp_no" value="PC00000025">
					<input type="hidden" name="mem_no" value="MEM0000003">
					<input type="hidden" name="pr_point" value="5">
							<%
								java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());
							%>
					<input type="hidden" name="fe_date" value=<%=date_SQL%>>
					<input type="hidden" name="action" value="insertToPC">
					<div class="input-group" style="text-align: center; width: 100%;">
						<input type="submit" value="送出新增" class="btn btn-info">
					</div>

					</div>


				</div>

			</div>

	</FORM>



	<button type="button" data-toggle="modal" data-target="#errorModal"
		id="errorBtn" style="display: none;">Open Modal</button>
	<div class="modal fade" id="errorModal" role="dialog" style="top: 25%;">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header modal-header-danger" style="height: 60px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">請將表格填好填滿喔!</h4>
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
	


	<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />


</body>
</html>

