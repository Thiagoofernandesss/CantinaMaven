
package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Dao.InterfaceDao;
import model.bo.ItemVenda;

public class ItemVendaDao implements InterfaceDao<ItemVenda>{

    private static ItemVendaDao instance;
    protected EntityManager entityManager;
    
    public static ItemVendaDao getInstance() {
        if (instance == null) {
            instance = new ItemVendaDao();
        }
        return instance;
    }

    public ItemVendaDao() {
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
    public void create(ItemVenda objeto) {   
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
    public List<ItemVenda> retrieve() {
        List<ItemVenda> listaObjetos;
        listaObjetos = entityManager.createQuery("select c from cliente c",ItemVenda.class).getResultList();
        return listaObjetos;
    }

    @Override
    public ItemVenda retrieve(int parPK) {
        return  entityManager.find(ItemVenda.class, parPK);
    }

    @Override
    public List<ItemVenda> retrieve(String parString) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(ItemVenda objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(ItemVenda objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List<ItemVenda> totalItemVenda(int parPK) {    
        List<ItemVenda> listaObjetos;
        listaObjetos = entityManager.createQuery("select * from itemvenda where itemvenda.venda_id=?", ItemVenda.class).getResultList();
        return listaObjetos;       
    }   
}
