package ru.rzd.weather.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "oblachnost")
@Data
@Builder
public class Oblachnost extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "oblachnost_time_9_21_id", referencedColumnName = "id")
    private OblachnostTime9_21 oblachnostTime9_21;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "oblachnost_time_3_15_id", referencedColumnName = "id")
    private OblachnostTime3_15 oblachnostTime3_15;

    @OneToOne(mappedBy = "stanciya")
    private Stanciya stanciya;

}
