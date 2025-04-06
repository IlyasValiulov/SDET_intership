package tests;

import com.google.gson.Gson;
import extensions.DbWork;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.Addition;
import pojo.Entity;
import pojo.EntityResponse;

import java.util.Arrays;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class TestApi {
    Integer userId;
    Gson gson;
    protected RequestSpecification requestSpec;

    @BeforeClass
    public void setup() {
        gson = new Gson();
        RestAssured.defaultParser = Parser.JSON;
        requestSpec = given()
                .contentType(ContentType.JSON)
                .baseUri("http://localhost:8080/");
    }

    @Test
    public void Post_CreateEntity_Test() {
        Entity entity = Entity.builder().build();

        userId = given()
                .spec(requestSpec)
                .body(gson.toJson(entity))
            .when()
                .post("/api/create")
            .then()
                .statusCode(200)
                .extract().as(Integer.class);
    }

    @Test
    public void Get_GetAllEntities_Test() {
        String searchTitle = "Заголовок сущности";
        boolean verifiedStatus = true;
        int page = 1;
        int perPage = 10;

        EntityResponse response = given()
                .queryParams(Map.of(
                        "title", searchTitle,
                        "verified", verifiedStatus,
                        "page", page,
                        "perPage", perPage
                ))
            .when()
                .get("/api/getAll")
            .then()
                .statusCode(200)
                .extract()
                .as(EntityResponse.class);

        assertEquals(page, response.getPage());
        response.getEntities().forEach(entity -> {
            assertEquals(searchTitle, entity.getTitle());
            assertTrue(entity.isVerified());
        });
        assertTrue(response.getEntities().size() <= perPage);
    }

    @Test
    public void Get_GetEntityById_Test() {
        int id = 1;

        Entity response = given()
                .spec(requestSpec)
                .pathParam("id", id)
            .when()
                .get("api/get/{id}")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .as(Entity.class);

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals("Заголовок сущности", response.getTitle());
        assertTrue(response.isVerified());
        assertEquals(Arrays.asList(42, 87, 15), response.getImportant_numbers());
        Addition addition = response.getAddition();
        assertNotNull(addition);
        assertEquals("Дополнительные сведения", addition.getAdditional_info());
        assertEquals(124, addition.getAdditional_number());
    }

    @Test
    public void Patch_PatchEntityById_Test() {
        int id = 2;

        Entity patchRequest = new Entity();
        patchRequest.setTitle("Updated Title");
        patchRequest.setVerified(false);
        patchRequest.setImportant_numbers(Arrays.asList(1, 2, 3));

        Addition addition = new Addition();
        addition.setAdditional_info("Updated Additional Info");
        addition.setAdditional_number(456);
        patchRequest.setAddition(addition);

        given()
                .spec(requestSpec)
                .pathParam("id", id)
                .body(patchRequest)
            .when()
                .patch("/api/patch/{id}")
            .then()
                .statusCode(204);
    }

    @Test
    public void Delete_DeleteEntityById_Test() {
        int id = 18;
        given()
                .when()
                .delete("api/delete/" + id)
                .then()
                .statusCode(204);
    }

    @AfterMethod
    public void tearDown() {
        if (userId != null)
            DbWork.deleleEntityById(userId);
        userId = null;
    }
}
