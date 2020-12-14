package ru.rzd.weather.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseIntEntity extends BaseEntity{
    private int d1,d2,d3,d4,d5,d6,d7,d8,d9,d10;
    private int n1,n2,n3,n4,n5,n6,n7,n8,n9,n10;
}
