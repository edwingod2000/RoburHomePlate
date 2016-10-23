/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robur58.business;

import com.robur58.domein.BsmIdeeen;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author godefrooije
 */
@Local
public interface BsmIdeeenFacadeLocal {

    void create(BsmIdeeen bsmIdeeen);

    void edit(BsmIdeeen bsmIdeeen);

    void remove(BsmIdeeen bsmIdeeen);

    BsmIdeeen find(Object id);

    List<BsmIdeeen> findAll();

    List<BsmIdeeen> findRange(int[] range);

    int count();
    
}
