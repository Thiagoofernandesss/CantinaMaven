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
        /*

        Connection conexao = ConnectionFactory.getConnection();

        String sqlExecutar = "select cli.*, e.*, c.*, b.* from cliente cli  "
                + "left outer join endereco e on cli.endereco_id = e.id "
                + "left outer join cidade c on e.cidade_id = c.id "
                + "left outer join bairro b on e.bairro_id = b.id where cli." + nomeParametro + " like ?";

        PreparedStatement pstm = null;
        ResultSet rst = null;
        List<Cliente> listaCliente = new ArrayList<>();

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setString(1,"%"+ parString +"%");
            rst = pstm.executeQuery();
            while (rst.next()) {
                Cliente cliente = new Cliente();

                cliente.setId(rst.getInt("cli.id"));
                cliente.setNome(rst.getString("cli.nome"));
                cliente.setFone1(rst.getString("cli.fone1"));
                cliente.setFone2(rst.getString("cli.fone2"));
                cliente.setEmail(rst.getString("cli.email"));
                cliente.setStatus(rst.getString("cli.status").charAt(0));
                cliente.setComplementoEndereco(rst.getString("cli.complementoEndereco"));
                cliente.setCpf(rst.getString("cli.cpf"));
                cliente.setRg(rst.getString("cli.rg"));
                cliente.setMatricula(rst.getString("cli.matricula"));
                cliente.setDataNascimento(rst.getString("cli.dataNascimento"));

                Endereco endereco = new Endereco();
                endereco.setId(rst.getInt("e.id"));
                endereco.setCep(rst.getString("e.cep"));
                endereco.setLogradouro(rst.getString("e.logradouro"));
                endereco.setStatus(rst.getString("e.status").charAt(0));

                Cidade cidade = new Cidade();
                cidade.setId(rst.getInt("cidade_id"));
                cidade.setUf(rst.getString("c.uf"));
                cidade.setDescricao(rst.getString("c.descricao"));

                Bairro bairro = new Bairro();
                bairro.setId(rst.getInt("bairro_id"));
                bairro.setDescricao(rst.getString("b.descricao"));

                endereco.setBairro(bairro);
                endereco.setCidade(cidade);

                cliente.setEndereco(endereco);

                listaCliente.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return listaCliente;
        }
        */
        return null;

    }
    
    /*
     public Cliente retrieveCPF(String parPK) {
        Connection conexao = ConnectionFactory.getConnection();
        String sqlExecutar = "select cli.*,e.*, c.*, b.* from cliente cli  "
                + "left outer join endereco e on cli.endereco_id = e.id "
                + "left outer join cidade c on e.cidade_id = c.id "
                + "left outer join bairro b on e.bairro_id = b.id where cli.cpf = ?";

        PreparedStatement pstm = null;
        ResultSet rst = null;

        Cliente cliente = new Cliente();

        try {
            pstm = conexao.prepareStatement(sqlExecutar);
            pstm.setString(1, parPK);
            rst = pstm.executeQuery();

            while (rst.next()) {

                cliente.setId(rst.getInt("cli.id"));
                cliente.setNome(rst.getString("cli.nome"));
                cliente.setFone1(rst.getString("cli.fone1"));
                cliente.setFone2(rst.getString("cli.fone2"));
                cliente.setEmail(rst.getString("cli.email"));
                cliente.setStatus(rst.getString("cli.status").charAt(0));
                cliente.setComplementoEndereco(rst.getString("cli.complementoEndereco"));
                cliente.setCpf(rst.getString("cli.cpf"));
                cliente.setRg(rst.getString("cli.rg"));
                cliente.setMatricula(rst.getString("cli.matricula"));
                cliente.setDataNascimento(rst.getString("cli.dataNascimento"));

                Endereco endereco = new Endereco();
                endereco.setId(rst.getInt("e.id"));
                endereco.setCep(rst.getString("e.cep"));
                endereco.setLogradouro(rst.getString("e.logradouro"));
                endereco.setStatus(rst.getString("e.status").charAt(0));

                Cidade cidade = new Cidade();
                cidade.setId(rst.getInt("cidade_id"));
                cidade.setUf(rst.getString("c.uf"));
                cidade.setDescricao(rst.getString("c.descricao"));

                Bairro bairro = new Bairro();
                bairro.setId(rst.getInt("bairro_id"));
                bairro.setDescricao(rst.getString("b.descricao"));

                endereco.setBairro(bairro);
                endereco.setCidade(cidade);

                cliente.setEndereco(endereco);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(conexao, pstm, rst);
            return cliente;
        }
    }
    */

    @Override
    public void update(Cliente objeto) {
        try {
            Cliente cliente = entityManager.find(Cliente.class, objeto);
            entityManager.getTransaction().begin();
            entityManager.merge(cliente);
            entityManager.getTransaction().commit();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Cliente objeto) {

    }

    @Override
    public List<Cliente> retrieve(String parString) {
        return null;
    }

}
