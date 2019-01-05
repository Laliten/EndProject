<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用户信息</title>
    <style type="text/css">
        body{
            background:url("../../image/city_banner.png")top center no-repeat;
            background-size:cover;
        }
        .Content-Main{
            float: none;
            margin-top: 100px;
            max-width: 500px;
            margin: 0 auto;
            border: 1px solid grey;
            border-radius: 5px;
            -moz-border-radius: 5px;
            -webkit-border-radius: 5px;
            font: 12px "Helvetica Neue", Helvetica, Arial, sans-serif;
            text-shadow: 1px 1px 1px #444;
            color: #D3D3D3;
            background: #555;
        }
        .Content-Main h1{
            padding: 8px 0px 40px 10px;
            display: block;
            border-bottom: 1px solid #444;
        }
        .text1{
            margin-left: 3px;
        }
        .Content-Main label{
            margin: 0px 0px 5px;
            display: block;
        }
        .fileInputContainer{
            height: 99px;
            width: 99px;
            margin: 0 auto ;
            border: none;
            background: url("../../image/city_banner.png");
            overflow: hidden;

        }

        .Content-Main label>span{
            width: 20%;
            float: left;
            text-align: right;
            padding-right: 10px;
            margin-top: 10px;
            font-weight: bold;
        }
        .Main-sex{
            padding-right: 13px;
            padding-bottom: 13px;
            font-weight: bold;
            line-height: 4px;
        }
        .Main-sex input[type=checkbox]{
            margin-top:6px;
            vertical-align:middle;
        }
        .Content-Main input[type="text"],.Content-Main input[type="email"],.Content-Main textarea{
            height: 25px;
            width: 70%;
            line-height: 15px;
            padding: 5px 0px 5px 5px;
            margin-bottom: 16px;
            margin-right: 6px;
            margin-top: 2px;
            border: none;
            border-radius:2px;
            -webkit-border-radius:2px;
            -moz-border-radius:2px;
            outline: 0 none;
            background:  #DFDFDF;
            color: #525252;
        }
        .Content-Main textarea{
            height: 100px;
            width: 70%;
            padding: 5px 0px 0px 5px;
        }
        .Content-Main .button{
            padding: 8px 24px 8px 24px;
            margin-bottom: 8px;
            border: none;
            border-radius: 4px;
            -moz-border-radius: 4px;
            -webkit-border-radius: 4px;
            font-weight: bold;
            text-shadow: 1px 1px 1px #FFE477;
            box-shadow: 1px 1px 1px  #3D3D3D;
            -moz-box-shadow: 1px 1px 1px  #3D3D3D;
            -webkit-box-shadow: 1px 1px 1px  #3D3D3D;
            color: #585858;
            background: #f6ff0a;
        }
        .Content-Main .button:hover{
            color:  #333;
            background-color:  #EBEBEB ;
        }
    </style>
</head>
<script src="../../jslib/jquery-1.8.3.js"></script>
<link href="../../css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
    function check(){
        var account = $("#account").val();
        $.ajax({
            url:"/appController/checkUsername",
            type:"post",
            dataType:"JSON",
            async:true,
            data:{account:account},
            success:function(result){
                if(result.type=="true"){
                    $("#submit").attr("disabled",true);
                    $("#submit").css({'background-color':'#A5A7A9'});
                    $("#account").focus();
                }
                else {
                    $("#submit").attr("disabled",false);
                    $("#submit").css({'background-color':'#f6ff0a'});
                }
            }
        });
    }
    $(function () {
        var type = $("#type");
        if(type.val()=="0"){
            type.val("普通用户");
        }
    });
</script>

<body>
<nav class="navbar-inverse navbar-fixed-top" role="navigation" style="height: 60px;padding-top: 5px">
    <div class="container" >
        <div>
            <a href="#" class="navbar-brand" style="font-size: 30px;margin-left: -10px">logo</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav" style="width: 92%">
                <li style="margin-left: 10px"><a href="/appController/userHome">主界面</a></li>
                <li ><a href="/appController/costEstimate">运费估计</a></li>
                <li ><a href="/appController/nearby">附件驿站</a></li>
                <li ><a href="/appController/wayBillQuery">运单查询</a></li>
                </li>
                <li style="float: right"><a href="/appController/loginOut">注销</a></li>
                <li style="float: right" class="dropdown" id="profile-messages"></a>
                    <a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle">
                        <i class="icon icon-user"></i>&nbsp;
                        <span class="text">用户信息</span>&nbsp;
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="/appController/userInfo?pageName=userInfo"><i class="icon-user"></i>个人资料</a></li>
                        <li class="divider"></li>
                        <li><a href="#passwordModel" data-toggle="modal"><i class="icon-check"></i> 修改密码</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<br><br><br><br>

<input type="hidden" name="pageName" id="pageName" value="${pageName}" form="form1">
<form action="/personController/updateUserInfo" method="post" name="form1" id="form1"></form>
<div class="Content-Main">
    <h1>个人信息</h1>
    <span class="text1">请在文本框中完善您的个人信息：</span>
    <p>
    <div class="fileInputContainer">
        <%--<input class="fileInput" id="" type="file" name="">--%>
    </div>
    <br><br>

    <label>
        <span>编号:</span>
        <input type="text" name="id" id="id" value="${sessionScope.sessionInfo.doneUser.id}" form="form1" readonly="readonly">
    </label>

    <label>
        <span>身份:</span>
        <input type="text" name="type" id="type" value="${sessionScope.sessionInfo.doneUser.type}" readonly="readonly">
    </label>

    <label>
        <span>姓名:</span>
        <input type="text"  name="name" id="name" value="${sessionScope.sessionInfo.doneUser.name}" form="form1">
    </label>

    <label>
        <span>用户名:</span>
        <input type="text" name="account" id="account" value="${sessionScope.sessionInfo.doneUser.account}" onblur="check()" form="form1">
    </label>
    <input type="submit" id="submit" class="button" value="提交" form="form1">
</div>
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
</body>
</html>
