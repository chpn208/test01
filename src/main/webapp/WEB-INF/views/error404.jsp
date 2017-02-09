<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
   <input type="hidden" id="e" value="${errorMsg}">
   <h1>error:<span style="color:red">${errorMsg}</span></h1>
   <c:if test="${e!= null}">
      ${e}
   </c:if>
</div>
<script type="text/javascript">
   /* var errorMsg = document.getElementById("e");
    alertError(errorMsg)
    function alertError(e) {
         alertError(e);
    }*/
</script>
