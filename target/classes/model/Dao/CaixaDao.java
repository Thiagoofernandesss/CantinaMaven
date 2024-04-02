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
        /*
        Connection conexao = ConnectionFactory.getConnection();
        String sqlExecutar = "select caixa.*, func.* from caixa caixa "
                + "left outer join funcionario func on caixa.Funcionario_id = func.id where caixa." + nomeParametro + " like ?";
        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Caixa> listaCaixa = new ArrayList<>();
        
        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setString(1,"%"+ parString +"%");
            rst = pstm.executeQuery();
            while (rst.next()) {
                Caixa caixa = new Caixa();

                caixa.setId(rst.getInt("caixa.id"));
                caixa.setDataHoraAbertura(rst.getString("caixa.dataHoraAbertura"));
                caixa.setDataHoraFechamento(rst.getString("caixa.dataHoraFechamento"));
                caixa.setValorAbertura(rst.getFloat("caixa.valorAbertura"));
                caixa.setValorFechamento(rst.getFloat("caixa.valorFechamento"));
                caixa.setStatus(rst.getString("caixa.status").charAt(0));
                
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rst.getInt("func.id"));
                funcionario.setNome(rst.getString("func.nome"));
                funcionario.setStatus(rst.getString("func.status").charAt(0));

                caixa.setFuncionario(funcionario);

                listaCaixa.add(caixa);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaCaixa;
        }*/
        return null;
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
