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

    <script type="text/javascript" src="../../Bootstrap/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="../../js/table.min.js"></script>
    <script type="text/javascript" src="../../Bootstrap/bootstrap-table.js"></script>
    <script type="text/javascript" src="../../Bootstrap/locale/bootstrap-table-zh-CN.js"></script>
    <script type="text/javascript">


    </script>
    <style>
        table{ border-collapse: collapse; border:1px solid #F2F2F2}
        table td{ text-align:center; height:20px; font-size:12px; line-height:30px; border:1px solid #DDDDDD;}
    </style>

    <script type="text/javascript" src="../../src/main/webapp/jilb/jquery.min.js"></script>
    <script type="text/javascript" src="../../src/main/webapp/jilb/jquery.easyui.min.js"></script>
    <link type="text/css" href="../../src/main/webapp/jilb/easyui.css"/>
    <script type="text/javascript">

    </script>
</head>
<body>
<div style="margin: 10px 0px 0px 20px">
    <input type="button" name="add" id="add" value="添加">
    <input type="button" name="select_delete" id="select_delete" value="批量删除">
    <input type="button" name="ensure" id="ensure" value="确定" style="display: none" >
</div>
<div>
<table id="table " class="table table-hover table-bordered"></table>
</div>
<script>
    $(function () {
        var str=new Array();
        var datas;
        $.ajax({
            url:'/personController/manage_manager',
            type:"post",
            success:function (res) {
                datas=eval(res);
                for (var i=0;i<datas.length;i++){
                    str.push({"id":datas[i].id,"1":datas[i].name,"2":datas[i].account,"3":datas[i].password,"4":datas[i].type})
                }
                $("#table").bootstrapTable({
                    data:str,
                    striped: true,
                    pageSize: 2,
                    pagination: true,
                    pageNumber: 1,
                    clickToSelect: true,
                    dataType:"json",
                    method:"POST",
                    contentType: "application/x-www-form-urlencoded;charset=utf-8",
                    dataField: "data",
                    columns:[
                        {
                            field:'name',title:'编号',
                            width:100
                        },{
                            field:'1',title:'姓名',
                            width:100
                        },{
                            field:'2',title:'账号',
                            width:100
                        },{
                            field:'3',title:'密码',
                            width:100
                        },{
                            field:'4',title:'类型',
                            width:100
                        }
                    ]
                })
            }
        })
    })
</script>
</body>
</html>
