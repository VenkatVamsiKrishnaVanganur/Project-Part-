import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.PreparedStatement;



public class ControlServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private userDAO userDAO = new userDAO();
	    private String currentUser;
	    private HttpSession session=null;
	    private treeDAO treeDAO = new treeDAO();
	    private quoteDAO quoteDAO = new quoteDAO();
	    private orderDAO orderDAO = new orderDAO();
	    private billDAO billDAO = new billDAO();
	    
	    public ControlServlet()
	    {
	    	
	    }
	    
	    public void init()
	    {
	    	userDAO = new userDAO();
	    	currentUser= "";
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getServletPath();
	        System.out.println(action);
	    
	    try {
        	switch(action) {  
        	case "/login":
        		login(request,response);
        		break;
        	case "/register":
        		register(request, response);
        		break;
        	case "/initialize":
        		userDAO.init();
        		rootPage(request,response,"");
        		break;
        	case "/root":
        		rootPage(request,response, "");
        		break;
        	case "/logout":
        		logout(request,response);
        		break;
        	case "/createQuote":
        		createQuote(request,response);
        		break;
        	case "/addTree":
        		addTree(request,response);
        		break;
        	case "/updateQuote":
        		updateQuote(request,response);
        		break;
        	case "/TreeDetails":
        		TreeDetails(request,response);
        		break;
        	case "/acceptQuote":
        		acceptQuote(request,response);
        		break;
        	case "/Quotes":
        		Quotes(request,response);
        		break;
        	case "/quoteHistory":
        		quoteHistory(request,response);
        		break;
        	case "/negotiation":
        		negotiation(request,response);
        		break;
        	 case "/list": 
                 listUser(request, response);           	
                 break;
        	 case "/orders": 
                 listOrders(request, response);           	
                 break;
        	 case "/bill": 
                 listBill(request, response);           	
                 break;
        	 case "/ViewOrders": 
                 ViewOrders(request, response);           	
                 break;
        	 case "/generateBill": 
                 generateBill(request, response);           	
                 break;
        	 case "/payment": 
                 payment(request, response);           	
                 break;
        	 case "/reject": 
                 reject(request, response);           	
                 break;
        	 case "/viewReports": 
                 viewReports(request, response);           	
                 break;
	    	}
	    }
	    catch(Exception ex) {
        	System.out.println(ex.getMessage());
	    	}
	    }
        	
	   
	    

		private void viewReports(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
			Report report = new Report();
			
			List<Client> easyClients =report.geteasyClients();
			request.setAttribute("bigClients", report.getbigClients());
			request.setAttribute("easyClients", report.geteasyClients());
			request.setAttribute("oneTreeQuote", report.getoneTreeQuote());
			request.setAttribute("prospectiveClients", report.getprospectiveClients());
			request.setAttribute("overDueBills", report.getoverDueBills());
			request.setAttribute("badClients", report.getbadClients());
			request.setAttribute("goodClients", report.getgoodClients());
			request.setAttribute("highestTrees", report.gethighestTree());
			request.setAttribute("Statistics", report.getStatistics());
            request.getRequestDispatcher("reports.jsp").forward(request, response);
	
		}

		private void reject(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	    	int bill_id = Integer.parseInt(request.getParameter("bill_id").toString());
		   	 Date bill_paid_date = Date.valueOf(LocalDate.now());
	    	 billDAO.updateBillStatus(bill_id, "Rejected", bill_paid_date);
	    	 request.setAttribute("ListBill", billDAO.BillList());
		     request.getRequestDispatcher("bill.jsp").forward(request, response);
			
		}

		private void payment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	int bill_id = Integer.parseInt(request.getParameter("bill_id").toString());
		   	 Date bill_paid_date = Date.valueOf(LocalDate.now());
	    	 billDAO.updateBillStatus(bill_id,"paid", bill_paid_date);
	    	 request.setAttribute("ListBill", billDAO.BillList());
		     request.getRequestDispatcher("bill.jsp").forward(request, response);
			
		}

		private void generateBill(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	    	int bill_id = billDAO.generateID();
	   	 	int order_id = Integer.parseInt(request.getParameter("order_id"));
	   	 	double total_amount = Double.parseDouble(request.getParameter("total_amount"));
	   	 	String status = "pending";
	   	    String note = null;
	   	    Date bill_paid_date =null;
	   	    Date generated_bill_date = Date.valueOf(LocalDate.now());
	   	    Bill bill = new Bill(bill_id, order_id, total_amount, status, note, generated_bill_date, bill_paid_date);
	        billDAO.insertBill(bill);
	    	request.getRequestDispatcher("bill.jsp").forward(request, response);
			
		}
        
		private void ViewOrders(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    	request.setAttribute("ListOrders", orderDAO.orderList());
	    	request.getRequestDispatcher("order.jsp").forward(request, response);
			
		}

		private void listBill(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	    	System.out.println("bill page");
			request.setAttribute("ListBill", billDAO.BillList());
	    	request.getRequestDispatcher("bill.jsp").forward(request, response);		
		}

		private void listOrders(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    	System.out.println("order page");
			request.setAttribute("ListOrders", orderDAO.orderList());
	    	request.getRequestDispatcher("order.jsp").forward(request, response);
			
			
		}

		private void quoteHistory(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	    	 
		        List<quoteHistory> quotes = quoteDAO.quoteHistory();
		        System.out.println(quotes.size());
		        request.setAttribute("quotesHistory", quotes);
		        request.getRequestDispatcher("quoteHistory.jsp").forward(request, response);
		}

		private void listUser(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listUser started: 00000000000000000000000000000000000");

	     
	        List<user> listUser = userDAO.listAllUsers();
	        request.setAttribute("listUser", listUser);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listPeople finished: 111111111111111111111111111111111111");
	    }
	    	        
	    private void rootPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("root view");
			request.setAttribute("listUser", userDAO.listAllUsers());
	    	request.getRequestDispatcher("rootView.jsp").forward(request, response);
	    }
	    
	    private void davidPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("David View");
			request.setAttribute("ListQuotes", quoteDAO.listAllQuotes());
	    	request.getRequestDispatcher("davidSmithView.jsp").forward(request, response);
	    }
	    
	    
	    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	 String email = request.getParameter("email");
	    	 String password = request.getParameter("password");
	    	 
	    	 if (email.equals("root") && password.equals("pass1234")) {
				 System.out.println("Login Successful! Redirecting to root");
				 session = request.getSession();
				 session.setAttribute("username", email);
				 rootPage(request, response, "");
	    	 }
	    	 else if (email.equals("david@gmail.com") && password.equals("david123")) {
				 System.out.println("Login Successful! Redirecting to root");
				 session = request.getSession();
				 session.setAttribute("username", email);
				 List<Quote> quotes= quoteDAO.getQuotesByStatus("requested");
				 request.setAttribute("listQuotes", quotes);

				 davidPage(request, response, "");
	    	 }
	    	 else if(userDAO.isClient(email, password )!= null) {
				 System.out.println("Login Successful! Redirecting to client view");
				 session = request.getSession();
				 
				 session.setAttribute("client_id", userDAO.getClient_Id(email));
				 request.getRequestDispatcher("clientView.jsp").forward(request, response);

	    	 }
	    	 
	    	 else if(userDAO.isValid(email, password)) 
	    	 {
			 	 
			 	 currentUser = email;
				 System.out.println("Login Successful! Redirecting");
				 request.getRequestDispatcher("activitypage.jsp").forward(request, response);
			 			 			 			 
	    	 }
	    	 else {
	    		 request.setAttribute("loginStr","Login Failed: Please check your credentials.");
	    		 request.getRequestDispatcher("login.jsp").forward(request, response);
	    	 }
	    }
	           
	    private void register(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    	String email = request.getParameter("email");
	   	 	String firstName = request.getParameter("firstName");
	   	 	String lastName = request.getParameter("lastName");
	   	 	String password = request.getParameter("password");
	   	    String confirm = request.getParameter("confirm");
	   	    String address = request.getParameter("address");
	   	    long phone = Long.parseLong(request.getParameter("phone"));
	   	    String credit_card_info = request.getParameter("credit_card_info");
	   	    String role = request.getParameter("role");

	   	   
	   	    
	   	 	
	   	 	if (password.equals(confirm)) {
	   	 		if (!userDAO.checkEmail(email)) {
		   	 		System.out.println("Registration Successful! Added to database");
		            user users = new user(email,firstName, lastName, password, role);
		   	 		System.out.println("user inserted :" + users.getEmail());
		   	 		userDAO.insert(users);
		   	 		System.out.println("Role: " + role);

		   	 		if(role.equals("clients")) {
		   	 			Client client = new Client(firstName, lastName,address, phone, credit_card_info);
		   	 			client.setEmail(email);
		   	 			userDAO.insertClient(client);
		   	 		}
		   	 		
		   	 		response.sendRedirect("login.jsp");
	   	 		}
	   	 		
		   	 	else {
		   	 		System.out.println("Username taken, please enter new username");
		    		 request.setAttribute("errorOne","Registration failed: Username taken, please enter a new username.");
		    		 request.getRequestDispatcher("register.jsp").forward(request, response);
		   	 	}
	   	 	}
	   	 	else {
	   	 		System.out.println("Password and Password Confirmation do not match");
	   		 request.setAttribute("errorTwo","Registration failed: Password and Password Confirmation do not match.");
	   		 request.getRequestDispatcher("register.jsp").forward(request, response);
	   	 	}
	    }    
	    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	currentUser = "";
        		response.sendRedirect("login.jsp");
        	}
	
	    protected void addTree(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	        int quote_id = Integer.parseInt(session.getAttribute("quote_id").toString());
	        System.out.println("quote Id from add tree is :" + quote_id);
	        System.out.println("client Id from add tree is :" + session.getAttribute("client_id").toString());
	        String size = request.getParameter("size");
	        System.out.println(size);
	        double height = Double.parseDouble(request.getParameter("height"));
	        System.out.println(height);

	        String location = request.getParameter("location");
	        System.out.println(location);

	        boolean near_house = request.getParameter("nearHouse").equalsIgnoreCase("Yes") ? true : false;
	        System.out.println(near_house);

	        String note = request.getParameter("note");
	        System.out.println(note);
	        

			int tree_id = treeDAO.generateID();
			
	        String status = "Requested";
	        double price = 0;

	        int client_id = Integer.parseInt( session.getAttribute("client_id").toString());
	        System.out.println("Client Id before quote insert: " + client_id);

	        Date date = Date.valueOf(LocalDate.now());

	        Quote quote = new Quote(quote_id, client_id, date,status, note, price,date, date);
	        if (!quoteDAO.isValid(quote_id)) {
	            quoteDAO.insertQuote(quote);
	        }

	        Tree tree = new Tree(tree_id,quote_id, size, height, location, near_house, note);
	        treeDAO.insertTree(tree);

	        request.setAttribute("treesAdded", treeDAO.listTreesByQuote(quote_id));
	       

	        request.getRequestDispatcher("quote request.jsp").forward(request, response);
	    }

	    protected void createQuote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	        String quoteId = String.valueOf(quoteDAO.generateID());
	        System.out.println("QuoteId " +quoteId);
	        session.setAttribute("quote_id", quoteId);
	        request.getRequestDispatcher("quote request.jsp").forward(request, response);
	    }

	    private void davidSmithPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	        System.out.println("David Smith view");
	        request.setAttribute("listQuoteRequests", quoteDAO.getQuotesByStatus("Requested"));
	        for (Quote q : quoteDAO.getQuotesByStatus("Requested")) {
	            
	        }
	        request.getRequestDispatcher("davidSmithView.jsp").forward(request, response);
	    }

	    private void updateQuote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

	        Quote quote = null;
	        System.out.println(request.getParameter("client_id"));

	        int client_id = Integer.parseInt(request.getParameter("client_id"));
	        System.out.println("client_id : " + client_id);

	        int quote_id = Integer.parseInt(request.getParameter("quote_id"));
	        System.out.println("quote_id : " + quote_id);

	        double price = Double.parseDouble(request.getParameter("price"));
	        System.out.println("price : " + price);

	        Date start_date = Date.valueOf(request.getParameter("start_date"));
	        System.out.println("start_date : " + start_date);

	        Date end_date = Date.valueOf(request.getParameter("end_date"));
	        System.out.println("end_date : " + end_date);

	        String note = request.getParameter("note");
	        System.out.println("note : " + note);

	        String status = request.getParameter("status");
	        System.out.println("status: " + status);

	        Date propose_date = Date.valueOf(LocalDate.now());
	        System.out.println("propose_date: " + propose_date);

	        quote = new Quote(quote_id, client_id, propose_date,status, note, price,start_date, end_date);
	        quoteDAO.update(quote);

	        request.getRequestDispatcher("davidSmithView.jsp").forward(request, response);
	    }

	    private void TreeDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	        int quote_id = Integer.parseInt(request.getParameter("quote_id"));

	        List<Tree> treeDetails = treeDAO.listTreesByQuote(quote_id);
	        request.setAttribute("listOfTrees", treeDetails);

	        request.getRequestDispatcher("treeDetail.jsp").forward(request, response);
	    }

	    private void Quotes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	        int client_id = Integer.parseInt(session.getAttribute("client_id").toString());
	        System.out.println("client Id: " + client_id);

	        List<Quote> quotes = quoteDAO.getQuoteViaClientID(client_id);
	        request.setAttribute("quotes", quotes);
	        request.getRequestDispatcher("viewQuotes.jsp").forward(request, response);

	    }
	   
	    

	    private void acceptQuote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	        int quote_id = Integer.parseInt(request.getParameter("quote_id"));
	        System.out.println("quote id : " + quote_id);
	        
	        int id = orderDAO.generateID();
	        int order_id =id;
	        int client_id = Integer.parseInt(session.getAttribute("client_id").toString());
	   	    System.out.println("client id : " + client_id);
	   	 	Date start_date = Date.valueOf(request.getParameter("start_date"));
	   	    System.out.println("start_date: " + start_date);
	   	    Date end_date = Date.valueOf(request.getParameter("end_date"));
	   	    System.out.println("end_date : " + end_date);
	   	    double total_amount = Double.parseDouble(request.getParameter("price"));
	   	    System.out.println("total_amount : " + total_amount);
	   	    Order order = new Order(order_id,  client_id,quote_id, start_date, end_date, total_amount);
	   	    quoteDAO.updateStatus(quote_id, "accepted");
	   	    orderDAO.insertOrder(order);
	        request.getRequestDispatcher("clientView.jsp").forward(request, response);
	    }

	    private void negotiation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	        int quote_id = Integer.parseInt(request.getParameter("quote_id"));
	        String note = request.getParameter("note");
	        quoteDAO.negotiation(quote_id, "negotiate", note);
	        System.out.println(note);
	        request.getRequestDispatcher("clientView.jsp").forward(request, response);
	    }

}	    
	 