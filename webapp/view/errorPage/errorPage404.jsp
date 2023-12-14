<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Page Not found</title>
<style type="text/css">
	body {
    font-family: Trebuchet MS;
    padding: 0;
    margin: 0;
    overflow: hidden;
}

a {
    text-decoration: none;
}
	.errorPage {
    background: url(https://img.freepik.com/premium-vector/straight-road-with-railings-through-meadow-trees-landscape-nature-highway-background_338371-621.jpg);
    background-size: cover;
    background-position: bottom;
    width: 100%;
    height: 100vh;
} 

img.errorPage__tembleweed {
    animation: templeWeed 7s infinite 1s;
   	mix-blend-mode: multiply;
   	width: 50vh;
   	left:50%;
   	pointer-events: none;
}

.errorPage__templeweed-container {
    position: absolute;
    bottom: 20px;
    width: 10vw;
	top:0px;
    animation: moveWeed 9s infinite linear;
    z-index: -10;
}

.pnf_text{
	color: #995050;
	text-shadow: 4px 4px 7px #FFD840;
	display: flex;
	font-size: x-large;
	width: 100%;
	padding-top: 35%;
}

.errorPage__text {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%,-50% );
    margin-top: -5%;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
}

.errorPage__button {
    display: block;
    cursor: pointer;
    border: 1px solid #fff;
    color: #e3594e;
    padding: 10px 30px;
    letter-spacing: 0.2em;
    box-shadow: 7px 7px 20px #00000026;
    background: #fff;
    transition: transform 300ms ease;
}

.errorPage__button:active {
    transform: translateY(2px);
    box-shadow: 3px 3px 20px #00000026;
}

.errorPage__text h3 {
    font-size: 20vw;
    line-height: 1;
    margin: 0;
    letter-spacing: 0.2em;
    margin-bottom: 50px;
    color: #fff;
    text-shadow: 7px 7px 20px #00000026;
    z-index:-11;
}

.hamburger__menu {
    color: #fff;
    font-size: 30px;
    font-weight: 300;
    display: block;
    margin-top: -6px;
    cursor: pointer;
}

@media only screen and (min-width: 768px) {
  .header__menu .hamburger__menu {
    display: none;
  }
}

@media only screen and (max-width: 767px) {
  .header__menu ul {
    display: none;
  }
}

@keyframes moveWeed {
    0% {
        top: -100%;  
        transform: rotate(0deg);
        transform: scale(200%);
    }
    50%{
    	transform: scale(350%);
    }
    
    100% {
        transform: translateY(110%) rotate(360deg);
        top: 150%;
    }
}

@keyframes templeWeed {
    0% {
        transform: translateX(-3);
    }
    
    30% {
        transform: translateX(-4vw);
    }
    
    60% {
        transform: translateX(0vw);
    }
    
    90% {
        transform: translateX(6);
    }
    
    90% {
        transform: translateX(4);
    }
    
}
</style>
</head>
<body>
 
    <section class="errorPage">  
        <div class="errorPage__text">
            <h3>404</h3> 
            <p class="errorPage__text pnf_text">Page not found</p>
        	<span class="errorPage__templeweed-container"><img src="https://clipart-library.com/images_k/leaves-falling-transparent-background/leaves-falling-transparent-background-15.png" class="errorPage__tembleweed"></span>
        	<div class="errorPage__terrain"></div>
			<form action="<%=request.getContextPath()%>">
				<button class="errorPage__button" type="submit">Go to Home</button>
			</form>
        </div>
    </section>

</body>
</html>