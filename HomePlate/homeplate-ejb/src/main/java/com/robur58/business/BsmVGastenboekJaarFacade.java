/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmVGastenboekJaar;
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
public class BsmVGastenboekJaarFacade extends AbstractFacade<BsmVGastenboekJaar> implements BsmVGastenboekJaarFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    public BsmVGastenboekJaarFacade() {
        super(BsmVGastenboekJaar.class);
    }
    
    public void create(BsmVGastenboekJaar bsmVGastenboekJaar) {
        em.persist(bsmVGastenboekJaar);
    }

    public void edit(BsmVGastenboekJaar bsmVGastenboekJaar) {
        em.merge(bsmVGastenboekJaar);
    }

    public void remove(BsmVGastenboekJaar bsmVGastenboekJaar) {
        em.remove(em.merge(bsmVGastenboekJaar));
    }

    public BsmVGastenboekJaar find(Object id) {
        return em.find(BsmVGastenboekJaar.class, id);
    }

    public List<BsmVGastenboekJaar> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmVGastenboekJaar.class));
        return em.createQuery(cq).getResultList();
    }

    public List<BsmVGastenboekJaar> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmVGastenboekJaar.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<BsmVGastenboekJaar> rt = cq.from(BsmVGastenboekJaar.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    
    public List<BsmVGastenboekJaar> findJaren() {
        Query q = em.createNamedQuery("BsmVGastenboekJaar.findOrderedJaren");
        return q.getResultList();
    }
            
    protected EntityManager getEntityManager() {
        return em;
    }    
}
