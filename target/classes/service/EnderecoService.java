package service;

import java.util.List;
import model.Dao.EnderecoDao;
import model.bo.Endereco;

public class EnderecoService {
    public static void adicionar(Endereco objeto) {
        //EnderecoDao enderecoDao = new EnderecoDao();
        //enderecoDao.create(objeto);
        EnderecoDao.getInstance().create(objeto);
    }

    public static List<Endereco> carregar() {
        //EnderecoDao enderecoDao = new EnderecoDao();
        //return enderecoDao.retrieve();
        return EnderecoDao.getInstance().retrieve();
    }

    public static Endereco carregar(int parPK) {
        //EnderecoDao enderecoDao = new EnderecoDao();
        //return enderecoDao.retrieve(parPK);
        return EnderecoDao.getInstance().retrieve(parPK);
    }

    public static List<Endereco> carregar(String nomeParametro, String parString) {
        //EnderecoDao enderecoDao = new EnderecoDao();
        //return enderecoDao.retrieve(nomeParametro ,parString);
        return EnderecoDao.getInstance().retrieve(nomeParametro, parString);
    }

    public static void atualizar(Endereco objeto) {
        //EnderecoDao enderecoDao = new EnderecoDao();
        //enderecoDao.update(objeto);
        EnderecoDao.getInstance().update(objeto);
    }

    public static void remover(Endereco objeto) {
        //EnderecoDao enderecoDao = new EnderecoDao();
        //enderecoDao.delete(objeto);
        EnderecoDao.getInstance().delete(objeto);
    }
}
