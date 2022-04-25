package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class bill {
	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electricitymanagmentsystem",
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
//insert...................
	public String insertItem(String cname, String cemail, String acno, String ccno, String bval)
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
	 String query = "insert into bill(billID,cusName,cusEmail,accNo,cusCNo,billval)"+ " values (?, ?, ?, ?, ?,?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, cname);
	 preparedStmt.setString(3, cemail);
	 preparedStmt.setString(4, acno);
	 preparedStmt.setString(5, ccno);
	 preparedStmt.setString(6, bval);
	 
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
	
	
//read................	
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
	 output = "<table border='1'>"
	 		+ "<tr>"
	 		+ "<th>Customer Name</th>"
	 		+ "<th>Customer Email</th>"
	 		+ "<th>Account Number</th>"
	 		+ "<th>Customer Contact Number</th>"
	 		+ "<th>Bill Value</th>"
	 		+ "<th>Update</th><th>Remove</th>"
	 		+ "</tr>";
	 String query = "select * from bill";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String billID = Integer.toString(rs.getInt("billID"));
	 String cusName = rs.getString("cusName");
	 String cusEmail = rs.getString("cusEmail");
	 String accNo = rs.getString("accNo");
	 String cusCNo = rs.getString("cusCNo");
	 String billval = rs.getString("billval");
	 // Add a row into the html table
	 output += "<tr><td>" + cusName + "</td>";
	 output += "<td>" + cusEmail + "</td>";
	 output += "<td>" + accNo + "</td>";
	 output += "<td>" + cusCNo + "</td>";
	 output += "<td>" + billval + "</td>";
	 // buttons
	 output += "<td>"
	 		+ "<input name='btnUpdate' "+ " type='button' value='Update'></td>"
	 + "<td><form method='post' action='bills.jsp'>"
	 + "<input name='btnRemove' "+ " type='submit' value='Remove'>"
	 + "<input name='billID' type='hidden' "
	 + " value='" + billID + "'>" + "</form>"
	 		+ "</td>"
	 		+ "</tr>";
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
	
	
	//update
	
	public String updateItem(String bid, String cname, String cemail, String acno, String ccno, String bval)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE bill SET cusName=?,cusEmail=?,accNo=?,cusCNo=?,billval=?WHERE billID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, cname);
	 preparedStmt.setString(2, cemail);
	 preparedStmt.setString(3, acno);
	 preparedStmt.setString(4, ccno);
	 preparedStmt.setString(5, bval);
	 preparedStmt.setInt(6, Integer.parseInt(bid));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 

	
	
	
	//delete..........
	
	public String deleteItem(String billID)
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for deleting.";
	 }
	 // create a prepared statement
	 String query = "delete from bill where billID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(billID));

	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	catch (Exception e)
	 {
	 output = "Error while deleting the item.";
	 System.err.println(e.getMessage());
	 }
	return output;
	}
	

}



