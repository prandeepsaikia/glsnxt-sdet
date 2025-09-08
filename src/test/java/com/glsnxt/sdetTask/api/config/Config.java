package com.glsnxt.sdetTask.api.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;


@Component
public class Config extends AbstractTestNGSpringContextTests {

    @Value("${api.baseURL}")
    private String baseURL;

    @Value("${api.postsBasePath}")
    private String postsBasePath;

    @Value("${api.mock.baseURL}")
    private String mockbaseURL;

    @Value("${api.mock.port}")
    private int mockPort;

    @Value("${api.mock.mappingsFilesDir}")
    String mappingsFilesDir;

    WireMockServer mockServer;


    public void postsSetUp() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseURL)
                .setBasePath(postsBasePath)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .build();
    }

    public void mockSetUp() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(mockbaseURL)
                .setBasePath(postsBasePath)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .build();

        mockServer = new WireMockServer(
                WireMockConfiguration.options()
                        .port(mockPort)
                        .usingFilesUnderDirectory(mappingsFilesDir)
        );
        mockServer.start();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        RestAssured.reset();
        if (mockServer != null && mockServer.isRunning()) {
            mockServer.stop();
        }
    }
}
