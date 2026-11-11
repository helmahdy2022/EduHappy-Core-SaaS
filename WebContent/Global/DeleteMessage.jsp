
<br/>
<img alt="Caution" src="${pageContext.request.contextPath}/images/Caution.png">
<br/>
<font size='5' color='red'>
<b>Caution</b>
</font>
<br/>
<font size='3' color='red'>
Please note that you are deleting the record from the database 
<br/>
which may impact other records already existing in the database
</font>

<div align='center'>

	<input type="button" style="width:200px;" id="ConfirmDelete" class="btn btn-info" onclick="displayModal();" value="Confirm to Proceed">


&nbsp;&nbsp;&nbsp;
<input type='button'  style="width:200px;" name='Cancel' id='Cancel' class="btn btn-success" value='Cancel' onClick='cancelDelete()'/>
</div>

