<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">


<html>

<head>

	<title>Player</title>

	<script src="Lib/sscompat.js" type="text/javascript"></script> 
	<script src="Lib/sscorlib.js" type="text/javascript"></script>  
	<script src="Lib/ssfx.Core.js" type="text/javascript"></script> 
 
	<script type="text/javascript" src="Lib/API_BASE.js"></script>    
        <script type="text/javascript" src="Lib/API.js"></script>  
        <script type="text/javascript" src="Lib/API_1484_11.js"></script>

        <script type="text/javascript" src="Lib/Controls.js"></script>        
	<script type="text/javascript" src="Lib/LocalStorage.js"></script>
	<script type="text/javascript" src="Lib/Player.js"></script>
	<script type="text/javascript" src="Lib/parseimsmanifest.js"></script>
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
	xmlhttp.onreadystatechange=function()
	{
	if (xmlhttp.readyState==4 && xmlhttp.status==200)
	{
	document.write(xmlhttp.responseText);
	}
	};

	stopTimer();
	xmlhttp.open("POST","${pageContext.request.contextPath}/Update_Attempt_Duration?attempt_Duration="+timer,true);
	xmlhttp.send(); 

};

var API = null;

function findAPI(win){
	  while (!win.API_1484_11 && win.parent && win.parent != win)
	       win = win.parent;
	     if(win.API_1484_11)
	return win.API_1484_11;
	   }

function getMaxScore()
{
	var win = window;

/* 	if(win.parent && win.parent != win)
	  API = findAPI(win.parent); 

	if(!API && win.top.opener)
	  API = findAPI(win.top.opener);  */
API = findAPI(win);


	  //API.Initialize("");
	  var student_Score;
	  var max_Score;
	  //alert(API.GetValue("cmi.completion_status"));
		var finish = confirm("Are you sure you want to finish the attempt?")  
	if(finish == true)
		{
		  student_Score = API.GetValue("cmi.score.raw");
		  max_Score = API.GetValue("cmi.score.max");  
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
	xmlhttp.open("POST","${pageContext.request.contextPath}/Update_Attempt_Duration?attempt_Duration="+timer,true);
	xmlhttp.send();
	xmlhttp.open("GET","${pageContext.request.contextPath}/Student/Assignments/Quiz_Result.jsp?student_Score="+student_Score+"&&max_Score="+max_Score+"&&attempt_Duration="+timer,true);
	xmlhttp.send();

}

}


function getMaxScoreAfterTimeOut()
{

	var win = window;

/* 	if(win.parent && win.parent != win)
	  API = findAPI(win.parent); 

	if(!API && win.top.opener)
	  API = findAPI(win.top.opener); */ 
API = findAPI(win);
	  //API.Initialize("");
	  var student_Score;
	  var max_Score;
	  //API.GetValue("cmi.completion_status");
	  //if(API.GetValue("cmi.completion_status")=="completed")
	//	{
		  student_Score = API.GetValue("cmi.score.raw");
		  max_Score = API.GetValue("cmi.score.max");  
		//}
		  
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
		stopTimer();
		//API.SetValue("cmi.exit", "normal");
		if(x==0)
		//API.SetValue("cmi.completion_status", "completed");
		xmlhttp.open("POST","${pageContext.request.contextPath}/Update_Attempt_Duration?attempt_Duration=0&&student_Score="+student_Score,true);
		xmlhttp.send();
		x=1;

	}
};
//if(API.GetValue("cmi.completion_status")=="completed")
//{

	//xmlhttp.open("GET","Quiz_Result.jsp?student_Score="+student_Score+"&&max_Score="+max_Score+"&&attempt_Duration=0",true);
	//xmlhttp.send();
	//xmlhttp.open("POST","${pageContext.request.contextPath}/SubmitQuizScore?student_Score="+student_Score+"&&attempt_Duration=0&&TimeOut=Yes",true);
	//xmlhttp.send();
	
	stopTimer();

	xmlhttp.open("GET","${pageContext.request.contextPath}/Student/Assignments/Quiz_Result.jsp?student_Score="+student_Score+"&&max_Score="+max_Score+"&&attempt_Duration=0",true);
	xmlhttp.send();

//}

}
var TimerKey;
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
 window.onload = function () {

 	
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
 		//var AttemptDuration = ${sessionScope['Attempt_Duration']};
 		var AttemptDuration = xmlhttp.responseText;
 		//var AttemptDuration = ${duration}
 	    //var AttemptDuration = 60;
 	     display = window.parent.document.querySelector('#time');
 	    
 	    startTimer(AttemptDuration, display);
	} 
	}; 
	xmlhttp.open("POST","${pageContext.request.contextPath}/Start_Attempt?Assignment_id=187",true);
	xmlhttp.send();  
}; 


</script>

</head>
<c:choose>
<c:when test="${sessionScope['Attempt_id']!=null}">

<body onload="InitPlayer();">

   <br><br><br>
  <div align="center"> <input type="button" value="Finish Attempt" onclick="getMaxScore();"></div>


    <script type="text/javascript">

	 var manifest_identifier;
	 var manifest_version;
	 var xmlns;
	 var xmlns_adlcp;
	 var xmlns_xsi;
	 var xsi_schemaLocation;
	 var schema;
     var schemaversion;
	 var organizations_default;
	 var organization_identifier
	 var organization_title;
	 var item_identifier;
	 var item_identifierref;
	 var resource_identifier;
	 var item_title;
	 var resource_type;
	 var resource_adlcp_scormtype;
	 var resource_href;
     function InitPlayer() {
      // PlayerConfiguration.Debug = true;
       //manifest by URL   
       Run.ManifestByURL("../${sessionScope['Assignment_id']}/OnlineQuiz/imsmanifest.xml", true);

       ///manifest as string
 /*         parseimsmanifest(${sessionScope["Assignment_id"]});
          Run.ManifestByString( 
'<manifest identifier="'+manifest_identifier+'" version="'+manifest_version+'"\r\n' + 
'        xmlns="'+xmlns+'"\r\n' + 
'       xmlns:adlcp="'+xmlns_adlcp+'"\r\n' + 
'       xmlns:xsi="'+xmlns_xsi+'"\r\n' + 
'       xsi:schemaLocation="'+xsi_schemaLocation+'">\r\n' +  
'  <metadata>\r\n' + 
'    <schema>'+schema+'</schema>\r\n' + 
'    <schemaversion>'+schemaversion+'</schemaversion>\r\n' + 
'  </metadata>\r\n' + 
'  <organizations default="'+organizations_default+'">\r\n' + 
'    <organization identifier="'+organization_identifier+'">\r\n' + 
'      <title>'+organization_title+'</title>\r\n' + 
'      <item identifier="'+item_identifier+'" identifierref="'+item_identifierref+'">\r\n' + 
'        <title>'+item_title+'</title>\r\n' + 
'      </item>\r\n' + 
'    </organization>\r\n' + 
'  </organizations>\r\n' + 
'  <resources>\r\n' + 
'    <resource identifier="'+resource_identifier+'" type="'+resource_type+'" adlcp:scormtype="sco" href="../${sessionScope['Assignment_id']}/OnlineQuiz/'+resource_href+'"/>\r\n' + 
'  </resources>\r\n' + 
' </manifest>');  */    
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
