<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}" />
<link rel="stylesheet" type="text/css" href="../../../easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="../../../easyui/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="../../../css/tableform.css"/>
<script type="text/javascript" src="../../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<div style="width: 1120px;">
    <div class="operation">
        <div style="padding-top: 20px; padding-left: 20px">
            玩家id:<input id="playerId" name="playerId" type="text" value="${player.playerId}"/>
        </div>

    </div>
    <div style="height:30px;" class="datagrid-toolbar">
            <span style="float:right">
                <a href="#" class="easyui-linkbutton l-btn"onclick="playerListsearch()">
                    <span class="l-btn-text icon-search l-btn-icon-left">
                        查询
                    </span>
                </a>
            </span>
    </div>
    <table class="easyui-datagrid"style="width:1120px;height:auto">
        <thead>
        <tr>
            <th data-options="field:'playerName',width:80">玩家id</th>
            <th data-options="field:'nickName',width:100">玩家状态</th>
            <th data-options="field:'options',width:120">操作</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <c:if test="${player != null}">
                <td>
                    ${player.playerId}
                </td>
                <td>
                    ${player.status}
                </td>
                <td>
                    [<a href="javascript:openCharge()">充值</a>]
                    [<a href="javascript:openChargeList()">充值详情</a>]
                </td>
                </c:if>
            </tr>
        </tbody>
    </table>
    <div id="dlg"></div>
</div>
<script type="text/javascript">
    function playerListsearch() {
        var playerId = $("#playerId").val();
        window.location.href="/agent/playerRecharge/playerInfo?playerId="+playerId;
    }
    function openCharge() {
        var playerId = $("#playerId").val();
        $("#dlg").dialog({
            title:"recharge",
            width:400,
            height:200,
            close:false,
            cache:false,
            href:'/agent/playerRecharge/preRecharge?playerId='+playerId,
            modal:true
        });

    }
    function openChargeList() {
        var playerId = $('#playerId').val();
        $('#dlg').dialog({
            title:"rechargeList",
            width:500,
            height:400,
            close:false,
            cache:false,
            href:"/agent/playerRecharge/rechargeList?playerId="+playerId,
            modal:true
        })

    }
</script>
