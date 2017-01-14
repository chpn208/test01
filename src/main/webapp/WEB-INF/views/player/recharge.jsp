<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
   我的剩余钻石:
    <span>${countDiamond}</span>
    <span style="color: red;">
        玩家充钻：
        <br>
        【满${chargeNum}送${sendNum}由系统自动赠送，切勿乱价】
    </span>
    <form id="chargeForm" action="/agent/payerRecharge/recharge">
        <table>
            <tbody><tr>
                <td align="right">
                    <label class="Validform_label">
                        玩家ID:
                    </label>
                </td>
                <td class="value">
                    <input class="inputxt" id="playerId" name="playerId" value="${playerId}" datatype="n" disabled="disabled">
                </td>
            </tr>
            <tr>
                <td align="right">
                    <label class="Validform_label">
                        昵称:
                    </label>
                </td>
                <td class="value">
                    <input class="inputxt" id="titleName" name="titleName" value="${nickName}" disabled="disabled">

                </td>
            </tr>


            <tr>
                <td align="right">
                    <label class="Validform_label">
                        数量:
                    </label>
                </td>
                <td class="value">
                    <select id="rechargeNum">
                        <c:forEach items="${rechargeMap}" var="item">
                            <option value="${item.key}">${item.value}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            </tbody>
        </table>
        <input id="ok" type="button" value="确定" onclick="recharge()">
    </form>
</div>
<script type="text/javascript">
    function recharge() {
        var playerId = $('#playerId').val();
        var rechargeNum= $('#rechargeNum').val();
        window.location.href="/agent/playerRecharge/recharge?playerId="+playerId+"&rechargeNum="+rechargeNum;
    }
</script>