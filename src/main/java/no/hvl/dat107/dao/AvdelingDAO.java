package no.hvl.dat107.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Avdeling;

public class AvdelingDAO {
    private EntityManagerFactory emf;

    public AvdelingDAO() {
        emf = Persistence.createEntityManagerFactory("dat107-innlevering-3");
    }

    public Avdeling finnAvdelingMedId(int id) {
        EntityManager em = emf.createEntityManager();

        Avdeling avdeling = null;
        try {
            avdeling = em.find(Avdeling.class, id);
        } finally {
            em.close();
        }
        return avdeling;
    }

    public void leggTilAvdeling(Avdeling avdeling) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        AnsattDAO ansattDAO = new AnsattDAO();

        try {
            tx.begin();

            Ansatt sjef = em.find(Ansatt.class, avdeling.getSjef().getId());
            if (sjef == null) {
                return;
            }

            em.persist(avdeling);
            sjef.setAvdeling(avdeling);
            em.merge(sjef);

            tx.commit();
        } catch (Throwable e) {
            if (tx != null && tx.isActive()) {
                e.printStackTrace();
                tx.rollback();
            }
        }
        finally {
            em.close();
        }
    }
}
