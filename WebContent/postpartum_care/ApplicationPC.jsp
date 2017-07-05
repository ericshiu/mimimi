<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.postpartum_care.model.*" import="com.pc_application.model.*"%>
<%@ page import="com.pc_picture.model.*" import="com.member.model.*"  import="com.firm.model.*"%>
<%
	PostpartumCareVO postpartumCareVO = (PostpartumCareVO) session.getAttribute("postpartumCareVO");
	PcApplicationVO pcApplicationVO = (PcApplicationVO) request.getAttribute("pcApplicationVO");
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
		
%>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

<title>產後照護廠商預約表</title>

<link rel="stylesheet"	href="https://cdn.jsdelivr.net/bootstrap.datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css">
	
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/css.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/postpartum_care/css/ApplicationPC.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">


	

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

	<FORM METHOD="post" ACTION="PCA.do" name="form1" >


		<div class="container">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="imgpclogin"></div>
					<div class="modal-header">

						<p class="login-modal-title" id="myModalLabel"
							style="text-align: center; font-weight: bold; color: #888;">產後照護廠商預約表格</p>
					</div>

					<div class="modal-body">


						<!--名稱 -->
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon input-sum">廠商名稱：</div>
							<input type="text" class="form-control" name="pc_name"
								id="pc_name" placeholder="請填寫完整名稱"
								value="<%=(postpartumCareVO == null) ? "好貝貝月子中心" : postpartumCareVO.getPc_name()%>" readOnly/>
						</div>

						<!--類型 -->
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon input-sum">廠商類型：</div>
							<input type="text" class="form-control" name="pc_type"
								id="pc_name" placeholder="請填寫完整名稱"
								value="<%=(postpartumCareVO == null) ? "月子中心" : postpartumCareVO.getPc_type()%>" readOnly/>
						</div>						
	
						<!--積分 -->
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon input-sum">需求積分：</div>
							<input type="text" class="form-control" name="pr_point"
								value="<%=(postpartumCareVO == null) ? "" : postpartumCareVO.getPc_point()%>" readOnly/>
						</div>						
						
						
						<!--預約人姓名-->
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon input-sum">我的姓名：</div>
							<input type="text" class="form-control" name="mem_name"
								id="pc_name" placeholder="請填寫完整名稱"
								value="<%=(userVO == null) ? "王小美" : userVO.getMem_name()%>" />
						</div>						
						
						<!--預約日期-->
						<p>&nbsp&nbsp&nbsp&nbsp※為了方便廠商處理申請案件，預約請在三日前完成，且只能預約二個月內的時間喔!</p>
			               <div class="input-group date" id="datetimepicker1" style="width: 95%;">
			               	   <div class="input-group-addon input-sum">預約日期：</div>
			                   <input type='text' class="form-control" id="datetimeinput"  onblur=getDatetime() />
			                   <span class="input-group-addon" onMouseover=formatDate()>
			                       <span class="glyphicon glyphicon-calendar"></span>
			                   </span>
			            	</div>
			   
				        <script type="text/javascript">
				        function getDatetime(){
 				        	var date= $('#datetimepicker1').find("input").val()+":00";
 				        	if (date.length>4){
			            	document.getElementById("pca_appdate").setAttribute('value',date);
 				        	}
				        }

				        </script>						
						
					
						


						<!-- 備註 -->
						<div class="input-group" style="width: 95%;">
							<div class="input-group-addon input-sum">備註事項：</div>
							<textarea class="form-control" rows="5" name="pca_memo" id="pca_memo" 
								style="resize: vertical;"><%=(pcApplicationVO == null) ? "" : pcApplicationVO.getPca_memo()%></textarea>
						</div>



						</div>
						
					<input type='hidden' name="pca_appdate" id="pca_appdate">
					<input type="hidden" name="pc_no" value="<%=postpartumCareVO.getPc_no()%>">
					<input type="hidden" name="pr_type" value="預約參觀/試吃">
					<input type="hidden" name="pr_content" value="預約廠商:<%=postpartumCareVO.getPc_name()%>">
					<input type="hidden" name="pc_email" value="<%=postpartumCareVO.getPc_email()%>">
					<input type="hidden" name="mem_no" value="<%=userVO.getMem_no()%>">
							<%
								java.sql.Date date_SQL = new java.sql.Date(System.currentTimeMillis());
							%>
					<input type="hidden" name="pca_date" value=<%=date_SQL%>>
					<input type="hidden" name="pca_status" value="0">
					<input type="hidden" name="action" value="insert">
					<div class="input-group" style="text-align: center; width: 100%;">
						<input type="submit" value="送出新增" class="btn btn-info">
					</div>		
					<div class="input-group" style="text-align: right; width: 90%;">
						<input type="button" value="寶寶來幫你" class="btn btn-success" onClick="babyHelper()">
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
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/bootstrap.datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=request.getContextPath()%>/postpartum_care/js/ApplicationPC.js"></script>

</body>
</html>

