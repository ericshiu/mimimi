<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.postpartum_care.model.*"  import="com.pc_picture.model.*" import="java.util.*" import="sun.misc.*"%>
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

Set<PcPictureVO> pcps = PCSvc.getPCPsByPc_no(userVO.getPc_no());
PcPictureVO pcp = null;
if (!pcps.isEmpty()){
	pcp = pcps.iterator().next();
}
pageContext.setAttribute("pcp",pcp);


	session.setAttribute("pageReq", "/postpartum_care/PersonalPC_pic.jsp");//這行是在存重導路徑，所以要打本頁的網址
%>


<%-- 此頁練習採用 EL 的寫法取值 --%>

<jsp:useBean id="listPCPs_ByPc_no" scope="request" type="java.util.Set" />
<%-- <jsp:useBean id="PCSvc" scope="page" class="com.postpartum_care.model.PostpartumCareService" /> --%>
<jsp:useBean id="PCPSvc" scope="page" class="com.pc_picture.model.PcPictureService" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

<title>產後照護廠商照片編輯專區</title>
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/css.css">	
	
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>


</head>
<body bgcolor='white'>

<jsp:include page="/front_end/frontTOP.jsp" flush="true" />
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
	<div class="container" style="border-style:dashed;border-width:3px 3px 3px 3px;padding:15px;">
	<div class="row">
		<div class="col-xs-12  col-sm-2 left" style="text-align:center;border-width:0px 1px 0px 0px;border-style:solid;">
			<div class="col-xs-12 col-sm-12 left1" style="padding:10px;">
				<img id="pxhead" src="<%=request.getContextPath()%>/PC_PictureTransfer?pcp_no=${pcp.pcp_no}"class="img-responsive" style="width:100%;border-radius:50%;">
			</div>
			<div class="col-xs-12 col-sm-12 left2">
				<p id="pcname" > ${userVO.pc_name} <br>歡迎使用照片管理區!
			
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
				<p>照片大小全部一致，畫面才會更美觀喔!!</p>
			</div>
			<!-- left3 -->
		</div>
		<!-- left -->

		<!-- 左邊選單區left結束 -->

<div class="col-xs-12  col-sm-10 " style="margin:0px;">

	
									<div class="input-group" style="text-align:right; width:98%;">
											<input type="button" value="新增照片" name="${userVO.pc_no}" class="btn btn-warning" onclick="showNewModal()">
									</div>

	

		<div class="panel panel-info">
			<div class="panel-heading">
				<h3 class="panel-title"></h3>
			</div>
		
		<table class="table table-hover table-striped" align="center">
			<thead>
			<tr>
				<th>照片編號</th>
				<th>廠商照片</th>
				<th>照片說明</th>
				<th>修改</th>
				<th>刪除</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="pcPictureVO" items="${listPCPs_ByPc_no}" >
				<tr valign='middle' ${(pcPictureVO.pcp_no==param.pcp_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
					<td>${pcPictureVO.pcp_no}</td>
					<td><img src="<%=request.getContextPath()%>/PC_PictureTransfer?pcp_no=${pcPictureVO.pcp_no}" width=200px ></td>
					<td>${pcPictureVO.pcp_summary}</td>
					<td>
						<button type="button" class="btn btn-info" data-target="#myEdit" name="${pcPictureVO.pcp_no}" id="${pcPictureVO.pcp_summary}" onclick="showEditModal()" >
						  修改
						</button>					
<%-- 					    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pc_picture/PCP.do"> --%>
<!-- 					    <input type="submit" value="修改" class="btn btn-info">  -->
<%-- 					    <input type="hidden" name="pcp_no"value="${pcPictureVO.pcp_no}"> --%>
<%-- 					    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--><!-- 目前尚未用到  --> --%>
<!-- 					    <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
					</td>
					<td>
						<button type="button" class="btn btn-warning" data-target="#myDelete" name="${pcPictureVO.pcp_no}" onclick="showDeleteModal()" >
						  刪除
						</button>
<%-- 					    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pc_picture/PCP.do"> --%>
<!-- 					    <input type="submit" value="刪除" class="btn btn-info"> -->
<%-- 					    <input type="hidden" name="pcp_no" value="${pcPictureVO.pcp_no}"> --%>
<%-- 					    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<!-- 					    <input type="hidden" name="action"value="delete"></FORM> -->
					</td>					
				</tr>
			</c:forEach>
			</tbody>
		</table>

		</div>		

</div>
</div>
</div>
			<!-- 右邊區塊結束right -->
<!-- Modal 刪除 -->
<div class="modal fade" id="myDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
  <div class="modal-dialog" role="document" style="margin-top:200px;">
    <div class="modal-content">
      <div class="modal-body"  align="center">
		<table class="table table-hover table-striped" align="center">
			<tr>
				<th>確定要刪除本張照片嗎?</th>
			</tr> 
			<tr>
				<td><img id="deleteImg" src="" width=400px ></td>
			</tr>   			          
		</table>				
      </div>
      <div class="modal-footer">
      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postpartum_care/PCP.do">
      	<input type="hidden" name="pcp_no" id="deleteNo" value="">
      	<input type="hidden" name="action" value="delete">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="submit" class="btn btn-primary" >確定</button>
       </FORM> 
      </div>
    </div>
  </div>
</div>

<!-- Modal 修改 -->
<div class="modal fade" id="myEdit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document" style="margin-top:200px;">
  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postpartum_care/PCP.do" enctype="multipart/form-data" >
    <div class="modal-content">
<!--       <div class="modal-header"> -->
<!--         <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> -->
<!--         <h4 class="modal-title" >修改照片</h4> -->
<!--       </div> -->
      <div class="modal-body"  align="center">
		<table class="table table-hover table-striped" align="center">
			<tr>
				<th colspan="2">修改照片</th>
			</tr>
			<tr>
				<td>照片說明(20字):</td>
				<td><input type="TEXT" name="pcp_summary"  id="oldSummary" size="50"	 /></td>
			</tr>
			<tr>
				<td rowspan="2">照片更新:</td>
				<td><input id="picture_edit" type="file" name="pcp_picture" /></td>
			</tr>
			<tr>
				<td><img id="editImg" src="" width=400px ></td>
			</tr>
		
		</table>		
      </div>
      <div class="modal-footer">
      
      	<input type="hidden" name="pcp_no" id="editNo" value="">      	
      	<input type="hidden" name="action" value="update">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="submit" class="btn btn-primary" >確定修改</button>
      
      </div>
    </div>
     </FORM> 
  </div>
</div>


<!-- Modal 新增-->
<div class="modal fade" id="myNew" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document" style="margin-top:200px;">
  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/postpartum_care/PCP.do" enctype="multipart/form-data" >
    <div class="modal-content">
<!--       <div class="modal-header"> -->
<!--         <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button> -->
<!--         <h4 class="modal-title" >新增照片</h4> -->
<!--       </div> -->
      <div class="modal-body"  align="center" style="width:auto;">
		<table class="table table-hover table-striped" align="center" style="width:100%;">
			<tr>
				<th colspan="2">新增照片</th>
			</tr>			
			<tr>
				<td>照片說明(20字):</td>
				<td><input type="TEXT" name="pcp_summary" size="50"	 /></td>
			</tr>
			<tr>
				<td rowspan="2">照片上傳:</td>
				<td><input id="picture_new" type="file" name="pcp_picture" /></td>
			</tr>
			<tr>
				<td><img id="newImg" src="" width=400px ></td>
			</tr>
		
		</table>		
      </div>
      <div class="modal-footer">
      
      	<input type="hidden" name="pc_no" id="newNo" value="">      	
      	<input type="hidden" name="action" value="insert">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="submit" class="btn btn-primary" >確定新增</button>
      
      </div>
    </div>
     </FORM> 
  </div>
</div>
	<!-- personal內容結束 -->







</body>

	<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</html>
<script>

	// show the modal onload

function showDeleteModal(e){
	var x = event.target;
	var pcp_no = x.name.toString();
	
	$('#deleteNo').val(pcp_no);
	
	$('#myDelete').modal('show');
	

    var img = document.getElementById("deleteImg");
    img.src = "<%=request.getContextPath()%>/PC_PictureTransfer?pcp_no="+pcp_no;


}
	
function showEditModal(e){
	var x = event.target;
	var pcp_no = x.name.toString();
	var pcp_summary = x.id.toString();
	
	$('#editNo').val(pcp_no);
	$('#oldSummary').val(pcp_summary);
	
	$('#myEdit').modal('show');
	

    var img = document.getElementById("editImg");
    img.src = "<%=request.getContextPath()%>/PC_PictureTransfer?pcp_no="+pcp_no;


}	

function showNewModal(e){
	var x = event.target;
	var pc_no = x.name.toString();
	
	$('#newNo').val(pc_no);
	
	$('#myNew').modal('show');
	

}

function showEditImage(e){

	obj = document.getElementById("editImg");
	obj.src = URL.createObjectURL(event.target.files[0]);

}

function showNewImage(e){

	obj = document.getElementById("newImg");
	obj.src = URL.createObjectURL(event.target.files[0]);

}

window.onload = function (){
document.getElementById("picture_edit").onchange=showEditImage;
document.getElementById("picture_new").onchange=showNewImage;
}



</script>
