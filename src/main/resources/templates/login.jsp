<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="../static/">
    
    <title>Login</title>
	<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
 	<link rel="stylesheet" type="text/css" href="./css/login.css">
 	<link rel="stylesheet" type="text/css" href="./css/nav.css">
 	<link rel="stylesheet" type="text/css" href="./css/style2.css">
 	 <!-- Custom Fonts -->
    <link href="./css/font-awesome.min.css" rel="stylesheet" type="text/css">   
  </head>
  <body>
    <div class="rw-wrapper">
				<h2 class="rw-sentence">
					<div class="rw-words rw-words-1">
						<span>爱心捐助平台</span>
						<span>欢迎你,登录</span>
					</div>
				</h2>
    </div>
    <div class="container main">
	    <div class="row sign">
		    <h4 class="title">
			    <a class="active">登录</a>
			    <b>.</b>
			    <a href="../static//login/register.jsp">注册</a>
		    </h4>
	    </div>
        <div class="row">
            <div class="col-xs-12">
                <form action="#">
                    <div class="input-group form-group">
                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                        <input class="form-control" type="text" placeholder="用户名" pattern="^[A-Za-z0-9_\-\u4e00-\u9fa5]{2,16}" title="中文或字母或数字2-16位" required="required">
                    </div>
                    <div class="input-group form-group">
                        <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                        <input class="form-control" type="password" placeholder="密码" pattern="^[A-Za-z0-9]{2,16}" title="字母或数字2-16位" required="required">
                    </div>
					<div class="form-group">
					     <div class="row contentmid text-muted">
						     <div class="col-xs-4">
						          <input type="checkbox">记住我
						      </div>
						     <div class="col-xs-8 text-right">
						        <a>登录遇到问题？</a>
						     </div>
					    </div>
					</div>
                    <div class="form-group">
                        <button class="btn btn-info btn-lg btn-block" type="submit">登录</button>
                    </div>
                </form>
                </div>
                
            </div>

    </div>


  </body>
</html>
