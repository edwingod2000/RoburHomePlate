/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robur58.business;

import com.robur58.domein.BsmTrainingstijd;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author godefrooije
 */
@Local
public interface BsmTrainingstijdFacadeLocal {

    void create(BsmTrainingstijd bsmTrainingstijd);

    void edit(BsmTrainingstijd bsmTrainingstijd);

    void remove(BsmTrainingstijd bsmTrainingstijd);

    BsmTrainingstijd find(Object id);

    List<BsmTrainingstijd> findAll();

    List<BsmTrainingstijd> findRange(int[] range);

    int count();
    
}
