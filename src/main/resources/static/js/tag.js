$(document).ready(function() {
	jQuery.jqtab = function(tabtit,tab_conbox,shijian) {
		$(tab_conbox).find("li").hide();
		$(tabtit).find("li:first").addClass("z_cur").show(); 
		$(tab_conbox).find("li:first").show();
	
		$(tabtit).find("li").bind(shijian,function(){
		  $(this).addClass("z_cur").siblings("li").removeClass("z_cur"); 
			var activeindex = $(tabtit).find("li").index(this);
			$(tab_conbox).children().eq(activeindex).show().siblings().hide();
			return false;
		});
	
	};
	/*调用方法如下：*/
	$.jqtab("#tags","#tag_conbox","click");	
	$.jqtab("#dags","#dag_conbox","click");
	$.jqtab("#planTags","#planTag_conbox","click");
});

/*首页视频*/
/*$(function() {		
        $('#hengxin_video').click(function(){  
			changVideo(this);		
			var videoSrc = $("#companyIntro").val();
			var videoNum = '<embed allowFullScreen="true" id="embedid"  quality="high" width="640" height="360" allowScriptAccess="always" type="application/x-shockwave-flash" src="'+videoSrc+'"></embed>';
			$('#player').html(videoNum);			
        });		
		$('#house_video').click(function(){
			changVideo(this);
			var videoSrc = $("#fangIntro").val();
			var videoNum = '<embed allowFullScreen="true" id="embedid"  quality="high" width="640" height="360" allowScriptAccess="always" type="application/x-shockwave-flash" src="'+videoSrc+'"></embed>';
			$('#player').html(videoNum);
			
        });
		$('#car_video').click(function(){
			changVideo(this);
			var videoSrc = $("#cheIntro").val();
			var videoNum = '<embed allowFullScreen="true" id="embedid"  quality="high" width="640" height="360" allowScriptAccess="always" type="application/x-shockwave-flash" src="'+videoSrc+'"></embed>';
			$('#player').html(videoNum);
			
        });		 
		$('.xf_wyjkfb_4_windowClose').click(function(){
			changClose(this);
		})
    });
	function changVideo(){
		$('#popDiv').removeClass().addClass('mydiv ckhtbankdiv show_block');
		$('#bg').removeClass().addClass('bg show_block');
		$('#popIframe').removeClass().addClass('popIframe show_block');
	};
	function changClose(obj){
		$('#popDiv').removeClass().addClass('mydiv ckhtbankdiv show_hidd');
		$('#bg').removeClass().addClass('bg show_hidd');
		$('#popIframe').removeClass().addClass('popIframe show_hidd');
		};*/