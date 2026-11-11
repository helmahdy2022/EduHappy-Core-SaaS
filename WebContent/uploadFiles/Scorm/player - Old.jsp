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
	//window.opener.document.write('hi');
/*    	if (window.XMLHttpRequest)
  	  {// code for IE7+, Firefox, Chrome, Opera, Safari
  	  xmlhttp=new XMLHttpRequest();
  	  }
	else
	  	{// code for IE6, IE5
	 		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	 	 }
	xmlhttp.open("GET","Reset_Attempt_Window.jsp",true);
	xmlhttp.send();  */

	if(window.opener.document.getElementById("Attempt_Window${sessionScope['Attempt_id']}")!=null)
	{
		window.opener.document.getElementById("Attempt_Window${sessionScope['Attempt_id']}").innerHTML="Window Closed";
	}


	
/* 	var message="";
    if (typeof event == 'undefined') {
        event = window.event;
    }
    if (event) 
    {

    	event.returnValue = message;
    	
    }
    return message;  */
};


</script>
</head>
<c:choose>
<c:when test="${sessionScope['Attempt_id']!=null}">

<body onload="InitPlayer();">

<br><br>
   Assignment_id= ${sessionScope['Assignment_id']}
  Attempt_id= ${sessionScope['Attempt_id']}
   Student_id= ${sessionScope['Student_Session']}


    <script type="text/javascript">

	var schemaversion;
	 var organizations_default;
	 var item_identifier;
	 var item_identifierref;
	 var resource_identifier;
	var manifest_identifier;
var xmlns;
var manifest_version;
	 var resource_href;
     function InitPlayer() {
       PlayerConfiguration.Debug = true;
       PlayerConfiguration.StorageSupport = true;

       PlayerConfiguration.TreeMinusIcon = "Img/minus.gif";
       PlayerConfiguration.TreePlusIcon = "Img/plus.gif";
       PlayerConfiguration.TreeLeafIcon = "Img/leaf.gif";
       PlayerConfiguration.TreeActiveIcon = "Img/select.gif";

       PlayerConfiguration.BtnPreviousLabel = "Previous";
       PlayerConfiguration.BtnContinueLabel = "Continue";
       PlayerConfiguration.BtnExitLabel = "Exit";
       PlayerConfiguration.BtnExitAllLabel = "Exit All";
       PlayerConfiguration.BtnAbandonLabel = "Abandon";
       PlayerConfiguration.BtnAbandonAllLabel = "Abandon All";
       PlayerConfiguration.BtnSuspendAllLabel = "Suspend All";

       //manifest by URL   
       //Run.ManifestByURL("q1/imsmanifest.xml", true);

       ///manifest as string
       parseimsmanifest(${sessionScope["Assignment_id"]});
document.write(xmlns)
         Run.ManifestByString( 
'<manifest identifier='+manifest_identifier+' version="1.3"\r\n' + 
'        xmlns="http://www.imsglobal.org/xsd/imscp_v1p1"\r\n' + 
'       xmlns:adlcp="http://www.adlnet.org/xsd/adlcp_v1p3"\r\n' + 
'       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"\r\n' + 
'       xsi:schemaLocation="http://www.imsglobal.org/xsd/imscp_v1p1 imscp_v1p1.xsd\r\n' + 
'                           http://www.adlnet.org/xsd/adlcp_v1p3 adlcp_v1p3.xsd\r\n' + 
'                           http://www.adlnet.org/xsd/adlseq_v1p3 adlseq_v1p3.xsd\r\n' + 
'                           http://www.adlnet.org/xsd/adlnav_v1p3 adlnav_v1p3.xsd\r\n' + 
'                           http://www.imsglobal.org/xsd/imsss imsss_v1p0.xsd">\r\n' + 
'  <metadata>\r\n' + 
'    <schema>ADL SCORM</schema>\r\n' + 
'    <schemaversion>'+schemaversion+'</schemaversion>\r\n' + 
'  </metadata>\r\n' + 
'  <organizations default="'+organizations_default+'">\r\n' + 
'    <organization identifier="Math_ORG">\r\n' + 
'      <title>Captivate E-Learning Course</title>\r\n' + 
'      <item identifier="'+item_identifier+'" identifierref="'+item_identifierref+'">\r\n' + 
'        <title>Math Quize</title>\r\n' + 
'      </item>\r\n' + 
'    </organization>\r\n' + 
'  </organizations>\r\n' + 
'  <resources>\r\n' + 
'    <resource identifier="'+resource_identifier+'" type="webcontent" adlcp:scormtype="sco" href="../${sessionScope['Assignment_id']}/OnlineQuiz/sco.jsp?resource_href='+resource_href+'"/>\r\n' + 
'  </resources>\r\n' + 
' </manifest>');  
  }
  </script>


  <table width="100%" height="100%" border="1" cellspacing="0" cellpadding="0">
    <tr>
      <td valign="top">
        <table border="0" cellspacing="0" cellpadding="0" width="100%">
         <tr>
              <td width="200" valign="top"><img src="Img/scormpool_s.jpg" style="padding-left:5px;" width="127" height="51" border="0" alt="SCORMPOOL"/></td>
  	      <td  valign="bottom"><div id="placeholder_navigationContainer" style="padding-top:5px;padding-bottom:5px;"></div></td>
         </tr>
        </table>
        
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
