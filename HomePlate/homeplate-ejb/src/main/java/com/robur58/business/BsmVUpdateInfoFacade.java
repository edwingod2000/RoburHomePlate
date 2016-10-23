/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robur58.business;

import com.robur58.domein.BsmVUpdateInfo;
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
public class BsmVUpdateInfoFacade extends AbstractFacade<BsmVUpdateInfo> implements BsmVUpdateInfoFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public BsmVUpdateInfoFacade() {
        super(BsmVUpdateInfo.class);
    }

    @Override
    public List<BsmVUpdateInfo> findAllOrderByDatum() {
        Query q = em.createNamedQuery("BsmVUpdateInfo.findAllOrderByDatum");
        return q.getResultList();
    }
    
}
