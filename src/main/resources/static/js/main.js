jQuery(document).ready(function($){
    // jQuery twittie
	$('#tweet').twittie({
        count: 2,
        template: '<i class="fa fa-twitter"></i> {{tweet}}'
    });
    
});

