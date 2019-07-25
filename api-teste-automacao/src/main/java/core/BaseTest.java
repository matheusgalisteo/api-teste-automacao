package core;

import org.junit.BeforeClass;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

/**
 * 
 * @author matheus.galisteo
 *
 */

public class BaseTest implements Interface{	
	
	@BeforeClass
	public static void setup(){
		RestAssured.baseURI = BASE_URL;
		RestAssured.port = PORT;
		RestAssured.basePath = BASE_PATH;
		
		ContentType CONTENT_TYPE = ContentType.JSON;		
		
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.setContentType(CONTENT_TYPE);
		RestAssured.requestSpecification = reqBuilder.build();
		
	}
}
