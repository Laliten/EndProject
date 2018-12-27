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
    <c:forEach items="${packageDtoList}" var="packageDtoList" >
    <span class="intro">编号</span>：${packageDtoList.id}
    <br>
    <span class="intro">包裹</span>：${packageDtoList.name}
    <br>
    <span class="intro">公司名称</span>：${packageDtoList.company_name}
    <br>
    <span class="intro">备注</span>：${packageDtoList.description}
</c:forEach>
</div>
