<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}" />
<link rel="stylesheet" type="text/css" href="../../../easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="../../../easyui/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="../../../css/tableform.css"/>
<script type="text/javascript" src="../../../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../../../easyui/jquery.easyui.min.js"></script>
<div style="width: 1120px;">
   <%-- <div class="operation">
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
    </div>--%>
    <table class="easyui-datagrid"style="width:1120px;height:auto">
        <thead>
        <tr>
            <th data-options="field:'type',width:80">活动类型</th>
            <th data-options="field:'state',width:200">活动状态</th>
            <th data-options="field:'startTime',width:280">开始时间</th>
            <th data-options="field:'endTime',width:280">结束时间</th>
            <th data-options="field:'options',width:80">修改</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${commonActives}" var="item">
            <tr id="tr"+${item.type}>
                <td>
                    <label id="type">${item.type}</label>
                </td>
                <td>
                    活动状态:
                    <select id="activeState">
                        <option value="0"  <c:if test="${item.value == 0}">selected="selected"</c:if>>关闭</option>
                        <option value="1" <c:if test="${item.value == 1}">selected="selected"</c:if>>开启</option>
                    </select>
                </td>
                <td>
                        <fmt:formatDate pattern="yyyy-MM-dd"
                                        value="${now}" />
                    <input  id="startDate" type="text" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.startDate}" />">
                    请按 年-月-日 时:分:秒 的格式填写
                </td>


                <td>
                    <input id="endDate" type="text" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.endDate}" />">
                    请按 年-月-日 时:分:秒 的格式填写
                </td>
                <td>
                    <input type="button" value="修改" onclick="upCommonActive(this)"/>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>


    <script type="text/javascript" src="../../../easyui/jquery.easyui.min.js"></script>
    <div id="dlg"></div>
</div>
<script type="text/javascript">

    function upCommonActive(obj) {
        debugger
        var data = new Object();
        var tr = $(obj).parents("tr")[0];
        var activeSate = $(tr).find("[id='activeState']")[0];
        var state= $(activeSate).find("option:selected").val();
        var startTime = $(tr).find("[id='startDate']")[0].value;
        var endTime = $(tr).find("[id='endDate']")[0].value;
        data.type=$(tr).find("[id='type']")[0].innerText;
        data.state= $(activeSate).find("option:selected").val();
        data.startTime = startTime;
        data.endTime = endTime;
        var url = "/admin/upCommonActive?type="+data.type+"&state="+state+"&startTime="+startTime+"&endTime="+endTime;
//        val url="/admin/upCommonActive";
        $.ajax({
            url:url,
            type:"GET",
//            data:data,
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
