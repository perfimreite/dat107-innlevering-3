package no.hvl.dat107.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import no.hvl.dat107.entity.ProsjektDeltagelse;

public class ProsjektDeltagelseDAO {
    private EntityManagerFactory emf;

    public ProsjektDeltagelseDAO() {
        emf = Persistence.createEntityManagerFactory("dat107-innlevering-3");
    }

    public void leggTilProsjektDeltagelse(ProsjektDeltagelse prosjektDeltagelse) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(prosjektDeltagelse);
            tx.commit();
        } catch (Throwable e) {
            e.printStackTrace();
            tx.rollback();
        }
        finally {
            em.close();
        }
    }

    public void leggTilTimer(ProsjektDeltagelse prosjektDeltagelse, int timer) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            prosjektDeltagelse.setTimer(prosjektDeltagelse.getTimer() + timer);
            em.merge(prosjektDeltagelse);
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
