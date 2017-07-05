// JavaScript Document
console.log("import calculate_age(age)");
/*計算年齡用(輸出字串)
 *比較順序:日>月>年
 */
function age_getAge_inString(dateString, examDay){
	//console.log("function getAge_inString(dateString)");		
    if(examDay == 'today'){
		var today = new Date();
	}else{
		var today = new Date(examDay);
	}	
    var birthDate = new Date(dateString);
    var y = birthDate.getFullYear() - today.getFullYear();
		y *= -1;
    var m = birthDate.getMonth() - today.getMonth();
	var d = birthDate.getDate() - today.getDate();	
	var m_inBirthday = age_how_many_days_in_this_month(birthDate.getFullYear(), birthDate.getMonth());	
	var strMandarin=strEnglish="";
		var try_today = new Date(1908, today.getMonth(), today.getDate());
		var try_birthdate = new Date(1908, birthDate.getMonth(), birthDate.getDate());
		var try_day = try_birthdate.getTime() - try_today.getTime();//測試今年生日過了沒
    		if(try_day < 0){//今年生日已過				
				if(d < 0){//今天生日已過
					m = today.getMonth() - birthDate.getMonth();//月就等於上面相減的
					d = today.getDate() - birthDate.getDate();//天就等於上面相減的			
				}else{//今天生日未到
					m = today.getMonth() - birthDate.getMonth() - 1;//尚未滿月
					d = m_inBirthday - birthDate.getDate() + today.getDate();//生日當月減掉還沒出生的天數再加上當月已過天數
				}
			}else if(try_day == 0){//今天生日	
				m = 0;//月就等於上面相減的
				d = 0;//天就等於上面相減的			
			}else{//今年生日未到				
				y -= 1;
				if(d < 0){//今天生日已過
					m = 12 - (birthDate.getMonth() + 1) + (today.getMonth() + 1);//生日當月減掉還沒出生的月數再加上當月已過月數
					d = today.getDate() - birthDate.getDate();//天就等於上面相減的			
				}else{
					m = 12 - (birthDate.getMonth() + 1) + (today.getMonth() + 1) - 1;//尚未滿月
					d = m_inBirthday - birthDate.getDate() + today.getDate();//生日當月減掉還沒出生的天數再加上當月已過天數
				}
			}
		strMandarin=y+"歲"+m+"月"+d+"天";		
		strEnglish=y+"Y"+m+"MO";
		var str=[strMandarin,strEnglish];
    	return str;
}
/*計算年齡用(輸出2位小數)
 */
function age_getAge_inDecimal(dateString, examDay){
	//console.log("function getAge_inDecimal(dateString)"); 
	const msInYear = 31556926000; //1 years = 31556926000 milliseconds
	if(examDay == 'today'){
		var today = new Date();
	}else{
		var today = new Date(examDay);
	}
    var birthDate = new Date(dateString);
	var age_ms = today.getTime() - birthDate.getTime();
	var age_y = age_ms / msInYear; 
	var age_round = Math.round(age_y * 100) / 100;
		return age_round;
}
/*回傳生日當月有幾天
 */
function age_how_many_days_in_this_month(what_year, what_month){
	//console.log("function how_many_days_in_this_month(what_year, what_month)");
	var m = -999;
	var y = what_year % 4;
    	switch(what_month){
 	    	case 0:		m = 31;		break;	//01月
   	     	case 1:
				m = ((y == 0)?29:28);	break;
			case 2:     m = 31;		break;	//03月
   	     	case 3:		m = 30;		break;	//04月
 	  	    case 4:		m = 31;		break;	//05月
 	  	    case 5:		m = 30;		break;	//06月
   		    case 6:		m = 31;		break;	//07月
   		    case 7:		m = 31;		break;	//08月
   		    case 8:		m = 30;		break;	//09月
   		    case 9:		m = 31;		break;	//10月
   		    case 10:	m = 30;		break;	//11月
   		    case 11:	m = 31;		break;	//12月
			default:	m = 30;		break;	//沒有月
		}
		return m; 
}
/*取得生長曲線的參數-把年月齡轉成小數
  例如6y6mo=>6.5yo
 */
function age_get_percentile_array_year_month_old_to_decimal(arr_yearOld, arr_monthOld){
	//console.log("function get_percentile_array_year_month_old_to_decimal(arr_yearOld, arr_monthOld)");
	var iStop = arr_yearOld.length;
	var i = 0;
	var age;
	var arr_age = new Array();
		for(i=0;i<iStop;i++){
			age = arr_yearOld[i] + (arr_monthOld[i] / 12);			
			arr_age.push(age);
		}
		return arr_age;
}