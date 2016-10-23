/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmLeden;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Gebruiker
 */
@Local
public interface BsmLedenFacadeLocal {

    void create(BsmLeden bsmLeden);

    void edit(BsmLeden bsmLeden);

    void remove(BsmLeden bsmLeden);

    BsmLeden find(Object id);

    List<BsmLeden> findAll();

    List<BsmLeden> findRange(int[] range);

    int count();

}
