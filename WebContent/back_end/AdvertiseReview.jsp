<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.manager.model.*"  %>
<%@ page import="com.firm.model.*"  %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%
session.setAttribute("pageReq", "/back_end/AdvertiseReview.jsp");

FirmVO firVO=(FirmVO)request.getAttribute("firVO");

%>

<!DOCTYPE >
<html>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<title>廣告審核</title>
</head>
<style>
		body{
				margin:0px;
				padding:0px;

				background:#fff url("img/backbaby1.jpg") center center fixed no-repeat;
				background-size: cover;　

			}     
</style>	
<style>

body{
				margin:0px;
				padding:0px;

				background:#fff url("img/backbaby1.jpg") center center fixed no-repeat;
				background-size: cover;　

			}     
</style>
<body>
<c:if test="${not empty errorMsgs}">
  <font color='red'>請修正以下錯誤:
  <ul>
  <c:forEach var="message" items="${errorMsgs}">
    <li>${message}</li>
  </c:forEach>
  </ul>
  </font>
</c:if>

<div class="col-xs-12  col-lg-2">	
<jsp:include page="/back_end/ManagerSelect.jsp" flush="true"/>
</div>
<jsp:useBean id="firSvc" scope="page" class="com.firm.model.FirmService" />
<jsp:useBean id="advSvc" scope="page" class="com.advertise.model.AdvertiseService" />
<div class="col-xs-12  col-lg-10 right" style="height:100%;font-size:20px;width:83%;margin-top:25px;">
<h2 >廣告清單：</h2>
<div class="col-xs-12 col-lg-12 right1" style="padding-left:30px;color:#333;">
		

		<FORM METHOD="post" ACTION="adv.do" style="display:inline;" name="form1">

	<div>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;廣告編號:<select size="1" name="adv_no" class="selectpicker" data-width="130px" style="display:inline;" >
        		<option value="" style="display:inline;" >
	         	<c:forEach var="advVO" items="${advSvc.allAdv}" > 
	          		<option value="${advVO.adv_no}">${advVO.adv_no}
	         	</c:forEach>   
	    </select>
           
           
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 廠商:<select size="1" name="fir_no" class="selectpicker" data-width="100px" style="display:inline;">
          <option value="" style="display:inline;">
        		<c:forEach var="firVO" items="${firSvc.allFir}" > 
	          		<option value="${firVO.fir_no}">${firVO.fir_name}
	         	</c:forEach>    
       </select>  
   
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上架狀態:<select size="1" name="adv_status" class="selectpicker" data-width="100px" style="display:inline;">
          <option value="" style="display:inline;">
        		<option value="1">上架
	         	<option value="2">下架
       </select>  
       
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 審核狀態:<select size="1" name="adv_review" class="selectpicker" data-width="100px" style="display:inline;">
          <option value="" style="display:inline;">
        		<option value="0">未審核
	         	<option value="1">通過
	         	<option value="2">未通過
       </select>
	        
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="submit" class="btn btn-default">查詢</button>
        <input type="hidden" name="action" value="compositeQuery">
     </FORM>
	 </div>
	    
	<br>
	
	
		  	<%if (request.getAttribute("query")!=null){ %>
				<jsp:include page="compositeQury_Adv.jsp" flush="true"/>
			<%}else{ %>
			<br>
		
				<jsp:include page="/back_end/AdvertiseLatestList.jsp" flush="true"/>
			<%} %>

   	</div>
</div>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>

<!-- (Optional) Latest compiled and minified JavaScript translation files -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/i18n/defaults-*.min.js"></script>	
</body>
</html>