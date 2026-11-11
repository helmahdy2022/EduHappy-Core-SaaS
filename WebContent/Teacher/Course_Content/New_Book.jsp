<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <!-- Input Fields -->
            <div class="panel">
              <div class="panel-heading">
                <span class="fa fa-book"></span> e-Book Details
              </div>
              <div class="panel-body">
 				<form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/Manage_Book" enctype="multipart/form-data">
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Book Title *</label>
                    <div class="col-lg-8">
                      <div class="bs-component">
						<input type="text" id="Title" name="Title" class="form-control"  required maxlength="255">                        
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-lg-3 control-label">ISBN</label>
                    <div class="col-lg-8">
                      <div class="bs-component"> 
						<input type="text" id="ISBN" name="ISBN" class="form-control"  maxlength="100">                     
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Author</label>
                    <div class="col-lg-8">
                      <div class="bs-component"> 
						<input type="text" id="Author" name="Author" class="form-control" maxlength="255">                    
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Publisher</label>
                    <div class="col-lg-8">
                      <div class="bs-component"> 
						<input type="text" id="Publisher" name="Publisher" class="form-control" maxlength="255">                    
                      </div>
                    </div>
                  </div>                                    
                  <div class="form-group">
                    <label class="col-lg-3 control-label">Publication Date</label>
                    <div class="col-lg-8">
                      <div class="bs-component"> 
						<input type="Date" id="Pub_Date" class="form-control" name="Pub_Date">                   
                      </div>
                    </div>
                  </div>   
 				<div class="form-group"> 
   				  <label class="col-lg-3 control-label">Upload e-Book Files</label>
                  <div class="admin-form">
                      <div class="col-lg-8">
						<div class="bs-component"> 
		                   <div class="section">
		                    <label class="field prepend-icon append-button file" onChange="filesSelected();">
		                      <span class="button btn-primary">Choose File</span>
		                      <input type="file" class="gui-file" name="Book_Files" id="Book_Files" multiple onChange="document.getElementById('uploader1').value = this.value;">
		                      <input type="text" class="gui-input" id="uploader1" placeholder="Please Select A File"><span id="file-selected"></span>
		                      <label class="field-icon">
		                        <i class="fa fa-upload"></i>
		                      </label>
		                    </label>                    
		                  </div>
	                  	</div> 
                      </div>               
                 </div> 
                 </div>            
                  <div class="form-group">
                    <label class="col-lg-3 control-label"></label>
                    <div class="col-lg-8">
                      <div class="bs-component"> 
						 <div class="progress">
		        				<div class="bar" id="bar"></div >
		        				<div class="percent" id="percent">0%</div >
		    			 </div>              
                      </div>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-lg-3 control-label"></label>
                    <div class="col-lg-8">
					<div style="text-align:center" >
							 <button type="submit" id="submit_button" name="submit_button" class="btn btn-success mr10" onClick="uploadFile();"><i class="fa fa-save"></i> Save</button>
							<input type="hidden" id="Course_id" name="Course_id" value="${param.Course_id}">
						<div class="requiredSignNote">Fields marked (*) are required</div>
					</div>
                    </div>
                  </div>  
				<input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
               </form>
              </div>
            </div>
            
            