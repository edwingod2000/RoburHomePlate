/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmFuncties;
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
public class BsmFunctiesFacade extends AbstractFacade<BsmFuncties> implements BsmFunctiesFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;
    
    public BsmFunctiesFacade() {
        super(BsmFuncties.class);
    }
    
    @Override
    public void create(BsmFuncties bsmFuncties) {
        em.persist(bsmFuncties);
    }

    @Override
    public void edit(BsmFuncties bsmFuncties) {
        em.merge(bsmFuncties);
    }

    @Override
    public void remove(BsmFuncties bsmFuncties) {
        em.remove(em.merge(bsmFuncties));
    }

    @Override
    public BsmFuncties find(Object id) {
        return em.find(BsmFuncties.class, id);
    }

    @Override
    public List<BsmFuncties> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmFuncties.class));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<BsmFuncties> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmFuncties.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    @Override
    public List<BsmFuncties> findHoofdMenu() {
        Query q = em.createNamedQuery("BsmFuncties.findForHoofdMenu");
        return q.getResultList();
    }

    @Override
    public List<BsmFuncties> findSubMenu(Long funVolgnrFk) {
        Query q = em.createNamedQuery("BsmFuncties.findByFunVolgnrFk");
        q.setParameter("funVolgnrFk", funVolgnrFk);
        return q.getResultList();
    }

    @Override
    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<BsmFuncties> rt = cq.from(BsmFuncties.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }    
}
