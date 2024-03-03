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
	void createUser() {
		// This method is used to create a new user by sending a POST request to the API.
		// User data is organized using HashMap, which is then sent as JSON.
		// The created user id is saved for use in other methods.
		
		HashMap<String, Object> data = new HashMap<>();
		data.put("name", "saka");
		data.put("email","saka@mail.com");
		data.put("gender","male");
		data.put("status","active");
		
		id = given()
			.contentType(ContentType.JSON)
			.header("Authorization", Config.API_TOKEN )
			.body(data)
			
		.when()
			.post(Config.getApiUrl("/public/v2/users"))
			.jsonPath().getInt("id");		

//		.then()
//			.statusCode(201)
//			.log().all();
	}
	
	@Test (priority = 2, dependsOnMethods = {"createUser"})
	void getUser() {
		// This method is used to get user information by sending a GET request to the API.
		// The previously created user id is used in the request URL.
		
		given ()
		.header("Authorization", Config.API_TOKEN )

		.when ()
			.get(Config.getApiUrl("/public/v2/users/" + id))
		
		.then ()
			.statusCode(200) // Make sure the API response has a status code of 200 (OK).
			.log().all();
		
	}
	
	@Test (priority = 3, dependsOnMethods = {"getUser"})
	void updateUser() {
		// This method is used to update user information by sending a PUT request to the API.
		// User update data is organized using HashMap and sent as JSON.
				
		HashMap<String, Object> data = new HashMap<>();
		data.put("name", "nasi");
		data.put("email","nasi@mail.com");
		data.put("gender","female");
		data.put("status","active");
		
		given()
			.contentType(ContentType.JSON)
			.body(data)
			.header("Authorization", Config.API_TOKEN )
			
		.when()
			.put(Config.getApiUrl("/public/v2/users/" + id))

		.then()
			.statusCode(200) // Make sure the API response has a status code of 200 (OK).
			.log().all();
	}
	
	@Test (priority = 4, dependsOnMethods = {"updateUser"})
	void deleteUser() {
		// This method is used to delete a user by sending a DELETE request to the API.
		// The previously created user id is used in the request URL.
		
		given()
			.contentType(ContentType.JSON)
			.header("Authorization", Config.API_TOKEN )
		
		.when()
			.delete(Config.getApiUrl("/public/v2/users/" + id))
		
		.then()
			.statusCode(204) // Make sure the API response has a status code of 204 (No Content).
			.log().all();
		
	}
}
