<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="https://www.hy.include" prefix="hy" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<%@ taglib uri="http://file-upload.upload" prefix="file"%>
<hy:extends name="title">博客管理系统</hy:extends>
<hy:extends name="css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/main/css/admin.css">
    <style type="text/css">
	    .input {
	        width: 80px;
	        height: 20px;
	        line-height: 20px;
	        background: #0088ff;
	        text-align: center;
	        display: inline-block;
	        overflow: hidden;
	        position: relative;
	        text-decoration: none;
	        top: 5px;
	    }
	     
	    .input:hover {
	        background: #ff8800;
	    }
	     
	    .file {
	        opacity: 0;
	        filter: alpha(opacity =     0);
	        font-size: 50px;
	        position: absolute;
	        top: 0;
	        right: 0;
	    }
	     
	    a:link {
	        text-decoration: none;
	    }
	    a:visited {
	        text-decoration: none;
	    }
	    a:hover {
	        color: #999999;
	        text-decoration: underline;
	    }
    </style>
</hy:extends>

<hy:extends name="javascript">
    <script type="text/javascript">
        if("ontouchend" in document) document.write("<script src='${ctx}/static/admin/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
    </script>
    <script src="${ctx}/static/admin/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/admin/js/typeahead-bs2.min.js"></script>
	
    <script src="${ctx}/static/ckeditor/ckeditor.js"></script>
    <script src="${ctx}/static/ckeditor/config.js"></script>
    <script src="${ctx}/static/ckeditor/sample.js"></script>
    <!-- page specific plugin scripts -->

    <!--[if lte IE 8]>
    <script src="${ctx}/static/admin/js/excanvas.min.js"></script>

    <![endif]-->
    <link rel="stylesheet" href="${ctx}/static/ztree/css/demo.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/static/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/static/ckeditor/skins/moono/editor.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/static/ckeditor/samples.css" type="text/css">
</hy:extends>
<hy:extends name="body">
    <div class="breadcrumbs" id="breadcrumbs">
        <script type="text/javascript">
            try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
        </script>
        <ul class="breadcrumb">
            <li>
                <i class="icon-home home-icon"></i>
                <a href="#">首页</a>
            </li>
            <li class="active">新增海报</li>
        </ul><!-- .breadcrumb -->
    </div>

    <div class="page-content">
        <div class="row">
            <div class="col-xs-12">
			    <file:upload/>
                <!-- PAGE CONTENT BEGINS -->
                <form class="form-horizontal" action="${ctx}/tribune/user/note/save.html" id="createForm" role="form" method="post">
                    <div class="clearfix form-actions">
                        <div class="col-md-offset-3 col-md-9">
                        	<input id="versionIds" style="display: none;" name="versionIds">
                            <button class="btn btn-info" type="submit">
                                <i class="icon-ok bigger-110"></i>
                                                                           提交
                            </button>
                            &nbsp; &nbsp; &nbsp;
                            <button class="btn btn-danger" type="reset">
                                <i class="icon-undo bigger-110"></i>
                                                                           重置
                            </button>
                            &nbsp; &nbsp; &nbsp;
                            <button class="btn" type="button" onclick="history.go(-1)">
                                <i class="icon-backward bigger-110"></i>
                                                                           返回
                            </button>
                        </div>
                    </div>
                </form>
                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.page-content -->
    <script type="text/javascript" src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/Validform/js/Validform_v5.3.2.js"></script>
    <script src="${ctx}/static/admin/js/jquery-ui-1.10.3.custom.min.js"></script>
    <script src="${ctx}/static/admin/js/chosen.jquery.min.js"></script>
    <script src="${ctx}/static/admin/js/date-time/bootstrap-datepicker.min.js"></script>
    <!-- ace scripts -->
    <script src="${ctx}/static/admin/js/ace-elements.min.js"></script>
    <script src="${ctx}/static/admin/js/ace.min.js"></script>
    <!-- inline scripts related to this page -->
</hy:extends>
<jsp:include page="/base/admin.jsp"/>
