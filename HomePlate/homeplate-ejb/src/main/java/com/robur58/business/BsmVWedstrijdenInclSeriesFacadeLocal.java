/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmVWedstrijdenInclSeries;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Gebruiker
 */
@Local
public interface BsmVWedstrijdenInclSeriesFacadeLocal {

    void create(BsmVWedstrijdenInclSeries bsmWedstrijdenInclSeries);

    void edit(BsmVWedstrijdenInclSeries bsmWedstrijdenInclSeries);

    void remove(BsmVWedstrijdenInclSeries bsmWedstrijdenInclSeries);

    BsmVWedstrijdenInclSeries find(Object id);

    List<BsmVWedstrijdenInclSeries> findAll();

    List<BsmVWedstrijdenInclSeries> findRange(int[] range);
    
    int count();

}
