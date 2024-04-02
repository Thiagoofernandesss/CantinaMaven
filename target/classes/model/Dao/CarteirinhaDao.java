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
import model.bo.Carteirinha;
import model.bo.Cliente;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Thiago and Gabrieli
 */
public class CarteirinhaDao implements InterfaceDao<Carteirinha>{
    
    private static CarteirinhaDao instance;
    protected EntityManager entityManager;

    public static CarteirinhaDao getInstance() {
        if (instance == null) {
            instance = new CarteirinhaDao();
        }
        return instance;
    }

    public CarteirinhaDao() {
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
    public void create(Carteirinha objeto) {
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
    public List<Carteirinha> retrieve() {
        List<Carteirinha> listaCarteirinhas;
        listaCarteirinhas = entityManager.createQuery("select c From Carteirinha",Carteirinha.class).getResultList();
        return listaCarteirinhas;
    }

    @Override
    public Carteirinha retrieve(int parPK) {
         return entityManager.find(Carteirinha.class, parPK);
    }
    
     @Override
    public List<Carteirinha> retrieve(String parString) {
        return null;
    }
  
    public List<Carteirinha> retrieve(String nomeParametro, String parString) {
        /*
        Connection conexao = ConnectionFactory.getConnection();
        String sqlExecutar = "select cart.*, b.* from carteirinha cart " +
        "left outer join cliente b on cart.cliente_id = b.id where cart." + nomeParametro + " like ?";
        
        PreparedStatement pstm = null;
        ResultSet rst = null;      
        List<Carteirinha> listaCarteirinha = new ArrayList<>();
        
        try {
            
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setString(1,"%"+ parString +"%");
            rst = pstm.executeQuery();
            
            while (rst.next()) {                
                Carteirinha carteirinha = new Carteirinha();
                
                carteirinha.setId(rst.getInt("cart.id"));
                carteirinha.setCodigoBarra(rst.getString("cart.codigoBarra"));
                carteirinha.setDataGeracao(rst.getString("cart.dataGeracao"));
                carteirinha.setDataCancelamento(rst.getString("cart.dataCancelamento"));
                
                Cliente cliente = new Cliente();
                cliente.setId(rst.getInt("b.id"));
                cliente.setNome(rst.getString("b.nome"));
                cliente.setCpf(rst.getString("b.cpf"));
                
                carteirinha.setCliente(cliente);
                
                listaCarteirinha.add(carteirinha);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaCarteirinha;
        }
        */
        return null;
    }

    @Override
    public void update(Carteirinha objeto) {
        try {
            Carteirinha carteirinha = entityManager.find(Carteirinha.class, objeto.getId());
            entityManager.getTransaction().begin();
            entityManager.merge(objeto);
            entityManager.getTransaction().commit();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Carteirinha objeto) {
        try {
            Carteirinha carteirinha = entityManager.find(Carteirinha.class, objeto.getId());
            entityManager.getTransaction().begin();
            entityManager.remove(objeto);
            entityManager.getTransaction().commit();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }

    }
    
}
