package com;

import model.bill;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;



@Path("/bill") 
public class BillService {
	bill itemObj = new bill();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
	 return itemObj.readItems();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
public String insertItem(@FormParam("cusName") String cusName,
	 @FormParam("cusEmail") String cusEmail,
	 @FormParam("accNo") String accNo,
	 @FormParam("cusCNo") String cusCNo,
     @FormParam("billval") String billval)
	{
	 String output = itemObj.insertItem(cusName, cusEmail, accNo, cusCNo, billval);
	return output;
	}	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData)
	{
	//Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	 String billID = itemObject.get("billID").getAsString();
	 String cusName = itemObject.get("cusName").getAsString();
	 String cusEmail = itemObject.get("cusEmail").getAsString();
	 String accNo = itemObject.get("accNo").getAsString();
	 String cusCNo = itemObject.get("cusCNo").getAsString();
	 String billval = itemObject.get("billval").getAsString();
	 
	 String output = itemObj.updateItem(billID, cusName, cusEmail, accNo, cusCNo, billval);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String billID = doc.select("billID").text();
	 String output = itemObj.deleteItem(billID);
	return output;
	}

	
}
