<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<%@ taglib prefix="hy" uri="https://www.hy.include" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
        <hy:block name="title"></hy:block>
    </title>
    <link rel="shortcut icon" href="${ctx}/static/bbs/images/favicon.png" />

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="js/html5.js"></script>
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
