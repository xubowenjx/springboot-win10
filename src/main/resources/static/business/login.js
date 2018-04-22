//登录事件
$('#btn-login').on('click', function() {
		var data = {
			userName : $('#name').val(),
			userPassword : $('#password').val()
		};
		var lid = layer.load(1);
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "./auth",
			data : JSON.stringify(data),
			dataType : 'json',
			timeout : 600000,
			success : function(data) {
				 var code = data.code;
				 layer.msg(data.msg);
				 layer.close(lid);
				 if(code==='1'){
					location.href='./redirectUrl';
				 }else{
					 layer.msg(data.msg);
				 }
			
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function() {
				console.log("DONE");
				
			}
		})
	});
//切换语言
	$('#changLange').on('click',function(){
		var lang =  $('#changLange').attr('targetLang');
		$.get('/changlang',{lang:lang},function(res){
			console.log(res);
			location.reload(true);
		})
	});