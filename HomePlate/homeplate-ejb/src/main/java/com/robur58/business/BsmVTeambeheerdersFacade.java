/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmVTeambeheerders;
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
public class BsmVTeambeheerdersFacade extends AbstractFacade<BsmVTeambeheerders> implements BsmVTeambeheerdersFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    public BsmVTeambeheerdersFacade() {
        super(BsmVTeambeheerders.class);
    }
    
    public void create(BsmVTeambeheerders bsmTeambeheerders) {
        em.persist(bsmTeambeheerders);
    }

    public void edit(BsmVTeambeheerders bsmTeambeheerders) {
        em.merge(bsmTeambeheerders);
    }

    public void remove(BsmVTeambeheerders bsmTeambeheerders) {
        em.remove(em.merge(bsmTeambeheerders));
    }

    public BsmVTeambeheerders find(Object id) {
        return em.find(BsmVTeambeheerders.class, id);
    }

    public List<BsmVTeambeheerders> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmVTeambeheerders.class));
        return em.createQuery(cq).getResultList();
    }

    public List<BsmVTeambeheerders> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmVTeambeheerders.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
    public BsmVTeambeheerders findByTemVolgnr(Long temVolgnr) {
        if (temVolgnr != null) {
            try {
                Query q = em.createNamedQuery("BsmVTeambeheerders.findByTemVolgnr");
                q.setParameter("temVolgnr", temVolgnr);
                return (BsmVTeambeheerders)q.getSingleResult();
            } catch(NoResultException nre) {
                return null;
            }
        } else {
            return null;
        }
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<BsmVTeambeheerders> rt = cq.from(BsmVTeambeheerders.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    protected EntityManager getEntityManager() {
        return em;
    }    
}
