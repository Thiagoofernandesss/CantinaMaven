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
import model.bo.Cidade;
import model.bo.Endereco;
import model.bo.Fornecedor;

/**
 *
 * @author Thiago and Gabrieli
 */
public class FornecedorDao implements InterfaceDao<Fornecedor> {

    private static FornecedorDao instance;
    protected EntityManager entityManager;

    public static FornecedorDao getInstance() {
        if (instance == null) {
            instance = new FornecedorDao();
        }
        return instance;
    }

    public FornecedorDao() {
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
    public void create(Fornecedor objeto) {
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
    public List<Fornecedor> retrieve() {
        List<Fornecedor> listaFornecedores;
        listaFornecedores = entityManager.createQuery("select f from Fornecedor f", Fornecedor.class).getResultList();
        return listaFornecedores;
    }

    @Override
    public Fornecedor retrieve(int parPK) {
        return entityManager.find(Fornecedor.class, parPK);
    }

    public List<Fornecedor> retrieve(String nomeParametro, String parString) {
        List<Fornecedor> listaFornecedores;
        listaFornecedores = entityManager.createQuery("Select f from Fornecedor f Where f." + nomeParametro + "  like "
                + ":parDescricao", Fornecedor.class).setParameter("parDescricao", "%" + parString + "%").getResultList();
        return listaFornecedores;
    }

    @Override
    public void update(Fornecedor objeto) {
        try {
            Fornecedor fornecedor = entityManager.find(Fornecedor.class, objeto.getId());
            entityManager.getTransaction().begin();
            entityManager.merge(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Fornecedor objeto) {
        try {
            Fornecedor fornecedor = entityManager.find(Fornecedor.class, objeto.getId());
            entityManager.getTransaction().begin();
            entityManager.remove(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public List<Fornecedor> retrieve(String parString) {
        return null;
    }

}
