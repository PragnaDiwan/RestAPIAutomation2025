package BasicPackage;

import static io.restassured.RestAssured.given;

import  io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import files.Payload;
public class Basic {

	
	public static void main(String[]args)
	{
		//first set base uri
		//given-All input method
		//when-submit the api and resource and httpmethod
		//then validate the respnse
		
		
		RestAssured.baseURI="https://rahulshettyacademy.com"; //set baseuri
		
		String Response=given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(
				Payload.addplace()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200).
		header("Server", "Apache/2.4.52 (Ubuntu)").body("scope", equalTo("APP")).extract().response().asString();
		
						
		System.out.print(Response);
		
		JsonPath js=new JsonPath(Response);
		String placeid=js.getString("place_id");
		System.out.println("place id is"+placeid);
		////////////////////////////////////////////////////////////////
		//put method
		given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeid).
		header("Content-Type", "application/json").body("{\r\n"
				+ "\"place_id\":'"+placeid+"',\r\n"
				+ "\"address\":\"70 winter walk, USA\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200);
		
		
		//get Method
		given().queryParam("key", "qaclick123").queryParam("place_id", placeid)
		.when().
		get("maps/api/place/get/json").then().log().all().
		assertThat().statusCode(200);
		
		
	
	}
	
	


	}
	

