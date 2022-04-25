package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class payment {
	
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
	//insert...................
	public String insertItem(String name, String address, String email, String type, String amount,String stus)
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
	String query = "insert into payment(ID,Name,Address,Email,Type,Amount,Stus)"+ " values (?,?, ?, ?, ?, ?,?)";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, 0);
	preparedStmt.setString(2, name);
	preparedStmt.setString(3, address);
	preparedStmt.setString(4, email);
	preparedStmt.setString(5, type);
	preparedStmt.setString(6, amount);
	preparedStmt.setString(7, stus);

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
	+ "<th> Name</th>"
	+ "<th> Address</th>"
	+ "<th> Email</th>"
	+ "<th>Type</th>"
	+ "<th>Amount</th>"
	+ "<th>Stus/th>"
	+ "<th>Update</th><th>Remove</th>"
	+ "</tr>";
	String query = "select * from payment";
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	// iterate through the rows in the result set
	while (rs.next())
	{
	String ID = Integer.toString(rs.getInt("ID"));
	String Name = rs.getString("Name");
	String Address = rs.getString("Address");
	String Email = rs.getString("Email");
	String Type = rs.getString("Type");
	String Amount= rs.getString("Amount");
	String Stus = rs.getString("Stus");
	// Add a row into the html table
	output += "<tr><td>" + Name + "</td>";
	output += "<td>" + Address + "</td>";
	output += "<td>" + Email + "</td>";
	output += "<td>" + Type + "</td>";
	output += "<td>" + Amount + "</td>";
	output += "<td>" + Stus + "</td>";
	// buttons
	output += "<td>"
	+ "<input name='btnUpdate' "+ " type='button' value='Update'></td>"
	+ "<td><form method='post' action='bills.jsp'>"
	+ "<input name='btnRemove' "+ " type='submit' value='Remove'>"
	+ "<input name='billID' type='hidden' "
	+ " value='" + ID + "'>" + "</form>"
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

	public String updateItem(String ID, String name, String address, String email, String type, String amount,String stus)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for updating."; }
	// create a prepared statement
	String query = "UPDATE payment SET Name=?,Address=?,Email=?,Type=?,Amount=?,Stus=? WHERE ID=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding value
	preparedStmt.setInt(1, Integer.parseInt(ID));
	preparedStmt.setString(2, name);
	preparedStmt.setString(3, address);
	preparedStmt.setString(4, email);
	preparedStmt.setString(5, type);
	preparedStmt.setString(6, amount);
	preparedStmt.setString(7, stus);
	
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

	public String deleteItem(String ID)
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
	String query = "delete from payment where ID=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, Integer.parseInt(ID));



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
