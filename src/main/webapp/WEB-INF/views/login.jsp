<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
    <%-- <link rel="stylesheet" href="${ctx}/static/login/css/font-awesome.min.css"/>
     <link rel="stylesheet" href="${ctx}/static/login/css/loginMy.css"/>--%>
    <link rel="stylesheet" href="${ctx}/static/login/css/login.css"/>
    <script type="text/javascript" src="${ctx}/static/login/js/jquery-1.9.1.min.js"></script>
    <title></title>
    <script type="text/javascript">
        $(function () {
			
        	$(".submit").click(function(){
        		$("#loginForm").submit();
        	});
        	
            var height = window.innerHeight;
            $(".main").css({
                'margin-top':height/2 - 100
            })
            //得到焦点
            $("#password").focus(function () {
                $("#left_hand").animate({
                    left: "150",
                    top: " -38"
                }, {
                    step: function () {
                        if (parseInt($("#left_hand").css("left")) > 140) {
                            $("#left_hand").attr("class", "left_hand");
                        }
                    }
                }, 2000);
                $("#right_hand").animate({
                    right: "-64",
                    top: "-38px"
                }, {
                    step: function () {
                        if (parseInt($("#right_hand").css("right")) > -70) {
                            $("#right_hand").attr("class", "right_hand");
                        }
                    }
                }, 2000);
            });
            //失去焦点
            $("#password").blur(function () {
                $("#left_hand").attr("class", "initial_left_hand");
                $("#left_hand").attr("style", "left:100px;top:-12px;");
                $("#right_hand").attr("class", "initial_right_hand");
                $("#right_hand").attr("style", "right:-112px;top:-12px");
            });
        });

    </script>
</head>

<meta name="generator" content="mshtml 11.00.9600.17496">
</head>
<body>
    <div style="position: relative">
        <form action="${ctx}/login" method="post" id="loginForm">
            <div class="main">
                <div style="width: 165px; height: 96px; position: absolute;">
                    <div class="tou"></div>
                    <div class="initial_left_hand" id="left_hand"></div>
                    <div class="initial_right_hand" id="right_hand"></div>
                </div>
                <p style="padding: 30px 0px 10px; position: relative;">
                    <span class="u_logo"></span>
                    <input class="ipt" type="text" name="loginname" placeholder="请输入用户名或邮箱" value="">
                </p>
                <p style="position: relative;">
                    <span class="p_logo"></span>
                    <input class="ipt" id="password" name="password" type="password" placeholder="请输入密码" value="">
                </p>
                <div class="toolbar">
                    <p style="margin: 0px 35px 20px 45px;">
                        <span style="float: left;">
                            <a style="color: rgb(204, 204, 204);" href="#">忘记密码?</a>
                        </span>
                       <span style="float: right;">
                           <a class="reg" href="#">注册</a>
                           <a class="submit" href="javascript:void(0)">登录</a>
                       </span>
                    </p>
                </div>
            </div>
        </form>
    </div>
</body>
</html>

