<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/20
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link href="../../css/bootstrap.css" rel="stylesheet">
<div>
    <table class="table">
        <thead>
        <tr>
            <th>公司</th>
            <th>起点</th>
            <th>终点</th>
            <th>花费</th>
            <th>信用等级</th>
        </tr>
        </thead>
<c:forEach items="${costEstimateDto}" var="costEstimateDtoList">
    <tr>
        <th>${costEstimateDto.name}</th>
        <th>${costEstimateDto.start}</th>
        <th>${costEstimateDto.destination}</th>
        <th>${costEstimateDto.cost}</th>
        <th>${costEstimateDto.trustDegree}</th>
    </tr>
</c:forEach>
    </table>
</div>
