// JavaScript Document
console.log("import calculate_bone_age_predict(bone)");
/*用骨齡預測身高
 *
 */
function bone_calculate_predict_height(strGender){	
	var str = ((strGender=='male')?"_m":"_f") 			
	var strBoneY = document.getElementById("inp_boneAge_year_ph" + str).value;
		strBoneY = parseInt(Nz(strBoneY, 0));
	var strBoneM = document.getElementById("inp_boneAge_month_ph" + str).value;		
		strBoneM = parseInt(Nz(strBoneM, 0));		
	var strHeight = document.getElementById("inp_height_ph" + str).value
		var strNoEnter = fool_check_ph(strHeight, 135);	
		var dblHeight  = parseFloat(Nz(strHeight, 135)); 				
	var strGenderToHTML = "性別：" + ((strGender == 'male')?"(男)":"(女)");	
	var intBoneAge = strBoneY + (strBoneM / 12);	
		intBoneAge = Math.round(intBoneAge * 100) / 100;	
	var strBoneAgeString = "您輸入的骨齡是:" + strBoneY + "歲" + strBoneM + "月(" + intBoneAge + "歲)"; 
	var strHeight = "您輸入的身高是:" + dblHeight + "cm";							
	var strPredictHeight = gd_determine_exact_height(intBoneAge ,dblHeight ,strGender, 'future');	
	var strInnerHTML = strGenderToHTML + "<br>" + strBoneAgeString  + "<br>" + strHeight + "<br>" + 
						strPredictHeight + strNoEnter + "<hr>";			
		document.getElementById("result_ph" + str).innerHTML = strInnerHTML;
}
