<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="/common/taglibs.jsp"%>
 <%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title><sitemesh:title /></title>
    ﻿<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="format-detection" content="telephone=no">
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="alternate icon" type="image/png" href="/ssm_dorm_manage/resource/res/images/favicon.png">
<link rel='icon' href='favicon.ico' type='image/x-ico' />
<meta name="description" content="" />
<meta name="keywords" content="" />
<link rel="stylesheet" href="/ssm_dorm_manage/resource/res/css/default.min.css?t=227" />
<!--[if (gte IE 9)|!(IE)]><!-->
<script type="text/javascript" src="/ssm_dorm_manage/resource/res/lib/jquery/jquery.min.js"></script>
<!--<![endif]-->
<!--[if lte IE 8 ]>
<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="lib/amazeui/amazeui.ie8polyfill.min.js"></script>
<![endif]-->
<script type="text/javascript" src="/ssm_dorm_manage/resource/res/lib/handlebars/handlebars.min.js"></script>
<script type="text/javascript" src="/ssm_dorm_manage/resource/res/lib/iscroll/iscroll-probe.js"></script>
<script type="text/javascript" src="/ssm_dorm_manage/resource/res/lib/amazeui/amazeui.min.js"></script>
<script type="text/javascript" src="/ssm_dorm_manage/resource/res/lib/raty/jquery.raty.js"></script>
<script type="text/javascript" src="/ssm_dorm_manage/resource/res/js/main.min.js?t=1"></script>

<link rel="stylesheet" href="/ssm_dorm_manage/resource/css/bootstrap.min.css">
<script type="text/javascript" src="/ssm_dorm_manage/resource/js/bootstrap.min.js"></script>
<sitemesh:head/>
</head>
<body>
<header class="hd">
    <div class="cml-g">
        <div class="hd-left">
            <div class="hd-logo">
                <a href="#">
                    <img src="/ssm_dorm_manage/resource/res/images/logo.png" />
                </a>
            </div>
            <div class="hd-company">
                <strong>${config.name }</strong>
                <span>${config.web }</span>
            </div>   
        </div>
        <!-- <div class="hd-right">
            <a href="#">ENGLISH</a><i class="font">&#xe62b;</i><a href="#">中文</a>
        </div> -->
    </div>
</header>

<div class="hd-nav">
    <button class="am-show-sm-only am-collapsed" data-am-collapse="{target: '.hd-navul'}">&#xe68b;</button>
    <nav>
        <ul class="hd-navul am-collapse" style="height: 0px;">
            <li id="index"><a href="/ssm_dorm_manage/web/index.do">首页</a></li>
            <li id="about"><a href="/ssm_dorm_manage/about/webAbout.do">关于我们</a></li>
            <li id="news"><a href="/ssm_dorm_manage/news/webNews.do">新闻资讯</a></li>
            <li id="product"><a href="/ssm_dorm_manage/product/products.do?type=5">产品展示</a></li>
            <li id="honor"><a href="/ssm_dorm_manage/honor/honors.do">资质荣誉</a>
            <li id="contact"><a href="/ssm_dorm_manage/web/contact.do">联系我们</a>
        </ul>
    </nav>
</div>

<sitemesh:body />

<footer class="footer">
    <div class="cml-g">
        <h2>
            <span>${config.name }</span>
            <span>${config.beian }</span>
        </h2>
        <h3>
            <span>地址:${config.address }</span>
            <span>邮编:${config.email }</span>
            <span>电话:${config.tel }</span>
        </h3>
<h3>
<!-- <span><a href="http://www.haothemes.com/" target="_blank" title="好主题">好主题</a>提供 - More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></span> --></h3>
    </div>
</footer>
<script type="text/javascript">
$(document).ready(function(){
	//设置菜单当前项
	var current = '${current}';
	$("#"+current).addClass("on");
	
});
</script>
</body>
</html>