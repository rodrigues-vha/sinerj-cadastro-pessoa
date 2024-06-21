package org.home.cadastro.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import javax.inject.Inject;
import org.home.cadastro.model.EnderecoModel;

public class EnderecoDAOImpl implements EnderecoDAO {

    @Inject
    private EntityManager em;
    
    @Override
    public void Salvar(EnderecoModel end) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        end = em.merge(end);
        em.persist(end);
        tx.commit();
    }

    @Override
    public void Excluir(EnderecoModel end) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        end = em.merge(end);
        em.remove(end);
        tx.commit();
    }
}
