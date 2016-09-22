<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/18
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>index</title>
    </head>
    <link rel="stylesheet" type="text/css" href="css/easy_ui/easyui.css">
    <link rel="stylesheet" type="text/css" href="css/easy_ui/icon.css">

    <script type="text/javascript" src="js/easy_ui/jquery.min.js"></script>
    <script type="text/javascript" src="js/easy_ui/jquery.easyui.min.js"></script>
    <body>
        <div class="easyui-layout" style="width: 100%; height: 100%;">
            <div data-options="region:'north'" style="height: 80px"></div>
            <div data-options="region:'south'" style="height: 80px;"></div>

            <div data-options="region:'west'" style="width: 200px;" title="west"></div>
            <div data-options="region:'center'"/>
        </div>
    </body>
</html>
