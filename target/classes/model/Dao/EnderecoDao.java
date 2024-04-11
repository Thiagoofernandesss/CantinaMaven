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
import model.bo.Bairro;
import model.bo.Cidade;
import model.bo.Endereco;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Thiago
 */
public class EnderecoDao implements InterfaceDao<Endereco> {
    
    private static EnderecoDao instance;
    protected EntityManager entityManager;

    public static EnderecoDao getInstance() {
        if (instance == null) {
            instance = new EnderecoDao();
        }
        return instance;
    }

    public EnderecoDao() {
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
    public void create(Endereco objeto) {
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
    public List<Endereco> retrieve() {
        List<Endereco> listaEnderecos;
        listaEnderecos = entityManager.createQuery("select e from Endereco e",Endereco.class).getResultList();
        return listaEnderecos;
    }

    @Override
    public Endereco retrieve(int parPK) {
        return entityManager.find(Endereco.class, parPK);
    }

    @Override
    public List<Endereco> retrieve(String parString) {
        return null;
    }

    public List<Endereco> retrieve(String nomeParametro, String parString) {
        List<Endereco> listaEnderecos;
        listaEnderecos = entityManager.createQuery("Select e From Endereco e Where " + nomeParametro + "  like "
                + ":parDescricao", Endereco.class).setParameter("parDescricao", "%" + parString + "%").getResultList();
        return listaEnderecos;
    }
    
    

    @Override
    public void update(Endereco objeto) {
        try {
            Endereco endereco = entityManager.find(Endereco.class, objeto.getId());
            entityManager.getTransaction().begin();
            entityManager.merge(objeto);
            entityManager.getTransaction().commit();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Endereco objeto) {
        try {
            Endereco endereco = entityManager.find(Endereco.class, objeto.getId());
            entityManager.getTransaction().begin();
            entityManager.remove(objeto);
            entityManager.getTransaction().commit();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

}
