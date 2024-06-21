package org.home.cadastro.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import java.util.List;
import javax.inject.Inject;
import org.home.cadastro.model.PessoaModel;

public class PessaoDAOImpl implements PessoaDAO {

    @Inject
    private EntityManager em;

    @Override
    public void Salvar(PessoaModel p) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        p = em.merge(p);
        em.persist(p);
        tx.commit();
    }

    @Override
    public List<PessoaModel> ListarTodasPessoas() {
        Query q = em.createQuery("select p from PessoaModel p left outer join p.enderecos EnderecoModel", PessoaModel.class);
        return q.getResultList();
    }

    @Override
    public void Excluir(PessoaModel p) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        p = em.merge(p);
        em.remove(p);
        tx.commit();
    }

    @Override
    public PessoaModel GetPessoaByID(long id) {
        Query q = em.createQuery("select p from PessoaModel p where p.id = :id", PessoaModel.class);
        q.setParameter("id", id);
        
        return (PessoaModel)q.getSingleResult();
    }
}

