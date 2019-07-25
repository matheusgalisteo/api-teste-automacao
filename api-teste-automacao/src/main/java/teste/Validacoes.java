package teste;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.*;
import org.junit.Assert;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * 
 * @author matheus.galisteo
 *
 */

public class Validacoes {
	@Test
	public void deveVerificarStatusRetorno() {
		given()
		.when()
			.get("http://5b847b30db24a100142dce1b.mockapi.io/api/v1/simulador")
		.then()
			.statusCode(200)
			;
	}
	@Test
	public void deveValidarBody(){
		
		given()
		.when()
			.get("http://5b847b30db24a100142dce1b.mockapi.io/api/v1/simulador")
		.then()
			.body("id", is(1))
			.body("meses", hasSize(4))
			.body("valor", hasSize(4))
			;
	}
	@Test
	public void deveVerificarPrimeiroNivelAssert() {
		Response response = RestAssured.request(Method.GET, "http://5b847b30db24a100142dce1b.mockapi.io/api/v1/simulador");
		// jsonpath
		JsonPath jpath = new JsonPath(response.asString());
		Assert.assertEquals(1, jpath.getInt("id"));
	}

	@Test
	public void deveVerificarListaRetornada() {
		given()
		.when()
		.get("http://5b847b30db24a100142dce1b.mockapi.io/api/v1/simulador")
		.then().statusCode(200)
				.body("id", is(1))
				.body("valor", hasSize(4))
				.body("meses", hasSize(4) )
				.body("meses[0]", is("112"))
				.body("meses[1]", is("124"))
				.body("meses[2]", is("136"))
				.body("meses[3]", is("148"))
				.body("valor[0].toFloat()", is(2.802f))
				.body("valor[1].toFloat()", is(3.174f))
				.body("valor[2].toFloat()", is(3.564f))
				.body("valor[3].toFloat()", is(3.971f))
				;
	}
	//API retornou "Max number of elements reached for this resource!" caso de teste não funcionou para adição de simulação
	@Test
	public void deveSalvarSimulacao(){
		given()
			.log().all() //ver a requisição que estou mandando
			.body("{\"meses\": \"360\",\"valor\": 5000}")
		.when()
			.post("http://5b847b30db24a100142dce1b.mockapi.io/api/v1/simulador")
		.then()
			.log().all()
			.statusCode(201)
			.body("id", is(notNullValue()))
			.body("meses", is("360"))
			.body("valor", is(5000f))
		;
	}
	@Test
	public void deveValidarSimulacao() {
		Map<String,String> simulation = new HashMap<>();
		simulation.put("perfil", "paraVoce");
		simulation.put("valorAplicar", "3000");
		simulation.put("valorInvestir", "1000");
		simulation.put("tempo", "24");
		simulation.put("periodo", "Meses");		
		given()
		.when()
			.post()
		.then()
			.statusCode(200)
		;		
	}	
}
