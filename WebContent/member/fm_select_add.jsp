
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="com.fetal_movement.model.*" import="com.member.model.*" import="java.util.*" import="sun.misc.*"%>
<%
	MemberVO userVO = (MemberVO) session.getAttribute("userVO");
	session.setAttribute("pageReq", "/member/PersonalMem.jsp");//這行是在存重導路徑，所以要打本頁的網址
%>
		<style>
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



        #modal-dialog-login{
          color:#555;
          width: 1300px;
          background-color: pink;
        }   
          
        #modal-body-login {   
          background-color: pink;
          height: 650px;
          color:#555;
        }  
        #p1{
          font-size: 16px;
          color:#555;
        }
	
	</style>


<!DOCTYPE html>
<html lang="">
	<head>
	<title>胎動紀錄</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>Title Page</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!-- 引用icon -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
		<!-- 引用Css -->
		 <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
  		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">

  		<link rel='stylesheet prefetch' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css'>
		<link rel='stylesheet prefetch' href='http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css'>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/title.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/titlehover.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/timecss.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/table.css">
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
		
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>
<jsp:include page="/front_end/frontTOP.jsp" flush="true" />	

		<div class="container">
			<div class="row">
			<div class="col-xs-12 col-sm-12">
				<div class="widget center">
					  <div class="text center">
					    <h1 class="">胎動紀錄</h1>
					    <p>Go ahead, resize me.</p>
					  </div>
					  <div class="blur">
					  </div>
					</div>
				<div class="col-xs-12 col-sm-3">
				 <jsp:include page="../listgrop.jsp" flush="true"/>
				</div>
				<div class="col-xs-12 col-sm-9">
				<h3>注意</h3>
				 <div class="title">
				 
					  <div class="title-word">胎動可是胎兒健康的重要指標唷!胎動與子宮內環境、胎兒生長狀況以及健康情形有關聯性。</div>
					  <div class="title-word">正常健康的baby一定會有適度的胎動，媽咪可以藉由胎動，感受到baby的活動情形，</div>
					  <div class="title-word">胎動太少可能原因包括:胎兒被臍帶纏繞缺氧、胎盤剝離造成腦缺氧、窘迫，</div>
					  <div class="title-word">造成出生異常的機會會升高胎動""突然""變的很頻繁，有可能因為胎盤剝離、受外力撞擊，</div>
					  <div class="title-word">baby會突然的激烈活動，但沒過多久胎動又明顯減少，甚至感受不到胎動。</div>
					  <div class="title-word">不管是胎動太少或突然太多，這種胎動異常的情況就要立即就醫囉!</div>
					  <div class="title-word">但隨著懷孕越到後期，會因羊水減少、胎兒長大，子宮內空間漸漸減少，而有胎動減少的情況!</div>
					  <div class="title-word">所以孕期約28週回診時，醫生應該就會請媽咪回家"每天"紀錄胎動囉!</div>
					</div>
				</div>
				</div>
				</div>
			<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-4"></div>
				<div class="col-xs-12 col-sm-4">
				<div class="clock clkwrapper">
				    <div class="clknum">
				        <!-- Hour tens -->
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				    </div>
				    <div class="clknum">
				        <!-- Hour units -->
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				    </div>
				    <div class="clknum">
				        <!-- Minute tens -->
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				    </div>
				    <div class="clknum">
				        <!-- Minute units -->
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				    </div>
				    <div class="clknum">
				        <!-- Seconde tens -->
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				    </div>
				    <div class="clknum">
				        <!-- Seconde units -->
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				    </div>
				    <div class="clknum">
				        <!-- Seconde tenth -->
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				    </div>
				    <div class="clknum">
				        <!-- Seconde hundredth -->
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				        <div class="clkbar"></div>
				    </div>
				    </div>
				<div class="col-xs-12 col-sm-4">
					<div class="container button">
					  	<button id="button2" onclick="myFunction()">開始記錄</button>
					</div></div>

				</div>	
				
				<div class="col-xs-12 col-sm-4">
					
				</div>

				
<!-- tavle -->
				<div class="col-xs-12 col-sm-12">
					
				
				  <div class="tbl-header">
				    <table cellpadding="0" cellspacing="0" border="0">
				      <thead>
				        <tr>
				          
				          <th>紀錄時間</th>
				          <th>胎動等級</th>
				         
				        </tr>
				      </thead>
				    </table>
				  </div>
				  <div class="tbl-content">
				    <table cellpadding="0" cellspacing="0" border="0">
				      <tbody>
				      <%int i=0; %>
				       <jsp:useBean id="FMSvc" scope="page" class="com.fetal_movement.model.FetalMovementService" />
				         <c:forEach var="fetalMovementVO" items="${FMSvc.all}">
				         <%i++; %>
				         <c:if test="${userVO.mem_no==fetalMovementVO.mem_no}">
				        <tr>
				          
				          <td id="lvidd<%=i%>">${fetalMovementVO.fm_date}</td>
				          <td id="lvid<%=i%>">${fetalMovementVO.fm_lv}</td>
				          
				          
				        </tr>
				        
				        </c:if>
				        </c:forEach>
				        <div id="lvcount" style="display: none;"><%=i %></div>
				        
				      </tbody>
				    </table>
				  </div>
				</div>
			</div>
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
			
		<FORM METHOD="post" ACTION="fm.do" name="form1">
			<input type="text"  id="txt" style="display: none;">
			<input type="text" name="fm_lv" id="fm_lv" style="display: none;">
			<input type="hidden" name="mem_no" value="${userVO.mem_no}" />
			<input type="hidden" name="action" value="insert">
			
		</FORM>
		
<!-- 				<button type="button" data-toggle="modal" data-target="#SModal" -->
<!-- 		id="SBtn" style="display:none;">Open Modal</button> -->
		<button type="button" data-toggle="modal" data-target="#SModal"
		id="SBtn" style="display: none;">Open Modal</button>
	<div class="modal fade" id="SModal" role="dialog" style="top: 25%;display: none;margin-left: -25%;">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content" style="width: 300%;">
				<div class="modal-header modal-header-danger" style="height: 60px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" style="color:#555;">請注意！！</h4>
				</div>
				<div class="modal-body">
					<p  style="font-size: 16px;">●當10次胎動在120分鐘內完成時（也就是填寫於A、B、C、D欄位中），表示胎動並無異常。</p>
					<p  style="font-size: 16px;">●一旦測量胎動10次超過2小時、甚至是超過2小時完全沒有胎動，請回診確認胎兒狀況。</p>
					<p  style="font-size: 16px;">●胎動紀錄僅能初步觀察胎兒在子宮內的胎動狀況，卻無法完全避免胎死腹中的可能性發生。</p>
					<p  style="font-size: 16px;">醫師小叮嚀～少數孕婦會購買胎兒監視器在家自行使用監測；在絕大多數孕婦沒有自備胎兒監視器的情況下，王樂明醫師提醒，尤其是35、36週之後，更要特別注意胎動。</p>
 					<p  style="font-size: 16px;">因為快生產時的生理狀況往往瞬息萬變，曾有一位孕婦已足月，胎兒卻突然沒有心跳了，研判可能是胎兒先天有問題、妊娠糖尿病或子癇前症過於嚴重所致。正因為懷孕後期的狀況比較難以預料，因此孕婦更應每日留意胎動狀況。</p>

 


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
		<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
		<script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>
		<script
  		src="https://code.jquery.com/jquery-3.2.1.js"
  		integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
  		crossorigin="anonymous"></script>
  		<script type="text/javascript" src='<%=request.getContextPath() %>/member/js/js.js'></script>
  		<script type="text/javascript" src='<%=request.getContextPath() %>/member/js/table.js'></script>
  		
  		<script>
  	  	var valueArr =new Array();
  	  var tip = 0;
  		
  		
  		function doFirst(){
  			
  			var lvcount = document.getElementById("lvcount").innerHTML;
//   	  		alert(lvcount);
  	  		lvcount=parseInt(lvcount);
  	  	//	alert(lvcount);
  			
  			
  			
  			for(var i=1;i<=lvcount;i++){
  				var aa = "lvid"+i;
  				var a1 = "lvidd"+i;
  				var mm;
  				
  				
  				//alert(aa);
  				if(document.getElementById(aa) != null){
  				mm = document.getElementById(aa).innerHTML;
  				}
  				
  				if(mm=="C"||mm=="D"||mm=="E"){
  					tip++;
  					
  					document.getElementById(aa).style.color = "#ff0000";
  					document.getElementById(aa).style.fontSize = "xx-large";
//   					$("#SModal").attr('class', 'modal fade in');
//   					document.getElementById("SModal").style="top: 25%; margin-left: -50%; display: block; padding-right: 6px;";
  					if(tip==1){
  	  					document.getElementById("SBtn").click();
  	  				}	
  				
  					
  					
	  					$('#'+aa).click(function(){
	  							
	  						document.getElementById("SBtn").click();
	  						
	  					});
  					
  				}
  				
  				
  			
  				
  			}
  			
  			
  			
  		}

  		window.addEventListener('load',doFirst,false);
  		
  		</script>
  		
			
  		
  		<script>
  			
		 	 		var c=0;
		 	 		var t

		 			function timedCount()
		 			 {
		 			 document.getElementById('txt').value=c
		 			 c=c+1
		 			 t=setTimeout("timedCount()",1000)
		 			 } 

					var a=0;
					var times=0;
					var stop
				  	var button = $('#button2'),
				  	clkbar = $('.clkbar'),
				  	playState = '-webkit-animation-play-state';


				  	
				  	button.click(function() {
				  	
				 
				  // pause or start
				   	if(a==0){
				   		setTimeout(timedCount());
				   		console.log(a);
				   		a=a+1;
				   		clkbar.css(playState, function(i, v) {
				      	return v === 'paused' ? 'running' : 'paused';
				      	$('body').toggleClass('paused', $(this).css(playState) === 'paused');
				    });
				   		
				   		
				   	document.getElementById("button2").innerHTML="胎動1次";
					
					
				   	return 
				    }

				   if(a==1){
					   console.log(a);
					   	a=a+1;
				   		
				   		document.getElementById("button2").innerHTML="胎動2次";
				   		return
				   }
				   if(a==2){
				   		console.log(a);
				   		a=a+1;
				   		document.getElementById("button2").innerHTML="胎動3次";
				   		return
				   }
				   if(a==3){
				   		console.log(a);
				   		a=a+1;
				   		document.getElementById("button2").innerHTML="胎動4次";
				   		return
				   }
				   if(a==4){
				   		console.log(a);
				   		a=a+1;
				   		document.getElementById("button2").innerHTML="胎動5次";
				   		return
				   }
				   if(a==5){
				   		console.log(a);
				   		document.getElementById("button2").innerHTML="胎動6次";
				   	 	clearTimeout(t)
				   		return fm_lv();
				   		
				   }
				      
				  });

				  	
				  	function fm_lv(){
			
 				  		var lv = document.getElementById("txt").innerHTML;
// 				  		var lv = 54000000;
				  		
				  		if(lv<18000000){
				  		
				  			document.getElementById("fm_lv").value="A";
				  			return send();
				  		}
				  		
						if(18000000<lv&&lv<=36000000){
							document.getElementById("fm_lv").value="B";	
							return send();
						}
						
						if(36000000<lv&&lv<=54000000){
							document.getElementById("fm_lv").value="C";	
							return send();
						}
						
						if(54000000<lv&&lv<=72000000){
							document.getElementById("fm_lv").value="D";	
							return send();
						}
						
						if(lv>72000000){
							document.getElementById("fm_lv").value="E";	
							
							return send();
						}
				  		
				  		
				  	}
				  	
				  	
				  	function send(){
					      document.form1.submit();
					}
				

				  	
  		</script>
	</body>
</html>

