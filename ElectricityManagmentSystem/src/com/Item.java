package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



public class Item 
{ 
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 con = DriverManager.getConnection( "jdbc:mysql://127.0.0.1:3307/test", "root", ""); 
 } 
 catch (Exception e) 
 { 
 e.printStackTrace(); 
 } 
 return con; 
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
 output = "<table border='1'><tr><th>unit</th> <th>unifee</th><th>total</th>"+ "<th>Update</th><th>Remove</th></tr>"; 
 String query = "select * from system"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String id = Integer.toString(rs.getInt("id")); 
 String unit = rs.getString("unit");
 String unifee = rs.getString("unifee"); 
 String total = Double.toString(rs.getDouble("total")); 

 // Add into the html table
output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + id+ "'>" + unit + "</td>"; 
 output += "<td>" + unifee + "</td>"; 
 output += "<td>" + total + "</td>"; 
  
 // buttons
output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-id='"+ id + "'></td>"+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"+ id + "'>" + "</td></tr>"; 
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
public String insertItem(String unit, String unifee, 
 String total) 
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
 String query = " insert into system (id, unit, unifee,total)"+"values (?,?,?,?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, unit); 
		 preparedStmt.setString(3, unifee); 
		 preparedStmt.setString(4, total); 
		  
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = readItems(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\":  \"Error while inserting the item.\"}"; System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		public String updateItem(String id, String unit, String unifee, String total) 
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
		 String query = "update system set unit=?,unifee=?,total=? WHERE id=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, unit); 
		 preparedStmt.setString(2, unifee); 
		 preparedStmt.setString(3,total); 		  
		 preparedStmt.setInt(4, Integer.parseInt(id)); 
		// execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = readItems(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		public String deleteItem(String id) 
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
		 String query = "delete from system where id=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(id)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newItems = readItems(); 
		 output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		}


