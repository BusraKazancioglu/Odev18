package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class ApiTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void getPetId() {

        Response response =
                given()
                        .when()
                        .get("/pet/100");  // path düzeltildi

        response.then()
                .statusCode(200)
                .body("id", equalTo(100))
                .body("name", equalTo("doggie"));
        System.out.println("Get metodu başarılı. Yanıt süresi: " +response.getTime());
    }


    @Test
    public void createPetTest() {

        String requestBody = """
                {
                    "id": 123,
                    "category": {
                        "id": 1,
                        "name": "Büş"
                    },
                    "name": "Büş",
                    "photoUrls": [
                        "string"
                    ],
                    "tags": [
                        {
                            "id": 0,
                            "name": "string"
                        }
                    ],
                    "status": "available"
                }
                """;

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)

                .when()
                .post("/pet");

                response.then()
                .statusCode(200)
                .body("name", equalTo("Büş"));

        System.out.println("Post istek kodu: " + response.statusCode());
    }

}