/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmSysvar;
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
public class BsmSysvarFacade extends AbstractFacade<BsmSysvar> implements BsmSysvarFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    public BsmSysvarFacade() {
        super(BsmSysvar.class);
    }
    
    public void create(BsmSysvar bsmSysvar) {
        em.persist(bsmSysvar);
    }

    public void edit(BsmSysvar bsmSysvar) {
        em.merge(bsmSysvar);
    }

    public void remove(BsmSysvar bsmSysvar) {
        em.remove(em.merge(bsmSysvar));
    }

    public BsmSysvar find(Object id) {
        return em.find(BsmSysvar.class, id);
    }

    public List<BsmSysvar> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmSysvar.class));
        return em.createQuery(cq).getResultList();
    }

    public List<BsmSysvar> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmSysvar.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<BsmSysvar> rt = cq.from(BsmSysvar.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    public BsmSysvar findByCode(String code) {
        BsmSysvar result = null;
        Query q = em.createNamedQuery("BsmSysvar.findByCode");
        q.setParameter("code", code);
        result = (BsmSysvar)q.getSingleResult();

        return result;
        
    }
}
