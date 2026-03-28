package no.hvl.dat107.entity;

import jakarta.persistence.*;

@Entity
@Table(schema = "dat107_innlevering_3", name = "prosjekt_deltagelse")
@IdClass(ProsjektDeltagelsePK.class)
public class ProsjektDeltagelse {
    @Id @ManyToOne @JoinColumn(name="ansatt_id")
    private Ansatt ansatt;
    @Id @ManyToOne @JoinColumn(name="prosjekt_id")
    private Prosjekt prosjekt;
    private String rolle;
    private int timer;

    public ProsjektDeltagelse() {}

    public ProsjektDeltagelse(Prosjekt prosjekt, Ansatt ansatt, String rolle) {
        this.prosjekt = prosjekt;
        this.ansatt = ansatt;
        this.rolle = rolle;
        this.timer = 0;
        prosjekt.leggTilProsjektDeltagelse(this);
        ansatt.leggTilProsjektDeltagelse(this);
    }

    @Override
    public String toString() {
        return "ProsjektDeltagelse{" +
                "ansatt=" + ansatt +
                ", prosjektId=" + prosjekt.getId() +
                ", rolle='" + rolle + '\'' +
                ", timer=" + timer +
                '}';
    }

    public Ansatt getAnsatt() {
        return ansatt;
    }

    public void setAnsatt(Ansatt ansatt) {
        this.ansatt = ansatt;
    }

    public Prosjekt getProsjekt() {
        return prosjekt;
    }

    public void setProsjekt(Prosjekt prosjekt) {
        this.prosjekt = prosjekt;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
}
