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
            background:url("../../image/userMessage/usermessage.jpg")top center no-repeat;
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
            margin: 20px 20px 20px 20px ;
            border: none;
            background: url("../../image/24.jpg");
            overflow: hidden;
            position: relative;
        }
        .fileInput{
            height: 106px;
            border: none;
            font-size: 300px;
            opacity: 0;
            filter:alpha(opacity=0);
            cursor: pointer;
            position: absolute;
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
<script>
    function commit() {
        document.form1.submit();
        <%--$.ajax({--%>
            <%--url:"/personController/updateUserInfo",--%>
            <%--type:"post",--%>
            <%--async:true,--%>
            <%--data:{--%>
                <%--name:name,--%>
                <%--id:id,--%>
                <%--page:${page}--%>
            <%--},--%>
            <%--success:function(result){--%>
                <%--alert(result.msg);--%>
            <%--}--%>
        <%--});--%>
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
                <li style="float: right"><a href="/appController/userInfo">用户信息</a></li>

            </ul>
        </div>
    </div>
</nav>
<br><br><br><br>
<input type="hidden" name="id" id="id" value="${sessionScope.sessionInfo.doneUser.id}" form="form1">
<input type="hidden" name="page" id="page" value="${page}" form="form1">
<form action="/personController/updateUserInfo" method="post" name="form1" id="form1"></form>
<div class="Content-Main">
    <h1>个人信息</h1>
    <span class="text1">请在文本框中完善您的个人信息：</span>
    <p>
    <div class="fileInputContainer">
        <input class="fileInput" id="" type="file" name="">
    </div>
    <label>
        <span>姓名:</span>
        <input type="text"  name="name" id="name" value="${sessionScope.sessionInfo.doneUser.name}" form="form1">
    </label>
    <%--<label class="Main-sex">--%>
        <%--<span>性别:</span>--%>
        <%--<input type="checkbox" class="man">男--%>
        <%--<input type="checkbox" class="women">女--%>
    <%--</label>--%>
    <label>
        <span>身份:</span>
        <input type="text" name="type" id="type" value="${sessionScope.sessionInfo.doneUser.type}" readonly="readonly" form="form1">
    </label>
    <%--<label>--%>
        <%--<span>Your phone:</span>--%>
        <%--<input type="text" name="phone" placeholder="Please input 11 number">--%>
    <%--</label>--%>
    <%--<label>--%>
        <%--<span>Message:</span>--%>
        <%--<textarea id="message" name="message" placeholder="Your message to us"></textarea>--%>
    <%--</label>--%>
    <input type="button" class="button" value="提交" onclick="commit()">
</div>
</body>
</html>
