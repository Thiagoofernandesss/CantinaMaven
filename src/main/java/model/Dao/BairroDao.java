/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.bo.Bairro;
import sun.security.jca.GetInstance;

/**
 *
 * @author Thiago
 */
public class BairroDao implements InterfaceDao<Bairro> {

    private static BairroDao instance;
    protected EntityManager entityManager;

    public static BairroDao getInstance() {
        if (instance == null) {
            instance = new BairroDao();
        }
        return instance;
    }

    public BairroDao() {
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
    public void create(Bairro objeto) {
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
    public List<Bairro> retrieve() {
        List<Bairro> listaBairros;
        listaBairros = entityManager.createQuery("select b From Bairro b", Bairro.class).getResultList();
        return listaBairros;    

    }

    @Override
    public Bairro retrieve(int parPK) {
        return entityManager.find(Bairro.class, parPK);
    }

    @Override
    public List<Bairro> retrieve(String parString) {

        List<Bairro> listaBairros;
        listaBairros = entityManager.createQuery("select b From Bairro b where "
                + "b.descricao like :parDescricao", Bairro.class).setParameter("parDescricao", "%" + parString + "%").getResultList();
        return listaBairros;

    }

    @Override
    public void update(Bairro objeto) {
        try {
            Bairro bairro = entityManager.find(Bairro.class, objeto);   
            entityManager.getTransaction().begin();
            entityManager.merge(bairro);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Bairro objeto) {
    }

}
