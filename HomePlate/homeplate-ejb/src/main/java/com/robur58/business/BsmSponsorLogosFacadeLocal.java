/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmSponsorLogos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Gebruiker
 */
@Local
public interface BsmSponsorLogosFacadeLocal {

    void create(BsmSponsorLogos bsmSponsorLogos);

    void edit(BsmSponsorLogos bsmSponsorLogos);

    void remove(BsmSponsorLogos bsmSponsorLogos);

    BsmSponsorLogos find(Object id);

    List<BsmSponsorLogos> findAll();

    List<BsmSponsorLogos> findRange(int[] range);

    List<BsmSponsorLogos> findByTonenInCarroussel();
    
    int count();

}
