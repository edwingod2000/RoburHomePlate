/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmVWedstrijdenInclSeries;
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
public class BsmVWedstrijdenInclSeriesFacade extends AbstractFacade<BsmVWedstrijdenInclSeries> implements BsmVWedstrijdenInclSeriesFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    public BsmVWedstrijdenInclSeriesFacade() {
        super(BsmVWedstrijdenInclSeries.class);
    }
    
    public void create(BsmVWedstrijdenInclSeries bsmWedstrijdenInclSeries) {
        em.persist(bsmWedstrijdenInclSeries);
    }

    public void edit(BsmVWedstrijdenInclSeries bsmWedstrijdenInclSeries) {
        em.merge(bsmWedstrijdenInclSeries);
    }

    public void remove(BsmVWedstrijdenInclSeries bsmWedstrijdenInclSeries) {
        em.remove(em.merge(bsmWedstrijdenInclSeries));
    }

    public BsmVWedstrijdenInclSeries find(Object id) {
        return em.find(BsmVWedstrijdenInclSeries.class, id);
    }

    public List<BsmVWedstrijdenInclSeries> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmVWedstrijdenInclSeries.class));
        return em.createQuery(cq).getResultList();
    }

    public List<BsmVWedstrijdenInclSeries> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmVWedstrijdenInclSeries.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<BsmVWedstrijdenInclSeries> rt = cq.from(BsmVWedstrijdenInclSeries.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    protected EntityManager getEntityManager() {
        return em;
    }    
}
