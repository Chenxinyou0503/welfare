<%@ page language="java" import="java.util.*" pageEncoding="UTf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html>
  <head>
    <base href="../static/">
    <title>主页</title>
    <link href="../static/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="../static/css/bootstrap.min.css">
 	<link rel="stylesheet" type="text/css" href="../static/css/index.css">
 	<link rel="stylesheet" type="text/css" href="../static/css/nav.css">
 	<script src="../static/js/jquery-2.2.3.min.js"></script>
    <script src="../static/js/bootstrap.min.js"></script>
  </head>
  <body>
   <div class="navbar navbar-default navbar-fixed-top">
        <div class="navbar-header">
           　        		<a href="##" class="navbar-brand">爱心公益网 </a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="../static/person/index.jsp">公益首页</a></li>
            <li><a href="../static/person/listinfo.jsp">公益活动</a></li>
            <li><a href="../static/person/personal.jsp">个人中心</a></li>
            <li><a href="../static/person/help.jsp">我要求助</a></li>
            <li><a href="../static/admin/allproject.jsp">管理活动</a></li>
        </ul>
        <form action="##" class="navbar-form navbar-left">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="请输入关键词" />
            </div>
            <button type="submit" class="btn btn-default">搜索</button>
        </form>
        <div class="navbar-right text-danger">
        	欢迎，<span></span><a class="text-danger">登录</a>&nbsp;|&nbsp;<span><a class="text-danger">注销</a></span>
        </div>
    </div>
    <div class="container">
	    <!-- 轮播图部分 -->
	    <div class="row col-xs-10 col-xs-offset-1">
		   <div class="carousel slide" id="slidershow" data-ride="carousel">
			    <!-- 设置图片轮播的顺序 -->
			    <ol class="carousel-indicators">
			        <li class="active" data-target="#slidershow" data-slide-to="0"></li>
			        <li data-target="#slidershow" data-slide-to="1"></li>
			        <li data-target="#slidershow" data-slide-to="2"></li>
			    </ol>
		    <!-- 设置轮播图片 -->
		    <div class="carousel-inner">
		        <div class="item active">
		            <a href="##"><img src="../static/img/c01.jpg" alt=""></a>
		                <div class="carousel-caption">
		                    <h4>
		                    	<a class="btn btn-success btn-sm">专题</a>
		                    	<span>贫困孤儿助养</span>
		                    </h4>
		                </div>

		        </div>
		        <div class="item">
		            <a href="##"><img src="../static/img/c02.jpg" alt=""></a>
		        			<div class="carousel-caption">
		                    <h4>
		                    	<a class="btn btn-danger btn-sm">活动</a>
		                    	<span>99公益日 一起爱</span>
		                    </h4>
		                </div>
		        </div>
		         <div class="item">
		            <a href="##"><img src="../static/img/c04.jpg" alt=""></a>
		        			<div class="carousel-caption">
		                    <h4>
		                    	<a class="btn btn-danger btn-sm">活动</a>
		                    	<span>免费午餐，小善大爱</span>
		                    </h4>
		                </div>
		        </div>
		    </div>
		    <!-- 设置轮播图片控制器 -->
		    <a class="left carousel-control" href="#slidershow" data-slide="prev">
		        <span class="fa fa-left fa-angle-left fa-3x"></span>
		    </a>
		    <a class="right carousel-control" href="#slidershow" data-slide="next">
		        <span class="fa fa-right fa-angle-right fa-3x"></span>
		    </a>
			</div>
		</div>
			<div class="row">
				<div class="col-xs-10 col-xs-offset-1">
					<div class="col-xs-8">
					<!-- 信息列表 -->
					 <div class="media">
					 <h4 class="media-heading h3 text-a">贵州遵义:82岁愚公曾带村民绝壁上凿出生命渠</h4>
				        <a class="pull-left fixedimg" href="#">
				            <img class="media-object" src="./img/list1.jpg">
				        </a>
				        <div class="media-body">
				            <div class="descript">
				            	贵州遵义有一条长7200米的生命渠，以潺潺渠水润泽了当地1200多人，
				            	被老百姓亲切地称为“大发渠”，因为修建这条生命之渠的领头人正是草王坝老支书黄大发。
							</div>
							<br>
							<p class="text-muted small">
								<span class="date">2017-04-21 11:53:07更新</span>
							</p>
							<p class="text-right text-muted lead1">
								<i class="fa fa-commenting-o fa-fw"></i>
								<a class="text-muted">详情</a>
							</p>
							
				        </div>
    				</div>
					<!-- 重复重复 -->
					
					<!-- 重复重复 -->					
					</div>
					<div class="col-xs-4">
					<div class="list2">
						<div class="imgtop"></div>
						<div class="imgbody">
						  <dl>
							<dt>历史善款总额：</dt>
							<dd><span>1,788,912,172</span>元</dd>
						</dl>
						<dl>
							<dt>历史爱心总人次：</dt>
							<dd><span>105,904,380</span>人次</dd>
						</dl>
						</div>
					</div>
					<div class="list3">
					   <div class="title">
					   		<span class="listtitle h2">乐捐</span>
					   		<span class="more pull-right">更多</span>
					   </div>
					   <div class="body">
					   		<img src="./img/list22.jpg">
					   </div>
					</div>
				</div>
			</div>
	</div>
	</div>
  <footer>
  		<div class="layout partner">
		<div class="hd"><h2>联系我们</h2></div>
		<div class="bd"></div>
		<div class="style123">
		<p>公益项目咨询：gongyi@qq.com|公益项目合作：gongyi@qq.com</p>
		<p>主办：徐富豪 ，王小婷，王春晓</p>
		<p><a>意见反馈</a>|<a>网友投诉</a></p>
		</div>
		<div class="bd"></div><br>
		<div class="text-center">@版权归徐富豪 ，王小婷，王春晓所有</div>
	</div>
  </footer>  
  

  </body>
</html>
