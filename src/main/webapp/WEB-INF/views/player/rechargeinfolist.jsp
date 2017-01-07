<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css" href="../../../easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="../../../easyui/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="../../../css/tableform.css"/>
<script type="text/javascript" src="../../../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../../../easyui/jquery.easyui.min.js"></script>

<div style="width: 500px;">
    <table class="easyui-datagrid"style="">
        <thead>
            <tr>
                <th data-options="field:''"></th>
                <th data-options="field:'playerId'" >玩家id</td>
                <th data-options="field:'agentId'">代理id</td>
                <th data-options="field:'rechargeNum'" >充值数量</td>
                <th data-options="field:'sendNum'" >赠送数量</td>
                <th data-options="field:'time'">充值时间</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${page.result}" var="item">
                <tr>
                    <td></td>
                    <td>${item.playerId}</td>
                    <td>${item.agentId}</td>
                    <td>${item.rechargeNum}</td>
                    <td>${item.sendNum}</td>
                    <td>${item.rechargeTime}</td>
                </tr>
            </c:forEach>
        </tbody>

    </table>
</div>
