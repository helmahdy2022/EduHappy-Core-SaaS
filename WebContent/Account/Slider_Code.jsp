<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
* {box-sizing: border-box}
body {font-family: Verdana, sans-serif; margin:0}
.mySlides {display: none}
img {vertical-align: middle;}

/* Slideshow container */
.slideshow-container {
  max-width: 100%;
  max-height: 600px;
  position: relative;
  margin: auto;
}

/* Next & previous buttons */
.prev, .next {
  cursor: pointer;
  position: absolute;
  top: 50%;
  width: auto;
  padding: 16px;
  margin-top: -22px;
  color: #FFFFFFCC;
  font-weight: bold;
  font-size: 36px;
  transition: 0.6s ease;
  border-radius: 0 3px 3px 0;
  user-select: none;
}

/* Position the "next button" to the right */
.next {
  right: 0;
  border-radius: 3px 0 0 3px;
}

/* On hover, add a black background color with a little bit see-through */
.prev:hover, .next:hover {
  background-color: rgba(0,0,0,0.8);
}

/* Caption text */
.text1 {

  padding: 8px 12px;
  position: absolute;
  bottom: 400px;
  width: 100%;
  text-align: center;
  font-family: 'Libre Franklin','Arial';
  color: #fff;
  font-size: 95%;
  text-shadow: none;
  line-height: 1.5;
  font-weight: 800;
  font-style: normal;
  text-decoration: none;
  letter-spacing: 1px;
  word-spacing: normal;
  text-transform: uppercase; 
  box-sizing: content-box;
 
}
.text2 {
  padding: 8px 12px;
  position: absolute;

  width: 100%;
  text-align: center;
  font-family: 'Libre Baskerville','Arial';
  color: #fff;
  font-size: 237.5%;
  text-shadow: none;
  line-height: 1.5;
  font-weight: 700;
  font-style: normal;
  text-decoration: none;
  letter-spacing: 1px;
  word-spacing: normal;
  text-transform: none;
  font-weight: 700;  
}
.text3 {
  color: #f2f2f2;
  font-size: 15px;
  padding: 8px 12px;
  position: absolute;
  width: 100%;
  text-align: center;
  font-family: 'Libre Franklin','Arial';
  color: rgba(255,255,255,.64);
  font-size: 150%;
  text-shadow: none;
  line-height: 1.5;
  font-weight: 400;
  font-style: normal;
  text-decoration: none;
  letter-spacing: normal;
  word-spacing: normal;
  text-transform: none;  
}
/* The dots/bullets/indicators */
.dot {
  cursor: pointer;
  height: 15px;
  width: 15px;
  margin: 0 2px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  transition: background-color 0.6s ease;
}

.active, .dot:hover {
  background-color: #717171;
}

/* Fading animation */
.fade {
  -webkit-animation-name: fade;
  -webkit-animation-duration: 1.5s;
  animation-name: fade;
  animation-duration: 1.5s;
}

@-webkit-keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

@keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

/* On smaller screens, decrease text size */
@media only screen and (max-width: 300px) {
  .prev, .next,.text {font-size: 11px}
}
</style>
</head>
<body>

<div class="slideshow-container">

<div class="mySlides fade">
  <img src="${pageContext.request.contextPath}/img/Boy_Inside_School_600.jpg" style="width:100%; filter: brightness(55%);">
  <div class="text1">  
		PAPERLESS ENVIRONMENT 
  </div>
  <div class="text2"  style=" bottom: 335px;">
		Are you ready to become paperless and Go-Online?  
  </div>
  <div class="text3" style=" bottom: 300px;">
		Most schools across the globe do
  </div>
</div>

<div class="mySlides fade">
  <img src="${pageContext.request.contextPath}/img/Boy_hand_600.jpg" style="width:100%; filter: brightness(55%);">
  <div class="text1">  
		DIGITALIZE YOUR SCHOOL<br>
  </div>
  <div class="text2" style=" bottom: 280px;">
	Parents are looking for schools that are competent <br> when it comes to technology  
  </div>
  <div class="text3" style=" bottom: 235px;">
		Your school can be one of those
  </div>
</div>

<div class="mySlides fade">
  <img src="${pageContext.request.contextPath}/img/girl_with_laptop_600.jpg" style="width:100%; filter: brightness(55%);">
  <div class="text1">  
		GREAT VALUE  
  </div>
  <div class="text2" style=" bottom: 335px;">
		Efficient. Productive. Cost-effective.  
  </div>
  <div class="text3" style=" bottom: 300px;">
		Save cost, increase profitability, and maximize ROI 
  </div>


</div>

<a class="prev" onclick="plusSlides(-1)">&#10094;</a>
<a class="next" onclick="plusSlides(1)">&#10095;</a>

</div>

<div style="text-align:center">
  <span class="dot" onclick="currentSlide(1)"></span> 
  <span class="dot" onclick="currentSlide(2)"></span> 
  <span class="dot" onclick="currentSlide(3)"></span> 
</div>

<script>
var slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
  showSlides(slideIndex += n);
}

function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("dot");
  if (n > slides.length) {slideIndex = 1}    
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";  
  }
  for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";  
  dots[slideIndex-1].className += " active";
}

</script>

</body>
</html> 