package com.ifood.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Prepare the connection reports showing the scheduled and unscheduled issues.
 *
 *
 * //TODO: This class need to be improved to be more precise in issues related to when some event stop and the other starts
 */
public class ConnectionReport {

    private List<ConnectionPeriodAssessed> connectionsSucceceded;
    private List<ConnectionPeriodAssessed> connectionFailed;
    private List<ConnectionPeriodAssessed> connectionIssuesScheduled;
    private List<ConnectionPeriodAssessed> bussinessIssuesScheduled;

    public ConnectionReport(RestaurantAvailability restaurantAvailability, RestaurantConnection restaurantConnection){
        this.connectionsSucceceded = new ArrayList<>();
        this.connectionFailed = new ArrayList<>();
        this.connectionIssuesScheduled = new ArrayList<>();
        this.bussinessIssuesScheduled = new ArrayList<>();

        List<ConnectionPeriodAssessed> connectionsFailed = restaurantConnection.getConnectionsFailed();
        List<ConnectionPeriodAssessed> connectionsSucceded = restaurantConnection.getConnectionsSucceded();

        connectionsFailed.forEach(connectionPeriodAssessed -> {
            LocalDateTime firstHealthSignal = connectionPeriodAssessed.getFirstHealthSignal();
            LocalDateTime scondHealthSignal = connectionPeriodAssessed.getSecondHealthSignal();
            if (restaurantAvailability.isAppUnavailable(firstHealthSignal) || restaurantAvailability.isAppUnavailable(scondHealthSignal)){
                return;
            }

            if(restaurantAvailability.isScheduledUnavailable(firstHealthSignal) || restaurantAvailability.isScheduledUnavailable(scondHealthSignal)){
                connectionIssuesScheduled.add(connectionPeriodAssessed);
                return;
            }

            connectionFailed.add(connectionPeriodAssessed);
        });

        connectionsSucceded.forEach(connectionPeriodAssessed -> {
            LocalDateTime firstHealthSignal = connectionPeriodAssessed.getFirstHealthSignal();
            LocalDateTime scondHealthSignal = connectionPeriodAssessed.getSecondHealthSignal();
            if (restaurantAvailability.isAppUnavailable(firstHealthSignal) || restaurantAvailability.isAppUnavailable(scondHealthSignal)){
                return;
            }

            if(restaurantAvailability.isScheduledUnavailable(firstHealthSignal) || restaurantAvailability.isScheduledUnavailable(scondHealthSignal)){
                this.bussinessIssuesScheduled.add(connectionPeriodAssessed);
                return;
            }

            connectionsSucceceded.add(connectionPeriodAssessed);
        });
    }

    public List<ConnectionPeriodAssessed> getConnectionsSucceceded() {
        return connectionsSucceceded;
    }

    public List<ConnectionPeriodAssessed> getConnectionFailed() {
        return connectionFailed;
    }

    public List<ConnectionPeriodAssessed> getConnectionIssuesScheduled() {
        return connectionIssuesScheduled;
    }

    public List<ConnectionPeriodAssessed> getBussinessIssuesScheduled() {
        return bussinessIssuesScheduled;
    }
}
