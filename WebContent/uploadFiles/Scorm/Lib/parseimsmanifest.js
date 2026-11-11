function parseimsmanifest(Assignment_id)
{
if (window.XMLHttpRequest)
  {
  xhttp=new XMLHttpRequest();
  }
else // code for IE5 and IE6
  {
  xhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xhttp.open("GET","../../Assignments/"+Assignment_id+"/OnlineQuiz/imsmanifest.xml",false);
//xhttp.open("GET","http://localhost:8080/EduHappy/uploadFiles/Assignments/"+Assignment_id+"/OnlineQuiz/imsmanifest.xml",false);
xhttp.send();

var xmlDoc=xhttp.responseXML;
//*******************Get manifest identifier **************************************************///
var manifest = xmlDoc.getElementsByTagName("manifest");
manifest_identifier = manifest[0].getAttribute('identifier')
//document.write(manifest_identifier +'<br>');
//*******************Get manifest version **************************************************///
manifest_version = manifest[0].getAttribute('version');
//document.write(manifest_version +'<br>');
//********************************************************************************************///
//*******************Get manifest xmlns **************************************************///
xmlns = manifest[0].getAttribute('xmlns');
//document.write(xmlns +'<br>');
//********************************************************************************************///
//*******************Get manifest xmlns:adlcp **************************************************///
xmlns_adlcp = manifest[0].getAttribute('xmlns:adlcp');
//document.write(xmlns_adlcp +'<br>');
//********************************************************************************************///
//*******************Get manifest xmlns:xsi **************************************************///
xmlns_xsi = manifest[0].getAttribute('xmlns:xsi')
//document.write(xmlns_xsi +'<br>');
//********************************************************************************************///
//*******************Get manifest xmlns:adlseq **************************************************///
xmlns_adlseq = manifest[0].getAttribute('xmlns:adlseq');
//document.write(xmlns_adlseq +'<br>');
//********************************************************************************************///
//*******************Get manifest xmlns:adlnav **************************************************///
xmlns_adlnav = manifest[0].getAttribute('xmlns:adlnav');
//document.write(xmlns_adlnav +'<br>');
//********************************************************************************************///
//*******************Get manifest xmlns:imsss **************************************************///
xmlns_imsss = manifest[0].getAttribute('xmlns:imsss');
//document.write(xmlns_imsss +'<br>');
//********************************************************************************************///
//*******************Get manifest xsi_schemaLocation **************************************************///
xsi_schemaLocation = manifest[0].getAttribute('xsi:schemaLocation');
//document.write(xsi_schemaLocation +'<br>');
//********************************************************************************************///
var m = xmlDoc.getElementsByTagName("metadata");
//*******************Get Schema *******************************************************///
schema = m[0].getElementsByTagName("schema")[0].childNodes[0].nodeValue;
//document.write(schema +'<br>');
//********************************************************************************************///
//*******************Get Schema Version *******************************************************///
schemaversion = m[0].getElementsByTagName("schemaversion")[0].childNodes[0].nodeValue;
//document.write(schemaversion +'<br>');
//********************************************************************************************///

//*******************Get organizations default attribute *************************************///
var os  = xmlDoc.getElementsByTagName("organizations");  
organizations_default = os[0].getAttribute('default');
//document.write(organizations_default +'<br>');
 //********************************************************************************************///   
var o = os[0].getElementsByTagName("organization");
 
//*******************Get organization identifier *****************************************///
organization_identifier = o[0].getAttribute('identifier');
//document.write(organization_identifier +'<br>');
//********************************************************************************************///
//*******************Get organization title *****************************************///
organization_title = o[0].getElementsByTagName("title")[0].childNodes[0].nodeValue;
//document.write(organization_title +'<br>');
//********************************************************************************************///
 //*******************Get organization item attributes *****************************************///
var it = o[0].getElementsByTagName("item");
item_identifier = it[0].getAttribute('identifier');
item_identifierref = it[0].getAttribute('identifierref');
item_title =  it[0].getElementsByTagName("title")[0].childNodes[0].nodeValue;
//document.write(item_identifier +'<br>');
//document.write(item_identifierref +'<br>');
//document.write(item_title +'<br>');
//********************************************************************************************/// 

 //*******************Get resource attributes *****************************************///
var rs = xmlDoc.getElementsByTagName("resources");
var r = rs[0].getElementsByTagName("resource");
resource_identifier = r[0].getAttribute('identifier');
resource_type = r[0].getAttribute('type');
resource_adlcp_scormtype = r[0].getAttribute('adlcp:scormType');
resource_href = r[0].getAttribute('href');
//document.write(resource_identifier +'<br>');
//document.write(resource_type +'<br>');
//document.write(resource_adlcp_scormtype +'<br>');
//document.write(resource_href +'<br>');
//********************************************************************************************/// 
}