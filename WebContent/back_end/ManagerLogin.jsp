<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.manager.model.*" import="java.util.*"%>
<%
	ManagerVO managerVO = (ManagerVO) request.getAttribute("managerVO");
	ManagerVO manuserVO = (ManagerVO) session.getAttribute("manuserVO");
%>
<!DOCTYPE html>
<html lang="">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>���}���_��ݵn�J����</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!-- 		<script language="JavaScript" src="js/ManagerLogin.js"></script>	 -->
		<style type="text/css">
			body{
				margin:0px;
				padding:0px;

				background:#fff url("../img/managerloginbackgroud.jpg") center center fixed no-repeat;
				background-size: cover;�@

			}        
			/*.container{
			
                  	
            background-color: rgba(180,100,100,0.3);
            width:1400px;
            height:800px;
        }*/
        #login{
        	text-align: center;
        	margin: auto;

        }
        .container{
        	position: absolute;
        	top:30%;
        	left:20%;
			width: 400px;

        }
			
			#login1{
				margin: 15px;
			}
			/*#form-group1{
				padding-right: 10%;
				padding-left: 10%;
			}*/
			.modal-header{
				background: #eeeeee;
			}	
			
			#footer{
				text-align: center;
			}
			
			.backadd{
				height: 250px;
				background:#fcc url('../img/backadd.jpg')no-repeat center top;
				background-size:cover  ;
			}
			#fgps{
				margin:10px;
			}
				.modal-header-success {
    color:#fff;
    padding:9px 15px;
    border-bottom:1px solid #eee;
    background-color: #5cb85c;
    -webkit-border-top-left-radius: 5px;
    -webkit-border-top-right-radius: 5px;
    -moz-border-radius-topleft: 5px;
    -moz-border-radius-topright: 5px;
     border-top-left-radius: 5px;
     border-top-right-radius: 5px;
}
.modal-header-warning {
    color:#fff;
    padding:9px 15px;
    border-bottom:1px solid #eee;
    background-color: #f0ad4e;
    -webkit-border-top-left-radius: 5px;
    -webkit-border-top-right-radius: 5px;
    -moz-border-radius-topleft: 5px;
    -moz-border-radius-topright: 5px;
     border-top-left-radius: 5px;
     border-top-right-radius: 5px;
}
.modal-header-danger {
    color:#fff;
    padding:9px 15px;
    border-bottom:1px solid #eee;
    background-color: #d9534f;
    -webkit-border-top-left-radius: 5px;
    -webkit-border-top-right-radius: 5px;
    -moz-border-radius-topleft: 5px;
    -moz-border-radius-topright: 5px;
     border-top-left-radius: 5px;
     border-top-right-radius: 5px;
}
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
.modal-header-primary {
    color:#fff;
    padding:9px 15px;
    border-bottom:1px solid #eee;
    background-color: #428bca;
    -webkit-border-top-left-radius: 5px;
    -webkit-border-top-right-radius: 5px;
    -moz-border-radius-topleft: 5px;
    -moz-border-radius-topright: 5px;
     border-top-left-radius: 5px;
     border-top-right-radius: 5px;
}

.modal-header-pink {
    color:#fff;
    padding:9px 15px;
    border-bottom:1px solid #eee;
    background-color: pink;
    -webkit-border-top-left-radius: 5px;
    -webkit-border-top-right-radius: 5px;
    -moz-border-radius-topleft: 5px;
    -moz-border-radius-topright: 5px;
     border-top-left-radius: 5px;
     border-top-right-radius: 5px;
}


.exampleModal{
    padding: 20px;
}


.modal-title{
    color: white;
    font-size: 20px;
    font-style: bold;
}
		</style>
	</head>
	<body>
<!-- 	�n�J���~�T������ -->
	<c:if test="${not empty errorMsgs}">
			<font color='red' style="display:none;">�Эץ��H�U���~:
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
				<div class="modal-header modal-header-success" style="height: 60px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" style="color:#555;">���~�T��</h4>
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
	
<!-- 	���~�T���������� -->
	
	
	
	
<!-- 	�n�J���\���� -->
		<c:if test="${not empty SMsgs}">
			<font color='red' style="display: none;">
				<ul id="Smessage">
					<c:forEach var="message" items="${SMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</font>
		</c:if>
		
		<button type="button" data-toggle="modal" data-target="#SModal"
		id="SBtn" style="display:none;">Open Modal</button>
	<div class="modal fade" id="SModal" role="dialog" style="top: 25%;display: none;">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header modal-header-info" style="height: 60px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" style="color:#555;">���U���\</h4>
				</div>
				<div class="modal-body">
					<p id="SMsg" style="font-size: 16px;"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>
		
<!-- 	�n�J���\�������� -->
	
				<!-- �n�J�����}�l -->
				 <FORM METHOD="post" ACTION="man.do" name="form1">	
				<div class="container">
						
					    <div class="panel panel-success" id="login">
					    <div class="panel-heading" style="text-align: center;font-size: 24px;">��ݺ޲z���n�J</div>
					    <div class="panel-body" style="color: black;text-align: left;font-size: 16px">
					   
							  <div class="form-group" id="form-group1">
							    <label for="exampleInputEmail1">�b��</label>
							    <input type="text" name="manLoginId" class="form-control" id="exampleInputEmail1" placeholder="Account">
							  </div>
							  <div class="form-group" id="form-group1">
							    <label for="exampleInputPassword1">�K�X</label>
							    <input type="password" name="manLoginPsw" class="form-control" id="exampleInputPassword1" placeholder="Password">
							  </div>

							  <div class="modal-footer">
							  <div class="form-group" id="form-group1" id="btlogin" style="text-align: right;">
							 
							  <button type="submit" class="btn btn-default">�n�J</button>
							  <button type="button" class="btn btn-default" data-toggle="modal" data-target=".bs-example-modal-sm">�ѰO�K�X</button>
							  <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal"> ���U</button>
 
							  </div>
							  </div>
						

					    </div>
						</div>
						
				</div> 
				<input type="hidden" name="action" value="ManLogin">
				</form>
			<!-- �n�J�������� -->

			<!-- ���U�b���}�l -->
<FORM METHOD="post" ACTION="man.do" name="form1">			
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">

    <div class="modal-content">
    <div class="backadd" src=""></div>
      <div class="modal-header ">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel" style="height:30px;text-align: center;color:#555">�޲z�����U</h4>
      </div>
      <div class="modal-body" style="text-align: center;">
       
        <div class="form-group" id="form-group1" style="padding-left: 20%;">
        			
        			<div class="input-group" id="login1" style="width:100%;">
			      		<div class="input-group-addon">�b���G</div>
			      		<input type="text" class="form-control" name="man_id" id="_id"
								maxlength="20" placeholder="6��20�X�^�Ʀr"
								style="width: 55%; height: 36px;" 
								value="<%=(managerVO == null) ? "" : managerVO.getMan_id()%>" />
							<div>
								<i id="checkid" class="fa fa-check-circle" aria-hidden="true"
								style="color: rgb(10, 200, 0); font-size: 10px; padding-top: 10px"></i>
							</div>
					</div>
			
					<div class="input-group" id="login1" style="width:100%;">
			      		<div class="input-group-addon">�m�W�G</div>
			      		<input type="text" class="form-control" id="man_name"
								name="man_name" placeholder="�O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����" style="width:55%;"
								value="<%=(managerVO == null) ? "" : managerVO.getMan_name()%>" />
						<div>
								<i id="checkname" class="fa fa-check-circle" aria-hidden="true"
								style="color: rgb(10, 200, 0); font-size: 10px; padding-top: 10px"></i>
							</div>
					
					</div>
					
					
					<div class="input-group" id="login1" style="width:100%;">
			      		<div class="input-group-addon">�H�c�G</div>
			      		<input type="email" class="form-control" id="man_email"
								name="man_email" placeholder="" style="width:55%;"
								value="<%=(managerVO == null) ? "" : managerVO.getMan_email()%>" />
						<div>
								<i id="checkemail" class="fa fa-check-circle" aria-hidden="true"
								style="color: rgb(10, 200, 0); font-size: 10px; padding-top: 10px"></i>
							</div>
					
					
					</div>


        </div>

      </div>
      <div class="modal-footer" id="footer">
        <!-- <button type="button" class="btn btn-default" data-dismiss="modal">����</button> -->
        <button type="submit" id="Btn" class="btn btn-primary" >���U</button>
        <button type="button" class="btn " id="RegisterBtngo"
						onClick=showValue()>���_�p���s</button>
      </div>
    </div>
  </div>

</div>

<input type="hidden" name="action" value="insert">
</FORM>
		<!-- ���U�b������ -->
		
		<!-- �ѰO�K�X�}�l -->
<FORM METHOD="post" ACTION="man.do" name="form2">	
		<div class="modal fade bs-example-modal-sm" tabindex="1" role="dialog" aria-labelledby="mySmallModalLabel" >
  		<div class="modal-dialog modal-sm" role="document" style="width: 350px;">
    		<div class="modal-content">
      			<div class="modal-header modal-header-success">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel" style="height:30px;text-align: center;color:#555">�ѰO�K�X</h4>
      </div>
      <div class="modal-body" style="text-align: center;">
        
        <div class="form-group"  class="form-group bg-info">
        			
					<div class="input-group" id="fgp" style="width:80%;" >
			      		<div class="input-group-addon">�b���G</div>
			      		<input type="text" class="form-control"  placeholder="���U�b��" name="forgetid">
					</div>
					
					<div class="input-group" id="fgp" style="width:80%;" >
			      		<div class="input-group-addon">�H�c�G</div>
			      		<input type="text" class="form-control"  placeholder="���Uemail" name="forgetemail">
					</div>


        </div>

      </div>
      <div class="modal-footer" id="footer">
        <button type="submit" class="btn btn-primary">�e�X</button>
        
      </div>
    	</div>
  </div>
</div>
<input type="hidden" name="action" value="forget">
</FORM>	
		<!-- �ѰO�K�X���� -->
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</body>
</html>
<script>

function $id(id){
	return document.getElementById(id);
}

function showValue(){
	$id('_id').value="goodjob";
	$id('man_name').value="�L�u��";
	$id('man_email').value="bucktooth888@gmail.com";


}

</script>