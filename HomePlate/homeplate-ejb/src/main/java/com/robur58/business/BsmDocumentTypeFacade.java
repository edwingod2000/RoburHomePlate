/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmDocumentType;
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
public class BsmDocumentTypeFacade extends AbstractFacade<BsmDocumentType> implements BsmDocumentTypeFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    public BsmDocumentTypeFacade() {
        super(BsmDocumentType.class);
    }
    public void create(BsmDocumentType bsmDocumentType) {
        em.persist(bsmDocumentType);
    }

    public void edit(BsmDocumentType bsmDocumentType) {
        em.merge(bsmDocumentType);
    }

    public void remove(BsmDocumentType bsmDocumentType) {
        em.remove(em.merge(bsmDocumentType));
    }

    public BsmDocumentType find(Object id) {
        return em.find(BsmDocumentType.class, id);
    }

    public List<BsmDocumentType> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmDocumentType.class));
        return em.createQuery(cq).getResultList();
    }

    public List<BsmDocumentType> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmDocumentType.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<BsmDocumentType> rt = cq.from(BsmDocumentType.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    protected EntityManager getEntityManager() {
        return em;
    }    
}
