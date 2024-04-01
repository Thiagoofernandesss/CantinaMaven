package model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.bo.Cidade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import sun.security.jca.GetInstance;


public class CidadeDao implements InterfaceDao<Cidade> {
    
    private static CidadeDao instance;
    protected EntityManager entityManager;

    public static CidadeDao getInstance() {
        if (instance == null) {
            instance = new CidadeDao();
        }
        return instance;
    }

    public CidadeDao() {
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
    public void create(Cidade objeto) {
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
    public List<Cidade> retrieve() {
        List<Cidade> listaCidades;
        listaCidades = entityManager.createQuery("select c from Cidade c",Cidade.class).getResultList();
        return listaCidades;
    }

    @Override
    public Cidade retrieve(int parPK) {
        return entityManager.find(Cidade.class,parPK);
    }

    
    public List<Cidade> retrieve(String nomeParametro, String parString) {
        /*
        Connection conexao = ConnectionFactory.getConnection();
        String sqlExecutar = " SELECT cidade.id, "
                + " cidade.descricao, "
                + " cidade.uf "
                + " FROM cidade "
                + " WHERE " + nomeParametro + " like ?";

        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Cidade> listaCidade = new ArrayList<>();

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setString(1, "%" + parString + "%");
            rst = pstm.executeQuery();
            while (rst.next()) {
                Cidade cidade = new Cidade();
                cidade.setId(rst.getInt("id"));
                cidade.setDescricao(rst.getString("descricao"));
                cidade.setUf(rst.getString("uf"));
                listaCidade.add(cidade);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaCidade;
        }
        */
        return null;
    }
    

    @Override
    public void update(Cidade objeto) {
        try {
            Cidade cidade = entityManager.find(Cidade.class, objeto);
            entityManager.getTransaction().begin();
            entityManager.merge(cidade);
            entityManager.getTransaction().commit();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        
    }

    @Override
    public void delete(Cidade objeto) {
        // Implement the delete method if needed
    }

    @Override
    public List<Cidade> retrieve(String parString) {
        return null;
    }
}
