/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmGebruikers;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Gebruiker
 */
@Stateless
public class BsmGebruikersFacade extends AbstractFacade<BsmGebruikers> implements BsmGebruikersFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    public BsmGebruikersFacade() {
        super(BsmGebruikers.class);
    }
        
    public void create(BsmGebruikers bsmGebruikers) {
        em.persist(bsmGebruikers);
    }

    public void edit(BsmGebruikers bsmGebruikers) {
        em.merge(bsmGebruikers);
    }

    public void remove(BsmGebruikers bsmGebruikers) {
        em.remove(em.merge(bsmGebruikers));
    }

    public BsmGebruikers find(Object id) {
        return em.find(BsmGebruikers.class, id);
    }

    public List<BsmGebruikers> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmGebruikers.class));
        return em.createQuery(cq).getResultList();
    }

    public List<BsmGebruikers> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmGebruikers.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public BsmGebruikers findByGebruikersId(String gebruikersId) {
        Query q = em.createNamedQuery("BsmGebruikers.findByGebruikersid");
        q.setParameter("gebruikersid", gebruikersId);
        BsmGebruikers result = null;
        try {
            result = (BsmGebruikers)q.getSingleResult();
        } catch (NoResultException nre) {
            result = null;
        }
        return result;
    }

    public BsmGebruikers findByRegistreerSid(String registreerSid) {
        Query q = em.createNamedQuery("BsmGebruikers.findByRegistreerSid");
        q.setParameter("registreerSid", registreerSid);
        BsmGebruikers result = null;
        try {
            result = (BsmGebruikers)q.getSingleResult();
        } catch (NoResultException nre) {
            result = null;
        }
        return result;
    }

    public BsmGebruikers findByLidNummer(String lidNummer) {
        Query q = em.createNamedQuery("BsmGebruikers.findByLidNummer");
        q.setParameter("lidNummer", lidNummer);
        BsmGebruikers result = null;
        try {
            result = (BsmGebruikers)q.getSingleResult();
        } catch (NoResultException nre) {
            result = null;
        }
        return result;
    }
    
    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<BsmGebruikers> rt = cq.from(BsmGebruikers.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    protected EntityManager getEntityManager() {
        return em;
    }    
}
