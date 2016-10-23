/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmTypeTeams;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Gebruiker
 */
@Local
public interface BsmTypeTeamsFacadeLocal {

    void create(BsmTypeTeams bsmTypeTeams);

    void edit(BsmTypeTeams bsmTypeTeams);

    void remove(BsmTypeTeams bsmTypeTeams);

    BsmTypeTeams find(Object id);

    List<BsmTypeTeams> findAll();

    List<BsmTypeTeams> findRange(int[] range);

    int count();

}
