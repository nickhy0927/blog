<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<%@ taglib prefix="hy" uri="https://www.hy.include" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <hy:block name="title"></hy:block>
    </title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/Validform/css/style.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/Validform/css/demo.css">
    <!-- basic styles -->
    <link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="${ctx}/static/admin/css/font-awesome.min.css" />
    <!--[if IE 7]>
    <link rel="stylesheet" href="${ctx}/static/admin/css/font-awesome-ie7.min.css" />
    <![endif]-->
    <!-- page specific plugin styles -->
    <!-- fonts -->
    <!-- ace styles -->
    <link rel="stylesheet" href="${ctx}/static/admin/css/ace.min.css" />
    <link rel="stylesheet" href="${ctx}/static/admin/css/ace-rtl.min.css" />
    <link rel="stylesheet" href="${ctx}/static/admin/css/ace-skins.min.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/main/css/admin.css">
    <link rel="stylesheet" href="${ctx}/static/admin/css/jquery-ui-1.10.3.custom.min.css"/>
    <link rel="stylesheet" href="${ctx}/static/admin/css/chosen.css"/>
    <link rel="stylesheet" href="${ctx}/static/admin/css/datepicker.css"/>
    <link rel="stylesheet" href="${ctx}/static/admin/css/bootstrap-timepicker.css"/>
    <link rel="stylesheet" href="${ctx}/static/admin/css/daterangepicker.css"/>
    <link rel="stylesheet" href="${ctx}/static/admin/css/colorpicker.css"/>
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="${ctx}/static/admin/css/ace-ie.min.css" />
    <![endif]-->
    <link rel="stylesheet" href="${ctx}/static/main/css/admin.css">
    <!-- inline styles related to this page -->
    <!-- ace settings handler -->
    <script src="${ctx}/static/admin/js/ace-extra.min.js"></script>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="${ctx}/static/admin/js/html5shiv.js"></script>
    <script src="${ctx}/static/admin/js/respond.min.js"></script>
    <script src="${ctx}/static/admin/js/respond.min.js"></script>
    <![endif]-->
    <hy:block name="css"></hy:block>
    <style type="text/css">
        #bg {
            display: none;
            position: absolute;
            top: 0%;
            left: 0%;
            width: 100%;
            height: 100%;
            background-color: black;
            z-index: 1001;
            -moz-opacity: 0.7;
            opacity: .70;
            filter: alpha(opacity=70);
        }

        #show {
            background-color: white;
            border: 0 solid #e8e9f7;
            display: none;
            height: 12%;
            left: 35%;
            overflow: auto;
            padding: 15px;
            position: absolute;
            top: 50%;
            width: 20%;
            z-index: 1002;
        }
        a:link {
            text-decoration-line: none;
        }
    </style>
    <script type="text/javascript">
        function showdiv() {
            document.getElementById("bg").style.display = "block";
            document.getElementById("show").style.display = "block";
        }
        function hidediv() {
            document.getElementById("bg").style.display = 'none';
            document.getElementById("show").style.display = 'none';
        }
    </script>
    <!-- basic scripts -->
    <!--[if !IE]> -->
    <script src="${ctx}/static/jquery/jquery.min.js"></script>
    <!-- <![endif]-->
    <!--[if IE]>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <![endif]-->
    <!--[if !IE]> -->
    <script type="text/javascript">
        window.jQuery || document.write("<script src='${ctx}/static/admin/js/jquery-2.0.3.min.js'>"+"<"+"script>");
    </script>
    <!-- <![endif]-->

    <!--[if IE]>
    <script type="text/javascript">
        window.jQuery || document.write("<script src='${ctx}/static/admin/js/jquery-1.10.2.min.js'>"+"<"+"script>");
    </script>
    <![endif]-->
    <script type="text/javascript">
        if("ontouchend" in document) document.write("<script src='${ctx}/static/admin/js/jquery.mobile.custom.min.js'>"+"<"+"script>");
    </script>
    <script src="${ctx}/static/admin/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.core-3.5.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/ztree/js/jquery.ztree.excheck-3.5.js"></script>
    <script src="${ctx}/static/admin/js/typeahead-bs2.min.js"></script>
    <!-- page specific plugin scripts -->
    <!--[if lte IE 8]>
    <script src="${ctx}/static/admin/js/excanvas.min.js"></script>
    <![endif]-->
    <script src="${ctx}/static/admin/js/jquery-ui-1.10.3.custom.min.js"></script>
    <script src="${ctx}/static/admin/js/jquery.ui.touch-punch.min.js"></script>
    <script src="${ctx}/static/admin/js/jquery.slimscroll.min.js"></script>
    <script src="${ctx}/static/admin/js/jquery.easy-pie-chart.min.js"></script>
    <script src="${ctx}/static/admin/js/jquery.sparkline.min.js"></script>
    <script src="${ctx}/static/admin/js/flot/jquery.flot.min.js"></script>
    <script src="${ctx}/static/admin/js/flot/jquery.flot.pie.min.js"></script>
    <script src="${ctx}/static/admin/js/flot/jquery.flot.resize.min.js"></script>
    <!-- ace scripts -->
    <script src="${ctx}/static/admin/js/ace-elements.min.js"></script>
    <script src="${ctx}/static/admin/js/ace.min.js"></script>
    <script src="${ctx}/static/bootstrap/js/bootstrap.js"></script>
    <!-- 填充javascript的代码 -->
    <hy:block name="javascript"></hy:block>

</head>
<body style="">
    <div class="container-fluid">
        <hy:block name="body"></hy:block>
        <div id="bg"></div>
        <div id="show">测试
            <input id="btnclose" type="button" value="Close" onclick="hidediv();"/>
        </div>

    </div>
</body>
</html>
