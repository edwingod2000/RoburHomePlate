/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmInfoPaginas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Gebruiker
 */
@Local
public interface BsmInfoPaginasFacadeLocal {

    void create(BsmInfoPaginas bsmInfoPaginas);

    void edit(BsmInfoPaginas bsmInfoPaginas);

    void remove(BsmInfoPaginas bsmInfoPaginas);

    BsmInfoPaginas find(Object id);

    List<BsmInfoPaginas> findAll();

    List<BsmInfoPaginas> findRange(int[] range);

    BsmInfoPaginas findByVolgnr(Long volgnr);
    
    int count();

}
