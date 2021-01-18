<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
    <%@include file="/common/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
   <title>管理中心</title>  
    <link rel="stylesheet" href="/ssm_dorm_manage/resource/css/pintuer.css">
    <link rel="stylesheet" href="/ssm_dorm_manage/resource/css/admin.css">
    <script src="/ssm_dorm_manage/resource/js/jquery.js"></script>
     <script src="/ssm_dorm_manage/resource/js/pintuer.js"></script>
      <script type="text/javascript" src="/ssm_dorm_manage/resource/js/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="/ssm_dorm_manage/resource/js/ueditor/ueditor.all.min.js"></script>  
</head>
<style>
    .file1 {
        position: relative;
        display: inline-block;
        background: #D0EEFF;
        border: 1px solid #99D3F5;
        border-radius: 4px;
        padding: 4px 12px;
        overflow: hidden;
        color: #1E88C7;
        text-decoration: none;
        text-indent: 0;
        line-height: 20px;
    }
    .file1 input {
        position: absolute;
        font-size: 100px;
        right: 0;
        top: 0;
        opacity: 0;
    }
    .file1:hover {
        background: #AADFFD;
        border-color: #78C3F3;
        color: #004974;
        text-decoration: none;
    }
</style>
<body>
<div class="panel admin-panel margin-top">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>学生入住登记</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="/ssm_dorm_manage/student/exAdd.do" enctype="multipart/form-data">
    
	<div class="form-group">
          <div class="label">
            <label>宿舍楼号：</label>
          </div>
          <div class="field">
            <select id="buildingId" name="buildingId" onchange="getDomitory()" class="input w50">
              <option value=""  selected="selected">请选择宿舍楼号</option>
               <c:forEach items="${buildingList}" var="data" varStatus="l">
               		<option value="${data.id}">${data.buildingNo }</option>
               </c:forEach>
            </select>
            <div class="tips"></div>
          </div>
        </div>        
        
        <div class="form-group">
          <div class="label">
            <label>宿舍号：</label>
          </div>
          <div class="field">
            <select name="domitoryId" id="domitoryId" onchange="getBed()"  class="input w50">
               <option value="">--请选择--</option>  
            </select>
            <div class="tips"></div>
          </div>
        </div>       
        
        <div class="form-group">
          <div class="label">
            <label>床位编号：</label>
          </div>
          <div class="field">
            <select name="bedId" id="bedId" class="input w50">
               <option value="">--请选择--</option>  
            </select>
            <div class="tips"></div>
          </div>
        </div>    
        
		 <div class="form-group">
			<div class="label">
			  <label>学生：</label>
			</div>
			<div class="field">
			  <input type="hidden" class="input w50" id="id"  value="" />         
			  <input type="text" class="input w50" id="nickName" name="nickName" value="" data-validate="" /> 
			  <input type="button" class="button border-main" value="选择学生" onclick="selectStudent()">
			  <div class="tips"></div>
			</div>
		  </div> 
	  
     <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="button" onclick="ruzhu();"> 入住</button>
        </div>
      </div>
       
    </form>
  </div>
</div>

<script type="text/javascript">
//通过ajax获取该宿舍楼下的所有宿舍
    function getDomitory() {
        var buildingId=$("#buildingId").val(); //获得第一个列表元素的主键
		var obj=document.getElementById("domitoryId");//回去id为。。的html元素
		var objd=document.getElementById("bedId");//回去id为。。的html元素
		$.ajax({
		    type : 'GET',
		    contentType : 'application/json',
		    url : '/ssm_dorm_manage/student/getDomitory.do?buildingId='+buildingId, //调用后台控制类的方法
		    dataType : 'json',	
		    success : function(data) {
		        obj.options.length=0;
		        objd.options.length=0;
		        obj.options.add(new Option('请选择宿舍号',''));
			$.each(data,function(i,item) {
			    obj.options.add(new Option(item.domitoryNo,item.id));
		         });
		    }
	    });
    }
  //通过ajax获取该宿舍下的所有可用床位
    function getBed() {
        var domitoryId=$("#domitoryId").val(); //获得第一个列表元素的主键
		var obj=document.getElementById("bedId");//回去id为。。的html元素
		$.ajax({
		    type : 'GET',
		    contentType : 'application/json',
		    url : '/ssm_dorm_manage/student/getBed.do?domitoryId='+domitoryId, //调用后台控制类的方法
		    dataType : 'json',	
		    success : function(data) {
		        obj.options.length=0;
		        obj.options.add(new Option('请选择床位号',''));
			$.each(data,function(i,item) {
			    obj.options.add(new Option(item.bedNo,item.id));
		         });
		    }
	    });
    }
	
	//选择学生弹框
	function selectStudent(){
		window.open ('/ssm_dorm_manage/student/selectStudent.do','','height=600,width=1200') 
	}
	
	//入住保存
	function ruzhu(){
		//数据校验
		var id = $("#id").val();
		var buildingId=$("#buildingId").val();
		var domitoryId=$("#domitoryId").val();
		var bedId=$("#bedId").val();
		
		if(buildingId == ''){
			alert("请选择宿舍楼！");
			return;
		}
		if(domitoryId == ''){
			alert("请选择宿舍！");
			return;
		}
		if(bedId == ''){
			alert("请选择床位！");
			return;
		}
		if(id == ''){
			alert("请选择学生！");
			return;
		}
		
		//通过校验，ajax提交保存
			$.ajax({
			//要用post方式 
			type : "get",
			//方法所在页面和方法名 
			url : "/ssm_dorm_manage/student/exRuzhu.do?id="+id+"&buildingId="+buildingId+"&domitoryId="+domitoryId+"&bedId="+bedId,
			contentType : "application/json; charset=utf-8",
			dataType : "json",
			data : {},
			success : function(data) {
				if(data == '0'){
					//保存成功
					$("#id").val();
					$("#nickName").val();
					alert("入住成功");
					
					//入住成功后跳到退房管理列表
					window.location.href="/ssm_dorm_manage/manage/tuifang.do";
				}else{
					//保存失败
					alert("入住失败");
				}


			},
			error : function(err) {
				alert(err);
			}
		});
	}
	
</script> 
</body>
</html>