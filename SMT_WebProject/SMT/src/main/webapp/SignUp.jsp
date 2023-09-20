<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>	
<link rel="stylesheet" type="text/css" href="css/Login_Join.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href='https://fonts.googleapis.com/css?family=Roboto:500,900,100,300,700,400' rel='stylesheet' type='text/css'>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> 
<meta charset="UTF-8">
    <title>User Signup</title>
</head>
<body>
    <h1>User Signup</h1>
    <form id="signupForm" method="post" action ="/SignupServlet">
        <label for="user_ID">User_ID</label>
        <input type="text" id="user_ID" name="user_ID" required><br><br>
        <label for="user_PW">Password:</label>
        <input type="password" id="password" name="user_PW" required onchange="check_pw()"><br><br>
        <label for="user_PW">Check Password:</label>
        <input type="password" id="password" name="user_PW2" required onchange="check_pw()"><br><br>
        <label for="phone_Num">PhoneNumber:</label>
        <input type="text" id="phone_Num" name="phone_Num" required><br><br>
        <label for="corp_Name">CompanyName:</label>
        <input type="text" id="corp_Name" name="corp_Name" required><br><br>
        <button type="submit">Signup</button>
    </form>

    <script type="text/javascript">
	 // js/signup.js
        document.getElementById("signupForm").addEventListener("submit", function(event) {
    	event.preventDefault(); // Prevent form from submitting normally

        // Get form data
        var formData = new FormData(this);

        // Create AJAX request
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "SignupServlet");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    alert("Signup successful!");
                    // Redirect to login page
                    window.location.href = "login_Join.jsp";
                } else {
                    alert("Signup failed. Please try again.");
                }
            }
        };

        // Send form data
        xhr.send(formData);
    });
	 
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