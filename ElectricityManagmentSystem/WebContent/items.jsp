<%@page import="com.Item"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
      
      
      
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consumption page</title>

	<!-- links -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
	<link rel="stylesheet" href="View/docs.min.css">
	<script src="Components/jquery.min.js" type="text/javascript"></script>
	<script src="Components/items.js" type="text/javascript"></script>
	
	<style >
	body{
    background-color: #0a140b;
    background-image:url("formBack.jpg");
    background-repeat: no-repeat;
 	 background-size: auto;
 	 min-height: 100%;
 	 background-size: cover;
 	 background-position: center center;
	}
	
	
	
	input[type=text], select {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}





.col-6 {
  	background-color: rgba(255,255,255,0.13);   
    top: 50%;
    position: center center;
    border-radius: 10px;
    backdrop-filter: blur(10px);
    border: 2px solid rgba(255,255,255,0.1);
    box-shadow: 0 0 40px rgba(8,7,16,0.6);
    
}



	</style>
	
	
	

</head>
<body>

	<div class="container"><div class="row"><div class="col-6"> 
    	<h1>Generate Monthly Bill </h1>
    		<form id="formItem" name="formItem">
    	
    			Previous Unit Count :
    				<input class="input--style-2" type = "text" id = "punit"/> <br><br>
    			Current Unit Count :
    				<input class="input--style-2" type = "text" id = "nunit"/> <br>
		    	Price per Unit :
		    		<input id="unifee" name="unifee" type="text" class="form-control form-control-sm"><br>
		    
		    	No of Units :
		    		<span id = "totalunits"></span><br>
		    	Total Bill Amount :
		    		<span id = "totalbill"></span><br>
		    	
		    	<button class="btn btn-warning" type="button" onClick = "Tcost()"   >Total cost</button><br><br>
				<button class="btn btn-success" type="button" onClick = "update()"   >confirm data</button><br>
		    
		    	No of Units :
		    		<input id="unit" name="unit" type="text" class="form-control form-control-sm">
		    	Total Bill Amount :
		    		<input id="total" name="total" type="text" class="form-control form-control-sm"><br>
     
		     	<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		     	<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
    	</form>
    		<div id="alertSuccess" class="alert alert-success"></div>
    		<div id="alertError" class="alert alert-danger"></div>
    		   <br>
    			<div id="divItemsGrid">
    			
     <%
     Item itemObj = new Item(); 
     out.print(itemObj.readItems()); 
     %>
    			</div>
    		</div>
     	</div>
     </div> 
    
<script>
    function Tcost()
    {    
    	unit = document.getElementById("unit").value;
  		unifee = document.getElementById("unifee").value;
  		punit = document.getElementById("punit").value;
  		nunit = document.getElementById("nunit").value;
  
  		document.getElementById("totalunits").innerHTML = nunit - punit; 
  		document.getElementById("totalbill").innerHTML = (nunit - punit) * unifee; 
    }
    function update()
	{        
  		document.getElementById("unit").value = document.getElementById("totalunits").textContent;
  		document.getElementById("total").value = document.getElementById("totalbill").textContent;   
	}
  </script>
  
  
</body>
</html>