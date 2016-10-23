/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmSpeellokaties;
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
public class BsmSpeellokatiesFacade extends AbstractFacade<BsmSpeellokaties> implements BsmSpeellokatiesFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    public BsmSpeellokatiesFacade() {
        super(BsmSpeellokaties.class);
    }
    
    public void create(BsmSpeellokaties bsmSpeellokaties) {
        em.persist(bsmSpeellokaties);
    }

    public void edit(BsmSpeellokaties bsmSpeellokaties) {
        em.merge(bsmSpeellokaties);
    }

    public void remove(BsmSpeellokaties bsmSpeellokaties) {
        em.remove(em.merge(bsmSpeellokaties));
    }

    public BsmSpeellokaties find(Object id) {
        return em.find(BsmSpeellokaties.class, id);
    }

    public List<BsmSpeellokaties> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmSpeellokaties.class));
        return em.createQuery(cq).getResultList();
    }

    public List<BsmSpeellokaties> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmSpeellokaties.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public BsmSpeellokaties findByVolgnr(Long volgnr) {
        Query q = em.createNamedQuery("BsmSpeellokaties.findByVolgnr");
        q.setParameter("volgnr", volgnr);
        return (BsmSpeellokaties)q.getSingleResult();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<BsmSpeellokaties> rt = cq.from(BsmSpeellokaties.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    protected EntityManager getEntityManager() {
        return em;
    }    
}
