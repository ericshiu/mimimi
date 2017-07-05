
function $id(id){
  return document.getElementById(id);
}

	function init() {

		//成功成功訊息視窗跳出
		if ($id("Login_Smessage")!=null){
			str=$id("Login_Smessage").innerHTML;
			$id("Login_SMsg").innerHTML=str;
			$id("Login_SBtn").click();
		
		}

		//錯誤訊息跳出
		if ($id("Login_Emessage")!=null){
			str=$id("Login_Emessage").innerHTML;
			$id("Login_EMsg").innerHTML=str;
			$id("Login_EBtn").click();
			
		}
		
		
		//錯誤訊息跳出
		if ($id("Logout_message")!=null){
			str=$id("Logout_message").innerHTML;
			$id("Logout_Msg").innerHTML=str;
			$id("Logout_Btn").click();
			
		}
		
	}

	window.onload = init;