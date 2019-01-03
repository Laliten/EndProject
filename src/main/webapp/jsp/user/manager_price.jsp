<%--
  Created by IntelliJ IDEA.
  User: xqh
  Date: 2018/12/27
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>计价表格</title>
    <link rel="stylesheet" href="../../css/bootstrap.css"/>

    <script type="text/javascript" src="../../js/table.min.js"></script>
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

</head>
<body>
<div style="margin: 10px 0px 0px 20px">
    <input type="button" name="button3" id="button3" value="添加">
    <input type="button" name="select_delete" id="select_delete" value="批量删除">
    <input type="button" name="ensure" id="ensure" value="确定" style="display: none" >
</div>

<table width="1000px" border="20" cellspacing="0" cellpadding="0" style="margin:0 auto">
    <tbody>
    <tr style="background-color: #EFEFEF">
        <td width="16.6%" ><img src="../image/勾.png" id="button1" height="20" width="20">
            <img src="../image/叉.png" id="button2" height="20" width="20"></td>
        <td width="16.6%"><strong>编号</strong></td>
        <td width="16.6%"><strong>目的地</strong></td>
        <td width="16.6%">首重1kg</td>
        <td width="16.6%">...</td>
        <td width="16.6%"><strong>操作</strong></td>
    </tr>
    <tr>
        <td width="16.6%"><input type="checkbox" name="checkbox" id="1"></td>
        <td width="16.6%">1</td>
        <td width="16.6%">广州</td>
        <td width="16.6%">1</td>
        <td width="16.6%">1</td>
        <td width="16.6%">
            <font color="green" style="cursor: pointer">修改</font>
            <font color="red" style="cursor: pointer"> 删除</font>
        </td>
    </tr>
    <tr>
        <td><input type="checkbox" name="checkbox2" id="2"></td>
        <td>2</td>
        <td>2</td>
        <td>2</td>
        <td>2</td>
        <td>
            <font color="green" style="cursor: pointer">修改</font>
            <font color="red" style="cursor: pointer"> 删除</font>
        </td>
    </tr>
    <tr>
        <td><input type="checkbox" name="checkbox3" id="3"></td>
        <td>3</td>
        <td>2</td>
        <td>2</td>
        <td>2</td>
        <td>
            <font color="green" style="cursor: pointer">修改</font>
            <font color="red" style="cursor: pointer"> 删除</font>
        </td>
    </tr>

    </tbody>
</table>
</body>

</html>