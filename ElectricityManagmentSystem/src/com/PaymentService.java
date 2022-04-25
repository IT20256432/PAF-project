package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.payment;

	@Path("/Payment")
	public class PaymentService {
	payment itemObj = new payment();
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
	public String insertItem(@FormParam("Name") String Name,
	@FormParam("Address") String Address,
	@FormParam("Email") String Email,
	@FormParam("Type") String Type,
	@FormParam("Amount") String Amount,
	@FormParam("Stus") String Stus)
	{
	String output = itemObj.insertItem(Name, Address, Email, Type, Amount, Stus);
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
	String ID = itemObject.get("ID").getAsString();
	String Name = itemObject.get("Name").getAsString();
	String Address = itemObject.get("Address").getAsString();
	String Email = itemObject.get("Email").getAsString();
	String Type = itemObject.get("Type").getAsString();
	String Amount = itemObject.get("Amount").getAsString();
	String Stus = itemObject.get("Stus").getAsString();
	String output = itemObj.updateItem(ID, Name, Address, Email, Type, Amount, Stus);
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
	String ID = doc.select("ID").text();
	String output = itemObj.deleteItem(ID);
	return output;
	}




	}
	







