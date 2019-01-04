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
                        <li style="margin-left:450px"><a href="/appController/userInfo?page=运单查询">用户信息</a></li>
                        <li ><a href="/appController/loginOut">注销</a></li>
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
</body>
</html>
