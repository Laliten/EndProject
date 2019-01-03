<%--
  Created by IntelliJ IDEA.
  User: YangDeJian
  Date: 2018-11-28
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
    <link rel="stylesheet" href="../../css/login/login.css">
</head>
<style>
    label{
        display: inline-block;
        width: 3em;
    }
</style>
<script>
    $(function(){
        $("#account").tooltip({
            show:{
                effect:"slideDown",
                delay: 250
            }
        });
        $("#password").tooltip({
            hide:{
                effect: "explode",
                delay: 250
            }
        });
    });
</script>
<body style="background:url(../../image/login/2.jpg); background-size:cover; background-repeat:no-repeat">
<header>
    <div class="header-line"></div>
</header>
<div class="content">
    <img class="content-logo" src="../../image/login/1.png" alt="logo">
    <h1 class="content-title">登录</h1>
    <div class="content-form">
        <form method="post" action="/appController/doLogin">
            <div>
                <input class="user" type="text" name="account" id="account" placeholder="请输入用户名" title="请输入用户名">
            </div>
            <div>
                <input class="password" type="password" name="password" id="password" placeholder="请输入密码" title="请输入密码">
            </div>
            <div>
                <font size="+1" color="#999999">
                    <input type="radio" name="type" id="user" value="0" checked/><label for="user">用户</label>
                    <input type="radio" name="type" id="admin" value="1"/><label for="admin">管理员</label>
                </font>
            </div>
            <div>
                <input class="content-form-signup" type="submit" value="登录">
            </div>
        </form>
    </div>
    <div class="content-login-description">没有账户？</div>
    <div><a class="content-login-link" href="/appController/register">注册</a></div>
</div>
</body>
</html>
