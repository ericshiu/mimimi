<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*" import="java.util.*" import="com.forum_article.model.*"
	import="com.firm.model.*" import="com.postpartum_care.model.*"%>

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
<script src="ckeditor/ckeditor.js"></script>  
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>修改文章</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="../css/css.css">
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
<!--固定的 -->
	</head>
	
	<style type="text/css">
			.input-group{
				margin:  10px;
			}

			.imgmemberlogin{
				height: 350px;
				background:#fcc url('../img/ArticleModify.jpg')no-repeat center top;
				background-size:cover  ;
			}
	
			#imgdiv{
				height: 150px;
				width: 150px;
				border-radius: 100%;

			}
		</style>
<body>


<jsp:include page="/front_end/frontTOP.jsp" flush="true"/>
		<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'">請修正以下錯誤:
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
		
		
<FORM METHOD="post" ACTION="FA.do" id="form1" >		
	<div class="modal-dialog" role="document" style="width: 800px;">
	    <div class="modal-content">
    		<div class="imgmemberlogin" src=""></div>
	      		<div class="modal-header ">
	        		
	        		<h4 class="modal-title" id="myModalLabel" style="text-align: center;font-weight:bold;color:#555;">修改文章</h4>
	      		</div>
      				<div class="modal-body">
        
        				<div class="form-group">
		        			<div class="input-group" style="width:95%;">
					      		<div class="input-group-addon">文章標題：</div>
					      		<input type="text" name="fa_title"class="form-control" id="exampleInput" placeholder="" value="${FAVO.fa_title}">
							</div>
							
							
							<div class="input-group">
					      		<div class="input-group-addon">文章內容：</div>
					      		 <textarea name="fa_content" class="form-control" name="article_content" rows="5" id="content" style="border: 1px solid #000;height: 313px;width: 750px;">${FAVO.fa_content}</textarea>
					                <script>
					                    CKEDITOR.replace( 'content', {
					                        width:600,height:250,
					                    });
					                </script>
							</div>
							
							
        				</div>

      				</div>
      			<div class="modal-footer" style="text-align: center;">
			        <button type="submit" class="btn btn-primary">送出文章</button>
				</div>
		</div>
	</div>
<input type="hidden" name="fa_no" value="${FAVO.fa_no}">
<input type="hidden" name="mem_no" value="${FAVO.mem_no }">	
<input type="hidden" name="action" value="update">	
	

</FORM>
	

	
<jsp:include page="/front_end/frontBOTTOM.jsp" flush="true" />
</body>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script
  		src="https://code.jquery.com/jquery-3.2.1.js"
  		integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
  		crossorigin="anonymous"></script>
  		
</html>

