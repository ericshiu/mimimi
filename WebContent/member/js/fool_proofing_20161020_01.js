// JavaScript Document
console.log("import fool_proofing(fool)");
/*隱藏進階功能
 *
 */
function fool_hide_advance(gender){
	var strGender = ((gender == 'male')?'_m':'_f');	
	var id = 'form_predict' + strGender;	
	var o = document.getElementById(id);
		if( o.style.display=='inline'){
			o.style.display='none';
    	}else{
    		o.style.display='inline';    
		}
}
/*檢查數值有沒有在範圍內(百分位)
 *每次都全檢查，但是分男女
 */
function fool_input_array(){
	var d = new Date();		var thisYear = d.getFullYear() - 1911;  	
	var arr = new Array();
	var input = new Array();	var minVal = new Array();	var maxVal = new Array();	var strErr = new Array();	
		input.push("inp_year");				minVal.push(0);		maxVal.push(thisYear);	strErr.push("inp_year_error");			//0
		input.push("inp_month");			minVal.push(1);		maxVal.push(12);		strErr.push("inp_month_error");			//1
		input.push("inp_day");				minVal.push(1);		maxVal.push(31);		strErr.push("inp_day_error");			//2
		input.push("inp_height");			minVal.push(25);	maxVal.push(255);		strErr.push("inp_height_error");		//3
		input.push("inp_weight");			minVal.push(0.1);	maxVal.push(255);		strErr.push("inp_weight_error");		//4
		input.push("inp_bbw");			    minVal.push(400);	maxVal.push(6000);		strErr.push("inp_bbw_error");   		//5
		input.push("inp_height_father");	minVal.push(25);	maxVal.push(255);		strErr.push("inp_height_father_error");	//6
		input.push("inp_height_mother");	minVal.push(25);	maxVal.push(255);		strErr.push("inp_height_mother_error");	//7
		arr.push(input);					arr.push(minVal);	arr.push(maxVal);		arr.push(strErr);
		return arr;
}
/*檢查數值有沒有在範圍內(骨齡)
 *每次都全檢查，但是分男女
 */
function fool_input_array_ph(){	
	var arr = new Array();
	var input = new Array();	var minVal = new Array();	var maxVal = new Array();	var strErr = new Array();	
		input.push("inp_boneAge_year_ph");	minVal.push(0);		maxVal.push(105);	strErr.push("inp_year_ph_error");		//0
		input.push("inp_boneAge_month_ph");	minVal.push(0);		maxVal.push(11);	strErr.push("inp_month_ph_error");		//1		
		input.push("inp_height_ph");		minVal.push(25);	maxVal.push(255);	strErr.push("inp_height_ph_error");	//2
		arr.push(input);					arr.push(minVal);	arr.push(maxVal);	arr.push(strErr);
		return arr;
}
/*檢查數值有沒有在範圍內
 *生長曲線和骨齡預測共用同一個
 */
function fool_check_range(gender, percentile_or_boneAgePredict){		
	var trueToDisable = false;
	var strGender = ((gender == 'male')?'_m':'_f');	
	var strRtn = "";		var check = "";
	var arr = new Array();		
		arr = (percentile_or_boneAgePredict == 'percentile')?fool_input_array():fool_input_array_ph();
	var input = new Array();	input  = arr[0];	
	var minVal = new Array();	minVal = arr[1];
	var maxVal = new Array();	maxVal = arr[2];
	var strErr = new Array();	strErr = arr[3];
	var i = 0;
	var iStop = input.length;	
		for(i=0;i<iStop;i++){
			input[i] += strGender;	strErr[i] += strGender;	strRtn = "";//初始化
			check = parseFloat(document.getElementById(input[i]).value);
			if(check != 0){
				if(check < minVal[i] || check > maxVal[i]){
					strRtn = "<b>(輸入的數值必須介於<i>" + minVal[i] + "</i>和<i>" + maxVal[i] + "</i>之間，不然會當機!!)</b>";
					trueToDisable = true;
				}
			}			
			document.getElementById(strErr[i]).innerHTML = strRtn;
		}
		if(trueToDisable == false){
			trueToDisable = fool_checkDateValidate(gender);
		}
	var strButton = (percentile_or_boneAgePredict == 'percentile')?"button":"button_ph";
		strButton += strGender;		
		if(trueToDisable){
			document.getElementById(strButton).disabled = true;
		}else{
			document.getElementById(strButton).disabled = false;
		}
}

/*檢查輸入的日期合不合法，目前有兩個要檢查的項目
 *1.檢查日期是不是大於今天
 *2.檢查輸入的月份是不是大於當月的日數 
 */
function fool_checkDateValidate(gender){	
	var strRtn = "";
	var strGender = ((gender == 'male')?'_m':'_f');	
	var input = new Array();		
		input.push("inp_year");		
		input.push("inp_month");	
		input.push("inp_day");		
	var i = 0;	var iStop = input.length;	
		for(i=0;i<iStop;i++){
			input[i] = input[i] + strGender;
		}
	var strCheckY = document.getElementById(input[0]).value;
	var strCheckM = document.getElementById(input[1]).value;
	var strCheckD = document.getElementById(input[2]).value;			
	var checkY = parseFloat(strCheckY);
	var checkM = parseFloat(strCheckM);
	var checkD = parseFloat(strCheckD);	
	if(strCheckY != "" && checkY == 0){
		strRtn = "年不可以輸入「0」!請檢查!";
	}
	if(strCheckM != "" && checkM == 0){
		strRtn = "月不可以輸入「0」!請檢查!";
	}
	if(strCheckD != "" && checkD == 0){
		strRtn = "日不可以輸入「0」!請檢查!";
	}
	if(checkY > 0 && checkM > 0 && checkD > 0){//年月日都有輸入的情形之下日期必須小於等於今天
		checkY += 1911;
		var strDateString = checkY + "/" + checkM + "/" + checkD;
		var d_now = new Date();
		var d_by_user = new Date(strDateString);
		if(d_by_user.getTime() >= d_now.getTime()){
			strRtn = "輸入的日期有錯!您輸入的生日日期大於今天!請檢查!\n";
		}		
	}	
	if(checkM > 0 && checkD > 0){//月日有輸入的情形之下一個月的天數必須合理
		var int_month_in_javascript = checkM - 1;
		var days_in_the_month = age_how_many_days_in_this_month(checkY, int_month_in_javascript);
		if(checkD > days_in_the_month){
			strRtn = "輸入的日期有錯!您輸入的日期大於" + checkM + "月日期，" + checkM + "月只有" + days_in_the_month + "天!請檢查!"
		}		
	}	
	if(strRtn != ""){
		alert(strRtn); 
		return true;//trueToDisable
	}else{		
		return false;
	}
}
/*因為年月日沒有輸入會當機，所以這個是檢查使用者有沒有輸入年月日用的
 */
function fool_checkYearMonthDay(year,month,day){
	//console.log("function checkYearMonthDay(year,month,day)");
	var strRtn = "";	
		if(year == ""){strRtn += "<br>因為沒有輸入「年」會當機，所以「年」暫時帶入「民國60年」";}
		if(month == ""){strRtn += "<br>因為沒有輸入「月」會當機，所以「月」暫時帶入「1月」";}
		if(day == ""){strRtn += "<br>因為沒有輸入「日」會當機，所以「日」暫時帶入「1日」";}
		return strRtn;
}
/*因為身高沒有輸入會當機，所以這個是檢查使用者有沒有輸入身高用的
 */
function fool_check_ph(height_input, height_default){
	//console.log("function checkYearMonthDay(year,month,day)");
	var strRtn = "";			
		if(height_input == ""){strRtn += "<br>因為沒有輸入「身高」會當機，所以「身高」暫時帶入「" + height_default + "公分」";}
		return strRtn;
}