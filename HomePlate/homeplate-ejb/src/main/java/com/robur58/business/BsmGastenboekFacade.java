/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmGastenboek;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class BsmGastenboekFacade extends AbstractFacade<BsmGastenboek> implements BsmGastenboekFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    public BsmGastenboekFacade() {
        super(BsmGastenboek.class);
    }
    
    public void create(BsmGastenboek bsmGastenboek) {
        em.persist(bsmGastenboek);
        System.out.println("Volgnr: " + bsmGastenboek.getVolgnr());
    }

    public void edit(BsmGastenboek bsmGastenboek) {
        em.merge(bsmGastenboek);
    }

    public void remove(BsmGastenboek bsmGastenboek) {
        em.remove(em.merge(bsmGastenboek));
    }

    public BsmGastenboek find(Object id) {
        return em.find(BsmGastenboek.class, id);
    }

    public List<BsmGastenboek> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmGastenboek.class));
        return em.createQuery(cq).getResultList();
    }

    public List<BsmGastenboek> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmGastenboek.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public List<BsmGastenboek> findHomeplateByJaar(String jaar) {

        List<BsmGastenboek> result = null;
        try {
            Query q = em.createNamedQuery("BsmGastenboek.findHomeplateByJaar");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date fromDate = sdf.parse("01-01-" + jaar);
            Date toDate = sdf.parse("31-12-" + jaar);
            q.setParameter("fromDate", fromDate);
            q.setParameter("toDate", toDate);
            result = q.getResultList();
        }
        catch (ParseException ex) {
            Logger.getLogger(BsmGastenboekFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<BsmGastenboek> rt = cq.from(BsmGastenboek.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    protected EntityManager getEntityManager() {
        return em;
    }    
}
