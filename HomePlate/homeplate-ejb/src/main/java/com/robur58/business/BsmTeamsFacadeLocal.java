/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmTeams;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Gebruiker
 */
@Local
public interface BsmTeamsFacadeLocal {

    void create(BsmTeams bsmTeams);

    void edit(BsmTeams bsmTeams);

    void remove(BsmTeams bsmTeams);

    BsmTeams find(Object id);

    List<BsmTeams> findAll();

    List<BsmTeams> findRange(int[] range);

    List<BsmTeams> findBySoortAndNotAll(String soort);

    BsmTeams findByVolgnr(Long volgnr);

    BsmTeams findHoofdteamBySoort(String soort);
    
    int count();

}
