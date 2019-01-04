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
<div>
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

<script>
    window.onload=function () {
        // var str = new Array();

        $.ajax({
            url: '/personController/${functionName}',
            type: "post",
            success: function (res) {
                data = eval(res);
                var datas = data.obj;
                    var table=document.getElementById("tables");
                if (datas.length == 0){
                    var temp = table.insertRow(table.rows.length).insertCell(0);
                    temp.innerHTML = "暂无数据";
                    temp.colSpan = 6;
                } else {
                    for(var i=0;i<datas.length;i++){
                        var row=table.insertRow(table.rows.length);
                        var c1=row.insertCell(0);
                        c1.innerHTML= i + 1;

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
            }
        })
    }
</script>
</div>
</body>
</html>
