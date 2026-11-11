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
					    <input type="text" name="Category_Name" id="Category_Name" class="form-control" required maxlength="100" value="<c:out value='${Assignment_Category_Details.category_Name}'/>">
					    <input type="hidden" name="Category_id" id="Category_id" value="<c:out value='${param.Category_id}'/>">						                       
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Category Weight *</label>
                    <div class="col-lg-8">
                      <div class="bs-component"> 
    					<input type="number" name="Category_Grade" id="Category_Grade" class="form-control" required  min="0" max="1000000000" value="<c:out value='${Assignment_Category_Details.category_Grade}'/>">						                    
                      </div>
                    </div>
                  </div>                                        
                  <div class="form-group">
                    <label class="col-lg-3 control-label"></label>
                    <div class="col-lg-8">
                      <div class="bs-component"> 					                    		     
					    <div class="checkbox-custom mb5">
						     <input name="Contribute" type="checkbox" id="Contribute" 
						   		<c:if test="${Assignment_Category_Details.contribute==1}">
								 	Checked
							    </c:if>
							    >
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
						   <input name="Create_Authority" type="checkbox" class="form-control" id="Create_Authority" 
						   		<c:if test="${Assignment_Category_Details.create_Authority==1}">
								 	Checked
							    </c:if>
							    >
							<label for="Create_Authority">Class Teacher can create Assignments under this Category</label>						    
						 </div>						   
                      </div> 
                    </div>
                  </div>                
                  <div class="form-group">
                    <label class="col-lg-3 control-label"></label>
                    <div class="col-lg-8">
					<div style="text-align:center" >
							 <button type="submit" name="button" id="button" class="btn btn-info mr10" value="Modify" onClick="checkSession();"><i class="fa fa-edit"></i> Edit Category</button>
							 <button type="button" name="Delete" id="Delete"  class="btn btn-danger mr10" value="Delete Category" onClick="showDeleteMessage();"><i class="fa fa-remove"></i> Delete Category</button>
						     <input type="hidden" name="Course" value="<c:out value='${param.Course_id}'/>"/>
						     <input type="hidden" name="Index" value="<c:out value='${param.Index}'/>"/>
					</div>
                    </div>
                  </div>  
              </div>
            </div>
