package consumptionManagment;


import java.sql.*;
import java.util.Calendar;

public class DataHandle {
	
	public static void main(String[] args)
	  {
	    try
	    {
	      // create a mysql database connection
	      String myDriver = "com.mysql.jdbc.Driver";
	      String myUrl = "jdbc:mysql://127.0.0.1:3307/electricitymanagmentsystem";
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "");
	    
	      // create a sql date object so we can use it in our INSERT statement
	      //Calendar calendar = Calendar.getInstance();
	      //java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

	      // the mysql insert statement
	      String query = " insert into billdata (id, unit, unifee)"
	        + " values (?, ?, ?)";

	      // create the mysql insert preparedstatement
	      PreparedStatement preparedStmt = conn.prepareStatement(query);
	      preparedStmt.setString (1, "0");
	      preparedStmt.setString (2, "1");
	      preparedStmt.setString   (3, "10");
	     

	      // execute the preparedstatement
	      preparedStmt.execute();
	      
	      conn.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception!");
	      System.err.println(e.getMessage());
	    }
	  }

}
