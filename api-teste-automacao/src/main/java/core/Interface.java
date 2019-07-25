package core;

import io.restassured.http.ContentType;

/**
 * 
 * @author matheus.galisteo
 *
 */

public interface Interface {
	
	String BASE_URL = "http://5b847b30db24a100142dce1b.mockapi.io/api/v1/simulador";
	Integer PORT = 80;
	String BASE_PATH = "";
	
	ContentType CONTENT_TYPE = ContentType.JSON;

}


