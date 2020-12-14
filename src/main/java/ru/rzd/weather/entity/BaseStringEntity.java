package ru.rzd.weather.entity;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class BaseStringEntity extends BaseEntity {
    private String d1,d2,d3,d4,d5,d6,d7,d8,d9,d10;
    private String n1,n2,n3,n4,n5,n6,n7,n8,n9,n10;
}
