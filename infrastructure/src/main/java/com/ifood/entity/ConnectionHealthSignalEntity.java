package com.ifood.entity;

import java.time.LocalDateTime;

public class ConnectionHealthSignalEntity {

    private String code;
    private LocalDateTime receivedAt;

    public ConnectionHealthSignalEntity(String code, LocalDateTime receivedAt) {
        this.code = code;
        this.receivedAt = receivedAt;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }
}
