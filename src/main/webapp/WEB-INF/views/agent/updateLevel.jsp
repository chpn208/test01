<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <form>
        <table>
            <tbody>
            <tr>
                <td align="right">
                    <label class="Validform_label">代理商id</label>
                </td>
                <td class="value">
                    <input class="input" id="agentId" name="agentId" value="${agentId}">
                </td>
            </tr>
            <tr>
                <td align="right">
                    <label class="Validform_label">代理名称</label>
                </td>
                <td class="value"><input class="input" value="${agentName}"></td>
            </tr>
            <tr>
                <td align="right">
                    <label class="Validform_label">等级</label>
                </td>
                <td>
                    <select id="agentLevel" class="input">
                        <c:forEach items="${permissions}" var="item">
                            <option value="${item.level}"
                                    <c:if test="${item.level == agentLevel}">selected="selected"</c:if>>${item.desc}
                                    <%--<c:if test="${item.level == agentLevel}">selected:selected</c:if>>${item.desc}--%>
                            </option>
                        </c:forEach>
                    </select>
                </td>
                <%--<td>代理商等级</td><td>${agentLevel}</td>--%>
            </tr>
            </tbody>
        </table>
    </form>
    <input type="button" value="确定" onclick="updateAgentLevel()">

</div>
<script type="text/javascript">
    function updateAgentLevel() {
        debugger
        var agentId = $("#agentId").val();
        var level = $("#agentLevel").val();
        var obj = new Object();
        obj.agentId = agentId;
        obj.level = level;
        $.ajax({
            url:"/agent/updateLevel",
            data:obj,
            type:"post",
            success:function (data) {
                if (data.code == 200){
                    alert("修改成功");
                }else {
                    alert(data.msg);
                }
            }
        })
    }
</script>
