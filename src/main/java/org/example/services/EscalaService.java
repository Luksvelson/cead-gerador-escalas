package org.example.services;

import org.example.domain.Escala;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

public class EscalaService {

    @PersistenceContext
    private EntityManager em;

    public Escala findEscala(Long id) {
        Escala escala = em.find(Escala.class, id);

        if (escala != null) {
            Hibernate.initialize(escala.getPosicoes());
        }

        return escala;
    }

    public void createEscala(Escala escala) {
        em.persist(escala);
    }

    public void updateEscala (Escala escala) {
        if (escala.getPosicoes() != null) {
            em.persist(escala.getPosicoes());
        } else {
            em.merge(escala.getPosicoes());
        }

        em.merge(escala);
    }

    public void deleteEscala(Long id) {
        Escala escala = findEscala(id);

        if(escala != null) {
            em.remove(escala);
        }
    }
}
