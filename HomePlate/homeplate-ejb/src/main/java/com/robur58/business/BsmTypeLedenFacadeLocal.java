/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmTypeLeden;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Gebruiker
 */
@Local
public interface BsmTypeLedenFacadeLocal {

    void create(BsmTypeLeden bsmTypeLeden);

    void edit(BsmTypeLeden bsmTypeLeden);

    void remove(BsmTypeLeden bsmTypeLeden);

    BsmTypeLeden find(Object id);

    List<BsmTypeLeden> findAll();

    List<BsmTypeLeden> findRange(int[] range);

    int count();

}
