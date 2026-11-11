<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




 


			  		<div class="message">
			  		${param.message}
			  		<br/>
					<a href="javascript:showSchedule(${param.Class_id}, ${param.Grade_id}, ${param.School_id})" class="flip-link btn btn-info" id="to-recover">Return to Class Schedule</a>
			  		</div>




<%-- <div style="width:700px;">
<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
<h5>Schedule Details</h5>
</div>
${param.message}
<br/>
<a href="javascript:showSchedule(${param.Class_id}, ${param.Grade_id}, ${param.School_id})"><font color="blue">Click here to return to Class Schedule</font></a> 
</div>
 --%>

