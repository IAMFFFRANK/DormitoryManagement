<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/common/taglibs.jsp"%>

<html>
<head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title></title>  
    <link rel="stylesheet" href="/ssm_dorm_manage/resource/css/pintuer.css">
    <link rel="stylesheet" href="/ssm_dorm_manage/resource/css/admin.css">
    <script src="/ssm_dorm_manage/resource/js/jquery.js"></script>
    <script src="/ssm_dorm_manage/resource/js/pintuer.js"></script>  

<title>年级列表</title>
</head>
<body>
<form method="post" action="" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder">宿舍楼列表</strong></div>
    <div class="padding border-bottom">
      <ul class="search">
        <li>
           <a class="button border-main icon-plus-square-o" href="${ctx }/building/add.do"> 添加</a> 
        </li>
         <li>
          <input type="text" placeholder="请输入宿舍楼名称" name="buildingNo"  value="${obj.buildingNo}" class="input" style="width:250px; line-height:17px;display:inline-block" />
          <a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()" > 搜索</a></li>
          </li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>

        <th width="">宿舍楼名称</th>
		<th width="">位置</th>
		<th width="">宿舍层数</th>
		<th width="">每层房间数</th>
		<th width="">管理员</th>
        <th width="310">操作</th>
      </tr>
        <c:forEach items="${list}" var="data" varStatus="l">
        <tr>

           <td>${data.buildingNo}</td>
		   <td>${data.position}</td>
		   <td>${data.layerNumber}</td>
		   <td>${data.roomNumber}</td>
		   <td>
		   		<c:if test="${empty  data.manageName }">
		   			未分配
		   		</c:if>
		   		<c:if test="${not empty  data.manageName }">
		   			${data.manageName }
		   		</c:if>
		   </td>
           <td>
              <div class="button-group">
              <c:if test="${empty  data.manageName }">
              	<a class="button border-main" href="/ssm_dorm_manage/building/setManage.do?id=${data.id}"><span class="icon-edit"></span>分配管理员</a> 
              </c:if>
              <a class="button border-main" href="/ssm_dorm_manage/building/update.do?id=${data.id}"><span class="icon-edit"></span>修改</a> 
              <a class="button border-red" href="javascript:void(0)" onclick="return del('${data.id}')"><span class="icon-trash-o"></span> 删除</a> </div>
           </td>
        </tr>
       </c:forEach>
    </table>
  </div>
</form>
<script type="text/javascript">

//搜索
function changesearch(){	
		$("#listform").submit();
}

//单个删除
function del(id){
	if(confirm("您确定要删除吗?")){
		window.location.href="/ssm_dorm_manage/building/delete.do?id="+id;
	}
}

</script>
</body>
</html>