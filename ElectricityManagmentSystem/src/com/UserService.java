package com;

import model.User;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/User")

public class UserService {

	User UserObj = new User();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUser()
	 {
	 return UserObj.readUser();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(@FormParam("Name") String Name,
	 @FormParam("Address") String Address,
	 @FormParam("TelephoneNo") String TelephoneNo,
	 @FormParam("PremisesId") String PremisesId)
	{
	 String output = UserObj.insertUser(Name, Address, TelephoneNo, PremisesId);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData)
	{
	//Convert the input string to a JSON object
	 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
	//Read the values from the JSON object
	 String UserId = userObject.get("UserId").getAsString();
	 String Name = userObject.get("Name").getAsString();
	 String Address = userObject.get("Address").getAsString();
	 String TelephoneNo = userObject.get("TelephoneNo").getAsString();
	 String PremisesId = userObject.get("PremisesId").getAsString();
	 String output = UserObj.updateUser(UserId, Name, Address, TelephoneNo, PremisesId);
	return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String UserId = doc.select("UserId").text();
	 String output = UserObj.deleteUser(UserId);
	return output;
	}

	
}
