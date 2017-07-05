<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*" import="java.util.*" import="com.forum_article.model.*"
	import="com.firm.model.*" import="com.postpartum_care.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

	ForumArticleVO FAVO=(ForumArticleVO)request.getAttribute("FAVO");
 	

	
 	String str="/forum/FA.do?fa_no="+FAVO.getFa_no()+"&mem_no="+FAVO.getMem_no()+"&action=getOne_For_Display";
 	session.setAttribute("pageReq", str);
%>





<!DOCTYPE html>
<html lang="">
<head>
<!--固定的 -->
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>媽咪樂寶討論區</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="../css/css.css">
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script	src="https://code.jquery.com/jquery-3.2.1.js" integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE=" crossorigin="anonymous"></script>
  		
<!--固定的 -->
<script language="JavaScript" src="js/ArticleFavorite.js"></script>
<script language="JavaScript" src="js/ArticleDetail.js"></script>

	</head>
	
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
	<body>
<jsp:include page="/front_end/frontTOP.jsp" flush="true"/>

<!-- 	登入成功視窗 -->
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
				<div class="modal-body">
					<p id="SMsg" style="font-size: 16px;"></p>
				</div>
			
			</div>

		</div>
	</div>
		
<!-- 	登入成功視窗結束 -->




<!-- 有檢舉申請成功訊息的話就會跳出視窗 -->
		<c:if test="${not empty FRSMsgs}">
			<font color='red' style="display: none;">
				<ul id="FRSmessage">
					<c:forEach var="message" items="${FRSMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</font>
		</c:if>
		
		<button type="button" data-toggle="modal" data-target="#FRSModal"
		id="FRSBtn" style="display:none;">Open Modal</button>
	<div class="modal fade" id="FRSModal" role="dialog" style="top: 25%;display: none;">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header modal-header-danger" style="height: 60px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">檢舉申請</h4>
				</div>
				<div class="modal-body">
					<p id="FRSMsg" style="font-size: 16px;"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>
		
<!-- 檢舉申請成功視窗 -->

<!-- 錯誤訊息視窗 -->

<button type="button" data-toggle="modal" data-target="#errorModal"
		id="errorBtn" style="display: none;">Open Modal</button>
	<div class="modal fade" id="errorModal" role="dialog" style="top: 25%;">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header modal-header-danger" style="height: 60px;">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">錯誤訊息</h4>
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

<!-- 錯誤訊息視窗 -->
<br>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="AFSvc" scope="page" class="com.article_favorite.model.ArticleFavoriteService" />			
<div class="container" style="border-width:3px;border-color:#5f94bf;border-style:dashed;height: 100%;padding:15px; ">
<div class="col-xs-12 col-sm-12">
<div class="col-xs-12 col-sm-2" style="background-color:rgba(71,255,253,0.1);">
<a href="<%= request.getContextPath() %>/forum/ForumINDEX.jsp" data-toggle="class="thumbnail" target="_blank" >
<img src="<%= request.getContextPath() %>/MemberPhoto?mem_no=${FAVO.mem_no}" style="width: 80px;height: 80px;border-radius: 100%;border-width:1px;border-style:solid;margin-top:20px;" >
</a>
	<p>會員：${memSvc.getOneMem(FAVO.mem_no).mem_name}</p>
	<p>性別：${memSvc.getOneMem(FAVO.mem_no).mem_sex}</p>
	<p>積分：${memSvc.getOneMem(FAVO.mem_no).mem_point}</p>
	<c:forEach var="AF_FAVVO" items="${AFSvc.all_FAV}">
	<c:if test="${AF_FAVVO.fa_no==FAVO.fa_no}">
	<p>收藏數：${AF_FAVVO.mem_no}</p>
	</c:if>
	</c:forEach>
	<p>發表時間：<fmt:formatDate value="${FAVO.fa_publish_date}" pattern="yyyy-MM-dd aaa HH:mm:ss "/></p>
	
	</div>




	<div class="col-xs-12 col-sm-10">
	<div class="col-xs-12 col-sm-12">
	<div class="panel panel-primary">
	<div class="panel-heading" style="height:60px;padding-top:1px;background-color:#aadcff;color:#337ab7;">
		
		<h3 >${FAVO.fa_title}</h3>

	</div>
		<table class="table table-hover" >
			
			<tbody >
				<tr >
					<td >
					${FAVO.fa_content}
					</td>		
				</tr>
			</tbody>
			<jsp:useBean id="AFDAO" scope="page" class="com.article_favorite.model.ArticleFavoriteDAO" />
			<tfoot style="background-color: #aadcff;">
				<tr style="text-align: right;">
					<td colspan="6" style="text-align:left;">

<!-- 					--收藏紐設定---------- -->
					<input type="hidden" id="empty" value="${empty AFDAO.getmemfa(userVO.mem_no,FAVO.fa_no)}">
					<c:if test="${empty AFDAO.getmemfa(userVO.mem_no,FAVO.fa_no)}">

	               		<button type="button" class="btn btn-primary" style="padding: 0px;" onclick="favorite()">
						<input type="hidden" id="path" value="<%=request.getContextPath() %>/forum/AF.do">
						<input type="hidden" id="mem_no1" value="${userVO.mem_no}">
						<input type="hidden" id="fa_no1" value="${FAVO.fa_no}">
						<i id="btn" class="glyphicon glyphicon-heart" >收藏</i></button>

	        		</c:if>

					<c:if test="${not empty AFDAO.getmemfa(userVO.mem_no,FAVO.fa_no)}">

						<button type="button" class="btn btn-primary" style="padding: 0px;" onclick="favorite()">
						<input type="hidden" id="path" value="<%=request.getContextPath() %>/forum/AF.do">
						<input type="hidden" id="mem_no1" value="${userVO.mem_no}">
						<input type="hidden" id="fa_no1" value="${FAVO.fa_no}">
						<i id="btn" class="glyphicon glyphicon-heart-empty" >收藏</i></button>
				
	        		</c:if>
					
<!-- 					--收藏紐設定---------- -->

					</td>
					<td><FORM METHOD="post" ACTION="FR.do" name="form1" >
					<button type="submit" class="btn btn-primary" style="padding: 0px;"><i class="glyphicon glyphicon-scissors"></i>檢舉</button>
					<input type="hidden" name="action" value="getOne_For_Display">
					<input type="hidden" name="fr_am_no" value="${FAVO.fa_no }">
					<input type="hidden" name="mem_no" value="${userVO.mem_no }">
					</FORM></td>
					
				</tr>
			</tfoot>
		</table>
	</div>	 <!-- panel-primary -->


	
	<hr>
	<br>

	</div>  <!-- bs12 -->


<jsp:useBean id="fmSvc" scope="page" class="com.forum_message.model.ForumMessageDAO" />

<%int q=0; %>
	<c:forEach var="fmVO" items="${fmSvc.all}">
			<c:if test="${fmVO.fa_no==FAVO.fa_no}">			
	               				    		
	       
	               			    		
	       	<div class="col-xs-12 col-sm-12">  <!-- 留言板 -->
	<div class="panel panel-info">
	<div class="panel-heading" >
		<%q++; %>
		<font display: inline;><%=q %>樓</font>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<font display: inline;>留言者：${memSvc.getOneMem(fmVO.mem_no).mem_name }</font>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<font display:inline;>時間：<fmt:formatDate value="${FAVO.fa_publish_date}" pattern="yyyy-MM-dd aaa HH:mm:ss "/></font>
		
	</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<td>
					${fmVO.fm_content },	
					</td>
					
				</tr>
			</thead>
			<tbody>
				
			</tbody>
			
		</table>
	</div>	


	</div>  <!-- 留言版bs12 -->
	               			    		
	 </c:if>     
	</c:forEach>
	<FORM METHOD="post" ACTION="FM.do" name="form1" >
	<div class="col-xs-12 col-sm-12"> 
	<div>
	<div class="panel-info" >
	<div class="panel-heading" style="background-color: #d9edf7;" >
	<font style="margin-left: 10px;">留言</font>
	<div class="input-group" style="width:100%;">
		<textarea name="fm_content" rows=6 style="width:96%;border-radius: 0 3% 3% 0;"></textarea>
	    <button class="btn btn-info" type="submit" disabled="ture" id="Btnaddmsg">登入會員留言</button>
	</div>
	</div>
	</div>
	</div>
	</div>  <!-- 留言版bs12 -->
	
	<input type="hidden" name="fa_no" value="${FAVO.fa_no }">
	<input type="hidden" name="mem_no" id="mem_no" value="${userVO.mem_no }">
<!-- 	<input type="hidden" name="mem_no" id="mem_no" value="MEM0000002"> -->
	<input type="hidden" name="action" value="insert">
	</form>



	</div> <!--  bs10 -->
</div> <!-- 最外bs12 -->
</div> <!-- container -->
<br>
<br>
<br>
		


	<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>
<!-- 		<script src="https://code.jquery.com/jquery.js"></script> -->
<!-- 		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<!-- 		<script -->
<!--   		src="https://code.jquery.com/jquery-3.2.1.js" -->
<!--   		integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE=" -->
<!--   		crossorigin="anonymous"></script> -->
  		
</html>