// JavaScript Document
console.log("import gadget(gd)");
/*因為年齡陣列不連續,所以必須判斷年齡是落在哪一個區間裡面,如果是落在12-13之間,會回傳12
 */
function gd_determine_age_interval(age){
	var arr_age = new Array;
		arr_age = cur_get_percentile_arr_age();
	var iStop = arr_age.length - 1;
	var falseToStop = true;
	var i = 0;		
		while(falseToStop){
			if((age >= arr_age[iStop])){//年齡超過範圍
				falseToStop = false;	i = iStop;				
			}else if((arr_age[i] <= age) && (arr_age[i+1] > age)){//找出年齡落在哪個範圍之內
				falseToStop = false;			
			}else{
				i++;//皆不合條件才累加
			}
		}
		return i;
}
/*利用內插法計算年齡在函數之上或是之下-回傳該年齡五條線上的身高或是體重
 *年齡在範圍計算斜率,年齡大於範圍斜率為1
 *arr_value		生長曲線陣列
 *ageInterval	年齡區間(因為是用內插法做所以年齡是一個段落一個段落的)
 *age			實際年齡
 */
function gd_calculate_above_or_below_determineValueOnLine(arr_value, ageInterval, age){
	//console.log("function calculate_above_or_below_determineValueOnLine(arr_value, ageInterval, age)");
	var arr_age = new Array();	arr_age = cur_get_percentile_arr_age();
	var i = ageInterval;
	var iStop = (arr_age.length - 1);
	var valueOnLine = 0.01;		
		if(i >= iStop){//年齡大於範圍,回傳最後一點的值
			valueOnLine = arr_value[iStop];
		}else{//年齡在範圍之內,用內插法計算線上值
			valueOnLine = (((age - arr_age[i]) * (arr_value[i+1] - arr_value[i]))/(arr_age[i+1] - arr_age[i])) + arr_value[i];
		}
		return valueOnLine
}
/*如果為空值的話回傳0
 */
function Nz(fieldToCheck, valueIfEmpty){
	//console.log("function Nz(fieldToCheck, valueIfEmpty)");
	var rtn = 0;
		if(valueIfEmpty != null){
			rtn = valueIfEmpty;
		}
		if(fieldToCheck != ""){	
			rtn = fieldToCheck;
		}
		return rtn;
}
/*檢查輸入的東西是否為數字(整數) 
 */
function gd_isIntegerKey(evt){
    var charCode = (evt.which) ? evt.which : event.keyCode
		if((charCode > 31 && charCode < 48) || charCode > 57)
			return false;
		return true;
}
/*檢查輸入的東西是否為數字(浮點數) 
 */
function gd_isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : event.keyCode
		if((charCode > 31 && charCode < 48 && charCode != 46) || charCode > 57)
			return false;
		return true;
}
/*debug用，看瀏覽器的寬度
 */
function gd_debug_resize(){
    var w = parseInt(window.outerWidth);
    var h = parseInt(window.outerHeight);
	var x = "User-agent header sent: " + navigator.userAgent;    	
    var txt = "Window size: width=" + w + ", height=" + h + "<br>" + x;	
	    document.getElementById("body_resize").innerHTML = txt;	
}
/*用骨齡預測身高的建構子，做法是判斷病患落於哪一個區間，用區間的比例，對到生長曲線尾端區間的比例，算出預測身高
 *intAge	測驗日骨齡
 *dblHeight	測驗日身高
 *strGender	性別
 */
function gd_determine_exact_height(intAge ,dblHeight ,strGender ,nowOrFuture){
	//console.log("function determine_future_height(intAge ,dblHeight ,strGender)");	
	var i = gd_determine_age_interval(intAge);	
	var dblFutureHeight = 0.01;
	var strRtn = '';	
	var strHeightPercentile = '';
	if(nowOrFuture == 'future'){		
		strHeightPercentile = cp_calculate_above_or_below_line_determinePercentile(dblHeight, strGender, i, intAge, "height");
	}else{
		var arrAge = new Array();
			arrAge = cur_get_percentile_arr_age();
		var aStop = arrAge.length - 1; 	
		var AgeMax = arrAge[aStop];
		console.log("aStop:" + aStop); 		
		console.log("AgeMax:" + AgeMax);
		strHeightPercentile = cp_calculate_above_or_below_line_determinePercentile(dblHeight, strGender, aStop, AgeMax, "height");	
	}
		switch(strHeightPercentile){
			case "<3%":
			case "3%-15%":
				dblFutureHeight = gd_determine_exact_percentile_in_interval("=3%", "=15%", strGender, intAge, dblHeight ,nowOrFuture);
				break;			
			case "15%-50%":
				dblFutureHeight = gd_determine_exact_percentile_in_interval("=15%", "=50%", strGender, intAge, dblHeight ,nowOrFuture);
				break;
			case "50%-85%":
				dblFutureHeight = gd_determine_exact_percentile_in_interval("=50%", "=85%", strGender, intAge, dblHeight ,nowOrFuture);
				break;
			case "85%-97%":					
			case ">97%":
				dblFutureHeight = gd_determine_exact_percentile_in_interval("=85%", "=97%", strGender, intAge, dblHeight ,nowOrFuture);
				break;
			case "=3%":
			case "=15%":
			case "=50%":
			case "=85%":
			case "=97%":
				if(nowOrFuture == 'future'){
					dblFutureHeight = gd_determine_end_height_on_line(strHeightPercentile, strGender);
				}else{
					dblFutureHeight = gd_determine_exact_height_on_line(strHeightPercentile, strGender, intAge);
				}	
				break;					
		}
		str_nowOrFuture_rtn = (nowOrFuture == 'future')?"<b>骨齡</b>預測未來身高":"<b>遺傳身高</b>預測現在身高";		
		return strRtn = str_nowOrFuture_rtn + "約在:" + dblFutureHeight + "cm附近, (百分位:" + strHeightPercentile + ")";
} 
/*算出目前現在(遺傳)身高占百分位區間的比例，推估遺傳(現在)佔百分位的比例然後求出遺傳(現在)身高
 *strPercentile_min	生長曲線下界
 *strPercentile_max	生長曲線上界
 *strGender			性別
 *age				年齡
 *dblHeight			身高
 *nowOrFuture		預測現在或未來
 */
function gd_determine_exact_percentile_in_interval(strPercentile_min, strPercentile_max, strGender, age, dblHeight ,nowOrFuture){
	var bMaxNow = gd_determine_exact_height_on_line(strPercentile_max, strGender, age);
	var bMinNow = gd_determine_exact_height_on_line(strPercentile_min, strGender, age);
	var bMaxFuture = gd_determine_end_height_on_line(strPercentile_max, strGender);
	var bMinFuture = gd_determine_end_height_on_line(strPercentile_min, strGender);
	var parameter = 0.01;
	var dblRtn = 0.01;
		if(nowOrFuture == 'future'){
			parameter = (dblHeight - bMinNow)/(bMaxNow - bMinNow);
			dblRtn = ((bMaxFuture - bMinFuture) * parameter) + bMinFuture;
		}else{
			parameter = (dblHeight - bMinFuture)/(bMaxFuture - bMinFuture);
			dblRtn = ((bMaxNow - bMinNow) * parameter) + bMinNow;
		}
		dblRtn = Math.round(dblRtn * 100) / 100;   
		return dblRtn;	//因為有四捨五入，所以沒有出現浮點運算奇怪的值
}
/*傳入百分位及年齡並回傳該年齡在該百分位上的值
 *
 */
function gd_determine_exact_height_on_line(str_percentile, str_gender, age){
	//console.log("function determine_exact_height_on_line(str_percentile, str_gender, age)");
	var arr_value = new Array();	//五條生長曲線陣列
		arr_value = gd_get_height_array(str_percentile, str_gender);	
	var valueOnLine = 0.01;
	var ageInterval = gd_determine_age_interval(age);		
		valueOnLine = gd_calculate_above_or_below_determineValueOnLine(arr_value, ageInterval, age);
		return valueOnLine;
}
/*回傳該百分位最後一個值
 *
 */
function gd_determine_end_height_on_line(str_percentile, str_gender){
	//console.log("function determine_end_height_on_line(str_percentile, str_gender)");
	var arr_value = new Array();	//五條生長曲線陣列
		arr_value = gd_get_height_array(str_percentile, str_gender);	
	var iStop = arr_value.length - 1;
	var dblRtn = arr_value[iStop];
		return dblRtn;		
}
/*回傳身高百分位陣列
 *
 */
function gd_get_height_array(str_percentile, str_gender){
	//console.log("function get_height_array(str_percentile, str_gender)");
	var arr = new Array();		arr = cur_get_percentile_arr_gender(str_gender);	
	var arr_value = new Array();	//五條生長曲線陣列
	var i = 0;
		switch(str_percentile){
			case "=3%":
				i = 0;	break;
			case "=15%":
				i = 1;	break;
			case "=50%":
				i = 2;	break;
			case "=85%":
				i = 3;	break;
			case "=97":
				i = 4;	break;
		}
		arr_value = arr[i][0];//0=身高,1=體重	
		return arr_value;		
}
function gd_numOk(obj){
    var rtn=(isNaN(parseInt(obj))?false:true);
		if(rtn){
			rtn=(parseInt(obj)==0?false:true);
			if(rtn){
				rtn=(parseInt(obj)<0?false:true);
			}
		}
	return rtn;
}