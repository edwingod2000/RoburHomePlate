/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmSponsorLogos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Gebruiker
 */
@Stateless
public class BsmSponsorLogosFacade extends AbstractFacade<BsmSponsorLogos> implements BsmSponsorLogosFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    public BsmSponsorLogosFacade() {
        super(BsmSponsorLogos.class);
    }
    
    public void create(BsmSponsorLogos bsmSponsorLogos) {
        em.persist(bsmSponsorLogos);
    }

    public void edit(BsmSponsorLogos bsmSponsorLogos) {
        em.merge(bsmSponsorLogos);
    }

    public void remove(BsmSponsorLogos bsmSponsorLogos) {
        em.remove(em.merge(bsmSponsorLogos));
    }

    public BsmSponsorLogos find(Object id) {
        return em.find(BsmSponsorLogos.class, id);
    }

    public List<BsmSponsorLogos> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmSponsorLogos.class));
        return em.createQuery(cq).getResultList();
    }

    public List<BsmSponsorLogos> findByTonenInCarroussel() {
        Query q = em.createNamedQuery("BsmSponsorLogos.findByTonenInCarroussel");
        q.setParameter("tonenInCarroussel", "YES");
        return q.getResultList();
    }
    
    public List<BsmSponsorLogos> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmSponsorLogos.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<BsmSponsorLogos> rt = cq.from(BsmSponsorLogos.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    protected EntityManager getEntityManager() {
        return em;
    }    
}
