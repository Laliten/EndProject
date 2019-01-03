<%--
  Created by IntelliJ IDEA.
  User: YangDeJian
  Date: 2018-11-28
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <title>主界面</title>
    <link rel="stylesheet" href="../../css/bootstrap.css"/>
    <style>
        body{
            padding-top: 50px;
        }
        .starter{
            padding: 40px 15px;
            text-align: center;
        }
        li{
            margin-left: 10px;
        }
    </style>
    <script src="../../js/bootstrap.js"></script>
</head>
<body>
<nav class="navbar-inverse navbar-fixed-top" role="navigation" style="height: 60px;padding-top: 5px">
    <div class="container" >
        <div class="navbar-header" >
            <a href="#" class="navbar-brand" style="font-size: 30px;margin-left: -10px">logo</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li style="margin-left: 10px"><a href="#">主界面</a></li>
                <li ><a href="/appController/costEstimate">运费估计</a></li>
                <li ><a href="/appController/nearby">附件驿站</a></li>
                <li ><a href="/appController/wayBillQuery">运单查询</a></li>
                <li style="margin-left:450px"><a href="#">用户信息</a></li>
                <li ><a href="#">登录</a></li>
                <li ><a href="/appController/loginOut">注销</a></li>
            </ul>
        </div>
    </div>
</nav>
<div style="float: right;width: 300px;height: 100px;background-color: white;margin:0px -20px 0px 20px">

</div>
<div style="margin-left: 0px">
    <iframe src="userHome_loopPlay.jsp"  width="1350px" height="550px" name="flower"
            scrolling="No"  noresize="noresize" frameborder="0" id="lunbo">
    </iframe>
</div>
<iframe src="userHome_intro.jsp" width="1350px" height="550px" name="topFrame"
        scrolling="No"  noresize="noresize" frameborder="0" id="topFrame">
</iframe>
</body>
</html>
