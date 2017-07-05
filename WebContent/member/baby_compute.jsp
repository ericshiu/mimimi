<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="">
	<head>
	<title>媽媽健康紀錄查詢</title>
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
  		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/title.css">
		
  		<link rel='stylesheet prefetch' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css'>
		<link rel='stylesheet prefetch' href='http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css'>
		
		
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/css.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/timecss.css">

		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/member/css/table.css">
		<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/6.0.0/normalize.min.css">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/titlehover.css">
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/css.css">
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
						    <h1 class="">生長曲線計算機</h1>
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
				 
					  <div class="title-word">一、0-5歲之體位，係採用世界衛生組織(WHO0公布之「國際嬰幼兒生長標準」。</div>
					  <div class="title-word">二、7-18 歲之體位標準曲線，係依據1997 年台閩地區中小學學生體適能(800/1600 公尺跑走、屈膝
　　仰臥起坐、立定跳遠、坐姿體前彎四項測驗成績皆優於25 百分位值之個案)檢測資料。</div>
					  <div class="title-word">三、5-7 歲銜接點部份，係參考WHO BMI rebound 趨勢，銜接前揭兩部份數據。
</div>
					  <div class="title-word">備註：檢測結果僅供參考，如有生長發育之疑義，請逕洽兒科或家醫科醫師。</div>
					  
					</div>


					
					</div>
					</div>	
					</div>
					</div>

<div class="container">
			<div class="row">
<div class="col-xs-12 col-sm-12">

	<div class="col-xs-12 col-sm-6"><div class="form_main" ">
							<form>
							  <fieldset>
							  	<legend>男(male)</legend>
							  	<div>
							      <fieldset><legend>小孩生日-民國年(child's birthday on Republic of China calendar)</legend>
							        <b class="ROC_year">民國</b>(year):
							          <input type="number" id="inp_year_m" min="60" max="105" onkeypress="return gd_isIntegerKey(event);" 
							          onblur="fool_check_range('male','percentile');"><span id="inp_year_error_m"></span>
							        /月(month):<input type="number" id="inp_month_m" min="1" max="12" onkeypress="return gd_isIntegerKey(event);"
							          onblur="fool_check_range('male','percentile');"><span id="inp_month_error_m"></span>
							        /日(day):<input type="number" id="inp_day_m" min="1" max="31" onkeypress="return gd_isIntegerKey(event);"
							          onblur="fool_check_range('male','percentile');"><span id="inp_day_error_m"></span>    
							      </fieldset>  
							      <fieldset><legend>小孩身高/體重(child's height/weight)</legend>
							        身高(height):<input type="number" id="inp_height_m" min="25" max="255" onkeypress="return gd_isNumberKey(event);"
							          onblur="fool_check_range('male','percentile');"><b class="units">公分</b><span id="inp_height_error_m"></span>
							        /體重(weight):<input type="number" id="inp_weight_m" min="25" max="255" onkeypress="return gd_isNumberKey(event);"
							          onblur="fool_check_range('male','percentile');"><b class="units">公斤</b><span id="inp_weight_error_m"></span><br />
							        出生體重(Baby's Birth Weight):<input type="number" id="inp_bbw_m" min="400" max="6000" onkeypress="return gd_isIntegerKey(event);" 
							          onblur="fool_check_range('male','bbw');"><b class="units">公克</b><span id="inp_bbw_error_m"></span>
							      </fieldset>      
							    </div>    
							    <div>

							      <fieldset><legend>父母身高(parents' height)</legend>
							        父高(father):<input type="number" id="inp_height_father_m" min="25" max="255" onkeypress="return gd_isNumberKey(event);"
							          onblur="fool_check_range('male','percentile');"><b class="units">公分</b><span id="inp_height_father_error_m"></span>
							        /母高(mother):<input type="number" id="inp_height_mother_m" min="25" max="255" onkeypress="return gd_isNumberKey(event);"
							          onblur="fool_check_range('male','percentile');"><b class="units">公分</b><span id="inp_height_mother_error_m"></span>
							      </fieldset>
							    </div>
							    <div class="button_align">       
							      <button type="button" id="button_m" onclick="cp_calculate_above_or_below_line('male');">計算!(submit)</button>&nbsp;&nbsp;    
							      <button type="reset" onclick="getElementById('result_m').innerHTML='';">清空!(reset)</button>&nbsp;&nbsp;  
							      <button type="button" id="button_predict_m" class="button_predict" 
							        onclick="fool_hide_advance('male');">骨齡預測身高</button>    
							      <button type="button" onclick=child_hw() >神奇小按鈕</button>&nbsp;&nbsp;
							    </div>  
							  </fieldset>
							  <div id="result_m"></div> 
							</form>     

							<!-- 預測身高 -->
							<form class="form_advance" id="form_predict_m" style="display:none;">
							  <fieldset><legend>用骨齡預測身高(predict height)</legend>       
							    <fieldset><legend>骨齡(bone age)</legend>      
							      歲(year):<input type="number" id="inp_boneAge_year_ph_m" min="0" max="105" onkeypress="return gd_isIntegerKey(event);" 
							        onblur="fool_check_range('female','boneAge');"><span id="inp_year_ph_error_m"></span>
							      /月(month):<input type="number" id="inp_boneAge_month_ph_m" min="0" max="11" onkeypress="return gd_isIntegerKey(event);"
							        onblur="fool_check_range('female','boneAge');"><span id="inp_month_ph_error_m"></span>
							    </fieldset>      
							    <fieldset><legend>檢查日身高(height on exam day):</legend>
							      請輸日檢查骨齡當日量的身高:<input type="number" id="inp_height_ph_m" min="25" max="255" onkeypress="return gd_isNumberKey(event);" 
							        onblur="fool_check_range('female','boneAge');"><b class="units">公分</b><span id="inp_height_ph_error_m"></span>
							    </fieldset>
							    <div class="button_align">
							      <button type="button" id="button_ph_m" onclick="bone_calculate_predict_height('male');">計算!(submit)</button>&nbsp;&nbsp;          
							      <button type="reset" onclick="getElementById('result_ph_m').innerHTML='';">清空!(reset)</button>      
							      <button type="button" onclick=inp_boneAge() >神奇小按鈕</button>&nbsp;&nbsp;
							    </div>  
							  </fieldset>
							  <div id="result_ph_m"></div> 
							</form>
							</div>
</div>
<div class="col-xs-12 col-sm-2">
</div>
<div class="col-xs-12 col-sm-6">
	<div class="form_main" style="">
							<form>
							  <fieldset>
							  	<legend>
							    	女(female)
							    </legend>
							  	<div>
							      <fieldset><legend>小孩生日-民國年(child's birthday on Republic of China calendar)</legend>
							        <b class="ROC_year">民國</b>(year):
							          <input type="number" id="inp_year_f" min="60" max="105" onkeypress="return gd_isIntegerKey(event);" 
							          onblur="fool_check_range('female','percentile');"><span id="inp_year_error_f"></span>
							        /月(month):<input type="number" id="inp_month_f" min="1" max="12" onkeypress="return gd_isIntegerKey(event);"
							          onblur="fool_check_range('female','percentile');"><span id="inp_month_error_f"></span>
							        /日(day):<input type="number" id="inp_day_f" min="1" max="31" onkeypress="return gd_isIntegerKey(event);"
							          onblur="fool_check_range('female','percentile');"><span id="inp_day_error_f"></span>    
							      </fieldset>  
							      <fieldset><legend>小孩身高/體重(child's height/weight)</legend>
							        身高(height):<input type="number" id="inp_height_f" min="25" max="255" onkeypress="return gd_isNumberKey(event);"
							          onblur="fool_check_range('female','percentile');"><b class="units">公分</b><span id="inp_height_error_f"></span>
							        /體重(weight):<input type="number" id="inp_weight_f" min="25" max="255" onkeypress="return gd_isNumberKey(event);"
							          onblur="fool_check_range('female','percentile');"><b class="units">公斤</b><span id="inp_weight_error_f"></span><br />
							        出生體重(Baby's Birth Weight):<input type="number" id="inp_bbw_f" min="400" max="6000" onkeypress="return gd_isIntegerKey(event);" 
							          onblur="fool_check_range('female','bbw');"><b class="units">公克</b><span id="inp_bbw_error_f"></span>
							      </fieldset>
							    </div>    
							    <div>
							      <fieldset><legend>父母身高(parents' height)</legend>
							        父高(father):<input type="number" id="inp_height_father_f" min="25" max="255" onkeypress="return gd_isNumberKey(event);"
							          onblur="fool_check_range('female','percentile');"><b class="units">公分</b><span id="inp_height_father_error_f"></span>
							        /母高(mother):<input type="number" id="inp_height_mother_f" min="25" max="255" onkeypress="return gd_isNumberKey(event);"
							          onblur="fool_check_range('female','percentile');"><b class="units">公分</b><span id="inp_height_mother_error_f"></span>
							      </fieldset>
							    </div>
							    <div class="button_align">
							      <button type="button" id="button_f" onclick="cp_calculate_above_or_below_line('female');">計算!(submit)</button>&nbsp;&nbsp;
							      <button type="reset" onclick="getElementById('result_f').innerHTML='';">清空!(reset)</button>&nbsp;&nbsp;      
							      <button type="button" id="button_predict_f" class="button_predict" 
							        onclick="fool_hide_advance('female');">骨齡預測身高</button>
							       <button type="button" onclick=child_hw_f() >神奇小按鈕</button>&nbsp;&nbsp;
							    </div>    
							  </fieldset>
							  <div id="result_f"></div>
							</form>  

							<!-- 預測身高 -->
							<form class="form_advance" id="form_predict_f" style="display:none;">
							  <fieldset><legend>用骨齡預測身高(predict height)</legend>       
							    <fieldset><legend>骨齡(bone age)</legend>      
							      歲(year):<input type="number" id="inp_boneAge_year_ph_f" min="0" max="105" onkeypress="return gd_isIntegerKey(event);" 
							        onblur="fool_check_range('female','boneAge');"><span id="inp_year_ph_error_f"></span>
							      /月(month):<input type="number" id="inp_boneAge_month_ph_f" min="0" max="11" onkeypress="return gd_isIntegerKey(event);"
							        onblur="fool_check_range('female','boneAge');"><span id="inp_month_ph_error_f"></span>
							    </fieldset>      
							    <fieldset><legend>檢查日身高(height on exam day):</legend>
							      請輸日檢查骨齡當日量的身高:<input type="number" id="inp_height_ph_f" min="25" max="255" onkeypress="return gd_isNumberKey(event);" 
							        onblur="fool_check_range('female','boneAge');"><b class="units">公分</b><span id="inp_height_ph_error_f"></span>
							    </fieldset>
							    <div class="button_align">
							      <button type="button" id="button_ph_f" onclick="bone_calculate_predict_height('female');">計算!(submit)</button>&nbsp;&nbsp; 
							      <button type="reset" onclick="getElementById('result_ph_f').innerHTML='';">清空!(reset)</button>
							   	   <button type="button" onclick=inp_boneAge_f() >神奇小按鈕</button>&nbsp;&nbsp;
							    </div>  
							  </fieldset>
							  <div id="result_ph_f"></div> 
							</form>
							</div>
</div>
</div>
</div>
</div>

		<div style="height:200px;" ></div>
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
  		  <script src="<%=request.getContextPath() %>/member/js/growth_percentile_curve_20161020_01.js"></script>
		  <script src="<%=request.getContextPath() %>/member/js/gadget_20161021_01.js"></script>
		  <script src="<%=request.getContextPath() %>/member/js/calculate_age_20161020_01.js"></script>
		  <script src="<%=request.getContextPath() %>/member/js/calculate_percentile_20161020_01.js"></script>
		  <script src="<%=request.getContextPath() %>/member/js/calculate_bone_age_predict_20150413_01.js"></script>
		  <script src="<%=request.getContextPath() %>/member/js/fool_proofing_20161020_01.js"></script>
		  <script src="<%=request.getContextPath() %>/member/js/google_analytics_20161021_01.js"></script>

	</body>
</html>

<script>

function $id(id){
	return document.getElementById(id);
}

function child_hw(){
	
	$id('inp_year_m').value="105";
	$id('inp_month_m').value="12";
	$id('inp_day_m').value="19";
	
	$id('inp_height_m').value="90";
	$id('inp_weight_m').value="9";
	$id('inp_bbw_m').value="3500";
	
	$id('inp_height_father_m').value="180";
	$id('inp_height_mother_m').value="158";

}



function inp_boneAge(){
	$id('inp_boneAge_year_ph_m').value="1";
	$id('inp_boneAge_month_ph_m').value="6";
	$id('inp_height_ph_m').value="90";
}

function child_hw_f(){
	
	$id('inp_year_f').value="105";
	$id('inp_month_f').value="1";
	$id('inp_day_f').value="1";
	
	$id('inp_height_f').value="95";
	$id('inp_weight_f').value="8";
	$id('inp_bbw_f').value="3300";
	
	$id('inp_height_father_f').value="172";
	$id('inp_height_mother_f').value="160";

}

function inp_boneAge_f(){
	$id('inp_boneAge_year_ph_f').value="1";
	$id('inp_boneAge_month_ph_f').value="4";
	$id('inp_height_ph_f').value="80";
}


</script>

