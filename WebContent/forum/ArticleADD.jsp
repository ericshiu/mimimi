<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*" import="java.util.*"
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

	
// 	session.setAttribute("pageReq", "/member/MyArticle.jsp");
%>





<!DOCTYPE html>
<html lang="">
<head>
<!--固定的 -->
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<title>新增文章</title>
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
				background:#fcc url('../img/cute1.jpg')no-repeat center top;
				background-size:cover  ;
			}
	
			#imgdiv{
				height: 150px;
				width: 150px;
				border-radius: 100%;

			}
		</style>
		<script src="ckeditor/ckeditor.js"></script>  
<body>


<jsp:include page="/front_end/frontTOP.jsp" flush="true"/>
		
		
<FORM METHOD="post" ACTION="FA.do" id="form1" >		
	<div class="modal-dialog" role="document" style="width: 750px;">
	    <div class="modal-content">
    		<div class="imgmemberlogin" src=""></div>
	      		<div class="modal-header ">
	        		
	        		<h4 class="modal-title" id="myModalLabel" style="text-align: center;font-weight:bold;color:#555;">新增文章</h4>
	      		</div>
      				<div class="modal-body">
        
        				<div class="form-group">
		        			<div class="input-group" style="width:100%;">
					      		<div class="input-group-addon">文章標題：</div>
					      		<input type="text" name="fa_title"class="form-control" id="exampleInput" placeholder="">
							</div>
							
							
							<div class="input-group">
					      		<div class="input-group-addon">文章內容：</div>
					      		 <textarea name="fa_content" class="form-control" name="article_content" rows="5" id="content" style="border: 1px solid #000;height: 313px;width: 750px;"></textarea>
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
<input type="hidden" name="mem_no" value="${userVO.mem_no }">	
<input type="hidden" name="action" value="insert">	
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


