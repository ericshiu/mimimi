//member/personalmem 使用之
function  Clickname(){

		var Fathername=document.getElementById('Fathername');
		var Username=document.getElementById('Username');
		var name=Username.innerHTML;

		var THname=document.createElement("th");
		var Brothername1=document.getElementById('Brothername');
		Inputname = document.createElement("input");
		THname.style="text-align:center;";
		Inputname.id="mem_name";
		Inputname.type = 'text';
		Inputname.style="width:200px;text-align:center;";
		Inputname.value= name;
		
		Inputname.onblur=checkMobile;
		THname.appendChild(Inputname);
		
		Fathername.replaceChild(THname, Username);

}

function  Clickpassword(){

	var Fatherpassword=document.getElementById('Fatherpassword');
	var Userpassword=document.getElementById('Userpassword');
	var password=Userpassword.innerHTML;

	var THpassword=document.createElement("th");
	var Brotherpassword1=document.getElementById('Brotherpassword');
	Inputpassword = document.createElement("input");
	THpassword.style="text-align:center;";
	Inputpassword.id="mem_password";
	Inputpassword.type = 'text';
	Inputpassword.style="width:200px;text-align:center;";
	Inputpassword.value= password;
	Inputpassword.onblur=checkMobile;
	THpassword.appendChild(Inputpassword);

	Fatherpassword.replaceChild(THpassword, Userpassword);
	
	var show=document.getElementById('Fatherpasswordcheck');
	show.style="display:";
}

function  Clickpasswordcheck(){

	var Fatherpasswordcheck=document.getElementById('Fatherpasswordcheck');
	var Userpasswordcheck=document.getElementById('Userpasswordcheck');
	var passwordcheck=Userpasswordcheck.innerHTML;

	var THpasswordcheck=document.createElement("th");
	var Brotherpasswordcheck1=document.getElementById('Brotherpasswordcheck');
	Inputpasswordcheck = document.createElement("input");
	THpasswordcheck.style="text-align:center;";
	Inputpasswordcheck.id="mem_passwordcheck";
	Inputpasswordcheck.type = 'text';
	Inputpasswordcheck.style="width:200px;text-align:center;";
	Inputpasswordcheck.value= passwordcheck;
	Inputpasswordcheck.onblur=checkMobile;
	THpasswordcheck.appendChild(Inputpasswordcheck);

	Fatherpasswordcheck.replaceChild(THpasswordcheck, Userpasswordcheck);
}



function  Clicknike(){

	var Fathernike=document.getElementById('Fathernike');
	var Usernike=document.getElementById('Usernike');
	var nike=Usernike.innerHTML;

	var THnike=document.createElement("th");
	var Brothernike1=document.getElementById('Brothernike');
	Inputnike = document.createElement("input");
	THnike.style="text-align:center;";
	Inputnike.id="mem_nike";
	Inputnike.type = 'text';
	Inputnike.style="width:200px;text-align:center;";
	Inputnike.value= nike;
	Inputnike.onblur=checkMobile;
	THnike.appendChild(Inputnike);
	Fathernike.replaceChild(THnike, Usernike);

}

function  Clickphone(){

	var Fatherphone=document.getElementById('Fatherphone');
	var Userphone=document.getElementById('Userphone');
	var phone=Userphone.innerHTML;

	var THphone=document.createElement("th");
	Inputphone = document.createElement("input");
	THphone.style="text-align:center;";
	Inputphone.id="mem_phone";
	Inputphone.type = 'text';
	Inputphone.style="width:200px;text-align:center;";
	Inputphone.value= phone;
	Inputphone.onblur=checkMobile;
	THphone.appendChild(Inputphone);

	Fatherphone.replaceChild(THphone, Userphone);
}

function  Clickaddress(){

	var Fatheraddress=document.getElementById('Fatheraddress');
	var Useraddress=document.getElementById('Useraddress');
	var address=Useraddress.innerHTML;

	var THaddress=document.createElement("th");
	var Brotheraddress1=document.getElementById('Brotheraddress');
	Inputaddress = document.createElement("input");
	THaddress.style="text-align:center;";
	Inputaddress.id="mem_address";
	Inputaddress.type = 'textarea';
	Inputaddress.rows="100";
	Inputaddress.cols="100";
	Inputaddress.style="text-align:center;";
	Inputaddress.onblur=checkMobile;
	Inputaddress.value= address;
	THaddress.appendChild(Inputaddress);

	Fatheraddress.replaceChild(THaddress, Useraddress);
}

function  Clickemail(){

	var Fatheremail=document.getElementById('Fatheremail');
	var Useremail=document.getElementById('Useremail');
	var email=Useremail.innerHTML;

	var THemail=document.createElement("th");
	var Brotheremail1=document.getElementById('Brotheremail');
	Inputemail = document.createElement("input");
	THemail.style="text-align:center;";
	Inputemail.id="mem_email";
	Inputemail.type = 'mail';
	Inputemail.style="width:200px;text-align:center;";
	Inputemail.value= email;
	Inputemail.onblur=checkMobile;
	THemail.appendChild(Inputemail);

	Fatheremail.replaceChild(THemail, Useremail);
}

function  Clickfacebook(){

	var Fatherfacebook=document.getElementById('Fatherfacebook');
	var Userfacebook=document.getElementById('Userfacebook');
	var facebook=Userfacebook.innerHTML;

	var THfacebook=document.createElement("th");
	var Brotherfacebook1=document.getElementById('Brotherfacebook');
	Inputfacebook = document.createElement("input");
	THfacebook.style="text-align:center;";
	Inputfacebook.id="mem_facebook";
	Inputfacebook.type = 'mail';
	Inputfacebook.style="width:200px;text-align:center;";
	Inputfacebook.value= facebook;
	Inputfacebook.onblur=checkMobile;
	THfacebook.appendChild(Inputfacebook);

	Fatherfacebook.replaceChild(THfacebook, Userfacebook);
}

function  Clickgoogle(){

	var Fathergoogle=document.getElementById('Fathergoogle');
	var Usergoogle=document.getElementById('Usergoogle');
	var google=Usergoogle.innerHTML;

	var THgoogle=document.createElement("th");
	var Brothergoogle1=document.getElementById('Brothergoogle');
	Inputgoogle = document.createElement("input");
	THgoogle.style="text-align:center;";
	Inputgoogle.id="mem_google";
	Inputgoogle.type = 'mail';
	Inputgoogle.style="width:200px;text-align:center;";
	Inputgoogle.value= google;
	Inputgoogle.onblur=checkMobile;
	THgoogle.appendChild(Inputgoogle);

	Fathergoogle.replaceChild(THgoogle, Usergoogle);
}



function checkMobile(){

	var inputname=event.currentTarget.id;
	var modifyValue=document.getElementsByName(inputname)[0];
	modifyValue.value=this.value;
}



function $id(id){
  return document.getElementById(id);
}

	function init() {

		
//		檢舉訊息視窗跳出
//		if ($id("FRSmessage")!=null){
//			str=$id("FRSmessage").innerHTML;
//			$id("FRSMsg").innerHTML=str;
//			$id("FRSBtn").click();
//		
//		}
		
		
		//成功訊息視窗跳出
//		if ($id("Smessage")!=null){
//			str=$id("Smessage").innerHTML;
//			$id("SMsg").innerHTML=str;
//			$id("SBtn").click();
//		
//		}
		
		
		//錯誤訊息跳出
//		if ($id("errormessage")!=null){
//			str=$id("errormessage").innerHTML;
//			$id("errorMsg").innerHTML=str;
//			$id("errorBtn").click();
//			
//		
//		}

	}

	window.onload = init;

