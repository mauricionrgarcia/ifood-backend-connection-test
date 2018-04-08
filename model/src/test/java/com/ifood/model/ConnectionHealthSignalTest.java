package com.ifood.model;

import com.ifood.domain.ConnectionHealthSignal;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ConnectionHealthSignalTest {

    @Test
    public void testDifferenceBetweenSignals() {
        ConnectionHealthSignal connectionHealthSignal = new ConnectionHealthSignal(LocalDateTime.now());
        assertEquals(connectionHealthSignal.getDifferenceBetweenSignal(new ConnectionHealthSignal(LocalDateTime.now().plusMinutes(1))).toMinutes(), 1);
    }

}