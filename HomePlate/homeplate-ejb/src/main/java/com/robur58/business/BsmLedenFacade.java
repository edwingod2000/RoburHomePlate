/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmLeden;
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
public class BsmLedenFacade extends AbstractFacade<BsmLeden> implements BsmLedenFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    public BsmLedenFacade() {
        super(BsmLeden.class);
    }
    
    public void create(BsmLeden bsmLeden) {
        em.persist(bsmLeden);
    }

    public void edit(BsmLeden bsmLeden) {
        em.merge(bsmLeden);
    }

    public void remove(BsmLeden bsmLeden) {
        em.remove(em.merge(bsmLeden));
    }

    public BsmLeden find(Object id) {
        return em.find(BsmLeden.class, id);
    }

    public List<BsmLeden> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmLeden.class));
        return em.createQuery(cq).getResultList();
    }

    public List<BsmLeden> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmLeden.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<BsmLeden> rt = cq.from(BsmLeden.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    protected EntityManager getEntityManager() {
        return em;
    }
}
