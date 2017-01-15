<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}" />
<html>
<head>
    <title>代理注册</title>
</head>
<script type="text/javascript" src="${ctx}/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/area.js"></script>
<script type="text/javascript" src="${ctx}/js/Validform.js"></script>
<script type="text/javascript" src="${ctx}/js/passwordStrength-min.js"></script>
<script type="text/javascript" src="${ctx}/js/Validform_Datatype_zh-cn.js"></script>
<link id="easyuiTheme" rel="stylesheet" href="${ctx}/easyui/themes/metro/easyui.css"/>
<link rel="stylesheet" href="${ctx}/css/tableform.css"/>
<link rel="stylesheet" href="${ctx}/css/layout.css"/>
    <form id="formobj" action="/agentAdd.do" name="formobj" method="post">
        <input type="hidden" id="id" name="id" value="${id}"/>
        <input type="hidden" id="upAgent" name="upAgent" value="${upAgent}">
        <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable" align="center">
            <tbody>
                <tr>
                    <td colspan="2" align="center">
                        <br>
                        <br>
                        <label style="font-size: 35px;">
                            代理注册
                        </label>
                        <br>
                        <br>
                        逗趣地方棋牌欢迎您
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <label class="Validform_label">
                            姓名:
                        </label>
                    </td>
                    <td class="value">
                        <input class="inputxt" id="titleName" name="titleName" placeholder="请输入您的真实姓名" errormsg="请输入1-5个中文字符" value="" datatype="zh,s1-5" onblur="checkInput(this)">
                        <span id="validate_titleName" class="validate_titleName Validform_wrong Validform_label"></span>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <label class="Validform_label">
                            微信号:
                        </label>
                    </td>
                    <td class="value">
                        <input class="inputxt" id="weixinCode" name="weixinCode" placeholder="请输入微信号" value="" datatype="*1-60" onblur="checkInput(this)">
                        <span id="validate_weixinCode" class="validate_weixinCode Validform_wrong Validform_label"></span>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <label class="Validform_label">
                            手机号:
                        </label>
                    </td>
                    <td class="value">
                        <input class="inputxt Validform_error" id="mobilePhone" name="mobilePhone" placeholder="请输入手机号码" value="" datatype="m"
                               maxlength="11" nullmsg="请填写手机号！" onblur="checkInput(this)">
                        <span id="validate_mobilePhone" class="validate_mobilePhone Validform_wrong"></span>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <label class="Validform_label ">
                            省份:
                        </label>
                    </td>
                    <td class="value">
                        <select id="s_province" name="s_province"></select>
                       <%-- <span class="Validform_checktip"></span>--%>
                    </td>
                </tr>
                <tr>

                    <td align="right">
                        <label class="Validform_label">
                            地市:
                        </label>
                    </td>
                    <td class="value">
                       <input type="hidden" class="combo-value" value="--请选择--"></span>
                       <%-- <input id="cityCode" name="cityCode" type="hidden" datatype="n4-4" errormsg="请选择地市">--%>
                        <select id="s_city" name="s_city" ></select>
                        <%--<span class="Validform_checktip"></span>--%>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <label class="Validform_label">
                            区县:
                        </label>
                    </td>
                    <td class="value">
                      <input type="hidden" class="combo-value" value="--请选择--"></span>
                        <%--<input id="regionCode" name="regionCode" type="hidden" datatype="n6-6" errormsg="请选择区县">--%>
                        <select id="s_county" name="s_county"></select>
                        <%--<span class="Validform_checktip"></span>--%>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <label class="Validform_label">
                            详细地址:
                        </label>
                    </td>
                    <td class="value">
                        <input class="inputxt" id="address" name="address" ignore="ignore" value="" datatype="s2-32">
                        <span class="Validform_checktip"></span>
                    </td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 密码: </label></td>
                    <td class="value">
                        <input id="password" name="password" type="password" class="inputxt" placeholder="请输入密码" value="" plugin="passwordStrength" onblur="checkInput(this)" datatype="*6-18">
                        <span class="passwordStrength" maxlength="18" style="display: none;">
	                        <span>弱</span>
	                        <span>中</span>
	                        <span class="last">强</span>
	                    </span>
                        <span id="validate_password" class="Validform_checktip validate_password Validform_wrong"> </span>
                    </td>
                </tr>
                <tr>
                    <td align="right"><label class="Validform_label"> 重复密码: </label></td>
                    <td class="value">
                        <input id="repassword" name="repassword" type="password" class="inputxt Validform_error" value="" recheck="password" placeholder="请重复密码" maxlength="18" datatype="*6-18" onblur="checkInput(this)" errormsg="两次输入的密码不一致！" nullmsg="请重复密码！">
                        <span id="validate_repassword" class="validate_repassword,Validform_wrong"></span>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                        <label class="Validform_label">
                            验证码:
                        </label>
                    </td>
                    <td class="value">
                        <input type="text" style="width:150px;" id="randCode" name="randCode" errormsg="请输入正确的验证码！"
                               ajaxurl="validate.do" nullmsg="请填写验证码！" class="Validform_error" onblur="checkInput(this)">
                        <img id="randCodeImage" src="${ctx}/validate.do" style="margin-bottom: -12px;" onclick="reloadRandCodeImage();">
                        <span id="validate_randCode" class="Validform_checktip Validform_wrong">请填写验证码！</span></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="button" value="提交" style="font-size: 30px;" onclick="docLocation();">
                    </td>
                </tr>
            </tbody>
        </table>

    </form>
<script type="text/javascript">
    _init_area();
</script>

<script type="text/javascript">
    var Gid  = document.getElementById ;
    var showArea = function(){
        Gid('show').innerHTML = "<h3>省" + Gid('s_province').value + " - 市" +
            Gid('s_city').value + " - 县/区" +
            Gid('s_county').value + "</h3>"
    }
    /*Gid('s_county').setAttribute('onchange','showArea()');*/
</script>
<script type="text/javascript">
    $(function () {


    });
    /**
     * 刷新验证码
     */
    function reloadRandCodeImage() {
        var date = new Date();
        var img = document.getElementById("randCodeImage");
        img.src="${ctx}/validate.do?a="+date;
    }

    function docLocation(){
        debugger

        var upAgent = $("#upAgent").val();
        var titleName = $("#titleName").val();
        var weixinCode = $("#weixinCode").val();
        var mobilePhone = $("#mobilePhone").val();
        var provinceCode = $("#s_province").val();
        var cityCode = $("#s_city").val();
        var regionCode = $("#s_county").val();
        var address = $("#address").val();
        var password = $("#password").val();
        var randCodeImage = $("#randCode").val();

        if ("" == titleName){
            alert("请输入用户名");
            return;
        }
        if ("" == weixinCode){
            alert("请输入微信号");
            return
        }
        if ("" == mobilePhone){
            alert("请输入手机号");
            return;
        }
        if ("" == provinceCode){
            alert("请输入省份")
            return
        }
        if ("" == cityCode){
            alert("请输入城市");
            return
        }
        if ("" == regionCode){
            alert("请输入县区");
            return;
        }
        if ("" == password){
            alert("请输入密码");
            return;
        }
        if ("" == repassword){
            alert("请重新输入密码")
            return
        }
        if ("" == randCodeImage){
            alert("请输入验证码")
            return
        }else {
             var dataCode = $("#randCode").val();
            $.ajax({
                url: "/checkCode?validateCode=" + dataCode,
                success: function (obj) {
                    if (obj != 200) {
                        $("#validate_randCode").html("请输入正确的验证码")
                        return ;
                    } else {
                        //$("#validate_randCode").html("")
                        //return ;
                    }
                },
                error: function (obj) {
                     $("#validate_randCode").html("请输入正确的验证码")
                    return ;
                }
            })
        }



        /*var actionURL = "/agentAdd.do?"
                +"&upAgent="+upAgent
                +"&titleName="+titleName
                +"&weixinCode="+weixinCode
                +"&mobilePhone="+mobilePhone
                +"&provinceCode="+provinceCode
                +"&cityCode="+cityCode
                +"&regionCode="+regionCode
                +"&address="+address
                +"&password="+password
                +"&randCodeImage="+randCodeImage
            ;
//        $("#formobj").Validform({ajaxPost:false}); //初始化参数，设置非ajax提交方式，页面跳转
        //$("#formobj").attr("action", encodeURI(encodeURI(actionURL)));
        //$('form').submit();
        window.location.href=encodeURI(encodeURI(actionURL))*/
        $.ajax({
            url:    "/agentAdd.do",
            data:   $('form').serialize(),
            type: 'post',
            success:function (obj) {
                if (obj.code == 200){
                    alert("你的帐号是"+obj.result);
                    window.location.href = "/home/login?userName="+obj.msg;
                }else {
                    alert(obj.info);
                }
            }
        })
    }
</script>

</html>
