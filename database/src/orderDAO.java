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
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/orderDAO")
public class orderDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private PreparedStatement preparedStatement1 = null;

	private ResultSet resultSet = null;
	
	public orderDAO(){}
	
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
    
    public boolean delete(String email) throws SQLException {
        String sql = "DELETE FROM User WHERE email = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    
    public List<Order> orderList() throws Exception{
    	List<Order> orderList =new ArrayList<Order>();
    	String sql ="SELECT * from orders ";
    	connect_func();
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	
        while(resultSet.next()) {
        	int order_id = resultSet.getInt("order_id");
            int client_id = resultSet.getInt("client_id");
            int quote_id = resultSet.getInt("quote_id");
            java.sql.Date start_date = resultSet.getDate("start_date");
            java.sql.Date end_date = resultSet.getDate("end_date");
            double total_amount = resultSet.getDouble("total_amount");

            Order order = new Order(order_id, client_id, quote_id, start_date, end_date, total_amount);
            orderList.add(order);
            	
        }
    	resultSet.close();
    	disconnect();
    	return orderList;
    	
    }
    
    private void disconnect() throws SQLException {
		if (connect != null && !connect.isClosed()) {
			connect.close();
		}
		
	}

	public boolean update(Order order) throws SQLException {
        String sql = "update Orders set total_amount = ?, start_date=?, end_date=? WHERE order_id=?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setDouble(1, order.gettotal_amount());
		preparedStatement.setDate(2, order.getstart_date());
		preparedStatement.setDate(3, order.getend_date());
		preparedStatement.setInt(4, order.getorder_id());


		
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    
    
    
    public List<Order> getOrderViaQuoteID(int quote_id) throws SQLException {
    	List<Order> Orders = new ArrayList<>();
    	Order Order=null;
        String sql = "SELECT * FROM orders WHERE quote_id = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, quote_id);
         
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
        	int order_id = resultSet.getInt("order_id");
        	int client_id = resultSet.getInt("client_id");    
        	Date start_date = resultSet.getDate("start_date");
        	Date end_date = resultSet.getDate("end_date");
        	double total_amount = resultSet.getDouble("total_amount");
        	
        	
        	Order= new Order(order_id, client_id,  start_date, end_date, total_amount);
        	Orders.add(Order);

        }
                
        resultSet.close();
        return Orders;
    }
    
	
    
  
	

	public List<Order> getOrderByClientId(int clientId) throws SQLException {
	    List<Order> orders = new ArrayList<>();
	    String sql = "SELECT * from orders where client_id = ?";
	    
	     connect_func();
	     PreparedStatement preparedStatement = connect.prepareStatement(sql);
	        
	        preparedStatement.setInt(1, clientId);
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	            	int order_id = resultSet.getInt("order_id");
	            	int quote_id = resultSet.getInt("quote_id");	            		             
		             Date start_date = resultSet.getDate("start_date");
		             Date end_date = resultSet.getDate("end_date");
		             double total_amount = resultSet.getDouble("total_amount");	
	                Order order = new Order(order_id,quote_id,  start_date, end_date, total_amount);
	                orders.add(order);
	            }
	       
	
        resultSet.close();
	    return orders;
	        }
	}
	
	 public int generateID() throws SQLException {
	    	int id=0;
	    	
	    	String sql ="Select MAX(order_id) as id FROM orders";
	    	
	    	connect_func();
	    	 preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	    	 ResultSet resultSet= preparedStatement.executeQuery();
	    	 
	    	 if(resultSet.next()) {
	    		 id= resultSet.getInt("id");
	    	 }
	    	 
	    	 resultSet.close();
	    	 return id+1;
	    }
	 public void insertOrder(Order order) throws SQLException {
		 connect_func("root","password");  
		    String sql1 = "set foreign_key_checks = 0";
			String sql2 = " insert into orders(order_id, client_id, quote_id, start_date, end_date, total_amount) values (?,?,?,?,?,?)";
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql2);
			    preparedStatement.setInt(1, order.getorder_id());
			    preparedStatement.setInt(2, order.getclient_id());
				preparedStatement.setDouble(3, order.getquote_id());
				preparedStatement.setDate(4, order.getstart_date());
				preparedStatement.setDate(5, order.getend_date());
				preparedStatement.setDouble(6, order.gettotal_amount());
				preparedStatement1 = (PreparedStatement)connect.prepareStatement(sql1);

			preparedStatement1.executeUpdate();
			preparedStatement.executeUpdate();
	        preparedStatement.close();
		 
		 
	 }

	
}

	
    
    
    
    
    
    
   
    
    
    
    
    
	
	

