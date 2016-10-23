/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robur58.business;

import com.robur58.domein.BsmMenu;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author godefrooije
 */
@Local
public interface BsmMenuFacadeLocal {

    void create(BsmMenu bsmMenu);

    void edit(BsmMenu bsmMenu);

    void remove(BsmMenu bsmMenu);

    BsmMenu find(Object id);

    List<BsmMenu> findAll();

    List<BsmMenu> findRange(int[] range);
  
    List<BsmMenu> findParents();
    
    int count();
    
}
