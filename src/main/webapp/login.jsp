
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}" />
<html>
<head>
    <title>逗趣运营管理系统</title>
</head>
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/ace.min.css"/>
<body class="login-layout light-login">
    <div class="main-container">
        <div class="main-content">
            <div class="row">
                <div class="col-sm-10 col-sm-offset-1">
                    <div class="login-container">
                        <div class="center">
                            <h2 id="id-text2" class="grey" style="display: none">
                                <i class="ace-icon fa fa-leaf green"></i>
                                逗趣运营管理系统
                            </h2>
                            <h2 class="blue" id="id-company-text">逗趣运营管理系统</h2>
                        </div>
                        <div class="space-6"></div>
                        <div class="position-relative">
                            <div id="login-box" class="login-box visible widget-box no-border">
                                <div class="widget-body">
                                    <form id="loinForm" class="form-horizontal" <%--check="/home/login"--%> role="form" action="/home/login" method="post">
                                        <div class="widget-main">
                                            <div class="alert alert-warning alert-dismissible" role="alert" id="errMsgContiner" style="display: none">
                                                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
                                                <div id="showErrMsg"></div>
                                            </div>
                                            <h4 class="header blue lighter bigger">
                                                <i class="ace-icon fa fa-coffee green"></i>
                                                用户登录
                                            </h4>
                                            <div class="space-6"></div>
                                            <label class="block clearfix">
								<span class="block input-icon input-icon-right">
									<input type="text" name="userName" class="form-control" placeholder="请输入用户名" id="userName" nullmsg="请输入用户名!" title="用户名">
									<i class="ace-icon fa fa-user"></i>
								</span>
                                            </label>
                                            <label class="block clearfix">
								<span class="block input-icon input-icon-right">
									<input type="password" name="password" class="form-control" placeholder="请输入密码" id="password" nullmsg="请输入密码!" title="密码">
									<i class="ace-icon fa fa-lock"></i>
								</span>
                                            </label>

                                            <div class="space"></div>
                                            <div class="clearfix">

                                                <button type="submit" id="but_login" onclick="checkUser()" class="width-35 pull-right btn btn-sm btn-primary">
                                                    <i class="ace-icon fa fa-key"></i>
                                                    <span class="bigger-110">登录</span>
                                                </button>
                                            </div>
                                            <div class="space-4"></div>

                                        </div>
                                       <%-- <div class="toolbar clearfix" style="display: none">
                                            <div style="float: right">
                                                <a href="#" class="forgot-password-link">
                                                    语言
                                                    <i class="ace-icon fa fa-arrow-right"></i>
                                                    <select name="langCode" style="padding:2px; width:80px;" id="langCode"> <option value="">---请选择--- </option> <option value="en">English </option> <option value="zh-cn" selected="selected">中文 </option></select>
                                                </a>
                                            </div>
                                        </div>--%>
                                    </form>
                                </div>
                            </div>
                            <div class="center"><h4 class="blue" id="id-company-text">© 逗趣科技版权所有</h4></div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
  <%--  <form action="/home/login" method="post">
        <input id="username" name="username" type="text" placeholder="username"/>
        <input id="password" name="password" type="password" placeholder="password"/>
        <input type="submit" value="submit"/>
    </form>--%>
</body>
</html>
