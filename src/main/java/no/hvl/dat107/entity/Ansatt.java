package no.hvl.dat107.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import no.hvl.dat107.dao.AvdelingDAO;

@Entity
@Table(schema = "dat107_innlevering_3")
public class Ansatt {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brukernavn;
    private String fornavn;
    private String etternavn;
    private LocalDate ansettelsedato;
    private String stilling;
    private int manedslonn;
    @OneToOne @JoinColumn(name = "avdeling_id")
    private Avdeling avdeling;

    public Ansatt() {}

    public Ansatt(String brukernavn, String fornavn, String etternavn, String stilling, int manedslonn, int avdelingId) {
        this.brukernavn = brukernavn;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.ansettelsedato = LocalDate.now();
        this.stilling = stilling;
        this.manedslonn = manedslonn;
        this.avdeling = new AvdelingDAO().finnAvdelingMedId(avdelingId);
    }

    @Override
    public String toString() {
        return "Ansatt{" +
                "id=" + id +
                ", brukernavn='" + brukernavn + '\'' +
                ", fornavn='" + fornavn + '\'' +
                ", etternavn='" + etternavn + '\'' +
                ", ansettelsedato=" + ansettelsedato +
                ", stilling='" + stilling + '\'' +
                ", manedslonn=" + manedslonn +
                ", avdeling=" + avdeling +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public LocalDate getAnsettesesdato() {
        return ansettelsedato;
    }

    public void setAnsettelsedato(LocalDate ansettelsesdato) {
        this.ansettelsedato = ansettelsesdato;
    }

    public String getStilling() {
        return stilling;
    }

    public void setStilling(String stilling) {
        this.stilling = stilling;
    }

    public int getManedslonn() {
        return manedslonn;
    }

    public void setManedslonn(int manedslonn) {
        this.manedslonn = manedslonn;
    }

    public Avdeling getAvdeling() {
        return avdeling;
    }

    public void setAvdeling(Avdeling avdeling) {
        this.avdeling = avdeling;
    }
}
