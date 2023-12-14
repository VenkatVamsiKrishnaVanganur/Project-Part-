import java.sql.Date;

public class Order
{
	    protected int order_id;
	    protected int client_id;
	    protected int quote_id;		    
	    protected Date start_date;
	    protected Date end_date;
	    protected double total_amount;
	    
	 
	    //constructors
	    public Order() {
	    }
	 
	   
	    
	    public Order(int order_id,int client_id,int quote_id, Date start_date, Date end_date, double total_amount) 
	    {
	    	this( client_id,quote_id,start_date, end_date, total_amount);
	    	this.order_id = order_id;
	    	
	    
	    }
	    
	    public Order(int client_id,int quote_id,  Date start_date, Date end_date, double total_amount) 
	    {
	    	this.client_id = client_id;
	    	this.quote_id = quote_id;
	        this.start_date = start_date;
	        this.end_date = end_date;
	        this.total_amount = total_amount;
	    }
	 
	
	    
	    
	    public int getorder_id() {
	        return order_id;
	    }

	    public void setorder_id(int order_id) {
	        this.order_id = order_id;
	    }

	    public int getclient_id() {
	        return client_id;
	    }

	    public void setclient_id(int client_id) {
	        this.client_id = client_id;
	    }

	    public int getquote_id() {
	        return quote_id;
	    }

	    public void setquote_id(int quote_id) {
	        this.quote_id = quote_id;
	    }

	    public Date getstart_date() {
	        return start_date;
	    }

	    public void setstart_date(Date start_date) {
	        this.start_date = start_date;
	    }

	    public Date getend_date() {
	        return end_date;
	    }

	    public void setend_date(Date end_date) {
	        this.end_date = end_date;
	    }

	    public double gettotal_amount() {
	        return total_amount;
	    }

	    public void settotal_amount(double total_amount) {
	        this.total_amount = total_amount;
	    }
	}