function $id(id) {
	return document.getElementById(id);
}


function Btn(){
	
	$id("Btnaddmsg").innerHTML="留言";
	$id("Btnaddmsg").disabled=false;

}


function doFirst() {

	var mem_no=$id("mem_no");
	if (mem_no.value!=""){
		Btn();
	}
	
	
//	成功訊息視窗跳出
	if ($id("Smessage")!=null){
		str=$id("Smessage").innerHTML
		$id("SMsg").innerHTML=str;
		$id("SBtn").click();
	
	}
	
//	錯誤訊息視窗跳出,這邊未使用
//	if($id("errormessage")!=null){
//		str2=$id("errormessage").innerHTML
//		$id("errorMsg").innerHTML=str2;
//		$id("errorBtn").click();
//	}

//檢舉申請成功視窗跳出
	if ($id("FRSmessage")!=null){
		str=$id("FRSmessage").innerHTML
		$id("FRSMsg").innerHTML=str;
		$id("FRSBtn").click();
	
	}
	
}
window.addEventListener('load', doFirst, false);
