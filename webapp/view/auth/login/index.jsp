<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login | ${initParam.projectName} </title>
    <link rel="stylesheet" href="loginstyle.css">
    <script src="/assets/js/global/jquery-3.6.1.min.js"></script>
    <!-- <link rel="stylesheet" href="assets/css/bootstrap.min.css"> -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@300&display=swap" rel="stylesheet">
    <link rel="icon" type="image/x-icon" href="/zobus/assets/images/ZOBUS_LOGO.png">
    
    <!-- for reCapche -->
    <script src="https://www.google.com/recaptcha/api.js"></script> 
</head>
<body>
<!-- loginPage -->
<div class="loginpage">
    <div class="loginbox">
        <div class="logo"></div>
        <p>Sign in to your account </p>
        <input name="username" id="user" type="text" placeholder="Username">
        <input name="password" id="pwd" class="password" type="text" placeholder="password">
        <div class="signinsignup" id="actionBar">
            <a id="forget"href="#">forget password</a>
        </div>
 <!--   <div class="submitBtn"><button type="submit" id="sub" onclick="verification()">Login</button></div>  -->
        <div class="submitBtn"><button type="button" id="signinBtn" >Login</button></div>
        <form action="signup" method="get">
         	<button type="submit" id="signup"> create new account</button>
    	</form>
    </div>
   <div class="busClip"></div>
</div>
</body>
</html> 