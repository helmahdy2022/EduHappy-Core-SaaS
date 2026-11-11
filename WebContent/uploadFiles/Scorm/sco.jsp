<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

  <script type="text/javascript">
var API = null;

function findAPI(win){
  while (!win.API_1484_11 && win.parent && win.parent != win)
       win = win.parent;
     if(win.API_1484_11)
return win.API_1484_11;
   }

function onloadFn(){
   var win = window;
 
  if(win.parent && win.parent != win)
    API = findAPI(win.parent); 
  
  if(!API && win.top.opener)
    API = findAPI(win.top.opener); 

  	//API.Initialize("");
/*   	API.SetValue("cmi.completion_status", "incomplete");
  	 API.Commit();
  	API.SetValue("cmi.entry", "ab-initio");
  	 
  	API.SetValue("cmi.location", "Slide_1");
	//alert(API.GetValue("cmi.completion_status"));
	API.SetValue("cmi.learner_id", "37");
	//alert(API.GetValue("cmi.learner_id"));
	alert(API.GetValue("cmi.objectives"));  */
	
	
	
  //if(API)

}

function onunloadFn(){
  if(API)
    API.LMSFinish("");
}
function findAPI(win){
	  while (!win.API_1484_11 && win.parent && win.parent != win)
	       win = win.parent;
	     if(win.API_1484_11)
	return win.API_1484_11;
	   }
function getMaxScore()
{

	var win = window;

	if(win.parent && win.parent != win)
	  API = findAPI(win.parent); 

	if(!API && win.top.opener)
	  API = findAPI(win.top.opener); 

	  //API.Initialize("");
	  var student_Score;
	  var max_Score;
	  API.GetValue("cmi.completion_status");
	  if(API.GetValue("cmi.completion_status")=="completed")
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
if(API.GetValue("cmi.completion_status")=="completed")
{
	stopTimer();
	xmlhttp.open("GET","../../Scorm/Quiz_Result.jsp?student_Score="+student_Score+"&&max_Score="+max_Score,true);
	xmlhttp.send();
}

}


function getMaxScoreAfterTimeOut()
{

	var win = window;

	if(win.parent && win.parent != win)
	  API = findAPI(win.parent); 

	if(!API && win.top.opener)
	  API = findAPI(win.top.opener); 

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
xmlhttp.onreadystatechange=function()
{
if (xmlhttp.readyState==4 && xmlhttp.status==200)
	{
		document.write(xmlhttp.responseText);
		stopTimer();
		API.SetValue("cmi.exit", "normal");
		
		API.SetValue("cmi.completion_status", "completed");
	}
};
//if(API.GetValue("cmi.completion_status")=="completed")
//{

	xmlhttp.open("GET","../../Scorm/Quiz_Result.jsp?student_Score="+student_Score+"&&max_Score="+max_Score,true);
	xmlhttp.send();
//}

}
var TimerKey;
function startTimer(duration, display) {
    var timer = duration, minutes, seconds;
    
    TimerKey = setInterval(function () {
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        display.textContent = minutes + ":" + seconds;

        if (--timer < 0) {
            getMaxScoreAfterTimeOut();
        }
    }, 1000);
}
function stopTimer()
{
	clearInterval(TimerKey);
}
 window.onload = function () {
    var fiveMinutes = 10,
        display = window.parent.document.querySelector('#time');
    
    startTimer(fiveMinutes, display);
}; 

</script>

</head>

<body onmousedown="getMaxScore()">

  <h4>Test SCO</h4>

<jsp:include page="${param.resource_href}"/>

</body>

</html>