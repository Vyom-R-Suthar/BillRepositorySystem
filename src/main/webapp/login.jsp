<!-- Jai Swaminarayan KESHAV , Swami-Shreeji -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.billrepository.entities.User" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    
    <script src="https://kit.fontawesome.com/356c2b5dab.js" crossorigin="anonymous"></script>

    <link rel="preconnect" href="https://fonts.googleapis.com">

    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

    <link href="https://fonts.googleapis.com/css2?family=Wix+Madefor+Display&display=swap" rel="stylesheet">
    
    <link rel="stylesheet" type="text/css" href="css/login.css"/>
    
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
	
	<%
		User user = (User)session.getAttribute("user");
		if(user != null){
			response.sendRedirect("index2.jsp");
		}
		
	%>
	<input type="hidden" id="message" value="<%=request.getAttribute("message") %>" />
    <nav class="navbar navbar-dark bg-dark">
        <span>Bill Repository System</span>
    </nav>

    <div class="fContainer">
        <form action="LoginServlet" method="post">
        <div class="loginContainer">
            <div class="loginHeading">
                <h1>Login</h1>
            </div>
            <!--<form class="loginForm" action="LoginServlet">-->
                <div class="loginFields">
                    <div class="field1">
                        <p><i class="fa-solid fa-user" style="color: #ffffff;"></i>Enter code </p>
                        <input type="text" name="uniqueCode" required/>
                    </div>
                    <div class="field1">
                        <p><i class="fa-solid fa-lock" style="color: #ffffff;"></i>Password </p>
                        <input type="password" name="password" required/>
                    </div>
                    <div class="login">
                        <button type="submit">Login</button>
                        <p>Don't have an account?<a href="register2.jsp">Create one</a></p>
                    </div>
                </div>
            <!--</form>-->
        </div>
        </form>
    </div>
    
    <div class="footer">
        <p>© Bill Repository System 2023</p>
    </div>
    
    <script type="text/javascript">
    	var message = document.getElementById("message").value;
    	if(message == "Invalid credentials"){
    		Swal.fire(
		 			  'Invalid credentials!',
		 			  'try again',
		 			  'error'
		 			)
    	}
    </script>
</body>
</html>