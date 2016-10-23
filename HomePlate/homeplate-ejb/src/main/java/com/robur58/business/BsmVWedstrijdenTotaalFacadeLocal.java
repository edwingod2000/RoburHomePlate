/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmVWedstrijdenTotaal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Gebruiker
 */
@Local
public interface BsmVWedstrijdenTotaalFacadeLocal {

    void create(BsmVWedstrijdenTotaal bsmWedstrijdenTotaal);

    void edit(BsmVWedstrijdenTotaal bsmWedstrijdenTotaal);

    void remove(BsmVWedstrijdenTotaal bsmWedstrijdenTotaal);

    BsmVWedstrijdenTotaal find(Object id);

    List<BsmVWedstrijdenTotaal> findAll();

    List<BsmVWedstrijdenTotaal> findRange(int[] range);

    int count();

}
