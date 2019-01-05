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
        table{ border-collapse: collapse; border:1px solid #F2F2F2}
        table td{ text-align:center; height:20px; font-size:12px; line-height:30px; border:1px solid #DDDDDD;}
    </style>

</head>
<body>
<div style="margin: 10px 0px 0px 20px">
    <input type="button" name="add" id="add" value="添加">
    <input type="button" name="select_delete" id="select_delete" value="批量删除">
    <input type="button" name="ensure" id="ensure" value="确定" style="display: none" >
</div>
<div style="text-align: center">
    <table id="tables" class="table table-hover table-bordered">
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>用户名</th>
            <th>密码</th>
            <th>类型</th>
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
        <a href="javascript:void(0);" name="pre" onclick="initManager('pre')"><u><< 上一页</u></a>
        &nbsp;
        <a href="javascript:void(0);" name="next" onclick="initManager('next')"><u>下一页 >></u></a>
    </span>
</div>
<script>
    $(function () {
        initManager("init");
    });

    function initManager(action) {
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
            url: '/personController/${functionName}',
            type: "post",
            data:{
                "currentPage":currentPage,
                "nextPage": nextPage,
                "pageSize": pageSize,
                "totalPages": totalPages,
                "totalRecordSize": totalRecordSize
            },
            success: function (res) {
                var num = totalRecordSize;
                var map = res.obj;
                var datas = map["allUsers"];
                var page = map["page"];
                var table=document.getElementById("tables");
                if (!res.success){
                    var temp = table.insertRow(table.rows.length).insertCell(0);
                    temp.innerHTML = res.msg;
                    temp.colSpan = table.rows[0].cells.length;
                } else {
                    $("#tables tr:not(:first)").empty("");
                    for(var i=0;i<datas.length;i++){
                        var row=table.insertRow(1);
                        var c1=row.insertCell(0);
                        c1.innerHTML= num--;

                        var c2=row.insertCell(1);
                        c2.innerHTML=datas[i].name;

                        var c3=row.insertCell(2);
                        c3.innerHTML=datas[i].account;

                        var c4=row.insertCell(3);
                        c4.innerHTML=datas[i].password;

                        var c5=row.insertCell(4);
                        c5.innerHTML=datas[i].type;

                        var c6=row.insertCell(5);
                        c6.innerHTML='<p>修改</p>'
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
</script>
</body>
</html>
