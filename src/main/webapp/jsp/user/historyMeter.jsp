<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/01/03
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
    body{
        font-family: "华文细黑";
        background:url("../../image/history/history.jpg") no-repeat;
        background-size:cover;
    }
    .text{
        text-align: center;
        width: 300px;
        height: 50px;
        font-weight: normal;
        background-image: -webkit-linear-gradient(left,blue,#66ffff 10%,#cc00ff 20%,#CC00CC 30%, #CCCCFF 40%, #00FFFF 50%,#CCCCFF 60%,#CC00CC 70%,#CC00FF 80%,#66FFFF 90%,blue 100%);
        -webkit-text-fill-color: transparent;/* 将字体设置成透明色 */
        -webkit-background-clip: text;/* 裁剪背景图，使文字作为裁剪区域向外裁剪 */
        -webkit-background-size: 200% 100%;
        -webkit-animation: masked-animation 4s linear infinite;
        font-size: 20px;
    }
</style>
<div>
    <c:forEach items="${history}" var="history" >
        <span class="intro">起点</span>：${history.start}
        <br>
        <span class="intro">终点</span>：${history.destination}
        <br>
        <span class="intro">重量</span>：${history.weight}
        <br>
        <span class="intro">时间</span>：<fmt:formatDate value="${history.time}" pattern="yyyy-MM-dd HH:mm:ss"/>
        <br>
        <hr>
    </c:forEach>
</div>