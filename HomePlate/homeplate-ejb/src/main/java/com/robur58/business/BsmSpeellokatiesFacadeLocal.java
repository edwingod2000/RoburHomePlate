/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmSpeellokaties;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Gebruiker
 */
@Local
public interface BsmSpeellokatiesFacadeLocal {

    void create(BsmSpeellokaties bsmSpeellokaties);

    void edit(BsmSpeellokaties bsmSpeellokaties);

    void remove(BsmSpeellokaties bsmSpeellokaties);

    BsmSpeellokaties find(Object id);

    List<BsmSpeellokaties> findAll();

    List<BsmSpeellokaties> findRange(int[] range);
    
    BsmSpeellokaties findByVolgnr(Long volgnr);

    int count();

}
