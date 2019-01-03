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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>注册页面</title>
    <style>
        label{
            display: inline-block;
            width: 5em;
        }
    </style>
    <script src="../../js/jquery-1.11.3.js"></script>
    <script>
        function check(){
            var account = $("#account").val();
            $.ajax({
                url:"/appController/checkUsername.html",
                type:"post",
                dataType:"JSON",
                async:true,
                data:{account:account},
                success:function(result){
                    if(result.type=="true"){
                        $("#s1").text("×");
                        $("#account").focus();
                    }
                    else if(result.type=="false"){
                        $("#s1").text("✔");
                    }
                }
            });
        }

        function checkpwd(){
            var pwd = $("#password").val();
            var ckpwd = $("#ckpassword").val();
            if(pwd==ckpwd){
                $("#s2").text("✔");
            }
            else {
                $("#s2").text("×");
            }
        }

        $(document).ready(function(){
            $("#account").blur(function(){
                var account = $("#account");
                var content = account.val();
                var zz = /^[a-zA-Z][a-zA-Z0-9]{3,15}$/;
                if(zz.test(content)){
                    $("#s3").text("");
                }
                else{
                    $("#s3").text("×");
                    $("#account").focus();
                }
            });

            $("#password").blur(function(){
                var password = $("#password");
                var content = password.val();
                var zz = /^[a-zA-Z0-9]{4,15}$/;
                if(zz.test(content)){
                    $("#s4").text("✔");

                }
                else{
                    $("#s4").text("×");
                    $("#password").focus();
                }
            });

            $("#name").blur(function(){
                $("#s5").text("✔");
            });
        });
    </script>
    <link rel="stylesheet" href="../../css/login.css">
</head>
<body style="background:url(../../image/2.jpg); background-size:cover; background-repeat:no-repeat">
<header>
    <div class="header-line"></div>
</header>
<div class="content">
    <img class="content-logo" src="../image/1.png" alt="logo">
    <h1 class="content-title">创建账户</h1>
    <div class="content-form">
        <form method="post" action="/appController/doRegister.html">
            <div>
                <input class="user" type="text" name="account" id="account" placeholder="请输入用户名" onblur="check()">
                <span id="s1" style="color:#FF0000"></span>
                <span id="s3" style="color:#FF0000"></span>
            </div>
            <div>
                <input class="password" type="password" name="password" id="password" placeholder="请输入密码" >
                <span id="s4" style="color:#FF0000"></span>
            </div>
            <div>
                <input class="password" type="password" id="ckpassword" name="ckpassword" placeholder="确认密码" onblur="checkpwd()"/>
                <span id="s2" style="color:#FF0000"></span>
            </div>
            <div>
                <input class="user" type="text" id="name" placeholder="请输入姓名" name="name"/>
                <span id="s5" style="color:#FF0000"></span>
            </div>
            <div>
                <input class="content-form-signup" type="submit" value="创建账户">
            </div>
        </form>
    </div>
    <div class="content-login-description">已经拥有账户？</div>
    <div><a class="content-login-link" href="log_in.html">登录</a></div>
</div>
</body>
</html>
