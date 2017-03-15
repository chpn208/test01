<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<input id="forwardUrl" type="hidden" value="${url}">
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
        var agentId= $('#agentId').val();
        var rechargeNum= $('#rechargeNum').val();
        var obj = new Object();
        obj.agentId = agentId;
        obj.rechargedNum = rechargeNum;
        $.ajax({
            url:"/agent/agentRecharge",
            data:obj,
            type:"post",
            success:function (data) {
                if (data.code == 200){
                    alert("充值成功")
                    $("#chargeDlg").dialog('close')
                    var forwardUrl = $('#forwardUrl').val();
                    if (forwardUrl) {
                        window.location.href = forwardUrl;
                    }
                }else {
                    alert("充值失败:"+data.msg);
                }
            }
        })
//        window.location.href="/agent/agentRecharge?agentId="+agentId+"&rechargedNum="+rechargeNum;
    }
</script>