package com.ifood.database.entity;

import com.ifood.database.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ConnectionHealthSignal")
public class ConnectionHealthSignalEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private RestaurantEntity owner;

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime receivedAt;

    public ConnectionHealthSignalEntity() {
    }

    public ConnectionHealthSignalEntity(RestaurantEntity owner, LocalDateTime receivedAt) {
        this.owner = owner;
        this.receivedAt = receivedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RestaurantEntity getOwner() {
        return owner;
    }

    public void setOwner(RestaurantEntity owner) {
        this.owner = owner;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(LocalDateTime receivedAt) {
        this.receivedAt = receivedAt;
    }
}
