package extensions;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import pojo.Addition;
import pojo.Entity;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class DbWork {
    static Gson gson = new Gson();

    @Step("Метод создания сущности")
    public static int createEntity() {
        Addition addition = Addition.builder().additional_info("additional_info").additional_number(123).build();
        Entity entity = Entity.builder().addition(addition).important_numbers(Arrays.asList(42, 87, 15)).title("Заголовок сущности").verified(true).build();

        int userId = given()
                .contentType(ContentType.JSON)
                .body(gson.toJson(entity))
                .when()
                .post("/api/create")
                .then()
                .statusCode(200)
                .extract().as(Integer.class);
        return userId;
    }

    @Step("Метод удаления сущности по id")
    public static void deleleEntityById(int id) {
        given()
                .when()
                .delete("api/delete/" + id)
                .then()
                .statusCode(204);
    }
}
