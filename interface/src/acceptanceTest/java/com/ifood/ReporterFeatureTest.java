package com.ifood;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ReporterFeatureTest {

    @Test
    public void shouldFetchTheConnectionReport(){
        String restaurantCode = "restaurant1";

        sendHealthSignal(restaurantCode);
        waitSignal();
        sendHealthSignal(restaurantCode);
        waitSignal();
        sendHealthSignal(restaurantCode);
        waitSignal();
        sendHealthSignal(restaurantCode);

        resquestReport(restaurantCode, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1));
    }

    private void waitSignal() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendHealthSignal(String restaurantCode) {
        when()
                .post("/connection/health/{restaurant_code}", restaurantCode)
        .then()
                .statusCode(200);
    }

    private void resquestReport(String restaurantCode, LocalDateTime reportStart, LocalDateTime reportEnd) {
        when()
                .get("/connection/report/{restaurant_code}/{reportStart}/{reportEnd}",
                        restaurantCode,
                        new DateFormatter().format(reportStart),
                        new DateFormatter().format(reportEnd))
        .then()
                .statusCode(200)
                .body("connectionsSucceceded.size()", is(3));
    }

}
