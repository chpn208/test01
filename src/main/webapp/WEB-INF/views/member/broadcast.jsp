
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="../../../easyui/jquery.min.js"></script>
<div>
    <label>广播编辑</label>
    <table id="broadcastTable">

    </table>
    <input type="button" value="添加" onclick="add()">
</div>
<script type="text/javascript">
    function add() {
        $("#broadcastTable").append("<tr><td>111</td></tr>")
    }
</script>
