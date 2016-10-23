/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmFuncties;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Gebruiker
 */
@Local
public interface BsmFunctiesFacadeLocal {

    void create(BsmFuncties bsmFuncties);

    void edit(BsmFuncties bsmFuncties);

    void remove(BsmFuncties bsmFuncties);

    BsmFuncties find(Object id);

    List<BsmFuncties> findAll();

    List<BsmFuncties> findRange(int[] range);

    List<BsmFuncties> findHoofdMenu();

    List<BsmFuncties> findSubMenu(Long funVolgnrFk);

    int count();

}
