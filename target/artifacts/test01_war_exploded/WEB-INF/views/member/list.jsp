<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

    </div>
    <table class="easyui-datagrid"style="width:1120px;height:auto">
        <thead>
        <tr>
            <th data-options="field:'membername',width:80">会员名</th>
            <th data-options="field:'memberpassword',width:100">会员密码</th>
            <th data-options="field:'memberlevel',width:80">会员等级</th>
            <th data-options="field:'memberdiamond',width:80">会员钻石</th>
            <th data-options="field:'memberstatus',width:250">会员状态</th>
            <th data-options="field:'options',width:250">操作</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${page.result}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.password}</td>
                    <td>${item.level}</td>
                   <%-- <td>${item.memberdiamond}</td>--%>
                    <td></td>
                    <td>${item.status}</td>
                    <td><a href="">删除</a> <a href="">修改</a> </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</div>

<div class="datagrid-pager pagination">
    <table cellspacing="0" cellpadding="0" border="0">
        <tbody>
        <tr>
            <td><select class="pagination-page-list">
                <option <c:if test="${page.pageSize==10}">selected="selected"</c:if>>10</option>
                <option <c:if test="${page.pageSize==20}">selected="selected"</c:if>>20</option>
                <option <c:if test="${page.pageSize==30}">selected="selected"</c:if>>30</option>
            </select></td>
            <td>
                <div class="pagination-btn-separator"></div>
            </td>
            <td><a href="javascript:void(0)" class="l-btn l-btn-plain l-btn-disabled" id=""><span
                    class="l-btn-left"><span class="l-btn-text"><span class="l-btn-empty pagination-first">&nbsp;</span></span></span></a>
            </td>
            <td><a href="javascript:void(0)" class="l-btn l-btn-plain l-btn-disabled" id=""><span
                    class="l-btn-left"><span class="l-btn-text"><span class="l-btn-empty pagination-prev">&nbsp;</span></span></span></a>
            </td>
            <td>
                <div class="pagination-btn-separator"></div>
            </td>
            <td><span style="padding-left:6px;"></span></td>
            <td><input class="pagination-num" type="text" value="${page.pageNum}" size="2"></td>
            <td><span style="padding-right:6px;">/${page.pageCount}</span></td>
            <td>
                <div class="pagination-btn-separator"></div>
            </td>
            <td><a href="javascript:void(0)" class="l-btn l-btn-plain l-btn-disabled" id=""><span
                    class="l-btn-left"><span class="l-btn-text"><span class="l-btn-empty pagination-next">&nbsp;</span></span></span></a>
            </td>
            <td><a href="javascript:void(0)" class="l-btn l-btn-plain l-btn-disabled" id=""><span
                    class="l-btn-left"><span class="l-btn-text"><span class="l-btn-empty pagination-last">&nbsp;</span></span></span></a>
            </td>
            <td>
                <div class="pagination-btn-separator"></div>
            </td>
            <td><a href="javascript:pageChange()" class="l-btn l-btn-plain" id=""><span class="l-btn-left"><span
                    class="l-btn-text"><span class="l-btn-empty pagination-load">&nbsp;</span></span></span></a></td>
        </tr>
        </tbody>
    </table>
    <div class="pagination-info">1-27共 ${page.count}条</div>
    <div style="clear:both;"></div>
</div>

<script type="text/javascript">
    function pageChange(){
        window.location.href="/member/list?pageNum=5"
    }
</script>