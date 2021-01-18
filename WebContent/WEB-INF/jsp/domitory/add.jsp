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
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>添加宿舍楼</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="/ssm_dorm_manage/domitory/exAdd.do" enctype="multipart/form-data">
     <div class="form-group">
          <div class="label">
            <label>宿舍楼号：</label>
          </div>
          <div class="field">
            <select name="buildingId" class="input w50">
              <option value="">请选择宿舍楼号</option>
               <c:forEach items="${buildingList}" var="data" varStatus="l">
               		<option value="${data.id}" selected="selected">${data.buildingNo }</option>
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
          <input type="text" class="input w50" name="domitoryNo"  data-validate="required:请输入宿舍号" />         
          <div class="tips"></div>
        </div>
      </div> 
     
	 <div class="form-group">
        <div class="label">
          <label>宿舍电话：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" name="tel"  data-validate="required:请输入宿舍电话" />         
          <div class="tips"></div>
        </div>
      </div> 
	  
	  <div class="form-group">
	   <div class="label">
            <label>宿舍类型：</label>
          </div>
          <div class="field">
            <select name="type" class="input w50">
              <option value="">请选择宿舍类型</option>
					<option value="1" selected="">单人间</option>
					<option value="2" selected="">双人间</option>
					<option value="4" selected="">四人间</option>
					<option value="6" selected="">六人间</option>
            </select>
            <div class="tips"></div>
          </div>
        </div>
		
     <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
        </div>
      </div>
       
    </form>
  </div>
</div>
</body>
</html>