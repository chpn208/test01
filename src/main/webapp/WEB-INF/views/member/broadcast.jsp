<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="../../../easyui/jquery.min.js"></script>
<div>
    <label>广播编辑</label>
    <input type="button" value="添加" onclick="add()">
    <table id="broadcastTable">
        <thead>
            <td>广播内容</td><td>详细</td><td>状态</td><td>日期</td><td>操作</td>
        </thead>
        <tbody>
        <c:forEach items="${broadCasts}" var="item">
            <tr>
                <td>${item.content}</td>
                <td>${item.desc}</td>
                <td>${item.status}</td>
                <td>${item.date}</td>
                <td>删除<td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script type="text/javascript">
    function add() {
        $("#broadcastTable").append("<tr><td>111</td></tr>")
    }

    function del(obj){

    }
</script>
