package com.ifood;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Responsible for checking the connection health.
 */
public class RestaurantConnection {

    /**
     * Acceptable Signal Delay in minutes.
     */
    private static final long ACCEPTABLE_SIGNAL_DELAY_IN_MINUTES = 2;

    private List<ConnectionHealthSignal> connectionHealthSignals;

    public RestaurantConnection(List<ConnectionHealthSignal> connectionHealthSignals) {
        this.connectionHealthSignals = connectionHealthSignals;
    }

    /**
     * Search by connections problems that happened. It is considered the ACCEPTABLE_SIGNAL_DELAY_IN_MINUTES
     * @return Return the connections problems found.
     * @throws IllegalStateException in case the connection could not be tested, eg: there is no tries to registered or less than two.
     */
    public List<ConnectionProblems> searchForConnectionsFailures(){
        List<ConnectionProblems>  connectionProblems = new ArrayList<>();
        if (connectionHealthSignals == null || connectionHealthSignals.size() < 2) {
            throw new IllegalStateException("It was not possible to check if the connection is OK.");
        }

        Collections.sort(connectionHealthSignals);

        ConnectionHealthSignal currentHealthSignal = null;
        for (ConnectionHealthSignal nextConnectionHealthSignal : connectionHealthSignals){
            if(currentHealthSignal == null){
                currentHealthSignal = nextConnectionHealthSignal;
            } else {
                Duration differenceBetweenSignal = currentHealthSignal.getDifferenceBetweenSignal(nextConnectionHealthSignal);
                if (differenceBetweenSignal.toMinutes() >= ACCEPTABLE_SIGNAL_DELAY_IN_MINUTES){
                    connectionProblems.add(new ConnectionProblems(currentHealthSignal, nextConnectionHealthSignal));
                    currentHealthSignal = nextConnectionHealthSignal;
                }
            }
        }

        return connectionProblems;
    }

}
