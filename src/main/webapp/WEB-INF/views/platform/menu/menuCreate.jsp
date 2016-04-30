<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<%@ taglib uri="http://curtainContain/tag" prefix="curtain"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <curtain:include href="base/bootstap.jsp" />
    <script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/static/project/css/index.css">
    <title>菜单信息添加</title>
    <script type="text/javascript">
        $(document).ready(function(){
        	$("#parentName").click(function() {
    			$(".treeColumn").slideDown(500);
    		});
    		$(".treeColumn").mouseleave(function(){
    			$(".treeColumn").slideUp(500);
    		});
    		
    		$('#parentName').keyup(function() {
    			if($('#parentName').val() == ''){
    				$('#parentId').val('');
    			}
    		});
        	
        	$(".registerform").Validform({
                tiptype:2,
                datatype:{
                    "*3-20": /^[^\s]{3,20}$/,
                    "z2-4" : /^[\u4E00-\u9FA5\uf900-\ufa2d]{2,4}$/
                }
            });
        	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
        });
        
        var setting = {
       		data: {
       			simpleData: {
       				enable: true
       			}
       		},
       		callback: {
       			onClick: onClick
       		}
       	};

       	var zNodes = ${menuTrees};
       	function onClick(event,treeId, treeNode) {
       		$("#parentId").val(treeNode.id);
       		$("#parentName").val(treeNode.name);
       		$(".treeColumn").hide();
       	}
    </script>
</head>
<body>
	<div class="container-fluid">
        <form class="form-horizontal registerform" method="post" action="${ctx}/platform/menu/menuSave">
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">菜单名称</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" name="name" id="name" placeholder="请输入菜单名称"
                           datatype="*3-20" nullmsg="请输入菜单名称！" errormsg="菜单名称至少3个字符,最多20个字符！">
                </div>
                <div class="Validform_checktip"></div>
            </div>
            <div class="form-group">
                <label for="href" class="col-sm-2 control-label">链接地址</label>
                <div class="col-sm-5">
                    <input type="text" name="href" class="form-control" id="href" placeholder="请输入链接地址"
                           datatype="*" nullmsg="请输入链接地址！" errormsg="链接地址不能为空" >
                </div>
                <div class="Validform_checktip"></div>
            </div>
            <div class="form-group">
                <label for="parentName" class="col-sm-2 control-label">上级菜单</label>
                <div class="col-sm-5">
                	<input type="hidden" id="parentId" name="parentId">
                    <input type="text" name="parentName" class="form-control" id="parentName" placeholder="请输入上级菜单" />
                	<div class="treeColumn">
                		<div class="zTreeDemoBackground left">
							<ul id="treeDemo" class="ztree"></ul>
						</div>
                	</div>
                </div>
            </div>
            <div class="form-group">
                <label for="authority" class="col-sm-2 control-label">菜单别名</label>
                <div class="col-sm-5">
                    <input type="text" name="authority" class="form-control" id="authority" placeholder="请输入菜单别名"
                           datatype="s6-20" nullmsg="请输入菜单别名！" errormsg="别名范围在6~20位之间！" >
                </div>
                <div class="Validform_checktip"></div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-6">
                    <button type="submit" class="btn btn-primary">添加</button>
                    <button type="button" onclick="javascript:history.go(-1)" class="btn btn-default">返回</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>