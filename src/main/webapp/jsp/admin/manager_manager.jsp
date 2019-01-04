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
    <script type="text/javascript" src="../../js/bootstrap.js"></script>
    <script type="text/javascript" src="../../js/mjs/bootstrap-table-zh-CN.js"></script>
    <script type="text/javascript">


    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <style>
        table{ border-collapse: collapse; border:1px solid #F2F2F2;table-layout: fixed;word-break: break-all; word-wrap: break-word;}
        table td{ text-align:center; height:20px; font-size:12px; line-height:30px; border:1px solid #DDDDDD;}
        th{text-align:center}
        span{cursor: pointer}
    </style>
<script>
    var page=1;

    var pageAll;

    var obj;
    //单个删除
    function del(id){
        $.ajax({
            url:'/personController/singleDelete',
            type:"post",
            data:{"id":id},
            success:function (id) {
                location.reload(true);
            }
        })
    }

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
                url:'/personController/batchDelete',
                type:"post",
                data:{"ids":arr},
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
    function show2() {
        $("#sure_delete").toggle();
    }
</script>
</head>
<body>
<div style="margin: 10px 0px 0px 20px">
    <input type="button" name="add" id="add" value="添加" onclick="window.parent.$('#add_modal').modal('show')" class="btn btn-default">
    <input type="button" name="select_delete" id="select_delete" value="批量删除" onclick="show1()" class="btn btn-default">
    <input type="button" name="ensure" id="ensure" value="确认删除？" style="display: none" onclick="select_del()" class="btn btn-danger">
</div>
<div>
<table id="tables" class="table table-hover table-bordered">
    <tr>
        <th><input type="checkbox" style="float: left" id="checkboxMain" value="zhang">编号</th>
        <th>姓名</th>
        <th>用户名</th>
        <th>密码</th>
        <th>类型</th>
        <th>操作</th>
    </tr>
</table>

<script>

    //全选checkbox
    document.getElementById("checkboxMain").onclick=function () {
        var s=document.getElementsByName("checkbox");
        for (var i=0;i<s.length;i++){
            s[i].checked = this.checked;
        }
    }

    window.onload=function () {
        $.ajax({
            url: '/personController/${functionName}',
            type: "post",
            success: function (res) {
                var datas = res.obj;
                var table=document.getElementById("tables");
                if (!res.success){
                    var temp = table.insertRow(table.rows.length).insertCell(0);
                    temp.innerHTML = res.msg;
                    temp.colSpan = table.rows[0].cells.length;
                } else {
                    for(var i=0;i<datas.length;i++) {
                        var row = table.insertRow(table.rows.length);
                        var c1 = row.insertCell(0);
                        c1.innerHTML = '<input type="checkbox" style="float: left" name="checkbox" value="' + datas[i].id + '">' + (i + 1);

                        var c2 = row.insertCell(1);
                        c2.innerHTML = datas[i].name;

                        var c3 = row.insertCell(2);
                        c3.innerHTML = datas[i].account;

                        var c4 = row.insertCell(3);
                        c4.innerHTML = datas[i].password;

                        var c5 = row.insertCell(4);
                        if (datas[i].type == 0) {
                            c5.innerHTML = "用户";
                        } else {
                            c5.innerHTML = "管理员";
                        }
                        var c6 = row.insertCell(5);
                        c6.innerHTML = '<span style="color: green" id="revise" onclick="window.parent.$(\'#revise_modal\').modal(\'show\');rel(' + datas[i].id + ')" >修改</span><span>&nbsp;&nbsp;</span>' +
                            ' <span style="color:black" id="delete" onclick="show2()">删除</span>' +
                    }                ' <span style="color:red;display: none" id="sure_delete" onclick="del('+datas[i].id+');" >确认删除？</span>';
                }

            }
        })
    }
</script>
</div>
</body>
</html>
