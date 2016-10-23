/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmInfo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Gebruiker
 */
@Local
public interface BsmInfoFacadeLocal {

    void create(BsmInfo bsmInfo);

    void edit(BsmInfo bsmInfo);

    void remove(BsmInfo bsmInfo);

    BsmInfo find(Object id);

    List<BsmInfo> findAll();

    List<BsmInfo> findRange(int[] range);

    List<BsmInfo> findByTitel(String titel);

    BsmInfo findByVolgnr(Long volgnr);
    
    List<BsmInfo> findByToonOpHomepageAndArchief(String toonOpHomepage, String archief);

    List<BsmInfo> findByTypeInfoAndArchief(Long typeInfoVolgnr, String archief);

    List<BsmInfo> findHighlights();

    int count();

}
