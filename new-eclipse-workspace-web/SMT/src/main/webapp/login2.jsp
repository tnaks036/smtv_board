<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');

* {margin: 0; padding: 0; box-sizing: border-box;}
body{
    font-family: 'Noto Sans KR', sans-serif;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background: url("bg.jpg") no-repeat center;
    background-color: cover;
 }
 body::before{
    content: "";
    position: absolute; z-index: 1;
    top:0; right: 0; bottom:0; left: 0;
    background-color: rgba(0, 0, 0, 0);
 }

 .login-form{position: relative; z-index: 2;}
 .login-form h1{ 
    font-size: 32px; color:#fff;
    text-align: center;
    margin-bottom: 60px;
 }

 .int-area{width: 400px; position: relative;
    margin-top: 20px;}
.int-area:first-child{margin-top: 0;}
 .int-area input{
    width: 100%;
    padding: 20px 10px 10px;
    background-color: transparent;
    border:none;
    border-bottom: 1px solid #999;
    font-size: 18px; color:#fff;
    outline:none;
}

.int-area label{
    position: absolute; left: 10px; top: 15px;
    font-size:18px; color: #999;
    transition: top .5s ease;
}

.int-area label.warning{
    color: red !important;
    animation: warning .3s ease;
    animation-iteration-count:3;
}
@keyframes warning{ 
    0% {transform: translateX(-8px);}
    25% {transform: translateX(8px);}
    50% {transform: translateX(-8px);}
    75% {transform: translateX(8px);}
}



.int-area input:focus + label,
.int-area input:valid + label {
    top:-2px;
    font-size: 13px; color: #166cea;
}

.btn-area {margin-top: 30px;}
.btn-area button{
    width: 100%; height: 50px;
    background: #166cea;
    color: #fff;
    font-size: 20px;
    border: none;
    border-radius: 25px;
    cursor: pointer;
}

.caption{
    margin-top: 20px;
    text-align: center;
}
.caption a{
    font-size: 15px; color: #999;
    text-decoration: none;
}

</style>
<title>Insert title here</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
</head>
<body>
<c:import url="header.jsp" />
 <section class="login-form">
        <h1>LOGO DESIGN</h1>
        <form action="">
            <div class="int-area">
                <input type="text" name="id" id="id"
                autocomplete="off" required>
                <label for="id">USER NAME</label> 
            </div>
            <div class="int-area">
                <input type="password" name="pw" id="pw"
                autocomplete="off" required>
                <label for="pw">PASSWORD</label> 
            </div>
            <div class="btn-area">
                <button id="btn"
                type="submit">LOGIN</button>
            </div>
        </form>
        <div class="caption">
            <a href="">Forgot Password?</a>
        </div>
    </section>

    <script>
        let id = $('#id');
        let pw = $('#pw');
        let btn = $('#btn');

        $(btn).on('click', function(){
            if($(id).val() == ""){
                $(id).next('label').addClass('warning');
                
            }
            else if($(pw).val()==""){
                $(pw).next('label').addClass('warning');
 
            }
        });
    </script>
</body>
</html>