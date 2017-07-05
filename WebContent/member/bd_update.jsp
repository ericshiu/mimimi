<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.baby_data.model.*"%>
<%
	MemberVO userVO = (MemberVO) session.getAttribute("userVO");
%>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

<title>修改寶寶資料</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script src="https://code.jquery.com/jquery.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">

<style type="text/css">
.input-group {
	margin: 10px;
}

.imgmemberlogin {
	height: 250px;
	background: #fcc url('../img/imgmemberlogin.jpg') no-repeat center top;
	background-size: cover;
}

#imgdiv {
	height: 150px;
	width: 150px;
	border-radius: 100%;
}
</style>
</head>

<div id="popupcalendar" class="text" style="z-index: 20"></div>
<body>
	<jsp:include page="/front_end/frontTOP.jsp" flush="true" />


		<!-- onsubmit="return false; -->

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font color='red' style="display: none;">請修正以下錯誤:
				<ul id="errormessage">
					<c:forEach var="message" items="${errorMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</font>
		</c:if>


		<div class="modal-dialog" role="document" style="width: 600px;">
			<div class="modal-content">
				<div class="imgmemberlogin" src=""></div>
				<div class="modal-header">

					<h4 class="modal-title" id="myModalLabel"
						style="text-align: center; font-weight: bold; color: #888;">修改寶寶資料</h4>
				</div>
				<FORM METHOD="post" ACTION="bd.do" name="form1" enctype="multipart/form-data">
				<div class="modal-body">
			<div class="form-group">

		
					<div class="input-group" style="width: 97%;">
						<div class="input-group-addon">寶寶姓名：</div>
						<input type="text" class="form-control" name="bd_name"
							placeholder="真實姓名"
							value="${babyDataVO.bd_name}" />
					</div>
				

					<div class="input-group" style="width: 95%;">
					<div class="input-group-addon input-radio">性別：</div>
					<div class="btn-group" data-toggle="buttons">
						<label class="btn btn-default"> <i
							class="fa fa-mars" aria-hidden="true"></i>
							<input
							type="radio" name="bd_sex" autocomplete="off" value="男">
							男
						</label> <label class="btn btn-default"> <i class="fa fa-venus"
							aria-hidden="true"></i>
							<input 
							type="radio" name="bd_sex" ryautocomplete="off" value="女"> 女
						</label>
					</div>
				</div>


				
					<div class="input-group" style="width: 95%; margin: 10px;">
						<div class="input-group-addon" style="width: 85px;">寶寶生日：</div>
						<td bgcolor="#CCCCFF">
						<input type="date" id="bookdate" name="bd_birthday" value="${babyDataVO.bd_birthday}">
						</td>
					</div>
					
					<div class="input-group" style="width: 95%; margin: 10px;">
						<div class="input-group-addon" style="width: 85px;">寶寶預產期：</div>

						<td bgcolor="#CCCCFF">
						<input type="date" id="bookdate" name="bd_pre" value="${babyDataVO.bd_pre}">
						</td>
					</div>


				
				<div class="input-group" style="width: 95%;">
					<div class="input-group-addon">胎次：</div>
					<input type="text" class="form-control" name="bd_order"
						placeholder=""
						value="${babyDataVO.bd_order}" />
				</div>
				

			

				<div class="input-group" style="width: 95%;">
					<div class="input-group-addon">大頭照：</div>
					<input type="file" class="form-control" name="bd_pictures"
						id="bd_pictures">
				</div>
				

				<div class="input-group" style="text-align: center; width: 100%;">

					<img id="imgdiv" src="../img/boy.png">
				</div>
			</div>
			</div>
				<input type="hidden" name="bd_no" value="${babyDataVO.bd_no}">
				<input type="hidden" name="bd_no" value="${babyDataVO.bd_no}">
				<input type="hidden" name="bd_pre" value="">
				<input type="hidden" name="mem_no" value="${userVO.mem_no}" />
			 	<input type="hidden" name="action" value="update">
			<div class="input-group" style="text-align: center; width: 100%;">
			
				<button type="submit" value="送出新增">送出新增</button>
				<button type="button" onclick=showvalue() >神奇小按鈕</button>&nbsp;&nbsp;
			</div>
			</FORM>
			
			
		</div>

		</div>


	<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
	</c:if>
	<script>


	function start1(e) {
		var file = e.target.files[0];
		var i1 = document.getElementById("imgdiv");

		if (file) {
			var reader = new FileReader();
			reader.onload = function(q) {
				i1.src = q.target.result;
			}
		}
		reader.readAsDataURL(file);
	}

	function init() {

		document.getElementById("bd_pictures").onchange = start1;

	}

	window.onload = init;
	</script>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>
</html>

<script>

function $id(id){
	return document.getElementById(id);
}

function $name(name){
	return document.getElementsByName(name);
}

function showvalue(){
	

	$name('bd_name')[0].value="銀貝貝";
	$name('bd_pre')[0].value="2018-02-15";
	$name('bd_birthday')[0].value="2018-02-15";
	$name('bd_order')[0].value="1";
	

}


</script>