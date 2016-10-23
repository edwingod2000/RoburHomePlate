/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmTypeGastenboek;
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
public class BsmTypeGastenboekFacade extends AbstractFacade<BsmTypeGastenboek> implements BsmTypeGastenboekFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;
    
    public BsmTypeGastenboekFacade() {
        super(BsmTypeGastenboek.class);
    }
    
    public void create(BsmTypeGastenboek bsmTypeGastenboek) {
        em.persist(bsmTypeGastenboek);
    }

    public void edit(BsmTypeGastenboek bsmTypeGastenboek) {
        em.merge(bsmTypeGastenboek);
    }

    public void remove(BsmTypeGastenboek bsmTypeGastenboek) {
        em.remove(em.merge(bsmTypeGastenboek));
    }

    public BsmTypeGastenboek find(Object id) {
        return em.find(BsmTypeGastenboek.class, id);
    }

    public List<BsmTypeGastenboek> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmTypeGastenboek.class));
        return em.createQuery(cq).getResultList();
    }

    public List<BsmTypeGastenboek> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmTypeGastenboek.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<BsmTypeGastenboek> rt = cq.from(BsmTypeGastenboek.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    protected EntityManager getEntityManager() {
        return em;
    }    
}
