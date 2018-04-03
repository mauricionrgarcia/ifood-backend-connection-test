package com.ifood;

/**
 * Problems found related to connection.
 */
public class ConnectionProblems {

    private ConnectionHealthSignal firstHealthSignal;
    private ConnectionHealthSignal secondHealthSignal;

    public ConnectionProblems(ConnectionHealthSignal firstHealthSignal, ConnectionHealthSignal secondHealthSignal) {
        this.firstHealthSignal = firstHealthSignal;
        this.secondHealthSignal = secondHealthSignal;
    }

}
