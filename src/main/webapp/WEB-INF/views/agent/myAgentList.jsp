
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
            <th data-options="field:'serId',width:80">代理id</th>
            <th data-options="field:'rechargeNum',width:80">代理等级</th>
            <th data-options="field:'sendNum',width:80">下级代理数</th>
            <th data-options="field:'operation',width:80">下级代理数</th>
           <%-- <th data-options="field:'sendNum',width:80">操作</th>--%>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.result}" var="item">
            <tr>
                <td>${item.id}</td>
                <td>${item.level}</td>
                <td>${item.agentNum}</td>
                <td><a href="javascript:preAgentRecharge(${item.id})"> 充值</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div id="chargeDlg">

    </div>
</div>
<script type="text/javascript">
    function preAgentRecharge(agentId) {
        debugger
        $("#chargeDlg").dialog({
            title:"recharge",
            width:400,
            height:200,
            close:false,
            cache:false,
            href:'/agent/preRecharge?agentId='+agentId,
            modal:true
        });
    }
</script>