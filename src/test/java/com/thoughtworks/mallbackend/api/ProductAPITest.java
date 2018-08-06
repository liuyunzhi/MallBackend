package com.thoughtworks.mallbackend.api;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.thoughtworks.mallbackend.entity.Product;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@ActiveProfiles("test")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public class ProductAPITest {

    @LocalServerPort
    private int port;

    @Test
    @DatabaseSetup("classpath:/product/products.xml")
    public void should_return_product_list_when_get() {
        RestAssured
            .given()
                .port(port)
            .when()
                .get("/products")
            .then()
                .statusCode(200)
                .body("name", contains("Sprite", "Coca Cola"));
    }

    @Test
    @DatabaseSetup("classpath:/product/products.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "classpath:/product/productsAfterAdd.xml")
    public void should_add_a_prouct_when_post() throws ParseException {
        RestAssured
            .given()
                .port(port)
            .when()
                .request()
                .contentType(ContentType.JSON)
                .body(new Product("Drinking Water",
                        2.0,
                        "bottle",
                        "water",
                        "drinking water",
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-08-05 20:20:00"),
                        "China"))
                .post("/products")
            .then()
                .statusCode(201)
                .header("location", notNullValue());
    }

    @Test
    @DatabaseSetup("classpath:/product/products.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "classpath:/product/productsAfterUpdate.xml")
    public void should_update_the_product_when_put_a_product() throws ParseException {
        RestAssured
            .given()
                .port(port)
            .when()
                .request()
                .contentType(ContentType.JSON)
                .body(new Product("Drinking Water",
                        5.0,
                        "bottle",
                        "water",
                        "drinking water",
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-08-05 20:20:00"),
                        "China"))
                .put("/products/2")
            .then()
                .statusCode(204);
    }
}
