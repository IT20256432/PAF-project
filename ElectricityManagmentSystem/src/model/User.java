package model;

import java.sql.*;


public class User {


	//A common method to connect to the DB
	private Connection connect()
	 {
		Connection con = null;
	 try
	 {
		 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electricitymanagmentsystem", "root", "");
	 }
	 catch (Exception e)
	 {
		 e.printStackTrace();}
	 	return con;
	 }
	
	
	public String insertUser(String Name, String Address, String TelephoneNo, String PremisesId)
	 {
		String output = "";
	 try
	 {
		 Connection con = connect();
	 if (con == null)
	 {
		 return "Error while connecting to the database for inserting.";
	}
	 // create a prepared statement
	 String query = " insert into user (`UserId`,`Name`,`Address`,`TelephoneNo`,`PremisesId`) values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2,Name );
	 preparedStmt.setString(3, Address);
	 preparedStmt.setInt(4, Integer.parseInt(TelephoneNo));
	 preparedStmt.setInt(5, Integer.parseInt(PremisesId));
	 
	 // execute the statement
	 
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	public String readUser()
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
	 output = "<table border='1'><tr><th>UserId</th><th>Name</th><th>Address</th>" +
	 "<th>TelephoneNo</th>" +
	 "<th>PremisesId</th>" +
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from user";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String UserId = Integer.toString(rs.getInt("UserId"));
	 String Name = rs.getString("Name");
	 String Address = rs.getString("Address");
	 String TelephoneNo = Integer.toString(rs.getInt("TelephoneNo"));
	 String PremisesId = Integer.toString(rs.getInt("PremisesId"));
	
	 // Add into the html table
	 output += "<tr><td>" + UserId + "</td>";
	 output += "<td>" + Name + "</td>";
	 output += "<td>" + Address + "</td>";
	 output += "<td>" + TelephoneNo + "</td>";
	 output += "<td>" + PremisesId + "</td>";
	 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='items.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='itemID' type='hidden' value='" + UserId
	 + "'>" + "</form></td></tr>";
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
	
	public String updateUser(String UserId, String Name, String Address, String TelephoneNo, String PremisesId)
	 
	 {
		String output = "";
	 try
	 {
		 Connection con = connect();
	 if (con == null)
	 {
		 return "Error while connecting to the database for updating.";
	}
	 // create a prepared statement
	 String query = "UPDATE user SET Name=?,Address=?,TelephoneNo=?,PremisesId=? WHERE UserId=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, Name);
	 preparedStmt.setString(2, Address);
     preparedStmt.setInt(3, Integer.parseInt(TelephoneNo));
	 preparedStmt.setInt(4, Integer.parseInt(PremisesId));
	 preparedStmt.setInt(5, Integer.parseInt(UserId));
	 
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
	
	
	public String deleteUser(String UserId)
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
	 String query = "delete from user where UserId=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(UserId));
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
