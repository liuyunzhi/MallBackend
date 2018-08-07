package com.thoughtworks.mallbackend.api;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.thoughtworks.mallbackend.controller.request.OrderItemRequest;
import com.thoughtworks.mallbackend.controller.request.OrderRequest;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
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
public class OrderAPITest {

    @LocalServerPort
    private int port;

    @Test
    @DatabaseSetup("classpath:/order/OrderItems.xml")
    @DatabaseSetup("classpath:/order/SimpleOrder.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT,
            value = "classpath:/order/OrderItemsAfterAdd.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT,
            value = "classpath:/order/ExceptOrder.xml")
    public void should_add_a_order_when_post() {
        List<OrderItemRequest> orderItemRequests = new ArrayList<>();
        orderItemRequests.add(new OrderItemRequest(2L, 10));
        RestAssured
            .given()
                .port(port)
            .when()
                .request()
                .contentType(ContentType.JSON)
                .body(new OrderRequest(orderItemRequests))
                .post("/orders")
            .then()
                .statusCode(201)
                .header("location", notNullValue());
    }

    @Test
    @DatabaseSetup("classpath:/order/Orders.xml")
    @DatabaseSetup("classpath:/product/Products.xml")
    @DatabaseSetup("classpath:/orderitem/OrderItems.xml")
    public void should_return_a_order_given_order_id() {
        RestAssured
            .given()
                .port(port)
            .when()
                .get("/orders/1")
            .then()
                .statusCode(200)
                .body("totalPrice",is(12.5f));
    }
}
