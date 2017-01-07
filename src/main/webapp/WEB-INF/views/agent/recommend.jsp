
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}" />
<link rel="stylesheet" type="text/css" href="../../../easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="../../../easyui/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="../../../css/tableform.css"/>
<script type="text/javascript" src="../../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<input type="hidden" id="pageSize" value="${page.pageSize}"/>
<input type="hidden" id="pageNum" value="${page.pageNum}"/>
<input type="hidden" id="pageCount" value="${page.count}"/>

<div style="text-align: center">
    <span><h1 align="center">推荐代理有好礼</h1></span>
    <span>成功邀请好友成为兜趣游戏代理，您将获得该代理购买钻石数量的10%返利。</span><br><br>
    <span>同时该代理的下级代理购买钻石，您同样可获得5%的钻石返利。</span><br><br>
    <span>返利不断，快快邀请身边的好友加入吧！</span><br><br>
    <input type="button" onclick="invitation();" value="邀请好友" style="width: 180px;height: 50px; font-size: 30px;">
    <div id="general" style="display: none">
        <textarea>${ctx}/register?c=${code}</textarea>
    </div>
</div>
<script type="text/javascript">
    function invitation() {
        debugger
//        $("#general").style.display="block";
        $("#general").css('display','block');
    }
</script>