package no.hvl.dat107.dao;

import jakarta.persistence.*;
import no.hvl.dat107.entity.Ansatt;

import java.util.List;

public class AnsattDAO {
    private EntityManagerFactory emf;

    public AnsattDAO() {
        emf = Persistence.createEntityManagerFactory("dat107-innlevering-3");
    }

    public Ansatt finnAnsattMedId(int id) {
        EntityManager em = emf.createEntityManager();

        Ansatt ansatt = null;
        try {
            ansatt = em.find(Ansatt.class, id);
        } finally {
            em.close();
        }
        return ansatt;
    }

    public Ansatt finnAnsattMedBrukernavn(String brukernavn) {
        EntityManager em = emf.createEntityManager();

        String query = "select a from Ansatt a where a.brukernavn = :brukernavn";

        Ansatt ansatt = null;
        try {
            ansatt = em.createQuery(query, Ansatt.class).setParameter("brukernavn", brukernavn).getSingleResult();
        } finally {
            em.close();
        }
        return ansatt;
    }

    public void listAnsatte() {
        EntityManager em = emf.createEntityManager();

        String query = "select a from Ansatt a order by a.id";

        try {
            System.out.println("Alle ansatte:");
            for (Ansatt a : em.createQuery(query, Ansatt.class).getResultList()) {
                System.out.println("    " + a.toString());
            }
        } finally {
            em.close();
        }
    }

    public void oppdaterAnsattStilling(int id, String stilling) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Ansatt ansatt = em.find(Ansatt.class, id);
            ansatt.setStilling(stilling);
            tx.commit();
        } catch (Throwable e) {
            e.printStackTrace();
            tx.rollback();
        }
        finally {
            em.close();
        }
    }

    public void leggTilAnsatt(Ansatt ansatt) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(ansatt);
            tx.commit();
        } catch (Throwable e) {
            e.printStackTrace();
            tx.rollback();
        }
        finally {
            em.close();
        }
    }
}