/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robur58.business;

import com.robur58.domein.BsmGebruikers;
import com.robur58.domein.BsmMijnDocuments;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author godefrooije
 */
@Stateless
public class BsmMijnDocumentsFacade extends AbstractFacade<BsmMijnDocuments> implements BsmMijnDocumentsFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    public BsmMijnDocumentsFacade() {
        super(BsmMijnDocuments.class);
    }
        
    public void create(BsmMijnDocuments bsmMijnDocuments) {
        em.persist(bsmMijnDocuments);
        System.out.println("Volgnr: " + bsmMijnDocuments.getVolgnr());
    }

    public void edit(BsmMijnDocuments bsmMijnDocuments) {
        em.merge(bsmMijnDocuments);
    }

    public void remove(BsmMijnDocuments bsmMijnDocuments) {
        em.remove(em.merge(bsmMijnDocuments));
    }

    public BsmMijnDocuments find(Object id) {
        return em.find(BsmMijnDocuments.class, id);
    }

    public List<BsmMijnDocuments> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmMijnDocuments.class));
        return em.createQuery(cq).getResultList();
    }

    public List<BsmMijnDocuments> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(BsmMijnDocuments.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
    /**
     * if parentVolgnr is null then a search will be done on objects with no parent
     * @param parentVolgnr
     * @return 
     */
    public List<BsmMijnDocuments> findDirectoriesByMdsVolgnr(Long parentVolgnr, boolean published) {
        if (parentVolgnr == null) {
            String queryString = "BsmMijnDocuments.findRootDirectories";
            if (published) {
                queryString = queryString + "Published";
            }
            Query q = em.createNamedQuery(queryString);
            return q.getResultList();
        } else {
            String queryString = "BsmMijnDocuments.findDirectoriesByMdsVolgnr";
            if (published) {
                queryString = queryString + "Published";
            }
            Query q = em.createNamedQuery(queryString);
            q.setParameter("mdsvolgnr", parentVolgnr);
            return q.getResultList();
        }
    }
    
    public List<BsmMijnDocuments> findFilesByMdsVolgnr(Long parentVolgnr, boolean published) {
        if (parentVolgnr == null) {
            String queryString = "BsmMijnDocuments.findFilesNoMdsVolgnr";
            if (published) {
                queryString = queryString + "Published";
            }
            Query q = em.createNamedQuery(queryString);
            return q.getResultList();
        } else {
            String queryString = "BsmMijnDocuments.findFilesByMdsVolgnr";
            if (published) {
                queryString = queryString + "Published";
            }
            Query q = em.createNamedQuery(queryString);
            q.setParameter("mdsvolgnr", parentVolgnr);
            return q.getResultList();
        }
    }

    public void createDocument(String naam, String opslagnaam, String type, String omschrijving, BsmMijnDocuments parent, BsmGebruikers gbrVolgnr, Date datumAangemaakt, boolean gepubliceerd) {
        BsmMijnDocuments mijnDocument = new BsmMijnDocuments();
        mijnDocument.setNaam(naam);
        mijnDocument.setOpslagnaam(opslagnaam);
        mijnDocument.setFolder(false);
        mijnDocument.setType(type);
        mijnDocument.setOmschrijving(omschrijving);
        mijnDocument.setDatumAangemaakt(datumAangemaakt);
        mijnDocument.setGbrVolgnr(gbrVolgnr);
        mijnDocument.setGepubliceerd(gepubliceerd);
        if (parent != null) {
            mijnDocument.setMdsVolgnr(new BsmMijnDocuments());
            mijnDocument.getMdsVolgnr().setVolgnr(parent.getVolgnr());
        }
        try {
           this.create(mijnDocument);
        } catch(Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public void createFolder(String naam, String omschrijving, BsmMijnDocuments parent, BsmGebruikers gbrVolgnr, Date datumAangemaakt, boolean gepubliceerd) {
        BsmMijnDocuments mijnDocument = new BsmMijnDocuments();
        mijnDocument.setNaam(naam);
        mijnDocument.setOmschrijving(omschrijving);
        mijnDocument.setFolder(true);
        mijnDocument.setGbrVolgnr(gbrVolgnr);
        mijnDocument.setDatumAangemaakt(datumAangemaakt);
        mijnDocument.setGepubliceerd(gepubliceerd);
        if (parent != null) {
            mijnDocument.setMdsVolgnr(new BsmMijnDocuments());
            mijnDocument.getMdsVolgnr().setVolgnr(parent.getVolgnr());
        }
        try {
           this.create(mijnDocument);
        } catch(Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<BsmMijnDocuments> rt = cq.from(BsmMijnDocuments.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
