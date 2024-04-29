/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bo.Caixa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author gabri
 */
public class CaixaDao implements InterfaceDao<Caixa>{
    
    private static CaixaDao instance;
    protected EntityManager entityManager;

    public static CaixaDao getInstance() {
        if (instance == null) {
            instance = new CaixaDao();
        }
        return instance;
    }

    public CaixaDao() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu_Cantina");

        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    @Override
    public void create(Caixa objeto) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(objeto);
            entityManager.getTransaction().commit();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        
    }

    @Override
    public List<Caixa> retrieve() {
        List<Caixa> listaCaixas;
        listaCaixas = entityManager.createQuery("select c from Caixa c",Caixa.class).getResultList();
        return listaCaixas;
       
    }

    @Override
    public Caixa retrieve(int parPK) {
        return entityManager.find(Caixa.class, parPK);
    }
    
    public List<Caixa> retrieve(String nomeParametro, String parString) {
        List<Caixa> listaCaixas;
        listaCaixas = entityManager.createQuery("Select c From Caixa c Where c." + nomeParametro + "  like "
                + ":parDescricao", Caixa.class).setParameter("parDescricao", "%" + parString + "%").getResultList();
        return listaCaixas;
    }
    

    @Override
    public void update(Caixa objeto) {
        try {
            Caixa caixa = entityManager.find(Caixa.class, objeto.getId());
            entityManager.getTransaction().begin();
            entityManager.merge(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Caixa objeto) {
        try {
            Caixa caixa = entityManager.find(Caixa.class, objeto.getId());
            entityManager.getTransaction().begin();
            entityManager.remove(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
    
    @Override
    public List<Caixa> retrieve(String parString) {
        return null;
    }
    
}
