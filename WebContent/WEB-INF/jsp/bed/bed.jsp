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

<title>床位管理</title>
</head>
<body>
<form method="post" action="" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder">床位列表</strong></div>
    <div class="padding border-bottom">
      <ul class="search">
        <li>
          <%--  <a class="button border-main icon-plus-square-o" href="${ctx }/bed/add.do"> 添加</a>  --%>
        </li>
         <li>
			<input type="text" placeholder="请输入床位编号" name="bedNo"  value="${obj.bedNo}" class="input" style="width:250px; line-height:17px;display:inline-block" />
          <a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()" > 搜索</a></li>
          </li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th width="">宿舍楼号</th>
        <th width="">宿舍号</th>
        <th width="">床位编号</th>
		<th width="">状态</th>
        <th width="310">操作</th>
      </tr>
        <c:forEach items="${list}" var="data" varStatus="l">
        <tr>
           <td>${data.buildingNo}</td>
           <td>${data.domitoryNo}</td>
           <td>${data.bedNo}</td>
		    <td>
			 <c:if test="${empty data.studentName}" >
				未入住
			 </c:if>
			 <c:if test="${not empty data.studentName}" >
			 ${data.studentName}已入住
			 </c:if>
		   </td>
           <td>
              <div class="button-group"> 
              <a class="button border-main" href="/ssm_dorm_manage/bed/update.do?id=${data.id}"><span class="icon-edit"></span>修改</a> 
			  <c:if test="${empty data.studentName}" >
              <a class="button border-red" href="javascript:void(0)" onclick="return del('${data.id}')"><span class="icon-trash-o"></span> 删除</a> </div>
			  </c:if>
           </td>
        </tr>
       </c:forEach>
<%--       <tr>
        <td colspan="8"><div class="pagelist">
        <!-- 分页开始 -->
			      <pg:pager  url="/ssm_dorm_manage/bed/findByObj.do" maxIndexPages="5" items="${pagers.total}"  maxPageItems="15" export="curPage=pageNumber" >
					<pg:last>  
						共${pagers.total}记录,共${pageNumber}页,  
					</pg:last>  
						当前第${curPage}页 
			        <pg:first>  
			    		<a href="${pageUrl}">首页</a>  
					</pg:first>  
					<pg:prev>  
			    		<a href="${pageUrl}">上一页</a>  
					</pg:prev>  
			       	<pg:pages>  
			        	<c:choose>  
			            	<c:when test="${curPage eq pageNumber}">  
			                	<font color="red">[${pageNumber }]</font>  
			            	</c:when>  
			            	<c:otherwise>  
			               		<a href="${pageUrl}">${pageNumber}</a>  
			            	</c:otherwise>  
			        	</c:choose>  
			    	</pg:pages>
			             
			        <pg:next>  
			    		<a href="${pageUrl}">下一页</a>  
					</pg:next>  
					<pg:last>  
						<c:choose>  
			            	<c:when test="${curPage eq pageNumber}">  
			                	<font color="red">尾页</font>  
			            	</c:when>  
			            	<c:otherwise>  
			               		<a href="${pageUrl}">尾页</a>  
			            	</c:otherwise>  
			        	</c:choose> 
			    		  
					</pg:last>
				</pg:pager>
				 </div></td>
                    <!-- 分页结束 -->
      </tr> --%>
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