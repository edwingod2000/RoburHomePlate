/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmTypeTeams;
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
public class BsmTypeTeamsFacade extends AbstractFacade<BsmTypeTeams> implements BsmTypeTeamsFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    public BsmTypeTeamsFacade() {
        super(BsmTypeTeams.class);
    }
    
    public void create(BsmTypeTeams bsmTypeTeams) {
        em.persist(bsmTypeTeams);
    }

    public void edit(BsmTypeTeams bsmTypeTeams) {
        em.merge(bsmTypeTeams);
    }

    public void remove(BsmTypeTeams bsmTypeTeams) {
        em.remove(em.merge(bsmTypeTeams));
    }

    public BsmTypeTeams find(Object id) {
        return em.find(BsmTypeTeams.class, id);
    }

    public List<BsmTypeTeams> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmTypeTeams.class));
        return em.createQuery(cq).getResultList();
    }

    public List<BsmTypeTeams> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmTypeTeams.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<BsmTypeTeams> rt = cq.from(BsmTypeTeams.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    protected EntityManager getEntityManager() {
        return em;
    }    
}
