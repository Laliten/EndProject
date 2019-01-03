<%--
  Created by IntelliJ IDEA.
  User: YangDeJian
  Date: 2018-11-28
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>附近驿站</title>
    <link rel="stylesheet" href="../../css/bootstrap.css"/>
    <style type="text/css">
        *{ margin:0; padding:0}
            html{height:100%}
            body{height:100%;margin:0px;padding:0px}
            #container{
            height:100%;
            z-index: 1;
        }
        body{text-align: center}
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
    <script src="../../js/bootstrap.js"></script>
    <script src="http://api.map.baidu.com/api?v=1.4" type="text/javascript"></script>
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
<div style="width:680px;height:150px;" class="box">
    <div class="h1">
        <input type="search"  id="cityName" placeholder="请输入详细地址（例如：浙江科技学院）" value="杭州" onfocus='if(this.value=="杭州"){this.value="";};' style="width:300px">
    </div>
    <div class="h2">
        <input type="button" onclick="setCity()" value="查找" style="height: 28px;width:80px;background-color:#0099FF;color:#FFFFFF" />
    </div>
</div>
<div id="container" style="height:550px;">
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
            search.search(document.getElementById("cityName").value+"菜鸟驿站");
        }
    </script>
</div>
</body>
</html>
