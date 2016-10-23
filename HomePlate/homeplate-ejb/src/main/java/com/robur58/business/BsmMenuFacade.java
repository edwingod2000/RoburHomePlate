/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robur58.business;

import com.robur58.domein.BsmMenu;
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
public class BsmMenuFacade extends AbstractFacade<BsmMenu> implements BsmMenuFacadeLocal {
    @PersistenceContext(unitName = "HomePlate-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public BsmMenuFacade() {
        super(BsmMenu.class);
    }
    
    public List<BsmMenu> findParents() {
       Query q = em.createNamedQuery("BsmMenu.findParents");
       return q.getResultList();
    }
    
}
