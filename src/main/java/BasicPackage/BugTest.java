package BasicPackage;

import org.testng.annotations.Test;
import static io.restassured.RestAssured .*;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class BugTest {
	
	
	
	public static void main(String [] args)
	{
		RestAssured.baseURI="https://pragnadiwan.atlassian.net/";
		
		String response=given().log().all().
				header("Content-Type","application/json").header("Authorization","Basic cGlua2xlZGl3YW5AZ21haWwuY29tOkFUQVRUM3hGZkdGMHFiNmtjYnBmQjlOMi1CSnptVzNNYzFid281WHhuT0pWY2J0R05peExTMmVGMnB1d291cXVGZkJ0bkt1ck9iaTBzU2tjejdjZDR1TC1iR2tNeDI5dFdvUjZybjdLajlNUTNLSURRcVBmRkNGd1dEODNkQlpXUi15VGNPdWs1UUtTN05uT2k0b2hCem03cV92T1dLLXFockJsQUxHek9IS19PVkthM1BHYWg2VT0zMDdCQTYxOA==").
	body("{\r\n"
			+ "    \"fields\": {\r\n"
			+ "       \"project\":\r\n"
			+ "       {\r\n"
			+ "          \"key\": \"SCRUM\"\r\n"
			+ "       },\r\n"
			+ "       \"summary\": \"link not working.\",\r\n"
			+ "       \"issuetype\": {\r\n"
			+ "          \"name\": \"Bug\"\r\n"
			+ "       }\r\n"
			+ "   }\r\n"
			+ "}").
	when().post("rest/api/3/issue").then().log().all().
	assertThat().statusCode(201).extract().response().asString();		
		System.out.print(response);
		
		JsonPath js=new JsonPath(response);
		String bugid=js.getString("id");
		System.out.println(bugid);
///////////////////////////////////////////attach screenshot//////////////
		
	String attachresponse=	given().log().all().
			pathParam("key", bugid)
		.header("X-Atlassian-Token","no-check").
		header("Authorization","Basic cGlua2xlZGl3YW5AZ21haWwuY29tOkFUQVRUM3hGZkdGMHFiNmtjYnBmQjlOMi1CSnptVzNNYzFid281WHhuT0pWY2J0R05peExTMmVGMnB1d291cXVGZkJ0bkt1ck9iaTBzU2tjejdjZDR1TC1iR2tNeDI5dFdvUjZybjdLajlNUTNLSURRcVBmRkNGd1dEODNkQlpXUi15VGNPdWs1UUtTN05uT2k0b2hCem03cV92T1dLLXFockJsQUxHek9IS19PVkthM1BHYWg2VT0zMDdCQTYxOA==")
		.multiPart("file",new File("C:\\Users\\Kamlesh Diwan\\OneDrive\\Desktop\\fluuter.txt")).
		when().post("rest/api/3/issue/{key}/attachments").then().log().all().
		statusCode(200).extract().response().asString();
		System.out.print(attachresponse);

}
}
