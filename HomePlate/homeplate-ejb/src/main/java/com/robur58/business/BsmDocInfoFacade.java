/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robur58.business;

import com.robur58.domein.BsmDocInfo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author godefrooije
 */
@Stateless
public class BsmDocInfoFacade extends AbstractFacade<BsmDocInfo> implements BsmDocInfoFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BsmDocInfoFacade() {
        super(BsmDocInfo.class);
    }
    
}
