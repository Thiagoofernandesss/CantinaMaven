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
import model.bo.Cliente;
import model.bo.Endereco;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Thiago
 */
public class ClienteDao implements InterfaceDao<Cliente> {
    
    private static ClienteDao instance;
    protected EntityManager entityManager;

    public static ClienteDao getInstance() {
        if (instance == null) {
            instance = new ClienteDao();
        }
        return instance;
    }

    public ClienteDao() {
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
    public void create(Cliente objeto) {
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
    public List<Cliente> retrieve() {
        List<Cliente> listaClientes;
        listaClientes = entityManager.createQuery("select c from cliente c",Cliente.class).getResultList();
        return listaClientes;

    }

    @Override
    public Cliente retrieve(int parPK) {
        return  entityManager.find(Cliente.class, parPK);
    }

    public List<Cliente> retrieve(String nomeParametro, String parString) {
        List<Cliente> listaClientes;
        listaClientes = entityManager.createQuery("Select c From Cliente c Where " + nomeParametro + "  like "
                + ":parDescricao", Cliente.class).setParameter("parDescricao", "%" + parString + "%").getResultList();
        return listaClientes;
    }
    
    @Override
    public void update(Cliente objeto) {
        try {
            Cliente cliente = entityManager.find(Cliente.class, objeto.getId());
            entityManager.getTransaction().begin();
            entityManager.merge(objeto);
            entityManager.getTransaction().commit();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Cliente objeto) {
        try {
            Cliente cliente = entityManager.find(Cliente.class, objeto.getId());
            entityManager.getTransaction().begin();
            entityManager.remove(objeto);
            entityManager.getTransaction().commit();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }

    }

    @Override
    public List<Cliente> retrieve(String parString) {
        return null;
    }

}
