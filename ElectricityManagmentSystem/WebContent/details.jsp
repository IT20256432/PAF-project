<%@ page import="consumptionManagment.DataMange"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% 
if (request.getParameter("unit") != null)
 {
 DataMange itemObj = new DataMange();
 //String stsMsg = itemObj.insertItem(request.getParameter("unit"),
		 String stsMsg = itemObj.insertItem(request.getParameter("unit"),
 request.getParameter("unifee"));
 session.setAttribute("statusMsg", stsMsg);
 }

//Delete item----------------------------------
if (request.getParameter("id") != null) 
{ 
DataMange itemObj = new DataMange(); 
String stsMsg = itemObj.deleteItem(request.getParameter("id")); 
session.setAttribute("statusMsg", stsMsg); 
}
%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<!--  
<h1>Items Management</h1>
<form method="post" action="details.jsp">
 Item code: <input name="itemCode" type="text"><br> Item
 name: <input name="itemName" type="text"><br> Item price:
 <input name="itemPrice" type="text"><br> Item
 description: <input name="itemDesc" type="text"><br> <input
 name="btnSubmit" type="submit" value="Save">
</form>
-->

<h1>Items Management</h1>
<form method="post" action="details.jsp">
 Item code: <input name="unit" type="text"><br> 
 name: <input name="unifee" type="text"><br> 

  <input name="btnSubmit" type="submit" value="Save">
</form>

<%
 out.print(session.getAttribute("statusMsg"));
%>
<br>
<%
 DataMange itemObj = new DataMange();
 out.print(itemObj.readItems());
%>

</body>
</html>