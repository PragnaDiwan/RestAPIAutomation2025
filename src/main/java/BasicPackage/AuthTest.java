package BasicPackage;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured .*;


public class AuthTest {

	public static void main(String[] args) {

		
		RestAssured.baseURI="https://rahulshettyacademy.com"; //set baseuri
		
		String response=given().log().all().
		formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
		formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W").
		formParam("grant_type","client_credentials").
		formParam("scope","trust").when().post("oauthapi/oauth2/resourceOwner/token").then().log().all().
		assertThat().statusCode(200).
		extract().response().
		asString();
		
		JsonPath js=new JsonPath(response);
		String tokenid=js.getString("access_token");
		System.out.println(tokenid);
		
		//=============================================================.
		
	String responsegetcourse=	given().log().all().
		queryParam("access_token",tokenid).when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails"
				).
		asString();
				
	System.out.println(responsegetcourse);
				
	}

}
