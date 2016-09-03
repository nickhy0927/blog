<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="https://www.hy.include" prefix="hy" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"></c:set>
<hy:extends name="title">博客管理系统</hy:extends>
<hy:extends name="css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <style type="text/css">
        .navbar .navbar-header {
            margin-bottom: -4px !important;
        }
    </style>
</hy:extends>

<hy:extends name="javascript">
    <script type="text/javascript">
        $(function () {
            $(".nav-list").find('li').each(function () {
                var that = $(this);
                that.find('a').each(function () {
                    $(this).on('click', function () {
                        $(".sidebar li").removeClass('active');
                        $(this).addClass('active');
                    });
                });
            });
        });
    </script>
</hy:extends>
<hy:extends name="body">
    <div class="navbar navbar-default" id="navbar">
        <script type="text/javascript">
            try {
                ace.settings.check('navbar', 'fixed')
            } catch (e) {
            }
        </script>

        <div class="navbar-container" id="navbar-container">
            <div class="navbar-header pull-left">
                <a href="#" class="navbar-brand">
                    <small>
                        <i class="icon-leaf"></i>
                        博客系统
                    </small>
                </a><!-- /.brand -->
            </div><!-- /.navbar-header -->
            <div class="navbar-header pull-right" role="navigation">
                <ul class="nav ace-nav">
                    <li class="purple">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <i class="icon-bell-alt icon-animated-bell"></i>
                            <span class="badge badge-important">8</span>
                        </a>
                        <ul class="pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
                            <li class="dropdown-header">
                                <i class="icon-warning-sign"></i>
                                8条通知
                            </li>
                            <li>
                                <a href="#">
                                    <div class="clearfix">
                                        <span class="pull-left">
                                            <i class="btn btn-xs no-hover btn-pink icon-comment"></i>
                                                                                                       新闻评论
                                        </span>
                                        <span class="pull-right badge badge-info">+12</span>
                                    </div>
                                </a>
                            </li>

                            <li>
                                <a href="#">
                                    <i class="btn btn-xs btn-primary icon-user"></i>
                                    切换为编辑登录..
                                </a>
                            </li>

                            <li>
                                <a href="#">
                                    <div class="clearfix">
                                        <span class="pull-left">
                                            <i class="btn btn-xs no-hover btn-success icon-shopping-cart"></i>
                                            新订单
                                        </span>
                                        <span class="pull-right badge badge-success">+8</span>
                                    </div>
                                </a>
                            </li>

                            <li>
                                <a href="#">
                                    <div class="clearfix">
                                        <span class="pull-left">
                                            <i class="btn btn-xs no-hover btn-info icon-twitter"></i>
                                            粉丝
                                        </span>
                                        <span class="pull-right badge badge-info">+11</span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    查看所有通知
                                    <i class="icon-arrow-right"></i>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="light-blue">
                        <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                            <img class="nav-user-photo" src="${ctx}/static/admin/avatars/user.jpg" alt="Jason's Photo"/>
                            <span class="user-info">
                                <small>欢迎光临,</small>
                                黄园
                            </span>
                            <i class="icon-caret-down"></i>
                        </a>
                        <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                            <li>
                                <a href="#">
                                    <i class="icon-cog"></i>
                                    设置
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <i class="icon-user"></i>
                                    个人资料
                                </a>
                            </li>

                            <li class="divider"></li>

                            <li>
                                <a href="#">
                                    <i class="icon-off"></i>
                                    退出
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul><!-- /.ace-nav -->
            </div><!-- /.navbar-header -->
        </div><!-- /.container -->
    </div>

    <div class="main-container" id="main-container">
        <script type="text/javascript">
            try {
                ace.settings.check('main-container', 'fixed')
            } catch (e) {
            }
        </script>
        <div class="main-container-inner">
            <a class="menu-toggler" id="menu-toggler" href="#">
                <span class="menu-text"></span>
            </a>
            <div class="sidebar" id="sidebar">
                <ul class="nav nav-list">
                    <li class="active">
                        <a href="${ctx}/admin/adminIndex.html">
                            <i class="icon-home"></i>
                            <span class="menu-text"> 首页 </span>
                        </a>
                    </li>
                    <li>
                        <a href="#" class="dropdown-toggle">
                            <i class="icon-tag"></i>
                            <span class="menu-text"> 平台管理 </span>
                            <b class="arrow icon-angle-down"></b>
                        </a>
                        <ul class="submenu">
                            <li>
                                <a href="${ctx}/admin/platform/user/list.html" target="main_frame">
                                    <i class="icon-double-angle-right"></i>
                                    用户管理
                                </a>
                            </li>
                            <li>
                                <a href="${ctx}/admin/platform/role/list.html" target="main_frame">
                                    <i class="icon-double-angle-right"></i>
                                    角色管理
                                </a>
                            </li>
                            <li>
                                <a href="${ctx}/admin/platform/menu/list.html" target="main_frame">
                                    <i class="icon-double-angle-right"></i>
                                    菜单管理
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#" class="dropdown-toggle">
                            <i class="icon-file-alt"></i>
                            <span class="menu-text">
                                                                             内容发布                                      
                            </span>
                            <b class="arrow icon-angle-down"></b>
                        </a>
                        <ul class="submenu">
                            <li>
                                <a href="${ctx}/admin/platform/category/categoryList.html" target="main_frame">
                                    <i class="icon-double-angle-right"></i>
                                                                                    分类管理
                                </a>
                            </li>
                            <li>
                                <a href="${ctx}/poster/list.html" target="main_frame">
                                    <i class="icon-double-angle-right"></i>
                                                                                   广告管理
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#" class="dropdown-toggle">
                            <i class="icon-file-alt"></i>
                            <span class="menu-text">
                                                                         系统管理
                            </span>
                            <b class="arrow icon-angle-down"></b>
                        </a>
                        <ul class="submenu">
                            <li>
                                <a href="faq.html">
                                    <i class="icon-double-angle-right"></i>
                                    帮助
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#" class="dropdown-toggle">
                            <i class="icon-file-alt"></i>
                            <span class="menu-text">
                                                                          帖子管理
                            </span>
                            <b class="arrow icon-angle-down"></b>
                        </a>
                        <ul class="submenu">
                            <li>
                                <a href="${ctx}/tribune/user/note/list.html" target="main_frame">
                                    <i class="icon-double-angle-right"></i>
                                    发布帖子
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul><!-- /.nav-list -->
                <div class="sidebar-collapse" id="sidebar-collapse">
                    <i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
                       data-icon2="icon-double-angle-right"></i>
                </div>
                <script type="text/javascript">
                    try {
                        ace.settings.check('sidebar', 'collapsed');
                    } catch (e) {
                    }
                </script>
            </div>

            <div class="main-content">
                <div class="page-content" style="padding: 0px;">
                    <iframe id="main_frame" style="width: 100%" onLoad="reinitIframeEND();" name="main_frame"
                            frameBorder=0 scrolling=no src="${ctx}/admin/home.html"></iframe>
                </div><!-- /.page-content -->
            </div><!-- /.main-content -->

            <div class="ace-settings-container" id="ace-settings-container">
                <div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
                    <i class="icon-cog bigger-150"></i>
                </div>

                <div class="ace-settings-box" id="ace-settings-box">
                    <div>
                        <div class="pull-left">
                            <select id="skin-colorpicker" class="hide">
                                <option data-skin="default" value="#438EB9">#438EB9</option>
                                <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                                <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                                <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                            </select>
                        </div>
                        <span>&nbsp; 选择皮肤</span>
                    </div>

                    <div>
                        <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar"/>
                        <label class="lbl" for="ace-settings-navbar"> 固定导航条</label>
                    </div>

                    <div>
                        <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar"/>
                        <label class="lbl" for="ace-settings-sidebar"> 固定滑动条</label>
                    </div>

                    <div>
                        <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs"/>
                        <label class="lbl" for="ace-settings-breadcrumbs">固定面包屑</label>
                    </div>

                    <div>
                        <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl"/>
                        <label class="lbl" for="ace-settings-rtl">切换到左边</label>
                    </div>

                    <div>
                        <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container"/>
                        <label class="lbl" for="ace-settings-add-container">
                            切换窄屏
                            <b></b>
                        </label>
                    </div>
                </div>
            </div><!-- /#ace-settings-container -->
        </div><!-- /.main-container-inner -->

        <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
            <i class="icon-double-angle-up icon-only bigger-110"></i>
        </a>
    </div>
    <!-- /.main-container -->
    <!-- inline scripts related to this page -->
    <script type="text/javascript">
        jQuery(function ($) {
            $('.dialogs,.comments').slimScroll({
                height: '300px'
            });
            $('#tasks').sortable({
                opacity: 0.8,
                revert: true,
                forceHelperSize: true,
                placeholder: 'draggable-placeholder',
                forcePlaceholderSize: true,
                tolerance: 'pointer',
                stop: function (event, ui) {//just for Chrome!!!! so that dropdowns on items don't appear below other items after being moved
                    $(ui.item).css('z-index', 'auto');
                }
            });
            $('#tasks').disableSelection();
            $('#tasks input:checkbox').removeAttr('checked').on('click', function () {
                if (this.checked) $(this).closest('li').addClass('selected');
                else $(this).closest('li').removeClass('selected');
            });
            jQuery(function ($) {
                var width = $('.page-content').width();
                $("#main_frame").width(width);
            });

            $(".sidebar-collapse").click(function () {
                change();
            });

            $(window).resize(function () {
                if (document.body.style.overflow != "hidden" && document.body.scroll != "no" &&
                        document.body.scrollHeight > document.body.offsetHeight) {
                } else {
                    change();
                }
            });
        });

        function change() {
            var width = $('.page-content').width();
            $("#main_frame").width(width);
        }
        function reinitIframe() {
            var iFrame = document.getElementById("main_frame");
            try {
                var bHeight = iFrame.contentWindow.document.body.scrollHeight;
                var dHeight = iFrame.contentWindow.document.documentElement.scrollHeight;
                var height = Math.max(bHeight, dHeight);
                iFrame.height = height;
            } catch (ex) {
            }
        }

        var timer1 = window.setInterval("reinitIframe()", 500); //定时开始
        function reinitIframeEND() {
            var iFrame = document.getElementById("main_frame");
            try {
                var bHeight = iFrame.contentWindow.document.body.scrollHeight;
                var dHeight = iFrame.contentWindow.document.documentElement.scrollHeight;
                var height = Math.max(bHeight, dHeight);
                iFrame.height = height < window.innerHeight ? (window.innerHeight - 70) : height;
                if (document.body.style.overflow != "hidden" && document.body.scroll != "no" &&
                        document.body.scrollHeight > document.body.offsetHeight) {
                } else {
                    change();
                }
            } catch (ex) {
            }
            // 停止定时
            window.clearInterval(timer1);
        }
    </script>
</hy:extends>
<jsp:include page="/base/admin.jsp"/>
