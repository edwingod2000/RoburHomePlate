/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmDocumenten;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Gebruiker
 */
@Stateless
public class BsmDocumentenFacade extends AbstractFacade<BsmDocumenten> implements BsmDocumentenFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    public BsmDocumentenFacade() {
        super(BsmDocumenten.class);
    }
    
    public void create(BsmDocumenten bsmDocumenten) {
        em.persist(bsmDocumenten);
    }

    public void edit(BsmDocumenten bsmDocumenten) {
        em.merge(bsmDocumenten);
    }

    public void remove(BsmDocumenten bsmDocumenten) {
        em.remove(em.merge(bsmDocumenten));
    }

    public BsmDocumenten find(Object id) {
        return em.find(BsmDocumenten.class, id);
    }

    public BsmDocumenten findByName(String name) {
        Query q = em.createNamedQuery("BsmDocumenten.findByName");
        q.setParameter("name", name);
        BsmDocumenten result = null;
        try {
            result = (BsmDocumenten)q.getSingleResult();
        } catch(Exception e) {
            result = null;
        }
        return result;
    }

    public List<BsmDocumenten> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmDocumenten.class));
        return em.createQuery(cq).getResultList();
    }

    public List<BsmDocumenten> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmDocumenten.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<BsmDocumenten> rt = cq.from(BsmDocumenten.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }

}
