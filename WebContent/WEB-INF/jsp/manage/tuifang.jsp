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

<title>专业管理</title>
</head>
<body>
<form method="post" action="" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder">学生信息列表</strong></div>
    <div class="padding border-bottom">
      <ul class="search">
        <li>
        </li>
         <li>
         	 <input type="text" placeholder="请输入学生名称" name="nickName"  value="${obj.nickName}" class="input" style="width:250px; line-height:17px;display:inline-block" />
          <a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()" > 搜索</a></li>
          </li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th width="">学院名</th>
        <th width="">年级</th>
        <th width="">专业名</th>
        <th width="">班级名</th>
        <th width="">学号</th>
        <th width="">姓名</th>
        <th width="">性别</th>
        <th width="">手机号</th>
        <th width="">籍贯</th>
        <th width="310">操作</th>
      </tr>
        <c:forEach items="${list}" var="data" varStatus="l">
        <tr>
           <td>${data.collegeName}</td>
           <td>${data.gradeName}</td>
           <td>${data.majorName}</td>
           <td>${data.teamName}</td>
           <td>${data.studentId}</td>
           <td>${data.nickName}</td>
           <td>
           <c:if test="${data.sex == 1 }">
                                  男
           </c:if>
            <c:if test="${data.sex == 0 }">
                                  女
           </c:if>
           </td>
           <td>${data.phone}</td>
           <td>${data.jiguan}</td>
           <td>
              <div class="button-group"> 
              <a class="button border-main" href="javascript:void(0)" onclick="tuifang('${data.id}')"><span class="icon-trash-o"></span> 退房</a> </div>
           </td>
        </tr>
       </c:forEach>
      </tr> 
    </table>
  </div>
</form>
<script type="text/javascript">

//搜索
function changesearch(){	
		$("#listform").submit();
}

//单个删除
function tuifang(id){
	if(confirm("您确定要退房吗?")){
		window.location.href="/ssm_dorm_manage/manage/exTuifang.do?id="+id;
	}
}
</script>
</body>
</html>