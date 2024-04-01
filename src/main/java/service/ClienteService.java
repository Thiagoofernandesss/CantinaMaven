package service;

import java.util.List;
import model.Dao.ClienteDao;
import model.bo.Cliente;

public class ClienteService {
    public static void adicionar(Cliente objeto) {
        //ClienteDao clienteDao = new ClienteDao();
        //clienteDao.create(objeto);
        ClienteDao.getInstance().create(objeto);
    }

    public static List<Cliente> carregar() {
        //ClienteDao clienteDao = new ClienteDao();
        //return clienteDao.retrieve();
        return ClienteDao.getInstance().retrieve();
    }

    public static Cliente carregar(int parPK) {
        //ClienteDao clienteDao = new ClienteDao();
        //return clienteDao.retrieve(parPK);
        return ClienteDao.getInstance().retrieve(parPK);
    }

    public static List<Cliente> carregar(String nomeParametro, String parString) {
        //ClienteDao clienteDao = new ClienteDao();
        //return clienteDao.retrieve(nomeParametro ,parString);
        return ClienteDao.getInstance().retrieve(nomeParametro, parString);
    }
    /*
    
    public static Cliente carregarCPF(String parPK) {
        //ClienteDao clienteDao = new ClienteDao();
        //return clienteDao.retrieveCPF(parPK);
        return ClienteDao.getInstance().retrieveCPF(parPK);
    }
    
    public static List<Cliente> carregarPorNomeCliente(String nomeParametro, String parString) {
        //ClienteDao enderecoDao = new ClienteDao();
        //return enderecoDao.retrieve(nomeParametro ,parString);
        return ClienteDao.getInstance().retrieve(nomeParametro, parString);
    }
    */

    public static void atualizar(Cliente objeto) {
        //ClienteDao clienteDao = new ClienteDao();
        //clienteDao.update(objeto);
        ClienteDao.getInstance().update(objeto);
    }

    public static void remover(Cliente objeto) {
        //ClienteDao clienteDao = new ClienteDao();
        //clienteDao.delete(objeto);
        ClienteDao.getInstance().delete(objeto);
    }
}
