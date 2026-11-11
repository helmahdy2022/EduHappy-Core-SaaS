<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="control-group">
  <label class="control-label">Allowance Type *</label>
  <div class="controls">
    <c:forEach items="${Allowance_List.allowance_id}" var="Allowance_id" varStatus="position">
		<c:set var="Allowance_Amount" value=""/>
		<c:set var="Payroll_Month" value=""/>
    	<input type="checkbox" name="Allowance${Allowance_id}" id="Allowance${Allowance_id}" value="${Allowance_id}" onchange=
    	"if(this.checked==true)
    	{
    		if(document.getElementById('Amount${Allowance_id}')!=null)
    		{
    		    document.getElementById('Amount${Allowance_id}').disabled=false;
    		}
    		if(document.getElementById('Payroll_Month${Allowance_id}')!=null)
    		{
    		   document.getElementById('Payroll_Month${Allowance_id}').disabled=false;
    		}
    	}
    	else
    	{
    		if(document.getElementById('Amount${Allowance_id}')!=null)
    		{
    		    document.getElementById('Amount${Allowance_id}').disabled=true;
    		}
    		if(document.getElementById('Payroll_Month${Allowance_id}')!=null)
    		{
    		   document.getElementById('Payroll_Month${Allowance_id}').disabled=true;
    		}
    	}"
			<c:forEach items="${Employee_Allowance.employee_Allowance_id}" var="Employee_Allowance_id" varStatus="loop">
				<c:if test="${Employee_Allowance_id==Allowance_id}">
					Checked
					<c:set var="Allowance_Amount" value="${Employee_Allowance.amount[loop.index]}"/>
					<c:set var="Payroll_Month" value="${Employee_Allowance.payroll_Month[loop.index]}"/>
				</c:if> 
			</c:forEach>   	
    	> 
    	<c:out value='${Allowance_List.allowance_Name[position.index]}'/>
   		<c:if test="${Allowance_List.allowance_Type[position.index]=='Specific'}">
   		<input type="number" id="Amount${Allowance_id}" name="Amount${Allowance_id}" class="span11" style="width:200px;" placeholder="Enter Amount" value="${Allowance_Amount}" step="0.01" min="0" max="1000000000" required <c:if test="${Allowance_Amount==null || Allowance_Amount==''}">disabled</c:if>>
   		</c:if>
   		<c:if test="${Allowance_List.period[position.index]=='Annually'}">
          <select name="Payroll_Month${Allowance_id}" id="Payroll_Month${Allowance_id}" class="span11" style="width:200px;" required <c:if test="${Payroll_Month==null || Payroll_Month==''}">disabled</c:if>>
			 <option value=""> 
		  		Select Payment Month
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
   		<input type="hidden" name="hiddenAllowance${Allowance_id}"  id="hiddenAllowance${Allowance_id}" value="${Allowance_id}">  
    </c:forEach>						
  </div>
</div>