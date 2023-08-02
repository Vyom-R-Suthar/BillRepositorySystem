<!-- Jai Swaminarayan KESHAV , Swami-Shreeji  -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.billrepository.entities.User,com.billrepository.constants.SessionDealerCounter" %>
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

    <link rel="stylesheet" type="text/css" href="css/dealerDetails.css" />
    
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
			User dealer = (User)request.getAttribute("dealer");
			SessionDealerCounter.counter++;
			String mySessionDealerID = "dealer"+SessionDealerCounter.counter;
			String mySessionDealerIDBillList = "dealer"+SessionDealerCounter.counter+"BillList";
			session.setAttribute(mySessionDealerID, dealer);
			session.setAttribute(mySessionDealerIDBillList, null);
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
              </li>
              </form>
            </ul>
            
          </div>
        </div>
      </nav>

      <div class="fContainer">
        <div class="detailsContainer">
        	<% String subpath = dealer.getPhotoPath();
        		String path = "img/"+subpath+".jpg";	
        	%>
            <img class="details" src="<%=path%>" alt="Image not available">
            <h1 class="details">Dealer Name : <%= dealer.getUserName()%></h1>
            <p class="details">"<%= dealer.getFirmName() %>"</p>
            <p class="details"><%= dealer.getAddress() %></p>
            <p class="details">Mo no. <%= dealer.getMobileNo() %></p>
            <p class="details">firm code "<%= dealer.getUniqueCode() %>"</p>
        </div>
        <div class="buttonContainer">

            <a href="AssignNewBill.jsp?mySessionDealerID=<%= mySessionDealerID%>">
            <div class="myButton">
                <h4>Assign a new bill</h4>
            </div>
            </a>

            <a href="DealerBillsServlet?mySessionDealerID=<%= mySessionDealerID%>&mySessionDealerIDBillList=<%= mySessionDealerIDBillList %>">
                <div class="myButton">
                    <h4>View Assigned bills</h4>
                </div>
            </a>
        </div>
      </div>

      <div class="footer">
        <p>© Bill Repository System 2023</p>
    </div>
    <%
	}
    %>
</body>
</html>