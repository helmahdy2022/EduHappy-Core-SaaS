<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
           <!-- Input Fields -->
            <div class="panel">
              <div class="panel-heading">
                <span class="fa fa-tags"></span> Assignment Category Details
              </div>
              <div class="panel-body">
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Assignment Category Name *</label>
                    <div class="col-lg-8">
                      <div class="bs-component">
					    <input type="text" name="Category_Name" id="Category_Name" class="form-control" required maxlength="100">					                       
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Category Weight *</label>
                    <div class="col-lg-8">
                      <div class="bs-component"> 
    					<input type="number" name="Category_Grade" id="Category_Grade" class="form-control" required  min="0" max="1000000000">						                    
                      </div>
                    </div>
                  </div>                                        
                  <div class="form-group">
                    <label class="col-lg-3 control-label"></label>
                    <div class="col-lg-8">
                      <div class="bs-component"> 					                    		     
					    <div class="checkbox-custom mb5">
						     <input name="Contribute" type="checkbox" id="Contribute" Checked>
							<label for="Contribute">Contribute to the total final grade</label>					    
						 </div>					   		
                      </div>
                    </div>
                  </div>
                    <div class="form-group">
                    <label class="col-lg-3 control-label"></label>
                    <div class="col-lg-8">
                      <div class="bs-component"> 					                    
					     <div class="checkbox-custom mb5">
						   <input name="Create_Authority" type="checkbox" class="form-control" id="Create_Authority">
							<label for="Create_Authority">Class Teacher can create Assignments under this Category</label>						    
						 </div>						   
                      </div> 
                    </div>
                  </div>                
                  <div class="form-group">
                    <label class="col-lg-3 control-label"></label>
                    <div class="col-lg-8">
					<div style="text-align:center" >
							 <button type="submit" name="button" id="button" class="btn btn-success mr10" value="Submit" onClick="checkSession();"><i class="fa fa-save"></i> Save</button>
						      <input type="hidden" name="Course" id="Course" value="<c:out value='${param.Course_id}'/>">
						      <input type="hidden" name="FromTeacher" id="FromTeacher" value="Yes">
					</div>
                    </div>
                  </div>  
              </div>
            </div>






<%-- 
		<table class="parentTable">
      	<thead>
	      <tr><th colspan="2">Assignment Category</th></tr>
	    </thead>
	          <tr>
          <td><label for="Category_Name">Assignment Category Name *</label></td>
          <td>
          <input type="text" name="Category_Name" id="Category_Name" class="bigText" required maxlength="100" >
          </td>
        </tr>
       <tr>
            <td><label>Category Weight *</label></td>
          <td><input name="Category_Grade" type="number" class="mediumNumber"  min="0" max="1000000000" id="Category_Grade" required></td>

        </tr>
        
                                           
        <tr>
          <td colspan="2" align = "left"><input name="Contribute" type="checkbox" id="Contribute" checked>Contribute to the total final grade</td>
        </tr> 

        <tr>
          <td colspan="2" align = "left"><input name="Create_Authority" type="checkbox" id="Create_Authority">Class Teacher can create Assignments under this Category</td>
        </tr> 

        <tr>
          <td colspan="2" align="center"  valign="top"><div class="submitHolder"><input type="submit" name="button" id="button" value="Submit" class="smallButton" onClick="checkSession();"></div></td>
        </tr>
      </table>
      <input type="hidden" name="Course" id="Course" value="<c:out value='${param.Course_id}'/>">
      <input type="hidden" name="FromTeacher" id="FromTeacher" value="Yes">
 --%>