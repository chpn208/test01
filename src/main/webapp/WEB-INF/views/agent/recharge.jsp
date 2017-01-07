<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
   我的剩余钻石:
    <span>${countDiamond}</span>
    <span style="color: red;">
        代理商充钻：
        <br>
        【满${chargeNum}送${sendNum}由系统自动赠送，切勿乱价】
    </span>
    <form id="chargeForm" action="/agent/payerRecharge/recharge">
        <table>
            <tbody><tr>
                <td align="right">
                    <label class="Validform_label">
                        代理商ID:
                    </label>
                </td>
                <td class="value">
                    <input class="inputxt" id="agentId" name="agnetId" value="${agent.id}" datatype="n" disabled="disabled">
                </td>
            </tr>
            <tr>
                <td align="right">
                    <label class="Validform_label">
                        代理商名称:
                    </label>
                </td>
                <td class="value">
                    <input class="inputxt" id="titleName" name="titleName" value="${agent.name}" disabled="disabled">

                </td>
            </tr>


            <tr>
                <td align="right">
                    <label class="Validform_label">
                        数量:
                    </label>
                </td>
                <td class="value">
                    <input class="inputxt" id="rechargeNum" name="rechargeNum" />
                </td>
            </tr>
            </tbody>
        </table>
        <input id="ok" type="button" value="确定" onclick="recharge()">
    </form>
</div>
<script type="text/javascript">
    function recharge() {
        debugger
        var agentId= $('#agentId').val();
        var rechargeNum= $('#rechargeNum').val();
        window.location.href="/agent/agentRecharge?agentId="+agentId+"&rechargedNum="+rechargeNum;
    }
</script>