package org.example.services;

import org.example.domain.Membro;
import org.hibernate.Hibernate;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class MembroService {

    @PersistenceContext
    private EntityManager em;

    public void createMembro(Membro membro) {
        em.persist(membro);
    }

    public Membro findMembro(Long id) {
        Membro membro = em.find(Membro.class, id);

        Hibernate.initialize(membro.getHabilidades());
        Hibernate.initialize(membro.getMinisterios());

        return membro;
    }

    public void updateMembro (Membro membro) {
        //em.createQuery("update Membro m set m.nome = " + membro.getNome() + ", m.ativoEscala = " + membro.getAtivoEscala() + ", m.ultimaEscala = " + membro.getUltimaEscala() + "where membro.id = " + membro.getId());

        if(membro.getHabilidades() != null) {
            membro.getHabilidades().forEach((habilidade -> {
                if (habilidade.getId() != null) {
                    em.merge(habilidade);
                } else {
                    em.persist(habilidade);
                }
            }));
        }

        em.merge(membro);
    }

    public void deleteMembro(Long id) {
        Membro membro = findMembro(id);

        if(membro != null) {
            em.remove(membro);
        }
    }

    public List<Membro> listAllMemmbros() {
        List<Membro> membros = em.createQuery("Select m FROM Membro m", Membro.class).getResultList();

        membros.forEach(membro -> {
            Hibernate.initialize(membro.getHabilidades());
            Hibernate.initialize(membro.getMinisterios());
        });

        return membros;
    }
}
