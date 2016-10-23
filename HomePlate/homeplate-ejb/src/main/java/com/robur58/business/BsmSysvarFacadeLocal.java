/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmSysvar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Gebruiker
 */
@Local
public interface BsmSysvarFacadeLocal {

    void create(BsmSysvar bsmSysvar);

    void edit(BsmSysvar bsmSysvar);

    void remove(BsmSysvar bsmSysvar);

    BsmSysvar find(Object id);

    List<BsmSysvar> findAll();

    List<BsmSysvar> findRange(int[] range);

    BsmSysvar findByCode(String code);
    
    int count();

}
