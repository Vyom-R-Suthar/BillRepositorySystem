<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.billrepository.entities.User"%>
<!-- Jai Swaminarayan KESHAV , Swami-Shreeji -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>

    <script src="https://kit.fontawesome.com/356c2b5dab.js" crossorigin="anonymous"></script>

    <link rel="preconnect" href="https://fonts.googleapis.com">

    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

    <link href="https://fonts.googleapis.com/css2?family=Wix+Madefor+Display&display=swap" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="css/header.css"/>

    <link rel="stylesheet" type="text/css" href="css/footer.css"/>

    <link rel="stylesheet" type="text/css" href="css/register2.css"/>
    
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
    <%--
    	String message = (String)request.getAttribute("message");
    	if(message != null){
    		--%>
    		<script>
    			alert(message);
    		</script>
    		<%--
    	}
    --%>  
    
    <%
    	User user = (User)session.getAttribute("tempRegData");
    %>
    
    <nav class="navbar navbar-dark bg-dark">
        <span>Bill Repository System</span>
    </nav>
    <form action="OtpGeneratorServlet" method="post" enctype="multipart/form-data">
      <div class="fContainer">
        <div class="headingContainer">
            <h1>Register</h1>
        </div>
        
        <%
        	String message = (String)request.getAttribute("message");
        	if(message != null && message == "Successfully inserted"){
        		%>
        		 <script>
        		 	/*alert("<%=message%>");*/
        		 	Swal.fire(
        		 			  'Successfully registered!',
        		 			  'go to login page',
        		 			  'success'
        		 			)
        		 </script>
        		<%	
        	}else if(message != null && message == "This code already exists"){	
        %>
        		<script>
        		 	Swal.fire(
        		 			  'The code already exists!',
        		 			  'try with different code',
        		 			  'error'
        		 			)
        		 </script>
        <%
        	}else if(message != null && message == "Some error occured , please try again"){
        %>
        		<script>
        		 	Swal.fire(
        		 			  'Some error occured , please try again',
        		 			  'try again',
        		 			  'warning'
        		 			)
        		 </script>
        <%
        	}else if(message != null && message == "Could not send OTP, please try again later"){
        		 %>
         		<script>
         		 	Swal.fire(
         		 			  'Could not send OTP, please try again later',
         		 			  'try again',
         		 			  'error'
         		 			)
         		 </script>
         <%
        	}
        %>
        <div class="billContainer">
            <div class="container1">
                <div class="field1">
                    <p>User Name</p>
                    <input type="text" name="userName" value="<%String val1=(user != null)?user.getUserName():null;%>" required>
                </div>
                <div class="field1">
                    <p>Firm Name</p>
                    <input type="text" name="firmName" value="<%String val2=(user != null)?user.getFirmName():null; %>" required>
                </div>
                <div class="field1">
                    <p>Mobile No.</p>
                    <input type="text" name="mobileNo" value="<%String val3=(user != null)?user.getMobileNo():null; %>" required pattern="\d{10}" placeholder="must be 10 digits">
                </div>
                <div class="field1">
                    <p>Address</p>
                    <input type="text" name="address" value="<%String val4=(user != null)?user.getAddress():null; %>" required>
                </div>
            </div>
            <div class="container2">
                <div class="field1">
                    <p>Password</p>
                    <input type="password" name="password" value="<%String val5=(user != null)?user.getPassword():null;%>" required>
                </div>
                <div class="field1">
                    <p>Passport size photo</p>
                    <input type="file" name="photo" required>
                </div>
                <div class="field1">
                    <p>Unique Code</p>
                    <input type="text" name="uniqueCode" value="<%String val6=(user != null)?user.getUniqueCode():null;%>" required>
                </div>
                <div class="register">
                    <button type="submit">Register</button>
                    <p>Already have an account?<a href="login.jsp">Login</a></p>
                </div>
            </div>
        </div>
      </div>
    </form>
      <div class="footer">
        <p>© Bill Repository System 2023</p>
    </div>
    
</body>
</html>