/**
 *三级联动  公共JS
 *
 */

$(function(){
	var contextPath = $("#contextPath").val();

	/*根据省：ID查询所有城市*/
	$("#provinces").change(function(){
		
		var action = contextPath+'/account/getCity';
		var provinceId = $("#provinces").val();
		$.ajax({
			url :  action,
			type : "POST",
			data :{"provinceId":provinceId},
			success : function(data) {
				$("#city").html("");
				$("#area").html("<option value='0'>县</option>");
				$("#city").html("<option value='0'>市</option>");
				var arr=eval(data);
				for(var i=0;i<arr.length;i++){
					$("#city").append("<option value='"+arr[i].id+"'>"+arr[i].name+"</option>");
				}
			}
		});
		
	});

	/*根据城市：ID查询所有区、县*/
	$("#city").change(function(){
		var action = contextPath+'/account/getAreas';
		var cityId = $("#city").val();
		 $.ajax({
				url : action,
				type : "POST",
				data :{"cityId":cityId},
				success : function(data) {
					$("#area").html("");
					var arr = eval(data);
					$("#area").html("<option value='0'>区\\县</option>");
					for(var i=0;i<arr.length-2;i++){
						$("#area").append("<option value='"+arr[i].id+"'>"+arr[i].name+"</option>");
					}
					$("#provinces").val(arr[arr.length-1].id);
					
					
				}
		 });
	});



});