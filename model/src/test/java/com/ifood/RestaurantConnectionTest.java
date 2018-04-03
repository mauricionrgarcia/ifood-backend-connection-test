package com.ifood;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RestaurantConnectionTest {

    @Test
    public void testConnectionWithoutFailure(){
        List<ConnectionHealthSignal> connectionHealthSignals = new ArrayList<>();
        connectionHealthSignals.add(new ConnectionHealthSignal(LocalDateTime.now().minusMinutes(2)));
        connectionHealthSignals.add(new ConnectionHealthSignal(LocalDateTime.now().minusMinutes(1)));

        RestaurantConnection restaurantConnection = new RestaurantConnection(connectionHealthSignals);
        List<ConnectionProblems> connectionProblems = restaurantConnection.searchForConnectionsFailures();

        assertEquals(0, connectionProblems.size());
    }

    @Test
    public void testConnectionWithFailure(){
        List<ConnectionHealthSignal> connectionHealthSignals = new ArrayList<>();
        connectionHealthSignals.add(new ConnectionHealthSignal(LocalDateTime.now().plusMinutes(5)));
        connectionHealthSignals.add(new ConnectionHealthSignal(LocalDateTime.now().plusMinutes(10)));
        connectionHealthSignals.add(new ConnectionHealthSignal(LocalDateTime.now().plusMinutes(15)));
        connectionHealthSignals.add(new ConnectionHealthSignal(LocalDateTime.now().plusMinutes(20)));
        connectionHealthSignals.add(new ConnectionHealthSignal(LocalDateTime.now().plusMinutes(25)));

        RestaurantConnection restaurantConnection = new RestaurantConnection(connectionHealthSignals);
        List<ConnectionProblems> connectionProblems = restaurantConnection.searchForConnectionsFailures();

        assertEquals(4, connectionProblems.size());
    }

    @Test
    public void testConnectionWithManyFailures(){
        List<ConnectionHealthSignal> connectionHealthSignals = new ArrayList<>();
        connectionHealthSignals.add(new ConnectionHealthSignal(LocalDateTime.now().minusMinutes(2)));
        connectionHealthSignals.add(new ConnectionHealthSignal(LocalDateTime.now().plusSeconds(1)));

        RestaurantConnection restaurantConnection = new RestaurantConnection(connectionHealthSignals);
        List<ConnectionProblems> connectionProblems = restaurantConnection.searchForConnectionsFailures();

        assertEquals(1, connectionProblems.size());
    }


    @Test
    public void testConnectionWithFailureInALongPeriod(){
        List<ConnectionHealthSignal> connectionHealthSignals = new ArrayList<>();
        connectionHealthSignals.add(new ConnectionHealthSignal(LocalDateTime.now().minusMinutes(2)));
        connectionHealthSignals.add(new ConnectionHealthSignal(LocalDateTime.now().plusHours(2)));

        RestaurantConnection restaurantConnection = new RestaurantConnection(connectionHealthSignals);
        List<ConnectionProblems> connectionProblems = restaurantConnection.searchForConnectionsFailures();

        assertEquals(1, connectionProblems.size());
    }

    @Test
    public void testConnectionWithOneFailure(){
        List<ConnectionHealthSignal> connectionHealthSignals = new ArrayList<>();
        connectionHealthSignals.add(new ConnectionHealthSignal(LocalDateTime.now().minusMinutes(2)));
        connectionHealthSignals.add(new ConnectionHealthSignal(LocalDateTime.now().plusSeconds(1)));
        connectionHealthSignals.add(new ConnectionHealthSignal(LocalDateTime.now().plusSeconds(10)));
        connectionHealthSignals.add(new ConnectionHealthSignal(LocalDateTime.now().plusSeconds(30)));
        connectionHealthSignals.add(new ConnectionHealthSignal(LocalDateTime.now().plusMinutes(1)));

        RestaurantConnection restaurantConnection = new RestaurantConnection(connectionHealthSignals);
        List<ConnectionProblems> connectionProblems = restaurantConnection.searchForConnectionsFailures();

        assertEquals(1, connectionProblems.size());
    }

    @Test(expected = IllegalStateException.class)
    public void testConnectionOnlyOneConnection(){
        List<ConnectionHealthSignal> connectionHealthSignals = new ArrayList<>();
        connectionHealthSignals.add(new ConnectionHealthSignal(LocalDateTime.now().minusMinutes(2)));

        RestaurantConnection restaurantConnection = new RestaurantConnection(connectionHealthSignals);
        restaurantConnection.searchForConnectionsFailures();
    }

}