/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robur58.business;

import com.robur58.domein.BsmSysteemMededeling;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author godefrooije
 */
@Local
public interface BsmSysteemMededelingFacadeLocal {

    void create(BsmSysteemMededeling bsmSysteemMededeling);

    void edit(BsmSysteemMededeling bsmSysteemMededeling);

    void remove(BsmSysteemMededeling bsmSysteemMededeling);

    BsmSysteemMededeling find(Object id);

    List<BsmSysteemMededeling> findAll();

    List<BsmSysteemMededeling> findRange(int[] range);

    int count();
    
    List<BsmSysteemMededeling> findByType(String type);
}
