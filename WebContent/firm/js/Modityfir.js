//member/personalmem 使用之
function  Clickname(){

		var Fathername=document.getElementById('Fathername');
		var Username=document.getElementById('Username');
		var name=Username.innerHTML;

		var THname=document.createElement("th");
		var Brothername1=document.getElementById('Brothername');
		Inputname = document.createElement("input");
		THname.style="text-align:center;";
		Inputname.id="fir_name";
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
	Inputpassword.id="fir_password";
	Inputpassword.type = 'text';
	Inputpassword.style="width:200px;text-align:center;";
	Inputpassword.value= "";
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
	Inputpasswordcheck.id="fir_passwordcheck";
	Inputpasswordcheck.type = 'text';
	Inputpasswordcheck.style="width:200px;text-align:center;";
	Inputpasswordcheck.value= passwordcheck;
	Inputpasswordcheck.onblur=checkMobile;
	THpasswordcheck.appendChild(Inputpasswordcheck);

	Fatherpasswordcheck.replaceChild(THpasswordcheck, Userpasswordcheck);
}





function  Clickphone(){

	var Fatherphone=document.getElementById('Fatherphone');
	var Userphone=document.getElementById('Userphone');
	var phone=Userphone.innerHTML;

	var THphone=document.createElement("th");
	Inputphone = document.createElement("input");
	THphone.style="text-align:center;";
	Inputphone.id="fir_phone";
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
	Inputaddress.id="fir_address";
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
	Inputemail.id="fir_email";
	Inputemail.type = 'mail';
	Inputemail.style="width:200px;text-align:center;";
	Inputemail.value= email;
	Inputemail.onblur=checkMobile;
	THemail.appendChild(Inputemail);

	Fatheremail.replaceChild(THemail, Useremail);
}

function  Clickfir_account(){

	var Fatherfir_account=document.getElementById('Fatherfir_account');
	var Userfir_account=document.getElementById('Userfir_account');
	var fir_account=Userfir_account.innerHTML;

	var THfir_account=document.createElement("th");
	var Brotherfir_account1=document.getElementById('Brotherfir_account');
	Inputfir_account = document.createElement("input");
	THfir_account.style="text-align:center;";
	Inputfir_account.id="fir_account";
	Inputfir_account.type = 'account';
	Inputfir_account.style="width:200px;text-align:center;";
	Inputfir_account.value= fir_account;
	Inputfir_account.onblur=checkMobile;
	THfir_account.appendChild(Inputfir_account);

	Fatherfir_account.replaceChild(THfir_account, Userfir_account);
}




function checkMobile(){

	var inputname=event.currentTarget.id;
	var modifyValue=document.getElementsByName(inputname)[0];
	modifyValue.value=this.value;
}
