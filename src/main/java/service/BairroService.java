/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.Dao.BairroDao;
import model.bo.Bairro;

/**
 *
 * @author Thiago
 */
public class BairroService {

    public static void adicionar(Bairro objeto) {
        BairroDao.getInstance().create(objeto);
        //BairroDao BairroDao = new BairroDao();
        //BairroDao.create(objeto);
    }

    public static List<Bairro> carregar() {
        return BairroDao.getInstance().retrieve();
        //BairroDao BairroDao = new BairroDao();
        //return BairroDao.retrieve();
    }

    public static List<Bairro> carregar(String nomeParametro, String parString) {
        BairroDao bairroDao = new BairroDao();
        return bairroDao.retrieve(nomeParametro, parString);
    }

    public static Bairro carregar(int parPK) {
        //BairroDao BairroDao = new BairroDao();
        //return BairroDao.retrieve(parPK);
        return BairroDao.getInstance().retrieve(parPK);
    }

    public static List<Bairro> carregar(String parString) {
        //BairroDao BairroDao = new BairroDao();
        //return BairroDao.retrieve(parString);
        return BairroDao.getInstance().retrieve(parString);
    }

    public static void atualizar(Bairro objeto) {
        //BairroDao BairroDao = new BairroDao();
        //BairroDao.update(objeto);
        BairroDao.getInstance().update(objeto);
    }

    public static void remover(Bairro objeto) {
        //BairroDao BairroDao = new BairroDao();
        //BairroDao.delete(objeto);
        BairroDao.getInstance().delete(objeto);
    }

}
