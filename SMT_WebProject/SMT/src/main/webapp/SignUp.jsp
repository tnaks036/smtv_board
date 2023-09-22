<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>	
<link rel="stylesheet" type="text/css" href="css/SignUp_Login.css">
<script defer src="SignUp.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href='https://fonts.googleapis.com/css?family=Roboto:500,900,100,300,700,400' rel='stylesheet' type='text/css'>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> 
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>User Signup</title>
</head>
<body>
    <main id="main-holder">
	    <h1 id="login-header">SignIn</h1>	
	    	    
	    <div id="login-error-msg-holder">
	      <p id="login-error-msg">Invalid username <span id="error-msg-second-line">and/or password</span></p>
    </div>
	
	<form id="login-form" method="post" action ="/SignupServlet" >
		<input type="text" name="user_ID" id="username-field" class="login-form-field" placeholder="ID">
		<input type="password" name="user_PW" id="password-field" class="login-form-field" placeholder="Password" 
		>&nbsp;<span style="color:cadetblue">보안성</span><progress id="pw_pro" value="0" max="3">
														</progress>&nbsp;<span id="pw_pro_label"></span>
		<input type="password" name="user_PW2" id="password-field" class="login-form-field" placeholder="Check Password"
		onchange="check_PW()">
		<input type="text" name="pheon_Num" id="phonenumber-field" class="login-form-field" placeholder="Phone Number" > 
		<input type="text" name="corp_Name" id="Corpname-field" class="login-form-field" placeholder="Company name" > 
		<input type="submit" value="Login" id="login-form-submit">
	</form>
    <!--  -->
    </main>
    <script type="text/javascript"> 
	 function check_PW(){
		 
		 var pw = document.getElementById('user_PW').value;
		 var SC = ["!","@","#","$","%"];
         var check_SC = 0;
		 
         if(pw.length<6||pw.length>16){
        	 window.alert('비밀번호는 6글자 이상, 16글자 이하만 사용 가능합니다.');
        	 document.getElementById('user_PW').value=";
         }
         for(var i=0;i<SC.length;i++){
             if(pw.indexOf(SC[i]) != -1){
                 check_SC = 1;
             }
         }
         if(check_SC == 0){
             window.alert('!,@,#,$,% 의 특수문자가 들어가 있지 않습니다.')
             document.getElementById('user_PW').value='';
         }
         if(document.getElementById('user_PW').value !='' && document.getElementById('user_PW2').value!=''){
             if(document.getElementById('user_PW').value==document.getElementById('user_PW2').value){
                 document.getElementById('check').innerHTML='비밀번호가 일치합니다.'
                 document.getElementById('check').style.color='blue';
             }
             else{
                 document.getElementById('check').innerHTML='비밀번호가 일치하지 않습니다.';
                 document.getElementById('check').style.color='red';
             }
         }
     }
	 
        /*function signup() {
            var username = document.getElementById("user_ID").value;
            var password = document.getElementById("user_PW").value;
            var phone_Num = document.getElementById("phone_Num").value;
            var corp_Name = document.getElementById("corp_Name").value;
            // Create XMLHttpRequest object
            var xhttp = new XMLHttpRequest();

            // Define callback function for when the request completes
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    var response = this.responseText;

                    // Process the response from the server
                    if (response === "Signup successful") {
                        alert("Signup successful");
                    } else {
                        alert("User ID already exists");
                    }
                }
            };

            // Send the request to the server
            xhttp.open("POST", "/signup", true);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send("user_ID=" + user_ID + "&user_PW=" + user_PW);
        }*/
    </script>
</body>
</html>