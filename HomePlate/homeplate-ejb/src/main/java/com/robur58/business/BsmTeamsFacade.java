/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmTeams;
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
public class BsmTeamsFacade extends AbstractFacade<BsmTeams> implements BsmTeamsFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    public BsmTeamsFacade() {
        super(BsmTeams.class);
    }
    
    public void create(BsmTeams bsmTeams) {
        em.persist(bsmTeams);
    }

    public void edit(BsmTeams bsmTeams) {
        em.merge(bsmTeams);
    }

    public void remove(BsmTeams bsmTeams) {
        em.remove(em.merge(bsmTeams));
    }

    public BsmTeams find(Object id) {
        return em.find(BsmTeams.class, id);
    }

    public List<BsmTeams> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmTeams.class));
        return em.createQuery(cq).getResultList();
    }

    public List<BsmTeams> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmTeams.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public List<BsmTeams> findBySoortAndNotAll(String soort) {
        Query q = em.createNamedQuery("BsmTeams.findBySoortAndNotAll");
        q.setParameter("soort", soort);
        return q.getResultList();
    }

    public BsmTeams findByVolgnr(Long volgnr) {
        try {
            Query q = em.createNamedQuery("BsmTeams.findByVolgnr");
            q.setParameter("volgnr", volgnr);
            return (BsmTeams)q.getSingleResult();
        } catch(NoResultException nre) {
            return null;
        }
    }

    public BsmTeams findHoofdteamBySoort(String soort) {
        Query q = em.createNamedQuery("BsmTeams.findHoofdteamBySoort");
        q.setParameter("soort", soort);
        return (BsmTeams)q.getSingleResult();
    }
    
    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<BsmTeams> rt = cq.from(BsmTeams.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    protected EntityManager getEntityManager() {
        return em;
    }    
}
