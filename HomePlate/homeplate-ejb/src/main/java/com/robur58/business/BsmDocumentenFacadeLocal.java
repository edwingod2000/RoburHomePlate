/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmDocumenten;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Gebruiker
 */
@Local
public interface BsmDocumentenFacadeLocal {

    void create(BsmDocumenten bsmDocumenten);

    void edit(BsmDocumenten bsmDocumenten);

    void remove(BsmDocumenten bsmDocumenten);

    BsmDocumenten find(Object id);
    
    BsmDocumenten findByName(String name);

    List<BsmDocumenten> findAll();

    List<BsmDocumenten> findRange(int[] range);

    int count();

}
