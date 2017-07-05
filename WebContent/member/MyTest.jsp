<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*" import="java.util.*" import="sun.misc.*"
import="com.firm.model.*" import="com.postpartum_care.model.*"%>

<%

session.setAttribute("pageReq", "/member/MyPersonal.jsp");

%>
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
%>

<!DOCTYPE html>
<html lang="">
<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>首頁</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
		
</head>
<body>
<jsp:include page="/front_end/frontTOP.jsp" flush="true" />
<!-- --------------內容--------------------- -->

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
          <h4 class="modal-title">錯誤訊息</h4>
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

		<div class="col-xs-12  col-lg-2 left" style="text-align:center;width:20%;">
			<jsp:include page="/member/MySelect.jsp" flush="true"/>
		</div>
		<!-- left -->

		<!-- 左邊選單區left結束 -->
<FORM METHOD="post" ACTION="mem.do" id="form1" enctype="multipart/form-data">
		    		 	     			

		<div class="col-xs-12  col-lg-10 right personalright" style="height:100%;width:80%;margin:0px;border-width:0px 0px 0px 1px;border-style:solid;">
			<!-- 右邊區塊開始right -->

			<div class="col-xs-12 col-sm-3"></div>
			<div class="col-xs-12 col-sm-6">
			<br><br>
<!-- 			<div class="modal-dialog" role="document" style="width: 500px;" > -->
			<div class="modal-content" style="width: 502px;">
			<div class="panel panel-info" style="width: 500px;">
				<div class="panel-heading">
					<h3 class="panel-title" style="text-align: center;"><b>會員資訊維護</b></h3>
				</div>
				<table class="table table-hover" style="text-align: center;">
					<tbody >
						<tr style="margin:100px;">
							<th style="text-align:center;">會員編號：</th>
							<th name="mem_no" value="${userVO.mem_no}" style="text-align:center;" >${userVO.mem_no}</th>
							<th></th><th></th>
							
						</tr>
						<tr id="Fathername" style="margin:200px;">
							<th style="text-align:center;">姓名：</th>
							<th name="mem_name" value="快要發瘋" style="text-align:center;" id="Username">${userVO.mem_name}</th>
							<th></th>
							<th style="text-align:center;"><i class="glyphicon glyphicon-pencil" onClick=Clickname()> </i></button></th>


						</tr>

						<tr style="margin:200px;">
							<th style="text-align:center;">帳號：</th>
							<th name="mem_id" value="${userVO.mem_id}"  style="text-align:center;">${userVO.mem_id}</th>
						<th></th><th></th>


						</tr>
						<%String dataPsw=null; %>
						<%try{ %>
						<%dataPsw=new String(userVO.getMem_password()); %>
						<%final BASE64Decoder decoder = new BASE64Decoder(); %>
						<%String Psw=new String(decoder.decodeBuffer(dataPsw), "UTF-8"); %>
						<%String real=Psw.replaceAll("[a-zA-Z0-9]","*"); %>
						
						<tr id="Fatherpassword">
							<th style="text-align:center;">密碼：</th>
							<th name="mem_password" value="${userVO.mem_password}"  id="Userpassword" style="text-align:center;"><%=real%></th>
							<th></th>
							<th style="text-align:center;"><i class="glyphicon glyphicon-pencil" onClick=Clickpassword()> </i></button></th>

						</tr>
						<%}catch(Exception e){ %>
						<% e.getStackTrace(); %>
						
						<%} %>
			
						<tr id="Fatherpasswordcheck" style="display:none;">
							<th style="text-align:center;color:red;">再次確認密碼：</th>
							<th   id="Userpasswordcheck" style="text-align:center;">
							<input type="text" name="mem_passwordcheck" style="width:200px;text-align:center;"
							value="">  </th>
							<th></th>
							<th style="text-align:center;"></th>

						</tr>

						<tr id="Fathernike">
							<th style="text-align:center;">暱稱：</th>
							<th name="mem_nike" value="${userVO.mem_nike}"  id="Usernike" style="text-align:center;">${userVO.mem_nike}</th>
							<th></th>
							<th style="text-align:center;"><i class="glyphicon glyphicon-pencil" onClick=Clicknike()> </i></button></th>

						</tr>


						<tr>
							<th style="text-align:center;">性別：</th>
							<th name="mem_sex" value="${userVO.mem_sex}" style="text-align:center;">${userVO.mem_sex}</th>
							<th></th><th></th>

						</tr>


						<tr>
							<th style="text-align:center;">生日：</th>
							<th name="mem_birthday" value="${userVO.mem_birthday}"  style="text-align:center;">${userVO.mem_birthday}</th>
							<th></th><th></th>

						</tr>


						<tr id="Fatherphone">
							<th style="text-align:center;">連絡電話：</th>
							<th name="mem_phone" value="${userVO.mem_phone}"  id="Userphone" style="text-align:center;">${userVO.mem_phone}</th>
							<th></th>
							<th style="text-align:center;"><i class="glyphicon glyphicon-pencil" onClick=Clickphone()> </i></button></th>


						</tr>

						<tr id="Fatheraddress">
							<th style="text-align:center;">地址：</th>
							<th name="mem_address" value="${userVO.mem_address}" style="text-align:center;" id="Useraddress">${userVO.mem_address}</th>
							<th></th>
							<th style="text-align:center;"><i class="glyphicon glyphicon-pencil" onClick=Clickaddress()> </i></button></th>

						</tr>
					
						<tr id="Fatheremail">
							<th style="text-align:center;">電子郵件：</th>
							<th name="mem_email" value="${userVO.mem_email}" style="text-align:center;" id="Useremail">${userVO.mem_email}</th>
							<th></th>
							<th style="text-align:center;"><i class="glyphicon glyphicon-pencil" onClick=Clickemail()> </i></button></th>

						</tr>

						<tr>
							<th style="text-align:center;">會員積分：</th>
							<th name="mem_point" value="${userVO.mem_point}" style="text-align:center;">${userVO.mem_point}</th>
							<th></th><th></th>


						</tr>


						<tr>
							<th style="text-align:center;">實際積分：</th>
							<th name="mem_actual_point" value="${userVO.mem_actual_point}" style="text-align:center;">${userVO.mem_actual_point}</th>
							<th></th><th></th>
						</tr>
						
						<tr id="Fatherfacebook">
							<th style="text-align:center;">Facebook：</th>
							<th name="mem_facebook" value="${userVO.mem_facebook}" style="text-align:center;" id="Userfacebook">${userVO.mem_facebook}</th>
							<th></th>
							<th style="text-align:center;"><i class="glyphicon glyphicon-pencil" onClick=Clickfacebook()> </i></button></th>

						</tr>
						
						<tr id="Fathergoogle">
							<th style="text-align:center;">Google：</th>
							<th name="mem_google" value="${userVO.mem_google}" style="text-align:center;" id="Usergoogle">${userVO.mem_google}</th>
							<th></th>
							<th style="text-align:center;"><i class="glyphicon glyphicon-pencil" onClick=Clickgoogle()> </i></button></th>

						</tr>
						<div class="modal-footer">
						<tr>
						<th></th>
						<th style="text-align:center;"><button type="submit" class="btn btn-info">送出</button></th>
						<th></th><th></th>
						</tr>
						
						</div>
					</tbody>
					
				</table>
			
			</div>
			</div>

			<div class="col-xs-12 col-sm-3"></div>

			
	  	</div>


			<!-- 右邊區塊結束right -->
		</div>
<input type="hidden" name="mem_no" value="${userVO.mem_no}">
<input type="hidden" name="mem_name" value="${userVO.mem_name}">
<input type="hidden" name="mem_id" value="${userVO.mem_id}">
<input type="hidden" name="mem_password" value="${userVO.mem_password}">
<input type="hidden" name="mem_nike" value="${userVO.mem_nike}">
<input type="hidden" name="mem_sex" value="${userVO.mem_sex}">
<input type="hidden" name="mem_birthday" value="${userVO.mem_birthday}">
<input type="hidden" name="mem_phone" value="${userVO.mem_phone}">
<input type="hidden" name="mem_address" value="${userVO.mem_address}">
<input type="hidden" name="mem_email" value="${userVO.mem_email}">
<input type="hidden" name="mem_point" value="${userVO.mem_point}">
<input type="hidden" name="mem_actual_point" value="${userVO.mem_actual_point}">
<input type="hidden" name="mem_facebook" value="${userVO.mem_facebook}">
<input type="hidden" name="mem_google" value="${userVO.mem_google}">	
<input type="hidden" name="mem_authority" value="${userVO.mem_authority}">

<input type="hidden" name="action" value="update">


<div class="modal fade" tabindex="-1" role="dialog" id="myModal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header modal-header-success">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <p class="modal-title" id="exampleModalLabel" >更改大頭照</p>
      </div>
      <div class="modal-body" style="height: 300px;">
       
		<div class="col-xs-12 col-lg-4">
			選擇上船的圖片<input type="file" name="mem_picture" id="mem_picture"
			value="${userVO.mem_picture}">
			
		</div>
		<div class="col-xs-12 col-lg-8" style="text-align: center;">
			<img id="Modifyphoto"  style="width:300px;height: 300px;border-radius: 100%;border-style:ridge;border-width:5px;">
		</div>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="submit" class="btn btn-primary">上傳圖片</button>
      </div>
    
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</FORM>			
</div>



	<!-- personal內容結束 -->

<!-- -----------------內容---------------------------- -->


<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script
  		src="https://code.jquery.com/jquery-3.2.1.js"
  		integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
  		crossorigin="anonymous"></script>
  		<script src="<%=request.getContextPath() %>/member/js/Modifymem.js"></script>
  		
</html>

<script>

	function start1(e) {
		var file = e.target.files[0];
		var i1 = document.getElementById("Modifyphoto");

		if (file) {
			var reader = new FileReader();
			reader.onload = function(q) {
				i1.src = q.target.result;
			}
		}
		reader.readAsDataURL(file);
	}

	function $id(id){
		  return document.getElementById(id);
		}	
	
	function init() {
		
		document.getElementById("mem_picture").onchange = start1;
		
	}

	window.onload = init;
</script>