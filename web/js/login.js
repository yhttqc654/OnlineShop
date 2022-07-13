
function signUp(){
	var email=$("#email").val();
	var name=$("#user").val();
	var password=$("#password").val();
	var confirmPassword=$("#confirmPassword").val();
	var paramStr="name="+name+"&email="+email+"&password="+password+"&confirmPassword="+confirmPassword;
	
	if(email == "" || name == "" || password == "" || confirmPassword == ""){
		showToast("信息填写不完整", 3000);
		return;
	}
	
	if(confirmPassword != password){
		showToast("密码与确认密码不一致！", 3000);
		return;
	}
	
	$.ajax({
		url:"http://localhost:8080/OnlineShop/signUp",
		data:paramStr,
		type:"POST",
		success:function(msg){
			var result = JSON.parse(msg);
			if(result.flag== "success"){
				showToast("注册成功", 3000);
				//注册成功会将邮箱存入缓存
				localStorage.setItem("userEmail", email);
				//延时三秒跳转
				setTimeout(function(){
					//页面跳转
					location.href='./goodsList.html';
				},1000);
			}else{
				showToast(result.data, 3000);
			}
		}		
	});
	
}
function login(){
	var email=$("#loginEmail").val();
	var password=$("#loginPassword").val();
	var paramStr="email="+email+"&password="+password;
	
	if(email == "" || password == "" ){
		showToast("信息填写不完整", 3000);
		return;
	}
	
	$.ajax({
		url:"http://localhost:8080/OnlineShop/login",
		data:paramStr,
		type:"POST",
		success:function(msg){
			var result = JSON.parse(msg);
			if(result.flag== "success"){
				showToast("登录成功", 3000);
				//页面跳转
				location.href='./goodsList.html';
				//注册成功会将邮箱存入缓存
				localStorage.setItem("userEmail", email);
			}else{
				showToast(result.data, 3000);
			}
		}		
	});
			

}

	