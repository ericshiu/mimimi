// JavaScript Document
console.log("import calculate_percentile(cp)");
/*利用內插法計算年齡在函數之上或是之下*/
function cp_calculate_age(strGender){
	/*回傳array
	 *0->能不能計算(boolean)
	 *1->輸入的日期(string中文)
	 *2->年齡(decimal)
	 *3->年齡(string中文)
	 *4->年齡(string英文)
	 */
	var trueToGo=false;
	var ageDecimal=0.0;
	var ageMandarin=ageEnglish="";	 	
	var strBirth="";
	var strAgeY=document.getElementById("inp_year" +strGender).value;
	var strAgeM=document.getElementById("inp_month"+strGender).value;
	var strAgeD=document.getElementById("inp_day"  +strGender).value;
	var height=document.getElementById("inp_height"+strGender).value; 
	var weight=document.getElementById("inp_weight"+strGender).value; 		
		if(gd_numOk(strAgeY)&&gd_numOk(strAgeM)&&gd_numOk(strAgeD)&&gd_numOk(height)&&gd_numOk(weight)){
			trueToGo=true;
			strAgeY=parseFloat(Nz(strAgeY)); strAgeM=parseFloat(Nz(strAgeM)); strAgeD=parseFloat(Nz(strAgeD));
			strBirth="您輸入的生日是民國:"+strAgeY+"年"+strAgeM+"月"+strAgeD+"日";
			strAgeY += 1911;			
			var strDateString=strAgeY+"/"+strAgeM+"/"+strAgeD;		
			var age=age_getAge_inString(strDateString,'today');				
			ageDecimal=age_getAge_inDecimal(strDateString, 'today');//這個是給電腦看的，不顯示在人類的介面					
			ageMandarin="年齡:"+age[0];
			ageEnglish="age:"+age[1];		 
		}
	var rtn=[trueToGo,strBirth,ageDecimal,ageMandarin,ageEnglish];
		return rtn;
}
function cp_calculate_bbw(strGender){	
	var bbw=document.getElementById("inp_bbw" + strGender).value;
	var rtn=(gd_numOk(bbw)?(",bbw:"+bbw+"g"):"");
		return rtn;
}
function cp_calculate_parent_height(strGender){
	var strMandarin=strEnglish="";	 
	var heightFather=document.getElementById("inp_height_father"+strGender).value; 
	var heightMother=document.getElementById("inp_height_mother"+strGender).value; 
		if(gd_numOk(heightFather)&&gd_numOk(heightMother)){
			heightFather=parseFloat(Nz(heightFather)); 
			heightMother=parseFloat(Nz(heightMother)); 
			var heightGenetic = (heightFather + heightMother)/2;
			heightGenetic = Math.round(heightGenetic * 100) / 100;						
			heightGenetic+=((strGender=='_m')?6:(-6));		
			strMandarin="父高:"+heightFather+"cm,母高:"+heightMother+"cm,遺傳身高:"+heightGenetic+"cm<br>";	
			strEnglish="Father BL:"+heightFather+"cm,Mother BL:"+heightMother+"cm,Target BL:"+heightGenetic+"cm";	
		}
	var rtn=[strMandarin,strEnglish];
		return rtn;
}
/*利用內插法計算年齡在函數之上或是之下-判斷是哪一個年齡區間
 *age 			→病人目前的年齡 
 *str_gender	→病患性別
 */
function cp_calculate_above_or_below_line_determineAge(age, str_gender){
	("function calculate_above_or_below_line_determineAge(age, arr_value, str_gender)");	
	var height=parseFloat(document.getElementById("inp_height"+str_gender).value); 
	var weight=parseFloat(document.getElementById("inp_weight"+str_gender).value);
	var i=gd_determine_age_interval(age);	
	var strHeight=cp_calculate_above_or_below_line_determinePercentile(height, str_gender, i, age, "height");
	var strWeight=cp_calculate_above_or_below_line_determinePercentile(weight, str_gender, i, age, "weight");			
	var strHeightMandarin="身高:"+height+"cm(百分位:"+strHeight+"),";
	var strWeightMandarin="體重:"+weight+"kg(百分位:"+strWeight+")";
	var strMandarin=strHeightMandarin+strWeightMandarin;
	var strHeightEnglish="BL:"+height+"cm("+strHeight+"),";
	var strWeightEnglish="BW:"+weight+"kg("+strWeight+")";
	var strEnglish=strHeightEnglish+strWeightEnglish;
	var strRtn=[strMandarin,strEnglish];
		return strRtn;		
}
function cp_calculate_above_or_below_line(strGender){		
	var strInnerHTML='<h1><font color="red">至少必須輸入生日及身高體重!</font></h1>';
	var str=((strGender=='male')?"_m":"_f");
	var age=cp_calculate_age(str);
	var trueToGo=   age[0];//0->能不能計算(boolean)
	var dateInput=  age[1];//1->輸入的日期(string中文)
	var ageDecimal= age[2];//2->年齡(decimal)
	var ageMandarin=age[3];//3->年齡(string中文)
	var ageEnglish= age[4];//4->年齡(string英文)
		if(trueToGo){			
			var strGenderToHTML="性別:"+((strGender=='male')?"(男)":"(女)");
			var bbw=cp_calculate_bbw(str);			
			var percentile=cp_calculate_above_or_below_line_determineAge(ageDecimal,str); 
			var geneticBL=cp_calculate_parent_height(str);	
			var strHead=strGenderToHTML+","+ageMandarin+"<br>";
			var strMandarin=percentile[0]+"<br>"+geneticBL[0];
			var strSeparate="♫♪♬♩♪♫.•*¨`*•..¸♥ೋღ❤ღೋ♥¸.•*¨`*•.♫♪♩♬♪♫"+"<br><br>";
			var strEnglish=percentile[1]+bbw+"<br>"+geneticBL[1];
			strInnerHTML=strHead+strMandarin+strSeparate+strEnglish; 		
		}
		//var strheightGenetic = cp_calculate_above_or_below_line_determineAge_and_parents_geneticHeight(intAge, heightGenetic, strGender);
		document.getElementById("result" + str).innerHTML = strInnerHTML;
}
/*利用內插法計算年齡在函數之上或是之下-判斷父母的遺傳身高是哪一個年齡區間 + 遺傳身高在目前年齡的身高
 *age 			→病人目前的年齡
 *geneticHeight	→父母遺傳身高
 *str_gender	→病患性別
 */
function cp_calculate_above_or_below_line_determineAge_and_parents_geneticHeight(age, geneticHeight, str_gender){
	//console.log("function calculate_above_or_below_line_determineAge(age, arr_value, str_gender)");	
	var arrAge = new Array();
		arrAge = cur_get_percentile_arr_age();
	var aStop = arrAge.length - 1; 	
	var AgeMax = arrAge[aStop];
	var strHeight = cp_calculate_above_or_below_line_determinePercentile(geneticHeight, str_gender, aStop, AgeMax, "height");	
		strHeight = "父母遺傳身高為:" + geneticHeight + "cm, (百分位:" + strHeight + ")<br>";
	var geneticHeight_AtThisAge = gd_determine_exact_height(age ,geneticHeight ,str_gender ,'now');		
	var strRtn = strHeight + geneticHeight_AtThisAge;
		return strRtn;		
} 

/*利用內插法計算年齡在函數之上或是之下
 *age					→	目前的年齡(decimal)
 *value					→	使用者輸入的身高或是體重 
 *str_gender			→	性別("male" or "female")
 *ageInterval			→	年齡區間(因為是用內插法做所以年齡是一個段落一個段落的)
 *str_heightOrWeight	→	身高或體重("height" or "weight")(合併在一個function裡面可以執行)
 */
function cp_calculate_above_or_below_line_determinePercentile(value, str_gender, ageInterval, age, str_heightOrWeight){
	//console.log("function calculate_above_or_below_line_determinePercentile(value, str_gender, ageInterval, age, str_heightOrWeight)");
	var arr = new Array();		arr = cur_get_percentile_arr_gender(str_gender);	
	var arr_value = new Array();	//五條生長曲線陣列			
	var i = 0;
	var iStop = 5;
	var valueOnLine = 0.01;
	var falseToStop = true;
	var strRtn = "";			
		while(falseToStop && (i < iStop)){			
			arr_value = (str_heightOrWeight == "height")?arr[i][0]:arr[i][1];//0=身高,1=體重						
			valueOnLine = gd_calculate_above_or_below_determineValueOnLine(arr_value, ageInterval, age)
			if(i == 0){//3%以下
				if(value < valueOnLine){
					falseToStop = false;	strRtn = "<3%";
				}else if(value == valueOnLine){
					falseToStop = false;	strRtn = "=3%";
				}//如果是大於就繼續往下跑
			}else if((i > 0) && (i <= 3)){//3%-97%
				switch(i){
					case 1:
						if(value < valueOnLine){
							falseToStop = false;	strRtn = "3-15%";
						}else if(value == valueOnLine){
							falseToStop = false;	strRtn = "=15%";
						}//如果是大於就繼續往下跑	
						break;
					case 2:
						if(value < valueOnLine){
							falseToStop = false;	strRtn = "15-50%";
						}else if(value == valueOnLine){
							falseToStop = false;	strRtn = "=50%";
						}//如果是大於就繼續往下跑
						break;
					case 3:
						if(value < valueOnLine){
							falseToStop = false;	strRtn = "50-85%";
						}else if(value == valueOnLine){
							falseToStop = false;	strRtn = "=85%";
						}//如果是大於就繼續往下跑
						break;
				}				
			}else{//97%以上
				if(value < valueOnLine){
					falseToStop = false;	strRtn = "85-97%";
				}else if(value == valueOnLine){
					falseToStop = false;	strRtn = "=97%";
				}else{
					falseToStop = false;	strRtn = ">97%";
				}
			}			
			i++;
		}		
		return strRtn;
}
