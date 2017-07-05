<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>廠商資料首頁</title>
</head>
<body>
<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>Mammy Love Baby PC: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for Mammy Love Baby PC: Home</p>

<h3>資料查詢:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<ul>
  <li><a href='listAllPC.jsp'>List</a> all PCs. </li><br><br>
  
  <li>
    <FORM METHOD="post" ACTION="PC.do" >
        <b>輸入廠商編號 (如PC00000001):</b>
        <input type="text" name="pc_no">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="PCSvc" scope="page" class="com.postpartum_care.model.PostpartumCareService" />
   
  <li>
     <FORM METHOD="post" ACTION="PC.do" >
       <b>選擇廠商編號:</b>
       <select size="1" name="pc_no">
         <c:forEach var="postpartumCareVO" items="${PCSvc.all}" > 
          <option value="${postpartumCareVO.pc_no}">${postpartumCareVO.pc_no}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="PC.do" >
       <b>選擇廠商名稱:</b>
       <select size="1" name="pc_no">
         <c:forEach var="postpartumCareVO" items="${PCSvc.all}" > 
          <option value="${postpartumCareVO.pc_no}">${postpartumCareVO.pc_name}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="listEmps_ByCompositeQuery">
     </FORM>
  </li>
</ul>


<h3>廠商管理</h3>

<ul>
  <li><a href='addPC.jsp'>Add</a> a new PC.</li>
</ul>
</body>
</html>