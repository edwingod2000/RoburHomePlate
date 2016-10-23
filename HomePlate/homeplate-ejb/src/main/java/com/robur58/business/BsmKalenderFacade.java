/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmKalender;
import java.util.Calendar;
import java.util.Date;
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
public class BsmKalenderFacade extends AbstractFacade<BsmKalender> implements BsmKalenderFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    public BsmKalenderFacade() {
        super(BsmKalender.class);
    }
        
    public void create(BsmKalender bsmKalender) {
        em.persist(bsmKalender);
    }

    public void edit(BsmKalender bsmKalender) {
        em.merge(bsmKalender);
    }

    public void remove(BsmKalender bsmKalender) {
        em.remove(em.merge(bsmKalender));
    }

    public BsmKalender find(Object id) {
        return em.find(BsmKalender.class, id);
    }

    public List<BsmKalender> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmKalender.class));
        return em.createQuery(cq).getResultList();
    }

    public List<BsmKalender> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmKalender.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public List<BsmKalender> findActual() {
        Query q = em.createNamedQuery("BsmKalender.findByGreaterThanDatum");
        Date currentDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DAY_OF_WEEK, -1);
        
        q.setParameter("datum", cal.getTime());
        return q.getResultList();
    }

    public List<BsmKalender> findForHomepage() {
        Query q = em.createNamedQuery("BsmKalender.findByGreaterThanDatum");
        Date currentDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DAY_OF_WEEK, -1);
        
        q.setParameter("datum", cal.getTime());
        q.setMaxResults(10);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<BsmKalender> rt = cq.from(BsmKalender.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    protected EntityManager getEntityManager() {
        return em;
    }    
}
