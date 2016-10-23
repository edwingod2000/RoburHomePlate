/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmInfoPaginas;
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
public class BsmInfoPaginasFacade extends AbstractFacade<BsmInfoPaginas> implements BsmInfoPaginasFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    public BsmInfoPaginasFacade() {
        super(BsmInfoPaginas.class);
    }
    
    public void create(BsmInfoPaginas bsmInfoPaginas) {
        em.persist(bsmInfoPaginas);
    }

    public void edit(BsmInfoPaginas bsmInfoPaginas) {
        em.merge(bsmInfoPaginas);
    }

    public void remove(BsmInfoPaginas bsmInfoPaginas) {
        em.remove(em.merge(bsmInfoPaginas));
    }

    public BsmInfoPaginas find(Object id) {
        return em.find(BsmInfoPaginas.class, id);
    }

    public List<BsmInfoPaginas> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmInfoPaginas.class));
        return em.createQuery(cq).getResultList();
    }

    public BsmInfoPaginas findByVolgnr(Long volgnr) {
        Query q = em.createNamedQuery("BsmInfoPaginas.findByVolgnr");
        q.setParameter("volgnr", volgnr);
        return (BsmInfoPaginas)q.getSingleResult();
    }
    
    public List<BsmInfoPaginas> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmInfoPaginas.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<BsmInfoPaginas> rt = cq.from(BsmInfoPaginas.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }    

}
