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
        listaCidades = entityManager.createQuery("select c from Cidade c", Cidade.class).getResultList();
        return listaCidades;
    }

    @Override
    public Cidade retrieve(int parPK) {
        return entityManager.find(Cidade.class, parPK);
    }

    public List<Cidade> retrieve(String nomeCampo, String valor) {
        List<Cidade> listaCidades;
        String jpql = "SELECT c FROM Cidade c WHERE c." + nomeCampo + " LIKE :parDescricao";
        listaCidades = entityManager.createQuery(jpql, Cidade.class)
                .setParameter("parDescricao", "%" + valor + "%")
                .getResultList();
        return listaCidades;
    }

    @Override
    public void update(Cidade objeto) {
        try {
            Cidade cidade = entityManager.find(Cidade.class, objeto.getId());
            entityManager.getTransaction().begin();
            entityManager.merge(objeto);
            entityManager.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }

    }

    @Override
    public void delete(Cidade objeto) {
        try {
            Cidade cidade = entityManager.find(Cidade.class, objeto.getId());
            entityManager.getTransaction().begin();
            entityManager.remove(objeto);
            entityManager.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public List<Cidade> retrieve(String parString) {
        return null;
    }
}
