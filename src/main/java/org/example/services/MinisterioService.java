package org.example.services;

import org.example.domain.Ministerio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import org.hibernate.Hibernate;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class MinisterioService {

    @PersistenceContext
    private EntityManager em;

    public Ministerio findMinisterio(Long id) {
         Ministerio ministerio = em.find(Ministerio.class, id);

         if (ministerio != null) {
             Hibernate.initialize(ministerio.getFuncoes());
             Hibernate.initialize(ministerio.getMembros());
         }

        return ministerio;
    }

    public void createMinisterio(Ministerio ministerio) {
        if (ministerio.getFuncoes() != null) {
            ministerio.getFuncoes().forEach((funcao) -> {
                em.persist(funcao);
            });
        } else {
            ministerio.setFuncoes(new ArrayList<>());
        }

        if (ministerio.getMembros() != null) {
            ministerio.getMembros().forEach((membro) -> {
                em.persist(membro);
            });
        } else {
            ministerio.setMembros(new ArrayList<>());
        }

        em.persist(ministerio);
    }

    public void updateMinisterio (Ministerio ministerio) {
        if (ministerio.getFuncoes() != null) {
            ministerio.getFuncoes().forEach((funcao) -> {
                if (funcao.getId() != null) {
                    em.merge(funcao);
                } else {
                    em.persist(funcao);
                }
            });
        }

        if (ministerio.getMembros() != null) {
            ministerio.getMembros().forEach((membro) -> {
                if (membro.getId() != null) {
                    em.merge(membro);
                } else {
                    em.persist(membro);
                }
            });
        }

        em.merge(ministerio);
    }

    public void deleteMinisterio(Long id) {
        Ministerio ministerio = findMinisterio(id);

        if(ministerio != null) {
            em.remove(ministerio);
        }
    }

    public List<Ministerio> listAllMimnisterios() {
        List<Ministerio> ministerios = em.createQuery("Select m FROM Ministerio m").getResultList();
        ministerios.forEach(ministerio -> {
            Hibernate.initialize(ministerio.getFuncoes());
            Hibernate.initialize(ministerio.getMembros());
        });
        return ministerios;
    }
}
