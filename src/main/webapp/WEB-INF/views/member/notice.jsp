<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="../../../easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="../../../easyui/themes/icon.css"/>
<link rel="stylesheet" type="text/css" href="../../../css/tableform.css"/>
<script type="text/javascript" src="../../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<input type="hidden" id="pageSize" value="${page.pageSize}"/>
<input type="hidden" id="pageNum" value="${page.pageNum}"/>
<input type="hidden" id="pageCount" value="${page.count}"/>
<div>
    <form action="" method="post">
        <div class="notice">
            类型:
            <select id="noticeType" class="em" onchange="changeNoticeType(this)">
                <c:forEach items="${notices}" var="item">
                    <option value="${item.type}">${item.name}</option>
                </c:forEach>
            </select>
            <br>
            <textarea id="noticeContent" class="em">

        </textarea>

            <input type="button" onclick="editNoticeContent()" value="修改">
        </div>
    </form>
</div>
<script type="text/javascript">
    function changeNoticeType(obj) {
//       debugger;
       var noticeType = obj.selectedOptions[0].value;
       $.ajax({
           url:"/admin/noticeContent?noticeType="+noticeType,
           type:"get",
           success:function (obj) {
               if (obj.code == 200){
                   $("#noticeContent").val(obj.msg);
               }else {
                   alert(obj.msg);
               }
           }

       })
    }

    function editNoticeContent() {
        debugger
        var noticeType = $('#noticeType').val();
        var noticeContent = $("#noticeContent").val();
        var obj = new Object();
        obj.noticeType = noticeType;
        obj.noticeContent = noticeContent;
        
        $.ajax({
            url:"/admin/editNoticeContent",
            data:obj,
            type:"post",
            success:function (obj) {
               if(obj.code == 200){
                   $("#noticeContent").val(obj.msg);
               }else {
                   alert(obj.msg);
               }
            }
        })

    }
</script>