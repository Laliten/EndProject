<%--
  Created by IntelliJ IDEA.
  User: YangDeJian
  Date: 2018-11-28
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>附近驿站</title>
    <script src="../../jslib/jquery-1.8.3.js"></script>
    <link href="../../css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="../../css/costEstimate/costEsitmate.css">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style type="text/css">
        *{ margin:0; padding:0}
            html{height:100%}
            body{height:100%;margin:0px;padding:0px}
            #container{
            height:100%;
            z-index: 1;
        }
        body{
            text-align: center;
            background:url("../../image/city_banner.png")top center no-repeat;
            background-size:cover;
        }
        input{
            height:30px;
            border-radius:18px;
            font-size: 16px;
        }
        .h1{
            width:70%;
            float:left;
            margin-top:80px;
        }
        .h2{
            width:30%;
            float:right;
            margin-top:80px;
        }
        .box{
            margin:0 auto;
        }

        li{
            margin-left: 10px;
        }
    </style>
    <script src="http://api.map.baidu.com/api?v=1.4" type="text/javascript"></script>
</head>
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
                <li style="float: right">
                    <a href="#helpWord" data-toggle="modal"><button style="width: 20px;height: 20px;border-radius: 50%;background-color:grey;border: grey;font-family: 华文细黑;color: black" >?</button></a>
                </li>
                <li style="float: right"><span class="navbar-brand" style="font-size: 14px" id="history">历史记录</span>
                </li>
                <li style="float: right"><a href="/appController/loginOut">注销</a></li>
                <li style="float: right" class="dropdown" id="profile-messages"></a>
                    <a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle">
                        <i class="icon icon-user"></i>&nbsp;
                        <span class="text">用户信息</span>&nbsp;
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="/appController/userInfo?pageName=nearby"><i class="icon-user"></i>个人资料</a></li>
                        <li class="divider"></li>
                        <li><a href="#passwordModel" data-toggle="modal"><i class="icon-check"></i> 修改密码</a></li>
                    </ul>
                </li>

            </ul>
        </div>
    </div>
</nav>

<div style="width:680px;height:130px;" class="box">
    <div class="h1">
        <input type="search" name="place" id="place" placeholder="请输入详细地址（例如：浙江科技学院" style="width:300px">
    </div>
    <div class="h2">
        <input type="button" onclick="setCity()" value="查找" style="height: 28px;width:80px;background-color:#0099FF;color:#FFFFFF" />
    </div>
</div>

<div id="container" style="height:590px;">
    <script type="text/javascript">


        var map = new BMap.Map("container");        //在container容器中创建一个地图,参数container为div的id属性;
        var point = new BMap.Point(120.2,30.25);    //创建点坐标
        map.centerAndZoom(point, 14);                //初始化地图，设置中心点坐标和地图级别
        map.enableScrollWheelZoom();                //激活滚轮调整大小功能
        map.addControl(new BMap.NavigationControl());    //添加控件：缩放地图的控件，默认在左上角；
        map.addControl(new BMap.MapTypeControl());        //添加控件：地图类型控件，默认在右上方；
        map.addControl(new BMap.ScaleControl());        //添加控件：地图显示比例的控件，默认在左下方；
        map.addControl(new BMap.OverviewMapControl());  //添加控件：地图的缩略图的控件，默认在右下方； TrafficControl
        var search = new BMap.LocalSearch("中国", {
            onSearchComplete: function(result){
                if (search.getStatus() == BMAP_STATUS_SUCCESS){
                    var res = result.getPoi(0);
                    var point = res.point;
                    map.centerAndZoom(point, 11);
                }
            },renderOptions: {  //结果呈现设置，
                map: map,
                autoViewport: true,
                selectFirstResult: true
            } ,onInfoHtmlSet:function(poi,html){//标注气泡内容创建后的回调函数，有了这个，可以简单的改一下返回的html内容了。
                // alert(html.innerHTML)
            }//这一段可以不要，只不过是为学习更深层次应用而加入的。
        });
        function setCity(){
            var place = document.getElementById("place").value;
            search.search(place+"菜鸟驿站");
            $.ajax({
                url:"/postController/addhistory",
                type:"post",
                dataType:"JSON",
                async:true,
                data:{
                    place:place,
                    userId:${sessionScope.sessionInfo.doneUser.id}
                },
                success:function(result){
                }
            });
        }
        $(function () {
            search.search("浙江科技学院菜鸟驿站");
        });
    </script>
</div>

<div  name="right" class="right" >
    <br>
    <br>
    <br>
    <div  class="list" style="height: 100%">
        <iframe src="/postController/findhistory?userId=${sessionScope.sessionInfo.doneUser.id}" style="height: 100%"></iframe>
    </div>
</div>

<script>
    $("#history").click(function () {
        $(".right").animate({
            height: "toggle"
        });
    });
    document.onclick =
        function (e) {
            var X = e.screenX;
            var Y = e.screenY;
            if (X < 1219 && Y < 821 && Y > 142) {
                $(".right").animate({
                    height: "hide"
                });
            }
        }
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
                用户可以通过在输入框输入地址来查询附近的驿站信息。<br><br>
                用户可以点击历史记录来查看自己的查询历史，<br><br>
                并可以点击历史信息中的地址来显示附近驿站的信息<br><br>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

</body>
</html>
