<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/common/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
   <title>宿舍管理系统</title>  
    <link rel="stylesheet" href="/ssm_dorm_manage/resource/css/pintuer.css">
    <link rel="stylesheet" href="/ssm_dorm_manage/resource/css/admin.css">
    <script src="/ssm_dorm_manage/resource/js/jquery.js"></script>
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="/ssm_dorm_manage/resource/images/z.jpg" class="radius-circle rotate-hover" height="50" alt="" />
         <span style="color: black;">宿舍管理系统</span>
    </h1>
  </div>
  <div class="head-l">
<!--   <a class="button button-little bg-green" href="" target="_blank"><span class="icon-home"></span> 前台首页</a>  -->
&nbsp;&nbsp;<a class="button button-little bg-red" href="/ssm_dorm_manage/login/tuichu.do"><span class="icon-power-off"></span> 退出登录</a> 
<font style="color:red;">欢迎，${user.userName }</font>
</div>
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span><span style="color: black;">菜单列表</span></strong></div>
  <h2><span class="icon-user"></span>后台管理</h2>
  <ul style="display:block">
    
       <li><a href="/ssm_dorm_manage/college/findByObj.do" target="right"><span class="icon-caret-right"></span>院系管理</a></li>
       <li><a href="/ssm_dorm_manage/major/findByObj.do" target="right"><span class="icon-caret-right"></span>专业管理</a></li>
       <li><a href="/ssm_dorm_manage/grade/findByObj.do" target="right"><span class="icon-caret-right"></span>年级管理</a></li>
       <li><a href="/ssm_dorm_manage/team/findByObj.do" target="right"><span class="icon-caret-right"></span>班级管理</a></li>
       <li><a href="/ssm_dorm_manage/student/findByObj.do" target="right"><span class="icon-caret-right"></span>学生管理</a></li>
       <li><a href="/ssm_dorm_manage/manage/findByObj.do" target="right"><span class="icon-caret-right"></span>宿舍楼管理员管理</a></li>
       <li><a href="/ssm_dorm_manage/building/findByObj.do" target="right"><span class="icon-caret-right"></span>宿舍楼管理</a></li>
       <li><a href="/ssm_dorm_manage/domitory/findByObj.do" target="right"><span class="icon-caret-right"></span>宿舍管理</a></li>
       <li><a href="/ssm_dorm_manage/bed/findByObj.do" target="right"><span class="icon-caret-right"></span>床位管理</a></li>
       <li><a href="/ssm_dorm_manage/student/ruzhu.do" target="right"><span class="icon-caret-right"></span>学生入住登记</a></li>
	   <li><a href="/ssm_dorm_manage/student/tuifang.do" target="right"><span class="icon-caret-right"></span>学生退房管理</a></li>
       
  </ul>   
</div>
<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>
<ul class="bread">
  <li><a href="##" id="a_leader_txt">首页</a></li>
<!--   <li><b>当前语言：</b><span style="color:red;">中文</php></span> -->
<!--   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;切换语言：<a href="##">中文</a> &nbsp;&nbsp;<a href="##">英文</a> </li> -->
</ul>
<div class="admin">
  <iframe scrolling="auto" rameborder="0" src="/ssm_dorm_manage/login/welcome.do" name="right" width="100%" height="100%"></iframe>
</div>
<div style="text-align:center;">
</div>
</body>
</html>