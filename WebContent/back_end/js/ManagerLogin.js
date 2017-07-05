function $id(id) {
	return document.getElementById(id);
}


function Btn(){
	if ($id("checkid").innerHTML=="此帳號可以使用" && $id("checkname").innerHTML=="正確" && $id("checkemail").innerHTML=="正確"){
	$id("Btn").disabled=false;
}else{
	$id("Btn").disabled=true;
}
}

function getInfo() {
	var xhr = new XMLHttpRequest();

	// 設定好回呼函數
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {

			if (xhr.status == 200) {
				var text = xhr.responseText;
				var idvalue = $id("_id").value;
				var idvaluecheck = "\"" + idvalue + "\""; // 因為indexOF只要有包含就算有，所以必須回傳的帳號與要驗證的帳號前後都加"，來鎖定ID的比對
				
				var id=$id("_id").value;
				var idReg = new RegExp("[(a-zA-Z0-9_)]{6,10}$");
				
				if (id=="" || id.length <=5 || id.length >10){
					$id("checkid").innerHTML="6~10英數字";
					Btn();
				}else if (!id.match(idReg)){   //有 match到為ture，但這邊有加!
					$id("checkid").innerHTML="格式錯誤";
					Btn();
				}else{
					if (text.indexOf(idvaluecheck) != -1) {

						$id("checkid").innerHTML = "重複，請更換帳號!!";
						Btn();
					} else {

						$id("checkid").innerHTML = "此帳號可以使用";
						 Btn();
						
					}
					
					
				}

			} else {
				alert(xhr.getAllResponseHeaders());
				alert(xhr.status);

			}// xhr.status == 200
		}// xhr.readyState == 4
	}// onreadystatechange

	// 建立好Post連接
	//
	var url = null;
	
		url = "man_idCheck.jsp";

	xhr.open("post", url, true);
	xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
	// 送出請求
	var data_info = "_id=" + $id("_id").value;
	xhr.send(data_info);

}// function

function doFirst() {
	
	$id("man_name").onchange=function(){
	 var man_name=$id("man_name").value;
	 var nameReg = new RegExp("^[(\u4e00-\u9fa5)]{2,5}$");
	
	 if (man_name == "" || man_name.length >6 || man_name.length <2 ) {
		 $id("checkname").innerHTML="中文2~5字";
		 Btn();
	 }else if (!man_name.match(nameReg)){
		 $id("checkname").innerHTML="需為中文字";
		 Btn();
	 }else{
		 $id("checkname").innerHTML="正確";
		 Btn();
	 }

	};
	
	
	$id("man_email").onchange=function(){
		
		var man_email=$id("man_email").value;
		
		
		if (man_email == "" ) {
			$id("checkemail").innerHTML="email請勿空白";
			Btn();
		}else if(man_email.indexOf('@')==-1){
			$id("checkemail").innerHTML="email格式錯誤";
			Btn();
		}else if (man_email.length >30){
			$id("checkemail").innerHTML="email長度太長"
			Btn();
		}else{
			$id("checkemail").innerHTML="正確";
			 Btn();
		}

	};

	$id("_id").onchange=getInfo;

//	成功訊息視窗跳出
	if ($id("Smessage")!=null){
		str=$id("Smessage").innerHTML
		$id("SMsg").innerHTML=str;
		$id("SBtn").click();
	
	}
	
//	錯誤訊息視窗跳出
	if($id("errormessage")!=null){
		str2=$id("errormessage").innerHTML
		$id("errorMsg").innerHTML=str2;
		$id("errorBtn").click();
	}
	
	
	
}
window.addEventListener('load', doFirst, false);
