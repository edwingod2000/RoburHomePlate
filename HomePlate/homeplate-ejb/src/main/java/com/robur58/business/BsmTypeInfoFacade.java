/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmTypeInfo;
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
public class BsmTypeInfoFacade extends AbstractFacade<BsmTypeInfo> implements BsmTypeInfoFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    public BsmTypeInfoFacade() {
        super(BsmTypeInfo.class);
    }
    
    public void create(BsmTypeInfo bsmTypeInfo) {
        em.persist(bsmTypeInfo);
    }

    public void edit(BsmTypeInfo bsmTypeInfo) {
        em.merge(bsmTypeInfo);
    }

    public void remove(BsmTypeInfo bsmTypeInfo) {
        em.remove(em.merge(bsmTypeInfo));
    }

    public BsmTypeInfo find(Object id) {
        return em.find(BsmTypeInfo.class, id);
    }

    public List<BsmTypeInfo> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmTypeInfo.class));
        return em.createQuery(cq).getResultList();
    }

    public List<BsmTypeInfo> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmTypeInfo.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public BsmTypeInfo findByVolgnr(Long volgnr) {
        Query q = em.createNamedQuery("BsmTypeInfo.findByVolgnr");
        q.setParameter("volgnr", volgnr);
        return (BsmTypeInfo)q.getSingleResult();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<BsmTypeInfo> rt = cq.from(BsmTypeInfo.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    protected EntityManager getEntityManager() {
        return em;
    }    
}
