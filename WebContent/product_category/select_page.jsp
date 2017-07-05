<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Mommy Loves Baby Shopping Center: Product Category</title></head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>Mommy Loves Baby Shopping Center: Product Category</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for Product Category</p>

<h3>商品類別資料查詢:</h3>
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
  <li><a href='listAllPRC.jsp'>List</a> all Product Category. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="prc.do" >
        <b>輸入商品類別編號 (如PC00000001):</b>
        <input type="text" name="prc_no">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="PRCSvc" scope="page" class="com.product_category.model.ProductCategoryService" />
   
  <li>
     <FORM METHOD="post" ACTION="prc.do" >
       <b>選擇商品類別編號:</b>
       <select size="1" name="prc_no">
         <c:forEach var="prcVO" items="${PRCSvc.all}" > 
          <option value="${prcVO.prc_no}">${prcVO.prc_no}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="prc.do" >
       <b>選擇商品主類別:</b>
       <select size="1" name="prc_no">
         <c:forEach var="prcVO" items="${PRCSvc.all}" > 
          <option value="${prcVO.prc_no}">${prcVO.prc_main}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>


<h3>商品類別管理</h3>

<ul>
  <li><a href='addPRC.jsp'>Add</a> a new Product Category.</li>
</ul>

</body>

</html>
