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
</head>

<body onload="InitPlayer();">



    <script type="text/javascript">

     function InitPlayer() {
       //PlayerConfiguration.Debug = true;

       //manifest by URL   
       Run.ManifestByURL("../Accounts/${param.Account_id}/Schools/${param.School_id}/Assignments/${param.Assignment_id}/OnlineQuiz/imsmanifest.xml", true);    ///working one
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
</html>