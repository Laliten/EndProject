<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/25
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <style>
        html,body{
            width:100%;
            height:100%
        }
        body{
            font-family: "华文细黑";
            background:url("../../image/history/history.jpg")top center no-repeat;
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
</head>
<body>
    <div class="text">
        <h4>历史记录</h4>
    </div>
    <div style="">
        <c:if test="${!empty history}">
            <c:forEach items="${history}" var="history" >
                <span class="intro">地点</span>：${history.place}
                <br>
                <span class="intro">时间</span>：${history.time}
                <br>
                <hr style="height: 1px;color: #0e90d2"/>
            </c:forEach>
        </c:if>
    </div>
</body>
</html>
