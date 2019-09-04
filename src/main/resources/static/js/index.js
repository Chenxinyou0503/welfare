$(function(){
		$w = $('.merits-item').width();
		$h = $('.merits-item').height();
		$w2 = $w + 24;
		$h2 = $h + 30;
		$w3 = $('.merits-icon').width();
		$h3 = $('.merits-icon').height();
		$('.merits-icon,.merits-name,.merits-text').css({'opacity':'1',});
		$('.merits-icon').css({'padding-bottom':'20px'});
		$('.merits-item').hover(function(){
			 $(this).stop().animate({height:$h2,width:$w2,left:"-12px",top:"-15px"},500);
			 $(this).find('.merits-icon,.merits-name,.merits-text').css({'opacity':'1',});
			 $(this).find('.merits-icon').stop().animate({width:$w3+24,height:$h3+30,left:"-12px",top:"-15px"},500);
			 //$(this).find('.merits-icon').css({'width':$w3+50,'height':$h3+50,});
			 $(this).parent().siblings().find('.merits-icon,.merits-name,.merits-text').css({'opacity':'0.4',});
		},function(){
			 $(this).stop().animate({height:$h,width:$w,left:"0px",top:"0px"},500);
			 $(this).find('.merits-icon,.merits-name,.merits-text').css({'opacity':'0.4',});
			 //$(this).find('.merits-icon').css({'width':$w3,'height':$h3});
			 $(this).find('.merits-icon').stop().animate({width:$w3,height:$h3,left:"-12px",top:"-15px"},500);
			 $('.merits-item').find('.merits-icon,.merits-name,.merits-text').css({'opacity':'1',});
		});	
});
