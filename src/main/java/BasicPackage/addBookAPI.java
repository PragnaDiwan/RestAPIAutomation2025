package BasicPackage;

import  io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;



public class addBookAPI {
	
	
	public static void main(String [] args)
	{
		
		RestAssured.baseURI="http://216.10.245.166";
		
		String response=given().log().all().
				header("Content-Type","application/json").body("{\r\n"
						+ "\r\n"
						+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
						+ "\"isbn\":\"bcd\",\r\n"
						+ "\"aisle\":\"227\",\r\n"
						+ "\"author\":\"John foe\"\r\n"
						+ "}\r\n"
						+ "").when().
		post("/Library/Addbook.php").then().
		assertThat().statusCode(200).extract().response().asString();
		System.out.print(response);

		JsonPath js=new JsonPath(response);
		String id=js.get("ID");
		System.out.print(id);

	
	}

}
