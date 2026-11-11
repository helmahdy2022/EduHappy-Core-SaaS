<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%
		int i = 0;
		int n=0;
		int Holidays=0;
		String Delete = request.getParameter("Function");
		request.setAttribute("Delete", Delete);
		String Holiday_qty = request.getParameter("Holiday_qty");
		request.setAttribute("Holiday_qty", Holiday_qty);
		
		String No_Existing_Holidays = request.getParameter("No_Existing_Holidays");
		request.setAttribute("No_Existing_Holidays", No_Existing_Holidays);
		
		
		if (request.getParameter("n")!=null)
		{
			n = Integer.parseInt(request.getParameter("n"));
			request.setAttribute("n", n);
		}
		if (request.getParameter("Holidays")!=null)
		{
			Holidays = Integer.parseInt(request.getParameter("Holidays"));
			request.setAttribute("Holidays", Holidays);
		}
%>

		 <c:choose>
			<c:when test="${n!=null || No_Existing_Holidays == 1}">
			    <c:if test="${No_Existing_Holidays == 1}">
					<div class="control-group">
						<label class="control-label">Number of Holidays</label>
						<div class="controls">
							<input name="Holidays_no" type="number" class="span11" id="Holidays_no" min="1" onkeyup="showHolidays(this.value, Country_id.value)" onchange="showHolidays(this.value, Country_id.value)"/>
						</div>
					</div>		
    			</c:if>
 				<c:forEach var="i" begin="${n}" end="${Holidays}">

					<div class="widget-title" id="wcell${i}"> <span class="icon"> <i class="icon-align-justify"></i> </span>
					<h5>Holiday Details</h5>
					<div style="float:right;">
			            <input type="button" name="Delete" id="Delete" class="btn btn-danger" value="X" style="height:36px;" onClick="deleteHoliday(this.value,${i}, Holiday_Index${i}.value, Country_id.value)">
					</div>
					</div>
					<div class="widget-content nopadding" id="cell${i}">
						<div class="control-group">
							<label class="control-label">Holiday Name *</label>
							<div class="controls">
								<input name="Holiday_Name${i}" type="text" class="span11" id="Holiday_Name"${i} required maxlength="100">
					        	<input name="Holiday_Index${i}" type="hidden" value="${i}">
					            <input name="Holidays_qty" type="hidden" value="${Holidays}"> 
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Start Date *</label>
							<div class="controls">
							 	 <input name="Holiday_Start${i}" type="Date" id="Holiday_Start${i}" class="span11" required>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">End Date *</label>
							<div class="controls">  
								<input name="Holiday_End${i}" type="Date" id="Holiday_End${i}" class="span11" required> 
							</div>
						</div>
					</div>
			 </c:forEach>
			 		<c:if test="${No_Existing_Holidays == 1 && Holidays!=0}">
						<div class="buttonHolder">
							<input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success">
						</div> 
				 	</c:if>
			</c:when>
			<c:otherwise>
				<c:if test="${Delete==null}">

		         <c:forEach items="${Holidays_Details.existingHolidays}" var="Holiday" varStatus="position">
					<c:if test="${Holiday[0]!=null}">
						<%								
							i=i+1;	
							request.setAttribute("i", i);
						%>
					<div class="widget-title" id="wcell${i}"> <span class="icon"> <i class="icon-align-justify"></i> </span>
					<h5>Holiday Details</h5>
	       	 			<div style="float:right;">
							<input type="button" name="Delete" id="Delete"  class="btn btn-danger" value="X" style="height:36px;" onClick="deleteHoliday(this.value,${i}, Holiday_Index${i}.value, Country_id.value, Holiday_id${i}.value)">
	       	 			</div>
					</div>
					<div class="widget-content nopadding" id="cell${i}">
						<div class="control-group">
							<label class="control-label">Holiday Name *</label>
							<div class="controls">															 	
						  		<input name="Holiday_Name${i}" type="text" class="span11" id="Holiday_Name"${i} value="<c:out value='${Holiday[1]}'/>" required maxlength="100">
					            <input name="Holiday_Index${i}" type="hidden" value="${i}">
					            <input name="Holiday_id${i}" type="hidden" value="${Holiday[0]}">
					            <input name="Holidays_qty" id="Holidays_qty" type="hidden" value="${Holiday_qty}">	
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Start Date *</label>
							<div class="controls">
								 <input name="Holiday_Start${i}" type="Date" id="Holiday_Start${i}" class="span11" value="<c:out value='${Holiday[2]}'/>" required>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">End Date *</label>
							<div class="controls">   
								<input name="Holiday_End${i}" type="Date" id="Holiday_End${i}" class="span11" value="<c:out value='${Holiday[3]}'/>" required>
							</div>
						</div>
					</div>
	  			</c:if>
					</c:forEach>
  				<div id="OneHoliday" ></div>
				<input name="i" id="i" type="hidden" value="${i}"/>
				<div class="buttonHolder">
	       	 	<input type="button" name="Add_New" id="Add_New" value="Add Holiday"  class="btn btn-info" onClick="showOneHoliday(this.value,i.value)">
	       	 	<input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success">
	       	 	</div>
	       	 	 <div class="requiredSignNote">Fields marked (*) are required</div>
				</c:if>			
			</c:otherwise>
		</c:choose> 
