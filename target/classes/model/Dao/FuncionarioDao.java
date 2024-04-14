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
import model.bo.Funcionario;

/**
 *
 * @author Thiago and Gabrieli
 */
public class FuncionarioDao implements  InterfaceDao<Funcionario>{
    
    private static FuncionarioDao instance;
    protected EntityManager entityManager;
    
    public static FuncionarioDao getInstance(){
        if (instance == null) {
            instance = new FuncionarioDao();
        }
        return instance;
    }
    
    public FuncionarioDao(){
        entityManager = getEntityManager();
    }
    
    private EntityManager getEntityManager(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu_Cantina");

        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    @Override
    public void create(Funcionario objeto) {
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
    public List<Funcionario> retrieve() {      
        List<Funcionario> listaFuncionarios;
        listaFuncionarios = entityManager.createQuery("select f from Funcionario f",Funcionario.class).getResultList();
        return listaFuncionarios;
  
    }

    @Override
    public Funcionario retrieve(int parPK) {
        return entityManager.find(Funcionario.class, parPK);
    }


    public List<Funcionario> retrieve(String nomeParametro, String parString) {
        List<Funcionario> listaFuncionarios;
        listaFuncionarios = entityManager.createQuery("Select f From Funcionario f Where f." + nomeParametro + "  like "
                + ":parDescricao", Funcionario.class).setParameter("parDescricao", "%" + parString + "%").getResultList();
        return listaFuncionarios;
    }

    @Override
    public void update(Funcionario objeto) {   
        try {
            Funcionario funcionario = entityManager.find(Funcionario.class, objeto.getId());
            entityManager.getTransaction().begin();
            entityManager.merge(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        } 
    }

    @Override
    public void delete(Funcionario objeto) {
        try {
            Funcionario funcionario = entityManager.find(Funcionario.class, objeto.getId());
            entityManager.getTransaction().begin();
            entityManager.remove(objeto);
            entityManager.getTransaction().commit();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public List<Funcionario> retrieve(String parString) {
        return null;
    }
    
}
