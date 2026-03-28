package no.hvl.dat107.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(schema = "dat107_innlevering_3")
public class Prosjekt {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String navn;
    private String beskrivelse;
    @OneToMany(mappedBy="prosjekt")
    private List<ProsjektDeltagelse> deltagelser;

    public Prosjekt() {}

    public Prosjekt(String navn, String beskrivelse, List<ProsjektDeltagelse> deltagelser) {
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        this.deltagelser = deltagelser;
    }

    public void leggTilProsjektDeltagelse(ProsjektDeltagelse prosjektdeltagelse) {
        deltagelser.add(prosjektdeltagelse);
    }

    public void fjernProsjektDeltagelse(ProsjektDeltagelse prosjektdeltagelse) {
        deltagelser.remove(prosjektdeltagelse);
    }

    @Override
    public String toString() {
        return "Prosjekt{" +
                "id=" + id +
                ", navn='" + navn + '\'' +
                ", beskrivelse='" + beskrivelse + '\'' +
                ", deltagelser=" + deltagelser +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public List<ProsjektDeltagelse> getDeltagelser() {
        return deltagelser;
    }

    public void setDeltagelser(List<ProsjektDeltagelse> deltagelser) {
        this.deltagelser = deltagelser;
    }
}
