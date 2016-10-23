/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robur58.business;

import com.robur58.domein.BsmSysteemMededeling;
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
public class BsmSysteemMededelingFacade extends AbstractFacade<BsmSysteemMededeling> implements BsmSysteemMededelingFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public BsmSysteemMededelingFacade() {
        super(BsmSysteemMededeling.class);
    }
    
    public List<BsmSysteemMededeling> findByType(String type) {
        Query q = em.createNamedQuery("BsmSysteemMededeling.findByType");
        q.setParameter("type", type);
        return q.getResultList();
    }

}
