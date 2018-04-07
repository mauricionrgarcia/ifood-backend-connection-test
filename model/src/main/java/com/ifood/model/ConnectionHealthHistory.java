package com.ifood.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.ifood.entity.ConnectionHistoryEntity;

import java.time.LocalDateTime;

public class ConnectionHealthHistory {
    private String code;
    
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime receivedAt;

    public ConnectionHealthHistory(ConnectionHistoryEntity healthHistory) {
        this.code = healthHistory.getCode();
        this.receivedAt = healthHistory.getReceivedAt();
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }
}
