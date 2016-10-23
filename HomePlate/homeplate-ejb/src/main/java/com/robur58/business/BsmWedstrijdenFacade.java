package com.robur58.business;

import com.robur58.domein.BsmWedstrijden;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class BsmWedstrijdenFacade extends AbstractFacade<BsmWedstrijden> implements BsmWedstrijdenFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    public BsmWedstrijdenFacade() {
        super(BsmWedstrijden.class);
    }
    
    @EJB
    private BsmSpeellokatiesFacadeLocal speellokatiesFacade;
    
    public void create(BsmWedstrijden bsmWedstrijden) {
        em.persist(bsmWedstrijden);
    }

    public void edit(BsmWedstrijden bsmWedstrijden) {
        em.merge(bsmWedstrijden);
    }

    public void remove(BsmWedstrijden bsmWedstrijden) {
        em.remove(em.merge(bsmWedstrijden));
    }

    public BsmWedstrijden find(Object id) {
        return em.find(BsmWedstrijden.class, id);
    }

    public List<BsmWedstrijden> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmWedstrijden.class));
        return em.createQuery(cq).getResultList();
    }

    public List<BsmWedstrijden> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmWedstrijden.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public List<BsmWedstrijden> findByTeamVolgnrAndYear(Long teamVolgnr, String jaar) {
        List<BsmWedstrijden> result = null;
        try {
            Query q = em.createNamedQuery("BsmWedstrijden.findByTeamVolgnrAndYear");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date fromDate = sdf.parse("01-01-" + jaar);
            Date toDate = sdf.parse("31-12-" + jaar);
            q.setParameter("teamVolgnr", teamVolgnr);
            q.setParameter("fromDate", fromDate);
            q.setParameter("archief", "N");
            result = q.getResultList();
        }
        catch (ParseException ex) {
            Logger.getLogger(BsmWedstrijdenFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public BsmWedstrijden findFirstGameByTeamVolgnr(Long teamVolgnr) {
        List<BsmWedstrijden> wedstrijden = null;
        BsmWedstrijden wedstrijd = null;
       
        Calendar yesterday = Calendar.getInstance(); // today
        yesterday.add(Calendar.DAY_OF_YEAR, -1); // yesterday
        
                
        Query q = em.createNamedQuery("BsmWedstrijden.findFutureGamesByTeamVolgnr");
        q.setParameter("teamVolgnr", teamVolgnr);
        q.setParameter("fromDate", yesterday.getTime());
        q.setParameter("archief", "N");
        wedstrijden = q.getResultList();

        if (wedstrijden != null && wedstrijden.size() > 0) {
            wedstrijd = wedstrijden.get(0);
        }
        return wedstrijd;
    }
    
    public List<BsmWedstrijden> findByYear(String jaar) {
        List<BsmWedstrijden> result = null;
        try {
            Query q = em.createNamedQuery("BsmWedstrijden.findByYear");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date fromDate = sdf.parse("01-01-" + jaar);
            Date toDate = sdf.parse("31-12-" + jaar);
            q.setParameter("fromDate", fromDate);
            q.setParameter("archief", "N");
            result = q.getResultList();
        }
        catch (ParseException ex) {
            Logger.getLogger(BsmWedstrijdenFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public List<BsmWedstrijden> findBySoortAndYear(String soort, String jaar) {
        List<BsmWedstrijden> result = null;
        try {
            Query q = em.createNamedQuery("BsmWedstrijden.findBySoortAndYear");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date fromDate = sdf.parse("01-01-" + jaar);
            Date toDate = sdf.parse("31-12-" + jaar);
            q.setParameter("soort", soort);
            q.setParameter("fromDate", fromDate);
            q.setParameter("archief", "N");
            result = q.getResultList();
        }
        catch (ParseException ex) {
            Logger.getLogger(BsmWedstrijdenFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<BsmWedstrijden> rt = cq.from(BsmWedstrijden.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    protected EntityManager getEntityManager() {
        return em;
    }    
}
