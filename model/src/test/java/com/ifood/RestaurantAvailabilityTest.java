package com.ifood;

import org.junit.Test;

import java.time.*;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RestaurantAvailabilityTest {

    private static ZoneOffset BRAZIL_ZONEOFFSET = ZoneOffset.of("-3");

    @Test
    public void testIfOnlineWithoutScheduledUnavailability(){
        List<UnavailabilitySchedule> unavailabilitySchedule = List.of(
                new UnavailabilitySchedule(LocalDateTime.now().minusHours(2), LocalDateTime.now().minusHours(1)), //
                new UnavailabilitySchedule(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2))
        );
        RestaurantAvailability restaurantAvailability = new RestaurantAvailability(unavailabilitySchedule);
        restaurantAvailability.setClock(Clock.fixed(getTodayAvailableAppTime(), ZoneId.systemDefault()));
        assertTrue(restaurantAvailability.isOnline());
    }

    @Test
    public void testIfOfflineWithScheduledUnavailability(){
        List<UnavailabilitySchedule> unavailabilitySchedule = List.of(
                new UnavailabilitySchedule(LocalDateTime.now().minusHours(1), LocalDateTime.now().plusHours(1)), //
                new UnavailabilitySchedule(LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(5))
        );
        RestaurantAvailability restaurantAvailability = new RestaurantAvailability(unavailabilitySchedule);
        restaurantAvailability.setClock(Clock.fixed(getTodayAvailableAppTime(), ZoneId.systemDefault()));
        assertFalse(restaurantAvailability.isOnline());
    }

    @Test
    public void testIfOfflineDueToAppClosed(){
        RestaurantAvailability restaurantAvailability = new RestaurantAvailability();
        restaurantAvailability.setClock(Clock.fixed(getTodayUnavailableAppTime(), ZoneId.systemDefault()));
        assertFalse(restaurantAvailability.isOnline());
    }

    @Test
    public void testIfOnlineWhileAppIsOpen(){
        RestaurantAvailability restaurantAvailability = new RestaurantAvailability();
        restaurantAvailability.setClock(Clock.fixed(getTodayAvailableAppTime(), ZoneId.systemDefault()));
        assertTrue(restaurantAvailability.isOnline());
    }

    private Instant getTodayAvailableAppTime(){
        int year = LocalDateTime.now().getYear();
        int month = LocalDateTime.now().getMonthValue();
        int day = LocalDateTime.now().getDayOfMonth();
        return LocalDateTime.of(year, month, day, 10,1).toInstant(BRAZIL_ZONEOFFSET);
    }


    private Instant getTodayUnavailableAppTime(){
        int year = LocalDateTime.now().getYear();
        int month = LocalDateTime.now().getMonthValue();
        int day = LocalDateTime.now().getDayOfMonth();
        return LocalDateTime.of(year, month, day, 9,1).toInstant(BRAZIL_ZONEOFFSET);
    }
}