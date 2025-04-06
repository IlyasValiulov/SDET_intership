package extensions;

import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class DbWork {
    @Step("Метод удаления сущности по id")
    public static void deleleEntityById(int id) {
        given()
                .when()
                .delete("api/delete/" + id)
                .then()
                .statusCode(204);
    }
}
