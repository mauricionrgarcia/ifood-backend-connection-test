package com.ifood.database.entity;

import com.ifood.database.LocalDateTimeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="UnavailabilitySchedule")
public class UnavailabilityScheduleEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String code;

    @ManyToOne
    private RestaurantEntity restaurant;

    private String reason;

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime scheduleStart;

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime scheduleEnd;

    public UnavailabilityScheduleEntity(){}

    public UnavailabilityScheduleEntity(RestaurantEntity restaurant, String reason, LocalDateTime scheduleStart, LocalDateTime scheduleEnd) {
        this.code = UUID.randomUUID().toString();
        this.restaurant = restaurant;
        this.reason = reason;
        this.scheduleStart = scheduleStart;
        this.scheduleEnd = scheduleEnd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getScheduleStart() {
        return scheduleStart;
    }

    public void setScheduleStart(LocalDateTime scheduleStart) {
        this.scheduleStart = scheduleStart;
    }

    public LocalDateTime getScheduleEnd() {
        return scheduleEnd;
    }

    public void setScheduleEnd(LocalDateTime scheduleEnd) {
        this.scheduleEnd = scheduleEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnavailabilityScheduleEntity that = (UnavailabilityScheduleEntity) o;
        return Objects.equals(reason, that.reason) &&
                Objects.equals(scheduleStart, that.scheduleStart) &&
                Objects.equals(scheduleEnd, that.scheduleEnd);
    }

    @Override
    public int hashCode() {

        return Objects.hash(reason, scheduleStart, scheduleEnd);
    }

    public String getCode() {
        return code;
    }
}
