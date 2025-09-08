package com.glsnxt.sdetTask.api.tests;

import com.glsnxt.sdetTask.api.config.Config;
import com.glsnxt.sdetTask.api.config.Endpoints;
import com.glsnxt.sdetTask.api.config.requests.RequestBodyCreateNewPost;
import io.qameta.allure.Description;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@ActiveProfiles("qa")

public class PostApiTest extends Config {

    String postId;
    RequestBodyCreateNewPost requestBody;

    @BeforeClass(alwaysRun = true)
    void setConfig() {
        postsSetUp();
    }

    @Test(description = "Get all posts", groups = "positive")
    @Description("Get all posts and validate the response")
    void getAllPosts() {

        given()
                .when()
                .get(Endpoints.GET_ALL_POSTS)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("PostResponseBody_get_all_posts.json"))
                .extract().response();
    }

    @Test(description = "Create a new Post", groups = {"positive", "post"})
    @Description("Create a new Post and validate the response")
    void createNewPost() {

        requestBody = RequestBodyCreateNewPost.withDefaults().build();

        Response response =
                given()
                        .body(requestBody)
                        .when()
                        .post(Endpoints.CREATE_NEW_POST)
                        .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("PostResponseBody_create_post.json"))
                        .body("title", equalTo(requestBody.title()),
                                "body", equalTo(requestBody.body()))
                        .extract().response();

        postId = response.getBody().jsonPath().get("id").toString();
    }

    @Test(description = "Get Post by Id", groups = {"positive", "get"}, dependsOnMethods = {"createNewPost"})
    @Description("Get Post by Id and validate the response")
    void getPostById() {

        given()
                .pathParam("id", 2)
                .when()
                .get(Endpoints.GET_POST_BY_ID)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("PostResponseBody_create_post.json"))
                .body("id", equalTo(2));
    }

    @Test(description = "Update Post by Id", groups = {"positive", "put"}, dependsOnMethods = {"createNewPost"})
    @Description("Update Post by Id and validate the response")
    void updatePostById() {

        requestBody = RequestBodyCreateNewPost.withDefaults().build();

        given()
                .pathParam("id", 2)
                .body(requestBody)
                .when()
                .put(Endpoints.UPDATE_POST)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("PostResponseBody_update_post.json"))
                .body("id", equalTo(2),
                        "title", equalTo(requestBody.title()),
                        "body", equalTo(requestBody.body()));
    }

    @Test(description = "Delete Post by Id", groups = {"positive", "delete"}, dependsOnMethods = {"updatePostById"})
    @Description("Delete Post by Id and validate the response")
    void deletePostById() {

        given()
                .pathParam("id", postId)
                .when()
                .delete(Endpoints.DELETE_POST)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test(description = "Create new post with Unicode Characters :", dataProvider = "languages", groups = {"positive", "post"})
    @Description("Create new post with Unicode Characters and validate the response")
    void createNewPostWithUnicodeCharacters(String lang) {

        requestBody = RequestBodyCreateNewPost.withDefaults(lang).build();

        given()
                .body(requestBody)
                .when()
                .post(Endpoints.CREATE_NEW_POST)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("PostResponseBody_create_post.json"))
                .body("title", equalTo(requestBody.title()),
                        "body", equalTo(requestBody.body()));
    }

    @DataProvider
    public Object[] languages() {
        return new Object[]{"ru-RU", "de-DE", "fr-FR", "zh-CN", "ar-EG"};
    }

}

