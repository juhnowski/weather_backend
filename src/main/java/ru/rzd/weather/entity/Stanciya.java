package ru.rzd.weather.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "stanciya")
@Data
@Builder
public class Stanciya extends BaseEntity  {

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "excel_id")
    private Excel excel;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "groza_id", referencedColumnName = "id")
    private Groza groza;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kolichestvo_osadkov_id", referencedColumnName = "id")
    private KolichestvoOsadkov kolichestvoOsadkov;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "metel_pozemok_id", referencedColumnName = "id")
    private MetelPozemok metelPozemok;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "oblachnost_id", referencedColumnName = "id")
    private Oblachnost oblachnost;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "porivi_id", referencedColumnName = "id")
    private Porivi porivi;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "t_rels_id", referencedColumnName = "id")
    private TRels tRels;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "t_vozduha_id", referencedColumnName = "id")
    private TVozduha tVozduha;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vid_osadkov_id", referencedColumnName = "id")
    private VidOsadkov vidOsadkov;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sneg_visota_id", referencedColumnName = "id")
    private SnegVisota snegVisota;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ledyanoy_dozhd_id", referencedColumnName = "id")
    private LedyanoyDozhd ledyanoyDozhd;
}
