
package model.Dao;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.bo.Bairro;

/**
 *
 * @author Thiago
 */
public class BairroDao implements InterfaceDao<Bairro> {

    private static BairroDao instance;
    protected EntityManager entityManager;

    public static BairroDao getInstance() {
        if (instance == null) {
            instance = new BairroDao();
        }
        return instance;
    }

    public BairroDao() {
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
    public void create(Bairro objeto) {
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
    public List<Bairro> retrieve() {
        List<Bairro> listaBairros;
        listaBairros = entityManager.createQuery("select b From Bairro b", Bairro.class).getResultList();
        return listaBairros;    

    }

    @Override
    public Bairro retrieve(int parPK) {
        return entityManager.find(Bairro.class, parPK);
    }

    @Override
    public List<Bairro> retrieve(String parString) {

        List<Bairro> listaBairros;
        listaBairros = entityManager.createQuery("select b From Bairro b where "
                + "b.descricao like :parDescricao", Bairro.class).setParameter("parDescricao", "%" + parString + "%").getResultList();
        return listaBairros;

    }

    @Override
    public void update(Bairro objeto) {
        try {
            Bairro bairro = entityManager.find(Bairro.class, objeto.getId());   
            entityManager.getTransaction().begin();
            entityManager.merge(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Bairro objeto) {
        try {
            Bairro bairro = entityManager.find(Bairro.class, objeto.getId());   
            entityManager.getTransaction().begin();
            entityManager.remove(objeto);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

}
