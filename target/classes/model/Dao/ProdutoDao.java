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
import model.bo.Produto;

/**
 *
 * @author Thiago
 */
public class ProdutoDao implements InterfaceDao<Produto> {

    private static ProdutoDao instance;
    protected EntityManager entityManager;

    public static ProdutoDao getInstance() {
        if (instance == null) {
            instance = new ProdutoDao();
        }
        return instance;
    }

    public ProdutoDao() {
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
    public void create(Produto objeto) {
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
    public List<Produto> retrieve() {
        List<Produto> listaProdutos;
        listaProdutos = entityManager.createQuery("select pro from produto pro", Produto.class).getResultList();
        return listaProdutos;
    }

    @Override
    public Produto retrieve(int parPK) {
        return entityManager.find(Produto.class, parPK);
    }

    @Override
    public List<Produto> retrieve(String parString) {
        return null;
    }

    public List<Produto> retrieve(String nomeParametro, String parString) {
        List<Produto> listaProduto;
        listaProduto = entityManager.createQuery("SELECT pro FROM produto pro WHERE " + nomeParametro + "  like "
                + ":parDescricao", Produto.class).setParameter("parDescricao", "%" + parString + "%").getResultList();
        return listaProduto;
    }

//    public Produto retrieveCodigoBarras(String parString) {
//        Connection conexao = ConnectionFactory.getConnection();
//        String sqlExecutar = "select * from produto where codigoBarra like ?";
//        PreparedStatement pstm = null;
//        ResultSet rst = null;
//        Produto objeto = new Produto();
//
//        try {
//            pstm = conexao.prepareStatement(sqlExecutar);
//            pstm.setString(1, parString + "%");
//            rst = pstm.executeQuery();
//
//            if (rst.next()) {
//
//                objeto.setId(rst.getInt("id"));
//                objeto.setCodigoBarra(rst.getString("codigoBarra"));
//                objeto.setDescricao(rst.getString("descricao"));
//                objeto.setPreco(rst.getFloat("preco"));
//                objeto.setStatus(rst.getString("status").charAt(0));
//
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            ConnectionFactory.closeConnection(conexao, pstm, rst);
//            return objeto;
//        }
//    }
    @Override
    public void update(Produto objeto) {
        try {
            Produto produto = entityManager.find(Produto.class, objeto.getId());
            entityManager.getTransaction().begin();
            entityManager.merge(objeto);
            entityManager.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Produto objeto) {
        try {
            Produto produto = entityManager.find(Produto.class, objeto.getId());
            entityManager.getTransaction().begin();
            entityManager.remove(objeto);
            entityManager.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

}
