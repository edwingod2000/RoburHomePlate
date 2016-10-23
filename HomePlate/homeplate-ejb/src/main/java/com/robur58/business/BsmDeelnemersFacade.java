package com.robur58.business;

import com.robur58.domein.BsmDeelnemers;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class BsmDeelnemersFacade extends AbstractFacade<BsmDeelnemers>  implements BsmDeelnemersFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    public BsmDeelnemersFacade() {
        super(BsmDeelnemers.class);
    }

    public void create(BsmDeelnemers bsmDeelnemers) {
        em.persist(bsmDeelnemers);
    }

    public void edit(BsmDeelnemers bsmDeelnemers) {
        em.merge(bsmDeelnemers);
    }

    public void remove(BsmDeelnemers bsmDeelnemers) {
        em.remove(em.merge(bsmDeelnemers));
    }

    public BsmDeelnemers find(Object id) {
        return em.find(BsmDeelnemers.class, id);
    }

    public List<BsmDeelnemers> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmDeelnemers.class));
        return em.createQuery(cq).getResultList();
    }

    public List<BsmDeelnemers> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmDeelnemers.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<BsmDeelnemers> rt = cq.from(BsmDeelnemers.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<BsmDeelnemers> findByTemVolgnr(Long temVolgnr) {
        Query q = em.createNamedQuery("BsmDeelnemers.findByTemVolgnr");
        q.setParameter("temVolgnr", temVolgnr);
        return q.getResultList();
    }

    public List<BsmDeelnemers> findByTemVolgnrExclusiveNonRegistered(Long temVolgnr) {
        Query q = em.createNamedQuery("BsmDeelnemers.findByTemVolgnrExclusiveNonRegistered");
        q.setParameter("temVolgnr", temVolgnr);
        return q.getResultList();
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }    
}
