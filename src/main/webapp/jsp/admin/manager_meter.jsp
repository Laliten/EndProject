<%--
  Created by IntelliJ IDEA.
  User: xqh
  Date: 2018/12/24
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>

    <meta charset="utf-8">

    <title>管理员表格</title>
    <link rel="stylesheet" href="../../css/bootstrap.css"/>
    <link rel="stylesheet" href="../../Bootstrap/Content/bootstrap-table.css">

    <script type="text/javascript" src="../../js/mjs/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/table.min.js"></script>
    <script type="text/javascript" src="../../js/bootstrap-table.js"></script>
    <script type="text/javascript" src="../../js/mjs/bootstrap-table-zh-CN.js"></script>
    <script type="text/javascript">


    </script>
    <style>
        table{ border-collapse: collapse; border:1px solid #F2F2F2;table-layout: fixed;word-break: break-all; word-wrap: break-word;}
        table td{ text-align:center; height:20px; font-size:12px; line-height:30px; border:1px solid #DDDDDD;}
        th{text-align:center}
        span{cursor: pointer}
    </style>


    <script type="text/javascript">
        var obj;


        //批量删除
        function select_del() {
            $("input[type='checkbox']").each(function () {
                if ($(this).is(":checked")) {
                    //alert($(this).val());
                    var arr="";
                    arr+=$(this).val()+",";
                    //alert(arr);
                }
                $.ajax({
                    url:'/meterController/batchDelete',
                    type:"post",
                    data:{"meterIds":arr},
                    success:function (res) {
                        location.reload(true);
                    }
                })
            })
        }

        //点击批量删除显示确定按钮
        function show1() {
            $("#ensure").toggle();
        }
        function show2(i) {
            var str="";
            str+="#";
            str+=i;
            $(str).toggle();
        }
        var i=0;

        //单个删除
        function del(id){
            $.ajax({
                url:'/meterController/singleDelete',
                type:"post",
                data:{"id":id},
                success:function (res) {
                    location.reload(true);
                }
            })
        }
    </script>


</head>
<body>
<div style="margin: 10px 0px 0px 20px">
    <input type="button" name="add" id="add" value="添加" onclick="window.parent.$('#add_price').modal('show')" class="btn btn-default">
    <input type="button" name="select_delete" id="select_delete" value="批量删除" onclick="show1()" class="btn btn-default">
    <input type="button" name="ensure" id="ensure" value="确认删除？" style="display: none" onclick="select_del()" class="btn btn-danger">
</div>
<div style="text-align: center">
    <table id="tables" class="table table-hover table-bordered" style="text-align: center">
        <tr>
            <th><input type="checkbox" style="float: left" id="checkboxMain" value="zhang">序号</th>
            <th>目的地</th>
            <th>首重(kg)</th>
            <th>首重价格(kg/元)</th>
            <th>续重价格(kg/元)</th>
            <th>公司名称</th>
            <th>操作</th>
        </tr>
    </table>
    <span>
        <form id="pageForm" method="post">
            <input type="hidden" id="currentPage" name="currentPage" value="${sessionScope.sessionInfo.page.currentPage}">
            <input type="hidden" id="nextPage" name="nextPage" value="${sessionScope.sessionInfo.page.nextPage}">
            <input type="hidden" id="pageSize" name="pageSize" value="${sessionScope.sessionInfo.page.pageSize}">
            <input type="hidden" id="totalPages" name="totalPages" value="${sessionScope.sessionInfo.page.totalPages}">
            <input type="hidden" id="totalRecordSize" name="totalRecordSize" value="${sessionScope.sessionInfo.page.totalRecordSize}">

        </form>
        <a href="javascript:void(0);" name="pre" onclick="initUser('pre')"><u><< 上一页</u></a>
        &nbsp;
        <a href="javascript:void(0);" name="next" onclick="initUser('next')"><u>下一页 >></u></a>
    </span>
</div>
<script>

    function rel(id) {
        $.ajax({
            url:'/meterController/search',
            type:"post",
            data:{"id":id},
            success:function (res) {
                parent.document.getElementById('id').value=res.obj.id;
                parent.document.getElementById('name').value=res.obj.name;
                parent.document.getElementById('account').value=res.obj.account;
            }
        })
        parent.document.getElementById('id').value=place;
    }

    //全选checkbox
    document.getElementById("checkboxMain").onclick=function () {
        var s=document.getElementsByName("checkbox");
        for (var i=0;i<s.length;i++){
            s[i].checked = this.checked;
        }
    }


    $(function () {
        initUser("init");
    });
    function initUser (action) {
        var currentPage = parseInt($("#currentPage").val());
        var nextPage = parseInt($("#nextPage").val());
        var pageSize = parseInt($("#pageSize").val());
        var totalPages = parseInt($("#totalPages").val());
        var totalRecordSize = parseInt($("#totalRecordSize").val());
        switch (action){
            case "pre":
                if (currentPage - 1 <= 0){
                    nextPage = 1;
                    currentPage = nextPage;
                } else {
                    nextPage = currentPage -1;
                    currentPage = nextPage;
                }
                break;
            case "next":
                if (currentPage + 1 <= totalPages){
                    nextPage = currentPage + 1;
                    currentPage = nextPage;
                } else {
                    nextPage = totalPages;
                    currentPage = nextPage;
                }
                break;
            default:
                currentPage = 1;
                nextPage = 1;
                pageSize = 10;
                totalPages = 1;
                totalRecordSize = 10;
                break;
        }

        $.ajax({
            url: "/meterController/${functionName}",
            data:{
                "companyCode":"${companyCode}",
                "currentPage":currentPage,
                "nextPage": nextPage,
                "pageSize": pageSize,
                "totalPages": totalPages,
                "totalRecordSize": totalRecordSize
            },
            type: "post",
            success: function (res) {
                var map = res.obj;
                var datas = map["allMeters"];
                var page = map["page"];
                var table=document.getElementById("tables");
                if (!res.success){
                    var temp = table.insertRow(table.rows.length).insertCell(0);
                    temp.innerHTML = res.msg;
                    temp.colSpan = table.rows[0].cells.length;
                } else {
                    $("#tables tr:not(:first)").empty("");
                    for(var i=0;i<datas.length;i++){
                        var row=table.insertRow(table.rows.length);
                        var c1=row.insertCell(0);
                        c1.innerHTML = '<input type="checkbox" style="float: left" name="checkbox" value="' + datas[i].id + '">' + (i + 1);

                        var c2=row.insertCell(1);
                        c2.innerHTML=datas[i].destination;

                        var c3=row.insertCell(2);
                        c3.innerHTML=datas[i].firstWeight;

                        var c4=row.insertCell(3);
                        c4.innerHTML=datas[i].firstWeightPrice;

                        var c5=row.insertCell(4);
                        c5.innerHTML=datas[i].nextWeightPrice;

                        var c6=row.insertCell(5);
                        c6.innerHTML='<p>韵达</p>'

                        var c7=row.insertCell(6);
                        c7.innerHTML = '<span style="color: green" id="revise" onclick="window.parent.$(\'#userInfoModel\').modal(\'show\');rel(' + datas[i].id + ')" >修改</span><span>&nbsp;&nbsp;</span>' +
                            ' <span style="color:black" id="delete" onclick="show2('+i+');myCan()">删除</span>' +
                            ' <span style="color:red;display: none" id='+i+' onclick="del(' + datas[i].id + ');" >确认删除？</span>';

                    }
                }
                $("#currentPage").val(page.currentPage);
                $("#nextPage").val(page.nextPage);
                $("#pageSize").val(page.pageSize);
                $("#totalPages").val(page.totalPages);
                $("#totalRecordSize").val(page.totalRecordSize);
            }
        })
    }

    $("#searchButton", parent.document).click(function () {
        var searchText = $("#searchText",parent.document);
        var iframe = $("#iframeContainer", parent.document);
        var type = iframe.attr("src").substring(iframe.attr("src").lastIndexOf("_")+1,iframe.attr("src").lastIndexOf("?"));

        if (searchText.val() != "" || searchText.val() != null || searchText.length != 0){
            if (iframe.attr("src") == "manager_manager"){
                type = "user";
            } else if (iframe.attr("src") == "/manager_meter"){
                type = "meter";
            }
            $.ajax({
                url:  "/searchController/doSearch",
                type:"post",
                data:{
                    "searchText": searchText.val(),
                    "type": type,
                    "currentPage":1,
                    "nextPage": 1,
                    "pageSize": 20,
                    "totalPages": 1,
                    "totalRecordSize": 0
                },
                success:function (res) {
                    alert(res.msg);
                    var map = res.obj;
                    var datas = map["allMeters"];
                    var table = document.getElementById("tables");
                    if (!res.success){
                        var temp = table.insertRow(table.rows.length).insertCell(0);
                        temp.innerHTML = res.msg;
                        temp.colSpan = table.rows[0].cells.length;
                    } else {
                        for(var i=0;i<datas.length;i++){
                            $("#tables tr:not(:first)").empty("");

                            var row=table.insertRow(table.rows.length);
                            var c1=row.insertCell(0);
                            c1.innerHTML = '<input type="checkbox" style="float: left" name="checkbox" value="' + datas[i].id + '">' + (i + 1);

                            var c2=row.insertCell(1);
                            c2.innerHTML=datas[i].destination;

                            var c3=row.insertCell(2);
                            c3.innerHTML=datas[i].firstWeight;

                            var c4=row.insertCell(3);
                            c4.innerHTML=datas[i].firstWeightPrice;

                            var c5=row.insertCell(4);
                            c5.innerHTML=datas[i].nextWeightPrice;

                            var c6=row.insertCell(5);
                            c6.innerHTML=datas[i].companyName;

                            var c7 = row.insertCell(6);
                            c7.innerHTML = '<span style="color: green" id="revise" onclick="window.parent.$(\'#revise_modal\').modal(\'show\');rel(' + datas[i].id + ')" >修改</span><span>&nbsp;&nbsp;</span>' +
                                ' <span style="color:black" id="delete" onclick="show2()">删除</span>' +
                                ' <span style="color:red;display: none" id="sure_delete" onclick="del(' + datas[i].id + ');" >确认删除？</span>';

                        }
                    }
                }
            });
        }
    });
</script>
</body>
</html>
