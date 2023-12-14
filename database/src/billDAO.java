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
@WebServlet("/billDAO")
public class  billDAO
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private PreparedStatement preparedStatement1 = null;

	private ResultSet resultSet = null;
	
	public billDAO(){}
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
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

	public List<Bill> BillList() throws SQLException {
		List<Bill> billList =new ArrayList<Bill>();
    	String sql ="SELECT * from bill ";
    	connect_func();
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	
        while(resultSet.next()) {
        	int bill_id = resultSet.getInt("bill_id");
        	int order_id = resultSet.getInt("order_id");
            double total_amount = resultSet.getDouble("total_amount");
            String status = resultSet.getString("status");
            String note = resultSet.getString("note");
            Date generated_bill_date = resultSet.getDate("generated_bill_date");
            Date bill_paid_date = resultSet.getDate("bill_paid_date");


            Bill bill = new Bill(bill_id, order_id, total_amount, status, note, generated_bill_date,bill_paid_date );
            billList.add(bill);
            	
        }
    	resultSet.close();
    	disconnect();
    	return billList;
	}
	private void disconnect() throws SQLException {
		if (connect != null && !connect.isClosed()) {
			connect.close();
		}
	
    
	}
	public void insertBill(Bill bill) throws SQLException {
		 connect_func("root","password");  
		    String sql1 = "set foreign_key_checks = 0";
			String sql2 = " insert into bill(bill_id, order_id, total_amount, status,note, generated_bill_date, bill_paid_date) values (?,?,?,?,?,?,?)";
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql2);
			    preparedStatement.setInt(1, bill.getbill_id());
			    preparedStatement.setInt(2, bill.getorder_id());
				preparedStatement.setDouble(3, bill.gettotal_amount());
				preparedStatement.setString(4, bill.getstatus());
				preparedStatement.setString(5, bill.getnote());
				preparedStatement.setDate(6, bill.getgenerated_bill_date());
				preparedStatement.setDate(7, bill.getbill_paid_date());
				preparedStatement1 = (PreparedStatement)connect.prepareStatement(sql1);
               
			preparedStatement1.executeUpdate();
			preparedStatement.executeUpdate();
	        preparedStatement.close();
		 
		 
	 }

	
	 public int generateID() throws SQLException {
	    	int id=0;
	    	
	    	String sql ="Select MAX(bill_id) as id FROM bill";
	    	
	    	connect_func();
	    	 preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	    	 ResultSet resultSet= preparedStatement.executeQuery();
	    	 
	    	 if(resultSet.next()) {
	    		 id= resultSet.getInt("id");
	    	 }
	    	 
	    	 resultSet.close();
	    	 return id+1;
	    }

	public void updateBillStatus(int bill_id , String status, Date bill_paid_date) throws SQLException {
        String sql = "UPDATE bill SET status = ? , bill_paid_date = ?WHERE bill_id = ?";
        connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, status);
		preparedStatement.setDate(2, bill_paid_date);
		preparedStatement.setInt(3, bill_id);

		preparedStatement.executeUpdate();

        

	}
	

}
	
	
    
    
    
    
    
    
    
   
    
    
    
    
    
	
	

