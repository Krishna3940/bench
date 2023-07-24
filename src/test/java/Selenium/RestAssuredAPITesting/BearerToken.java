package Selenium.RestAssuredAPITesting;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

@SuppressWarnings("unchecked")
public class BearerToken
{
	String url = "https://gorest.co.in/";
	String endPoint = "public/v2/users/";
	private String bearerToken = "Bearer 4805f950a3cfa7125c3a4fc2deb4dd5ec99a38fe21f84da559df96cabc9da34e";
	int id;
	
	@Test(priority=1)
	public void postMethod()
	{
		// JSONObject is a class that represents a Simple JSON.
		JSONObject requestParams = new JSONObject(); 
		requestParams.put("name", "Krishna"); 
		requestParams.put("gender", "Male");
		requestParams.put("email", "krishna123@gmail.com");
		requestParams.put("status", "Active");
		
		Response response = given().
			baseUri(url).basePath(endPoint).header("Authorization",bearerToken).contentType(ContentType.JSON).
			body(requestParams.toString()).
		when().
			post();
		response.then().assertThat().statusCode(201).log().all();
		id = response.jsonPath().getInt("id");
	}

	
	@Test(priority=2, dependsOnMethods= {"postMethod"})
	public void getMethod()
	{
		given().
			baseUri(url).basePath(endPoint).header("Authorization",bearerToken).
		when().
			get("{id}",id).
		then().assertThat().statusCode(200).log().all();
	}
	
	@Test(priority=3, dependsOnMethods= {"postMethod"})
	public void patchMethod()
	{
		// JSONObject is a class that represents a Simple JSON.
		JSONObject requestParams = new JSONObject(); 
		requestParams.put("name", "Gaurav Mahor");
		
		given().
			baseUri(url).basePath(endPoint).header("Authorization",bearerToken).contentType(ContentType.JSON).
			body(requestParams.toString()).
		when().
			patch("{id}",id).
		then().assertThat().statusCode(200).log().all();
	}
	
	@Test(priority=4, dependsOnMethods= {"postMethod"})
	public void putMethod()
	{
		// JSONObject is a class that represents a Simple JSON.
		JSONObject requestParams = new JSONObject(); 
		requestParams.put("name", "Kanak Singh");
		requestParams.put("gender", "Female");
		requestParams.put("email", "kanaksingh001@gmail.com");
		requestParams.put("status", "active");
		
		given().
			baseUri(url).basePath(endPoint).header("Authorization",bearerToken).contentType(ContentType.JSON).
			body(requestParams.toString()).
		when().
			put("{id}",id).
		then().assertThat().statusCode(200).log().all();
	}
	
	@Test(priority=5, dependsOnMethods= {"postMethod"})
	public void deleteMethod()
	{
		given().
			baseUri(url).basePath(endPoint).header("Authorization",bearerToken).
		when().
			delete("{id}",id).
		then().
			assertThat().statusCode(204).log().all();
	}
	
}
