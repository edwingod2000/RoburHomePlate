/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmTypeGastenboek;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Gebruiker
 */
@Local
public interface BsmTypeGastenboekFacadeLocal {

    void create(BsmTypeGastenboek bsmTypeGastenboek);

    void edit(BsmTypeGastenboek bsmTypeGastenboek);

    void remove(BsmTypeGastenboek bsmTypeGastenboek);

    BsmTypeGastenboek find(Object id);

    List<BsmTypeGastenboek> findAll();

    List<BsmTypeGastenboek> findRange(int[] range);

    int count();

}
