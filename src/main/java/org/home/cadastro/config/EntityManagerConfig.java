package org.home.cadastro.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class EntityManagerConfig {
    private EntityManagerFactory factory;

    public EntityManagerConfig() {
        this.factory = Persistence.createEntityManagerFactory("CadastroPessoa");
    }
    
    @Produces
    @RequestScoped
    public EntityManager LoadEntityManager(){
        return factory.createEntityManager();
    }
    
    public void CloseEntityManger(@Disposes EntityManager em){
        em.close();
    }
}
