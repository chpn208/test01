
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
    <div data-options="region:'north'" style="height: 47px;background:#438eb9">
        <div class="title navbar">
      <%--  逗趣运营管理系统--%>
          <%--  <div class="navbar-header pull-right" role="navigation">
                <ul class="nav ace-nav" style="width: 130px">
                    <li class="light-blue" id="topUser" style="width: 130px">
                        <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                            <img class="nav-user-photo" src="../../img/icon/user1.png" alt="Jason's Photo">
                            <span class="user-info">
									<small style="font-size: 10px">50109</small>
				                    <span style="color: #666633; font-size: 10px;">代理</span>
								</span>
                            <i class="icon-caret-down"></i>
                        </a>

                        <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret" id="user-menu" style="background-color: white;width: 130px; float: left;margin-left: 0px">
                            <li>
                                <a href="javascript:openUserInfo();">
                                    <i class="icon-user"></i>
                                    个人信息
                                </a>
                            </li>
                            <li>
                                <a href="javascript:openPasswordInfo()">
                                    <i class="icon-cog"></i>
                                    修改密码
                                </a>
                            </li>

                            <li class="divider"></li>
                            <li>
                                <a href="javascript:logout()">
                                    <i class="icon-off"></i>
                                    注销
                                </a>
                            </li>
                        </ul>
                </ul>
            </div>--%>

          <div id="dlg">

          </div>

          <div id="user">
              <a href="javascript:void(0)" id="sb1"  >
                  <img src="../../img/icon/user1.png">

              </a>
               <span style="font-size: 10px">${sessionScope.userName}</span>
              <!-- <a href="javascript:void(0)" id="sb2" icon="icon-ok" onclick="javascript:alert('ok')">Ok</a>
               <a href="javascript:void(0)" id="mb3" icon="icon-help">Help</a>-->
              <div id="mm1" style="width:150px;">
                  <div class="icon-user" onclick="openUserInfo()">个人信息</div>
                  <div class="icon-cog" onclick="openPasswordInfo()">修改密码</div>
                  <div class="menu-sep" class="icon-off"></div>
                  <div onclick="logout()">注销</div>

              </div>
              <!--<div id="mm2" style="width:100px;">
                  <div icon="icon-ok">Ok</div>
                  <div icon="icon-cancel">Cancel</div>
              </div>
              <div id="mm3" style="width:150px;">
                  <div>Help3</div>
                  <div class="menu-sep"></div>
                  <div>About3</div>
              </div>-->
          </div>
        </div>
    </div>

    <div data-options="region:'south'" style="height: 80px;"></div>
    <div data-options="region:'center'">
        <div id="tt" class="easyui-tabs" style="width: 1125px; height: auto;"></div>
    </div>
</div>
</body>
<script type="text/javascript">
    function openUserInfo() {
        $("#dlg").dialog({
            title:"用户信息",
            width:400,
            height:250,
            close:false,
            cache:false,
            href:'/agent/userInfo',
            modal:true
        });

    }
    function openPasswordInfo() {
        $("#dlg").dialog({
            title:"修改密码",
            width:400,
            height:200,
            close:false,
            cache:false,
            href:'/agent/userPasswordInfo',
            modal:true
        });

    }
    function logout(){
        window.location.href="/agent/logout"
    }


    $('#sb1').splitbutton({menu:'#mm1'});

</script>
</html>
