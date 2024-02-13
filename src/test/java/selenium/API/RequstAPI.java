package selenium.API;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class RequstAPI {
	
	int id;

	@Test (priority = 1)
	void getUser() {
		
		given ()
			.header("Authorization", "Bearer 7ae181045e2c211e897a4cf823c0d1f8976a087f83a9af4e9e595454b296c29b" )

		.when ()
			.get("https://gorest.co.in/public/v2/users")
		
		.then ()
			.statusCode(200)
			.log().all();
		
	}
	
	@Test (priority = 2)
	void createUser() {
		
		HashMap<String, Object> data = new HashMap<>();
		data.put("name", "sakura");
		data.put("email","sakura@mail.com");
		data.put("gender","female");
		data.put("status","active");
		
		id = given()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer 7ae181045e2c211e897a4cf823c0d1f8976a087f83a9af4e9e595454b296c29b" )
			.body(data)
			
		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id");
			

//		.then()
//			.statusCode(201)
//			.log().all();
	}
	
	@Test (priority = 3, dependsOnMethods = {"createUser"})
	void updateUser() {
				
		HashMap<String, Object> data = new HashMap<>();
		data.put("name", "nami");
		data.put("email","nami@mail.com");
		data.put("gender","female");
		data.put("status","active");
		
		given()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer 7ae181045e2c211e897a4cf823c0d1f8976a087f83a9af4e9e595454b296c29b" )
			.body(data)
			
		.when()
			.put("https://gorest.co.in/public/v2/users/" + id)

		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test (priority = 4)
	void deleteUser() {
		
		given()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer 7ae181045e2c211e897a4cf823c0d1f8976a087f83a9af4e9e595454b296c29b" )
		
		.when()
			.delete("https://gorest.co.in/public/v2/users/" + id)
		
		.then()
			.statusCode(204)
			.log().all();
		
	}
}
