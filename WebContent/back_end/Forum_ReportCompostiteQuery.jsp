<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>IBM Emp: Home</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>
<body bgcolor='white'>

<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>
<ul>  
  <li>   
    <FORM METHOD="post" ACTION="FR.do" name="form1">
        <b><font color=blue>萬用複合查詢:</font></b> <br>
        <b>檢舉編號:</b>
        <input type="text" name="fr_no" value="FR00000001"><br>
           
       <b>檢舉人編號:</b>
       <input type="text" name="mem_no" value=""><br>
       
       <b>檢舉文章編號:</b>
       <input type="text" name="fr_am_no" value=""><br>
        
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="listFourmRepoerts_ByCompositeQuery">
     </FORM>
  </li>
</ul>



</body>
</html>