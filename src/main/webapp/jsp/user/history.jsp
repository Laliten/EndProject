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

<div>
    <c:forEach items="${history}" var="history" >
        <span class="intro">地点</span>：${history.place}
        <br>
        <span class="intro">时间</span>：${history.time}
        <br>
    </c:forEach>
</div>
