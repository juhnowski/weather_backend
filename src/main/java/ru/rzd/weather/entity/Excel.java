package ru.rzd.weather.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "excel")
@Data
@Builder
public class Excel extends BaseEntity{

    private Date date;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    private List<Stanciya> stations;

}
