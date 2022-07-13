var orderId=0;
var email = "";
$(document).ready(function(){
	email = localStorage.getItem("userEmail");
	if(email == null || email == undefined){
		showToast("登录信息失效，请登录后重试！", 1000);
		return;
	}
	initHead(email);
	
	//显示订单列表
	showOrderList();
	
	//付款按钮
	$(".goods-content").on('click', '.item-pay', function() {
		orderId=$(this).parent('.goods-operate').parent('.panel-body').children(".goods-count").find("span").html();
		$('#payItemTip').modal('show');
	})
	
	
	//确定付款
	$('.paySure').on('click', function() {
		$('#payItemTip').modal('hide');
		email = localStorage.getItem("userEmail");
		if(email == null || email == undefined){
			email ="";
		}
		var paramStr ="email="+email+"&orderId="+orderId;
		$.ajax({
			url:"http://localhost:8080/OnlineShop/pay",
			data:paramStr,
			type:"POST",
			success:function(msg){
				var result = JSON.parse(msg);
				if(result.flag== "success"){
					showToast("付款成功", 1000);
					//延时三秒跳转
					setTimeout(function(){
						//页面跳转
						location.href='./orderList.html';
					},1000);
				}else{
					showToast(result.data, 3000);
				}
			}
		});
        
	})
	
	//点击详情按钮
	$(".goods-content").on('click', '.item-detail', function() {
		//获取订单id
		orderId=$(this).parent('.goods-operate').parent('.panel-body').children(".goods-count").find("span").html();
		email = localStorage.getItem("userEmail");
		if(email == null || email == undefined){
			email ="";
		}
		var paramStr ="email="+email+"&orderId="+orderId;
		$.ajax({
			url:"http://localhost:8080/OnlineShop/orderDetail",
			data:paramStr,
			type:"POST",
			success:function(msg){
				var result = JSON.parse(msg);
				if(result.flag== "success"){
					//弹窗显示详情
			    	showDetail(result.data);	
				}else{
					showToast(result.data, 3000);
				}
			}
		});
	})
	
});


function showOrderList(){
	email = localStorage.getItem("userEmail");
	if(email == null || email == undefined){
		email ="";
	}
	var paramStr ="email="+email;
	$.ajax({
		url:"http://localhost:8080/OnlineShop/orderList",
		data:paramStr,
		type:"POST",
		success:function(msg){
			var result = JSON.parse(msg);
			if(result.flag== "success"){
				loadOrders(result.data);
			}else{
				showToast(result.data, 3000);
			}
		}
		
	});
}







