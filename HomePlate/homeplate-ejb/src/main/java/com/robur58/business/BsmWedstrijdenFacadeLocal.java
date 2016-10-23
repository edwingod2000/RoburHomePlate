/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmWedstrijden;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Gebruiker
 */
@Local
public interface BsmWedstrijdenFacadeLocal {

    void create(BsmWedstrijden bsmWedstrijden);

    void edit(BsmWedstrijden bsmWedstrijden);

    void remove(BsmWedstrijden bsmWedstrijden);

    BsmWedstrijden find(Object id);

    List<BsmWedstrijden> findAll();

    List<BsmWedstrijden> findRange(int[] range);

    List<BsmWedstrijden> findByTeamVolgnrAndYear(Long teamVolgnr, String jaar);
    
    BsmWedstrijden findFirstGameByTeamVolgnr(Long teamVolgnr);

    List<BsmWedstrijden> findByYear(String jaar);

    List<BsmWedstrijden> findBySoortAndYear(String soort, String jaar);

    int count();

}
