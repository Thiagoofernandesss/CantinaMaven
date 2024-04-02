/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.List;
import model.Dao.CaixaDao;
import model.bo.Caixa;

/**
 *
 * @author gabri
 */
public class CaixaService {
    public static void adicionar(Caixa objeto) {
        //CaixaDao caixaDao = new CaixaDao();
        //caixaDao.create(objeto);
        CaixaDao.getInstance().create(objeto);
    }

    public static List<Caixa> carregar() {
        //CaixaDao caixaDao = new CaixaDao();
        //return caixaDao.retrieve();
        return CaixaDao.getInstance().retrieve();
    }

    public static Caixa carregar(int parPK) {
        //CaixaDao caixaDao = new CaixaDao();
       //return caixaDao.retrieve(parPK);
       return CaixaDao.getInstance().retrieve(parPK);
    }

    public static List<Caixa> carregar(String nomeParametro, String parString) {
        //CaixaDao caixaDao = new CaixaDao();
       //return caixaDao.retrieve(nomeParametro ,parString);
       return CaixaDao.getInstance().retrieve(nomeParametro, parString);
    }
    
    public static void atualizar(Caixa objeto) {
        //CaixaDao caixaDao = new CaixaDao();
        //caixaDao.update(objeto);
        CaixaDao.getInstance().update(objeto);
    }

    public static void remover(Caixa objeto) {
        //CaixaDao caixaDao = new CaixaDao();
        //caixaDao.delete(objeto);
        CaixaDao.getInstance().delete(objeto);
    }
}
