package com.ifood.database.entity;

import javax.persistence.*;

@Entity
@Table(name = "Restaurant")
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String code;

    public RestaurantEntity() {
    }

    public RestaurantEntity(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
