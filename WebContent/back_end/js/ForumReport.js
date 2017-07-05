
function $id(id){
  return document.getElementById(id);
}

	function init() {

//		通過訊息視窗跳出
		if ($id("Smessage")!=null){
			str=$id("Smessage").innerHTML
			$id("SMsg").innerHTML=str;
			$id("SBtn").click();
		
		}
		
		
//		不通過訊息
		if ($id("Emessage")!=null){
			str=$id("Emessage").innerHTML
			$id("EMsg").innerHTML=str;
			$id("EBtn").click();
		
		}

	}

	window.onload = init;