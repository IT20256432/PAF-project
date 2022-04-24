package consumptionManagment;

import java.sql.*;

public class DataMange {
	
	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/electricitymanagmentsystem",
	 "root", "");
	 //For testing
	 System.out.print("Successfully connected");
	 }
	 catch(Exception e)
	 {
	 e.printStackTrace();
	 }

	 return con;
	}
	
	public String insertItem(String unit, String unifee)
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database";
	 }
	 // create a prepared statement
	 String query = " insert into billdata (id, unit, unifee)"+ "values (?,?,?)";
	 //String query = "insert into mydb(itemID,itemCode,itemName,itemPrice,itemDesc)"+ " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, unit);
	 preparedStmt.setString(3, unifee);
	             // preparedStmt.setString(4, price);
	             // preparedStmt.setString(5, desc);
	 
	 //execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	catch (Exception e)
	 {
	 output = "Error while inserting";
	 System.err.println(e.getMessage());
	 }
	return output;
	}
	
	
	
	public String readItems()
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for reading.";
	 }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>ID</th>"
	 +"<th>unit</th><th>unit fee</th>"
	 
	 + "<th>Update</th><th>Remove</th></tr>";
	 String query = "select * from billdata";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String id = Integer.toString(rs.getInt("id"));
	 String unit = rs.getString("unit");
	 String unifee = rs.getString("unifee");
	 //String itemPrice = rs.getString("itemPrice");
	 //String itemDesc = rs.getString("itemDesc");
	 // Add a row into the html table
	 output += "<tr><td>" + id + "</td>";
	 output += "<td>" + unit + "</td>";
	output += "<td>" + unifee + "</td>";
	
	 // buttons
	 output += "<td><input name='btnUpdate' "
	 + " type='button' value='Update'></td>"
	 + "<td><form method='post' action='details.jsp'>"
	 + "<input name='btnRemove' "
	 + " type='submit' value='Remove'>"
	 + "<input name='id' type='hidden' "
	 + " value='" + id + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	catch (Exception e)
	 {
	 output = "Error while reading the items.";
	 System.err.println(e.getMessage());
	 }
	return output;
	}


}
