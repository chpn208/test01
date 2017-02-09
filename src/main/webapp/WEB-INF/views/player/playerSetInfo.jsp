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
            <th data-options="field:'options',width:280">操作</th>
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
                            <%--<input type="hidden" id="doubleIntegral" value="${player.doubleIntegral}"/>--%>
                        [<a id="doubleIntegral" setValue="${player.doubleIntegral}" setType="0"
                            href="javascript:upPlayerSet('doubleIntegral')">
                                <label>
                                    <c:choose>
                                        <c:when test="${player.doubleIntegral == 0}">
                                            开启
                                        </c:when>
                                        <c:otherwise>
                                            关闭
                                        </c:otherwise>
                                    </c:choose>
                                </label>
                        玩家双倍积分
                    </a>]
                        [<a id="noConsumeDiamond" setValue="${player.noConsumeDiamond}" setType="1"
                            href="javascript:upPlayerSet(noConsumeDiamond)">
                                <label>
                                    <c:choose>
                                        <c:when test="${player.noConsumeDiamond == 0}">
                                            开启
                                        </c:when>
                                        <c:otherwise>
                                            关闭
                                        </c:otherwise>
                                    </c:choose>
                                </label>
                        玩家创建房间不扣钻石
                    </a>]
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
        window.location.href="/admin/player/"+playerId;
    }
    function upPlayerSet(obj) {
        debugger
        var data = new Object();
        var playerId = $('#playerId').val();
        data.playerId = playerId;
        data.setType=$("#"+obj).attr("setType") == 0?1:0;
        data.setValue = $("#"+obj).attr("setValue") == 0?1:0;
        $.ajax({
            url:"/admin/playerSet",
            type:"POST",
            data:data,
            success:function (msg) {
                if(msg.code == 200){
                    alert("修改成功")
                    $("#"+obj).attr("setValue").val(msg.result);
                    var text;
                    if(msg.result == 0){
                        text = "开启";
                    }else {
                        text="关闭";
                    }
                    $("#"+obj).find("label").text(text);
                }else {
                    alert(obj.message);
                }
            }
        })

    }
</script>
