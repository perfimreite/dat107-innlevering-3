package no.hvl.dat107.entity;

import jakarta.persistence.*;
import no.hvl.dat107.dao.AnsattDAO;

@Entity
@Table(schema = "dat107_innlevering_3")
public class Avdeling {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String navn;
    @OneToOne @JoinColumn(name = "sjef_id")
    private Ansatt sjef;

    public Avdeling() {}

    public Avdeling(String navn, int sjef_id) {
        this.navn = navn;
        this.sjef = new AnsattDAO().finnAnsattMedId(sjef_id);
    }

    @Override
    public String toString() {
        return "Avdeling{" +
                "id=" + id +
                ", navn='" + navn + '\'' +
                ", sjef=" + sjef +
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

    public Ansatt getSjef() {
        return sjef;
    }

    public void setSjef(Ansatt sjef) {
        this.sjef = sjef;
    }
}
