
function $id(id){
  return document.getElementById(id);
}

	function init() {

//		檢舉訊息視窗跳出
		if ($id("FRSmessage")!=null){
			str=$id("FRSmessage").innerHTML;
			$id("FRSMsg").innerHTML=str;
			$id("FRSBtn").click();
		
		}
		
		
		//成功訊息視窗跳出
		if ($id("Smessage")!=null){
			str=$id("Smessage").innerHTML;
			$id("SMsg").innerHTML=str;
			$id("SBtn").click();
		
		}
		
		
		//錯誤訊息跳出
		if ($id("errormessage")!=null){
			str=$id("errormessage").innerHTML;
			$id("errorMsg").innerHTML=str;
			$id("errorBtn").click();
			
		
		}

	}

	window.onload = init;
