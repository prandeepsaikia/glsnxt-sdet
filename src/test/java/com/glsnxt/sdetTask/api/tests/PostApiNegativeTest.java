package com.glsnxt.sdetTask.api.tests;

import com.glsnxt.sdetTask.api.config.Config;
import com.glsnxt.sdetTask.api.config.Endpoints;
import com.glsnxt.sdetTask.api.config.requests.RequestBodyCreateNewPost;
import io.qameta.allure.Description;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@ActiveProfiles("qa")
public class PostApiNegativeTest extends Config {

    @BeforeClass(alwaysRun = true)
    void setConfig() {
        mockSetUp();
    }

    RequestBodyCreateNewPost requestBody;

    @Test(description = "Create new post with Invalid UserId", groups = {"negative", "post"})
    @Description("Create new post with Invalid UserId and validate the response")
    void createNewPostwithInvalidUserId() {
        requestBody = RequestBodyCreateNewPost.withDefaults().userId(-1).build();

        given()
                .body(requestBody)
                .when()
                .post(Endpoints.CREATE_NEW_POST)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("error", equalTo("Invalid userId"));
    }

    @Test(description = "Create new post without Title Field", groups = {"negative", "post"})
    @Description("Create new post without Title Field and validate the response")
    void createNewPostWithNoTitle() {
        requestBody = RequestBodyCreateNewPost.withDefaults().title(null).build();

        given()
                .body(requestBody)
                .when()
                .post(Endpoints.CREATE_NEW_POST)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("error", equalTo("Missing title"));
    }

    @Test(description = "Create new post without Body Field", groups = {"negative", "post"})
    @Description("Create new post without Body Field and validate the response")
    void createNewPostWithNoBody() {
        requestBody = RequestBodyCreateNewPost.withDefaults().body(null).build();

        given()
                .body(requestBody)
                .when()
                .post(Endpoints.CREATE_NEW_POST)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("error", equalTo("Missing body"));
    }
}
