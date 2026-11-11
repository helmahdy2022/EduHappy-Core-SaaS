<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
    function getClientTimeZone() {
        var clientDate = new Date();
        var offset = clientDate.getTimezoneOffset();
        //var clientYear= clientDate.getYear();
        //var clientMonth= clientDate.getMonth() +1;
        //var clientDay = clientDate.getDate();
        var clientHours = clientDate.getHours();
        var clientMinutes = clientDate.getMinutes();
        
        //var clientSeconds = clientDate.getSeconds();
        //var tagDiv=document.getElementById("DivTag");
        //tagDiv.innerText = clientDay + "/" + clientMonth + "/" + clientYear + " " + clientHours + ":" + clientMinutes + ":" + clientSeconds;
        
        //window.location = "${pageContext.request.contextPath}/Get_Current_Session?timeOffset="+offset;
        
        window.location = "${pageContext.request.contextPath}/Get_Current_Session?clientHours="+clientHours+"&&clientMinutes="+clientMinutes;
    }
    
</script>
</head>

<body
<c:if test="${sessionScope['Teacher_Session']!=null}">
 onload="getClientTimeZone();"
 </c:if> 
 >

</body>


</html>