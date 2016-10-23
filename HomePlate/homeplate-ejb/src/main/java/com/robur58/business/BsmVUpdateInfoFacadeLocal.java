/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robur58.business;

import com.robur58.domein.BsmVUpdateInfo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author godefrooije
 */
@Local
public interface BsmVUpdateInfoFacadeLocal {

    void create(BsmVUpdateInfo bsmVUpdateInfo);

    void edit(BsmVUpdateInfo bsmVUpdateInfo);

    void remove(BsmVUpdateInfo bsmVUpdateInfo);

    BsmVUpdateInfo find(Object id);

    List<BsmVUpdateInfo> findAll();

    List<BsmVUpdateInfo> findRange(int[] range);

    List<BsmVUpdateInfo> findAllOrderByDatum();
    
    int count();
    
}
