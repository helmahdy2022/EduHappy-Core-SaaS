<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%int i = 0;%>
<c:choose>
 <c:when test="${param.Existing_Terms ne false}">
<c:choose>
    <c:when test="${param.n!=null}">
 		<c:forEach var="i" begin="${param.n}" end="${param.Term_qty}">
			<div class="widget-title" id="wcell${i}"> <span class="icon"> <i class="icon-align-justify"></i> </span>
			<h5>Term Details</h5>
			<div style="float:right;">
				<input type="button" name="Delete" id="Delete" class="btn btn-danger" value="X"  style="height:36px;" onClick="deleteTerm(this.value,${i}, Term_Index${i}.value, School_Year_id.value, '${param.Grade_id}')">
			</div>
			</div>
			<div class="widget-content nopadding" id="tcell${i}">
				<div class="control-group">
					<label class="control-label">Term Name *</label>
					<div class="controls">
						<input name="Term_Name${i}" type="text" class="span11" required id="Term_Name"${i} maxlength="50">
			            <input name="Term_Index${i}" type="hidden" value="${i}"/>
			            <input name="Terms_qty" id="Terms_qty" type="hidden" value="<c:out value='${param.Term_qty }'/>"> 
			            <input name="Grade_id" id="Grade_id" type="hidden" value="${param.Grade_id}">	
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
		<c:if test="${param.Term_qty!=0 && param.n==1}">		
			<div class="buttonHolder">
				<input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success">
			</div> 
		</c:if>
 	</c:when>
 	<c:otherwise>
 		<c:if test="${param.Function==null}">
			<c:if test="${not empty Grade_Terms}">

		         <c:forEach items="${Grade_Terms.grade_Terms_List}" var="Term" varStatus="position">

					<c:if test="${Term[0]!=null}">
						<%								
							i=i+1;	
							request.setAttribute("i", i);
						%>
					<div class="widget-title" id="wcell${i}"> <span class="icon"> <i class="icon-align-justify"></i> </span>
					<h5>Term Details</h5>
	       	 		<c:if test="${Term[6]==null}">
	       	 			<div style="float:right;">
							<input type="button" name="Delete" id="Delete"  class="btn btn-danger" value="X" style="height:36px;" onClick="deleteTerm(this.value,${i}, Term_Index${i}.value, School_Year_id.value, '${param.Grade_id}', Grade_Term_id${i}.value)">
	       	 			</div>
	       	 		</c:if>
					</div>
					<div class="widget-content nopadding" id="tcell${i}">
						<div class="control-group">
							<label class="control-label">Term Name *</label>
							<div class="controls">															 
					        	<input name="Term_Name${i}" type="text" class="span11" id="Term_Name"${i} required value="<c:out value='${Term[1]}'/>" maxlength="50"/>
					            <input name="Term_Index${i}" type="hidden" value="${i}">
					            <input name="Grade_id" type="hidden" value="${param.Grade_id}"/>
					            <input name="Grade_Term_id${i}" type="hidden" value="<c:out value='${Term[0]}'/>"/>
					            <input name="Terms_qty" id="Terms_qty" type="hidden" value="<c:out value='${param.Term_qty}'/>">	
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Start Date *</label>
							<div class="controls">
								 <input name="Term_Start${i}" type="Date" id="Term_Start${i}" class="span11" value="<c:out value='${Term[2]}'/>" required>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">End Date *</label>
							<div class="controls">   
								<input name="Term_End${i}" type="Date" id="Term_End${i}" class="span11" value="<c:out value='${Term[3]}'/>" required>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Vacation Start *</label>
							<div class="controls">   
								<input name="Vacation_Start${i}" type="Date" id="Vacation_Start${i}" class="span11" value="<c:out value='${Term[4]}'/>" required/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Vacation End *</label>
							<div class="controls"> 
								<input name="Vacation_End${i}" type="Date" id="Vacation_End${i}" class="span11" value="<c:out value='${Term[5]}'/>" required/>
							</div>
						</div>
					</div>
					</c:if>
				</c:forEach>
			</c:if>
		</c:if>
		       	<div id="OneTerm" ></div>
				<div class="buttonHolder">
					<input type="button" name="Add_New" id="Add_New" value="Add Term" class="btn btn-info" onClick="showOneTerm(this.value,i.value, '${param.School_Year_id}', '${param.Grade_id}')">
					<input type="submit" name="Submit" id="Submit" value="Save" class="btn btn-success">
					<input name="i" id="i" type="hidden" value="${i}"/>
				</div>
 	</c:otherwise>
</c:choose> 
</c:when>
<c:otherwise>
	<div class="control-group">
		<label class="control-label">Number of Grade terms</label>
		<div class="controls">
			<input name="terms" type="number" class="span11" id="terms" min="1" onkeyup="showTerms(this.value,'${param.Grade_id}')" onChange="showTerms(this.value,'${param.Grade_id}')">	
		</div>
	</div>
</c:otherwise>
</c:choose>

