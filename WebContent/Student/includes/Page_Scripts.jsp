<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  <!-- BEGIN: PAGE SCRIPTS -->

  <!-- jQuery -->
  	    <c:if test="${!(fn:contains(pageContext.request.servletPath, 'Manage_Lesson.jsp')||
  	    fn:contains(pageContext.request.servletPath, 'Manage_Assignment_Category.jsp') ||
  	    fn:contains(pageContext.request.servletPath, 'Manage_Standard_Assignment.jsp') ||
  	    fn:contains(pageContext.request.servletPath, 'New_Assignment.jsp') ||
  	    fn:contains(pageContext.request.servletPath, 'Semester_Exams.jsp'))}">
  <script src="${pageContext.request.contextPath}/Global/lib/js/jquery-1.11.1.min.js"></script>
	    </c:if>    
  <script src="${pageContext.request.contextPath}/Global/js-webshim/minified/polyfiller.js"></script>	
  <script src="${pageContext.request.contextPath}/Global/lib/js/jquery-ui.min.js"></script>

  <!-- HighCharts Plugin -->
  <script src="${pageContext.request.contextPath}/Global/lib/js/highcharts.js"></script>

  <!-- Sparklines Plugin -->
  <script src="${pageContext.request.contextPath}/Global/lib/js/jquery.sparkline.min.js"></script>

  <!-- Simple Circles Plugin -->
  <script src="${pageContext.request.contextPath}/Global/lib/js/circles.js"></script>

  <!-- JvectorMap Plugin + US Map (more maps in plugin/assets folder) -->
  <script src="${pageContext.request.contextPath}/Global/lib/js/jquery.jvectormap.min.js"></script>
  <script src="${pageContext.request.contextPath}/Global/lib/js/jquery-jvectormap-us-lcc-en.js"></script> 

  <!-- Theme Javascript -->
  <script src="${pageContext.request.contextPath}/Global/lib/js/utility.js"></script>
  <script src="${pageContext.request.contextPath}/Global/lib/js/demo.js"></script>
  <script src="${pageContext.request.contextPath}/Global/lib/js/main.js"></script>
<script src="${pageContext.request.contextPath}/Global/lib/js/canvasbg.js"></script>

  <!-- Widget Javascript -->

  <script type="text/javascript">
 
  jQuery(document).ready(function() {

    "use strict";

    // Init Theme Core      
    Core.init();

    // Init Demo JS
    Demo.init();

    // Init Widget Demo JS
    // demoHighCharts.init();

    // Because we are using Admin Panels we use the OnFinish 
    // callback to activate the demoWidgets. It's smoother if
    // we let the panels be moved and organized before 
    // filling them with content from various plugins

    // Init plugins used on this page
    // HighCharts, JvectorMap, Admin Panels

    // Init Admin Panels on widgets inside the ".admin-panels" container
    $('.admin-panels').adminpanel({
      grid: '.admin-grid',
      draggable: true,
      preserveGrid: true,
      mobile: false,
      onStart: function() {
        // Do something before AdminPanels runs
      },
      onFinish: function() {
        $('.admin-panels').addClass('animated fadeIn').removeClass('fade-onload');

        // Init the rest of the plugins now that the panels
        // have had a chance to be moved and organized.
        // It's less taxing to organize empty panels
        //demoHighCharts.init();
        runVectorMaps(); // function below
      },
      onSave: function() {
        $(window).trigger('resize');
      }
    });

    // Widget VectorMap
    function runVectorMaps() {

      // Jvector Map Plugin
      var runJvectorMap = function() {
        // Data set
        var mapData = [900, 700, 350, 500];
        // Init Jvector Map
        $('#WidgetMap').vectorMap({
          map: 'us_lcc_en',
          //regionsSelectable: true,
          backgroundColor: 'transparent',
          series: {
            markers: [{
              attribute: 'r',
              scale: [3, 7],
              values: mapData
            }]
          },
          regionStyle: {
            initial: {
              fill: '#E5E5E5'
            },
            hover: {
              "fill-opacity": 0.3
            }
          },
          markers: [{
            latLng: [37.78, -122.41],
            name: 'San Francisco,CA'
          }, {
            latLng: [36.73, -103.98],
            name: 'Texas,TX'
          }, {
            latLng: [38.62, -90.19],
            name: 'St. Louis,MO'
          }, {
            latLng: [40.67, -73.94],
            name: 'New York City,NY'
          }],
          markerStyle: {
            initial: {
              fill: '#a288d5',
              stroke: '#b49ae0',
              "fill-opacity": 1,
              "stroke-width": 10,
              "stroke-opacity": 0.3,
              r: 3
            },
            hover: {
              stroke: 'black',
              "stroke-width": 2
            },
            selected: {
              fill: 'blue'
            },
            selectedHover: {}
          },
        });
        // Manual code to alter the Vector map plugin to 
        // allow for individual coloring of countries
        var states = ['US-CA', 'US-TX', 'US-MO',
          'US-NY'
        ];
        var colors = [bgWarningLr, bgPrimaryLr, bgInfoLr, bgAlertLr];
        var colors2 = [bgWarning, bgPrimary, bgInfo, bgAlert];
        $.each(states, function(i, e) {
          $("#WidgetMap path[data-code=" + e + "]").css({
            fill: colors[i]
          });
        });
        $('#WidgetMap').find('.jvectormap-marker')
          .each(function(i, e) {
            $(e).css({
              fill: colors2[i],
              stroke: colors2[i]
            });
          });
      }

      if ($('#WidgetMap').length) {
        runJvectorMap();
      }
    }
    
  });  
//////////////////WebShims Polyfill///////////////////////
  webshims.setOptions('forms-ext', {types: 'date'});
  webshims.polyfill('forms forms-ext');
  webshims.cfg.no$Switch='true';
  $.webshims.formcfg = {
  en: {
      dFormat: '/',
      dateSigns: '/',
      patterns: {
          d: "mm/dd/yy"
      }
  }
  };  

////////////////////////////////////Modal///////////////////////////////////////////  
var modal = document.getElementById('myModal');
var span = document.getElementsByClassName("close")[0];
//span.onclick = function() {
//    modal.style.display = "none";
//}
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
function displayModal()
{
	modal.style.display = "block";	
}
function hideModal()
{
	modal.style.display = "none";
}  



function overlayOn() {
    document.getElementById("overlay").style.display = "block";
}
</script>

  <!-- END: PAGE SCRIPTS -->