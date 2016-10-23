/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmKalender;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Gebruiker
 */
@Local
public interface BsmKalenderFacadeLocal {

    void create(BsmKalender bsmKalender);

    void edit(BsmKalender bsmKalender);

    void remove(BsmKalender bsmKalender);

    BsmKalender find(Object id);
    
    List<BsmKalender> findActual();
        
    List<BsmKalender> findAll();

    List<BsmKalender> findRange(int[] range);

    List<BsmKalender> findForHomepage();

    int count();

}
