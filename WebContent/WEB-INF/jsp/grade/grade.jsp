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
    <div class="panel-head"><strong class="icon-reorder">年级列表</strong></div>
    <div class="padding border-bottom">
      <ul class="search">
        <li>
           <a class="button border-main icon-plus-square-o" href="${ctx }/grade/add.do"> 添加</a> 
        </li>
         <li>
          <input type="text" placeholder="请输入年级名称" name="name"  value="${obj.name}" class="input" style="width:250px; line-height:17px;display:inline-block" />
          <a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()" > 搜索</a></li>
          </li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
       <!--  <th width="100" style="text-align:left; padding-left:20px;">ID</th> -->
        <th width="">年级名称</th>
        <th width="310">操作</th>
      </tr>
        <c:forEach items="${list}" var="data" varStatus="l">
        <tr>
          <%--  <td>${data.id}</td> --%>
           <td>${data.name}</td>
           <td>
              <div class="button-group"> 
              <a class="button border-main" href="/ssm_dorm_manage/grade/update.do?id=${data.id}"><span class="icon-edit"></span>修改</a> 
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
		window.location.href="/ssm_dorm_manage/grade/delete.do?id="+id;
	}
}

</script>
</body>
</html>