<%--
  Created by IntelliJ IDEA.
  User: YangDeJian
  Date: 2018-11-28
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8"/>
    <title>运费估计</title>
    <script src="../../jslib/jquery-1.8.3.js"></script>
    <script src="../../jslib/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../../jslib/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" href="../../css/costEstimate/plugins/kuCity.css">
    <link href="../../css/bootstrap.css" rel="stylesheet">

    <script type="text/javascript" src="../../js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="../../css/costEstimate/costEsitmate.css">

    <link rel="stylesheet" href="../../css/costEstimate/plugins/bc.grid-1.0.0.min.css">

    <script type="text/javascript" src="../../Bootstrap/bootstrap-table.js"></script>

    <script type="text/javascript" src="../../Bootstrap/locale/bootstrap-table-zh-CN.js"></script>

    <link rel="stylesheet" href="../../Bootstrap/Content/bootstrap-table.css">

    <style>
        div .shadow{
            text-align: center;
        }
        div{
            min-width: 20%  ;
        }
        div[name=right]{
            min-width: 50px;
        }
        div[name=centerDiv]{
            float: none;
            margin: 0 auto;
        }
        .row{
            margin: 0 auto;
        }
        #container{
            text-align: center;
        }
    </style>
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
                        <li><a href="/appController/userInfo?pageName=costEstimate"><i class="icon-user"></i>个人资料</a></li>
                        <li class="divider"></li>
                        <li><a href="#passwordModel" data-toggle="modal"><i class="icon-check"></i> 修改密码</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="zm-banner text-center">
    <div class="zm-banner-bac">
        <h1 style="color: #fff; padding-top: 30px">快递帮--为你选出性价比最高的公司</h1>
        <input type="text" class="form-control form-control-inline" style="width: 300px;margin-left: 0px;z-index: 99"
               placeholder="搜索。。。">
        <button class="btn btn-primary">搜索</button>
    </div>
</div>
<div class="zm-main-container">
    <div id="container" style="text-align: center">
        <div class="row">
            <div class="zm-slogan zm-slogan-container">
                快递帮，专业服务100年
            </div>
        </div>
        <div class="row">
            <div name="centerDiv" class="col-sm-9">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="shadow" style="padding-top: 6%;padding-bottom: 6%">
                            <form name="filterForm">
                                <span class="intro">起点</span>
                                <input type="text" name="start" class="search form-control form-control-inline" id="start">
                                <span class="intro">终点</span>
                                <input type="text" name="destination" class="search form-control form-control-inline" id="destination">
                                <span class="intro">重量</span>
                                <input type="text" name="weight" value="12" class="form-control form-control-inline"
                                       id="weight">
                                <button class="btn btn-default" type="button">kg</button>
                                <input type="button" class="btn btn-primary form-control-inline"
                                       value="查询" id="Search">
                                <div style="padding-top: 1%">
                                    <button style="cursor: pointer;text-align: center" id="get"
                                            class="btn btn-primary dropdown-toggle" type="button">
                                        筛选条件<span class="caret"></span>
                                    </button>
                                </div>
                            </form>
                            <br>
                            <br>
                            <div class="condition">
                                <span>价格（元）</span>
                                <input type="text" name="value_min" class="form-control form-control-inline" placeholder="最低">--->
                                <input
                                        type="text" name="value_max" class="form-control form-control-inline" placeholder="最高">
                                <span>时间（天）</span>
                                <input type="text" name="day_min" class="form-control form-control-inline" placeholder="最低">---><input
                                    type="text" name="day_max" class="form-control form-control-inline" placeholder="最高">
                                <br>
                                <br>
                                <span>体积</span>
                                <input type="text" name="x" class="form-control form-control-inline">x
                                <input type="text" name="y" class="form-control form-control-inline">x
                                <input type="text" name="z" class="form-control form-control-inline">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div  class="row">
            <div name="centerDiv" class="col-sm-9">
                <div class="panel panel-default">
                    <div class="panel-heading" style="text-align: center">
                        <span class="intro">查询结果</span>
                    </div>
                    <div class="panel-body">
                        <table id="table" class="table table-hover table-bordered" style="table-layout: fixed;width: 100%"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div name="right" class="right">
    <br>
    <br>
    <br>
    <div class="list">
        <%--<span class="intro">编号</span>：12138--%>
        <%--<br>--%>
        <%--<span class="intro">包裹</span>：Java程序设计--%>
        <%--<br>--%>
        <%--<span class="intro">公司名称</span>：顺丰--%>
        <%--<br>--%>
        <%--<span class="intro">备注</span>：很快--%>
        <iframe src="/packageController/history?user_id=123"></iframe>
    </div>
</div>
<script src="../../js/costEstimate/plugins/kuCity.js"></script>
<script>
    $('.search').kuCity();
    $('.search3').kuCity();
</script>
<script>
    $(function () {
        $("#get").click(function () {
            $(".condition").animate({
                height: "toggle"
            })
        })
    })
    $("#history").click(function () {
        $(".right").animate({
            height: "toggle"
        });
    })

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

        $("#Search").click(function () {
            var str = new Array();
            var datas;
            $("#table").bootstrapTable('destroy');
            var destination = $("#destination").val();
            var weight = $("#weight").val();
            var start = $("#start").val();
            $.ajax({
                url:'/packageController/costQuery?destination='+destination+'&weight='+weight+'&start='+start,
                type:"post",
                success:function (res) {
                    datas = eval(res);
                    for (var i=0;i<datas.length;i++){
                        str.push({"name":datas[i].name,"1":datas[i].start,"2":datas[i].destination,"3":datas[i].cost,"4":datas[i].trustDegree})
                    }
                    $("#table").bootstrapTable({
                        data: str,
                        striped: true,
                        pageSize: 8,
                        pagination: true,
                        pageNumber: 1,
                        clickToSelect: true,
                        dataType: "json",
                        method: "POST",
                        contentType: "application/x-www-form-urlencoded;charset=utf-8",
                        dataField: "data",

                        columns: [
                            {
                                field: 'name',
                                title: '公司名称'
                                ,width:100
                            },
                            {
                                filed: 'start',
                                title: '起点',
                                width:100
                            }, {
                                filed: 'destination',
                                title: "终点",width:100
                            }, {
                                filed: "cost",
                                title: "花费",
                                sortable: true, width:100,
                            }, {
                                filed: "trustDegree",
                                title: "信用等级", width:100,
                            }
                        ]
                    })
                }
            })
        })
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
</body>
</html>