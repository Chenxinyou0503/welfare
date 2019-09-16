var http_path = '';
/**
 * 是否相等
 */
function equals(obj1, obj2) {
	if (obj1 == obj2)
		return true;

	if (typeof (obj1) == "undefined" || obj1 == null
			|| typeof (obj1) != "object")
		return false;

	if (typeof (obj2) == "undefined" || obj2 == null
			|| typeof (obj2) != "object")
		return false;

	var length1 = 0;
	var length2 = 0;

	for ( var ele in obj1) {
		length1++;
	}

	for ( var ele in obj2) {
		length2++;
	}

	if (length1 != length2)
		return false;

	if (obj1.constructor == obj2.constructor) {
		for ( var ele in obj1) {
			if (typeof (obj1[ele]) == "object") {
				if (!equals(obj1[ele], obj2[ele]))
					return false;
			} else if (typeof (obj1[ele]) == "function") {
				if (!obj1[ele].toString().equals(obj2[ele].toString()))
					return false;
			} else if (obj1[ele] != obj2[ele])
				return false;
		}

		return true;
	}

	return false;
}

/**
 * 是否包含对象
 * 
 * @param obj
 * @returns {Boolean}
 */
Array.prototype.inArray = function(obj) {
	for (var i = 0; i < this.length; i++) {
		if (equals(this[i], obj))
			return true;
	}

	return false;
};

/**
 * 查找对象在数组中的位置
 * @param obj
 * @returns
 */
Array.prototype.indexOf = function(obj) {
	for ( var i = 0; i < this.length; i++) {
		if (equals(this[i], obj))
			return i;
	}

	return -1;
};

/**
 * 方法:removeObject(obj)
 * 功能:根据元素值删除数组元素. 
 * 参数:obj 
 * 返回:在原数组上修改数组
 */
Array.prototype.removeObject = function(obj) {
	var index = this.indexOf(obj);
	if (index > -1) {
		this.splice(index, 1);
	}

	return this;
};

/**
 * 方法:removeObjectById(id) 
 * 功能:根据元素值删除数组元素. 
 * 参数:id 
 * 返回:在原数组上修改数组
 */
Array.prototype.removeObjectById = function(id) {
	for ( var i = 0; i < this.length; i++) {
		if (this[i].id == id){
			return this.splice(i, 1);
		}
	}

	return this;
};

/**
 * 方法:removeObjectAtIndex(dx) 
 * 功能:根据元素位置值删除数组元素. 
 * 参数:dx 
 * 返回:在原数组上修改数组
 */
Array.prototype.removeObjectAtIndex = function(dx) {
	if (isNaN(dx) || dx < this.length || dx > this.length) {
		return this;
	}

	return this.splice(dx, 1);
};

/**
 * 方法:findObjectById(id) 
 * 功能:根据id查找数组元素. 
 * 参数:id 
 * 返回:数组元素
 */
Array.prototype.findObjectById = function(id) {
	for ( var i = 0; i < this.length; i++) {
		if (this[i].id == id){
			return this[i];
		}
	}

	return null;
};

/**
 * 删除所有空白
 * @returns
 */
String.prototype.trim = function() {
	return this.replace(/(^\s+)|(\s+$)/g,"");
}

/**
 * 是否为空白
 * @returns {Boolean}
 */
String.prototype.isBlank = function() {
    return this == null || this.trim() == "";
}

/**
 * 删除左边的空白
 * 
 * @returns
 */
String.prototype.lTrim = function() {
	return this.replace(/(^\s+)/g, "");
}

/**
 * 删除右边的空白
 * 
 * @returns
 */
String.prototype.rTrim = function() {
	return this.replace(/(\s+$)/g, "");
}

/**
 * 是否有效的手机号码
 * 
 * @returns
 */
String.prototype.test = function() {
	return (new RegExp(/^([\S^'^‘^’]{6,20})$/)
			.test(this));
}

/**
 * 是否有效的手机号码
 * 
 * @returns
 */
String.prototype.isMobileNum = function() {
	return (new RegExp(/^((13[0-9])|(14[4,7])|(15[^4,\D])|(17[0-9])|(18[0-9]))(\d{8})$/)
			.test(this));
}

/**
 * 是否有效的邮箱
 * 
 * @returns
 */
String.prototype.isEmail = function() {
	return (
			new RegExp(/^([a-zA-Z0-9])+([a-zA-Z0-9_.-])+@([a-zA-Z0-9_-])+((\.([a-zA-Z0-9_-]){2,3}){1,2})$/).test(this)
		   );
}

/**
 * 是否是QQ邮箱
 */
String.prototype.isQQEmail = function(){
	return new RegExp(/^([\s\S]*@qq.com)$/).test(this);
}


/**
 * 是否日期
 * 
 * @returns
 */
String.prototype.isDate = function() {
	return (new RegExp(
			/^([1-2]\d{3})[\/|\-](0?[1-9]|10|11|12)[\/|\-]([1-2]?[0-9]|0[1-9]|30|31)$/ig)
			.test(this));
}

/**
 * 替换全部
 */
String.prototype.replaceAll  = function(s1,s2){     
    return this.replace(new RegExp(s1,"gm"),s2);     
} 

/**
 * 克隆(深拷贝)
 * 
 * @param obj
 * @returns
 */
function clone(obj) {
	if (typeof (obj) != 'object') {
		return obj;
	}

	var re = {};

	if (obj.constructor == Array) {
		re = [];
	}

	for ( var i in obj) {
		re[i] = clone(obj[i]);
	}

	return re;
}

/**
 * 居中显示div
 * 
 * @param obj
 */
function showDiv(obj) {
	$(obj).show();

	center(obj);

	$(window).scroll(function() {
		center(obj);
	});

	$(window).resize(function() {
		center(obj);
	});
}

function center(obj) {
	var windowWidth = document.documentElement.clientWidth;
	var windowHeight = document.documentElement.clientHeight;
	var popupHeight = $(obj).height();
	var popupWidth = $(obj).width();
	$(obj).css({
		"position" : "absolute",
		"top" : (windowHeight - popupHeight) / 2 + $(document).scrollTop(),
		"left" : (windowWidth - popupWidth) / 2
	});
}

/**
 * 上传图片
 * @param fileElementId
 * @param imageElementId
 */
function uploadImage(fileElementId, imageElementId) {
	var path = $("#" + fileElementId).val();
	
	if(path == "") {
		alert("请选择上传的图片");
		return;
	}
	
	$.ajaxFileUpload({
		url : http_path + "/FileUpload/uploadImage",
		secureuri : false,
		fileElementId : fileElementId,
		dataType : 'text',
		success : function(data) {
			data = $.evalJSON(data);
			if (data.code < 0) {
				alert(data.msg);

				return;
			}
			$("#" + imageElementId).attr("src", data.filename);
		},
		error : function(data, status, e) {
			alert("上传图片失败");
		}
	})
}

/**
 * 上传图片至服务器
 * @param fileElementId
 * @param imageElementId
 */
function uploadImageOs(fileElementId, imageElementId) {
	var path = $("#" + fileElementId).val();
	
	if(path == "") {
		alert("请选择上传的图片");
		return;
	}
	
	$.ajaxFileUpload({
		url : http_path + "/FileUpload/uploadImageOs",
		secureuri : false,
		fileElementId : fileElementId,
		dataType : 'text',
		success : function(data) {
		data = $.evalJSON(data);
		if (data.code < 0) {
			alert(data.msg);
			
			return;
		}
		$("#" + imageElementId).attr("src", data.fileName);
	},
	error : function(data, status, e) {
		alert("上传图片失败");
	}
	})
}

/**
 * 上传图片
 * @param fileElementId
 * @param imageElementId
 */
function uploadImageType(fileElementId, imageElementId) {
	var path = $("#" + fileElementId).val();
	
	if(path == "") {
		alert("请选择上传的图片");
		return;
	}
	
	$.ajaxFileUpload({
		url : http_path + "/FileUpload/uploadImageReturnType",
		secureuri : false,
		fileElementId : fileElementId,
		dataType : 'text',
		success : function(data) {
			data = $.evalJSON(data);
			if (data.code < 0) {
				alert(data.msg);

				return;
			}

			$("#" + imageElementId).attr("src", data[0].filePath);
			$("#imageSize").val(data[0].size);
			$("#imageType").val(data[0].fileType);
			$("#imageResolution").val(data[0].resolution);
			$("#imageType2").html(data[0].fileType);
			$("#imageResolution2").html(data[0].resolution);
		},
		error : function(data, status, e) {
			alert("上传图片失败");
		}
	})
}

/**       
 * 对Date的扩展，将 Date 转化为指定格式的String       
 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符       
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)       
 * eg:       
 * (new Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423       
 * (new Date()).format("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04       
 * (new Date()).format("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04       
 * (new Date()).format("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04       
 * (new Date()).format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18       
 */ 
Date.prototype.format = function(fmt) {
	var o = {
		"M+": this.getMonth() + 1, //月份           
		"d+": this.getDate(), //日           
		"h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时           
		"H+": this.getHours(), //小时           
		"m+": this.getMinutes(), //分           
		"s+": this.getSeconds(), //秒           
		"q+": Math.floor((this.getMonth() + 3) / 3), //季度           
		"S": this.getMilliseconds() //毫秒           
	};
	
	var week = {
		"0": "日",
		"1": "一",
		"2": "二",
		"3": "三",
		"4": "四",
		"5": "五",
		"6": "六"
	};
	
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	
	if (/(E+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "星期" : "周") : "") + week[this.getDay() + ""]);
	}
	
	for (var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	
	return fmt;
}

function inputNum() {
	if(!((event.keyCode>=48&&event.keyCode<=57)||event.keyCode == 8 || event.keyCode == 0))  {
		event.returnValue=false;
	}
}

var time = 120;//设置手机验证码时间 
var time2 = 120;
var blug;
var blug2;

//手机验证码调用时间定时器(当点击按钮是input标签时使用)
function teleCodeTimer(id){
	blug = id;
	timer();
}

//手机验证码重新获取时间间隔
function timer(){
	$("#"+blug).attr("style","background-color: rgba(137, 140, 141, 0.8);");
    $("#"+blug).attr("disabled", true);
    time = time - 1;
    $("#"+blug).val(time+"秒后获取");
    if(time == 0){
       $("#"+blug).val("获取验证码");
       time = 120;
       $("#"+blug).attr("disabled", false);
       $("#"+blug).attr("style","background: #2EAAD6 none repeat scroll 0% 0%;color: #FFF;");
    }else{
       setTimeout('timer()',1000);
    }
}

//手机验证码调用时间定时器(当点击按钮是a标签时使用)
function teleCodeTimer2(id){
	blug2 = id;
	timer2();
}

//手机验证码重新获取时间间隔
function timer2(){
	$("#"+blug2).attr("style","background-color: rgba(137, 140, 141, 0.8);");
    $("#"+blug2).attr("disabled", true);
    time2 = time2 - 1;
    $("#"+blug2).val(time2+"秒后获取");
    
    if(time2 == 0){
       $("#"+blug2).val("获取验证码");
       time2 = 120;
       $("#"+blug2).attr("disabled", false);
       $("#"+blug2).attr("style","background: #2EAAD6 none repeat scroll 0% 0%;color: #FFF;");
    }else{
       setTimeout('timer2()',1000);
    }
}

//手机验证码调用时间定时器(当点击按钮是a标签时使用)
function teleCodeTimer3(id){
	blug = id;
	timer3();
}

//手机验证码重新获取时间间隔
function timer3(){
	$("#"+blug).attr("style","background-color: rgba(137, 140, 141, 0.8);");
    $("#"+blug).attr("disabled", true);
    time = time - 1;
    $("#"+blug).val(time+"秒后获取");
    
    if(time == 0){
       $("#"+blug).val("获取验证码");
       time = 120;
       $("#"+blug).attr("disabled", false);
       $("#"+blug).attr("style","background: #2EAAD6 none repeat scroll 0% 0%;color: #FFF;");
    }else{
       setTimeout('timer3()',1000);
    }
}


//手机验证码调用时间定时器(当点击按钮是a标签时使用)
function teleCodeTimer4(id){
	$('#'+id).removeAttr('onclick');
	blug = id;
	timer4();
}

//a标签不支持disabled，此方法第二行实际上没有起到取消事件的作用，a标签需要用removeAttr('onclick')
//另外a标签不支持value，要用html改变倒计时的内容
function timer4(){
	$("#"+blug).attr("style","background-color: rgba(137, 140, 141, 0.8);");
    $("#"+blug).attr("disabled", true);
    
    time = time - 1;
    $("#"+blug).html(time+"秒后获取");
    
    if(time == 0){
       $("#"+blug).html("获取验证码");
       time = 120;
       $("#"+blug).attr("disabled", false);
       //颜色还原
       $("#"+blug).attr("style","background-color:  #ff7f00 ");
       //onclick事件还原
       $("#"+blug).click(getMobile);
    }else{
       setTimeout('timer4()',1000);
    }
}

/**
 * 后台高亮显示
 * @param lab 大项+ - 符号
 * @param hi 隐藏项
 * @param mg 子项
 * @return
 */
function showHighLight(lab, hi, mg){
	$("#lab_" + lab).addClass('xn_c_li_bg_jian');
	$("#hi_" + hi).show();
	$("#mg_" + mg).attr('class','xn_c_li_head_ishow');
	var nav_mh = $(".xn_c_content_leftul").height(),
    on_mh = $(".xn_c_con_leftbutton").height(),
    xc_ht = (nav_mh-on_mh)/2;
	$(".xn_c_con_leftbutton").css({"margin-top":xc_ht});
}

function highLight(index){
	$("#lab_" + index).addClass('xn_c_li_head_oneishow');;
}

/* 前台选中样式 */
function showHighLightFront(index){
	$("#child" + index).css({'background-color':'#fafafa','color':'#2eaad6'});
}

/* 前台选项卡样式 */
function showHighLightFront2(maxIndex, nowIndex){
	var li = $("#li_" + nowIndex)
	//var lab = $("#lab_" + nowIndex);
	var more = $("#more_" + nowIndex);
	var tg = $("#tg_" + nowIndex);
	
	li.attr('class','xf_con_wyjk_leftliisshow'); 
	more.attr('class', 'xf_mem_r_more xf_mem_r_jian');
	
	if(null != tg){
		if(tg.is(":visible")){
			tg.hide();
		}else{
			tg.show();
		}
	}
}

/* 替换被拦截的html代码,请遵循JAVA定义规则 */
function replaceAllHTML(content){
	if(content == null || content == '')
		return '';
		
	content = content.replaceAll("#", "html_j");
	content = content.replaceAll("<img", "html_i");
	content = content.replaceAll("<a", "html_a");
	content = content.replaceAll("<frame", "html_f");
	
	return content;
}

function checkAll(){
	var allCheckBoxs=document.getElementsByName("check_box");  
	var flag;
	
	for (var i=0;i<allCheckBoxs.length ;i++){       
		if(allCheckBoxs[i].type=="checkbox"){  
		    flag = allCheckBoxs[i].checked;
			allCheckBoxs[i].checked= !flag;       
		}   
	}  
}

//设置 cookie
function setCookie(name, value, day, path) {
	var exp = new Date();
	exp.setTime(exp.getTime() + day*24*60*60*1000);
	document.cookie = name + "=" + value + ";expires=" + exp.toGMTString() + (path == "" ? "" : ";path="+path);
}

// 删除 cookie
function delCookie(name, path) {
	var value = getCookie(name);
	if(value != null) {
		setCookie(name, value, -1, path);
	}
}
//金额千分位
function fmoney(s, n) {  
    n = n > 0 && n <= 20 ? n : 2;  
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";  
    var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];  
    t = "";  
    for (i = 0; i < l.length; i++) {  
        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");  
    }  
    return t.split("").reverse().join("") + "." + r;  
}  
// 获取 cookie
function getCookie(name){
 	var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
 	if(arr != null) {
		return unescape(arr[2]);
	} 
	return null;
}


var discriCard = function(){
	//获取输入身份证号码
	var UUserCard = $("#card").val();
	//获取出生日期
	UUserCard.substring(6, 10) + "-" + UUserCard.substring(10, 12) + "-" + UUserCard.substring(12, 14);
	//获取性别
	if (parseInt(UUserCard.substr(16, 1)) % 2 == 1) {
		$("#sex").val("男");
	//是男则执行代码 ...
	} else {
		$("#sex").val("女");
	//是女则执行代码 ...
	}
	//获取年龄
	var myDate = new Date();
	var month = myDate.getMonth() + 1;
	var day = myDate.getDate();
	var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1;
	if (UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day) {
	age++;
	}
	 $("#age").val(age);
	//年龄 age
}

var Psatchca = new Object(); 
Psatchca.change = function(){
	var contextPath = $("#contextPath").val();
	$.post(contextPath+"/front/bidaction/setCode",function(data){
		var arr=eval(data);
		var img = arr.img;
		$("#randomID").val(img);
		$("#img").attr("src",contextPath+"/front/getImg?id="+img)	
	});
}

/**
 * JavaScript构建一个form
 * @param url
 * @param obj
 */
function makeSubmitForm(url, obj, target) {
    // 创建一个 form
    var form = document.createElement("form");
    // 创建输入
    for (var key in obj) {
        var input = document.createElement("input");
        // 设置相应参数
        input.type = "hidden";
        input.name = key;
        input.value = obj[key];
        // 将该输入框插入到 form 中
        form.appendChild(input);
    }
    // form 的提交方式
    form.method = "POST";
    if(target){
        form.target = target;
    }
    // form 提交路径
    form.action = url;
    // 添加该 form
    document.body.appendChild(form);
    // 对该 form 执行提交
    form.submit();
    // 删除该 form
    document.body.removeChild(form);
}

/**
 * 延时点击
 * @param url
 */
function timeoutProxy(url) {
    setTimeout(function () {window.location.href=url;}, 200);
}

/***
 * 防止F5持续刷新
 * @type {number}
 */
var initDateFlag=0;
setTimeout(function () {
    initDateFlag+=1;
},1000);
document.onkeydown = function(e){
    e = window.event || e;
    var keycode = e.keyCode || e.which;
    if( keycode = 116){//F5刷新
        if(initDateFlag==0){
            if(window.event){//ie
                try{
                    e.keyCode = 0;
                }catch(e){}
                e.returnValue = false;
            }else{// ff
                e.preventDefault();
            }
        }
    }
}

//是否有登录cookie
function getLoginUserCookie() {
    var userName=getCookie("_un");
    if(userName==null||userName==""||userName==undefined){
    	return false;
	}
    if (!(/^(1[3-9])\d{9}$/.test(userName))) {
    	userName=userName.substring(0,3)+"****"+userName.substring(8);
    }
    var login=document.getElementById("login_li_l");
    if(login!=null){
        login.innerHTML='<a href="/account/home" title="">'+userName+'</a>';
	}
    var register=document.getElementById("login_li_r");
    if(register!=null){
        register.innerHTML='<a href="/loginAndRegister/logout" title="">退出</a>';
    }
};
window.onload = function(){
    getLoginUserCookie();
};

//重写Ajax
/*(function ($) {
    //首先备份下jquery的ajax方法
    var _ajax = $.ajax;

    //重写jquery的ajax方法
    $.ajax = function (opt) {
        //备份opt中error和success方法
        var fn = {
            error: function (XMLHttpRequest, textStatus, errorThrown) {
            },
            success: function (data, textStatus) {
            }
        };
        if (opt.error) {
            fn.error = opt.error;
        }
        if (opt.success) {
            fn.success = opt.success;
        }

        //扩展增强处理
        var _opt = $.extend(opt, {
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                //错误方法增强处理
                fn.error(XMLHttpRequest, textStatus, errorThrown);
            },
            success: function (data, textStatus) {
                //成功回调方法增强处理
                fn.success(data, textStatus);
            },
            beforeSend: function (XHR) {
                //提交前回调方法
                //$('body').append("<div id='ajaxInfo' style=''>正在加载,请稍后...</div>");
            },
            complete: function (XHR, TS) {
                //请求完成后回调函数 (请求成功或失败之后均调用)。
                //$("#ajaxInfo").remove();;
            }
        });
        return _ajax(_opt);
    };
})(jQuery);*/