<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.billrepository.entities.User" %>
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

    <link rel="stylesheet" type="text/css" href="css/addDistributor.css"/>
    
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    
    <script type="text/javascript">
    	function logout(){
    		if(confirm("Logout from the app ?")){
    			document.getElementById("logoutButton").submit();
    		}
    	}
    </script>
    
</head>
<body>
	
	<%
		User user = (User)session.getAttribute("user");
		if(user == null){
			response.sendRedirect("login.jsp");
		}else{
	%>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
          <a class="navbar-brand" href="index2.jsp">Bill Repository System</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link active navField1" href="#">messages <i class="myFontAwsome fa-regular fa-message" style="color: white;"></i></a>
              </li>
              <li class="nav-item">
                <a class="nav-link active navField2" href="#">account <i class="myFontAwsome fa-regular fa-user"></i></a>
              </li>
              <li onclick="logout()" class="nav-item">
              	<form id="logoutButton" method="post" action="LogoutServlet">
                <a class="nav-link active navField3" style="cursor:pointer;">logout <i class="myFontAwsome fa-solid fa-power-off"></i></a>
                </form>
              </li>
            </ul>
            
          </div>
        </div>
      </nav>
	  
	  <% 
	  	String message = (String)request.getAttribute("message"); 
	  	if(message != null && message.equals("Distributor does not exists OR the code is wrong")){
	  		%>
	  		<script>
	  		const Toast = Swal.mixin({
	  		  toast: true,
	  		  position: 'top-end',
	  		  showConfirmButton: false,
	  		  timer: 3000,
	  		  timerProgressBar: true,
	  		  didOpen: (toast) => {
	  		    toast.addEventListener('mouseenter', Swal.stopTimer)
	  		    toast.addEventListener('mouseleave', Swal.resumeTimer)
	  		  }
	  		})

	  		Toast.fire({
	  		  icon: 'error',
	  		  title: 'Distributor does not exists OR the code is wrong'
	  		})
	  		</script>
	  		<%
	  	}else if(message != null && message.equals("Distributor was already added")){
	  		%>
	  			<script>
	  			const Toast = Swal.mixin({
	  		  	toast: true,
	  		  	position: 'top-end',
	  		  	showConfirmButton: false,
	  		  	timer: 3000,
	  		  	timerProgressBar: true,
	  		  	didOpen: (toast) => {
	  		    toast.addEventListener('mouseenter', Swal.stopTimer)
	  		    toast.addEventListener('mouseleave', Swal.resumeTimer)
	  		  	}
	  			})

	  			Toast.fire({
  		  		icon: 'warning',
	  		  	title: 'Distributor was already added'
	  			})
	  		</script>
	  		<%
	  	}else if(message != null && message.equals("Distributor added successfully")){
	  		%>
	  			<script>
	  			const Toast = Swal.mixin({
	  		  	toast: true,
	  		  	position: 'top-end',
	  		  	showConfirmButton: false,
	  		  	timer: 3000,
	  		  	timerProgressBar: true,
	  		  	didOpen: (toast) => {
	  		    toast.addEventListener('mouseenter', Swal.stopTimer)
	  		    toast.addEventListener('mouseleave', Swal.resumeTimer)
	  		  	}
	  			})

	  			Toast.fire({
  		  		icon: 'success',
	  		  	title: 'Distributor added successfully'
	  			})
	  		</script>
	  		<%
	  	}else if(message != null && message.equals("Some error occured , try again")){
	  		%>
	  			<script>
	  			const Toast = Swal.mixin({
	  		  	toast: true,
	  		  	position: 'top-end',
	  		  	showConfirmButton: false,
	  		  	timer: 3000,
	  		  	timerProgressBar: true,
	  		  	didOpen: (toast) => {
	  		    toast.addEventListener('mouseenter', Swal.stopTimer)
	  		    toast.addEventListener('mouseleave', Swal.resumeTimer)
	  		  	}
	  			})

	  			Toast.fire({
  		  		icon: 'error',
	  		  	title: 'Some error occured , try again'
	  			})
	  		</script>
	  		<%
	  	}else if(message != null && message.equals("Invalid distributor code")){
	  		%>
	  			<script>
	  			const Toast = Swal.mixin({
	  		  	toast: true,
	  		  	position: 'top-end',
	  		  	showConfirmButton: false,
	  		  	timer: 3000,
	  		  	timerProgressBar: true,
	  		  	didOpen: (toast) => {
	  		    toast.addEventListener('mouseenter', Swal.stopTimer)
	  		    toast.addEventListener('mouseleave', Swal.resumeTimer)
	  		  	}
	  			})

	  			Toast.fire({
  		  		icon: 'error',
	  		  	title: 'Invalid distributor code'
	  			})
	  		</script>
	  		<%
	  	}
	  %>
      <div class="fContainer">
      	<form method="post" action="AddDistributorServlet">
        <div class="distributorContainer">
            <div class="field1">
                <p>Enter Distributor's Code </p>
                <input type="text" name="distributorCode"/>
                <button type="submit">Add Distributor</button>
            </div>
        </div>
        </form>
      </div>

      <div class="footer">
        <p>© Bill Repository System 2023</p>
      </div>
      <%
		}
      %>
</body>
</html>