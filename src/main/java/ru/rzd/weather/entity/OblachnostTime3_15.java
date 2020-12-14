package ru.rzd.weather.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "oblachnost_time_3_15")
@Data
@Builder
public class OblachnostTime3_15 extends BaseStringEntity {
    @OneToOne(mappedBy = "oblachnost")
    private Oblachnost oblachnost;

    private String d1,d2,d3,d4,d5,d6,d7,d8,d9,d10;
    private String n1,n2,n3,n4,n5,n6,n7,n8,n9,n10;
}
