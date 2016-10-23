package com.robur58.business;

import com.robur58.domein.BsmInfo;
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
public class BsmInfoFacade extends AbstractFacade<BsmInfo> implements BsmInfoFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    public BsmInfoFacade() {
        super(BsmInfo.class);
    }
    
    public void create(BsmInfo bsmInfo) {
        em.persist(bsmInfo);
    }

    public void edit(BsmInfo bsmInfo) {
        em.merge(bsmInfo);
    }

    public void remove(BsmInfo bsmInfo) {
        em.remove(em.merge(bsmInfo));
    }

    public BsmInfo find(Object id) {
        return em.find(BsmInfo.class, id);
    }

    public List<BsmInfo> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmInfo.class));
        return em.createQuery(cq).getResultList();
    }

    public List<BsmInfo> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmInfo.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public List<BsmInfo> findByTitel(String titel) {
        Query q = em.createNamedQuery("BsmInfo.findByTitel");
        q.setParameter("titel", titel);
        return q.getResultList();
    }

    public List<BsmInfo> findByToonOpHomepageAndArchief(String toonOpHomepage, String archief) {
        Query q = em.createNamedQuery("BsmInfo.findByToonOpHomepageAndArchief");
        q.setParameter("toonOpHomepage", toonOpHomepage);
        q.setParameter("archief", archief);
        return q.getResultList();
    }

    public List<BsmInfo> findByTypeInfoAndArchief(Long typeInfoVolgnr, String archief) {
        Query q = em.createNamedQuery("BsmInfo.findByTypeInfoAndArchief");
        q.setParameter("typeInfoVolgnr", typeInfoVolgnr);
        q.setParameter("archief", archief);
        return q.getResultList();
    }

    public BsmInfo findByVolgnr(Long volgnr) {
        Query q = em.createNamedQuery("BsmInfo.findByVolgnr");
        q.setParameter("volgnr", volgnr);
        return (BsmInfo) q.getSingleResult();
    }

    public List findHighlights() {
        return findByToonOpHomepageAndArchief("H", "N");
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<BsmInfo> rt = cq.from(BsmInfo.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    protected EntityManager getEntityManager() {
        return em;
    }
    
}
