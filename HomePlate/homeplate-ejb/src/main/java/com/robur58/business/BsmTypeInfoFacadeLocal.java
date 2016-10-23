/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.robur58.business;

import com.robur58.domein.BsmTypeInfo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Gebruiker
 */
@Local
public interface BsmTypeInfoFacadeLocal {

    void create(BsmTypeInfo bsmTypeInfo);

    void edit(BsmTypeInfo bsmTypeInfo);

    void remove(BsmTypeInfo bsmTypeInfo);

    BsmTypeInfo find(Object id);

    List<BsmTypeInfo> findAll();

    List<BsmTypeInfo> findRange(int[] range);

    BsmTypeInfo findByVolgnr(Long volgnr);

    int count();

}
