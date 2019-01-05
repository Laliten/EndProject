<%--
  Created by IntelliJ IDEA.
  User: YangDeJian
  Date: 2018-11-28
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../../css/wayBillQuery/KDNWidget.css">
    <script src="../../js/wayBIllQuery/KDNWidget.js"></script>
    <script src="../../js/bootstrap.js"></script>
    <script src="../../js/jquery-1.11.3.js"></script>
    <link rel="stylesheet" href="../../css/bootstrap.css"/>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body background="../../image/city_banner.png" style="background-repeat:no-repeat;background-size:100% 100%">
<div id="container" >
    <div id="header">
        <nav class="navbar-inverse navbar-fixed-top" role="navigation" style="height: 60px;padding-top: 5px">
            <div class="container" >
                <div class="navbar-header" >
                    <a href="#" class="navbar-brand" style="font-size: 30px;margin-left: -10px">logo</a>
                </div>
                <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li style="margin-left: 10px"><a href="/appController/userHome">主界面</a></li>
                        <li ><a href="/appController/costEstimate">运费估计</a></li>
                        <li ><a href="/appController/nearby">附件驿站</a></li>
                        <li ><a href="/appController/wayBillQuery">运单查询</a></li>
                        <li style="margin-left: 450px" class="dropdown" id="profile-messages"></a>
                            <a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle">
                                <i class="icon icon-user"></i>&nbsp;
                                <span class="text">用户信息</span>&nbsp;
                                <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="/appController/userInfo?pageName=wayBillQuery"><i class="icon-user"></i>个人资料</a></li>
                                <li class="divider"></li>
                                <li><a href="#passwordModel" data-toggle="modal"><i class="icon-check"></i> 修改密码</a></li>
                            </ul>
                        </li>
                        <li ><a href="/appController/loginOut">注销</a></li>
                        <li>
                            <a href="#helpWord" data-toggle="modal"><button style="width: 20px;height: 20px;border-radius: 50%;background-color:grey;border: grey;font-family: 华文细黑;color: black" >?</button></a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
    <div style="margin: 100px auto; text-align: center;" >
        <div id="search">
            正在加载中，请稍后
        </div>
    </div>
</div>

<script>
    $(function () {
        KDNWidget.run({
            "serviceType": "D",
            "container": "search",
        })
    });
</script>
<!--修改密码的模态-->
<div class="modal fade" id="passwordModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改密码</h4>
            </div>
            <div class="modal-body" style="text-align: center;">

                <form class="bs-example bs-example-form" role="form" id="passwordForm" style="font-size: 15px">
                    <div class="input-group">
                        <span class="input-group-addon">原密码&nbsp;</span>
                        <input name="oldPwd" type="text" class="form-control" placeholder="你的原密码" autofocus required pattern="^{a-zA-Z0-9}{1,7}$">
                    </div>
                    <br>
                    <div class="input-group">
                        <span class="input-group-addon">新密码&nbsp;</span>
                        <input name="newPwd" type="text" class="form-control" placeholder="请输入密码" required pattern="^{a-zA-Z}\w{1,7}$">
                    </div>
                    <br>
                    <div class="input-group">
                        <span class="input-group-addon">密码确认</span>
                        <input name="confirmPwd" type="text" class="form-control" placeholder="确认密码" required>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="passwordSubmitButton" type="button" class="btn btn-primary" form="passwordForm">提交更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!--修改密码的模态结束-->

<div class="modal fade" id="helpWord" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">帮助文档</h4>
            </div>
            <div class="modal-body" style="text-align: center;">
                aaaa
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

</body>
</html>
