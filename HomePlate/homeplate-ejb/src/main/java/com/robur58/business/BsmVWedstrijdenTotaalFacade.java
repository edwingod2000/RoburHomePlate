/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmVWedstrijdenTotaal;
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
public class BsmVWedstrijdenTotaalFacade extends AbstractFacade<BsmVWedstrijdenTotaal> implements BsmVWedstrijdenTotaalFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    public BsmVWedstrijdenTotaalFacade() {
        super(BsmVWedstrijdenTotaal.class);
    }
    
    public void create(BsmVWedstrijdenTotaal bsmWedstrijdenTotaal) {
        em.persist(bsmWedstrijdenTotaal);
    }

    public void edit(BsmVWedstrijdenTotaal bsmWedstrijdenTotaal) {
        em.merge(bsmWedstrijdenTotaal);
    }

    public void remove(BsmVWedstrijdenTotaal bsmWedstrijdenTotaal) {
        em.remove(em.merge(bsmWedstrijdenTotaal));
    }

    public BsmVWedstrijdenTotaal find(Object id) {
        return em.find(BsmVWedstrijdenTotaal.class, id);
    }

    public List<BsmVWedstrijdenTotaal> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmVWedstrijdenTotaal.class));
        return em.createQuery(cq).getResultList();
    }

    public List<BsmVWedstrijdenTotaal> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmVWedstrijdenTotaal.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<BsmVWedstrijdenTotaal> rt = cq.from(BsmVWedstrijdenTotaal.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    protected EntityManager getEntityManager() {
        return em;
    }    
}
