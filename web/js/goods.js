var goodsId = 0;
var email = "";
$(document).ready(function() {
	email = localStorage.getItem("userEmail");
	if(email == null || email == undefined){
		showToast("登录信息失效，请登录后重试！", 1000);
		return;
	}
	initHead(email);
	//显示商品列表
	showGoodsList();
		
	//点击buy按钮
    $(".row").on('click', '.add-to-cart', function() {
        goodsId = $(this).parents('.product-grid').find('.id').val();
		$('#buyItemTip').modal('show');
    })
	$('.buySure').on('click', function() {
		$('#buyItemTip').modal('hide');
		email = localStorage.getItem("userEmail");
		if(email == null || email == undefined){
			email ="";
		}
		var goodsNum = $("#count").val();
		//清空输入框
		$("#count").val("");
		if(goodsNum == ""){
			showToast("请输入购买件数!", 3000);
			return;
		}
		var paramStr ="email="+email+"&goodsId="+goodsId+"&goodsNum="+goodsNum;
		$.ajax({
			url:"http://localhost:8080/OnlineShop/buy",
			data:paramStr,
			type:"POST",
			success:function(msg){
				var result = JSON.parse(msg);
				if(result.flag== "success"){
					showToast("购买成功", 1000);
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

});


function showGoodsList(){
	email = localStorage.getItem("userEmail");
	if(email == null || email == undefined){
		email ="";
	}
	var paramStr ="email="+email;
	$.ajax({
		url:"http://localhost:8080/OnlineShop/goodsList",
		data:paramStr,
		type:"POST",
		success:function(msg){
			var result = JSON.parse(msg);
			if(result.flag== "success"){
				goodsList = result.data;
				loadGoodsList(goodsList);
			}else{
				showToast(result.data, 3000);
			}
		}
		
	});
}




