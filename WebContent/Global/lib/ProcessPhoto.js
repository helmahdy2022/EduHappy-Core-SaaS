

  var resized_image;
  var resized_logo
  var dataurl;
  var logourl;
  var rotated = 0;
  function readImage()
  {

	  document.getElementById("spinner").style="display:block";
	  
	  var filesToUpload = document.getElementById('Entity_Photo').files;
	  var file = filesToUpload[0];
		  var img = document.createElement("img");
		  var reader = new FileReader();  
		  
	  
		  reader.onload = function(e) 
		  {		  
			  
			  var reject=false;
			  var fileType;
			  var buffer = reader.result;
			  var mimetype = buffer.split(",")[0].split(":")[1].split(";")[0];				  
		      if(!mimetype.startsWith("image"))
		    	  {
		    	  	alert("File is not image. Please upload file type image");
		    	  	document.getElementById("spinner").style="none";
		    	  	document.getElementById("Entity_Photo").disabled=false;
		    	  }
		      else
		    	  {
		    	  	img.src = e.target.result;	   				    	  	
		    	  	img.onload = function() 
		   	      	{		   	      	  
					      var canvas = document.getElementById('canvas');
						  var ctx = canvas.getContext("2d");
					   	  EXIF.getData(img, function() 
					   	  {			            
						   	  console.log('Exif=', EXIF.getTag(this, "Orientation"));
							  ctx.setTransform(1, 0, 0, 1, 0, 0);  
				 			  switch(parseInt(EXIF.getTag(this, "Orientation"))) 
				 			  {
							       case 2:
							              ctx.setTransform(-1, 0, 0, 1, canvas.width, 0);  break;
							       case 3:
							              ctx.setTransform(-1, 0, 0, -1, canvas.width, canvas.height );  break;
							       case 4:
							              ctx.setTransform(1, 0, 0, -1, 0, canvas.height );  break;
							       case 5:
							              ctx.setTransform(0, 1, 1, 0, 0, 0); break;
							       case 6:
							              ctx.setTransform(0, 1, -1, 0, canvas.height , 0);  break;
							       case 7:
							              ctx.setTransform(0, -1, -1, 0, canvas.height , canvas.width);  break;
							       case 8:
							              ctx.setTransform(0, -1, 1, 0, 0, canvas.width);  break;
							    } 
						    });    			    
						
						   //////////////////use down-steps resizing///////////////////////////////////////////////////////////
					       //if(navigator.userAgent.search("Firefox") > -1)
				    	   //{
					    	//  alert('Image is reloading now');
				    	   //}				  	
						   if((img.width > canvas.width*2) || (img.height > canvas.height*2))
						   {
							   //alert("1:"+img.width+"X"+img.height);
						   	   /// step 1 - resize to 50%							    
							   var oc = document.createElement('canvas'),
							   octx = oc.getContext('2d');							
							   oc.width = img.width * 0.5;
							   oc.height = img.height * 0.5;
							   octx.drawImage(img, 0, 0, oc.width, oc.height);							
							   /// step 2 - resize 50% of step 1
							   octx.drawImage(oc, 0, 0, oc.width * 0.5, oc.height * 0.5);   
							   /// step 3, resize to final size
							   try
							   {
								   if(oc.height > oc.width)						   
									   ctx.drawImage(oc, 0, 0, oc.width * 0.5, oc.width * 0.5, 0, 0, canvas.width, canvas.width);
								   else
									   ctx.drawImage(oc, 0, 0, oc.height * 0.5, oc.height * 0.5, 0, 0, canvas.height, canvas.height);
							   }
							   catch(InvalidStateError)
							   {
								   alert("File may be corrupted or not an image");
								   reject=true;
								   document.getElementById("spinner").style="none";
							   }			   	    		
							}
							else
							{
								//alert("2:"+img.width+"X"+img.height);
								try
								{
									//alert("2:"+img.width+"X"+img.height);
									ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
								}
								catch(InvalidStateError)
								{
									alert("File may be corrupted or not an image");
									reject=true;
									document.getElementById("spinner").style="none";
								}	
								
							}	
						    ////////////////////////////////////////////////////////////////////////////////////////////////////		  
							if(reject==false)
							{
								dataurl = canvas.toDataURL("image/jpeg");  
								// convert base64 to raw binary data held in a string
								// doesn't handle URLEncoded DataURIs - see SO answer #6850276 for code that does this
								var byteString = atob(dataurl.split(',')[1]);					
								// separate out the mime component
								var mimeString = dataurl.split(',')[0].split(':')[1].split(';')[0]					
								// write the bytes of the string to an ArrayBuffer
								var ab = new ArrayBuffer(byteString.length);
								var ia = new Uint8Array(ab);
								for (var i = 0; i < byteString.length; i++) 
								{
									ia[i] = byteString.charCodeAt(i);
								}						  
								var blob = new Blob([ab], {type: mimeString});
								resized_image = new Blob([ab], {type: mimeString});
								//resized_image =  new File([blob], file.name);	  								  
								document.getElementById('Entity_Photo').value = "";  
								document.getElementById('Photo').src = window.URL.createObjectURL(resized_image);		  
								document.getElementById('Photo').style.display='block';
								document.getElementById("RemovePhoto").style.display='block';
								document.getElementById("RemovePhoto").innerHTML = "Remove Photo";
								document.getElementById('custom-file-upload').style.backgroundImage = 'none';	
								document.getElementById("spinner").style="none";
							}
				   	};		    	      	  
		    	  }
		  }
			try
			{
				reader.readAsDataURL(file);
			}
			catch(TypeError)
			{
				alert("Can't read the file. Please try another one");
				document.getElementById("spinner").style="none";
			}
		  
  }

  function readLogo()
  {

	  document.getElementById("spinner").style="display:block";
	  
	  var filesToUpload = document.getElementById('Entity_Logo').files;
	  var file = filesToUpload[0];
		  var img = document.createElement("img");
		  var reader = new FileReader();  
		  
	  
		  reader.onload = function(e) 
		  {		  
			  
			  var reject=false;
			  var fileType;
			  var buffer = reader.result;
			  var mimetype = buffer.split(",")[0].split(":")[1].split(";")[0];				  
		      if(!mimetype.startsWith("image"))
		    	  {
		    	  	alert("File is not image. Please upload file type image");
		    	  	document.getElementById("spinner").style="none";
		    	  	document.getElementById("Entity_Logo").disabled=false;
		    	  }
		      else
		    	  {
		    	  	img.src = e.target.result;	   				    	  	
		    	  	img.onload = function() 
		   	      	{		   	      	  
					      var canvas = document.getElementById('canvas-logo');
						  var ctx = canvas.getContext("2d");
					   	  EXIF.getData(img, function() 
					   	  {			            
						   	  console.log('Exif=', EXIF.getTag(this, "Orientation"));
							  ctx.setTransform(1, 0, 0, 1, 0, 0);  
				 			  switch(parseInt(EXIF.getTag(this, "Orientation"))) 
				 			  {
							       case 2:
							              ctx.setTransform(-1, 0, 0, 1, canvas.width, 0);  break;
							       case 3:
							              ctx.setTransform(-1, 0, 0, -1, canvas.width, canvas.height );  break;
							       case 4:
							              ctx.setTransform(1, 0, 0, -1, 0, canvas.height );  break;
							       case 5:
							              ctx.setTransform(0, 1, 1, 0, 0, 0); break;
							       case 6:
							              ctx.setTransform(0, 1, -1, 0, canvas.height , 0);  break;
							       case 7:
							              ctx.setTransform(0, -1, -1, 0, canvas.height , canvas.width);  break;
							       case 8:
							              ctx.setTransform(0, -1, 1, 0, 0, canvas.width);  break;
							    } 
						    });    			    
						
						   //////////////////use down-steps resizing///////////////////////////////////////////////////////////
					       //if(navigator.userAgent.search("Firefox") > -1)
				    	   //{
					    	//  alert('Image is reloading now');
				    	   //}				  	
						   if((img.width > canvas.width*2) || (img.height > canvas.height*2))
						   {
							   //alert("1:"+img.width+"X"+img.height);
						   	   /// step 1 - resize to 50%							    
							   var oc = document.createElement('canvas-logo'),
							   octx = oc.getContext('2d');							
							   oc.width = img.width * 0.5;
							   oc.height = img.height * 0.5;
							   octx.drawImage(img, 0, 0, oc.width, oc.height);							
							   /// step 2 - resize 50% of step 1
							   octx.drawImage(oc, 0, 0, oc.width * 0.5, oc.height * 0.5);   
							   /// step 3, resize to final size
							   try
							   {
								   if(oc.height > oc.width)						   
									   ctx.drawImage(oc, 0, 0, oc.width * 0.5, oc.width * 0.5, 0, 0, canvas.width, canvas.width);
								   else
									   ctx.drawImage(oc, 0, 0, oc.height * 0.5, oc.height * 0.5, 0, 0, canvas.height, canvas.height);
							   }
							   catch(InvalidStateError)
							   {
								   alert("File may be corrupted or not an image");
								   reject=true;
								   document.getElementById("spinner").style="none";
							   }			   	    		
							}
							else
							{
								//alert("2:"+img.width+"X"+img.height);
								try
								{
									//alert("2:"+img.width+"X"+img.height);
									ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
								}
								catch(InvalidStateError)
								{
									alert("File may be corrupted or not an image");
									reject=true;
									document.getElementById("spinner").style="none";
								}	
								
							}	
						    ////////////////////////////////////////////////////////////////////////////////////////////////////		  
							if(reject==false)
							{
								logourl = canvas.toDataURL("image/jpeg");  
								// convert base64 to raw binary data held in a string
								// doesn't handle URLEncoded DataURIs - see SO answer #6850276 for code that does this
								var byteString = atob(logourl.split(',')[1]);					
								// separate out the mime component
								var mimeString = logourl.split(',')[0].split(':')[1].split(';')[0]					
								// write the bytes of the string to an ArrayBuffer
								var ab = new ArrayBuffer(byteString.length);
								var ia = new Uint8Array(ab);
								for (var i = 0; i < byteString.length; i++) 
								{
									ia[i] = byteString.charCodeAt(i);
								}						  
								var blob = new Blob([ab], {type: mimeString});
								resized_logo = new Blob([ab], {type: mimeString});
								//resized_image =  new File([blob], file.name);	  								  
								document.getElementById('Entity_Logo').value = "";  
								document.getElementById('Logo').src = window.URL.createObjectURL(resized_logo);		  
								document.getElementById('Logo').style.display='block';
								document.getElementById("RemovePhoto").style.display='block';
								document.getElementById("RemovePhoto").innerHTML = "Remove Photo";
								document.getElementById('custom-file-upload-logo').style.backgroundImage = 'none';	
								document.getElementById("spinner").style="none";
							}
				   	};		    	      	  
		    	  }
		  }
			try
			{
				reader.readAsDataURL(file);
			}
			catch(TypeError)
			{
				alert("Can't read the file. Please try another one");
				document.getElementById("spinner").style="none";
			}
		  
  }
	