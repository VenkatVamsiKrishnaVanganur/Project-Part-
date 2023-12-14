import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/userDAO")
public class userDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public userDAO(){}
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
    	//uses default connection to the database
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false&user=john&password=john1234");
            System.out.println(connect);
        }
    }
    
    public boolean database_login(String userName, String password) throws SQLException{
    	try {
    		connect_func("root","password");
    		String sql = "select * from user where user_name = ?";
    		preparedStatement = connect.prepareStatement(sql);
    		preparedStatement.setString(1, userName);
    		ResultSet rs = preparedStatement.executeQuery();
    		return rs.next();
    	}
    	catch(SQLException e) {
    		System.out.println("failed login");
    		return false;
    	}
    }
	//connect to the database 
    public void connect_func(String username, String password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/userdb?"
  			          + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }
    
    public List<user> listAllUsers() throws SQLException {
        List<user> listUser = new ArrayList<user>();        
        String sql = "SELECT * FROM User";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String userName = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String userRole = resultSet.getString("role");

             
            user users = new user(userName,firstName, lastName, password,userRole);
            listUser.add(users);
        }        
        resultSet.close();
        disconnect();        
        return listUser;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public boolean checkEmail(String email) throws Exception{
    	boolean checks= false;
    	String sql =" Select * from User WHERE email = ?";
    	connect_func();
    	preparedStatement= (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.setString(1, email);
    	ResultSet resultSet = preparedStatement.executeQuery();
    	
    	if(resultSet.next()) {
    		checks=true;
    	}
    	return checks;
		
    }
    
    public void insert(user users) throws SQLException {
    	connect_func("root","password");         
		String sql = "insert into User(email, firstName, lastName, password, role) values (?,?,?,?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, users.getEmail());
			preparedStatement.setString(2, users.getFirstName());
			preparedStatement.setString(3, users.getLastName());
			preparedStatement.setString(4, users.getPassword());
			preparedStatement.setString(5, users.getrole());

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public void insertClient(Client client) throws SQLException{
    	connect_func("root","password");         
		String sql = "insert into Client(firstName, lastName, address, email,phone,credit_card_info,client_id) values (?,?,?,?,?,?,?)";
		System.out.println("insert in client");
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, client.getFirstName());
			preparedStatement.setString(2, client.getLastName());
			preparedStatement.setString(3, client.getaddress());
			preparedStatement.setString(4, client.getEmail());
			preparedStatement.setString(5, String.valueOf(client.getphone()));
			preparedStatement.setString(6, client.getcredit_card_info());
			int id = generateID();
			preparedStatement.setInt(7,id);

		preparedStatement.executeUpdate();
		System.out.println("done inserting");
        preparedStatement.close();
    	
    }
    public int generateID() throws SQLException {
    	int id=0;
    	
    	String sql ="Select MAX(client_id) as id FROM client";
    	
    	connect_func();
    	 preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	 ResultSet resultSet= preparedStatement.executeQuery();
    	 
    	 if(resultSet.next()) {
    		 id= resultSet.getInt("id");
    	 }
    	 
    	 resultSet.close();
    	 return id+1;
    }
    
    public boolean delete(String email) throws SQLException {
        String sql = "DELETE FROM User WHERE email = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(user users) throws SQLException {
        String sql = "update User set user_name = ?, firstName=?, lastName =?,password = ? , role=? where email = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, users.getEmail());
		preparedStatement.setString(2, users.getFirstName());
		preparedStatement.setString(3, users.getLastName());
		preparedStatement.setString(4, users.getPassword());
		preparedStatement.setString(5, users.getrole());
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public user getUser(String userName) throws SQLException {
    	user user = null;
        String sql = "SELECT * FROM User WHERE user_name = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, userName);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String userRole = resultSet.getString("role");
            user = new user(userName, firstName, lastName, password, userRole);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }
    
    public String getClient_Id(String email) throws SQLException {
    	String client_id = null;
        String sql = "SELECT client_id from client where email = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
              client_id = resultSet.getString("client_id"); 
             
        }
         
        resultSet.close();
        statement.close();
        
        return client_id;
    }
    public Client getClientId(int id) throws SQLException {
    	Client client = null;
        String sql = "SELECT * FROM Client WHERE client_id = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
             int client_id = resultSet.getInt("client_id"); 
             String firstName = resultSet.getString("firstName");      
             String lastName = resultSet.getString("lastName");   
             String email = resultSet.getString("email"); 
             long phone = resultSet.getLong("phone");    
             
             client = new Client(client_id, firstName, lastName, null,email, phone, "122222");
             return client;



        }
         
        
        return null;
    }
    
    public boolean checkUserName(String userName) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE user_name = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, userName);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
    	return checks;
    }
    
    public boolean checkPassword(String password) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE password = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
       	return checks;
    }
    
    
    
    public boolean isValid(String userName, String password) throws SQLException
    {
    	String sql = "SELECT * FROM User";
    	connect_func();
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	resultSet.last();
    	
    	int setSize = resultSet.getRow();
    	resultSet.beforeFirst();
    	
    	for(int i = 0; i < setSize; i++)
    	{
    		resultSet.next();
    		if(resultSet.getString("email").equals(userName) && resultSet.getString("password").equals(password)) {
    			return true;
    		}		
    	}
    	return false;
    }
    
    public String isClient(String userName, String password) throws SQLException
    {
    	System.out.println("Client verification");
    	String sql = "SELECT * FROM User";
    	connect_func();
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	resultSet.last();
    	
    	int setSize = resultSet.getRow();
    	resultSet.beforeFirst();
    	String firstName = "";
    	
    	for(int i = 0; i < setSize; i++)
    	{
    		resultSet.next();
    		if(resultSet.getString("email").equals(userName) && resultSet.getString("password").equals(password) && resultSet.getString("role").equalsIgnoreCase("Clients")) {
    			firstName= resultSet.getString("firstName");
    		}		
    	}
    	
     return firstName;
    }
   
    
    
    
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = {
					        "use testdb; ",
					        "set foreign_key_checks=0;",
					        "delete from  User; ",
					        "delete from quote_response",
					        "delete from bill",
					        "delete from orders",
					        "delete from quote_request",
					        "delete from client",
					        "delete from Tree"
					        
        					};
        
        					
        String[] TUPLES = {("insert into User(email, firstName, lastName, password, role)"+
        			"values ('susie@gmail.com', 'Susie ', 'Guzman', 'susie1234','clients'),"+
			    		 	"('don@gmail.com', 'Don', 'Cummings','don123','clients'),"+
			    	 	 	"('margarita@gmail.com', 'Margarita', 'Lawson','margarita1234','clients'),"+
			    		 	"('jo@gmail.com', 'Jo', 'Brady','jo1234','clients'),"+
			    		 	"('wallace@gmail.com', 'Wallace', 'Moore','wallace1234', 'clients'),"+
			    		 	"('amelia@gmail.com', 'Amelia', 'Phillips','amelia1234','clients' ),"+
			    			"('sophie@gmail.com', 'Sophie', 'Pierce','sophie1234','clients' ),"+
			    			"('angelo@gmail.com', 'Angelo', 'Francis','angelo1234','clients' ),"+
			    			"('rudy@gmail.com', 'Rudy', 'Smith','rudy1234','clients' ),"+
			    			"('jeannette@gmail.com', 'Jeannette ', 'Stone','jeannette1234','clients' ),"+
			    			"('root', 'default', 'default','pass1234', 'Admin root');")
			    			};
        
        
        String[] CLIENTS = {
        	    "INSERT INTO Client(client_id, firstName, lastName, address, phone, email, credit_card_info) VALUES " +
        	    "(1, 'John', 'Doe', '123 Main St', '55555555', 'john@gmail.com', '1254524'), " +
        	    "(2, 'Jane', 'Smith', '456 Elm St', '55512347', 'jane@gmail.com', '1524521'), " +
        	    "(3, 'Bob', 'Johnson', '789 Oak St', '5559543', 'bob@gmail.com', '5245154'), " +
        	    "(4, 'Alice', 'Brown', '246 Maple St', '555222', 'alice@gmail.com', '1205421'), " +
        	    "(5, 'Charlie', 'Wilson', '789 Pine St', '554444', 'charlie@gmail.com', '4528795'), " +
        	    "(6, 'Emily', 'Jones', '654 Cedar St', '7777', 'emily@gmail.com', '1524879'), " +
        	    "(7, 'Daniel', 'Taylor', '890 Birch St', '553333', 'daniel@gmail.com', '7854215'), " +
        	    "(8, 'Ella', 'White', '753 Oak St', '54545', 'ella@gmail.com', '4520154'), " +
        	    "(9, 'Frank', 'Lee', '125 Pine St', '55222', 'frank@gmail.com', '1254215'), " +
        	    "(10, 'Grace', 'Smith', '367 Maple St', '545451', 'grace@gmail.com', '1204587');"
        	};

        	String[] TREES = {
        	    "INSERT INTO Tree(tree_id, quote_id,size, height, location, nearHouse, note) VALUES " +
        	    "(1, 1,'Large', 15.5, 'Backyard', true, 'Tall oak tree'), " +
        	    "(2,2, 'Medium', 8.2, 'Front yard', false, 'Small apple tree'), " +
        	    "(3, 3,'Small', 5.0, 'Side yard', true, 'Dwarf pine tree'), " +
        	    "(4, 4,'Medium', 10.0, 'Front yard', false, 'Maple tree'), " +
        	    "(5, 4,'Large', 20.0, 'Backyard', true, 'Pine tree'), " +
        	    "(6,5, 'Small', 6.5, 'Backyard', false, 'Birch tree'), " +
        	    "(7,5, 'Medium', 9.0, 'Front yard', true, 'Cherry tree'), " +
        	    "(8,5, 'Large', 18.5, 'Backyard', false, 'Cypress tree'), " +
        	    "(9,6, 'Medium', 12.0, 'Front yard', true, 'Palm tree'), " +
        	    "(10,6, 'Small', 7.0, 'Side yard', false, 'Willow tree');"
        	};

        	String[] QUOTE_REQUEST = {
        			"INSERT INTO quote_request (quote_id, client_id, propose_date, status, note, price, start_date, end_date) VALUES"+
        			"(1, 1, '2023-10-24', 'requested', 'small', 100.00, '2023-11-01', '2023-11-10'),"+
        			"(2, 2, '2023-10-25', 'requested', 'small tree', 200.00, '2023-11-02', '2023-11-11'),"+
        			"(3, 3, '2023-10-26', 'requested', 'big tree', 150.00, '2023-11-03', '2023-11-12'),"+
        			"(4, 4, '2023-10-27', 'requested', 'small tree', 250.00, '2023-11-04', '2023-11-13'),"+
        			"(5, 5, '2023-10-28', 'requested', 'big tree', 120.00, '2023-11-05', '2023-11-14'),"+
        			"(6, 6, '2023-10-29', 'requested', 'small tree', 180.00, '2023-11-06', '2023-11-15'),"+
        			"(7, 7, '2023-10-30', 'requested', 'big tree', 220.00, '2023-11-07', '2023-11-16'),"+
        			"(8, 8, '2023-10-31', 'requested', 'small tree', 130.00, '2023-11-08', '2023-11-17'),"+
        			"(9, 9, '2023-11-01', 'requested', 'big tree', 170.00, '2023-11-09', '2023-11-18'),"+
        			"(10, 10, '2023-11-02', 'requested', 'big tree', 270.00, '2023-11-10', '2023-11-19');"

        	};
        	String[] QUOTE_RESPONSE = {
        			"INSERT INTO quote_response (id, client_id, quote_id, propose_date, status, price, start_date, end_date, note) VALUES"+
        			"(1, 10001, 1, '2023-10-24', 'requested', 100.00, '2023-11-01', '2023-11-10', 'small'),"+
        			"(2, 10002, 2, '2023-10-25', 'requested', 200.00, '2023-11-02', '2023-11-11', 'small tree'),"+
        			"(3, 10003, 3, '2023-10-26', 'requested', 150.00, '2023-11-03', '2023-11-12', 'big tree'),"+
        			"(4, 10004, 4, '2023-10-27', 'requested', 250.00, '2023-11-04', '2023-11-13', 'small tree'),"+
        			"(5, 10005, 5, '2023-10-28', 'requested', 120.00, '2023-11-05', '2023-11-14', 'big tree'),"+
        			"(6, 10006, 6, '2023-10-29', 'requested', 180.00, '2023-11-06', '2023-11-15', 'small tree'),"+
        			"(7, 10007, 7, '2023-10-30', 'requested', 220.00, '2023-11-07', '2023-11-16', 'big tree'),"+
        			"(8, 10008, 8, '2023-10-31', 'requested', 130.00, '2023-11-08', '2023-11-17', 'small tree'),"+
        			"(9, 10009, 9, '2023-11-01', 'requested', 170.00, '2023-11-09', '2023-11-18', 'big tree'),"+
        			"(10, 100010, 10, '2023-11-02', 'requested', 270.00, '2023-11-10', '2023-11-19', 'big tree');"


        	};

        	String[] ORDERS = {
        			"INSERT INTO Orders (order_id, client_id, quote_id, start_date, end_date, total_amount) VALUES"+
        			"(1, 10001, 1, '2023-10-26', '2023-10-28', 1000),"+
        			"(2, 10002, 2, '2023-10-27', '2023-10-30', 500),"+
        			"(3, 10003, 3, '2023-10-28', '2023-10-30', 500),"+
        			"(4, 10004, 4, '2023-10-29', '2023-11-01', 4000),"+
        			"(5, 10005, 5, '2023-10-30', '2023-11-02', 421),"+
        			"(6, 10006, 6, '2023-10-31', '2023-11-03', 875),"+
        			"(7, 10007, 7, '2023-11-01', '2023-11-03', 452),"+
        			"(8, 10008, 8, '2023-11-02', '2023-11-04', 1400),"+
        			"(9, 10009, 9, '2023-11-03', '2023-11-05', 800),"+
        			"(10, 10010, 10, '2023-11-04', '2023-11-06', 900);"
        	};


        	String[] BILLS = {
        	    "INSERT INTO Bill(bill_id, order_id, total_amount, status, note) VALUES " +
        	    "(1, 1, 500.00, 'Paid', 'Tree trimming service'), " +
        	    "(2, 2, 750.00, 'Pending', 'Tree removal service'), " +
        	    "(3, 3, 450.00, 'Paid', 'Tree pruning service'), " +
        	    "(4, 4, 800.00, 'Pending', 'Tree removal service'), " +
        	    "(5, 5, 600.00, 'Paid', 'Tree trimming service'), " +
        	    "(6, 6, 350.00, 'Pending', 'Tree pruning service'), " +
        	    "(7, 7, 900.00, 'Paid', 'Tree removal service'), " +
        	    "(8, 8, 550.00, 'Pending', 'Tree trimming service'), " +
        	    "(9, 9, 400.00, 'Paid', 'Tree pruning service'), " +
        	    "(10, 10, 700.00, 'Pending', 'Tree removal service');"
        	};

        
        
        
        
        
       for(int i=0;i<INITIAL.length;i++) {
    	   statement.execute(INITIAL[i]);
       }
      for(int i=0;i<CLIENTS.length;i++) {
  	   statement.execute(CLIENTS[i]);
     }
       for(int i=0;i<TUPLES.length;i++) {
    	   statement.execute(TUPLES[i]);
       }
      
       for(int i=0;i<TREES.length;i++) {
    	   statement.execute(TREES[i]);
       }
       for(int i=0;i<QUOTE_REQUEST.length;i++) {
    	   statement.execute(QUOTE_REQUEST[i]);
       }   
    	for(int i=0;i<QUOTE_RESPONSE.length;i++) {
        	   statement.execute(QUOTE_RESPONSE[i]);
       }
       for(int i=0;i<ORDERS.length;i++) {
    	   statement.execute(ORDERS[i]);
       }
       for(int i=0;i<BILLS.length;i++) {
    	   statement.execute(BILLS[i]);
       }
       
        disconnect();
    }

	
  
}