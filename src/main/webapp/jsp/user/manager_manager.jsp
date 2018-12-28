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
    <link rel="stylesheet" href="../css/bootstrap.css"/>

    <script type="text/javascript" src="../js/table.min.js"></script>
    <script type="text/javascript">

        $(document).ready(function() {

            $("#ensure").click(function(){

                $(":checked").parent().parent().fadeOut("show"); //隐藏所有被选中的input元素

                //parent() 获得当前匹配元素集合中每个元素的父元素,

            })

            $("tr").mousemove(function(){
                $(this).css("background","#F0F0F0");  //鼠标经过背景颜色变为灰色
            })
            $("tr").mouseout(function(){
                $(this).css("background","#fff");  //离开后背景颜色回复白色
            })

            $("#button1").click(function(){
                $(":checkbox").attr("checked",true);   //设置所有复选框默认勾选
            })
            $("#button2").click(function(){
                $(":checkbox").attr("checked",false);   //设置所有复选框未勾选
            })
            $("#select_delete").click(function () {
                $("#ensure").toggle();
            })
        });
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
<table id="datagrid"></table>
</div>
</body>
</html>
