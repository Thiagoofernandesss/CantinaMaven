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
        /*
        Connection conexao = ConnectionFactory.getConnection();
        String sqlExecutar = "SELECT endereco.id, "
                + "endereco.cep, "
                + "endereco.logradouro, "
                + "endereco.cidade_id, "
                + "endereco.bairro_id, "
                + "endereco.status, "
                + "bairro.descricao , "
                + "cidade.descricao , "
                + "cidade.uf "
                + "FROM endereco "
                + " LEFT OUTER JOIN BAIRRO ON BAIRRO.id = ENDERECO.bairro_id "
                + " LEFT OUTER JOIN CIDADE ON CIDADE.id = ENDERECO.Cidade_id "
                + " WHERE " + nomeParametro + " like ?";

        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Endereco> listaEndereco = new ArrayList<>();

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setString(1, parString + "%");
            rst = pstm.executeQuery();
            while (rst.next()) {
                Endereco endereco = new Endereco();

                endereco.setId(rst.getInt("id"));
                endereco.setLogradouro(rst.getString("logradouro"));
                endereco.setStatus(rst.getString("status").charAt(0));
                //Utilizei o String.CharAt(0) para transformar a 
                //String de retorno em char
                endereco.setCep(rst.getString("cep"));

                Bairro bairro = new Bairro();
                bairro.setId(rst.getInt("Bairro_id"));
                bairro.setDescricao(rst.getString("bairro.descricao"));
                endereco.setBairro(bairro);

                Cidade cidade = new Cidade();
                cidade.setId(rst.getInt("Cidade_id"));
                cidade.setDescricao(rst.getString("cidade.descricao"));
                cidade.setUf(rst.getString("uf"));
                endereco.setCidade(cidade);

                listaEndereco.add(endereco);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaEndereco;
        }
        */
        return null;
    }
    
    

    @Override
    public void update(Endereco objeto) {
        try {
            Endereco endereco = entityManager.find(Endereco.class, objeto);
            entityManager.getTransaction().begin();
            entityManager.merge(endereco);
            entityManager.getTransaction().commit();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
    
    /*
    public Endereco retrieveCEP(String parPK) {
        Connection conexao = ConnectionFactory.getConnection();
        String sqlExecutar = "SELECT endereco.id, "
                + "endereco.cep, "
                + "endereco.logradouro, "
                + "endereco.cidade_id, "
                + "endereco.bairro_id, "
                + "endereco.status, "
                + "bairro.descricao , "
                + "cidade.descricao , "
                + "cidade.uf "
                + "FROM endereco "
                + " LEFT OUTER JOIN bairro ON bairro.id = endereco.bairro_id "
                + " LEFT OUTER JOIN cidade ON cidade.id = endereco.cidade_id "
                + " WHERE endereco.cep = ? ";

        PreparedStatement pstm = null;
        ResultSet rst = null;
        Endereco endereco = new Endereco();
        //criei o objeto endereco fora do bloco protegido
        //para que seu escopo permita carregá-lo como retorno do método

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setString(1, parPK);
            rst = pstm.executeQuery();

            while (rst.next()) {

                endereco.setId(rst.getInt("id"));
                endereco.setLogradouro(rst.getString("logradouro"));
                endereco.setStatus(rst.getString("status").charAt(0));
                //Utilizei o String.CharAt(0) para transformar a 
                //String de retorno em char
                endereco.setCep(rst.getString("cep"));

                Bairro bairro = new Bairro();
                bairro.setId(rst.getInt("Bairro_id"));
                bairro.setDescricao(rst.getString("bairro.descricao"));
                endereco.setBairro(bairro);

                Cidade cidade = new Cidade();
                cidade.setId(rst.getInt("Cidade_id"));
                cidade.setDescricao(rst.getString("cidade.descricao"));
                cidade.setUf(rst.getString("uf"));
                endereco.setCidade(cidade);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return endereco;
        }
    }
    */

    @Override
    public void delete(Endereco objeto) {
    }

}
