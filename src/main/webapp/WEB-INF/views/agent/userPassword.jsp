<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="../../../js/Validform.js"></script>
<div>
    <table class="formtable">
        <tbody>
        <tr>
            <td align="right" id="passwordLable" style="width: 60px;"><span class="filedzt">原密码:</span></td>
            <td class="value"><input type="password" value="" id="oldpassword" name="password" class="inputxt Validform_error"
                                     datatype="*" maxlength="18" placeholder="请输入原密码" sucmsg=" " nullmsg="请填写信息！" onblur="checkInput(this)">
                <span id="validate_oldpassword"></span>
            </td>
        </tr>
        <tr>
            <td align="right"><span class="filedzt">新密码:</span></td>
            <td class="value"><input type="password" value="" id="password" name="newpassword" class="inputxt Validform_error" plugin="passwordStrength" datatype="*6-18"
                                     errormsg="密码至少6个字符,最多18个字符！" placeholder="请输入新密码" nullmsg="请填写信息！" onblur="checkInput(this)">
                <span id="validate_password"></span>
            </td>
        </tr>
        <tr>
            <td align="right"><span class="filedzt">重复密码:</span></td>
            <td class="value"><input id="repassword" type="password" recheck="newpassword" class="inputxt" datatype="*6-18" placeholder="请重复一次密码" errormsg="两次输入的密码不一致！"
                                     onblur="checkInput(this)">
                <span id="validate_repassword"></span>
                </td>
        </tr>
        </tbody>
    </table>
    <input type="button" value="确定" onclick="updatePassword()"/>
</div>
<script type="text/javascript">
    function updatePassword() {
        debugger
        var oldPassWord = $("#oldpassword").val();
        var password = $("#password").val();
        var repassword =$("#repassword").val();
        if ("" == oldPassWord){
            alert("请输入原密码");
            return;
        }
        if ("" == password){
            alert("请输入新密码");
            return
        }
        if ("" == repassword){
            alert("请重复新密码")
            return;
        }
        if (password != repassword){
            alert("两次输入的密码不一致");
            return;
        }
        var data = new Object();
        data.oldpassword = oldPassWord;
        data.password = password;
        data.repassword = repassword;
        $.ajax({
            url:"/agent/updatePassword",
            data:data,
            type:"GET",
            success:function (obj) {
                if(obj.code == 200){
                    alert("修改成功")
                }
                else {
                    alert(obj.msg);
                }
            }
        })
    }
</script>
