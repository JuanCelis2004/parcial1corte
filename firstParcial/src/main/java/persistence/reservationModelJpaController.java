/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import javax.persistence.Persistence;
import model.reservationModel;
import persistence.exceptions.NonexistentEntityException;

/**
 *
 * @author HOGAR
 */
public class reservationModelJpaController implements Serializable {

    public reservationModelJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    /*
    Se crea un constructor para poder conectarnos con la persostence.xml, en la cual previamente se tuvo que haber creado una persistence unit
     para que se pueda conectar a la base de datos y ya luego ser llamada aqui 
    */
    public reservationModelJpaController(){
        emf=Persistence.createEntityManagerFactory("parcialdbPU");
    }
    
    /*
     en adelante se encuentran los metodos que se van a utilizar para conformar un CRUD
     */
    public void create(reservationModel reservationModel) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(reservationModel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(reservationModel reservationModel) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            reservationModel = em.merge(reservationModel);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = reservationModel.getId();
                if (findreservationModel(id) == null) {
                    throw new NonexistentEntityException("The reservationModel with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            reservationModel reservationModel;
            try {
                reservationModel = em.getReference(reservationModel.class, id);
                reservationModel.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reservationModel with id " + id + " no longer exists.", enfe);
            }
            em.remove(reservationModel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<reservationModel> findreservationModelEntities() {
        return findreservationModelEntities(true, -1, -1);
    }

    public List<reservationModel> findreservationModelEntities(int maxResults, int firstResult) {
        return findreservationModelEntities(false, maxResults, firstResult);
    }

    private List<reservationModel> findreservationModelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(reservationModel.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public reservationModel findreservationModel(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(reservationModel.class, id);
        } finally {
            em.close();
        }
    }

    public int getreservationModelCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<reservationModel> rt = cq.from(reservationModel.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
