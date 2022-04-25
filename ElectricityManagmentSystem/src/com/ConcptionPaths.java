package com;

import model.ConceptionOpareters;

//start
//import javax.websocket.server.PathParam;
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

import javax.ws.rs.core.MediaType; 
@Path("/Hello") 


public class ConcptionPaths {

	
	ConceptionOpareters itemObj = new ConceptionOpareters();
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_PLAIN) 
	public String hello() 
	 { 
		
		return itemObj.readItems();
	 //return "Hello world chathu sa"; 
	 } 
	
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem(@FormParam("unit") String unit, 
	 @FormParam("unifee") String unifee)
	
	  
	{ 
	 String output = itemObj.insertItem(unit, unifee); 
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
	 String id = itemObject.get("id").getAsString();
	 String unit = itemObject.get("unit").getAsString();
	 String unifee = itemObject.get("unifee").getAsString();
	
	 
	 String output = itemObj.updateItem(id, unit, unifee);
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
	 String id = doc.select("id").text();
	 String output = itemObj.deleteItem(id);
	return output;
	}
	
	
}
