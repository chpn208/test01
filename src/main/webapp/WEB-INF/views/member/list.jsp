<%@ page import="com.oooo.util.Constant" %>
<%@ page import="com.oooo.model.Permissions" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.66dear.cn/jsp/jstl/util" prefix="fn"%>
<link rel="stylesheet" type="text/css" href="../../../easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="../../../easyui/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="../../../css/tableform.css"/>
<script type="text/javascript" src="../../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<input type="hidden" id="pageSize" value="${page.pageSize}"/>
<input type="hidden" id="pageNum" value="${page.pageNum}"/>
<input type="hidden" id="pageCount" value="${page.count}"/>


<div style="width: 1120px">
    <div class="operation">
        <a href="" >刷新</a>
    </div>

    <table class="easyui-datagrid"style="width:1120px;height:auto">
        <thead>
        <tr>
            <th data-options="field:'id',width:50">会员id</th>
            <th data-options="field:'membername',width:80">代理商名</th>
            <th data-options="field:'memberpassword',width:100">代理商密码</th>
            <th data-options="field:'memberlevel',width:80">代理商等级</th>
            <th data-options="field:'memberdiamond',width:80">代理商钻石</th>
            <th data-options="field:'memberstatus',width:250">代理商状态</th>
            <th data-options="field:'options',width:250">操作</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${page.result}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${item.password}</td>
                    <td>
                       ${fn:permissionName(item.level)}
                    </td>
                    <td>${item.diamond}</td>
                    <td>${item.status}</td>
                    <td>
                        <a href="">删除</a>
                        <a href="javascript:preUpLevel(${item.id})">升级</a>
                        <a href="javascript:preAgentRecharge(${item.id})">充值</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</div>

<div class="datagrid-pager pagination">
    <table cellspacing="0" cellpadding="0" border="0">
        <tbody>
        <tr>
            <td><select class="pagination-page-list" onchange="changePageSize(this)">
                <option <c:if test="${page.pageSize==10}">selected="selected"</c:if>>10</option>
                <option <c:if test="${page.pageSize==20}">selected="selected"</c:if>>20</option>
                <option <c:if test="${page.pageSize==30}">selected="selected"</c:if>>30</option>
            </select></td>
            <td>
                <div class="pagination-btn-separator"></div>
            </td>
            <td><a href="javascript:changePage(0)" class="l-btn l-btn-plain l-btn-disabled" id=""><span
                    class="l-btn-left"><span class="l-btn-text"><span class="l-btn-empty pagination-first">&nbsp;</span></span></span></a>
            </td>
            <td><a href="javascript:changePage(${page.prePageNum})" class="l-btn l-btn-plain l-btn-disabled" id=""><span
                    class="l-btn-left"><span class="l-btn-text"><span class="l-btn-empty pagination-prev">&nbsp;</span></span></span></a>
            </td>
            <td>
                <div class="pagination-btn-separator"></div>
            </td>
            <td><span style="padding-left:6px;"></span></td>
            <td><input class="pagination-num" type="text" value="${page.pageNum + 1}" size="2"></td>
            <td><span style="padding-right:6px;">/${page.pageCount}</span></td>
            <td>
                <div class="pagination-btn-separator"></div>
            </td>
            <td><a href="javascript:changePage(${page.nextPageNum})" class="l-btn l-btn-plain l-btn-disabled" id=""><span
                    class="l-btn-left"><span class="l-btn-text"><span class="l-btn-empty pagination-next">&nbsp;</span></span></span></a>
            </td>
            <td><a href="javascript:changePage(${page.pageCount-1})" class="l-btn l-btn-plain l-btn-disabled" id=""><span
                    class="l-btn-left"><span class="l-btn-text"><span class="l-btn-empty pagination-last">&nbsp;</span></span></span></a>
            </td>
            <td>
                <div class="pagination-btn-separator"></div>
            </td>

        </tr>
        </tbody>
    </table>
    <div class="pagination-info">${page.startNum}-${page.endNum}共 ${page.count}条</div>
    <div style="clear:both;"></div>
    <div id="chargeDlg">

    </div>
</div>

<script type="text/javascript">
    function changePage(pageNum){
        var pageSize = $("#pageSize").val();
        window.location.href="/admin/list?pageNum="+pageNum+"&pageSize="+pageSize;
    }

    function changePageSize(obj) {
        var pageSize = obj.selectedOptions[0].value;
        window.location.href="/admin/list?pageNum="+0+"&pageSize="+pageSize;
    }

    function preUpLevel(agentId) {
        debugger
        $("#chargeDlg").dialog({
            title:"recharge",
            width:400,
            height:200,
            close:false,
            cache:false,
            href:'/agent/preUpLevel?agentId='+agentId +'&url=/admin/list'+'&pageSize='+$('#pageSize').val()+'&pageNum='+$('#pageNum').val(),
            modal:true
        });
    }
    function preAgentRecharge(agentId) {
        $("#chargeDlg").dialog({
            title:"recharge",
            width:400,
            height:200,
            close:false,
            cache:false,
            href:'/agent/preRecharge?agentId='+agentId +'&url=/admin/list'+'&pageSize='+$('#pageSize').val()+'&pageNum='+$('#pageNum').val(),
            modal:true
        });

    }
</script>