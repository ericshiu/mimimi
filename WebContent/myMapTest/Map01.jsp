<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA6uC_QFIO4lPVQ2TKgLh6i4WIcBqw7MCs"> -->
<!--     </script> -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBvBAWeCCbBOg0URgIiMI9T0RoyAKq98SY"
  type="text/javascript"></script>

		<script src="js/map01.js"></script>
		<title>Google Maps Javascript API</title>
		

</head>
<body >

	<select size="1" id="myhome" onchange="doFirst()">
       <option value="彰化縣大村鄉茄苳村茄苳路二段53號" selected>現在的家
	   <option value="彰化縣大村鄉茄苳村茄苳路1段179巷15號">舊家   
	</select>

	

	<div id="myMap" style="width:800px;height:600px;"></div>
</body>
</html>