package Selenium.RestAssuredAPITesting;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@SuppressWarnings("unchecked")
public class APITesting
{
	@Test
	public void getMethod()
	{
		RestAssured.baseURI = "https://reqres.in/";
		RequestSpecification request = RestAssured.
		given();
			Response response = request.
		when().
			get("/api/users?page=2");
			
//		response.prettyPeek();
		System.out.println("Status Body: "+response.getBody().asString());
		System.out.println("Status Code: "+response.getStatusCode());
		System.out.println("Time: "+response.getTime());
		System.out.println("Content-Type: "+response.getHeader("content-type"));
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	public void postMethod()
	{
		// JSONObject is a class that represents a Simple JSON.
		JSONObject requestParams = new JSONObject(); 
		requestParams.put("name", "Gorav"); 
		requestParams.put("profile", "Automation engg.");
		
		RestAssured.baseURI = "https://reqres.in/";
		RequestSpecification request = RestAssured.
		given();
			request.header("Content-Type", "application/json").contentType(ContentType.JSON).body(requestParams.toJSONString()).
		when().
			post("api/users").
		then().assertThat().statusCode(201).log().all(); 
	}
	
	@Test
	public void patchMethod()
	{
		// JSONObject is a class that represents a Simple JSON.
		JSONObject json = new JSONObject(); 
		json.put("name", "Gaurav");
		
		RestAssured.baseURI = "https://reqres.in/";
		
		RequestSpecification request = RestAssured.
		given();
			request.header("Content-Type", "application/json").contentType(ContentType.JSON).body(json.toJSONString()).
		when().
			patch("api/users/998").
		then().
			assertThat().statusCode(200).log().all();
	}
	
	@Test
	public void putMethod()
	{
		// JSONObject is a class that represents a Simple JSON.
		JSONObject json = new JSONObject(); 
		json.put("name", "Gaurav Mahor"); 
		json.put("profile", "Automation Tester");
		
		RestAssured.baseURI = "https://reqres.in/";
		RequestSpecification request = RestAssured.
		given();
			request.header("Content-Type", "application/json").contentType(ContentType.JSON).body(json.toJSONString()).
		when().
			put("api/users/998").
		then().
			assertThat().statusCode(200).log().all();
	}
	
	@Test
	public void deleteMethod()
	{
		RestAssured.baseURI = "https://reqres.in/";
		RequestSpecification request = RestAssured.
		given();
			request.
		when().
			delete("api/users/998").
		then().
			assertThat().statusCode(204).log().all();
	}
}
