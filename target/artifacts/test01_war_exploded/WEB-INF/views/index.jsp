
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>index</title>
</head>

<link rel="stylesheet" type="text/css" href="../../css/tableform.css"/>
<link rel="stylesheet" type="text/css" href="../../easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="../../easyui/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="../../css/ace.min.css"/>
<script type="text/javascript" src="../../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../../js/menu.js"></script>
<body>
<div class="easyui-layout" style="width: 100%; height: 100%;">
    <div data-options="region:'west'" style="width: 200px;" title="menu">
        <%--<div style="margin:0px 0;"></div>--%>
        <%-- <div class="easyui-panel" style="padding:5px;height: 100px" >--%>
        <%-- <ul class="easyui-tree" style="height: 100px;">
             <li>

                &lt;%&ndash; <c:forEach items="${menus}" var="item">
                     <c:choose>
                         <c:when test="${item.menuLevel == 1}">
                             <span>${item.name}</span>
                             <ul>
                                 <c:forEach items="${item.children}" var="item">
                                       <li>
                                           <ul onclick="addTab('${item.name}','${item.url}')">${item.name}</ul>
                                         </li>
                                 </c:forEach>

                             </ul>
                         </c:when>

                     </c:choose>
                 </c:forEach>&ndash;%&gt;

             </li>

         </ul>--%>
        <div class="easyui-accordion" style="width:auto;height:300px;">
            <c:forEach items="${menus}" var="item">
                <c:if test="${item.menuLevel == 1}">
                    <div title="${item.name}" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
                        <ul class="nav nav-list user-menu pull-left dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                           <c:forEach items="${item.children}" var="item">
                               <li class="hover">
                               <a href="javascript:addTab('${item.name}','${item.url}')">${item.name}</a>
                               </li>

                           </c:forEach>
                        </ul>
                    </div>
                </c:if>
            </c:forEach>
        </div>


        <%--  </div>--%>
    </div>
    <div data-options="region:'north'" style="height: 40px;background:#438eb9">
        <div class="title">
        逗趣运营管理系统
        </div>
    </div>

    <div data-options="region:'south'" style="height: 80px;"></div>
    <div data-options="region:'center'">
        <div id="tt" class="easyui-tabs" style="width: 1125px; height: auto;"></div>
    </div>
</div>
</body>
</html>
