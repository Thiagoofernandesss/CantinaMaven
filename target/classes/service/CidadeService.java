package service;

import java.util.List;
import model.Dao.CidadeDao;
import model.bo.Cidade;

public class CidadeService {
    public static void adicionar(Cidade objeto) {
        CidadeDao.getInstance().create(objeto);
        //CidadeDao cidadeDao = new CidadeDao();
        //cidadeDao.create(objeto);
    }

    public static List<Cidade> carregar() {
        return CidadeDao.getInstance().retrieve();
    }

    public static Cidade carregar(int parPK) {
        //CidadeDao cidadeDao = new CidadeDao();
        //return cidadeDao.retrieve(parPK);
        return CidadeDao.getInstance().retrieve(parPK);
    }

    public static List<Cidade> carregar(String nomeParametro, String parString) {
        //CidadeDao cidadeDao = new CidadeDao();
        //return cidadeDao.retrieve(nomeParametro,parString);
        return CidadeDao.getInstance().retrieve(nomeParametro, parString);
    }

    public static void atualizar(Cidade objeto) {
        //CidadeDao cidadeDao = new CidadeDao();
        //cidadeDao.update(objeto);
        CidadeDao.getInstance().update(objeto);
    }

    public static void remover(Cidade objeto) {
        //CidadeDao cidadeDao = new CidadeDao();
        //cidadeDao.delete(objeto);
        CidadeDao.getInstance().delete(objeto);
    }
}
