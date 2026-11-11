<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
    function getClientTimeZone() {
        var clientDate = new Date();
        var offset = clientDate.getTimezoneOffset();
        var clientHours = clientDate.getHours();
        var clientDay = clientDate.getDay();
        var clientMinutes = clientDate.getMinutes();
		//if (${sessionScope['Teacher_Session']!=null})
    		//{
			window.location = "${pageContext.request.contextPath}/Get_Teacher_Schedule?clientHours="+clientHours+"&&clientMinutes="+clientMinutes+"&&clientDay="+clientDay;
			//}   		
    }
</script>    
</head>
<body onload="getClientTimeZone();">

</body>
</html>

