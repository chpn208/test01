<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <table class="formtable" style="width: 280px;">
        <tbody>
        <tr>
            <td align="right" id="userName" style="width: 60px;"><span class="filedzt">用户名:</span></td>
            <td class="value"><input class="inputxt" value="${userId}" readonly="readonly" disabled="disabled"></td>
        </tr>
        <tr>
            <td align="right"><span class="filedzt">姓名:</span></td>
            <td class="value"><input class="inputxt" value="${userName}" readonly="readonly" disabled="disabled"></td>
        </tr>
        <tr>
            <td align="right"><span class="filedzt">微信号:</span></td>
            <td class="value"><input class="inputxt" value="${wxNumber}" readonly="readonly" disabled="disabled"></td>
        </tr>
        <tr>
            <td align="right"><span class="filedzt">手机号码:</span></td>
            <td class="value"><input class="inputxt" value="${mobile}" readonly="readonly" disabled="disabled"></td>
        </tr>
        <tr>
            <td align="right"><span class="filedzt">剩余钻石:</span></td>
            <td class="value"><input class="inputxt" value="${diamond}" readonly="readonly" disabled="disabled"></td>
        </tr>
        </tbody>
    </table>
</div>
