<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/common/taglibs.jsp"%>
      <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
<head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>12</title>  
    <link rel="stylesheet" href="/ssm_dorm_manage/resource/css/pintuer.css">
    <link rel="stylesheet" href="/ssm_dorm_manage/resource/css/admin.css">
    <script src="/ssm_dorm_manage/resource/js/jquery.js"></script>
    <script src="/ssm_dorm_manage/resource/js/pintuer.js"></script>  

<title>床位管理</title>
</head>
<body>
<form method="post" action="" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder">我的床位</strong></div>
    <div class="padding border-bottom">
      <ul class="search">
        <li>
        </li>
         <%-- <li>
			<input type="text" placeholder="请输入床位编号" name="bedNo"  value="${obj.bedNo}" class="input" style="width:250px; line-height:17px;display:inline-block" />
          <a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()" > 搜索</a></li>
          </li> --%>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th width="">宿舍楼号</th>
        <th width="">宿舍号</th>
        <th width="">床位编号</th>
      </tr>
	  <c:if test="${fn:length(list) > 0}">
	        <c:forEach items="${list}" var="data" varStatus="l">
	        <tr>
	           <td>${data.buildingNo}</td>
	           <td>${data.domitoryNo}</td>
	           <td>${data.bedNo}</td>
	
	        </tr>
	       </c:forEach>
	   </c:if>
		<c:if test="${fn:length(list) == 0}">
			<tr>
			<td colspan="3">您还未入住</td>
			</tr>
		</c:if>
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
		window.location.href="/ssm_dorm_manage/bed/delete.do?id="+id;
	}
}

</script>
</body>
</html>