package selenium.API;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class NegativeRequestAPI {

    @Test(priority = 5)
    void createDuplicateUser() {
        // This method is used to test creating a duplicate user, which should fail.
        // Sending a POST request with the same user data as before.

        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "saka");
        data.put("email", "saka@mail.com");
        data.put("gender", "male");
        data.put("status", "active");

        given()
            .contentType(ContentType.JSON)
            .header("Authorization", Config.API_TOKEN)
            .body(data)

        .when()
            .post(Config.getApiUrl("/public/v2/users"))

        .then()
            .statusCode(422) // Expecting a status code 422 (Unprocessable Entity) for duplicate user creation.
            .log().all();
    }

    @Test(priority = 6, dependsOnMethods = {"createUser"})
    void getUserWithInvalidId() {
        // This method is used to test getting user information with an invalid user id.

        given()
            .header("Authorization", Config.API_TOKEN)

        .when()
            .get(Config.getApiUrl("/public/v2/users/invalidUserId"))

        .then()
            .statusCode(404) // Expecting a status code 404 (Not Found) for an invalid user id.
            .log().all();
    }

    @Test(priority = 7, dependsOnMethods = {"getUser"})
    void updateUserWithInvalidId() {
        // This method is used to test updating user information with an invalid user id.

        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "nasi");
        data.put("email", "nasi@mail.com");
        data.put("gender", "female");
        data.put("status", "active");

        given()
            .contentType(ContentType.JSON)
            .body(data)
            .header("Authorization", Config.API_TOKEN)

        .when()
            .put(Config.getApiUrl("/public/v2/users/invalidUserId"))

        .then()
            .statusCode(404) // Expecting a status code 404 (Not Found) for an invalid user id.
            .log().all();
    }

    @Test(priority = 8, dependsOnMethods = {"updateUser"})
    void deleteUserWithInvalidId() {
        // This method is used to test deleting a user with an invalid user id.

        given()
            .contentType(ContentType.JSON)
            .header("Authorization", Config.API_TOKEN)

        .when()
            .delete(Config.getApiUrl("/public/v2/users/invalidUserId"))

        .then()
            .statusCode(404) // Expecting a status code 404 (Not Found) for an invalid user id.
            .log().all();
    }
}
