/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmTypeLeden;
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
public class BsmTypeLedenFacade extends AbstractFacade<BsmTypeLeden> implements BsmTypeLedenFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    public BsmTypeLedenFacade() {
        super(BsmTypeLeden.class);
    }
    
    public void create(BsmTypeLeden bsmTypeLeden) {
        em.persist(bsmTypeLeden);
    }

    public void edit(BsmTypeLeden bsmTypeLeden) {
        em.merge(bsmTypeLeden);
    }

    public void remove(BsmTypeLeden bsmTypeLeden) {
        em.remove(em.merge(bsmTypeLeden));
    }

    public BsmTypeLeden find(Object id) {
        return em.find(BsmTypeLeden.class, id);
    }

    public List<BsmTypeLeden> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmTypeLeden.class));
        return em.createQuery(cq).getResultList();
    }

    public List<BsmTypeLeden> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmTypeLeden.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<BsmTypeLeden> rt = cq.from(BsmTypeLeden.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    protected EntityManager getEntityManager() {
        return em;
    }    
}
