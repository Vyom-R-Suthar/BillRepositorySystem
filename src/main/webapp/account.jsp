<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.billrepository.entities.User,java.util.List"%>
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

    <link rel="stylesheet" type="text/css" href="css/header.css" />

    <link rel="stylesheet" type="text/css" href="css/footer.css" />

    <link rel="stylesheet" type="text/css" href="css/account.css" />
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
			List<User> myDealers = (List<User>)session.getAttribute("myDealers");
			List<User> myDistributors = (List<User>)session.getAttribute("myDistributors");
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
                <a class="nav-link active navField2" href="account.jsp">account <i class="myFontAwsome fa-regular fa-user"></i></a>
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

      <div class="fContainer">
        
        <div class="userContainer">
            <div class="userPhotoContainer">
                <img src="img/<%= user.getUniqueCode() %>.jpg" alt="">
            </div>
            <div class="userInfoContainer">
                <div class="info">
                <p><%= user.getUserName() %></p>
                <p><%= user.getFirmName() %></p>
                <p><%= user.getMobileNo() %></p>
                <p>Address : <%= user.getAddress() %></p>
                <p>Unique Code : <%= user.getUniqueCode() %></p>
                </div>

                <div class="buttons">
                    <button class="updateButton">Update</button>
                    <button class="deleteButton">Delete</button>
                </div>
            </div>
        </div>

        <div class="ddContainer">
            <div class="dealerContainer">
                <h2>My dealers</h2>
                    <table class="dealerTable">
                    	<% int i = 0;
                    	for(User dealer:myDealers){
							i++;              	
                    	%>
                        <tr>
                            <td><%= i %></td>
                            <td><img class="ddImage" src="img/<%= dealer.getUniqueCode() %>.jpg"></td>
                            <td><a style="color:white;text-decoration:none" href="VerifyDealerServlet?dealerCode=<%= dealer.getUniqueCode() %>"><%= dealer.getFirmName() %></a></td>
                            <td><i class="fa-solid fa-trash fa-lg" style="color: #e62d2d;"></i></td>
                            <td><a href="https://wa.me/91<%= dealer.getMobileNo() %>" target = "_blank"><i class="fa-brands fa-whatsapp fa-xl" style="color: #08e769;"></a></i></td>
                        </tr>
                        <%
                    	}
                        %>
                    </table>
            </div>
            <div class="distributorContainer">
                <h2>My distributors</h2>
                <table class="dealerTable">
                <%  int i1 = 0;
                	for(User distributor: myDistributors){ 
                	i1++;
                	%>
                    <tr>
                        <td><%= i1 %></td>
                        <td><img class="ddImage" src="img/<%= distributor.getUniqueCode() %>.jpg"></td>
                        <td><%= distributor.getFirmName() %></td>
                        <td><i class="fa-solid fa-trash fa-lg" style="color: #e62d2d;"></i></td>
                        <td><a href="https://wa.me/91<%= distributor.getMobileNo() %>" target="_blank"><i class="fa-brands fa-whatsapp fa-xl" style="color: #08e769;"></i></a></td>
                    </tr>
                    <%
                	}
                    %>
                </table>  
            </div>
        </div>
      </div>
	<%
		}
	%>
      <div class="footer">
        <p>© Bill Repository System 2023</p>
    </div>
</body>
</html>