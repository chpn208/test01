
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" href="easyui/jquery.min.js"></script>
<script type="text/javascript" href="easyui/jquery.easyui.min.js"></script>
<body>
<div style="background:#EDF4E6;padding:5px;width:300px;border:1px solid #ccc;">
    <a href="javascript:void(0)" id="sb1" icon="icon-edit" onclick="javascript:alert('edit')">Edit</a>
    <a href="javascript:void(0)" id="sb2" icon="icon-ok" onclick="javascript:alert('ok')">Ok</a>
    <a href="javascript:void(0)" id="mb3" icon="icon-help">Help</a>
    <div id="mm1" style="width:150px;">
        <div icon="icon-undo">Undo</div>
        <div icon="icon-redo">Redo</div>
        <div class="menu-sep"></div>
        <div>Cut</div>
        <div>Copy</div>
        <div>Paste</div>
        <div class="menu-sep"></div>
        <div>
            <span>工具栏</span>
            <div style="width:150px;">
                <div>地址</div>
                <div>链接</div>
                <div>导航工具栏</div>
                <div>书签工具栏</div>
                <div class="menu-sep"></div>
                <div>新建工具栏...</div>
            </div>
        </div>
        <div icon="icon-remove">Delete</div>
        <div>Select All</div>
    </div>
    <div id="mm2" style="width:100px;">
        <div icon="icon-ok">Ok</div>
        <div icon="icon-cancel">Cancel</div>
    </div>
    <div id="mm3" style="width:150px;">
        <div>Help3</div>
        <div class="menu-sep"></div>
        <div>About3</div>
    </div>
</div>
</body>
<script type="text/javascript">
    $('#sb1').splitbutton({menu:'#mm1'});
    $('#sb2').splitbutton({menu:'#mm2'});
    $('#mb3').menubutton({menu:'#mm3'});
</script>
</html>
