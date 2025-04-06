package extensions;

import static io.restassured.RestAssured.given;

public class DbWork {
    public static void deleleEntityById(int id) {
        given()
                .when()
                .delete("api/delete/" + id)
                .then()
                .statusCode(204);
    }
}
