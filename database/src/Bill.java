import java.sql.Date;

public class Bill
{
    	protected int bill_id;
	    protected int order_id;
	    protected double total_amount;
	    protected String status;
	    protected String note;
	    protected Date generated_bill_date;
	    protected Date bill_paid_date;




	 
	    //constructors
	    public Bill() {
	    }
	 
	   
	   
	    
	    public Bill(int bill_id, int order_id, double total_amount, String status, String note, Date generated_bill_date, Date bill_paid_date ) 
	    {
	    	this.bill_id = bill_id;
	    	this.order_id = order_id;
	    	this.total_amount = total_amount;
	    	this.status = status;
	    	this.note = note;
	    	this.generated_bill_date = generated_bill_date;
	    	this.bill_paid_date =bill_paid_date;
	        
	    }
	 
	
	    
	    public int getbill_id() {
	        return bill_id;
	    }

	    public void setbill_id(int bill_id) {
	        this.bill_id = bill_id;
	    }

	    public int getorder_id() {
	        return order_id;
	    }

	    public void setorder_id(int order_id) {
	        this.order_id = order_id;
	    }


	    public double gettotal_amount() {
	        return total_amount;
	    }

	    public void settotal_amount(double total_amount) {
	        this.total_amount = total_amount;
	    }
	    public String getstatus() {
	        return status;
	    }

	    public void setstatus(String status) {
	        this.status = status;
	    }
	    public String getnote() {
	        return status;
	    }

	    public void setnote(String note) {
	        this.note = note;
	    }
	    public Date getgenerated_bill_date() {
	        return generated_bill_date;
	    }

	    public void setgenerated_bill_date(Date generated_bill_date) {
	        this.generated_bill_date = generated_bill_date;
	    }
	    public Date getbill_paid_date() {
	        return bill_paid_date;
	    }

	    public void setbill_paid_date(Date bill_paid_date) {
	        this.bill_paid_date = bill_paid_date;
	    }
	}