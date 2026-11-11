<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function getClientTimeZone() 
    {
        var clientDate = new Date();
        var offset = clientDate.getTimezoneOffset();
        var clientHours = clientDate.getHours();
        var clientMinutes = clientDate.getMinutes();
        var clientDay = clientDate.getDay();
        var url_string = window.location.href;
        var url = new URL(url_string);
        var Student_id = url.searchParams.get("Student_id");
		if(Student_id==null)
    		{
			window.location = "${pageContext.request.contextPath}/Get_Student_Schedule?clientHours="+clientHours+"&&clientMinutes="+clientMinutes+"&&clientDay="+clientDay;
			}
    	else
    		{
    		window.location = "${pageContext.request.contextPath}/Get_Student_Schedule?Student_id=${param.Student_id}&&clientHours="+clientHours+"&&clientMinutes="+clientMinutes+"&&clientDay="+clientDay;
    		}
    		
    }
</script>    
</head>
<body onload="getClientTimeZone();">

</body>
</html>
