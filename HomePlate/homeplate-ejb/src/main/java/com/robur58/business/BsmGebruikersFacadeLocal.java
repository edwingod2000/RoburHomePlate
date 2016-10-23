/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmGebruikers;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Gebruiker
 */
@Local
public interface BsmGebruikersFacadeLocal {

    void create(BsmGebruikers bsmGebruikers);

    void edit(BsmGebruikers bsmGebruikers);

    void remove(BsmGebruikers bsmGebruikers);

    BsmGebruikers find(Object id);

    List<BsmGebruikers> findAll();

    List<BsmGebruikers> findRange(int[] range);

    BsmGebruikers findByGebruikersId(String gebruikersId);

    BsmGebruikers findByRegistreerSid(String registreerSid);

    BsmGebruikers findByLidNummer(String lidNummer);
    
    int count();

}
