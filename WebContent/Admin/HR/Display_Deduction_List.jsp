<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="control-group">
  <label class="control-label">Deduction Type *</label>
  <div class="controls">
    <c:forEach items="${Deduction_List.deduction_id}" var="Deduction_id" varStatus="position">
 		<c:set var="Deduction_Amount" value=""/>
		<c:set var="Payroll_Month" value=""/>
    	<input type="checkbox" name="Deduction${Deduction_id}" id="Deduction${Deduction_id}" value="${Deduction_id}" onchange=
     	"if(this.checked==true)
    	{
    		if(document.getElementById('Amount${Deduction_id}')!=null)
    		{
    		    document.getElementById('Amount${Deduction_id}').disabled=false;
    		}
    		if(document.getElementById('Payroll_Month${Deduction_id}')!=null)
    		{
    		   document.getElementById('Payroll_Month${Deduction_id}').disabled=false;
    		}
    	}
    	else
    	{
    		if(document.getElementById('Amount${Deduction_id}')!=null)
    		{
    		    document.getElementById('Amount${Deduction_id}').disabled=true;
    		}
    		if(document.getElementById('Payroll_Month${Deduction_id}')!=null)
    		{
    		   document.getElementById('Payroll_Month${Deduction_id}').disabled=true;
    		}
    	}"
			<c:forEach items="${Employee_Deduction.employee_Deduction_id}" var="Employee_Deduction_id" varStatus="loop">
				<c:if test="${Employee_Deduction_id==Deduction_id}">
					Checked
					<c:set var="Deduction_Amount" value="${Employee_Deduction.amount[loop.index]}"/>
					<c:set var="Payroll_Month" value="${Employee_Deduction.payroll_Month[loop.index]}"/>
				</c:if> 
			</c:forEach>   	
    	> 
    	<c:out value='${Deduction_List.deduction_Name[position.index]}'/>
   		<c:if test="${Deduction_List.deduction_Type[position.index]=='Specific'}">
   		<input type="number" id="Amount${Deduction_id}" name="Amount${Deduction_id}" class="span11" style="width:200px;" placeholder="Enter Amount" value="${Deduction_Amount}" step="0.01" min="0" max="1000000000" required <c:if test="${Deduction_Amount==null || Deduction_Amount==''}">disabled</c:if>>
   		</c:if>
   		<c:if test="${Deduction_List.period[position.index]=='Annually'}">
          <select name="Payroll_Month${Deduction_id}" id="Payroll_Month${Deduction_id}" class="span11" style="width:200px;" required <c:if test="${Payroll_Month==null || Payroll_Month==''}">disabled</c:if>>
			 <option value=""> 
		  		Select Deduction Month
		  	 </option>  
		  	 <option value="1" <c:if test='${Payroll_Month==1}'>Selected</c:if>>January</option>
		  	 <option value="2" <c:if test='${Payroll_Month==2}'>Selected</c:if>>February</option>
		  	 <option value="3" <c:if test='${Payroll_Month==3}'>Selected</c:if>>March</option>
		  	 <option value="4" <c:if test='${Payroll_Month==4}'>Selected</c:if>>April</option>
		  	 <option value="5" <c:if test='${Payroll_Month==5}'>Selected</c:if>>May</option>
		  	 <option value="6" <c:if test='${Payroll_Month==6}'>Selected</c:if>>June</option>
		  	 <option value="7" <c:if test='${Payroll_Month==7}'>Selected</c:if>>July</option>
		  	 <option value="8" <c:if test='${Payroll_Month==8}'>Selected</c:if>>August</option>
		  	 <option value="9" <c:if test='${Payroll_Month==9}'>Selected</c:if>>September</option>
		  	 <option value="10" <c:if test='${Payroll_Month==10}'>Selected</c:if>>October</option>
		  	 <option value="11" <c:if test='${Payroll_Month==11}'>Selected</c:if>>November</option>
		  	 <option value="12" <c:if test='${Payroll_Month==12}'>Selected</c:if>>December</option>
		  </select>  
   		</c:if>
   		<br><br>
   		<input type="hidden" name="hiddenDeduction${Deduction_id}"  id="hiddenDeduction${Deduction_id}" value="${Deduction_id}">  
    </c:forEach>						
  </div>
</div>