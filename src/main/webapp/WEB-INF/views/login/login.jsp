<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="hy" uri="https://www.hy.include" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>
<hy:extends name="title">用户登录</hy:extends>
<hy:extends name="css">
    <link rel="stylesheet" href="${ctx}/static/login/css/supersized.css">
    <link rel="stylesheet" href="${ctx}/static/login/css/login.css">
    <style type="text/css">
        body {
            background: url('${ctx}/static/login/images/backgrounds/0.jpg') !important;
            -webkit-background-size: 100% 100% !important;
            background-size: 100% 100% !important;
            background-repeat: no-repeat !important;
        }

        .form-group label {
            color: #000000;
            font-size: 14px;
        }

        .login_box .form-group label.t {
            width: 120px;
            text-align: right;
            cursor: pointer;
            font-size: 16px;
            height: 25px;
            line-height: 30px;
        }
    </style>
</hy:extends>
<hy:extends name="javascript">
    <script type="text/javascript">
        $(function () {
        });
    </script>
</hy:extends>
<hy:extends name="body">
    <div class="page-container">
        <div class="main_box">
            <div class="login_box">
                <div class="login_form">
                    <form action="${ctx}/outLink/admin/adminLogin.html" id="login_form" method="post">
                        <div class="form-group">
                            <label for="username" class="t">邮　箱：</label>
                            <input id="username" value="" name="username" type="text" class="form-control x319 in"
                                   autocomplete="off">
                        </div>
                        <div class="form-group">
                            <label for="password" class="t">密　码：</label>
                            <input id="password" value="" name="password" type="password"
                                   class="password form-control x319 in">
                        </div>
                        <div class="form-group">
                            <label for="j_captcha" class="t">验证码：</label>
                            <input id="j_captcha" name="j_captcha" type="text" class="form-control x164 in">
                            <img id="captcha_img" alt="点击更换" title="点击更换" src="${ctx}/static/login/images/captcha.jpeg"
                                 class="m">
                        </div>
                        <div class="form-group">
                            <label class="t"></label>
                            <label for="j_remember" class="m">
                                <input id="j_remember" type="checkbox" value="true">&nbsp;记住登陆账号!</label>
                        </div>
                        <hr style="border: 1px dashed #ffffff">
                        <div class="form-group">
                            <label class="t"></label>　　　
                            <button type="submit" id="submit_btn"
                                    class="btn btn-primary btn-lg">&nbsp;登&nbsp;录&nbsp
                            </button>
                            <input type="reset" value="&nbsp;注&nbsp;册&nbsp;" class="btn btn-default btn-lg">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</hy:extends>
<jsp:include page="/base/admin.jsp"/>