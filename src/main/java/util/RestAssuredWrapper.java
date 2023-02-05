package util;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static common.Constants.BASE_URL;


public class RestAssuredWrapper {
    private RequestSpecification requestSpec;

    public RestAssuredWrapper() {

        RestAssured.baseURI = BASE_URL;

        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }

    public void setHeader(String name, String value) {
        requestSpec = requestSpec.header(name, value);
    }

    public void setQueryParam(String name, String value) {
        requestSpec = requestSpec.queryParam(name, value);
    }

    public void setBody(Object body) {
        requestSpec = requestSpec.body(body);
    }

    public Response get(String path) {
        return RestAssured.given(requestSpec)
                .when()
                .get(path)
                .then()
                .extract()
                .response();
    }

    public Response post(String path) {
        return RestAssured.given(requestSpec)
                .when()
                .post(path)
                .then()
                .extract()
                .response();
    }

    public Response put(String path) {
        return RestAssured.given(requestSpec)
                .when()
                .put(path)
                .then()
                .extract()
                .response();
    }

    public Response delete(String path) {
        return RestAssured.given(requestSpec)
                .when()
                .delete(path)
                .then()
                .extract()
                .response();
    }
}

