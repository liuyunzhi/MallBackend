package com.thoughtworks.mallbackend.api;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.thoughtworks.mallbackend.controller.request.OrderItemRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@ContextConfiguration
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public class OrderItemAPITest {

    @LocalServerPort
    private int port;

    @Test
    @DatabaseSetup("classpath:/orderitem/OrderItems.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT,
            value = "classpath:/orderitem/OrderItemsAfterAdd.xml")
    public void should_add_a_order_item_when_post() {
        RestAssured
            .given()
                .port(port)
            .when()
                .request()
                .contentType(ContentType.JSON)
                .body(new OrderItemRequest(2L, 1L, 10))
                .post("/orderItems")
            .then()
                .statusCode(201)
                .header("location", notNullValue());
    }

    @Test
    @DatabaseSetup("classpath:/orderitem/OrderItems.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT,
            value = "classpath:/orderitem/OrderItemsAfterUpdate.xml")
    public void should_update_a_order_item_when_put() {
        RestAssured
            .given()
                .port(port)
            .when()
                .request()
                .contentType(ContentType.JSON)
                .body(new OrderItemRequest(15))
                .put("/orderItems/1")
            .then()
                .statusCode(204);
    }

    @Test
    @DatabaseSetup("classpath:/orderitem/OrderItems.xml")
    public void should_delete_a_order_item_when_delete() {
        RestAssured
            .given()
                .port(port)
            .when()
                .delete("/orderItems/1")
            .then()
                .statusCode(204);
    }
}
