package TestTasks;

import api.models.FindFilms;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.List;

import static api.Endpoints.FILMS;
import static api.models.FindFilms.getTitleFilms;

public class AutomationTestingAPITest {
    String expectedTitleFilm = "A New Hope";

    @Test
    public void testAPI() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .setBaseUri("https://swapi.dev")
                .setBasePath("/api")
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .build();

        ValidatableResponse filmsResponse = RestAssured
                .given()
                .when()
                .get(FILMS)
                .then()
                .statusCode(200);

        List<FindFilms.Result> results = filmsResponse.extract().as(FindFilms.class).getResults();
        List<String> titleFilms = getTitleFilms(results);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(titleFilms)
                .contains(expectedTitleFilm);

        softAssertions.assertAll();
    }
}
