<%--
  Created by IntelliJ IDEA.
  User: xqh
  Date: 2018/12/24
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>后台管理模板</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="../../css/mcss/bootstrap.min.css" />
    <link rel="stylesheet" href="../../css/mcss/bootstrap-responsive.min.css" />
    <link rel="stylesheet" href="../../css/mcss/matrix-style.css" />
    <link rel="stylesheet" href="../../css/mcss/matrix-media.css" />
    <link rel="stylesheet" href="../../css/font-awesome/css/font-awesome.css" />

    <script type="text/javascript" src="../../js/mjs/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/table.min.js"></script>
    <script type="text/javascript" src="../../js/bootstrap-table.js"></script>
    <script type="text/javascript" src="../../js/mjs/bootstrap-table-zh-CN.js"></script>
    <script type="text/javascript">
        $("#manager").click(function () {
            $("#manager_manager").attr("src","/personController/manage_manager");
        })

        $("#user").click(function () {
            $("#manager_manager").attr("src","/personController/user");
        })
    </script>
</head>
<body>
<!--Header-part-->
<div id="header">
    <h1><a href="#">快递帮系统平台</a></h1>
</div>
<!--close-Header-part-->

<!--top-Header-menu-->
<div id="user-nav" class="navbar navbar-inverse">
    <ul class="nav">
        <li  class="dropdown" id="profile-messages" >
            <a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle">
                <i class="icon icon-user"></i>&nbsp;
                <span class="text">欢迎你，${sessionScope.sessionInfo.doneUser.id}</span>&nbsp;
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="#"><i class="icon-user"></i> 个人资料</a></li>
                <li class="divider"></li>
                <li><a href="#"><i class="icon-check"></i> 我的任务</a></li>
            </ul>
        </li>
        <li class="dropdown" id="menu-messages">
            <a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle">
                <i class="icon icon-paste"></i>&nbsp;
                <span class="text">控制面板</span>&nbsp;
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a class="sAdd" title="" href="#"><i class="icon icon-pencil"></i>修改密码</a></li>
            </ul>
        </li>
        <li class="dropdown" id="cancel">
            <a href="#" data-toggle="dropdown" data-target="#cancel-messages" class="dropdown-toggle">
                <i class="icon icon-cloud"></i>&nbsp;
                <span class="text">注销</span>&nbsp;
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a class="sAdd" title="" href="#"><i class="icon icon-refresh"></i>重新登录</a></li>
                <li><a href="#"><i class="icon icon-share-alt"></i>退出系统</a></li>
            </ul>
        </li>
    </ul>
</div>
<!--close-top-Header-menu-->

<!--start-top-serch-->
<div id="search">
    <input type="text" placeholder="搜索..."/>
    <button type="submit" class="tip-bottom" title="Search"><i class="icon-search icon-white"></i></button>
</div>
<!--close-top-serch-->

<!--sidebar-menu-->
<div id="sidebar" style="OVERFLOW-Y: auto; OVERFLOW-X:hidden;">
    <ul>
        <li class="submenu">
            <a href="#">
                <i class="icon icon-table"></i>
                <span>用户管理</span>
            </a>
            <ul>
                <li id="manager"><a class="menu_a" ><i class="icon icon-user-md"></i>管理员</a></li>
                <li id="user"><a class="menu_a" ><i class="icon icon-user"></i>用户</a></li>
            </ul>
        </li>
        <li class="submenu">
            <a href="#">
                <i class="icon icon-th"></i>
                <span>计价表管理</span>
            </a>
            <ul>
                <li><a class="menu_a" ><i class="icon icon-columns"></i>韵达</a></li>
                <li><a class="menu_a" ><i class="icon icon-columns"></i>申通</a></li>
                <li><a class="menu_a" ><i class="icon icon-columns"></i>顺丰</a></li>
                <li><a class="menu_a" ><i class="icon icon-columns"></i>圆通</a></li>

            </ul>
        </li>
    </ul>
</div>
<!--sidebar-menu-->

<!--main-container-part-->
<div id="content">
    <!--breadcrumbs-->
    <div id="content-header">
        <div id="breadcrumb"> <a href="" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
    </div>
    <!--End-breadcrumbs-->
    <div style="margin: 0px 0px 0px 50px">

        <iframe src="manager_manager" width="95%" height="550px" name="iframeContainer" id="iframeContainer">
        </iframe>

    </div>
</div>

<!--end-main-container-part-->

<script src="../../js/mjs/excanvas.min.js"></script>
<script src="../../js/mjs/jquery.ui.custom.js"></script>
<script src="../../js/mjs/bootstrap.min.js"></script>
<script src="../../js/mjs/nicescroll/jquery.nicescroll.min.js"></script>
<script src="../../js/mjs/matrix.js"></script>
<script type="text/javascript">
    //初始化相关元素高度
    function init(){
        $("body").height($(window).height()-80);
        $("#iframe-main").height($(window).height()-90);
        $("#sidebar").height($(window).height()-50);
    }
    $(function(){
        init();
        $(window).resize(function(){
            init();
        });

        $(".menu_a").click(function () {
            var src;
            if ($(this).text() == "用户" || $(this).text() == "管理员"){
                src = "/appController/manager_manager";
                if ($(this).text() == "用户"){
                    src += "?requestType=user";
                } else {
                    src += "?requestType=admin";
                }
            } else {
                src = "/appController/manager_meter";
                switch ($(this).text()) {
                    case "圆通": src += "?companyCode=YTO";break;
                    case "申通": src += "?companyCode=STO";break;
                    case "韵达": src += "?companyCode=YD";break;
                    case "中通": src += "?companyCode=ZTO";break;
                    case "顺丰": src += "?companyCode=SF";break;
                    default:src += "?companyCode=none";break;
                }
            }
            $("#iframeContainer").attr("src", src);
        });
    });
    function goPage (newURL) {

        if (newURL != "") {

            if (newURL == "-" ) {
                resetMenu();
            }
            else {
                document.location.href = newURL;
            }
        }
    }
    function resetMenu() {
        document.gomenu.selector.selectedIndex = 2;
    }

</script>
<!-- 模态框 -->
<div class="modal fade" id="revise_modal" style="margin-top: 100px">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">

            <!-- 模态框头部 -->
            <div class="modal-header">
                <h4 class="modal-title">修改信息</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- 模态框主体 -->
            <div class="modal-body">
                <span style="font-size: 15px">请输入姓名：</span><input type="text" style="width: 20%">
            </div>

            <!-- 模态框底部 -->
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
            </div>

        </div>
    </div>
</div>

</body>
</html>
