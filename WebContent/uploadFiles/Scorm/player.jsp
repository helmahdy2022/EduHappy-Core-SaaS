<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">


<html>
<head>

	<title>Player</title>

 	<script src="${pageContext.request.contextPath}/uploadFiles/Scorm/Lib/sscompat.js" type="text/javascript"></script> 
	<script src="${pageContext.request.contextPath}/uploadFiles/Scorm/Lib/sscorlib.js" type="text/javascript"></script>  
	<script src="${pageContext.request.contextPath}/uploadFiles/Scorm/Lib/ssfx.Core.js" type="text/javascript"></script> 
 
	<script type="text/javascript" src="${pageContext.request.contextPath}/uploadFiles/Scorm/Lib/API_BASE.js"></script>    
        <script type="text/javascript" src="${pageContext.request.contextPath}/uploadFiles/Scorm/Lib/API.js"></script>  
        <script type="text/javascript" src="${pageContext.request.contextPath}/uploadFiles/Scorm/Lib/API_1484_11.js"></script>

        <script type="text/javascript" src="${pageContext.request.contextPath}/uploadFiles/Scorm/Lib/Controls.js"></script>        
	<script type="text/javascript" src="${pageContext.request.contextPath}/uploadFiles/Scorm/Lib/LocalStorage.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/uploadFiles/Scorm/Lib/Player.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/uploadFiles/Scorm/Lib/parseimsmanifest.js"></script> 
	
<!-- 		<script src="Lib/sscompat.js" type="text/javascript"></script> 
	<script src="Lib/sscorlib.js" type="text/javascript"></script>  
	<script src="Lib/ssfx.Core.js" type="text/javascript"></script> 
 
	<script type="text/javascript" src="Lib/API_BASE.js"></script>    
        <script type="text/javascript" src="Lib/API.js"></script>  
        <script type="text/javascript" src="Lib/API_1484_11.js"></script>

        <script type="text/javascript" src="Lib/Controls.js"></script>        
	<script type="text/javascript" src="Lib/LocalStorage.js"></script>
	<script type="text/javascript" src="Lib/Player.js"></script>
	<script type="text/javascript" src="Lib/parseimsmanifest.js"></script> -->
	
	<style type="text/css">
		* { margin: 0px; }
                html, body, iframe { height:100%; }
		html, body { overflow-y:hidden; font-family:Helvetica, "Helvetica Neue", Arial; font-size:12px; }
		iframe { display:block; width:100%; }

		html, body, iframe , a, img, table, tbody, tr, td, table td, table th {
		 border : 0px none;
                 padding: 0px; 
		}

		a:link { color: #0000FF; }
		a:visited { color: #0000FF; }
		a:hover { color: #000080; }
		a:active { color: #0000FF; }

		#btnExit {margin-left:5px;}
		#btnAbandon {margin-left:5px;}
		#btnSuspendAll {margin-left:5px;}
	</style>
<script>

window.onbeforeunload = function (event) {

	if(window.opener.document.getElementById("Attempt_Window${sessionScope['Attempt_id']}")!=null)
	{
		window.opener.document.getElementById("Attempt_Window${sessionScope['Attempt_id']}").innerHTML="Window Closed";
	}  

 	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
	xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
	xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	stopTimer();
	xmlhttp.open("GET","${pageContext.request.contextPath}/Update_Attempt_Duration?attempt_Duration="+timer,true);
	xmlhttp.send(); 

};

//var API = null;
var API;
function findAPI(win){
	  while (!win.API && win.parent && win.parent != win)
	       win = win.parent;
	     if(win.API)
	return win.API;
	   }
function getMaxScore()
{
	var win = window;
	API = findAPI(win);
	var student_Score;
	var max_Score;
	var score_Scale;
	var finish = confirm("Are you sure you want to finish the attempt?")  
	if(finish == true)
		{
		  	try
		  	{
			     student_Score = API.LMSGetValue("cmi.core.score.raw");
		  		 max_Score = API.LMSGetValue("cmi.core.score.max");
		  		score_Scale = student_Score/max_Score; 
		  	}
		  	catch(e)
		  	{
 				while (!win.API_1484_11 && win.parent && win.parent != win)
			       win = win.parent;
			    if(win.API_1484_11)
			 	 API = win.API_1484_11;
			  	 student_Score = API.GetValue("cmi.score.raw");
	  			 max_Score = API.GetValue("cmi.score.max"); 
	  			score_Scale = API.GetValue("cmi.score.scaled"); 
	  			 
		  	}					
		}
		  
if (window.XMLHttpRequest)
{// code for IE7+, Firefox, Chrome, Opera, Safari
xmlhttp=new XMLHttpRequest();
}
else
{// code for IE6, IE5
xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
 xmlhttp.onreadystatechange=function()
{
if (xmlhttp.readyState==4 && xmlhttp.status==200)
{
document.write(xmlhttp.responseText);
}
}; 
if(finish==true)
{
	stopTimer();
	xmlhttp.open("GET","${pageContext.request.contextPath}/Update_Attempt_Duration?attempt_Duration="+timer+"&&student_Score="+student_Score+"&&max_Score="+max_Score+"&&score_Scale="+score_Scale+"&&unload=0",true);
	xmlhttp.send();
}

}


function getMaxScoreAfterTimeOut()
{

	var win = window;
	API = findAPI(win);
	var student_Score;
	var max_Score;
  	try
  	{
	     student_Score = API.LMSGetValue("cmi.core.score.raw");
  		 max_Score = API.LMSGetValue("cmi.core.score.max");
  		score_Scale = student_Score/max_Score;
  	}
  	catch(e)
  	{
		//if(e.message=="Cannot read property 'LMSGetValue' of null")
			//{
				while (!win.API_1484_11 && win.parent && win.parent != win)
			       win = win.parent;
			    if(win.API_1484_11)
			 	 API = win.API_1484_11;
			  	 student_Score = API.GetValue("cmi.score.raw");
	  			 max_Score = API.GetValue("cmi.score.max"); 
	  			score_Scale = API.GetValue("cmi.score.scaled"); 
	  			
			//}
  	} 
		  
if (window.XMLHttpRequest)
{// code for IE7+, Firefox, Chrome, Opera, Safari
xmlhttp=new XMLHttpRequest();
}
else
{// code for IE6, IE5
	xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
var x=0;
 xmlhttp.onreadystatechange=function()
{
if (xmlhttp.readyState==4 && xmlhttp.status==200)
	{
		document.write(xmlhttp.responseText);
	}
}; 

	stopTimer();
	xmlhttp.open("GET","${pageContext.request.contextPath}/Update_Attempt_Duration?attempt_Duration=0&&student_Score="+student_Score+"&&max_Score="+max_Score+"&&score_Scale="+score_Scale+"&&unload=0",true);
	xmlhttp.send();

}
var TimerKey;
var timer = -1;
function startTimer(duration, display) {
    timer = duration
    var minutes, seconds;
    
    TimerKey = setInterval(function () {
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        display.textContent = minutes + ":" + seconds;

        if (--timer < 0) {
        	stopTimer();

        	getMaxScoreAfterTimeOut();        	

        }
    }, 1000);
}
function stopTimer()
{
	clearInterval(TimerKey);
}

</script>

</head>
<c:choose>
<c:when test="${sessionScope['Attempt_id']!=null}">

<body onload="InitPlayer();">

   <br><br><br>
  <div align="center"> <input type="button" value="Finish Attempt" onclick="getMaxScore();"></div>


    <script type="text/javascript">

     function InitPlayer() {
       //PlayerConfiguration.Debug = true;

       //manifest by URL   
 		if(${param.Attempt_Duration}!=-1)
			{
			 	var AttemptDuration = ${param.Attempt_Duration}
			    display = window.parent.document.querySelector('#time');
			    startTimer(AttemptDuration, display);
			}
 		if(${param.Attempt_Duration}==0)
 			{
 				window.location="${pageContext.request.contextPath}/Update_Attempt_Duration?attempt_Duration="+timer+"&unload=0";
 			}
       
       Run.ManifestByURL("uploadFiles/Accounts/${param.Account_id}/Schools/${param.School_id}/Assignments/${sessionScope['Assignment_id']}/OnlineQuiz/imsmanifest.xml", true);    ///working one
  }
      
    </script>


  <table width="100%" height="100%" border="1" cellspacing="0" cellpadding="0">
    <tr>
      <td valign="top">

        
      </td>
    </tr>
    <tr><td align="center"><div id="time"></div></td></tr>
    <tr>
      <td height="100%" valign="top">
       <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td valign="top" height="100%" style="padding-left:5px;"><div id="placeholder_treeContainer" style="width:200px;height:100%;overflow:auto;"></div></td>
          <td width="100%" height="100%" valign="top">
          <div id="placeholder_contentIFrame" style="width:100%;height:100%;-webkit-overflow-scrolling:touch;" >

	
          </div></td>
          <td valign="top" height="100%">
          <div id="placeholder_Debugger" style="font-size:10px;width:250px;height:100%;overflow:auto;padding-left:5px;display:none;">
          
          </div></td>
        </tr>
       </table>
      </td>
    </tr>
    <tr>
      <td style="font-size:10px;padding-left:5px;" align="center">

      </td>
    </tr>
  </table>


</body>
</c:when>
<c:otherwise>
<c:out value="Attempt failed to start">
</c:out>
</c:otherwise>
</c:choose>
</html>
