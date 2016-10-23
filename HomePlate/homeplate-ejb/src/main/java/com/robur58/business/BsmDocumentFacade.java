/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robur58.business;

import com.robur58.domein.BsmDocument;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author godefrooije
 */
@Stateless
public class BsmDocumentFacade extends AbstractFacade<BsmDocument> implements BsmDocumentFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BsmDocumentFacade() {
        super(BsmDocument.class);
    }
    
    public List<BsmDocument> findByDceVolgnr(long dceVolgnr) {
        Query q = em.createNamedQuery("BsmDocument.findByDceVolgnr");
        q.setParameter("dceVolgnr", dceVolgnr);
        return q.getResultList();
    }
}
