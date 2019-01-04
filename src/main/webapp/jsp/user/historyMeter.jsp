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