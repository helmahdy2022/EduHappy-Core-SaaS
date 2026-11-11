<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%
		int i = 0;
%>
 <c:choose>
    <c:when test="${param.n!=null}">
 		<c:forEach var="i" begin="${param.n}" end="${param.Terms}">
			<div class="widget-title" id="wcell${i}"> <span class="icon"> <i class="icon-align-justify"></i> </span>
			<h5>Term Details</h5>
			<div style="float:right;"><input type="button" name="Delete" id="Delete" class="btn btn-danger" value="X" onClick="deleteTerm(this.value,i.value, Term_Index${i}.value, School_Year_id.value)"></div>
			</div>
			<div class="widget-content nopadding" id="tcell${i}">
				<div class="control-group">
					<label class="control-label">Term Name *</label>
					<div class="controls">
						 <input name="Term_Name${i}" type="text" class="span11" id="Term_Name${i}" required  maxlength="50">
						 <input name="Term_Index${i}" type="hidden" value="${i}"/>
						 <input name="Terms_qty" id="Terms_qty" type="hidden" value="${param.Terms}"/> 
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Start Date *</label>
					<div class="controls">
						 <input name="Term_Start${i}" type="Date" id="Term_Start${i}" class="span11" required>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">End Date *</label>
					<div class="controls">  
						<input name="Term_End${i}" type="Date" id="Term_End${i}" class="span11" required> 
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Vacation Start *</label>
					<div class="controls">   
						<input name="Vacation_Start${i}" type="Date" id="Vacation_Start${i}" class="span11" required>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Vacation End *</label>
					<div class="controls">
						<input name="Vacation_End${i}" type="Date" id="Vacation_End${i}" class="span11" required>   
					</div>
				</div>
			</div>
 		</c:forEach>
 	</c:when>
 	<c:otherwise>
 		<c:if test="${param.Function==null}">
			<div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
			<h5>Year Details</h5>
			</div>
			<div class="control-group">
			  <label class="control-label">Academic Year Name *</label>
	             <div class="controls">
					<input name="Year_Name" type="text" class="span11" id="Term_Name"  required value="<c:out value='${School_Year.year_Name}'/>" maxlength="50">					
				 </div>
			</div>	
			<div class="control-group">
			  <label class="control-label">Start Date *</label>
	             <div class="controls">	
					<input name="Year_Start" type="date" id="Year_Start" class="span11" value="<c:out value='${School_Year.YS_date}'/>" required/>					
				 </div>
			</div>
			<div class="control-group">
			  <label class="control-label">End Date *</label>
	             <div class="controls">	
					<input name="Year_End" type="date" id="Year_End" class="span11" value="<c:out value='${School_Year.YE_date}'/>" required/>										
				 </div>
			</div> 
			<c:forEach items="${School_Year.year_Terms}" var="Term" varStatus="position">	
				<c:if test="${Term[0]!=null}">
					<%i=i+1;request.setAttribute("i", i);%>
					<div class="widget-title" id="wcell${i}"> <span class="icon"> <i class="icon-align-justify"></i> </span>
					<h5>Term Details</h5>
	       	 		<c:if test="${Term[6]==null}">
	       	 			<div style="float:right;"><input type="button" name="Delete" id="Delete" class="btn btn-danger" value="X"  onClick="deleteTerm(this.value,i.value, Term_Index${i}.value, School_Year_id.value, Term_id${i}.value )"></div>
	       	 		</c:if>
					</div>
					<div class="widget-content nopadding" id="tcell${i}">
						<div class="control-group">
							<label class="control-label">Term Name *</label>
							<div class="controls">								 
					        	<input name="Term_Name${i}" type="text" class="span11" id="Term_Name${i}" required value="<c:out value='${Term[1]}'/>" maxlength="50"/>
					        	<input name="Term_Index${i}" type="hidden" value="${i}">
					            <input name="Term_id${i}" type="hidden" value="${Term[0]}"/>
					            <input name="Terms_qty" id="Terms_qty" type="hidden" value="<c:out value='${param.Term_qty}'/>">								 
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Start Date *</label>
							<div class="controls">
								 <input name="Term_Start${i}" type="Date" id="Term_Start${i}" class="span11" value="${Term[2]}" required>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">End Date *</label>
							<div class="controls">  
								<input name="Term_End${i}" type="Date" id="Term_End${i}" class="span11" value="${Term[3]}" required> 
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Vacation Start *</label>
							<div class="controls">   
								<input name="Vacation_Start${i}" type="Date" id="Vacation_Start${i}" class="span11"  value="${Term[4]}" required/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Vacation End *</label>
							<div class="controls"> 
								<input name="Vacation_End${i}" type="Date" id="Vacation_End${i}" class="span11" value="${Term[5]}" required/> 
							</div>
						</div>
					</div>
				</c:if>			
			</c:forEach>		 		
		       	<div id="OneTerm" ></div>
				<div class="buttonHolder">
					<input type="button" name="Add_New" id="Add_New" value="Add Term" class="btn btn-info" onClick="showOneTerm(this.value,i.value)">
					<input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success">
					<input name="i" id="i" type="hidden" value="${i}"/>
				</div>
 		</c:if>
 	</c:otherwise>
</c:choose>
