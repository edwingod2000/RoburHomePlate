/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmVGastenboekJaar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Gebruiker
 */
@Local
public interface BsmVGastenboekJaarFacadeLocal {

    void create(BsmVGastenboekJaar bsmVGastenboekJaar);

    void edit(BsmVGastenboekJaar bsmVGastenboekJaar);

    void remove(BsmVGastenboekJaar bsmVGastenboekJaar);

    BsmVGastenboekJaar find(Object id);

    List<BsmVGastenboekJaar> findAll();

    List<BsmVGastenboekJaar> findRange(int[] range);
 
    List<BsmVGastenboekJaar> findJaren();
    
    int count();

}
