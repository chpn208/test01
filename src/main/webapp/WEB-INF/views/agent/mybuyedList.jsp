<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="../../../easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="../../../easyui/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="../../../css/tableform.css"/>
<script type="text/javascript" src="../../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<input type="hidden" id="pageSize" value="${page.pageSize}"/>
<input type="hidden" id="pageNum" value="${page.pageNum}"/>
<input type="hidden" id="pageCount" value="${page.count}"/>

<div>
    <table class="easyui-datagrid" style="width:1120px;height:auto">
        <thead>
        <tr>
            <%-- <th data-options="field:'membername',width:80">会员名</th>--%>
            <th data-options="field:'serId',width:80">购钻时间</th>
            <th data-options="field:'rechargeNum',width:80">购钻数量</th>
            <th data-options="field:'sendNum',width:80">赠送数量</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.result}" var="item">
            <tr>
                <td>${item.time}</td>
                <td>${item.rechargeNum}</td>
                <td>${item.sendNum}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
