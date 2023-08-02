<!-- Jai Swaminarayan KESHAV , Swami-Shreeji -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.billrepository.entities.User,com.billrepository.entities.Bill,java.util.List,java.util.ArrayList,java.text.SimpleDateFormat,java.util.Date,java.text.ParseException,java.time.LocalDateTime" %>
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

    <link rel="stylesheet" type="text/css" href="css/bills.css" />
    
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    
    <script type="text/javascript">
    	function logout(){
    		if(confirm("Logout from the app ?")){
    			document.getElementById("logoutButton").submit();
    		}
    	}
    </script>
    
    <script type="text/javascript">
    	function billStatusFunction(){
    		document.getElementById("billStatusForm").submit();
    	}
    	
    	function billSortingFunction(){
    		document.getElementById("billSortingForm").submit();
    	}
    	function distributorFunction(){
    		document.getElementById("distributorForm").submit();
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
          <a class="navbar-brand" href="index2.html">Bill Repository System</a>
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
                <a class="nav-link active navField3" style="cursor:pointer">logout <i class="myFontAwsome fa-solid fa-power-off"></i></a>
              </form>
              </li>
            </ul>
            
          </div>
        </div>
      </nav>

      <div class="fContainer">
            <div class="optionsContainer">
            	<form id="billStatusForm" method="post" action="BillsAccordingToStatusServlet">
                <div class="option">
                    <p class="pText">Bill status : </p>
                    <select onchange="billStatusFunction()" name="billStatus" id="billStatus">
                    	<option value="select">Select</option>
                        <option value="all">All</option>
                        <option value="unpaid" >Unpaid</option>
                        <option value="paid" >Paid</option>
                    </select>
                </div>
                </form>
                <form id="billSortingForm" method="post" action="BillsAccordingToSortingServlet">
                <div class="option">
                    <p>Sorting criteria : </p>
                    <select onchange="billSortingFunction()" name="sortingCriteria" id="sortingCriteria">
                    	<option value="select">Select</option>
                        <option value="none">none</option>
                        <option value="accordingToDate" >according to date</option>
                        <option value="accordingToLeftDays" >according to left days</option>
                        <option value="accordingToAmount">according to amount</option>
                    </select>
                </div>
                </form>
                <form id="distributorForm" method="post" action="BillsAccordingToDistributor">
                <div class="option">
                    <p class="pText">Distributor : </p>
                    <select onchange="distributorFunction()" name="distributorCriteria" id="billStatus">
                    	<option value="select">Select</option>
                        <option value="all">All</option>
                        <% 
                        	List<User> distributors = (List<User>)session.getAttribute("distributorList");
                        	for(User distributor : distributors){
                        %>
                        <option value="<%= distributor.getUniqueCode() %>" ><%= distributor.getUniqueCode() %></option>
                        <% 
                        	}
                        %>
                    </select>
                </div>
                </form>
            </div>
            <div class="amountContainer">
            	<h4>Total Amount to be paid : 
            	<%  
            	List<Bill> billList1 = (List<Bill>)session.getAttribute("billList");
            	double amount = 0;
            	for(Bill bill : billList1){
            		if(bill.getBillStatus().equalsIgnoreCase("unpaid")){
            			amount += bill.getBillAmount();
            		}
            	}
            	out.print(amount + " Rs.");
            	%>
            	</h4>
            </div>
             
            <div class="tableContainer">
                <div class="table-responsive">
                <table class="table table-striped table-fixed">
                    <thead class="table-dark">
                      <tr>
                        <th scope="col">#</th>
                        <th scope="col">Distributor Code</th>
                        <th scope="col">Bill no</th>
                        <th scope="col">Bill date</th>
                        <th scope="col">Bill amount</th>
                        <th scope="col">Due Days</th>
                        <th scope="col">Left Days</th>
                        <th scope="col">Bill status</th>
                        <th scope="col">Bill PDF</th>
                        <th scope="col">Receipt PDF</th>
                      </tr>
                    </thead>
                    <tbody class="myTBody">
                    <%  List<Bill> billList = new ArrayList<>(); 
                    	billList = (List<Bill>)session.getAttribute("billList");
                    	int i = 0;
                    	for(Bill bill : billList){
                    	i++;
                    %>
                      <tr>
                        <th scope="row"><%= i %></th>
                        <td><%= bill.getDistributorCode() %></td>
                        <td><%= bill.getBillNo() %></td>
                        <td><%= bill.getBillDate() %></td>
                        <td><%= bill.getBillAmount() %></td>
                        <td><%= bill.getDueDays() %></td>
                        <%
                        SimpleDateFormat sdf
                        = new SimpleDateFormat(
                            "yyyy-mm-dd");
                    	Date d1 = sdf.parse(bill.getBillDate());
                    	Date d2 = sdf.parse(java.time.LocalDate.now().toString());
                    	long difference = d2.getTime() - d1.getTime();
                    	long days =  difference/(1000*24*3600);
                    	long leftDays = bill.getDueDays() - days;
                    	if(bill.getBillStatus().equalsIgnoreCase("paid") && leftDays<0){
                    		leftDays = 0;
                    	}
                    	%>
                    	<td><%= leftDays %></td>
                        <td><%= bill.getBillStatus() %></td>
                        <td>
                        <a href="DownloadBillPDFServlet?billID=<%= bill.getBillID() %>">
                        <button type="button" class="btn btn-outline-success">Download
                        </button>
                        </a>
                        </td>
                        <td>
                        <% if(bill.getBillStatus().equalsIgnoreCase("unpaid")){ %>
                        <a href="MarkAsPaid.jsp?billID=<%= bill.getBillID() %>">
                        <button type="button" class="btn btn-outline-success">Mark as paid</button>
                        </a>
                        <%}else{ %>
                        <a href="DownloadPaidReceiptServlet?billID=<%= bill.getBillID() %>">
                        <button type="button" class="btn btn-outline-success">Download</button>
                        </a>
                        </td>
                        <%} %>
                      </tr>
                      <%
                    	}
                      %>
                    </tbody>
                  </table>
                  </div>
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